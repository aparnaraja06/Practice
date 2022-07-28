package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import accounts.Accounts;
import custom.CustomException;

/**
 * Servlet implementation class PasswordServlet
 */

public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public PasswordServlet() {
        super();
       
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
		
		Accounts account=(Accounts)request.getServletContext().getAttribute("Instance");
		
		String pass1=request.getParameter("pass");
		
		HttpSession session=request.getSession();
		int emp_id=(int)session.getAttribute("emp_id");
		
		
		//System.out.println("Emp id : "+emp_id);
		//int emp_id=Integer.valueOf(id);
		
		PrintWriter out=response.getWriter();
		
		String msg="Welcome";
		
		try
		{
			String pass2=account.checkPassword(emp_id);
			
			boolean result=pass1.equals(pass2);
			
			
			if(!result)
			{
				String error="Incorrect password";
				request.setAttribute("Error",error);
				
				
				out.print(error);
				
				//RequestDispatcher dispatch=request.getRequestDispatcher("/Password.jsp");
				//dispatch.include(request, response);
			}
			else
			{
				request.setAttribute("Message",msg);
				
				out.print(msg);
				//RequestDispatcher dispatch=request.getRequestDispatcher("Home.jsp");
				//dispatch.forward(request, response);
			}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}

}
