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
	public BCBServer() throws RemoteException {
		myUser = new User();
		myBez = new BCBBusiness(this);
	}
	
	/**
	 * Sets the password for the MySQL database
	 * 
	 * @param p
	 */
	public void setPass(String p){
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
	
	public void sendJMSMessage(String msg)
	{
		myBez.sendJMSMessage(msg);
	}

}
