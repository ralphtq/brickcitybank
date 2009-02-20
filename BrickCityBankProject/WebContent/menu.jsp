<div id="menu">
<div class='element_menu'>
<h3>Login</h3>
<%

HttpSession s = request.getSession(false);
String user ="";
if((s.getAttribute("userID")) == null){
%>
<form action="http://localhost:8181/BrickCityBankProject/Login" method="post">
		Username:<br/> <input type="text" name="userName"/><br/>
		Password:<br/> <input type="password" name="password"/><br /><br />
		<input type="submit" value="Login"/>
	</form>
<%
}else{
	
	try
	{
		user =(String)s.getAttribute("userID").toString();
		String username = s.getAttribute("username").toString();
	
	
		if(username != null)
			out.write("You are logged in as:\n"+ username);
		else
			out.write("You are logged in. User ID: " +user);
	}
	catch(Exception e)
	{
		out.write("You are logged in.");
	}
	// Log out
	out.write("<center><br><form action=\"Login\" method=\"POST\">");
	out.write("<input type=\"hidden\" name=\"logout\" value=\"logout\">");
	out.write("<input type=\"submit\" value=\"Logout\"></center></form>");
}%>


	
</div>
</div>