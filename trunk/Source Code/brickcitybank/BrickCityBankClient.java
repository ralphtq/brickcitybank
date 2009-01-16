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
	
	//Variables
	private static boolean oOF = true;
	
	public static String getInput(){
		String inp = "No input";
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));
		
		try{
			inp = in.readLine();
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
		
		return inp;
	}
	
	/**
	 * Main function of the Client
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Variables we will need
		BCBServer myServ = null;
		String uRespon = "no input";
		
		//Counters for the operations
		int insertNum = 0;
		int updateNum = 0;
		int deleteNum = 0;
		
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
		
		uRespon = getInput();
		
		//create database
		try{
			myServ.createDB();
		}catch(Exception e){
			e.getMessage();
		}
		System.out.println("================Creating Database================");
		System.out.println("");
		
		//populate database
		
		System.out.println("======Populating Database with Sample users======");
		System.out.println("");
		
		//Enter Actual server usage
		System.out.println("Please select a function to perform");
		System.out.println("1) Insert a new record");
		System.out.println("2) Update an existing record");
		System.out.println("3) Drop an existing record");
		System.out.println("");
		
		//get user input
		in= getInput();
		
		if ( in.equalsIgnoreCase("No Input")){
			System.out.println("Invalid Input");
		}else if (in.equals("1")){
			//run insert command
			//show contents of db
		}else if (in.equals( "2" )){
			//run update command
			//show contents of DB
		}else if (in.equals( "3" )){
			//run drop command
			//show contents of DB
		}
	}

}
