package companyInfo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customexception.CustomException;
import department.Department;
import salary.Salary;

public class DepartmentDb 
{

	public Map<Integer,Department> showAllDept()throws CustomException
	{
		String query="SELECT * FROM department";
		
		Map<Integer,Department> deptMap=new HashMap<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					Department dept=new Department();
					
					int id=result.getInt("dept_id");
					dept.setDept_id(id);
					
					dept.setDept_name(result.getString("dept_name"));
					dept.setProduct_name(result.getString("product_name"));
					
					deptMap.put(id, dept);
					
				}
				
				return deptMap;
			}
			
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public List<Integer> getDeptIdByName(String name)throws CustomException
	{
		String query="SELECT dept_id FROM department WHERE dept_name=?";
		
		List<Integer> list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, name);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					int id=result.getInt("dept_id");
					
					list.add(id);
				}
				
				return list;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Department getDeptById(int id)throws CustomException
	{
		String query="SELECT * FROM department WHERE dept_id=?";
		
		Department dept=new Department();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, id);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					int dept_id=result.getInt("dept_id");
					dept.setDept_id(dept_id);
					
					dept.setDept_name(result.getString("dept_name"));
					dept.setProduct_name(result.getString("product_name"));

				}
				return dept;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
		
	}
	
	public Map<String,Map<String,List<Double>>> getAllDeptAllEmployee()throws CustomException
	{
		
		String query="select department.dept_name,employee.emp_name,salary.amount from(( department inner join employee on department.dept_id=employee.dept_id)"
				+ " inner join salary salary on employee.emp_id=salary.emp_id)";
		
		Map<String,Map<String,List<Double>>> detailsMap=new HashMap<>();
		
		Map<String,List<Double>> tempMap=new HashMap<>();
		
		List<Double> list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String dept_name=result.getString("dept_name");
					
					String emp_name=result.getString("emp_name");
					
					double amount=result.getDouble("amount");
					
					tempMap=detailsMap.get(dept_name);
					
					if(tempMap==null)
					{
						tempMap=new HashMap<>();
					}
					
					list=tempMap.get(emp_name);
					
					if(list==null)
					{
						list=new ArrayList<>();
					}
					
					list.add(amount);
					
					tempMap.put(emp_name, list);
					
					detailsMap.put(dept_name, tempMap);
				}
				
				return detailsMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Map<String,List<Double>>> getDetailsByEmployee(String name)throws CustomException
	{
		String query="select department.dept_name,employee.emp_name,salary.amount from(( department inner join employee on department.dept_id=employee.dept_id)"
				+ " inner join salary salary on employee.emp_id=salary.emp_id) where emp_name=?";
		
		Map<String,Map<String,List<Double>>> detailsMap=new HashMap<>();
		
		Map<String,List<Double>> tempMap=new HashMap<>();
		
		List<Double> list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, name);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String dept_name=result.getString("dept_name");
					
					String emp_name=result.getString("emp_name");
					
					double amount=result.getDouble("amount");
					
					tempMap=detailsMap.get(dept_name);
					
					if(tempMap==null)
					{
						tempMap=new HashMap<>();
					}
					
					list=tempMap.get(emp_name);
					
					if(list==null)
					{
						list=new ArrayList<>();
					}
					
					list.add(amount);
					
					tempMap.put(emp_name, list);
					
					detailsMap.put(dept_name, tempMap);
				}
				
				return detailsMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public Map<String,Map<String,List<Double>>> getDetailsByDept(String name)throws CustomException
	{
		String query="select department.dept_name,employee.emp_name,salary.amount from(( department inner join employee on department.dept_id=employee.dept_id)"
				+ " inner join salary salary on employee.emp_id=salary.emp_id) where dept_name=?";
		
		Map<String,Map<String,List<Double>>> detailsMap=new HashMap<>();
		
		Map<String,List<Double>> tempMap=new HashMap<>();
		
		List<Double> list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, name);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					String dept_name=result.getString("dept_name");
					
					String emp_name=result.getString("emp_name");
					
					double amount=result.getDouble("amount");
					
					tempMap=detailsMap.get(dept_name);
					
					if(tempMap==null)
					{
						tempMap=new HashMap<>();
					}
					
					list=tempMap.get(emp_name);
					
					if(list==null)
					{
						list=new ArrayList<>();
					}
					
					list.add(amount);
					
					tempMap.put(emp_name, list);
					
					detailsMap.put(dept_name, tempMap);
				}
				
				return detailsMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
}
