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
			//redirect people to accountlisting 
			PrintWriter out = response.getWriter();
			out.write("login success - UserID:" +userID);
			loan = serv.getAccount(userID, "Loan");
			checking = serv.getAccount(userID, "Checking");
			savings = serv.getAccount(userID, "Saving");
			
			out.write("<br />Loan:<br />");
			for(int i = 0; i<loan.size(); i++)
			{
				out.write(loan.get(i).getIdAccount() +"--" +loan.get(i).getBalance() +"<br />");
			}
			
			out.write("<br />Checking:<br />");
			for(int i = 0; i<checking.size(); i++)
			{
				out.write(checking.get(i).getIdAccount() +"--" +checking.get(i).getBalance() +"<br />");
			}
			
			out.write("<br />Saving:<br />");
			for(int i = 0; i<savings.size(); i++)
			{
				out.write(savings.get(i).getIdAccount() +"--" +savings.get(i).getBalance() +"<br />");
			}
		}
		//PrintWriter out = response.getWriter();
		//out.write("login fail");
	}

}
