package companyInfo.db;

import java.sql.Connection;
import java.sql.DriverManager;

import customexception.CustomException;

public enum ConnectDb {
CONNECTION;
	
	private String url="jdbc:mysql://localhost:3306/company_details";
	private String username="inc8";
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
				throw new CustomException(e);
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
				throw new CustomException(e);
			}
		}
	}

}
