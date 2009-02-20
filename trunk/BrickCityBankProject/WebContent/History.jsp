<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<div id="content">
<h2>History</h2>
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


<form action="Action" method="POST">
	
	<input type="hidden" name="accountid" value=<%=(String)request.getParameter("accountid")%>>
	<input type="hidden" name="action" value="history">
	<input type="submit" value="View History">
</form>
</div>
</div>
</body>
</html>