/**
 * BCBBusiness.java
 * 
 * @author Louis Duke
 */
package brickcitybank;

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
		try{
			myConn = myserv.establishConn();
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		
		//set up our tools
		DepositTool	myDeposit = new DepositTool(myConn,null);
		TransferTool myTrans = new TransferTool(myConn,myDeposit);
		WithdrawalTool myWithdraw = new WithdrawalTool(myConn,myTrans);
		myTool = myWithdraw;
		
		//create our JMSProducer object
		myProd = new JMSProducer();
		
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
	
}
