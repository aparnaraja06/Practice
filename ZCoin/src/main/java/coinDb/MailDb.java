package coinDb;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.List;

import operation.Checker;
import operation.CustomException;
import user.User;

public class MailDb {
	
	public void createTable()throws CustomException
	{
		String query="CREATE TABLE IF NOT EXISTS mail(user_id int,"
				+ " mail_id varchar(100)not null,foreign key(user_id) references user(user_id))";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query)) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
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
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
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
		catch(CustomException e)
		{
			System.out.println("In mail db");
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("MAIL"); // No I18N
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
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			
			throw new CustomException("Mail id not available");
		}

		
	}
	
	public boolean checkMailExists(String mail)throws CustomException
	{
		String query = "SELECT mail_id FROM mail WHERE mail_id=?";
		
		String mail_id=null;
		boolean check=true;
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setString(1, mail);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					mail_id = result.getString("mail_id");
					
					if(!mail_id.equals(mail))
					{
						throw new CustomException("MAIL");
					}
				}
				return check;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("MAIL");
		}
	}
	

	

}
