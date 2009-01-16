package brickcitybank;

import java.rmi.*;
import java.sql.*;

public interface BCBRemoteServer extends Remote {

	public void establishConn(String pass ) throws RemoteException;
	
	public void createDB(String s) throws RemoteException;
	
	public void insertRecord() throws RemoteException;
	
	public void dropRecord() throws RemoteException;
	
	public void updateRecord() throws RemoteException;
	
	public ResultSet getAllUsers() throws RemoteException;
	
	
}
