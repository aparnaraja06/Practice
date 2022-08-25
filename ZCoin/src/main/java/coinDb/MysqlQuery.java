package coinDb;


public enum MysqlQuery {
	
	MYSQL;
	
  public String  createUserTable()
  {
	String query="CREATE TABLE IF NOT EXISTS user(user_id int auto_increment,name varchar(50) not null,"
		+ "mobile long, human_id varchar(12), password varchar(8) not null, rc_amount double not null,"
		+ "approved boolean default false,role varchar(10) default \"user\",unique(human_id),"
		+ " primary key (user_id))Engine=InnoDb auto_increment=100";
		
		return query;
	}
  
    public String getRole()
	{
		String query="SELECT role FROM user WHERE user_id=?";
		
		return query;
	}
    
    public String getPassword()
	{
    	String query="SELECT password FROM user WHERE user_id=?";
    	
    	return query;
	}
    
    public String addUser()
	{
		
		String query="INSERT INTO user(name,mobile,human_id,password,rc_amount) VALUES(?,?,?,?,?)";
		
		return query;
	}
    
    public String showWaitingList()
	{
		String query = "SELECT * FROM user WHERE approved=false";
		
		return query;
	}
    
    public String approveAsUser()
	{
    	String query = "UPDATE user SET approved=true WHERE user_id=?";
    	
    	return query;
	}
    
    public String approveAsAdmin()
	{
    	String query = "UPDATE user SET approved=true, role =? WHERE user_id=?";
    	
    	return query;
	}
    
    public String getUser()
	{
		String query="SELECT * FROM user WHERE user_id=?";
		
		return query;
	}
    
    public String changePassword()
	{
		String query="UPDATE user SET password=? WHERE user_id=?"; 
		
		return query;
	}
    
    public String updateName()
	{
    	String query = "UPDATE user SET name=? WHERE user_id=?";
    	
    	return query;
	}
    
    public String createMailTable()
	{
		String query="CREATE TABLE IF NOT EXISTS mail(user_id int,"
				+ " mail_id varchar(100)not null,foreign key(user_id) references user(user_id))";
		
		return query;
	}
    
    public String getId()
	{
    	String query="SELECT user_id FROM mail WHERE mail_id=?";
    	
    	return query;
	}
    
    public String addMail()
	{
    	String query="INSERT INTO mail(user_id,mail_id) VALUES(?,?)";
    	
    	return query;
	}
    
    public String getMailById()
	{
    	String query="SELECT mail_id FROM mail WHERE user_id=?";
    	
    	return query;
	}
    
    public String checkMailExists()
	{
		String query = "SELECT mail_id FROM mail WHERE mail_id=?";
		
		return query;
	}
    
    public String createAccountTable()
	{
		String query="CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num int not null auto_increment,"
				+ " rc_amount double not null, zc_amount double, primary key(account_num), "
				+ "foreign key(user_id) references user(user_id))Engine=InnoDb auto_increment=1000";
		
		return query;
	}
    
    public String addAccount()
	{
		String query = "INSERT INTO account(user_id,rc_amount) VALUES(?,?)";
		
		return query;
	}
    
    public String accountDetails()
	{
    	String query="SELECT * FROM account WHERE user_id=?";
    	
    	return query;
	}
    
    public String getAccountNumById()
	{
		String query = "SELECT account_num FROM account WHERE user_id=?";
		
		return query;
	}
    
    public String getRcBalance()
	{
    	String query="SELECT rc_amount FROM account WHERE account_num=?";
    	
    	return query;
	}
    
    public String withdrawRc()
	{
    	String query = "UPDATE account SET rc_amount=? WHERE account_num=?";
    	
    	return query;
	}
    
    public String depositRc()
	{
    	String query = "UPDATE account SET rc_amount=? WHERE account_num=?";
    	
    	return query;
	}
    
    public String getZcBalance()
	{
		String query = "SELECT zc_amount FROM account WHERE account_num=?";
		
		return query;
	}
    
    public String withdrawZc()
	{
    	String query = "UPDATE account SET zc_amount=? WHERE account_num=?";
    	
    	return query;
	}
    
    public String depositZc()
	{
    	String query = "UPDATE account SET zc_amount=? WHERE account_num=?";
    	
    	return query;
	}
    
    public String changeZcAmount()
	{
		String query = "UPDATE account SET zc_amount=zc_amount * ? WHERE zc_amount > 0";
		
		return query;
	}
    
    public String createTransactionTable()
	{
		String query="CREATE TABLE IF NOT EXISTS transaction(user_id int not null, from_account int not null,"
				+ " to_account int not null, type varchar(10) not null, amount double , date varchar(20),"
				+ " foreign key(user_id) references user(user_id), "
				+ "foreign key(from_account) references account(account_num))";
		
		return query;
	}
    
    public String addTransaction()
	{
		String query="INSERT INTO transaction(user_id,from_account,to_account,type,amount,date) "
				+ "VALUES(?,?,?,?,?,?)";
		
		return query;
	}
    
    public String getAllHistory()
	{
		String query = "SELECT * FROM transaction";
		
		return query;
	}
    
    public String getHistoryByUserId()
	{
		String query = "SELECT * FROM transaction WHERE user_id=?";
		
		return query;
	}
		

}
