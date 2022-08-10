package coinDb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import checker.Checker;
import connect.MysqlConnection;
import custom.CustomException;
import user.User;

public class UserDb 
{
	MailDb mailObj = new MailDb();


	public void createTable()throws CustomException
	{
		String query="CREATE TABLE IF NOT EXISTS user(user_id int auto_increment,name varchar(50) not null,"
		+ "mobile long, human_id varchar(12), password varchar(8) not null, rc_amount double not null,"
		+ "approved boolean default false,role varchar(10) default \"user\",unique(human_id),"
		+ " primary key (user_id))Engine=InnoDb auto_increment=100";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query)) {
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
		String query="SELECT role FROM user WHERE user_id=?";
		
		String role="";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
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
		String query="SELECT password FROM user WHERE user_id=?";
		
		String password="";
		
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
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
		
		String query="INSERT INTO user(name,mobile,human_id,password,rc_amount) VALUES(?,?,?,?,?)";
		
		int id=0;
		 
		try (PreparedStatement statement = MysqlConnection.CONNECTION.getConnection().prepareStatement(query,
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
					
					//System.out.println("Id : "+id);
				}
				
				return id;
			}
			
		}
		catch(CustomException e)
		{
			throw new CustomException(e);
		}
		catch (Exception e) {
			throw new CustomException("Unable to add user");
		}
		
	}
	
	public List<User> showWaitingList()throws CustomException
	{
		String query = "SELECT * FROM user WHERE approved=false";
		
		List<User> list = new ArrayList<>();
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection().prepareStatement(query))
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
		
		String query = "UPDATE user SET approved=true WHERE user_id=?";
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection().prepareStatement(query))
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
		
		String query = "UPDATE user SET approved=true, role =? WHERE user_id=?";
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection().prepareStatement(query))
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
		String query="SELECT * FROM user WHERE user_id=?";
		
		User user = new User();
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection().prepareStatement(query))
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
		String query="UPDATE user SET password=? WHERE user_id=?"; // No I18N
		
		try(PreparedStatement statement =MysqlConnection.CONNECTION.getConnection().prepareStatement(query))
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

}
