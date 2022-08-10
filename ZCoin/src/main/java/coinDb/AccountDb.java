package coinDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import account.Account;
import connect.MysqlConnection;
import custom.CustomException;
import user.User;

public class AccountDb {
	
	public void createTable() throws CustomException
	{
		String query="CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num int not null auto_increment,"
				+ " rc_amount double not null, zc_amount double, primary key(account_num), "
				+ "foreign key(user_id) references user(user_id))Engine=InnoDB auto_increment=1000";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query)) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Account Table");
		}
	}
	
	public int addAccount(User user)throws CustomException
	{
		String query = "INSERT INTO account(user_id,rc_amount) values(?,?)";
		
		int acc_num=0;
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			int id = user.getUser_id();
			double amount = user.getRc_amount();
			
			statement.setInt(1, id);
			statement.setDouble(2, amount);
			
              statement.executeUpdate();
			
			try (ResultSet result = statement.getGeneratedKeys()) 
			{
				if (result.next()) 
				{
					acc_num=result.getInt("account_num");
				}
				
				return acc_num;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Account Table");
		}
			
	}
	
	public Account accountDetails(int id)throws CustomException
	{
		String query="SELECT * FROM account WHERE user_id=?";
		
		Account account = new Account();
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					account.setAccount_num(result.getInt("account_num"));
					account.setRc_amount(result.getDouble("rc_amount"));
					account.setZc_amount(result.getDouble("zc_amount"));
				}
				
				return account;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Fetch Account details");
		}
		
	}
	
	public int getAccountNumById(int id)throws CustomException
	{
		String query = "SELECT account_num FROM account WHERE user_id=?";
		
		int account_num=0;
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					account_num=result.getInt("account_num");
				}
				
				return account_num;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get account number");
		}
	}
	
	public double getRcBalance(int acc_num)throws CustomException
	{
		String query="SELECT rc_amount FROM account WHERE account_num=?";
		
		double amount=0.0;
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, acc_num);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					amount =  result.getDouble("rc_amount");
				}
				
				return amount;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get balance");
		}


					
	}
	
	public boolean withdrawRc(int acc_num, double amount)throws CustomException
	{
		double balance = getRcBalance(acc_num);
		
		if(balance < amount)
		{
			throw new CustomException("WITHDRAW");
		}
		
		double total = balance-amount;
		
		String query = "UPDATE account SET rc_amount=? WHERE account_num=?";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to withdraw");
		}
	
	}
	
	public boolean depositRc(int acc_num, double amount)throws CustomException
	{
		double balance = getRcBalance(acc_num);
		
		double total = balance+amount;
		
		String query = "UPDATE account SET rc_amount=? WHERE account_num=?";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setDouble(1, total);
			statement.setInt(2, acc_num);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to Deposit");
		}
	
	}


}
