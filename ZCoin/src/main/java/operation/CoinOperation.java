package operation;

import java.util.List;

import account.Account;
import coinDb.CoinDb;
import custom.CustomException;
import user.User;

public class CoinOperation 
{

	CoinDb coin = new CoinDb();
	
	
	public void createTable()throws CustomException
	{
		try
		{
		coin.createTable();
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new CustomException(e.getMessage());
		}
	}
	
	public String getRole(int id)throws CustomException
	{
		return coin.getRole(id);
	}
	
	public String getPassword(int id)throws CustomException
	{
		return coin.getPassword(id);
	}
		
	public int addUser(User user)throws CustomException
	{
		
		return coin.addUser(user);
	}
	
	public int getId(String mail)throws CustomException
	{
		return coin.getId(mail);
	}
	
	public void addMail(String mail,int id)throws CustomException
	{
		coin.addMail(mail,id);
	}
	public List<User> showWaitingList()throws CustomException
	{
		return coin.showWaitingList();
	}
	public User approveAsUser(User user)throws CustomException
	{
		return coin.approveAsUser(user);
	}
	public User approveAsAdmin(User user)throws CustomException
	{
		return coin.approveAsAdmin(user);
	}
	public int addAccount(User user)throws CustomException
	{
		return coin.addAccount(user);
	}
	public Account accountDetails(int id)throws CustomException
	{
		return coin.accountDetails(id);
	}
	public User getUser(int id)throws CustomException
	{
		return coin.getUser(id);
	}
	public void changePassword(String pass,int id)throws CustomException
	{
		coin.changePassword(pass, id);
	}
	public double zCoinRate()
	{
		return coin.zCoinRate();
	}
	public String getDate()
	{
		return coin.getDate();
	}
	
	
}
