/**
 * 
 */
package brickcitybank.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import brickcitybank.message.MessageOrder;
import brickcitybank.message.MessageOrderMoney;
import brickcitybank.message.MessageResponse;

import database.DBConnection;
import exception.AccountNotFoundException;
import exception.CannotUpdateException;
import exception.SumIncorrectException;

/**
 * @author Caroline
 * Class used to handle the Message to depose money
 */
public class DepositTool extends ActionTool{

	String old1, old2, new1, new2;
	int successQuery;
	
	public DepositTool(DBConnection connector, ActionTool succ) {
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	

	
	@Override
	/**
	 * Return true if the MessageOrder is a deposit Order
	 */
	public boolean existAction(MessageOrder m) {
		boolean response = false;
		
		if (m instanceof MessageOrderMoney )
			{
			response = (((MessageOrderMoney) m).getTYPE_TRANSACTION() == DEPOSIT);
			
			}
		
		System.out.println("Deposit tool response :"+ response);
		return response;
	}
	
	
	@Override
	/**
	 * 
	 */
	public MessageResponse executeAction(MessageOrder m) 
	{
		System.out.println("Top of executeAction in DepositTool");
		if(((MessageOrderMoney)m).getSum()<0){
			//can't accept the sum must be >0
			return new MessageResponse("Sum to depose must be positive!");
			
		}else{
			//the sum is positive
		
		Statement state = null;
		ResultSet rs = null;
		double sumToUpdate = 0;
		try
		{
			state = getConnector().getConn().createStatement();
			//first get the current balance of the Account
			//case of a loan, the jointure is not empty
			rs = state.executeQuery("SELECT Balance FROM Account,Loan WHERE idAccount ="+m.getIdAcount()+" AND idLoan="+m.getIdAcount());
			
			if (rs.first())
			{
				//means it's a loan
				sumToUpdate = rs.getDouble("Balance");
				
				if (sumToUpdate >= ((MessageOrderMoney)m).getSum())
				{
					
					//means the sum to add is < than the current balance the user owes
					//add it the the account (update)
					sumToUpdate -= ((MessageOrderMoney)m).getSum();
				}
				else
				{
					//the user entered to much money for the loan
					throw new  SumIncorrectException("Your Loan has not been updated, you try to add to much money, the current balance is "+sumToUpdate);
				}
			}
			else
			{
				//it's not a loan
				rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
				if (rs.first())
				{
					//means it has an entry
					sumToUpdate = rs.getDouble("Balance");
					sumToUpdate += ((MessageOrderMoney)m).getSum();
				}
				else
				{
					throw new AccountNotFoundException();
				}
			}
			
			try
			{
				// Get old balance
				rs = state.executeQuery("select balance from account where idAccount =" +m.getIdAcount());
				old1 = "0";
				old2 = "0";
				new1 = "0";
				new2 = "0";
				while(rs.next())
				{
					old1 = rs.getString(1);
					new1 = old1;
				}
			}
			catch(Exception e) {e.printStackTrace();}
			
			try
			{
				successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
			}
			catch(Exception e) {e.printStackTrace();}

			try
			{
				rs = state.executeQuery("select balance from account where idAccount =" +m.getIdAcount());
				while(rs.next())
				{
					old2 = rs.getString(1);
					new2 = old2;
				}
	
				if (successQuery > 0)
				{	
					//success in updating
					
					// Insert this transaction into transaction table
					String DATE_FORMAT_NOW = "yyyy-MM-dd";
					String TIME_FORMAT_NOW = "HH:mm:ss";
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
					String date = sdf.format(cal.getTime());
					sdf = new SimpleDateFormat(TIME_FORMAT_NOW);
					String time = sdf.format(cal.getTime());
					System.out.println("Date: " +date +" time: " +time);
	
					String query = "insert into transaction (type, account1, account2, Date, Time, old_balance1, new_balance1, old_balance2, new_balance2) " +
					"values ('D', '"+m.getIdAcount() +"', '" +m.getIdAcount() +"', '" +date +"', '" +time +"', '" +old1 +"', '" +new1 +"', '" +old2 +"', '" +new2 +"')";
					
					System.out.println(query);
					state.executeUpdate(query);
					
					return new MessageResponse("Your Balance has been succesfully updated, the current balance is "+sumToUpdate);
				}
				else
				{
					//can't update
					throw new  CannotUpdateException("Your Loan has not been updated, please try again");
				}
			}
			catch (CannotUpdateException e)
			{
				//when it's not possible to update the balance
				return new MessageResponse(e.getMessage());
			}
			catch(Exception e) 
			{ 
				e.printStackTrace();
			}
			
		}
		catch (AccountNotFoundException e)
		{
			//when the Account is not found
			return new MessageResponse(e.getMessage());
		}
		
		catch (SumIncorrectException e)
		{
			//when a user entered a non valid sum of money
			return new MessageResponse(e.getMessage());
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			//System.err.println(" Vendor Message: " + e.getErrorCode());
		}
		catch(Exception e) { e.printStackTrace(); }
		
		return null;
	}
}
}
