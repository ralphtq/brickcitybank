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
		myUser.updateUser(1,"Smith", myConn);
		
	}
	
	/**
	 * Gets all users from the user database. 
	 */
	public ResultSet getAllUsers(){
		ResultSet retVal = null;
		retVal=myUser.getAllUsers();
		return retVal;
	}
	public void createDB() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
