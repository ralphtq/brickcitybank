package brickcitybank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;

public class CreateDB {

	
	public CreateDB(){
	}
	
	
	public CreateDB(String pass)
	{
		System.out.println("==== Creating Brick City Bank Database ====");
//		DBConnection conn = new DBConnection("localhost","","root",pass);
		DBConnection conn = new DBConnection("mydb","admin");
		Statement state = null;
		ResultSet rs = null;
		try
		{
			state = conn.getConn().createStatement();
			StringBuilder contents = new StringBuilder();
			BufferedReader in = new BufferedReader(new FileReader("brickcitybank.sql"));
			try 
			{
				String line = null; //not declared within while loop
		        /*
		        * readLine is a bit quirky :
		        * it returns the content of a line MINUS the newline.
		        * it returns null only for the END of the stream.
		        * it returns an empty String if two newlines appear in a row.
		        */
				System.out.println("Reading .sql file...");
		        while (( line = in.readLine()) != null)
		        {
		          contents.append(line);
		          contents.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		        in.close();
		      }
		    
		      System.out.println("Formatting .sql contents...");
		    String script = contents.toString();
		    String[] commands = script.split(";");
			System.out.println("Executing MySQL statements...");
		    for(int i = 0; i<commands.length-1; i++)
		    {
		    	//System.out.println(commands[i]);
		    	String temp = commands[i] + ";";
		    	state.execute(temp);
		    }
		    System.out.println("Testing database with query...");
			rs = state.executeQuery("select * from user;");

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
			System.out.println("Done!");
			System.out.println("This is where we need to start RMI connection, and then provide a UI");
		}
		catch(SQLException e) {
			System.err.println("SQL Error(s) as follows:");
			while (e != null) {
				System.err.println("SQL Return Code: " + e.getSQLState());
				System.err.println("  Error Message: " + e.getMessage());
				System.err.println(" Vendor Message: " + e.getErrorCode());
				e = e.getNextException();
			}		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
