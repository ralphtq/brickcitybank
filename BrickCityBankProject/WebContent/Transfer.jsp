<%@ page session="true" language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BrickCityBank - Transfer</title>
</head>
<body>
<%
out.write("<h2>Transfer<h1>" +" user ID: "+session.getAttribute("userID"));
out.write("<br />Balance: " +(String)request.getParameter("balance"));
%>
<br><br>
<form action="Action" method="POST">
	<ul>
	<li>Transfer Amount: $<input type="text" name="transferAmount" value="0"></li>
	<li>To Account# <input type="text" name="destAccount" value="0"></li>
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="transfer">
	<li><input type="submit" value="Transfer"></li>
	</ul>
</form>
</body>
</html>