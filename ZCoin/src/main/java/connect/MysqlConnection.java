package connect;

import java.sql.Connection;
import java.sql.DriverManager;

import custom.CustomException;

public enum MysqlConnection 
{
 CONNECTION;
	
	private String url="jdbc:mysql://localhost:3306/zCoin";
	private String username="root";
	private String password="Root@123";
	
	Connection connectt=null;
	
	public Connection getConnection() throws CustomException {
		if(connectt==null)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				connectt=DriverManager.getConnection(url,username,password);
				
				return connectt;
			}
			catch(Exception e)
			{
				throw new CustomException("CONNECTION");
			}
		}
		else
		{
			return connectt;
		}
	}
	
	public void closeConnection()throws Exception
	{
		if(connectt!=null)
		{
			try
			{
				connectt.close();
			}
			catch(Exception e)
			{
				throw new Exception("CLOSE_CONNECTION");
			}
		}
	}

}
