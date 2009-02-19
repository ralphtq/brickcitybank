<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div id="content">
<h2>Transfer</h2>
<p>
<%
out.write("Your user ID is : "+session.getAttribute("userID"));
%>
<br /></p>
<h3>
 Here are your current account information:
</h3>
<table class="fancytable">
<tr><td>Account#</td><td>Balance: $</td></tr>
<tr><td><%=(String)request.getParameter("accountid")%></td><td><%=(String)request.getParameter("balance")%></td>
</tr>
</table>

<br><br>
<h3>
Please fill in the form to perform the transfer:
</h3>
<form action="Action" method="POST">
	
	Transfer Amount: $<input type="text" name="transferAmount" value="0"><br />
	To Account# <input type="text" name="destAccount" value="0"><br />
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="transfer"><br /><br />
	<input type="submit" value="Transfer">
</form>
</body>
</html>