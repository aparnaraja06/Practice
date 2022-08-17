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
			throw new CustomException("ALPHABETS");
		}
		
		String number=Long.toString(mobile);
		
		if(password.contains(name) || password.contains(mail) || password.contains(number))
		{
			throw new CustomException("ALPHABETS");
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
		
		if(amount==null || amount.isEmpty())
		{
			throw new CustomException("EMPTY");
		}
		
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
		
		if(number==null || number.isEmpty())
		{
			throw new CustomException("EMPTY");
		}
		
		result = number.matches("[0-9]+");
		
		if(!result)
		{
			
			throw new CustomException("NUMBER");
		}
		
		long num = Long.valueOf(number);
		
		System.out.println("Num : "+num);
		
		if(num <= 0)
		{
			
			throw new CustomException("NUMBER");
		}
	}
	
	public void validateMail(String mail)throws CustomException
	{
          boolean result= false;
          
          if(mail==null || mail.isEmpty())
          {
        	  result=false;
          }
          
		
		Pattern pattern = Pattern.compile("^[A-Za-z0-9+-._]+@(.+)$");
		Matcher matcher = pattern.matcher(mail);
		
         result = matcher.find();
         
		
		if(!result)
		{
			throw new CustomException("MAIL_ID");
		}
		
	}
	
	public void validateMobile(String mobile)throws CustomException
	{
		boolean result= false;
        
        if(mobile==null || mobile.isEmpty())
        {
      	  result=false;
        }
        
        if(mobile.length()!=10)
        {
        	throw new CustomException("MOBILE");
        }
        
       //checkInteger(mobile);
        
		Pattern pattern = Pattern.compile("[7-9][0-9]{9}");
		Matcher matcher = pattern.matcher(mobile);
		
       result = matcher.find();
       
       if(!result)
		{
			throw new CustomException("MOBILE");
		}
	}
	
	public void validateHumanId(String human_id)throws CustomException
	{
            boolean result= true;
        
        if(human_id==null || human_id.isEmpty())
        {
      	  result=false;
        }
        
        if(human_id.length()!=12)
        {
        	result= false;
        }
        
       //checkInteger(human_id);
       
       if(!result)
		{
			throw new CustomException("HUMAN_ID");
		}

	}

}
