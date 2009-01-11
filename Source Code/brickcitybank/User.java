package brickcitybank;

import java.io.*;
import java.sql.*;

public class User {
	
	//attributes
	//accessors
	//mutators
	
	public User()
	{
		System.out.println("user class");
		DBConnection conn = new DBConnection();
		Statement state = null;
		ResultSet rs = null;
		try
		{
			state = conn.getConn().createStatement();
			rs = state.executeQuery("select * from user");
		
		
		while(rs.next())
		{
			for(int i=1;i<9;i++)
			{
				System.out.print(rs.getString(i) + " ");
			}
			System.out.println();
		}
		rs.close();
		state.close();
		conn.closeConnection();
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
	}

}
