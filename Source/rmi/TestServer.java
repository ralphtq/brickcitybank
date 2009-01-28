package rmi;


public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		//DBConnection conn = new DBConnection();
		
		//User st = new User();
		//CreateDB create = new CreateDB("admin");
		System.out.println("Testing JMS Stuff");
		//User usr = new User();
		
		rmi.JMSProducer producer = new rmi.JMSProducer();
		producer.setMessage("Test message w00t!");
		//producer.setQueueName("destinationQueue");
		producer.setConnFactoryName("connectionFactory");
		producer.setDestName("destinationQueue");
		producer.createConnections();
		
		//brickcitybank.GUI gui = new brickcitybank.GUI();
		//usr.deleteUser(1, conn);
		//usr.insertUser();

	}

}
