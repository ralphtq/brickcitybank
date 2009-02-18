

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
		
		
		
		
		//if it's a deposit
		if(action.equals("deposit"))
		{
			//get the deposit amount
			try{
			depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
			}catch(Exception e){
				out.write("Deposit amount forced to 0");
				depositAmount = 0;
			}
			out.write("Deposit amount: " +depositAmount);
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.DEPOSIT, depositAmount);
			out.write("<br />" +serv.bankAction(mom).getResponse());
		}
		else if(action.equals("withdraw"))
		{
			//if it's a withdrawal
			try{
			withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));
			}catch(Exception e){
				out.write("Withdrawal amount forced to 0");
				withdrawAmount = 0;
			}
			out.write("Withdraw amount: " +withdrawAmount);
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.WITHDRAW, withdrawAmount);
			out.write("<br />" +serv.bankAction(mom).getResponse());		
		}
		else if(action.equals("transfer"))
		{
			//it's a tranfert
			System.out.println("Action - Transfer");
			try{
				transferAmount = Double.parseDouble(request.getParameter("transferAmount"));
				destAccount = Integer.parseInt(request.getParameter("destAccount"));
			}catch(Exception e){
				out.write("Transfert amount forced to 0");
				transferAmount = 0;
				destAccount = 0;
			}
			out.write("Operation : Transfer amount: " +transferAmount+ "  to acount " + destAccount);
			
			
			//create message and send it to the server
			MessageTransfer mt = new MessageTransfer(userID, accountID, transferAmount, destAccount);
			System.out.println("Action - trying serv.bankAction()");
			// THIS DOESNT DO ANYTHING!!!! returns null
			out.write("<br />" +serv.bankAction(mt).getResponse());		
			//
			System.out.println("Action - done");
		}
		
		// Return user to Accounts Summary page (Login servlet)
		out.write("<form action=\"Login\" method=\"POST\">");
		out.write("<input type=\"submit\" value=\"Continue\">");
		out.write("</form>");
		
	}

}