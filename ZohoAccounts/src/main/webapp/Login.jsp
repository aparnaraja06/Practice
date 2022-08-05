<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="accounts.Accounts" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<link rel = "icon" href = 
"A-letter.jpg" 
        type = "image/x-icon">
<title>SIGN IN</title>
</head>
<body>
<div class="total">
<p id="error"></p>
<div class="container">
<form id="login_form">
<img src="Aparna-design-sketch-name.png" alt="Aparna" width="100" height="80"><br>
<h2>SIGN IN </h2>
<h3>to access accounts</h3><br><br>
<input type="text" placeholder="Email address" name="username" id="name" required>
<p id="result"></p>
<div id="msgbox"></div>
<input type="button" id="loginbtn" value="NEXT" onclick="login()"><br><br>
<a href="#" class="link">Forgot password?</a><br><br>
</form>
</div>
<div class="signup">
Don't have an account?<a href="#" class="sign">Sign up now</a>
</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function login()
	{
			  
		var name=$('#name').val();
		
		$.ajax({
			
			type : 'POST',
			url: 'login',
			data :{name : name},
			success:function(result)
			{
				
				if(result=="Invalid username") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Invalid Username");
				 
				 $("#name").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
		
					var successUrl = "Password.jsp";
				    window.location.href = successUrl;
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
				 	 
				 
				 $("#name").click(function()
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