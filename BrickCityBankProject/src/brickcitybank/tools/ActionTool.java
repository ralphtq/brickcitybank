/**
 * 
 */
package brickcitybank.tools;

import brickcitybank.message.MessageOrder;
import brickcitybank.message.MessageResponse;
import database.DBConnection;

/**
 * @author Caroline
 * 
 *Abstract Class used to implement the Chain-of-responsibility pattern
 *to perform the correct action while receiving a Message
 */
public abstract class ActionTool {
	//Attributes
	//tool to connect to the database
	private DBConnection connector;
	//the actionTool successor
	private ActionTool succ;
	
	public static final int DEPOSIT = 0;
	public static final int WITHDRAW = 1;
	public static final int HISTORY = -1;
	
	
	
	/**
	 * Constructor
	 * @param connector
	 * @param succ
	 */
	public ActionTool(DBConnection connector, ActionTool succ) {
		super();
		this.connector = connector;
		this.succ = succ;
	}

	/**
	 * 
	 * @param m
	 * @return
	 */
	public MessageResponse manageAction(MessageOrder m)
	{
		//System.out.println("ActionTools - manageAction");
		 if(existAction(m))
		 {
			 return executeAction( m);
		 }
		 else
		 {
			 if (succ != null)
			 {
				 return succ.manageAction(m);
			 }
			 else 
			 {
				 //to check return error?but will never being accessed
				 return new MessageResponse();
			 }
		 }
		
	}
	
	/**
	 * 
	 * @param m
	 * @return
	 */
	public abstract boolean existAction(MessageOrder m);
	
	/**
	 * 
	 * @param m
	 * @return
	 */
	public abstract MessageResponse executeAction(MessageOrder m);
	
	

	public DBConnection getConnector() {
		return connector;
	}

	public void setConnector(DBConnection connector) {
		this.connector = connector;
	}

	public ActionTool getSucc() {
		return succ;
	}

	public void setSucc(ActionTool succ) {
		this.succ = succ;
	}
	
	

}
