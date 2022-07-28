package accounts;

import custom.CustomException;
import login.db.LoginDb;

public class Accounts 
{

	LoginDb login=new LoginDb();
	
	public int checkUsername(String mail)throws CustomException
	{
		return login.checkUsername(mail);
	}
	
	public String checkPassword(int id)throws CustomException
	{
		return login.checkPassword(id);
	}
}
