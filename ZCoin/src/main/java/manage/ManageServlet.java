package manage;

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
import validate.ErrorMsg;


public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
		 
			
			PrintWriter out=response.getWriter();
			
			String name =  request.getParameter("name");
			
			try
			{
				double amount = Double.parseDouble(name);
				
				CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
				
				double amountt=coin.changeZCoinRate(amount);
				
				HttpSession session = request.getSession();
				
				String msg = "error";
				
				if(amount!=amountt)
				{
                   session.setAttribute("Error", msg);
					
					out.print(msg);

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
