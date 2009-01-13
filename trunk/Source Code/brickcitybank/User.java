package brickcitybank;

import java.io.*;
import java.sql.*;

public class User {
	
	//==================== attributes ====================
	private int idUser;
	private String lastName;
	private String firstName;
	private String password;
	private String username;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	//==================== Constructors ====================
	/*public User()
	{
		
	}*/
	//===================== Accessors =====================
	public int getIdUser()
	{
		return idUser;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getPassword()
	{
		return password;
	}
	public String getUsername()
	{
		return username;
	}
	public String getStreet()
	{
		return street;
	}
	public String getCity()
	{
		return city;
	}
	public String getState()
	{
		return state;
	}
	public String getZipCode()
	{
		return zipCode;
	}
	
	//==================== mutators ====================
	public void setIdUser(int userID)
	{
		idUser = userID;
	}
	public void setLastName(String last)
	{
		lastName = last;
	}
	public void setFirstName(String first)
	{
		firstName = first;
	}
	public void setPassword(String pass)
	{
		password = pass;
	}
	public void setUsername(String user)
	{
		username = user;
	}
	public void setStreet(String _street)
	{
		street = _street;
	}
	public void setCity(String _city)
	{
		city = _city;
	}
	public void setState(String _state)
	{
		state = _state;
	}
	public void setZipCode(String zip)
	{
		zipCode = zip;
	}
	//sample query 
	public ResultSet getAllUsers()
	{
		System.out.println("user class");
		DBConnection conn = new DBConnection();
		Statement state = null;
		ResultSet rs = null;
		try
		{
			state = conn.getConn().createStatement();
			rs = state.executeQuery("select * from user");
		
			rs.close();
			state.close();
			conn.closeConnection();
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			System.err.println(" Vendor Message: " + e.getErrorCode());
		}
		
		return rs;
	}
	//sample insert
	//sample update
}
