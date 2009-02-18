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
import java.util.Scanner;

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
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your root MySQL password: ");
			String pass = scan.nextLine();
            //build our server object
			
			BCBServer myServ = new BCBServer(pass);
			
			//bind it to the RMI registry
            Naming.rebind("rmi://localhost:1099/BCBServer", myServ);
            System.out.println("BCBServer launched successfully.");
        }
        catch(Exception e){
        	//if the server fails to launch tell us why
            System.out.println("Server launch error: " + e.getMessage());
            System.out.println(e.getCause());
        }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		startServer();

	}

}
