<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="accounts.Accounts" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<link rel = "icon" href = 
"A-letter.jpg" 
        type = "image/x-icon">
<title>SIGN IN</title>
</head>
<body>
<div class="total">
<div class="container">
<form id="login_form">
<img src="Aparna-design-sketch-name.png" alt="Aparna" width="100" height="80"><br>
<h2>SIGN IN </h2>
<h3>to access accounts</h3><br><br>
<input type="text" placeholder="Email address" name="username" id="name" required>
<p id="result"></p>
<div id="msgbox"></div>
<input type="submit" id="loginbtn" value="NEXT" onclick="login()"><br><br>
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
				//alert(result);
				
				if(result=="Invalid username")
				{
					alert("Error");
					
					//var successUrl = "/ZohoAccounts/?username="+name;
				  //  window.location.href = successUrl;
				     
				 //var successUrl = "/ZohoAccounts";
				 //window.location.href = successUrl; 	
				 $('#result').append("Invalid Username");
				 
				  
				}
				else
				{
					alert("success");
					var successUrl = "Password.jsp";
				    window.location.href = successUrl;
				}
				
			}
			
			
		});
	}

</script>	
</body>
</html>