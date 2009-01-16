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
import java.io.*;

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
		BCBServer myServ = null;
		String uRespon = "no input";
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
		String inp = "No Input";
				
		//try and look up the server.
		try{
			myServ = (BCBServer)Naming.lookup( "rmi://localhost/BCBServer");
		}catch( Exception e ){
			System.err.println( "Client Terminating - Server Lookup Failed" );
			System.exit(1);
		}
		
		//prompt the user for the root password
		System.out.println("");
		System.out.println("Welcome to the Brick City Bank Client");
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
			e.getMessage();
		}
		System.out.println("================Creating Database================");
		System.out.println("");

		try{
		//Enter Actual server usage
		myServ.establishConn(uRespon);
		System.out.println("Welcome to the Brick City Bank Demo");
		System.out.println("Inserting new Users....");
		myServ.insertRecord();
		System.out.println("");
		System.out.println("Updating Records...");
		myServ.updateRecord();
		System.out.println("Dropping Record...");
		myServ.dropRecord();
		}catch(Exception e){
			
		}
		
		
		

	}

}
