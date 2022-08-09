package password;

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
import validate.Validate;

/**
 * Servlet implementation class ChangePassword
 */

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
         response.setContentType("text/html"); // No I18N
		
		CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");
		
		String old=request.getParameter("old");
				
		HttpSession session=request.getSession();
		int user_id=(int)session.getAttribute("user_id");
		
		
		PrintWriter out=response.getWriter();
		
		
		
		try
		{
			String pass = coin.getPassword(user_id);
			
			if(pass.equals(old))
			{
				String new1 = request.getParameter("new1");
				
				String new2 = request.getParameter("new2");
				
				if(new1.equals(new2))
				{
					coin.changePassword(new2, user_id);
					
				}
				else
				{
					session.setAttribute("Error","Password doesn't match");
				}
			}
			else
			{
				session.setAttribute("Error", "Incorrect password");
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
