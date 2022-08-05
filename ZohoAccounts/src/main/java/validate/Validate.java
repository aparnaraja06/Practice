package validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Validate 
{

	Map<Integer,String> errorMap=new HashMap<>();
	
	int status=400;
	
	public Validate()
	{
		commitMsg();
	}
	
	public int generateStatus()
	{
		return ++status;
	}
	
	public void commitMsg()
	{
		String[] msg= {"Oops! Connection failed..unable to fetch data", // No I18N
				"Something went wrong! couldn't close ConnectionString", // No I18N
				"Username should not be empty!", // No I18N
				"Password should not be empty!"}; // No I18N
		
		for(int i=0;i<msg.length;i++)
		{
			int code = generateStatus();
			
			errorMap.put(code, msg[i]);
		}
	}
	
	public int addError(String msg)
	{
		int key=0;
	
		for(Entry<Integer, String> entry: errorMap.entrySet()) 
		{

		      if(entry.getValue().equals(msg)) 
		      {
		    	  key=entry.getKey();
		      }
		}
		
		return key;

	}
	
	
	public String getMsg(int code)
	{
		String msg=errorMap.get(code);
		
		return msg;
	}
}
