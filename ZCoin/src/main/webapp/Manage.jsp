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
<p id="result"></p>
<div class="container">
<h1>SET Z COIN RATE</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="manage()"><br><br>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function manage()
	{
			  
		var name=$('#amount').val();
		
		$.ajax({
			
			type : 'POST',
			url: 'manage',
			data :{name : name},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! unable to set");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$('#result').append("Successfully updated");
					
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