<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRANSACTION</title>
<link rel="stylesheet" href="Manage.css">
</head>
<body>
<%
String typeName=(String)request.getParameter("type");
%>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<% 
if(typeName.equals("withdraw"))  
{
%>
<p id="result"></p>
<div class="container">
<h1>WITHDRAW RC</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="withdraw()"><br><br>
</div>
<%}
else
{%>
<p id="result"></p>
<div class="container">
<h1>DEPOSIT RC</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="deposit()"><br><br>
</div>
<%}%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function withdraw()
	{
			  
		var name=$('#amount').val();
		
          var type="withdraw";
		
		//alert(type);
		
		$.ajax({
			
			type : 'POST',
			url: 'transaction',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$("#result").empty();
					 
					 $('#result').append("Successfully withdrawn");
					
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
				 	 
				 
				 $("#amount").click(function()
					{
					  $("#result").empty();
					});
				}
				}
			}); 
			
	}
	function deposit()
	{
			  
		var name=$('#amount').val();
		
		var type="deposit";
		
		
		$.ajax({
			
			type : 'POST',
			url: 'transaction',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$("#result").empty();
					 
					 $('#result').append("Successfully desposited");
					
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
				 	 
				 
				 $("#amount").click(function()
					{
					  $("#result").empty();
					});
				}
				}
			}); 
			
	}
</script>	
</body>
</html>