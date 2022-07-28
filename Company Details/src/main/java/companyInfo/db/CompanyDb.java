package companyInfo.db;

import java.util.List;
import java.util.Map;

import customexception.CustomException;
import department.Department;
import employee.Employee;
import salary.Salary;
import validate.Validation;

public class CompanyDb {
	
	Validation validate=new Validation();
	DepartmentDb dept=new DepartmentDb();
	EmployeeDb employee=new EmployeeDb();
	SalaryDb salary=new SalaryDb();
	
	public Map<Integer,Department> showAllDept()throws CustomException
	{
		Map<Integer,Department> deptMap=dept.showAllDept();
		
		return deptMap;
	}
	
	public Map<Integer,Employee> showAllEmployee()throws CustomException
	{
		Map<Integer,Employee> employeeMap=employee.showAllEmployee();
		
		return employeeMap;
		
	}
	
	public Map<Integer,Salary> showAllSalary()throws CustomException
	{
		Map<Integer,Salary> salaryMap=salary.showAllSalary();
		
		return salaryMap;
	}
	
	public List<Employee> getEmployeeByName(String name)throws CustomException
	{
		validate.checkString(name);
		
		List<Employee> employeeDetails=employee.getEmployeeByName(name);
		
		validate.checkListEmployee(employeeDetails);
		
		return employeeDetails;
	}
	
	/*public List<Employee> getEmployeeByDeptId(int id)throws CustomException
	{
		validate.checkInteger(id);
		
		List<Employee> emp_list=employee.getEmployeeByDeptId(id);
		
		validate.checkListEmployee(emp_list);
		
		return emp_list;
	}
	
	public List<Salary> getSalaryById(int id)throws CustomException
	{
		validate.checkInteger(id);
		
		List<Salary> list=salary.getSalaryById(id);
		
		return list;
	}*/
	
	public Map<String,Map<String,List<Double>>> getDetailsByEmployee(String name)throws CustomException
	{
		validate.checkString(name);
		
		Map<String,Map<String,List<Double>>> detailsMap=dept.getDetailsByEmployee(name);
		
		validate.checkDeptDetails(detailsMap);
		
		return detailsMap;
	}
	
	public Map<String,Map<String,List<Double>>> getDetailsByDept(String name)throws CustomException
	{
		validate.checkString(name);
		
		Map<String,Map<String,List<Double>>> detailsMap=dept.getDetailsByDept(name);
		
		validate.checkDeptDetails(detailsMap);
		
		return detailsMap;
	}
	
	public List<Integer> getDeptIdByName(String name)throws CustomException
	{
		validate.checkString(name);
		
		List<Integer> list=dept.getDeptIdByName(name);
		
		validate.checkDept(list);
		
		return list;
	}
	
	public Department getDeptById(int id)throws CustomException
	{
		validate.checkInteger(id);
		
		Department department=dept.getDeptById(id);
		
		return department;
	}
	
	public double minimumAmount(int month)throws CustomException
	{
		validate.checkInteger(month);
		
		double amount=salary.minimumAmount(month);
		
		return amount;
	}
	
	public double maximumAmount(int month)throws CustomException
	{
		validate.checkInteger(month);
		
		double amount=salary.maximumAmount(month);
		
		return amount;
	}
	
	public double averageAmount(int month)throws CustomException
	{
		validate.checkInteger(month);
		
		double amount=salary.averageAmount(month);
		
		return amount;
	}
	
	public Map<String,Double> minSalaryByDeptName(String dept,int month)throws CustomException
	{
		validate.checkString(dept);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.minSalaryByDeptName(dept,month);
		
		System.out.println("Dept map : "+deptMap);
		
		validate.checkSalByDept(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Double> maxSalaryByDeptName(String dept,int month)throws CustomException
	{
		validate.checkString(dept);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.maxSalaryByDeptName(dept, month);
		
		validate.checkSalByDept(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Double> avgSalaryByDeptName(String dept,int month)throws CustomException
	{
		validate.checkString(dept);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.avgSalaryByDeptName(dept, month);
		
		validate.checkSalByDept(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Double> avgSalaryByUser(String emp_name,int month)throws CustomException
	{
		validate.checkString(emp_name);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.avgSalaryByUser(emp_name, month);
		
		validate.checkSalByUser(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Double> maxSalaryByUser(String emp_name,int month)throws CustomException
	{
		validate.checkString(emp_name);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.maxSalaryByUser(emp_name, month);
		
		validate.checkSalByUser(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Double> minSalaryByUser(String emp_name,int month)throws CustomException
	{
		validate.checkString(emp_name);
		validate.checkInteger(month);
		
		Map<String,Double> deptMap=salary.minSalaryByUser(emp_name, month);
		
		
		validate.checkSalByUser(deptMap);
		
		return deptMap;
	}
	
	public Map<String,Map<String,List<Double>>> getAllDeptAllEmployee()throws CustomException
	{
		Map<String,Map<String,List<Double>>> detailsMap=dept.getAllDeptAllEmployee();
		
		
		return detailsMap;
	}
	
	public void closeConnection()throws CustomException
	{
		ConnectDb.CONNECTION.closeConnection();
	}

}
