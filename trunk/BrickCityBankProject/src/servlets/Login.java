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
		PrintWriter out = response.getWriter();

		RmiUtils rmi = new RmiUtils();
		rmi.connectToRmi();
		rmi.BCBRemoteServer serv = rmi.getMyServ();
		
		String username = "";
        String password = "";
		
        String jmsMessage = serv.getJMSMessage();
        System.out.println("jms message from server: " + jmsMessage);
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
				out.write("<html><head><style type=\"text/css\">caption {font-weight:bold;} h1 {font-family:verdana;text-align:center;} h2 {font-family:verdana;text-align:center;} h3 {font-family:verdana;text-align:center;}body{font-family:verdana;text-align:center;}</style>");
				out.write("</head><body>");
				out.write("<h1>Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
				out.write("You have been logged out.");
				session.invalidate();
				session = null;
				out.write("<a href=\"C:/login.html\"> Click here</a> to continue.");
				out.write("</body></html>");
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			loggedOut = true;
		}

		try
		{
			if(userID <= 0 && !request.getParameter("logout").equals("logout") && !loggedOut)
			{
				//redirect user to login page
				out.write("<html><head><style type=\"text/css\">caption {font-weight:bold;} h1 {font-family:verdana;text-align:center;} h2 {font-family:verdana;text-align:center;} h3 {font-family:verdana;text-align:center;}body{font-family:verdana;text-align:center;}</style>");
				out.write("</head><body>");
				out.write("<h1>Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
				out.write("Login failed.");
				session.invalidate();
				session = null;
				out.write(" <a href=\"C:/login.html\">Click here</a> to go back and try again.");
				out.write("</body></html>");
			}
			else
			{
				session.setAttribute("userID", userID);
				//redirect people to account listing 
				out.write("<html><head>" +
						"<style type=\"text/css\">" +
						"caption {font-weight:bold;}" +
						"h1 {font-family:verdana;text-align:center;} " +
						"h2 {font-family:verdana;text-align:center;} " +
						"h3 {font-family:verdana;text-align:center;}" +
						"body{font-family:verdana;text-align:center;}" +
						"h6 {font-weight:bold;text-align:center;}" +
						"</style>");
				out.write("</head><body>");
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
								+"\">Transfer</a></td>"
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
				out.write("<br><form action=\"Login\" method=\"POST\">");
				out.write("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
				out.write("<input type=\"submit\" value=\"Logout\">");
				out.write("</form>");
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