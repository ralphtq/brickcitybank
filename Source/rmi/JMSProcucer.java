package rmi;

import javax.naming.*;
import javax.jms.*;

public class JMSProcucer {
	
	private String queueName;
	private String topicName;
	private String connFactoryName;
	private Context jndiContext;
	private ConnectionFactory connFact;
	private Destination messageDest;
	private String message;
	private String destName;
	
	//---------- Constructors ----------
	public JMSProcucer()
	{
		
	}
	/*public JMSProcucer(String destName)
	{
		
	}*/
	
	//---------- mutatiors ----------
	public void setDestName(String msgDest)
	{
		destName = msgDest;
	}
	public void setQueueName(String qName)
	{
		queueName = qName;
	}
	public void setTopicName(String tName)
	{
		topicName = tName;
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
	public String getQueueName()
	{
		return queueName;
	}
	public String getTopicName()
	{
		return topicName;
	}
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
	public void createConnections()
	{
		try
		{
			jndiContext = new InitialContext();
		}
		catch(NamingException ne)
		{
			System.out.println("Unble to get conext for jndi");
			System.err.println(ne.getMessage());
		}
		
		try
		{
			connFact = (ConnectionFactory)jndiContext.lookup(this.connFactoryName);
		}
		catch(Exception e)
		{
			System.out.println("Error looking up connection factory");
			System.err.println(e.getMessage());
		}
		
		try
		{
			messageDest =(Destination) jndiContext.lookup(this.destName);
		}
		catch(Exception e)
		{
			System.out.println("Error finding message dest");
			System.err.println(e.getMessage());
		}
	}
	
	public void sendMessage()
	{
		Connection conn = null;
		Session msgSession = null;
		MessageProducer producer = null;
		TextMessage txtMsg = null;
		
		
		
		try
		{
			conn = connFact.createConnection();
			msgSession = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			producer = msgSession.createProducer(this.messageDest);
			txtMsg = msgSession.createTextMessage();
			
			//send message
			System.out.println("Sending Message: " + this.getMessage());
			txtMsg.setText(this.getMessage());
			producer.send(txtMsg);
			
			//send empty to indicate no more messages
			producer.send(msgSession.createMessage());
			
			//close connection
			if(conn != null)
			{
				conn.close();
			}
		}
		catch(JMSException jmse)
		{
			System.out.println("Error sending messages");
			System.err.println(jmse.getMessage());
		}
	}
}//end JMS PRODUCER
