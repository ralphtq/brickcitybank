package rmi;

import java.rmi.*;
import java.sql.*;
import java.util.*;

public interface BCBRemoteServer extends Remote {

	public void establishConn(String pass ) throws RemoteException;
	
	public void createDB(String s) throws RemoteException;
	
	public void insertRecord() throws RemoteException;
	
	public void dropRecord() throws RemoteException;
	
	public void updateRecord() throws RemoteException;
	
	public ArrayList<String> getAllUsers() throws RemoteException;
	
	
}
