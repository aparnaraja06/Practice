package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import checker.Checker;
import custom.CustomException;
import operation.CoinOperation;
import validate.Validate;

/**
 * Servlet implementation class PasswordServlet
 */

public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setContentType("text/html");
		
		CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");
		
		String pass1=request.getParameter("pass");
				
		HttpSession session=request.getSession();
		int user_id=(int)session.getAttribute("user_id");
		
		
		PrintWriter out=response.getWriter();
		
		
		
		try
		{
			String role = coin.getRole(user_id);
			
			Checker check = new Checker();
			check.checkPassword(pass1);
			
			String pass2=coin.getPassword(user_id);
			
			boolean result=pass1.equals(pass2);
			
			
			if(!result)
			{
				String error="Incorrect password";
				request.setAttribute("Error",error);
				
				
				out.print(error);
				
				
			}
			else
			{
				request.setAttribute("Role",role);
				
				session.setAttribute("Role", role);
				
				out.print(role);
				
			}
		}
		catch(CustomException e)
		{
            String error=e.getMessage();
			
            Validate validator=(Validate)request.getServletContext().getAttribute("Validate");
			
			int code = validator.addError(error);
			
			
			response.sendError(code);
		}
		
		
	}

}
