package operation;

import java.util.List;
import java.util.Map;

import account.Account;
import coinDb.CoinDb;
import custom.CustomException;
import transaction.Transaction;
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
	public double changeZCoinRate(double amount)throws CustomException
	{
		return coin.changeZCoinRate(amount);
	}
	public void changeZcAmount(double times)throws CustomException
	{
		coin.changeZcAmount(times);
	}
	public String getDate()
	{
		return coin.getDate();
	}
	public int getAccountNumById(int id)throws CustomException
	{
		return coin.getAccountNumById(id);
	}
	public double getRcBalance(int acc_num)throws CustomException
	{
		return coin.getRcBalance(acc_num);
	}
	public boolean withdrawRc(int acc_num, double amount)throws CustomException
	{
		return coin.withdrawRc(acc_num, amount);
	}
	public boolean depositRc(int acc_num, double amount)throws CustomException
	{
		return coin.depositRc(acc_num, amount);
	}
	public boolean buyZCoin(int acc_num, double amount)throws CustomException
	{
		return coin.buyZCoin(acc_num, amount);
	}
	public boolean transferZCoin(int from_account, int to_account, double amount)throws CustomException
	{
		return coin.transferZCoin(from_account, to_account, amount);
	}
	public void addTransaction(Transaction transfer)throws CustomException
	{
		coin.addTransaction(transfer);
	}
	public Map<Integer,List<Transaction>> getAllHistory()throws CustomException
	{
		return coin.getAllHistory();
	}
	public List<Transaction> getHistoryByUserId(int user_id)throws CustomException
	{
		return coin.getHistoryByUserId(user_id);
	}
	public boolean checkMailExists(String mail)throws CustomException
	{
		return coin.checkMailExists(mail);
	}
	
}
