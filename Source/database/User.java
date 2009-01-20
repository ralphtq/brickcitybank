package database;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * @author  Louis Duke
 */
public class User {
	
	//==================== attributes ====================
	/**
	 * @uml.property  name="idUser"
	 */
	private int idUser;
	/**
	 * @uml.property  name="lastName"
	 */
	private String lastName;
	/**
	 * @uml.property  name="firstName"
	 */
	private String firstName;
	/**
	 * @uml.property  name="password"
	 */
	private String password;
	/**
	 * @uml.property  name="username"
	 */
	private String username;
	/**
	 * @uml.property  name="street"
	 */
	private String street;
	/**
	 * @uml.property  name="city"
	 */
	private String city;
	/**
	 * @uml.property  name="state"
	 */
	private String state;
	/**
	 * @uml.property  name="zipCode"
	 */
	private String zipCode;
	//==================== Constructors ====================
	/*public User()
	{
		
	}*/
	//===================== Accessors =====================
	/**
	 * @return
	 * @uml.property  name="idUser"
	 */
	public int getIdUser()
	{
		return idUser;
	}
	/**
	 * @return
	 * @uml.property  name="lastName"
	 */
	public String getLastName()
	{
		return lastName;
	}
	/**
	 * @return
	 * @uml.property  name="firstName"
	 */
	public String getFirstName()
	{
		return firstName;
	}
	/**
	 * @return
	 * @uml.property  name="password"
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @return
	 * @uml.property  name="username"
	 */
	public String getUsername()
	{
		return username;
	}
	/**
	 * @return
	 * @uml.property  name="street"
	 */
	public String getStreet()
	{
		return street;
	}
	/**
	 * @return
	 * @uml.property  name="city"
	 */
	public String getCity()
	{
		return city;
	}
	/**
	 * @return
	 * @uml.property  name="state"
	 */
	public String getState()
	{
		return state;
	}
	/**
	 * @return
	 * @uml.property  name="zipCode"
	 */
	public String getZipCode()
	{
		return zipCode;
	}
	
	//==================== mutators ====================
	/**
	 * @param userID
	 * @uml.property  name="idUser"
	 */
	public void setIdUser(int userID)
	{
		idUser = userID;
	}
	/**
	 * @param last
	 * @uml.property  name="lastName"
	 */
	public void setLastName(String last)
	{
		lastName = last;
	}
	/**
	 * @param first
	 * @uml.property  name="firstName"
	 */
	public void setFirstName(String first)
	{
		firstName = first;
	}
	/**
	 * @param pass
	 * @uml.property  name="password"
	 */
	public void setPassword(String pass)
	{
		password = pass;
	}
	/**
	 * @param user
	 * @uml.property  name="username"
	 */
	public void setUsername(String user)
	{
		username = user;
	}
	/**
	 * @param _street
	 * @uml.property  name="street"
	 */
	public void setStreet(String _street)
	{
		street = _street;
	}
	/**
	 * @param _city
	 * @uml.property  name="city"
	 */
	public void setCity(String _city)
	{
		city = _city;
	}
	/**
	 * @param _state
	 * @uml.property  name="state"
	 */
	public void setState(String _state)
	{
		state = _state;
	}
	/**
	 * @param zip
	 * @uml.property  name="zipCode"
	 */
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
