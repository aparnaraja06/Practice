package coinDb;

import java.sql.PreparedStatement;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import operation.CustomException;
import user.User;

public class UserDb 
{
	MailDb mailObj = new MailDb();


	public void createTable()throws CustomException
	{
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.CREATE.createUserTable())) {
			statement.executeUpdate();
		} 
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			
			throw new CustomException("Unable to create User Table");
		}
	}
	
	
	public String getRole(int id)throws CustomException
	{
		
		String role="";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.SELECT_USER.getRole(),
						PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					role=result.getString("role");
				}
				
				return role;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			throw new CustomException("Unable to get Role");
		}
	}
	
	public String getPassword(int id)throws CustomException
	{
		
		String password="";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.SELECT_USER.getPassword(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					password=result.getString("password");
				}
				
				return password;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			throw new CustomException("Unable to get password");
		}
	}
	
	public int addUser(User user)throws CustomException
	{
			
		int id=0;
		 
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.INSERT.addUser(),
				PreparedStatement.RETURN_GENERATED_KEYS)) 
		{
			
			String name = user.getName();
			long mobile = user.getMobile();
			String human_id = user.getHuman_id();
			String password =  user.getPassword();
			double amount = user.getRc_amount();
			
			statement.setString(1, name);
			statement.setLong(2, mobile);
			statement.setString(3, human_id);
			statement.setString(4, password);
			statement.setDouble(5, amount);
			
			statement.executeUpdate();
			
			try (ResultSet result = statement.getGeneratedKeys()) 
			{
				if (result.next()) 
				{
					id=result.getInt(1);
			
				}
				
				return id;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException("Unable to add user");
		}
		
	}
	
	public List<User> showWaitingList()throws CustomException
	{
		
		List<User> list = new ArrayList<>();
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.SELECT_ALL.showWaitingList()))
		{
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					User user = new User();
					
					int id=result.getInt("user_id");
					user.setUser_id(id);
					String mail=mailObj.getMailById(id);
					user.setMail(mail);
					user.setName(result.getString("name"));
					user.setHuman_id(result.getString("human_id"));
					user.setMobile(result.getLong("mobile"));
					user.setRc_amount(result.getDouble("rc_amount"));
					user.setApproved(result.getBoolean("approved"));
					user.setRole(result.getString("role"));
					
					list.add(user);
				}
				
				return list;
			}
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to fetch data");
		}
	}
	
	public User approveAsUser(User user)throws CustomException
	{
		int id = user.getUser_id();
		
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.UPDATE_USER.approveAsUser()))
		{
			statement.setInt(1, id);
		
			statement.executeUpdate();
			
			return user;
			
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update data");
		}
	}
	
	public User approveAsAdmin(User user)throws CustomException
	{
       int id = user.getUser_id();
       
       String role="admin";
		
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.UPDATE_USER.approveAsAdmin()))
		{
			statement.setString(1, role);
			statement.setInt(2, id);
		
			statement.executeUpdate();
			
			return user;
			
		} 
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update data");
		}
	}
	
	public User getUser(int id)throws CustomException
	{
		
		User user = new User();
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.SELECT_ALL.getUser()))
		{
			statement.setInt(1, id);
			
			try (ResultSet result = statement.executeQuery()) 
			{
				while (result.next()) 
				{
					user.setName(result.getString("name"));
					user.setHuman_id(result.getString("human_id"));
					user.setMobile(result.getLong("mobile"));
					user.setRc_amount(result.getDouble("rc_amount"));
					String mail=mailObj.getMailById(id);
					user.setMail(mail);
				}
				
				return user;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to get user details");
		}
		
	}
	
	public void changePassword(String pass,int id)throws CustomException
	{
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.UPDATE_USER.changePassword()))
		{
			statement.setString(1, pass);
			statement.setInt(2, id);
			
			statement.executeUpdate();
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update password"); // No I18N
		}
		
	}
	
	public boolean updateName(String name,int id)throws CustomException
	{
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection()
				.prepareStatement(MysqlQuery.UPDATE_USER.updateName()))
		{
			statement.setString(1, name);
			statement.setInt(2, id);
			
			statement.executeUpdate();
			
			return true;
		}
		catch(CustomException e)
		{
			throw new CustomException(e.getMessage());
		}
		catch (Exception e) {
			throw new CustomException("Unable to update name"); // No I18N
		}
		
	}

}
