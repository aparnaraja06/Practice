<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PASSWORD</title>
<link rel = "icon" href = 
"A-letter.jpg" 
        type = "image/x-icon">
<link rel="stylesheet" href="Login.css">
</head>
<body>
<div class="container">
<form>
<img src="Aparna-design-sketch-name.png" alt="Aparna" width="100" height="80"><br>
<h2>PASSWORD</h2><br><br>
<input type="password" placeholder="Enter Password" id= "pass" name="password" required>
<p id="result"></p><br><br>
<input type="submit" id="loginbtn" value="LOGIN" onclick="welcome()"><br><br>
</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

	function welcome()
	{

		
		var pass=$('#pass').val();
		
		$.ajax({
			
			type : 'POST',
			url: 'password',
			data :{pass : pass},
			success:function(result)
			{
				//alert(result);
				
				if(result=="Incorrect password")
				{
					alert("Error");
					
					//var successUrl = "Password.jsp";
				    //window.location.href = successUrl;
				    $('#result').html("Incorrect Password");
				}
				else
				{
					alert("success");
					var successUrl = "Home.jsp";
				    window.location.href = successUrl;
				}
				
			}
			
			
		});
	}
</script>	
	
</body>
</html>