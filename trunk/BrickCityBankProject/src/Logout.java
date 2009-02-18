

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		// log out user
		
		
		out.write("<html><head><style type=\"text/css\">caption {font-weight:bold;} h1 {font-family:verdana;text-align:center;} h2 {font-family:verdana;text-align:center;} h3 {font-family:verdana;text-align:center;}body{font-family:verdana;text-align:center;}</style>");
		out.write("</head><body>");
		out.write("<h1>Brick City Bank</h1><br /><h2>Online Banking System</h2><br />");
		out.write("You have been logged out.\n\n");
		
		// Return user to Accounts Summary page (Login servlet)
		out.write("<form action=\"Login\" method=\"POST\">");
		out.write("<input type=\"hidden\" value=\"logout\">");
		out.write("<input type=\"submit\" value=\"Continue\">");
		out.write("</form>");
		
		out.write("</body></html>");
	}

}
