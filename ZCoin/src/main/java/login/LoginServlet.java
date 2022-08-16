package login;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import checker.Checker;
import custom.CustomException;
import instance.CreateInstance;
import operation.CoinOperation;
import validate.ErrorMsg;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*public void init(ServletConfig config)throws ServletException
	   {
		  CoinOperation coin = new CoinOperation();
		  Validate validator = new Validate();
		   config.getServletContext().setAttribute("Instance", coin);
		   config.getServletContext().setAttribute("Validate", validator);
			super.init(config);
	   }*/
       	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 response.setContentType("text/html");
		 
			
			PrintWriter out=response.getWriter();
			
			String name =  request.getParameter("name");
			
			try
			{
				//CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");
				
				CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
				
				Checker check = CreateInstance.COINOPERATION.getCheckInstance();
				
				check.checkName(name);
				
				int id=coin.getId(name);
				
				String msg="Invalid username";
				
				HttpSession session = request.getSession();
				
				if(id==0)
				{
					
					session.setAttribute("Error", msg);
					
					out.print(msg);
					
					
				}
				else
				{
					
					
					session.setAttribute("user_id", id);	
					
					
				    out.print(id);
					
					
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
