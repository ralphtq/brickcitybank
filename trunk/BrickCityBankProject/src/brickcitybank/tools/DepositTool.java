/**
 * 
 */
package brickcitybank.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public MessageResponse executeAction(MessageOrder m) {
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
			
			if (rs.first()){
				//means it's a loan
				
					sumToUpdate = rs.getDouble("Balance");
				
				if (sumToUpdate >= ((MessageOrderMoney)m).getSum()){
					
					//means the sum to add is < than the current balance the user owes
					//add it the the account (update)
					sumToUpdate -= ((MessageOrderMoney)m).getSum();
				}else{
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
			
			int successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
			System.out.println(successQuery +"<- successquery");
			if (successQuery > 0)
			{	
				//success in updating
				return new MessageResponse("Your Balance has been succesfully updated, the current balance is "+sumToUpdate);
			}
			else
			{
				//can't update
				throw new  CannotUpdateException("Your Loan has not been updated, please try again");
			}
				
		
		}
		catch (AccountNotFoundException e){
				//when the Account is not found
				return new MessageResponse(e.getMessage());
			}
		catch (CannotUpdateException e){
			//when it's not possible to update the balance
			return new MessageResponse(e.getMessage());
		}
		catch (SumIncorrectException e){
			//when a user entered a non valid sum of money
			return new MessageResponse(e.getMessage());
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			//System.err.println(" Vendor Message: " + e.getErrorCode());
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	}
}
