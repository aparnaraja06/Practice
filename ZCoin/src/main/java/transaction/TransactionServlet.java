package transaction;

import java.io.IOException;
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


public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
   	 response.setContentType("text/html");
   	 
   	String name =  request.getParameter("name");
   	
   	HttpSession session = request.getSession();
	
	try
	{
		//CoinOperation coin=(CoinOperation)request.getServletContext().getAttribute("Instance");
		
		CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();
		
		int id = (int)request.getSession().getAttribute("user_id");
		
		int acc_num = coin.getAccountNumById(id);
		
		double amount = Double.parseDouble(name);
		
		String msg = "error";
		
		boolean result=false;
		
		if(request.getParameter("type").equals("withdraw"))
		{
		
		result = coin.withdrawRc(acc_num, amount);
	
		}
		else
		{
			result=coin.depositRc(acc_num, amount);
		}
		
		if(!result)
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
