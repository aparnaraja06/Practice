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

}
