/**
 * Authenticator.java
 * 
 */

package brickcitybank;

import java.sql.ResultSet;
import java.sql.Statement;
import database.*;

/**
 * Authenticator
 * 
 * Authentication module for the Brick City Bank program.
 * 
 * @author Louis Duke
 */

public class Authenticator {

	//Variables
	DBConnection myConn;
	
	//Constructor(s)
	
	/**
	 * Authenticator()
	 * 
	 * Default Constructor
	 */
	public Authenticator(){
		
	}
		
	//Methods
	
	/**
	 * setConnection(DBConnection conn)
	 * 
	 * 'Set' i.e. hand the authenticator an existing database connection
	 * 
	 * @param	conn	-	Database connection to use
	 */
	public void setConn(DBConnection conn){
		myConn = conn;
	}
	
	/**
	 * ATM Authentication Logic; returns login success/failure
	 *  
	 * @param 	cardNum	- Number of the Debit card being 'used' in the ATM 
	 * @param 	pin		- PIN supplied by the user of the ATM
	 * 
	 * @return	boolean	- Boolean indicating good/bad login attempt
	 * 
	 * @author	Jeff Mueller 
	 * @author	Caroline Malnoe
	 */
	public boolean ATMAuthentication(int cardN, int pin){
		ResultSet rs;
		Statement state = null;
		int cardPin = 0, cardNum = 0;
		try
		{
			String query = "select idCredit_card, pin from credit_card where idCredit_card=\"" +cardN +"\"";
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
	
	/**
	 * Web Authentication Logic; returns login success/failure
	 * 
	 * @param userN		- User Name supplied by the user
	 * @param passW		- Password supplied by the user
	 * 
	 * @return	int		- userID result from login query
	 * 
	 * @author	Jeff Mueller 
	 * @author	Caroline Malnoe
	 */
	public int WebAuthentication( String userN, String passW ){
		ResultSet rs = null;
		Statement state = null;
		int userID = 0;
		try
		{
			System.out.println("trying");
			state = myConn.getConn().createStatement();
			System.out.println("connected");
			String query = "select idUser from User where UserName=\'" + userN +"' and Password='"+passW +"'";
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
	
	
}//End Authenticator
