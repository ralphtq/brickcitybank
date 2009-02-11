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
			out.write("<html><head><style type=\"text/css\">h1 {text-align:center;} h3 {text-align:center;}</style>");
			out.write("</head><body>");
			out.write("<h1>Welcome to Brick City Bank</h1><br /><h3>Online Banking System</h3><br />");
			loan = serv.getAccount(userID, "Loan");
			checking = serv.getAccount(userID, "Checking");
			savings = serv.getAccount(userID, "Saving");
			
			
			
			out.write("<h4>Checking Accounts:</h4>");
			if(checking.size() < 1)
			{
				out.write("None");
			}
			for(int i = 0; i<checking.size(); i++)
			{
				out.write("Account#" +checking.get(i).getIdAccount() +"&nbsp;&nbsp;Balance: $" +checking.get(i).getBalance());
			}
			
			
			out.write("<br /><br /><br /><h4>Saving Accounts:</h4>");
			if(savings.size() < 1)
			{
				out.write("None");
			}
			for(int i = 0; i<savings.size(); i++)
			{
				out.write("Account#" +savings.get(i).getIdAccount() +"&nbsp;&nbsp;Balance: $" +savings.get(i).getBalance());
			}
			
			
			out.write("<br /><br /><br /><h4>Loans:</h4>");
			if(loan.size() < 1)
			{
				out.write("None");
			}
			for(int i = 0; i<loan.size(); i++)
			{
				out.write("Loan#" +loan.get(i).getIdAccount() +"&nbsp;&nbsp;Balance: $" +loan.get(i).getBalance());
			}
		}
		//PrintWriter out = response.getWriter();
		//out.write("login fail");
	}

}
