package coinDb;

import java.sql.PreparedStatement;

import connect.MysqlConnection;
import custom.CustomException;

public class TransactionDb 
{

	public void createTable()throws CustomException
	{
		String query="CREATE TABLE IF NOT EXISTS transaction(user_id int not null, from_account int not null,"
				+ " to_account int not null, type varchar(10) not null, amount double , date varchar(20),"
				+ " foreign key(user_id) references user(user_id), "
				+ "foreign key(from_account) references account(account_num))";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query)) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to create Transaction Table");
		}
	}
}
