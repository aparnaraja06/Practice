package companyInfo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import customexception.CustomException;
import employee.Employee;

public class EmployeeDb {
	
	public Map<Integer,Employee> showAllEmployee()throws CustomException
	{
		String query="SELECT * FROM employee";
		
		Map<Integer,Employee> employeeMap=new HashMap<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					Employee employee=new Employee();
					
					int id=result.getInt("emp_id");
					employee.setEmp_id(id);
					
					int dept=result.getInt("dept_id");
					employee.setDept_id(dept);
					
					employee.setEmp_name(result.getString("emp_name"));
					employee.setDesignation(result.getString("designation"));
					
					employeeMap.put(id, employee);
				}
				
				return employeeMap;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public List<Employee> getEmployeeByName(String name)throws CustomException
	{
		String query="SELECT * FROM employee WHERE emp_name=?";
		
		List<Employee> emp_list=new ArrayList<>();
		
		try(PreparedStatement statement = ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setString(1, name);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
					Employee employee=new Employee();
					
					int id=result.getInt("emp_id");
					employee.setEmp_id(id);
					
					int dept=result.getInt("dept_id");
					employee.setDept_id(dept);
					
					employee.setDesignation(result.getString("designation"));
					employee.setEmp_name(name);
					
					emp_list.add(employee);
					
				}
				
				return emp_list;
				
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}
	
	public List<Employee> getEmployeeByDeptId(int id)throws CustomException
	{
		String query="SELECT * FROM employee where dept_id=?";
		
		List<Employee> emp_list=new ArrayList<>();
		
		try(PreparedStatement statement=ConnectDb.CONNECTION.getConnection().prepareStatement(query))
		{
			statement.setInt(1, id);
			
			try(ResultSet result=statement.executeQuery())
			{
				while(result.next())
				{
                   Employee employee=new Employee();
					
					int emp_id=result.getInt("emp_id");
					employee.setEmp_id(emp_id);
					
					int dept=result.getInt("dept_id");
					employee.setDept_id(dept);
					
					employee.setDesignation(result.getString("designation"));
					employee.setEmp_name(result.getString("emp_name"));
					
					emp_list.add(employee);
				}
				
				return emp_list;
			}
		}
		catch(Exception e)
		{
			throw new CustomException(e);
		}
	}

}
