package checker;

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

}
