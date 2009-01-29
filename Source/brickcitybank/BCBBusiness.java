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
 * @author louisduke
 */
public class BCBBusiness {

	//variables
	BCBServer myserv; //reference to the creating server
	ActionTool myTool; //Base tool to access the database
	
	
	
	/**
	 * Constructor - Mostly empty
	 * 
	 * @param	-	ms	-	server creating this BCBBusiness Object
	 */
	public BCBBusiness(BCBServer ms, DBConnection mc){
		myserv = ms;
		
		DepositTool	myDeposit = new DepositTool(mc,null);
		TransferTool myTrans = new TransferTool(mc,myDeposit);
		WithdrawalTool myWithdraw = new WithdrawalTool(mc,myTrans);
		myTool = myWithdraw;
		
		
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
		
		//instantiate our producer
		JMSProducer myProd = new JMSProducer();
		
		//set the connection information
		
		//set the message to send
		myProd.setMessage(mess);
		
		//send the message
		myProd.sendMessage();
		
		return retval;
	}
	
}
