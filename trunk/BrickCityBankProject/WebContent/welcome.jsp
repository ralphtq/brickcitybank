<%@ page import="rmi.*" %>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div id="content">
<h1>
Welcome to Brick City Bank !
</h1>
<p>
<%
	RmiUtils rmi = new RmiUtils();
	rmi.connectToRmi();
	rmi.BCBRemoteServer serv = rmi.getMyServ();
	String jmsMessage = "";
	jmsMessage = serv.getJMSMessage(true);
	
	if(!jmsMessage.equals(""))
    	out.write("<p class=\"msg\">Message of the Day: " + jmsMessage +"</p>");
%>
</p>

<p>

This is a project for 4002-572
Distributed Application Programming
<br />
<br />
</p>

<p>
For our project, we decided to implement a banking system. 
We plan to support Savings, Checking and loans within the application.

</p>
<p>
User will be able to depose, withdraw and transfer money into and between their account.
<br />
They will also be able to view historic transactions of each account.
</p>

<p>
This is the project of TEAM 4:
<ul>
<li>Caroline Malnoë</li>
<li>Jeffrey Mueller</li>
<li>William Manville</li>
<li>Louis Duke</li>
<li>Ira Mertes</li>
</ul>
</p>
</div>
</div>
</body>
</html>