<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MANAGE</title>
<link rel="stylesheet" href="Manage.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<div class="container">
<h1>SET Z COIN RATE</h1>
<label><b>ENTER AMOUNT</b></label><br><br>
<input type="text" placeholder="Amount" name="amount" id="name" required><br><br>
<input type="button" id="loginbtn" value="NEXT" onclick="login()"><br><br>
</div>
</body>
</html>