package rmi;

import javax.naming.*;
import javax.jms.*;

public class JMSProducer {
	
	//attributes
	private String connFactoryName; //name of connection factory
	private Context jndiContext;
	private ConnectionFactory connFact;
	private Destination messageDest;
	private String message; //message sent to consumer
	private String destName; //name of the destination
	
	//---------- Constructors ----------
	public JMSProducer()
	{
		this.connFactoryName = "connectionFactory";
		this.destName = "destinationQueue";
	}
	//---------- mutatiors ----------
	public void setDestName(String msgDest)
	{
		destName = msgDest;
	}
	public void setConnFactoryName(String cfName)
	{
		connFactoryName = cfName;
	}
	public void setJndiContext(Context context)
	{
		jndiContext = context;
	}
	public void setConnFact(ConnectionFactory cf)
	{
		connFact = cf;
	}
	public void setMessageDest(Destination dest)
	{
		messageDest = dest;
	}
	public void setMessage(String mesg)
	{
		message = mesg;
	}
	//---------- Accessors ----------
	public String getConnFactoryName()
	{
		return connFactoryName;
	}
	public Context getJndiContext()
	{
		return jndiContext;
	}
	public ConnectionFactory getConnFact()
	{
		return connFact;
	}
	public String getMessage()
	{
		return message;
	}
	//---------- METHODS -----------
	
	/**
	 * Creates a connection to JNDI
	 * 
	 */
	public void createConnections()
	{
		try
		{
			System.out.println("Attempting to create jndiContext obj");
			jndiContext = new InitialContext();
		}
		catch(NamingException ne)
		{
			System.out.println("Unble to get conext for jndi");
			System.err.println(ne.getMessage());
		}
		System.out.println("jndi connection created");
		System.out.println("Creating connection factory");
		try
		{
			connFact = (ConnectionFactory)jndiContext.lookup(this.connFactoryName);
		}
		catch(Exception e)
		{
			System.out.println("Error looking up connection factory");
			System.err.println(e.getMessage());
		}
		System.out.println("Conn Factory created");
		System.out.println("Attempting to create dest");
		
		try
		{
			messageDest =(Destination) jndiContext.lookup(this.destName);
		}
		catch(Exception e)
		{
			System.out.println("Error finding message dest");
			System.err.println(e.getMessage());
		}
		System.out.println("created dest");
	}
	//-------------------- SEND MESSAGE ------------------------
	/**
	 * Sends message to consumer
	 */
	public boolean sendMessage(String msg)
	{
		//set the message
		this.setMessage(msg);
		//if messages sent correctly will be true
		boolean success = false;
		
		//set some vars
		Connection conn = null;
		Session msgSession = null;
		MessageProducer producer = null;
		TextMessage txtMsg = null;
		
		
		
		try
		{
			System.out.println("Create connection");
			conn = connFact.createConnection();
			System.out.println("create session");
			msgSession = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			System.out.println("create producer");
			producer = msgSession.createProducer(this.messageDest);
			System.out.println("create message");
			txtMsg = msgSession.createTextMessage();
			
			//send message
			System.out.println("Sending Message: " + this.getMessage());
			txtMsg.setText(this.getMessage());
			producer.send(txtMsg);
			System.out.println("Message has been sent");
			//send empty to indicate no more messages
			producer.send(msgSession.createMessage());
			
			//close connection
			if(conn != null)
			{
				conn.close();
			}
			//set to true for successful message sent
			success = true;
		}
		catch(JMSException jmse)
		{
			System.out.println("Error sending messages");
			System.err.println(jmse.getMessage());
		}
		
		return success;
	}
}//end JMS PRODUCER