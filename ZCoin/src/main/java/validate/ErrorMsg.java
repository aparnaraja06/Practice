package validate;

public enum ErrorMsg 
{
	  CONNECTION(401),
	  CLOSE_CONNECTION(402),
	  USERNAME(403),
	  PASSWORD(404),
	  MAIL(405),
	  ALPHABETS(406),
	  NUMBER(409),
	  AMOUNT(408),
	  WITHDRAW(410);
	  
	  private final int code;
	  
	  private ErrorMsg(int code)
	  {
		    this.code = code;
	  }

	  public int getCode() {
	     return code;
	  }
	  
	 
	
}
