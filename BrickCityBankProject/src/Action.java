

import java.io.IOException;
import brickcitybank.tools.*;
import brickcitybank.message.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlets.RmiUtils;

/**
 * Servlet implementation class Action
 */
public class Action extends HttpServlet {
	private static final long serialVersionUID = 1L;
    double depositAmount = 0;
    double withdrawAmount = 0;
    double transferAmount = 0;
    int destAccount = 0;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Action() {
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
		HttpSession session = request.getSession(false);
		
		RmiUtils rmi = new RmiUtils();
		rmi.connectToRmi();
		rmi.BCBRemoteServer serv = rmi.getMyServ();
		
		// should try catch this, error out if letters etc
		
		int accountID = Integer.parseInt(request.getParameter("accountid"));
		String action = (String)request.getParameter("action");
		String idString = session.getAttribute("userID").toString();
		int userID = Integer.parseInt(idString);
		
		PrintWriter out = response.getWriter();
		
		//echo the header and the menu
		 String header ="<html><link rel=\"STYLESHEET\" type=\"text/css\" href=\"style.css\">"+
	        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
	        "<title>Brick City Bank</title>"+
	        "</head><body><div id=\"header\">"+
	        "<a id=\"ban-title\" href=\"welcome.jsp\">Brick City Bank</a>"+
	        "</div>";
	        out.write(header);
	        
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
	        	user =(String)session.getAttribute("userID").toString();
	        	menu +=("You are logged in "+ user);
	        	// Log out
	        	menu +=("<center><br><form action=\"Login\" method=\"POST\">");
	        	menu +=("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
	        	menu +=("<input type=\"submit\" value=\"Logout\"></center></form>");
	        }

	        menu +=" </div></div>";
	        out.write(menu);
	        
		
		//print content
		out.write("<div id='content'>");
		
		//if it's a deposit
		if(action.equals("deposit"))
		{
			//get the deposit amount
			out.write("<h2>Result Deposit</h2>");
			try{
			depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
			}catch(Exception e){
				out.write("Deposit amount forced to 0");
				depositAmount = 0;
			}
			out.write("Deposit amount: " +depositAmount);
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.DEPOSIT, depositAmount);
			out.write("<br /><p>" +serv.bankAction(mom).getResponse()+"</p>");
		}
		else if(action.equals("withdraw"))
		{
			//if it's a withdrawal
			out.write("<h2>Result Withdrawal</h2>");
			try{
			withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));
			}catch(Exception e){
				out.write("Withdrawal amount forced to 0");
				withdrawAmount = 0;
			}
			out.write("Withdraw amount: " +withdrawAmount);
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.WITHDRAW, withdrawAmount);
			out.write("<br /><p>" +serv.bankAction(mom).getResponse()+"</p>");		
		}
		else if(action.equals("transfer"))
		{
			//it's a tranfert
			out.write("<h2>Result Transfer</h2>");
			System.out.println("Action - Transfer");
			try{
				transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
				destAccount = Integer.parseInt(request.getParameter("destAccount"));
			}catch(Exception e){
				out.write("<p>Transfert amount forced to 0</p>");
				transferAmount = 0;
				destAccount = 0;
			}
			out.write("<p>Operation : Transfer amount: " +transferAmount+ "  to acount " + destAccount+"</p>");
			
			
			//create message and send it to the server
			MessageTransfer mt = new MessageTransfer(userID, accountID, transferAmount, destAccount);
			System.out.println("Action - trying serv.bankAction()");
			// THIS DOESNT DO ANYTHING!!!! returns null
			out.write("<br /><p>" +serv.bankAction(mt).getResponse()+"</p>");		
			//
			System.out.println("Action - done");
		}
		else if(action.equals("history"))
		{
			//it's a tranfert
			out.write("<h2>Result History</h2>");
			
			
			
			//create message and send it to the server
			MessageHistory mh = new MessageHistory(userID, accountID);
			
			// THIS DOESNT DO ANYTHING!!!! returns null
			out.write("<br /><p>" +serv.bankAction(mh).getResponse()+"</p>");		
			//
			System.out.println("Action - done");
		}
		// Return user to Accounts Summary page (Login servlet)
		out.write("<form action=\"Login\" method=\"POST\">");
		out.write("<input type=\"submit\" value=\"Continue\">");
		out.write("</form>");
		
		
		//close the page
		out.write("</div></body></html>");
	}

}
