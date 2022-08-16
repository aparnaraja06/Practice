package checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import custom.CustomException;

public class Checker {
	
	public void checkName(String name)throws CustomException
	{
		if(name==null || name.isEmpty())
		{
			throw new CustomException("USERNAME"); // No I18N
		}
	}
	
	public void checkPassword(String pass)throws CustomException
	{
		if(pass==null || pass.isEmpty())
		{
			throw new CustomException("PASSWORD"); // No I18N
		}
	}
	
	public void validatePassword(String password,String name,long mobile,String mail)throws CustomException
	{
		boolean result=false;
		
		
		if(password.length()<8)
		{
			result=false;
		}
		
		String number=Long.toString(mobile);
		
		if(name.contains(password) || mail.contains(password) || number.contains(password))
		{
			result=false;
		}
		
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        
        result=matcher.find();
				
		if(!result)
		{
			throw new CustomException("ALPHABETS");
		}
	}
	
	public void validateAmount(String amount)throws CustomException
	{
		boolean result= false;
		
		Pattern pattern = Pattern.compile("[0-9]+([,.][0-9]{1,2})?");
		Matcher matcher = pattern.matcher(amount);
		
		result =  matcher.find();
		
		if(!result)
		{
			throw new CustomException("AMOUNT");
		}
		
		double price = Double.parseDouble(amount);
	
		
		if(price < 0)
		{
			throw new CustomException("AMOUNT");
		}
		
		
		
	}
	
	public void checkInteger(String number)throws CustomException
	{
		boolean result= false;
		
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(number);
		
		result = matcher.find();
		
		if(!result)
		{
			throw new CustomException("NUMBER");
		}
		
		int num = Integer.valueOf(number);
		
		if(num < 0)
		{
			throw new CustomException("NUMBER");
		}
	}

}
