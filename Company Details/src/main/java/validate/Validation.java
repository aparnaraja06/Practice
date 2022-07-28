package validate;

import java.util.List;
import java.util.Map;

import customexception.CustomException;
import employee.Employee;

public class Validation {
	
	public void checkListEmployee(List<Employee> list)throws CustomException
	{
		if(list==null || list.isEmpty())
		{
			throw new CustomException("Employee details not available..please check the credentials");
		}
	}
	
	public void checkDept(List<Integer> list)throws CustomException
	{
		if(list==null || list.isEmpty())
		{
			throw new CustomException("Department not available...please check the credentials");
		}
	}
	
	public void checkSalByDept(Map<String,Double> salMap)throws CustomException
	{
		if(salMap.isEmpty())
		{
			throw new CustomException("No records found...please check the credentials");
		}
	}
	
	public void checkSalByUser(Map<String,Double> salMap)throws CustomException
	{
		
		if(salMap.isEmpty())
		{
			throw new CustomException("No records found...please check the credentials");
		}
	}
	
	public void checkDeptDetails(Map<String,Map<String,List<Double>>> detailsMap)throws CustomException
	{
		
		if(detailsMap.isEmpty())
		{
			throw new CustomException("No records found...please check the credentials");
		}
	}
	
	public void checkString(String name)throws CustomException
	{
		if(name==null || name.isEmpty())
		{
			throw new CustomException("Input should not be empty or null");
		}
	}
	
	public void checkInteger(int num)throws CustomException
	{
		if(num<0)
		{
			throw new CustomException("Input should not be negative");
		}
		else if(num==0)
		{
			throw new CustomException("Input should not be zero");
		}
	}
	
	public void checkMonth(int month)throws CustomException
	{
		if(month<=0 || month>12)
		{
			throw new CustomException("Please enter the month between 1-12");
		}
	}

}
