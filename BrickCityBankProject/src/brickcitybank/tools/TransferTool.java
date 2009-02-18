/**
 * 
 */
package brickcitybank.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.transaction.*;

import database.DBConnection;
import exception.AccountNotFoundException;
import exception.CannotUpdateException;
import exception.SumIncorrectException;

import brickcitybank.message.MessageOrder;
import brickcitybank.message.MessageOrderMoney;
import brickcitybank.message.MessageResponse;
import brickcitybank.message.MessageTransfer;


/**
 * @author Caroline
 *
 */
public class TransferTool extends ActionTool{

	String old1, old2, new1, new2;
	int successQuery;
	/**
	 * Constructor
	 * @param connector
	 * @param succ
	 */
	
	public TransferTool(DBConnection connector, ActionTool succ) 
	{
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MessageResponse executeAction(MessageOrder m) 
	{
		System.out.println("Tranfer in execution");
		// TODO Auto-generated method stub
		try{
		this.getConnector().getConn().setAutoCommit(true);
		Statement state = null;
		ResultSet rs = null;
		state = getConnector().getConn().createStatement();
		//first check the sum is positive
		if (((MessageTransfer)m).getSumToTransfer()<0)
		{
			throw new SumIncorrectException("Sum to Transfert must be > 0");
		}
		else
		{
			System.out.println("HERE I AM");
			System.out.println("my idAccount :"+m.getIdAcount());
			//the sum is > 0
			double sumToUpdate = 0;
			//check if idAccount number 1 has enough money 
			System.out.println("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
			rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
			if (rs.first())
			{
				//means it has an entry
				System.out.println("ok");
				sumToUpdate = rs.getDouble("Balance");
				System.out.println("Account1 found, balance :" + sumToUpdate);
				if(sumToUpdate < ((MessageTransfer)m).getSumToTransfer())
				{
					//means that the user wants to withdraw more than he owes
					throw new SumIncorrectException("You want to transfer more than you have, your current balance is :"+sumToUpdate);
	
				}else
				{
					//acount1 has enough money
					sumToUpdate -= ((MessageTransfer)m).getSumToTransfer();
					//money of account2
					double sumToUpdateAc2 = 0;
					//check account 2 loan or not
					rs = state.executeQuery("SELECT Balance FROM Account,Loan WHERE idAccount ="+((MessageTransfer)m).getToIdAccount()+" AND idLoan="+((MessageTransfer)m).getToIdAccount());					
					if (rs.first()){
						//means it's a loan
						
						sumToUpdateAc2 = rs.getDouble("Balance");
						
						if (sumToUpdateAc2 >= ((MessageTransfer)m).getSumToTransfer())
						{
							
							//means the sum to add is < than the current balance the user owes
							//add it the the account (update)
							sumToUpdateAc2 -= ((MessageTransfer)m).getSumToTransfer();
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
						rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+((MessageTransfer)m).getToIdAccount());
						if (rs.first()){
							//means it has an entry
							
							sumToUpdateAc2 = rs.getDouble("Balance");
							sumToUpdateAc2 += ((MessageTransfer)m).getSumToTransfer();
						}
						else
						{
							throw new AccountNotFoundException("Account not found");
						}
					}
					//update both account
					//account 1 = balance1 - sum to transfer
					//account 2 = balance2 + sum to transfer
						
						
					int successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
							
					if (successQuery >0)
					{
						//succes in updating account1
						successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdateAc2 + " WHERE idAccount ="+((MessageTransfer)m).getToIdAccount()+" ");
								
						if (successQuery > 0)
						{
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
									"values ('T', '"+m.getIdAcount() +"', '" +m.getIdAcount() +"', '" +date +"', '" +time +"', '" +old1 +"', '" +new1 +"', '" +old2 +"', '" +new2 +"')";
									
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

							return new MessageResponse("Your Balance has been succesfully updated, the current balance is "+sumToUpdate);
						}
						else
						{
							//can't update
							throw new  CannotUpdateException("Your Loan has not been updated, please try again");
						}
					}
					else
					{
						//can't update
						throw new  CannotUpdateException("Your Loan has not been updated, please try again");
					}
				}	
			}
			else
			{
				throw new AccountNotFoundException("Account not found");
			}
			
		}
		
		}
		catch(Exception e)
		{
		try
		{
			//this.getConnector().getConn().rollback();
			System.err.println(e.getMessage());
			return new MessageResponse(e.getMessage());
			
		}
		catch(Exception e2)
		{
		
		System.err.println(e2.getMessage());
		System.err.println("  Error Message: " + e.getMessage());
		return new MessageResponse(e.getMessage());
		//System.err.println(" Vendor Message: " + e.getErrorCode());
		}
	}
	finally
	{
		//change the mode as it was before
		try
		{
			this.getConnector().getConn().setAutoCommit(true);
		}
		catch(Exception e2) {}
	}
}

	@Override
	/**
	 * 
	 */
	public boolean existAction(MessageOrder m) {
		boolean response = false;
		
		if(m instanceof MessageTransfer)
			response = true;
		else
			response = false;
		
		//System.out.println("transfer!"+ response);	
		System.out.println("Transfer tool response :"+ response);
			
		return response;
	}

}
