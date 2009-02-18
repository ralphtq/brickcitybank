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
out.write("<h2>Withdraw</h2>" +"user ID: "+session.getAttribute("userID"));
out.write("<h3>Balance: " +(String)request.getParameter("balance")+"</h3>" );
%>
<br><br>
<form action="Action" method="POST"><ul><li>
	Withdraw Amount: $<input type="text" name="withdrawAmount" value="0"></li>
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="withdraw">
	<li><input type="submit" value="Withdraw"></li>
</form>
</body>
</html>