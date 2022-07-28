<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="accounts.Accounts" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHECK</title>
</head>
<body>
<%

String user=request.getParameter("user");
System.out.println("In check : "+user);
Accounts account=new Accounts();

int id=account.checkUsername(user);

if(id!=0)
{
	out.print(id);
}
else
{
	out.print("Error");
}
%>
</body>
</html>