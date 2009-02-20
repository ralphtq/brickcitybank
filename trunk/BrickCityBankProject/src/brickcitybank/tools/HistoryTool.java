package brickcitybank.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import brickcitybank.message.MessageHistory;
import brickcitybank.message.MessageOrder;
import brickcitybank.message.MessageResponse;

public class HistoryTool extends ActionTool
{

	public HistoryTool(DBConnection connector, ActionTool succ) {
		super(connector, succ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MessageResponse executeAction(MessageOrder m) 
	{
		int account = m.getIdAcount();
		
		Statement state = null;
		ResultSet rs = null;
		try
		{
			state = getConnector().getConn().createStatement();
			
			rs = state.executeQuery("select * from transaction where account1=' " +account + " ' or account2 = ' " + account + " ' ");
			String resp = "";
			System.out.println("select * from transaction where account1=' " +account + " ' or account2 = ' " + account + " ' ");
			
			resp += "<table class=\"fancytable2\">";
			resp += "<tr><th>Type</th><th>Account1</th><th>Account2</th><th>Date</th><th>Time</th>"
						+"<th>OldBalance1</th><th>NewBalance1</th><th>OldBalance2</th><th>NewBalance2</th></tr>";
			
			while(rs.next())
			{
				resp += "<tr>";
				for(int i = 2; i <= 10; i++)
				{
					try{
						resp += "<td>" +rs.getString(i) +"</td>";
					}
					catch(Exception e){
						resp += "<td></td>";
					}
				}
				resp += "</tr>";
			}
			resp += "</table>";
			
			return new MessageResponse(resp);
		}
		catch(SQLException sql)
		{
			System.out.println("SQL Exception in HistoryTool");
		}
		
		return new MessageResponse("<p>Something went wrong!</p>");
	}

	@Override
	public boolean existAction(MessageOrder m) {
		// TODO Auto-generated method stub
		return (m instanceof MessageHistory);
	}
	
}