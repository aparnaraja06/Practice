package validate;

public enum ErrorMsg 
{
	  CONNECTION(401, "Oops! Connection failed..unable to fetch data"),
	  CLOSE_CONNECTION(402,"Something went wrong! couldn't close Connection"),
	  USERNAME(403,"Username should not be empty!"),
	  PASSWORD(404,"Password should not be empty!"),
	  MAIL(405,"Mail id already exists");
	  
	  private final int code;
	  private final String message;
	  
	  private ErrorMsg(int code, String message)
	  {
		    this.code = code;
		    this.message = message;
	  }

	  public String getMessage() {
	     return message;
	  }

	  public int getCode() {
	     return code;
	  }
	  
	 
	
}
