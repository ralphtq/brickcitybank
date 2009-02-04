package rmi;

import java.rmi.*;
import java.sql.*;
import java.util.*;

import database.*;

public interface BCBRemoteServer extends Remote {

	public void setPass(String p);
	
	public DBConnection establishConn() throws RemoteException;
	
	public void createDB(String s) throws RemoteException;
	
	public void insertRecord() throws RemoteException;
	
	public void dropRecord() throws RemoteException;
	
	public void updateRecord() throws RemoteException;
	
	public ArrayList<String> getAllUsers() throws RemoteException;
	
	public void sendJMSMessage(String message) throws RemoteException;
	
	
}
