package rmi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class RmiUtils {
	
	private BCBRemoteServer myServ;
	private String rmiUrl;

	public RmiUtils()
	{
		rmiUrl = "rmi://localhost:1099/BCBServer";
	}
	public RmiUtils(String url)
	{
		rmiUrl = url;
	}
	
	public String getRmiUrl() {
		return rmiUrl;
	}
	public void setRmiUrl(String rmiUrl) {
		this.rmiUrl = rmiUrl;
	}
	public BCBRemoteServer getMyServ() {
		return myServ;
	}

	public void setMyServ(BCBRemoteServer myServ) {
		this.myServ = myServ;
	}

	public void connectToRmi()
	{
		myServ = null;
				
		//try and look up the server.
		try{
			myServ = (BCBRemoteServer)Naming.lookup(rmiUrl);
		}catch( Exception e ){
			System.err.println( "Client Terminating - Server Lookup Failed: " +e.getMessage());
			System.exit(1);
		}
	}
}
