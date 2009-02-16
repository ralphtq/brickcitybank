

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
		try
		{
			depositAmount = Double.parseDouble(request.getParameter("depositAmount"));
		}
		catch(NullPointerException npe)
		{
			System.out.println("NullPointerException for DEPOSIT");
		}

		try
		{
			withdrawAmount = Double.parseDouble(request.getParameter("withdrawAmount"));
		}
		catch(NullPointerException npe)
		{
			System.out.println("NullPointerException for WITHDRAW");
		}
		
		int accountID = Integer.parseInt(request.getParameter("accountid"));
		String action = (String)request.getParameter("action");
		String idString = session.getAttribute("userID").toString();
		int userID = Integer.parseInt(idString);
		
		PrintWriter out = response.getWriter();
		out.write("Deposit amount: " +depositAmount);
		out.write("Withdraw amount: " +withdrawAmount);
		
		if(action.equals("deposit"))
		{
			System.out.println("in deposit");
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.DEPOSIT, depositAmount);
			out.write("<br />" +serv.bankAction(mom).getResponse());
		}
		else if(action.equals("withdraw"))
		{
			System.out.println("in withdraw");
			MessageOrderMoney mom = new MessageOrderMoney(userID, accountID, ActionTool.WITHDRAW, withdrawAmount);
			out.write("<br />" +serv.bankAction(mom).getResponse());		
		}
		
		// Return user to Accounts Summary page (Login servlet)
		out.write("<form action=\"Login\" method=\"POST\">");
		out.write("<input type=\"submit\" value=\"Continue\">");
		out.write("</form>");
		
	}

}
