<%@ page session="true" language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BrickCityBank - Deposit</title>
</head>
<body>
<%
out.write("Deposit<br />" +"user ID: "+session.getAttribute("userID"));
out.write("<br />Balance: " +(String)request.getParameter("balance"));
%>
<br><br>
<form action="Action" method="POST">
	Deposit Amount: $<input type="text" name="depositAmount" value="0">
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="deposit">
	<input type="submit" value="Deposit">
</form>
</body>
</html>