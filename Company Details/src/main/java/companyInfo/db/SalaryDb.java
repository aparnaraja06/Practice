package companyInfo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customexception.CustomException;
import salary.Salary;

public class SalaryDb {
	
	public Map<Integer,Salary> showAllSalary()throws CustomException
	{
		String query="SELECT * FROM salary";
		
		Map<Integer,Salary> salaryMap=new HashMap<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					Salary salary=new Salary();
					
					int id=result.getInt("emp_id");
					salary.setEmp_id(id);
					
					salary.setYear(result.getInt("sal_year"));
					salary.setMonth(result.getInt("sal_month"));
					salary.setAmount(result.getDouble("amount"));
					
					salaryMap.put(id, salary);
				}
				
				return salaryMap;
			}
		}
		
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public List<Salary> getSalaryById(int id)throws CustomException
	{
		String query="SELECT * FROM salary where emp_id=?";
		
		 List<Salary> sal_list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, id);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					
					Salary salary=new Salary();
            
					int emp_id=result.getInt("emp_id");
					salary.setEmp_id(emp_id);
					
					salary.setYear(result.getInt("sal_year"));
					salary.setMonth(result.getInt("sal_month"));
					salary.setAmount(result.getDouble("amount"));
					
					sal_list.add(salary);
					
				}
				
				return sal_list;
			}
	     }
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public double minimumAmount(int month)throws CustomException
	{
		String query="SELECT MIN(amount) as amount FROM salary WHERE sal_month=?";
		
		double amount=0.0;
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			/*
			 * if(connect!=null) { System.out.println("connection found"); } else {
			 * System.out.println("No connection"); }
			 */
			statement.setInt(1, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					amount=result.getDouble("amount");
					
					//System.out.println("DB amount : "+amount);
				}
				return amount;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public double maximumAmount(int month)throws CustomException
	{
        String query="SELECT MAX(amount) as amount FROM salary WHERE sal_month=?";
		
		double amount=0.0;
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					amount=result.getDouble("amount");
				}
				return amount;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public double averageAmount(int month)throws CustomException
	{
        String query="SELECT AVG(amount) as amount FROM salary WHERE sal_month=?";
		
		double amount=0.0;
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					amount=result.getDouble("amount");
				}
				return amount;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Double> minSalaryByDeptName(String dept,int month)throws CustomException
	{
		String query="SELECT dept_name,Min(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
		         + "inner join department on employee.dept_id=department.dept_id) where dept_name=? and sal_month=?";
		
		Map<String,Double> deptMap=new HashMap<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, dept);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");

					double salary=result.getDouble("amount");
					
					if(name != null) {
					deptMap.put(name, salary);
					}
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
		
	}
	
	public Map<String,Double> maxSalaryByDeptName(String dept,int month)throws CustomException
	{
		String query="SELECT dept_name,Max(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
		         + "inner join department on employee.dept_id=department.dept_id) "
		         + "where dept_name=? and sal_month=?";
		
		Map<String,Double> deptMap=new HashMap<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, dept);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");
					double salary=result.getDouble("amount");
					
					if(name != null) {
					deptMap.put(name, salary);
					}
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
		
	}
	
	public Map<String,Double> avgSalaryByDeptName(String dept,int month)throws CustomException
	{
		String query="SELECT dept_name,AVG(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
		         + "inner join department on employee.dept_id=department.dept_id) "
		         + "where dept_name=? and sal_month=?";
		
		Map<String,Double> deptMap=new HashMap<>();
		
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, dept);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");
					double salary=result.getDouble("amount");
					
					if(name != null) {
					deptMap.put(name, salary);
					}
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
		
	}
	
	public Map<String,Double> minSalaryByUser(String emp_name,int month)throws CustomException
	{
		String query="select dept_name,Min(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
				+"inner join department on employee.dept_id=department.dept_id) "
				+ "where emp_name=? and sal_month=? group by dept_name";
		
             Map<String,Double> deptMap=new HashMap<>();
		
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, emp_name);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");
					double salary=result.getDouble("amount");
					
					
						deptMap.put(name, salary);
				
					
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Double> maxSalaryByUser(String emp_name,int month)throws CustomException
	{
		String query="select dept_name,Max(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
				+ "inner join department on employee.dept_id=department.dept_id)"
				+ " where emp_name=? and sal_month=? group by dept_name";
		
             Map<String,Double> deptMap=new HashMap<>();
		
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, emp_name);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");
					double salary=result.getDouble("amount");
					
					
					deptMap.put(name, salary);
	
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Double> avgSalaryByUser(String emp_name,int month)throws CustomException
	{
		String query="select dept_name,Avg(salary.amount) as amount from ((salary inner join employee on salary.emp_id=employee.emp_id)"
				+ "inner join department on employee.dept_id=department.dept_id)"
				+ " where emp_name=? and sal_month=? group by dept_name";
		
             Map<String,Double> deptMap=new HashMap<>();
		
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, emp_name);
			statement.setInt(2, month);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String name=result.getString("dept_name");
					double salary=result.getDouble("amount");
					
					
					deptMap.put(name, salary);
			
					
				}
				
				return deptMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}

}
