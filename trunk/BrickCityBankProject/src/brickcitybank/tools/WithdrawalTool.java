/**
 * 
 */
package brickcitybank.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import brickcitybank.message.*;
import database.DBConnection;
import exception.AccountNotFoundException;
import exception.CannotUpdateException;
import exception.SumIncorrectException;

/**
 * @author Caroline
 * Class used to handle the Message to withdraw money
 *
 */
public class WithdrawalTool extends ActionTool{

	
	/**
	 * Constructor
	 * @param connector
	 * @param succ
	 */
	public WithdrawalTool(DBConnection connector, ActionTool succ) 
	{
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * 
	 */
	public MessageResponse executeAction(MessageOrder m)
	{
		double old1=0,  new1=0;
		
		if(((MessageOrderMoney)m).getSum()<0)
		{
			//can't accept the sum must be >0
			return new MessageResponse("Sum to withdraw must be positive!");
			
		}
		else
		{
			Statement state = null;
			ResultSet rs = null;
			double sumToUpdate = 0;
			try
			{
				state = getConnector().getConn().createStatement();
				//first get the current balance of the Account
				//case of a loan, the jointure is not empty
				rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
				if (rs.first())
				{
					//means it has an entry
					
					sumToUpdate = rs.getDouble("Balance");
					old1 = sumToUpdate;
					
					if(sumToUpdate <= ((MessageOrderMoney)m).getSum())
					{
						//means that the user wants to withdraw more than he owes
						throw new SumIncorrectException("You want to withdraw more than you have, your current balance is :"+sumToUpdate);
		
					}
					else
					{
						//correct amount
					
						sumToUpdate -= ((MessageOrderMoney)m).getSum();
						new1 =sumToUpdate;
				
				
						int successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
						
						if (successQuery > 0)
						{
							//success in updating
							
							
							try
							{
								successQuery = state.executeUpdate("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
							}
							catch(Exception e) {e.printStackTrace();}
							
							try
							{
//								rs = state.executeQuery("select balance from account where idAccount =" +m.getIdAcount());
//								while(rs.next())
//								{
//									old2 = rs.getString(1);
//									new2 = old2;
//								}
//					
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
									//System.out.println("Date: " +date +" time: " +time);
					
									String query = "insert into transaction (type, account1, account2, Date, Time, old_balance1, new_balance1, old_balance2, new_balance2) " +
									"values ('W', '"+m.getIdAcount() +"', '" +m.getIdAcount() +"', '" +date +"', '" +time +"', '" +old1 +"', '" +new1 +"', '" +old1 +"', '" +new1 +"')";
									
									//System.out.println(query);
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
							throw new  CannotUpdateException("Your Balance has not been updated, please try again");
						}
					}
				}
				else
				{
					//the account number doesn't exist
					throw new AccountNotFoundException();
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
					System.err.println(" Vendor Message: " + e.getErrorCode());
				}
				catch(Exception e) { e.printStackTrace(); }
				
				return null;
		}
	}

	@Override
	/**
	 * 
	 */
	public boolean existAction(MessageOrder m) {
		boolean response = false;
		
		if (m instanceof MessageOrderMoney )
		{
			response = (((MessageOrderMoney) m).getTYPE_TRANSACTION() == WITHDRAW);
			//System.out.println("withdraw!!!!!! response: "+ response +" end response");
		}
		//System.out.println("Withdrawal tool response :"+ response);
			
		return response;
	}
	

}
