<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div id="content">
<h2>Deposit</h2>
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

<h3>
Please fill in the form to perform the deposit:
</h3>
<form action="Action" method="POST">
	Deposit Amount: $ &nbsp;&nbsp;<input type="text" name="depositAmount" value="0"><br /><br /><br />
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="deposit">
	<input type="submit" value="Deposit">
</form>
</div>
</div>
</body>
</html>