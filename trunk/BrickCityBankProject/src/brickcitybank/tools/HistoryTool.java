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
			
			resp += "<table class=\"fancytable\">";
			resp += "<tr><th>ID</th><th>Type</th><th>Account1</th><th>Account2</th><th>Date</th><th>Time</th>"
						+"<th>OldBalance1</th><th>NewBalance1</th><th>OldBalance2</th><th>NewBalance2</th></tr>";
			
			while(rs.next())
			{
				resp += "<tr>";
				for(int i = 0; i < 9; i++)
				{
					resp += "<td>" +rs.getString(i) +"</td>";
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
		
		return null;
	}

	@Override
	public boolean existAction(MessageOrder m) {
		// TODO Auto-generated method stub
		return (m instanceof MessageHistory);
	}
	
}