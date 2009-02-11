package servlets;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		
		//get username
		String username = request.getParameter("userName");
		//getpassword
		String password = request.getParameter("password");
		//get values
		
		RmiUtils rmi = new RmiUtils();
		rmi.connectToRmi();
		rmi.BCBRemoteServer serv = rmi.getMyServ();
		int userID = serv.authenWeb(username,password);
		
		if(userID <= 0)
		{
			//reddirect user to login page
			PrintWriter out = response.getWriter();
			out.write("login fail");
		}
		else
		{
			//redirect people to account listing 
			PrintWriter out = response.getWriter();
			out.write("<html><head><style type=\"text/css\">caption {font-weight:bold;} h1 {font-family:verdana;text-align:center;} h3 {font-family:verdana;text-align:center;}body{font-family:verdana;}</style>");
			out.write("</head><body>");
			out.write("<h1>Welcome to Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
			loan = serv.getAccount(userID, "Loan");
			checking = serv.getAccount(userID, "Checking");
			savings = serv.getAccount(userID, "Saving");
			
			
			out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
			out.write("<CAPTION>Checking Accounts:</CAPTION>");
			out.write("<TR> <TH>Account#</TH> <TH>Balance</TH></TR>"); 
			for(int i = 0; i<checking.size(); i++)
			{
				out.write("<tr> " +"<td>" +checking.get(i).getIdAccount() +"</td>" +"<td>$" +checking.get(i).getBalance() +"</td></tr>");
			}
			out.write("</table><br /><br />");
			
			
			out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
			out.write("<CAPTION>Saving Accounts:</CAPTION>");
			out.write("<TR> <TH>Account#</TH> <TH>Balance</TH></TR>"); 
			for(int i = 0; i<savings.size(); i++)
			{
				out.write("<tr> " +"<td>" +savings.get(i).getIdAccount() +"</td>" +"<td>$" +savings.get(i).getBalance() +"</td></tr>");
			}
			out.write("</table><br /><br />");
			
			
			out.write("<TABLE Border=\"3\" Cellpadding=\"6\" Cellspacing=\"1\" Align=\"center\">"); 
			out.write("<CAPTION>Loans:</CAPTION>");
			out.write("<TR> <TH>Loan#</TH> <TH>Balance</TH></TR>"); 
			for(int i = 0; i<loan.size(); i++)
			{
				out.write("<tr> " +"<td>" +loan.get(i).getIdAccount() +"</td>" +"<td>$" +loan.get(i).getBalance() +"</td></tr>");
			}
			out.write("</table><br /><br />");
		}
		//PrintWriter out = response.getWriter();
		//out.write("login fail");
	}

}
