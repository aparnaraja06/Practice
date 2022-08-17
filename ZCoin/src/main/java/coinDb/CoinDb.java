package coinDb;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

import account.Account;
import operation.CustomException;
import transaction.Transaction;
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
	
	public double changeZCoinRate(double amount)throws CustomException
	{
		double old = zCoin;
		
		zCoin=amount;
		
		double times = getDifference(old,zCoin);
		
		changeZcAmount(times);
		
		return zCoin;
	}
	
	public double getDifference(double old_value,double new_value)
	{
		double change = new_value/old_value;
		
		return change;
	}
	
	public void changeZcAmount(double times)throws CustomException
	{
		accountObj.changeZcAmount( times);
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
	
	public int getAccountNumById(int id)throws CustomException
	{
		return accountObj.getAccountNumById(id);
	}
	
	public double getRcBalance(int acc_num)throws CustomException
	{
		return accountObj.getRcBalance(acc_num);
	}
	
	public boolean withdrawRc(int acc_num, double amount)throws CustomException
	{
		return accountObj.withdrawRc(acc_num, amount);
	}
	
	public boolean depositRc(int acc_num, double amount)throws CustomException
	{
		return accountObj.depositRc(acc_num, amount);
	}
	
	public boolean buyZCoin(int acc_num, double amount)throws CustomException
	{
		return accountObj.buyZCoin(acc_num, amount);
	}
	
	public boolean transferZCoin(int from_account, int to_account, double amount)throws CustomException
	{
		return accountObj.transferZCoin(from_account, to_account, amount);
	}
	
	public void addTransaction(Transaction transfer)throws CustomException
	{
		transactionObj.addTransaction(transfer);
	}
	
	public Map<Integer,List<Transaction>> getAllHistory()throws CustomException
	{
		return transactionObj.getAllHistory();
	}
	
	public List<Transaction> getHistoryByUserId(int user_id)throws CustomException
	{
		return transactionObj.getHistoryByUserId(user_id);
	}
	
	public boolean checkMailExists(String mail)throws CustomException
	{
		return mailObj.checkMailExists(mail);
	}
	
	
}
