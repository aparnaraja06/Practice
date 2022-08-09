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
import operation.CoinOperation;
import user.User;
import validate.Validate;

/**
 * Servlet implementation class AddServlet
 */

public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
			

			PrintWriter out=response.getWriter();
			
			String role =  request.getParameter("role");
			
			String mail = request.getParameter("mail");
			
			
			try
			{
				CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");
				
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
				
				
				Validate validator=(Validate)request.getServletContext().getAttribute("Validate");
				
				int code = validator.addError(msg);
				
				response.sendError(code);
			}
	}

}
