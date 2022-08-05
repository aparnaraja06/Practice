package connect;


import java.sql.Connection;
import java.sql.DriverManager;

import custom.CustomException;

public enum ConnectDb {

CONNECTION;
	
	private String url="jdbc:mysql://localhost:3306/accounts";
	private String username="root"; // No I18N
	private String password="Root@123";
	
	Connection connect=null;
	
	public Connection getConnection()throws CustomException
	{
		if(connect==null)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				connect=DriverManager.getConnection(url, username, password);
				
				
				return connect;
			}
			catch(Exception e)
			{
				throw new CustomException("Oops! Connection failed..unable to fetch data"); // No I18N
			}
		}
		else
		{
			return connect;
		}
	}
	
	public void closeConnection()throws CustomException
	{
		if(connect!=null)
		{
			try
			{
				connect.close();
			}
			catch(Exception e)
			{
				throw new CustomException("Something went wrong! couldn't close ConnectionString"); // No I18N
			}
		}
	}

}
