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
<li><a href="Details.jsp">DETAILS</a></li>
<li><a href="Manage.jsp">MANAGE Z COIN</a></li>
<li><a href="#">TRANSACTION HISTORY</a></li>
<li><a href="#">LOGOUT</a></li>
</ul>
<%
} 
else
{
%>
<ul>
<li><a href="Details.jsp">ACCOUNT</a></li>
<li><a href="Signup.jsp">PROFILE</a></li>
<li><a href="UpdateDetails.jsp">CHANGE PASSWORD</a></li>
<li><a href="Transaction.jsp?type=withdraw">WITHDRAW</a></li>
<li><a href="Transaction.jsp?type=deposit">DEPOSIT</a></li>
<li><a href="#">TRANSFER</a></li>
<li><a href="#">HISTORY</a>
<li><a href="#">BUY Z COIN</a></li>
<li><a href="#">LOGOUT</a></li>
</ul>
<%} %>
</nav>
</body>
</html>