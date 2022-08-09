package checker;

import custom.CustomException;

public class Checker {
	
	public void checkName(String name)throws CustomException
	{
		if(name==null || name.isEmpty())
		{
			throw new CustomException("Username should not be empty!"); // No I18N
		}
	}
	
	public void checkPassword(String pass)throws CustomException
	{
		if(pass==null || pass.isEmpty())
		{
			throw new CustomException("Password should not be empty!"); // No I18N
		}
	}

}
