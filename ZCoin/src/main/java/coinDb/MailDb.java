package coinDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import checker.Checker;
import connect.MysqlConnection;
import custom.CustomException;
import user.User;

public class MailDb {
	
	public void createTable()throws CustomException
	{
		String query="CREATE TABLE IF NOT EXISTS mail(user_id int,"
				+ " mail_id varchar(100)not null,foreign key(user_id) references user(user_id))";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query)) {
			statement.executeUpdate();
		} catch (Exception e) {
			throw new CustomException("Unable to create Mail Table");
		}
	}
	
	public int getId(String mail)throws CustomException
	{
		Checker check = new Checker();
		
		check.checkName(mail);
		
		String query="SELECT user_id FROM mail WHERE mail_id=?";
		
		int id=0;
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setString(1, mail);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					id=result.getInt("user_id");
				}
				
				return id;
			}
		}

		catch (Exception e) {
			throw new CustomException("Id not available");
		}
    }
	
	public void addMail(String mail,int id)throws CustomException
	{
		String query="INSERT INTO mail(user_id,mail_id) VALUES(?,?)";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			statement.setString(2, mail);
			
			statement.executeUpdate();
			
		}
		catch (Exception e) {
			throw new CustomException("Mail id already exists");
		}
	}
	
	public String getMailById(int id)throws CustomException
	{
		
		String query="SELECT mail_id FROM mail WHERE user_id=?";
		
		String mail="";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					mail=result.getString("mail_id");
				}
				
				return mail;
			}
		}
		catch (Exception e) {
			
			throw new CustomException("Mail id not available");
		}

		
	}
	

	

}
