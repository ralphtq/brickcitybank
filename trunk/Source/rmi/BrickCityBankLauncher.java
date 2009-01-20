/**
 * This class merely creates our BCBServer and registers it with the RMI registry.
 * 
 * @author Louis Duke
 *
 * Unless otherwise noted the following code is the sole intellectual property of
 * the author, all rights reserved.
 */
package rmi;

import java.rmi.*;

/**
 * @author Louis Duke
 *
 */
public class BrickCityBankLauncher {

	public BrickCityBankLauncher(){
		startServer();
	}
	
	
	public static void startServer(){
		try {
            //build our server object
			BCBServer myServ = new BCBServer();
			
			//bind it to the RMI registry
            Naming.rebind("rmi://localhost:1099/BCBServer", myServ);
            System.out.println("BCBServer launched successfully.");
        }
        catch(Exception e){
        	//if the server fails to launch tell us why
            System.out.println("Server launch error: " + e.getMessage());
        }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startServer();

	}

}
