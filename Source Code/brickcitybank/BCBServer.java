/**
 * 
 * 
 * @author Louis Duke
 *
 * Unless otherwise noted the following code is the sole intellectual property of
 * the author, all rights reserved.
 */
package brickcitybank;

import java.sql.*;
import java.rmi.*;
import java.rmi.server.*;

/**
 * @author Louis Duke
 *
 */
public class BCBServer extends UnicastRemoteObject implements BCBRemoteServer {

	//Veriables
	CreateDB myCreateDB;
	DBConnection myConn;
	User myUser;
	
	/**
	 * Constructor for the server... mostly empty
	 */
	public BCBServer() throws RemoteException {
	}

	/**
	 * This method creates and returns a Database Connection
	 * 
	 * @param	String pass			//password for the users root MySQL server
	 * 
	 * @return - DBConnection		//A Database Connection Object
	 */
	public void establishConn(String pass ) throws RemoteException{
		myConn = new DBConnection( "", pass);
	}
	
	/**
	 * createDB uses an existing connection to create the Brick City Bank
	 * database structure
	 * 
	 * @args pass - user root password
	 */	
	public void createDB(String pass) throws RemoteException{
		myCreateDB = new CreateDB( pass );
	}
	
	/**
	 *  Primary method used to insert new records into the user table of
	 *  the database.
	 */
	public void insertRecord() throws RemoteException{
		
	}
	
	/**
	 *  Used to drop a record from the user table of the database.
	 */
	public void dropRecord() throws RemoteException{
		
	}
	
	/**
	 *  Used to update an existing record in the user table
	 */
	public void updateRecord() throws RemoteException{
		
	}
	
	/**
	 * Gets all users from the user database. 
	 */
	public ResultSet getAllUsers(){
		ResultSet retVal = null;
		
		
		return retVal;
	}
}
