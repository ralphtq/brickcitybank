package brickcitybank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

public class CreateDB {

	public CreateDB()
	{
		System.out.println("Create DB Class===================");
		DBConnection conn = new DBConnection();
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
		        while (( line = in.readLine()) != null)
		        {
		          contents.append(line);
		          contents.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		        in.close();
		      }

		    String script = contents.toString();
		    String[] commands = script.split(";");
		    
		    for(int i = 0; i<commands.length; i++)
		    {
		    	String temp = commands[i] + ";";
		    	state.execute(temp);
		    }
		    
		    System.out.println(commands[0]);
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
		}
		catch(Exception e)
		{
			System.out.println(""+e.getMessage());
		}
	}
}
