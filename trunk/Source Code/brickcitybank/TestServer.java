package brickcitybank;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		DBConnection conn = new DBConnection();
		
		//User st = new User();
		CreateDB create = new CreateDB();
		
		User usr = new User();
		usr.deleteUser(1);
		usr.insertUser();

	}

}
