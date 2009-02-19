package rmi;

/*
 * Consumer.java
 *
 * Will be created by GUI/Client and will fetch messages from Queue, displaying them in the GUI for the user 
 */

/**
 *
 * @author kjb
 */

import javax.naming.*;
import javax.jms.*;

public class JMSConsumer 
{
  private final String QUEUE_NAME = "destinationQueue";
  private final String CONN_FACTORY = "connectionFactory";
  public String message = "";
 
 private Context jndiContext; // JNDI context for looking up names
 private ConnectionFactory cf;
 private Destination dest;
    /*
 	public static void main(String[] args)
 	{
 		JMSConsumer consume = new JMSConsumer("destinationQueue");
 		consume.getMessages();
 	}*/
 
    /** Creates a new instance of Consumer */
    public JMSConsumer(String destName) {
        // get a JNDI naming context
        try 
		{
            jndiContext = new InitialContext();
        }
        catch(NamingException ne){
            System.out.println("Unable to get a JNDI context for naming");
            System.exit(1);
        }
        
        // set up a ConnectionFactory and destination
        try 
		  {
           cf = (ConnectionFactory)jndiContext.lookup(CONN_FACTORY);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a ConnectionFactory. Msg: " + exc.getMessage());
            System.exit(1);
        }
        
        try
		  {
           dest = (Destination)jndiContext.lookup(destName);
        }
        catch(Exception exc) {
            System.out.println("Unable to get a Destination. Msg: " + exc.getMessage());
            System.exit(1);
        }
    }
    
    /** get messages from the queue */
    public void getMessages() 
	 {
    	System.out.println("get messages method");
         try 
			{
            // create the connection
            Connection conn = cf.createConnection();
        
            // create the session
            Session sess = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
            // create a producer
            MessageConsumer cons = sess.createConsumer(dest);
        
            // start receiving messages
            conn.start();
        
            // loop to create and send the messages
            while(message.equals("")) 
			{
                Message m = cons.receive(); // block until a message appears
                if(m != null) 
                {
                    if(m instanceof TextMessage)
                    {
                        TextMessage msg = (TextMessage)m;
                        System.out.println("Consumer received " + msg.getText());
                        message = msg.getText();
                        break;
                    }
                }
                else 
				{
                    break;
                }
            }
            
            //close everything down
            if(conn != null) 
				{
                conn.close();
            }
        }
        catch(JMSException je) 
		  {
            System.out.println("Unable to close the connection: " + je.getMessage());
            System.exit(1);
        }
	 }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}