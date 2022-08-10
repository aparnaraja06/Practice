package custom;

public class CustomException extends Exception
{

	private static final long serialVersionUID = 1L;
	
	public CustomException(String message)
	{
		super(message);
	}
	public CustomException(Exception e)
	{
		super(e.getMessage());
	}
}