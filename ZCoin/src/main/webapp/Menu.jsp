<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MENU</title>
<link rel="stylesheet" href="Menu.css">
</head>
<body>
<nav>
<br>
<ul>
<%
String role =(String)session.getAttribute("Role");

if(role.equals("admin"))
{
%>
<li><a href="Details.jsp?type=details">DETAILS</a></li>
<li><a href="Details.jsp?type=account">ACCOUNT</a></li>
<li><a href="Signup.jsp?type=profile">PROFILE</a></li>
<li><a href="UpdateDetails.jsp">CHANGE PASSWORD</a></li>
<li><a href="Transaction.jsp?type=withdraw">WITHDRAW</a></li>
<li><a href="Transaction.jsp?type=deposit">DEPOSIT</a></li>
<li><a href="Transaction.jsp?type=transfer">TRANSFER</a></li>
<li><a href="Manage.jsp?type=set">MANAGE Z COIN</a></li>
<li><a href="History.jsp?type=transaction">TRANSACTION HISTORY</a></li>
<li><a href="History.jsp?type=history">HISTORY</a>
<li><a href="Manage.jsp?type=buy">BUY Z COIN</a></li>
<li><a href="logout">LOGOUT</a></li>
</ul>
<%
} 
else
{
%>
<ul>
<li><a href="Details.jsp?type=account">ACCOUNT</a></li>
<li><a href="Signup.jsp?type=profile">PROFILE</a></li>
<li><a href="UpdateDetails.jsp">CHANGE PASSWORD</a></li>
<li><a href="Transaction.jsp?type=withdraw">WITHDRAW</a></li>
<li><a href="Transaction.jsp?type=deposit">DEPOSIT</a></li>
<li><a href="Transaction.jsp?type=transfer">TRANSFER</a></li>
<li><a href="History.jsp?type=history">HISTORY</a>
<li><a href="Manage.jsp?type=buy">BUY Z COIN</a></li>
<li><a href="logout">LOGOUT</a></li>
</ul>
<%} %>
</nav>
</body>
</html>