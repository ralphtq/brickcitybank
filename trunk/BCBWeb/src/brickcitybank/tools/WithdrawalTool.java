/**
 * 
 */
package brickcitybank.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public WithdrawalTool(DBConnection connector, ActionTool succ) {
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * 
	 */
	public MessageResponse executeAction(MessageOrder m) {
		
		if(((MessageOrderMoney)m).getSum()<0){
			//can't accept the sum must be >0
			return new MessageResponse("Sum to withdraw must be positive!");
			
		}else{
			Statement state = null;
			ResultSet rs = null;
			double sumToUpdate = 0;
			try
			{
				state = getConnector().getConn().createStatement();
				//first get the current balance of the Account
				//case of a loan, the jointure is not empty
				rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
					if (rs.first()){
						//means it has an entry
						
						sumToUpdate = rs.getDouble("Balance");
						
						if(sumToUpdate <= ((MessageOrderMoney)m).getSum()){
							//means that the user wants to withdraw more than he owes
							throw new SumIncorrectException("You want to withdraw more than you have, your current balance is :"+sumToUpdate);
			
						}else{
							//correct amount
						
							sumToUpdate -= ((MessageOrderMoney)m).getSum();
					
					
							boolean successQuery = state.execute("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
						
							if (successQuery){
								//succes in updating
								return new MessageResponse("Your Balance has been succesfully updated, the current balance is "+sumToUpdate);
							}else{
							//can't update
							throw new  CannotUpdateException("Your Balance has not been updated, please try again");
							}
						}
					}else{
						//the account number doesn't exist
						throw new AccountNotFoundException();
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
			
		}
		return null;
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
			}
			
		return response;
	}
	

}
