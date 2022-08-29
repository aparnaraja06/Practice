package coinDb;

public enum PsqlQuery implements ChooseDb
{
	DOMAIN("CREATE DOMAIN check_integer varchar(12) not null check(value ~'[0-9]+');",
			"CREATE DOMAIN check_mail varchar(100) not null check(value ~'^[A-Za-z0-9+-._]+@(.+)$');",
			"CREATE SEQUENCE u_id start 100 increment 1 owned by customer.user_id;,",
			"CREATE SEQUENCE acc_num start 1000 increment 1 owned by account.account_num;"),
	
	CREATE("CREATE TABLE IF NOT EXISTS customer(user_id serial primary key,name varchar (50) not null, "
			+ "mobile check_integer, human_id check_integer, password varchar(8) not null, "
			+ "rc_amount numeric(5,2) not null, approved boolean default false,role varchar(10) "
			+ "default 'user',constraint h_id unique(human_id));",
			
	   "CREATE TABLE IF NOT EXISTS mail(user_id int not null, mail_id check_mail, "
	   + "constraint email unique(mail_id), "
	   + "constraint fk_user foreign key(user_id) references customer(user_id));",
	   
      "CREATE TABLE IF NOT EXISTS account(user_id int not null, account_num serial not null primary key,"
        + "rc_amount numeric(5,2) not null, zc_amount numeric(5,2), "
        + "constraint fk_user foreign key(user_id) references customer(user_id)),",
        
       "CREATE TABLE IF NOT EXISTS transaction(user_id int not null,from_account int not null,"
       + " to_account int not null, type varchar(20), amount numeric(5,2), date varchar(20), "
       + "constraint fk_user foreign key(user_id) references customer(user_id), "
       + "constraint fk_account foreign key(from_account) references account(account_num));"),
	
	INSERT("INSERT INTO customer(user_id,name,mobile,human_id,password,rc_amount)"
			+ "VALUES(nextval('u_id'),?,?,?,?,?);",
			"INSERT INTO mail(user_id,mail_id) VALUES(?,?);",
			"INSERT INTO account(user_id,account_num,rc_amount) VALUES(?,nextval('acc_num'),?)",
			"INSERT INTO transaction(user_id,from_account,to_account,type,amount,date) "
			+ "VALUES(?,?,?,?,?,?)"),
	
	SELECT_ALL("SELECT * FROM customer WHERE approved=false;",
			"SELECT * FROM customer WHERE user_id=100;",
			"SELECT * FROM account WHERE user_id=?",
			"SELECT * FROM transaction"),
	
	UPDATE_USER("UPDATE customer SET approved=true WHERE user_id=?",
			"UPDATE customer SET approved=true, role =? WHERE user_id=?",
			"UPDATE customer SET password=? WHERE user_id=?",
			"UPDATE customer SET name=? WHERE user_id=?"),
	
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
	
    SELECT_USER("SELECT role FROM customer WHERE user_id=?",
			"SELECT password FROM customer WHERE user_id=?");
	
	
	
	private final String query1;
	private final String query2;
	private final String query3;
	private final String query4;
	
	PsqlQuery(String query1, String query2) {
		
		  this.query1=query1;
			this.query2=query2;
			this.query3 = "";
			this.query4 = "";
	}
	
	 PsqlQuery(String query1, String query2, String query3, String query4) 
	  {
					
				this.query1=query1;
				this.query2=query2;
				this.query3=query3;
				this.query4=query4;
	  }

	@Override
	public String createUserTable() {
		
		return query1;
	}

	@Override
	public String getRole() {
		
		return query1;
	}

	@Override
	public String getPassword() 
	{
		
		return query2;
	}

	@Override
	public String addUser() {
		
		return query1;
	}

	@Override
	public String showWaitingList() {
		
		return query1;
	}

	@Override
	public String approveAsUser() {
		
        return query1;
	}

	@Override
	public String approveAsAdmin() {
		
		return query2;
	}

	@Override
	public String getUser() {
		
		return query2;
	}

	@Override
	public String changePassword() {
		
		return query3;
	}

	@Override
	public String updateName() {
		
		return query4;
	}

	@Override
	public String createMailTable() {
		
		return query2;
	}

	@Override
	public String getId() {
		

		return query1;
	}

	@Override
	public String addMail() {
		

		return query2;
	}

	@Override
	public String getMailById() {
		

		return query2;
	}

	@Override
	public String checkMailExists() {
		
		
		return query3;
	}

	@Override
	public String createAccountTable() {
		

		return query3;
		
	}

	@Override
	public String addAccount() {
		

		return query3;
	}

	@Override
	public String accountDetails() {
		
		return query3;

	}

	@Override
	public String getAccountNumById() {
		

		return query1;
		
	}

	@Override
	public String getRcBalance() {
		
		return query2;
		
	}

	@Override
	public String withdrawRc() {
		
		return query1;
	}

	@Override
	public String depositRc() {
		
		return query2;
		
	}

	@Override
	public String getZcBalance() {
		

		return query3;
		
	}

	@Override
	public String withdrawZc() {
		
		return query3;
	}

	@Override
	public String depositZc() {
		
		return query4;
		
	}

	@Override
	public String changeZcAmount() {
		

		return query4;
		
	}

	@Override
	public String createTransactionTable() {
		

		return query4;
		
	}

	@Override
	public String addTransaction() {
		
		
		return query4;
	}

	@Override
	public String getAllHistory() {
		

		return query4;
		
	}

	@Override
	public String getHistoryByUserId() {
		
		return query4;
	}

}
