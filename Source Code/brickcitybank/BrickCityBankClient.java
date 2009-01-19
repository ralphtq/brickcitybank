/**
 * BrickCityBankClient.java
 * 
 * @author Louis Duke
 *
 * Unless otherwise noted the following code is the sole intellectual property of
 * the author, all rights reserved.
 */
package brickcitybank;

import java.rmi.*;
import java.sql.ResultSet;
import java.io.*;
import java.util.*;

/**
 * @author Louis Duke
 */
public class BrickCityBankClient {
		
	/**
	 * Main function of the Client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Variables we will need
		BCBRemoteServer myServ = null;
		String uRespon = "no input";
		BufferedReader in = new BufferedReader(new InputStreamReader( System.in ));
				
		//try and look up the server.
		try{
			myServ = (BCBRemoteServer)Naming.lookup("rmi://localhost:1099/BCBServer");
		}catch( Exception e ){
			System.err.println( "Client Terminating - Server Lookup Failed: " +e.getMessage());
			System.exit(1);
		}
		
		//prompt the user for the root password
		System.out.println("");
		System.out.println("Welcome to the Brick City Bank Client\n");
		System.out.println("Please input your MySQL root password:");
		System.out.println("");
		
		//get user input
		try{
			uRespon = in.readLine();
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
		
		//create database
		try{
			myServ.createDB(uRespon);
		}catch(Exception e){
			System.out.println("FAIL");
			e.getMessage();
		}
		System.out.println("================Creating Database================");
		System.out.println("");

		try{
		//Enter Actual server usage
		myServ.establishConn(uRespon);
		System.out.println("Welcome to the Brick City Bank Demo\n");
		System.out.println("Current State of the user table:");
		ArrayList<String> al1 = new ArrayList<String>();
		
		al1 = myServ.getAllUsers();
		
		if(al1.get(0) == null)
		{
			System.out.println("Your MySQL password is incorrect. Please try again.\nExiting...");
			System.exit(0);
		}
		
		for(int i = 0; i < al1.size(); i++)
		{
			System.out.print(al1.get(i));
		}
		System.out.println();
		
		// Insert
		myServ.establishConn(uRespon);
		System.out.println("Inserting \"Tom Smith\" with ID=5...");
		myServ.insertRecord();
		myServ.establishConn(uRespon);
		al1 = myServ.getAllUsers();
		for(int i = 0; i < al1.size(); i++)
		{
			System.out.print(al1.get(i));
		}
		System.out.println("Done inserting!\n\n");
		
		// Update
		myServ.establishConn(uRespon);
		System.out.println("Updating first name of user with UID=1...");
		myServ.updateRecord();
		myServ.establishConn(uRespon);
		al1 = myServ.getAllUsers();
		for(int i = 0; i < al1.size(); i++)
		{
			System.out.print(al1.get(i));
		}
		System.out.println("Done updating!\n\n");

		
		// Delete
		myServ.establishConn(uRespon);
		System.out.println("Deleting user with UID=2...");
		myServ.dropRecord();
		myServ.establishConn(uRespon);
		al1 = myServ.getAllUsers();
		for(int i = 0; i < al1.size(); i++)
		{
			System.out.print(al1.get(i));
		}
		System.out.println("Done deleting!\n\nThis concludes Deliverable 2 =)\nPress the Enter key to exit...");
		
		in.readLine();
		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		
		

	}

		
			
			
			public void stupidMethod(){
			System.out.println("oui this worked"); }

}
