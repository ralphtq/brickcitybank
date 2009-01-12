package brickcitybank;

import java.io.*;
import java.sql.*;

public class User {
	
	/*
		+-----------+-------------+------+-----+---------+----------------+
		| Field     | Type        | Null | Key | Default | Extra          |
		+-----------+-------------+------+-----+---------+----------------+
		| idUser    | int(11)     | NO   | PRI | NULL    | auto_increment |
		| LastName  | varchar(45) | NO   |     | NULL    |                |
		| FirstName | varchar(45) | NO   |     | NULL    |                |
		| Pin       | varchar(45) | YES  |     | NULL    |                |
		| Password  | varchar(45) | NO   |     | NULL    |                |
		| Street    | varchar(40) | YES  |     | NULL    |                |
		| City      | varchar(45) | YES  |     | NULL    |                |
		| State     | varchar(45) | YES  |     | NULL    |                |
		| Zipcode   | varchar(10) | YES  |     | NULL    |                |
		+-----------+-------------+------+-----+---------+----------------+
	 */
	//attributes
	private int idUser;
	private String lastName;
	private String firstName;
	private String password;
	private String username;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	//accessors
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
	//mutators
	
	
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
}
