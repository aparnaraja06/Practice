package login;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import accounts.Accounts;
import custom.CustomException;
import validate.Validate;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   public void init(ServletConfig config)throws ServletException
   {
	   Accounts account=new Accounts();
	   Validate validator=new Validate();
	   config.getServletContext().setAttribute("Instance", account);
	   config.getServletContext().setAttribute("Validate", validator);
		super.init(config);
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		String name =  request.getParameter("name");
		
		try
		{
			Accounts account=(Accounts)request.getServletContext().getAttribute("Instance");
			
			int id=account.checkUsername(name);
			
			String msg="Invalid username";
			
			HttpSession session = request.getSession();
			
			if(id==0)
			{
				
				session.setAttribute("Error", msg);
				
				out.print(msg);
				
				
			}
			else
			{
				
				
				session.setAttribute("emp_id", id);				
				
			    out.print(id);
				
				
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
