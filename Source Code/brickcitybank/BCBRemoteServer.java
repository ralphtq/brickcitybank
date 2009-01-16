package brickcitybank;

import java.rmi.*;

public interface BCBRemoteServer extends Remote {

	public void establishConn(String pass ) throws RemoteException;
	
	public void createDB() throws RemoteException;
	
	public void insertRecord(int i) throws RemoteException;
	
	public void dropRecord(int i) throws RemoteException;
	
	public void updateRecord(int i) throws RemoteException;
	
	
}
