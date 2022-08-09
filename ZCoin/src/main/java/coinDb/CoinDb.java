package coinDb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import account.Account;
import custom.CustomException;
import user.User;

public class CoinDb 
{
	
	double zCoin=50;

	UserDb userObj = new UserDb();
	AccountDb accountObj = new AccountDb();
	MailDb mailObj = new MailDb();
	TransactionDb transactionObj = new TransactionDb();
	
	public void createTable()throws CustomException
	{
		userObj.createTable();
		accountObj.createTable();
		mailObj.createTable();
		transactionObj.createTable();
	}
	
	public String getRole(int id)throws CustomException
	{
		return userObj.getRole(id);
	}
	
	public String getPassword(int id)throws CustomException
	{
		return userObj.getPassword(id);
	}
		
	public int addUser(User user)throws CustomException
	{
		
		return userObj.addUser(user);
	}
	
	public int getId(String mail)throws CustomException
	{
		return mailObj.getId(mail);
	}
	
	public void addMail(String mail,int id)throws CustomException
	{
		mailObj.addMail(mail,id);
	}
	public List<User> showWaitingList()throws CustomException
	{
		return userObj.showWaitingList();
	}
	public User approveAsUser(User user)throws CustomException
	{
		return userObj.approveAsUser(user);
	}
	public User approveAsAdmin(User user)throws CustomException
	{
		return userObj.approveAsAdmin(user);
	}
	public int addAccount(User user)throws CustomException
	{
		return accountObj.addAccount(user);
	}
	public Account accountDetails(int id)throws CustomException
	{
		return accountObj.accountDetails(id);
	}
	public User getUser(int id)throws CustomException
	{
		return userObj.getUser(id);
	}
	public void changePassword(String pass,int id)throws CustomException
	{
		userObj.changePassword(pass, id);
	}
	
	public double zCoinRate()
	{
		return zCoin;
	}
	
	public double changeZCoinRate(double amount)
	{
		zCoin=amount;
		
		return zCoin;
	}
	
	public String getDate()
	{
		String result="";
		
		long millis=System.currentTimeMillis();
		
		SimpleDateFormat format=new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		
		Date date= new Date(millis);
		
		result=format.format(date);
		
		return result;
	}
	
	
}
