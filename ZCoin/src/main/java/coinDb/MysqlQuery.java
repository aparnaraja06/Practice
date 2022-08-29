package coinDb;


public enum MysqlQuery implements ChooseDb{
	
	CREATE("CREATE TABLE IF NOT EXISTS user(user_id int auto_increment,name varchar(50) not null,"
		+ "mobile long, human_id varchar(12), password varchar(8) not null, rc_amount double not null,"
		+ "approved boolean default false,role varchar(10) default \"user\",unique(human_id),"
		+ " primary key (user_id))Engine=InnoDb auto_increment=100",
		"CREATE TABLE IF NOT EXISTS mail(user_id int,"
				+ " mail_id varchar(100)not null,foreign key(user_id) references user(user_id))",
		"CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num int not null auto_increment,"
			+ " rc_amount double not null, zc_amount double, primary key(account_num), "
			+ "foreign key(user_id) references user(user_id))Engine=InnoDb auto_increment=1000",
		"CREATE TABLE IF NOT EXISTS transaction(user_id int not null, from_account int not null,"
			+ " to_account int not null, type varchar(10) not null, amount double , date varchar(20),"
			+ " foreign key(user_id) references user(user_id), "
			+ "foreign key(from_account) references account(account_num))"),
	
	INSERT("INSERT INTO user(name,mobile,human_id,password,rc_amount) VALUES(?,?,?,?,?)",
			"INSERT INTO mail(user_id,mail_id) VALUES(?,?)",
			"INSERT INTO account(user_id,rc_amount) VALUES(?,?)",
			"INSERT INTO transaction(user_id,from_account,to_account,type,amount,date) "
					+ "VALUES(?,?,?,?,?,?)"),
	
	SELECT_ALL("SELECT * FROM user WHERE approved=false",
			"SELECT * FROM user WHERE user_id=?",
			"SELECT * FROM account WHERE user_id=?",
			"SELECT * FROM transaction"),
	
	UPDATE_USER("UPDATE user SET approved=true WHERE user_id=?",
			"UPDATE user SET approved=true, role =? WHERE user_id=?",
			"UPDATE user SET password=? WHERE user_id=?",
			"UPDATE user SET name=? WHERE user_id=?"),
	
	TRANSACTION("UPDATE account SET rc_amount=? WHERE account_num=?",
			"UPDATE account SET rc_amount=? WHERE account_num=?",
			"UPDATE account SET zc_amount=? WHERE account_num=?",
			"UPDATE account SET zc_amount=? WHERE account_num=?"),
	
	SELECT_ACCOUNT("SELECT account_num FROM account WHERE user_id=?",
			"SELECT rc_amount FROM account WHERE account_num=?",
			"SELECT zc_amount FROM account WHERE account_num=?",
			"SELECT * FROM transaction WHERE user_id=?"),
	
	SELECT_MAIL("SELECT user_id FROM mail WHERE mail_id=?",
			"SELECT mail_id FROM mail WHERE user_id=?",
			"SELECT mail_id FROM mail WHERE mail_id=?",
			"UPDATE account SET zc_amount=zc_amount * ? WHERE zc_amount > 0"),
	
	SELECT_USER("SELECT role FROM user WHERE user_id=?",
			"SELECT password FROM user WHERE user_id=?");
	
	
	
	private final String query1;
	private final String query2;
	private final String query3;
	private final String query4;
	
	MysqlQuery(String query1, String query2) {
		
		  this.query1=query1;
			this.query2=query2;
			this.query3 = "";
			this.query4 = "";
	}
	
  MysqlQuery(String query1, String query2, String query3, String query4) 
  {
				
			this.query1=query1;
			this.query2=query2;
			this.query3=query3;
			this.query4=query4;
  }

  

public String  createUserTable()
  {
		
		return query1;
  }
  
    public String getRole()
	{
		
		return query1;
	}
    
    public String getPassword()
	{
    	
    	return query2;
	}
    
    public String addUser()
	{
		
		return query1;
	}
    
    public String showWaitingList()
	{
		
		return query1;
	}
    
    public String approveAsUser()
	{
    	
    	return query1;
	}
    
    public String approveAsAdmin()
	{
    	
    	return query2;
	}
    
    public String getUser()
	{
		
		return query2;
	}
    
    public String changePassword()
	{
	
		return query3;
	}
    
    public String updateName()
	{
    	
    	return query4;
	}
    
    public String createMailTable()
	{
		
		return query2;
	}
    
    public String getId()
	{
    	
    	return query1;
	}
    
    public String addMail()
	{
    	
    	return query2;
	}
    
    public String getMailById()
	{
    	
    	return query2;
	}
    
    public String checkMailExists()
	{
		
		return query3;
	}
    
    public String createAccountTable()
	{
		
		return query3;
	}
    
    public String addAccount()
	{
		
		return query3;
	}
    
    public String accountDetails()
	{
    	
    	return query3;
	}
    
    public String getAccountNumById()
	{
		
		return query1;
	}
    
    public String getRcBalance()
	{
    	
    	return query2;
	}
    
    public String withdrawRc()
	{
    	
    	return query1;
	}
    
    public String depositRc()
	{
    	
    	return query2;
	}
    
    public String getZcBalance()
    {
		
		return query3;
	}
    
    public String withdrawZc()
	{
    	
    	return query3;
	}
    
    public String depositZc()
	{
    	
    	return query4;
	}
    
    public String changeZcAmount()
	{
		
		return query4;
	}
    
    public String createTransactionTable()
	{

		return query4;
	}
    
    public String addTransaction()
	{
		
		return query4;
	}
    
    public String getAllHistory()
	{
		
		return query4;
	}
    
    public String getHistoryByUserId()
	{
		
		return query4;
	}
		

}
