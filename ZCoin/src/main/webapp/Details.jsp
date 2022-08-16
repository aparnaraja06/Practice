<%@ page import="operation.CoinOperation" %>
<%@ page import="user.User" %>
<%@ page import="account.Account" %>
<%@ page import="instance.CreateInstance"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DETAILS</title>
<link rel="stylesheet" href="Details.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<p id="result"></p>
<%
CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();

String typeName = request.getParameter("type");

%>
<div id="coin">
<h2>Z COIN</h2>
<h2 id="rate">Rate ~ <%= coin.zCoinRate()%></h2>
<h2 id="date"><%= coin.getDate()%></h2>
</div>
<p id="result"></p>
<%

if(typeName.equals("details"))
{
%>
<h1>USER DETAILS</h1>
<table class="center">
<tr>
<th>MAIL</th>
<th>NAME</th>
<th>MOBILE</th>
<th>HUMAN_ID</th>
<th>RC AMOUNT</th>
<th>APPROVED</th>
<th>ROLE</th>
</tr>
<%

List<User> list = coin.showWaitingList();

if(list.isEmpty())
{
%>
<tr><td colspan="5" class="record"><b>*** No records found ***</b></td></tr>
<%
}
else
{
for(int i=0;i<list.size();i++)
{
	User user=list.get(i);
%>
<tr>
<td id="mail"><%=user.getMail()%></td>
<td><%=user.getName() %></td>
<td><%=user.getMobile()%></td>
<td><%=user.getHuman_id()%></td>
<td><%=user.getRc_amount()%></td>
<td><%=user.getRole() %></td>
<td><br><br><input type="button" class="add" onClick="userLogin('mail')" value="ADD USER"><br><br>
<input type="button" class="add" onClick="adminLogin('mail')"value="ADD ADMIN"><br><br></td>
</tr>
<%} }%>
</table>
<%
}
else
{
%>
<h1>ACCOUNT DETAILS</h1>
<table class="center">
<tr>
<th>ACCOUNT NUMBER</th>
<th>RC AMOUNT</th>
<th>ZC AMOUNT</th>
</tr>
<%
int id = (int)session.getAttribute("user_id");

Account account=coin.accountDetails(id);

%>
<tr>
<td><%=account.getAccount_num()%></td>
<td><%=account.getRc_amount()%></td>
<td><%=account.getZc_amount()%></td>
</tr>
<%}%>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function userLogin(mail_id)
	{
		
		var mail = document.getElementById(mail_id);
		
		var id = mail.innerHTML;
		
		var role = "user";
		
		//alert("mail : "+id);
		
       $.ajax({
			
			type : 'POST',
			url: 'add',
			data :{id : id,role : role},
			success:function(result)
			{
				if(result=="Invalid username") 
				{
					 $("#result").empty();
					 
				 $('#result').append(result);
				 				  
				}
				else
				{
					 $("#result").empty();
					 
					 $('#result').append("Successfully added!");
				}
				
			},
			 error: function(xhr)
				{

					try
					{
					if(xhr.status==401)
					{
						throw "Oops! Connection failed! "; // No I18N
					}
					
					else if(xhr.status==402)
					{
						throw "Error! couldn't close Connection! "; // No I18N
					}
					else if(xhr.status==403)
					{
						throw "Username should not be empty!"; // No I18N
					}
					else if(xhr.status==404)
					{
						throw "Password should not be empty!";  // No I18N
					}
					else
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#result").empty();
					  
			        
					 $('#result').append(err);
					 	 
					 
					}
					}
				}); 
				
		}
	function adminLogin(mail_id)
	{
		
		var mail = document.getElementById(mail_id);
		
		var id = mail.innerHTML;
		
		var role = "admin";
		
		//alert("mail : "+id);
		
       $.ajax({
			
			type : 'POST',
			url: 'add',
			data :{id : id,role : role},
			success:function(result)
			{
				if(result=="Invalid username") 
				{
					 $("#result").empty();
					 
				 $('#result').append(result);
				 				  
				}
				else
				{
					 $("#result").empty();
					 
					 $('#result').append("Successfully added!");
				}
				
			},
			 error: function(xhr)
				{

					try
					{
					if(xhr.status==401)
					{
						throw "Oops! Connection failed! "; // No I18N
					}
					
					else if(xhr.status==402)
					{
						throw "Error! couldn't close Connection! "; // No I18N
					}
					else if(xhr.status==403)
					{
						throw "Username should not be empty!"; // No I18N
					}
					else if(xhr.status==404)
					{
						throw "Password should not be empty!";  // No I18N
					}
					else
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#result").empty();
					  
			        
					 $('#result').append(err);
					 	 
					 
					}
					}
				}); 
				
		}
</script>	
</body>
</html>