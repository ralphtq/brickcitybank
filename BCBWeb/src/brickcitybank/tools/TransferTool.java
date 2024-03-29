/**
 * 
 */
package brickcitybank.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	/**
	 * Constructor
	 * @param connector
	 * @param succ
	 */
	
	public TransferTool(DBConnection connector, ActionTool succ) {
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MessageResponse executeAction(MessageOrder m) {
		// TODO Auto-generated method stub
		try{
		this.getConnector().getConn().setAutoCommit(false);
		Statement state = null;
		ResultSet rs = null;
		
		//first check the sum is positive
		if (((MessageTransfer)m).getSumToTransfer()<0){
			throw new SumIncorrectException("Sum to Transfert must be > 0");
		}else{
			//the sum is > 0
			double sumToUpdate = 0;
			//check if idAccount number 1 has enough money 
			rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+m.getIdAcount());
			if (rs.first()){
				//means it has an entry
				
				sumToUpdate = rs.getDouble("Balance");
				
				if(sumToUpdate < ((MessageTransfer)m).getSumToTransfer()){
					//means that the user wants to withdraw more than he owes
					throw new SumIncorrectException("You want to transfer more than you have, your current balance is :"+sumToUpdate);
	
				}else{
					//acount1 has enough money
					sumToUpdate -= ((MessageTransfer)m).getSumToTransfer();
					//money of account2
					double sumToUpdateAc2 = 0;
					//check account 2 loan or not
					rs = state.executeQuery("SELECT Balance FROM Account,Loan WHERE idAccount ="+((MessageTransfer)m).getToIdAccount()+" AND idLoan="+((MessageTransfer)m).getToIdAccount());					
					if (rs.first()){
						//means it's a loan
						
						sumToUpdateAc2 = rs.getDouble("Balance");
						
						if (sumToUpdateAc2 >= ((MessageTransfer)m).getSumToTransfer()){
							
							//means the sum to add is < than the current balance the user owes
							//add it the the account (update)
							sumToUpdateAc2 -= ((MessageTransfer)m).getSumToTransfer();
						}else{
							//the user entered to much money for the loan
							throw new  SumIncorrectException("Your Loan has not been updated, you try to add to much money, the current balance is "+sumToUpdate);
							}
					}else{
						//it's not a loan
						rs = state.executeQuery("SELECT Balance FROM Account WHERE idAccount ="+((MessageTransfer)m).getToIdAccount());
						if (rs.first()){
							//means it has an entry
							
							sumToUpdateAc2 = rs.getDouble("Balance");
							sumToUpdateAc2 += ((MessageOrderMoney)m).getSum();
						}else{
							throw new AccountNotFoundException();
						}
					}
					//update both account
					//account 1 = balance1 - sum to transfer
					//account 2 = balance2 + sum to transfer
						
						
					boolean successQuery = state.execute("UPDATE Account set Balance="+sumToUpdate + " WHERE idAccount ="+m.getIdAcount()+" ");
							
					if (successQuery){
						//succes in updating account1
						successQuery = state.execute("UPDATE Account set Balance="+sumToUpdateAc2 + " WHERE idAccount ="+((MessageTransfer)m).getToIdAccount()+" ");
								
						if (successQuery){
									
							//commit
							this.getConnector().getConn().commit();
							return new MessageResponse("Your Balance has been succesfully updated, the current balance is "+sumToUpdate);
						}else{
							//can't update
							throw new  CannotUpdateException("Your Loan has not been updated, please try again");
						}
					}else{
						//can't update
						throw new  CannotUpdateException("Your Loan has not been updated, please try again");
					}
				}	
			}else{
				throw new AccountNotFoundException();
			}
			
		}
		
		}catch(Exception e)
	{
		try{
			this.getConnector().getConn().rollback();
			System.err.println(e.getMessage());
			return new MessageResponse(e.getMessage());
			
		}catch(Exception e2)
		{
		
		System.err.println(e2.getMessage());
		System.err.println("  Error Message: " + e.getMessage());
		return new MessageResponse(e.getMessage());
		//System.err.println(" Vendor Message: " + e.getErrorCode());
	}
	}
		finally{
		//change the mode as it was before
			try{
		this.getConnector().getConn().setAutoCommit(true);
			}catch(Exception e2)
			{}
		}
	}

	@Override
	/**
	 * 
	 */
	public boolean existAction(MessageOrder m) {
		boolean response = false;
		
		response = (m instanceof MessageTransfer );
			
			
		return response;
	}

}
