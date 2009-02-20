package servlets;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.jasper.tagplugins.jstl.core.Out;

//import rmi.*;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public ArrayList<database.Account> loan = new ArrayList<database.Account>();
    public ArrayList<database.Account> checking = new ArrayList<database.Account>();
    public ArrayList<database.Account> savings = new ArrayList<database.Account>();
    boolean loggedOut = true;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = 0;
		HttpSession session = request.getSession();
		
		//System.out.print("a userid?"+(session.getAttribute("userID")) == null);
		PrintWriter out = response.getWriter();

		RmiUtils rmi = new RmiUtils();
		rmi.connectToRmi();
		rmi.BCBRemoteServer serv = rmi.getMyServ();
		
		String username = "";
        String password = "";
		//echo the page
        
        String header ="<html><link rel=\"STYLESHEET\" type=\"text/css\" href=\"style.css\">"+
        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
        "<title>Brick City Bank</title>"+
        "</head><body><div id=\"header\">"+
        "<a id=\"ban-title\" href=\"welcome.jsp\">Brick City Bank</a>"+
        "</div>";
        out.write(header);
        
       
       
        
        
        
		
        //String jmsMessage = serv.getJMSMessage();
        //System.out.println("jms message from server: " + jmsMessage);
		
        try
		{
			//get username
			username = request.getParameter("userName");
		}
		catch (Exception e) {}
		
		try
		{
			//get password
			password = request.getParameter("password");
		}
		catch(Exception e) {}
		
		
		// Current login info.
		try
		{
			// Try to login.
			if(username != null && password != null)
			{
				userID = serv.authenWeb(username,password);
			}
			// Already logged in.
			else
			{
				System.out.println("Already logged in!");
				userID = Integer.parseInt(session.getAttribute("userID").toString());
			}
		}
		catch(NullPointerException npe)
		{
			loggedOut = true;
		}
		
		
		
		try
		{
			System.out.println("Logout: " +request.getParameter("logout"));
			if(request.getParameter("logout").equals("logout"))
			{
				//redirect user to login page
				
				//print menu
				 String menu ="";
			        
			        
			        menu += "<div id=\"menu\"><div class='element_menu'><h3>Log part</h3>";
		        String user ="";
	        	menu += "<form action=\"http://localhost:8181/BrickCityBankProject/Login\" method=\"post\">";
	        	menu += "	Username:<br/> <input type=\"text\" name=\"userName\"/><br/>";
	        	menu += "	Password:<br/> <input type=\"password\" name=\"password\"/><br /><br />";
	        	menu += "<input type=\"submit\" value=\"Login\"/></form>";
	        	menu +=" </div></div>";
			    out.write(menu);

				out.write("<div id=\"content\"><h1>Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
				out.write("You have been logged out.");

				//kill the session
				session.invalidate();
				session = null;
				userID =0;
				out.write("</div></body></html>");
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			loggedOut = true;
		}
		
		//System.out.println("Here  print first bool: "+ !request.getParameter("logout").equals("logout") +" second bool "+ !loggedOut);
		try
		{
			if(userID <= 0)
			{
				
				//print menu
				
				 //echo the menu
		        String menu ="";
		        
		        
		        menu += "<div id=\"menu\"><div class='element_menu'><h3>Log part</h3>";
		        String user ="";
		        if((session.getAttribute("userID")) == null){
		        	menu += "<form action=\"http://localhost:8181/BrickCityBankProject/Login\" method=\"post\">";
		        	menu += "	Username:<br/> <input type=\"text\" name=\"userName\"/><br/>";
		        	menu += "	Password:<br/> <input type=\"password\" name=\"password\"/><br /><br />";
		        	menu += "<input type=\"submit\" value=\"Login\"/></form>";
		        }else{
		        	user =(String)session.getAttribute("username").toString();
		        	menu +=("You are logged in"+ user);
		        	// Log out
		        	menu +=("<center><br><form action=\"Login\" method=\"POST\">");
		        	menu +=("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
		        	menu +=("<input type=\"submit\" value=\"Logout\"></center></form>");
		        }

		        menu +=" </div></div>";
		        out.write(menu);
				//redirect user to login page
				out.write("<div id=\"content\">");
				out.write("<h1>Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
				out.write("Login failed.");
				session.invalidate();
				session = null;
				out.write(" <a href=\"C:/login.html\">Click here</a> to go back and try again.");
				out.write("</div></body></html>");
			}
			else
			{
				//connected and registered into the bank database
				session.setAttribute("userID", userID);
				session.setAttribute("username", username);
				
				
				System.out.print("userID >=0");
				//print menu
				 //echo the menu
		        String menu ="";
		        
		        
		        menu += "<div id=\"menu\"><div class='element_menu'><h3>Log part</h3>";
		        String user ="";
		        
		        	menu +=("You are logged in"+ user);
		        	// Log out
		        	menu +=("<center><br><form action=\"Login\" method=\"POST\">");
		        	menu +=("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
		        	menu +=("<input type=\"submit\" value=\"Logout\"></center></form>");
		        

		        menu +=" </div></div>";
		        out.write(menu);
				
				
				//redirect people to account listing 
				out.write("<div id=\"content\">");
				out.write("<h1>Welcome to Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
				loan = serv.getAccount(userID, "Loan");
				checking = serv.getAccount(userID, "Checking");
				savings = serv.getAccount(userID, "Saving");
				
				out.write("<br>User ID: "+ userID +"<br>");
				
				if(checking.size() > 0)
				{
					out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
					out.write("<CAPTION>Checking Accounts:</CAPTION>");
					out.write("<TR> <TH>Account#</TH> <TH>Balance</TH> </TR>"); 
					for(int i = 0; i<checking.size(); i++)
					{
						out.write("<tr> " +"<td>" +checking.get(i).getIdAccount() +"</td>" +"<td>$" +checking.get(i).getBalance() +"</td>" +
								"<td><a href=\"Deposit.jsp?accountid=" +checking.get(i).getIdAccount() +"&balance=" +checking.get(i).getBalance()
								+"\">Deposit</a></td>" +
								"<td><a href=\"Withdraw.jsp?accountid=" +checking.get(i).getIdAccount() +"&balance=" +checking.get(i).getBalance()
								+"\">Withdraw</a></td>" +
								"<td><a href=\"Transfer.jsp?accountid=" +checking.get(i).getIdAccount() +"&balance=" +checking.get(i).getBalance()
								+"\">Transfer</a></td>"+
								"<td><a href=\"History.jsp?accountid=" +checking.get(i).getIdAccount() +"&balance=" +checking.get(i).getBalance()
								+"\">History</a></td>"
								+"</tr>");
					}
					out.write("</table><br /><br />");
				}
	
				if(savings.size() > 0)
				{
					out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
					out.write("<CAPTION>Saving Accounts:</CAPTION>");
					out.write("<TR> <TH>Account#</TH> <TH>Balance</TH></TR>"); 
					for(int i = 0; i<savings.size(); i++)
					{
						out.write("<tr> " +"<td>" +savings.get(i).getIdAccount() +"</td>" +"<td>$" +savings.get(i).getBalance() +"</td>" +
								"<td><a href=\"Deposit.jsp?accountid=" +savings.get(i).getIdAccount() +"&balance=" +savings.get(i).getBalance()
								+"\">Deposit</a></td>" 
								+"<td><a href=\"Withdraw.jsp?accountid=" +savings.get(i).getIdAccount() +"&balance=" +savings.get(i).getBalance()
								+"\">Withdraw</a></td>"
								+"</tr>");
					}
					out.write("</table><br /><br />");
				}
				
				
				if(loan.size() > 0)
				{
					out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
					out.write("<CAPTION>Loans:</CAPTION>");
					out.write("<TR> <TH>Loan#</TH> <TH>Balance</TH></TR>"); 
					for(int i = 0; i<loan.size(); i++)
					{
						out.write("<tr> " +"<td>" +loan.get(i).getIdAccount() +"</td>" +"<td>$" +loan.get(i).getBalance() +"</td></tr>");
					}
					out.write("</table><br /><br />");
				}
				
				// Log out
//				out.write("<br><form action=\"Login\" method=\"POST\">");
//				out.write("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
//				out.write("<input type=\"submit\" value=\"Logout\"></form></div>");
				
				
				
		        //end
				out.write("</body></html>");
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		//PrintWriter out = response.getWriter();
		//out.write("login fail");
	}

}
