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
	   config.getServletContext().setAttribute("Instance", account);
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
						System.out.println("Id : "+id);
			
			String msg="Invalid username";
			
			HttpSession session = request.getSession();
			
			if(id==0)
			{
				//request.setAttribute("Error",msg);
				
				session.setAttribute("Error", msg);
				
				out.print(msg);
				
				//RequestDispatcher dispatch=request.getRequestDispatcher("Login.jsp");
				//dispatch.include(request, response);
				
			}
			else
			{
				//request.setAttribute("emp_id", id);
				
				session.setAttribute("emp_id", id);				
				
				out.print(id);
				
				//RequestDispatcher dispatch=request.getRequestDispatcher("Password.jsp");
				//dispatch.forward(request, response);
				
			}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
				
		
	}	

}
