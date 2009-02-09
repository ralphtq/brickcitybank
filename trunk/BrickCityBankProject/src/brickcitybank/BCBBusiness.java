/**
 * BCBBusiness.java
 * 
 * @author Louis Duke
 */
package brickcitybank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import rmi.*;
import brickcitybank.tools.*;
import brickcitybank.message.*;
import database.*;

/**
 * BCBBusiness
 * 
 *    This is the main 'routing' class of the server side of the Brick City Bank
 * application.
 * 
 * @author Louis Duke
 */
public class BCBBusiness {

	//variables
	private BCBServer myserv; //reference to the creating server
	private ActionTool myTool; //Base tool to access the database
	private JMSProducer myProd; //JMSProducer we use
	private DBConnection myConn;
	
	/**
	 * Constructor - Mostly empty; commented out for now
	 * 
	 * @param	-	ms	-	server creating this BCBBusiness Object
	 * @param	-	mc	-	Database Connection Object given by the creating server
	 */
	public BCBBusiness(BCBServer ms){
		//Store reference to creating BCBServer
		myserv = ms;
		
		//establish connection
		try
		{
			myConn = myserv.establishConn();
			System.out.println("Connection established");
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		
		//set up our tools
		DepositTool	myDeposit = new DepositTool(myConn,null);
		TransferTool myTrans = new TransferTool(myConn,myDeposit);
		WithdrawalTool myWithdraw = new WithdrawalTool(myConn,myTrans);
		myTool = myWithdraw;
		
		//create our JMSProducer object
		
		//myProd = new JMSProducer();
		
	}
	
	/**
	 * JMS message send functionality; returns boolean flag indicating
	 * send failure/success
	 * 
	 * @param mess	-	String holding the message to deliver to the JMSProducer
	 * @return		-	Boolean flag on send failure/success
	 */
	public boolean sendJMSMessage( String mess ){
		boolean retval = false;
		
		//Establish Connections
		myProd.createConnections();
		
		//send the message up
		retval = myProd.sendMessage(mess);
		
		return retval;
	}
	
	/**
	 * Authentication for ATMs; routed to the authentication module here
	 * 
	 * note: returns void for now, other returns possible in future
	 * 
	 * @param	dcn		Users Debit Card Number
	 * @param	pin		Users PIN
	 */
	public boolean authenticateATM( int dcn, int pin ){
		ResultSet rs;
		Statement state = null;
		int cardPin = 0, cardNum = 0;
		try
		{
			String query = "select idCredit_card, pin from credit_card where idCredit_card=\"" +dcn +"\"";
	        rs = state.executeQuery(query);
	        while(rs.next())
	        {
	            cardNum = rs.getInt(1);
	            cardPin = rs.getInt(2);
	        }
	        
	        if(cardPin == pin && cardPin != 0 && cardNum != 0)
	        {
	        	return true;
	        	// Login is valid!
	        	// 		Get the accounts for this user, store in variable, maybe array or ArrayList
	        	//		Get the balance for those accounts, store in another array/ArrayList
	        	//		Same index relates to same account (arrayA[3] is account , arrayB[3] is balance)
	        	//		BCBServer will get these accounts from getAccounts() method here, if login is valid.
	        	//		BCBServer will get balances from getBalances() method here, if login is valid.
	        	//			These 2 methods are below, stubbed/commented
	        }
	        else
	        {
	        	return false;
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// If error, say their login failed
		return false;
	}
	/*
	public int[] getAccounts()
	{
		// return accounts after getting from DB
	}
	
	public int[] getBalances()
	{
		// return balances after getting from DB
	}
	*/
	
	
	
	/**
	 * Authentication means for the Web site; routed to the authentication module here
	 * 
	 * @param	uName	User Name
	 * @param	passW	Password for Users Account
	 */
	public int authenticateWeb( String uName, String passW ){
		ResultSet rs = null;
		Statement state = null;
		int userID = 0;
		try
		{
			System.out.println("trying");
			state = myConn.getConn().createStatement();
			System.out.println("connected");
			String query = "select idUser from User where UserName=\'" +uName +"' and Password='"+passW +"'";
	        System.out.println(query);
			rs = state.executeQuery(query);
	        while(rs.next())
	        {
	            userID = rs.getInt(1);
	            System.out.println(rs.getInt(1));
	        }
	        state.close();
			//myConn.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			userID = -1;
		}
		System.out.println(userID);
		// If error, say their login failed
		return userID;
	}
	
	public ArrayList<Account> getAccount(int id, String type)
	{
		ResultSet rs = null;
		Statement state = null;
		ArrayList<Account> reply = new ArrayList<Account>();
		double balance;
		int accountNum;
		int accountType;
		try
		{
			state = myConn.getConn().createStatement();
			String query = "select account_number, balance from account, user_accounts, " +type +" where account.idAccount = user_accounts.account_number and " +type +".id" +type +"=user_accounts.account_number and user_id=\'" +id +"'";
	        System.out.println("QUERY: " +query);
			rs = state.executeQuery(query);
	        while(rs.next())
	        {
	        	accountNum = rs.getInt(1);
	            balance = rs.getDouble(2);
	            reply.add(new Account(accountNum, balance));
	        }
	        
	        state.close();
			//myConn.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// If error, say their login failed
		return reply;
	}
}
