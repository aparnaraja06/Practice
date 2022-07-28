package login.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.ConnectDb;
import custom.CustomException;

public class LoginDb {
	
	
	public int checkUsername(String mail)throws CustomException
	{
		
		String query="SELECT emp_id FROM login WHERE mail_id=?";
		
		int id=0;
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, mail);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
				id=result.getInt("emp_id");
				}
				
				return id;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public String checkPassword(int id)throws CustomException
	{
		
		String query="SELECT Password FROM login where emp_id=?";
		
		String password=null;
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, id);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					password=result.getString("Password");
					
					
				}
				
				return password;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}

}
