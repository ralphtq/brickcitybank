/**
 * 
 * 
 * @author Louis Duke
 *
 * Unless otherwise noted the following code is the sole intellectual property of
 * the author, all rights reserved.
 */
package rmi;

import java.sql.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import brickcitybank.message.*;

import database.CreateDB;
import database.DBConnection;
import database.User;

import brickcitybank.*;

/**
 * @author  Louis Duke
 */
public class BCBServer extends UnicastRemoteObject implements BCBRemoteServer {

	//Veriables
	private CreateDB myCreateDB;
	private DBConnection myConn;
	private User myUser;
	private BCBBusiness myBez;
	private String pass;
	
	/**
	 * Constructor for the server... mostly empty
	 */
	public BCBServer() throws RemoteException 
	{
		myUser = new User();
		myBez = new BCBBusiness(this);
	}
	
	/**
	 * Constructor for the server - takes password
	 */
	public BCBServer(String p) throws RemoteException 
	{
		pass = p;
		System.out.println("BCB SERVER CONSTR");
		myUser = new User();
		myBez = new BCBBusiness(this);
		
	}
	
	/**
	 * Sets the password for the MySQL database
	 * 
	 * @param p
	 */
	public void setPass(String p) throws RemoteException{
		pass = p;
	}
	

	/**
	 * This method creates and returns a Database Connection
	 * 
	 * @param	String pass			//password for the users root MySQL server
	 * 
	 * @return - DBConnection		//A Database Connection Object
	 */
	public DBConnection establishConn() throws RemoteException{
		//myConn = new DBConnection("", pass);
		System.out.println("PASSWORD IS: :::: " +pass);
		myConn = new DBConnection(pass);
		return myConn;
	}
	
	/**
	 * createDB uses an existing connection to create the Brick City Bank
	 * database structure
	 * 
	 * @param pass - user root password
	 */	
	public void createDB(String pass) throws RemoteException{
		myCreateDB = new CreateDB(pass);
	}
	
	/**
	 *  Primary method used to insert new records into the user table of
	 *  the database.
	 */
	public void insertRecord() throws RemoteException{
		//create a fake user
		myUser.insertUser(myConn, "Tom", "Smith", "tsmith", "it4567", "Kimball drive", "Rochester", "NY", "14623");
	}
	
	/**
	 *  Used to drop a record from the user table of the database.
	 */
	public void dropRecord() throws RemoteException{
		//call the method from User.java
		myUser.deleteUser(2, myConn);	
	}
	
	/**
	 *  Used to update an existing record in the user table
	 */
	public void updateRecord() throws RemoteException{
		
		//call the method from User.java, call him Smith
		myUser.updateUser(1,"UPDATE!", myConn);
	}
	
	/**
	 * Gets all users from the user database. 
	 */
	public ArrayList<String> getAllUsers()
	{
		ArrayList<String> retVal = new ArrayList<String>();
		retVal = myUser.getAllUsers(myConn);
		return retVal;
	}
	
	/**
	 * Sends messages to the business class
	 */
	public void sendJMSMessage(String msg)
	{
		myBez.sendJMSMessage(msg);
	}

	/** 
	 * Authentication means for ATMs; routed to Business
	 * 
	 * @param	dcn		Users Debit Card Number
	 * @param	pin		Users PIN
	 */
	public boolean authenATM(int dcn, int pin) throws RemoteException
	{
		boolean loginSuccess = myBez.authenticateATM(dcn, pin);	
		// int[] accounts = myBez.getAccounts();
		// int[] balances = myBez.getBalances();
		return loginSuccess;
	}

	/**
	 * Authentication means for the Web site; routed to Business
	 * 
	 * @param	uName	User Name
	 * @param	passW	Password for Users Account
	 */
	public int authenWeb(String uName, String passW) throws RemoteException
	{
		int userID = 0;
		userID = myBez.authenticateWeb(uName, passW);
		// int[] accounts = myBez.getAccounts();
		// int[] balances = myBez.getBalances();
		return userID;
	}
	
	public ArrayList<database.Account> getAccount(int id, String type)
	{
		return myBez.getAccount(id, type);
	}
	
	public MessageResponse bankAction(MessageOrder mo) throws RemoteException
	{
		return myBez.doAction(mo);
	}
	
}
