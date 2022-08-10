package add;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import custom.CustomException;
import instance.CreateInstance;
import operation.CoinOperation;
import user.User;
import validate.ErrorMsg;


/**
 * Servlet implementation class AddServlet
 */

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
  

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
			
			String role =  request.getParameter("role");
			
			String mail = request.getParameter("mail");
			
			
			try
			{
				CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
				
				int user_id=coin.getId(mail);
				
				User user = coin.getUser(user_id);
				
				String msg="Something went wrong";

				HttpSession session = request.getSession();
				
				if(role.equals("user"))
				{
					
					coin.approveAsUser(user);
					
					session.setAttribute("user_id", user_id);
					
				}
				else if(role.equals("admin"))
				{
					coin.approveAsAdmin(user);
					
					session.setAttribute("user_id", user_id);
				}
				else
				{
					session.setAttribute("Error", msg);
				}
			}
			catch(CustomException e)
			{
                String msg=e.getMessage();	
				
                ErrorMsg err = ErrorMsg.valueOf(msg);
				
				int code = err.getCode();
				
				response.sendError(code);
			}
	}

}
