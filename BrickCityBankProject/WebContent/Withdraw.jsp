<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div id="content">
<h2>Withdraw</h2>
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
	Withdraw Amount: $<input type="text" name="withdrawAmount" value="0"><br />
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="withdraw"><br />
	<input type="submit" value="Withdraw">
</form>
</div>
</div>
</body>
</html>