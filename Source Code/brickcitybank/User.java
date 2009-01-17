package brickcitybank;

import java.io.*;
import java.sql.*;
import java.util.*;

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
	public ArrayList<String> getAllUsers(DBConnection conn)
	{
		System.out.println("user class");
		//conn = new DBConnection();
		Statement state = null;
		ResultSet rs = null;
		ResultSet rs2;
		ArrayList<String> al = new ArrayList<String>();
		try
		{
			state = conn.getConn().createStatement();
			rs = state.executeQuery("select * from user");
			while(rs.next())
			{
				String res = "";
				for(int i=1;i<9;i++)
				{
					res += rs.getString(i)+"\t";
				}
				al.add(res);
				al.add("\n");
			}
			state.close();
			conn.closeConnection();
		}
		catch(SQLException e)
		{
			
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			System.err.println(" Vendor Message: " + e.getErrorCode());
		}
		return al;
		/*while(Resultset.next()){}*/
	}
	//update
	public void updateUser(int userId,String firstName, DBConnection conn)
	{
		Statement state = null;
		ResultSet rs = null;
		
		try
		{
			state = conn.getConn().createStatement();
			state.execute("update user set firstname='"+firstName + "' where iduser = " + userId);
			
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			System.err.println(" Vendor Message: " + e.getErrorCode());
		}
	}
	//drop
	public void deleteUser(int userID, DBConnection conn)
	{
		Statement state = null;
		ResultSet rs = null;
		
		try
		{
			state = conn.getConn().createStatement();
			state.execute("delete from user_accounts where user_id = " + userID);
			state.execute("delete from user where iduser = " + userID);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			System.err.println(" Vendor Message: " + e.getErrorCode());
		}
	}
	//sample insert
	public void insertUser(DBConnection conn, String first, String last,String user,String usrPassword,String street,String city,String mystate,String zip )
	{
		Statement state1 = null;
		ResultSet rs = null;
		 
		try
		{
			state1 = conn.getConn().createStatement();
			state1.execute("insert into user(firstname,lastname,username,password,street,city,state,zipcode) values ('" + first+"','" + last + "','" + user + "','" + usrPassword + "','" + street + "','" + city + "','" + mystate + "','" + zip + "')");
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			System.err.println("  Error Message: " + e.getMessage());
			System.err.println(" Vendor Message: " + e.getErrorCode());
		}
	}
}
