package runner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import companyInfo.db.CompanyDb;
import customexception.CustomException;
import department.Department;
import employee.Employee;
import salary.Salary;
import validate.Validation;

public class CompanyRunner {
	
	static Scanner scanner=new Scanner(System.in);
	static CompanyDb company=new CompanyDb();
	static Validation validate=new Validation();
	
	public void showAllDept()
	{
		try
		{
		Map<Integer,Department> deptMap=company.showAllDept();
		
		for(int id : deptMap.keySet())
		{
			Department dept=deptMap.get(id);
			
			System.out.println("DEPT ID  :  "+id);
			System.out.println(dept);
			System.out.println("-----------------------------------------------------------");
		}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public void showAllEmployee()
	{
		try
		{
			Map<Integer,Employee> employeeMap=company.showAllEmployee();
			
			for(int id: employeeMap.keySet())
			{
				Employee employee=employeeMap.get(id);
				
				System.out.println("EMPLOYEE ID : "+id);
				System.out.println(employee);
				System.out.println("-----------------------------------------------------------");
			}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void showAllSalary()
	{
		try
		{
			Map<Integer,Salary> salaryMap=company.showAllSalary();
			
			for(int id: salaryMap.keySet())
			{
				Salary salary = salaryMap.get(id);
				
				System.out.println("EMPLOYEE ID : "+id);
				System.out.println(salary);
				System.out.println("-----------------------------------------------------------");
			}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void getEmployeeByName()
	{
		System.out.println("Enter the name");
		String name=scanner.nextLine();
		
		try
		{
			List<Employee> list=company.getEmployeeByName(name);
			
			for(int i=0;i<list.size();i++)
			{
				Employee employee=list.get(i);
				
				System.out.println(employee);
				System.out.println("--------------------------------------------------");
			}
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void getEmployeeByDept()
	{
		boolean flag=true;
		
		while(flag)
		{

			
		System.out.println("Enter the choice");
		System.out.println("1. ALL DEPT ALL EMPLOYEE");
		System.out.println("2. INDIVIDUAL EMPLOYEE");
		System.out.println("3. ALL EMPLOYEE");
		System.out.println("4. EXIT");
		
		int choice=scanner.nextInt();
		scanner.nextLine();
		
		switch(choice)
		{
		case 1:
		{
			try
			{
				
				
				Map<String,Map<String,List<Double>>> detailsMap=company.getAllDeptAllEmployee();
				
				for(String dept_name: detailsMap.keySet())
				{
					System.out.println("-----------------------------------------");
					System.out.println("DEPARTMENT : "+dept_name);
					System.out.println("-----------------------------------------");
					
					Map<String,List<Double>> tempMap=detailsMap.get(dept_name);
					
					for(String emp_name:tempMap.keySet())
					{
						System.out.println("EMPLOYEE NAME : "+emp_name);
						
						List<Double> list=tempMap.get(emp_name);
						
						for(int i=0;i<list.size();i++)
						{
							double amount=list.get(i);
							
							System.out.println("AMOUNT : "+amount);
							 System.out.println();
						}
					}
				}
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			break;
		}
		
		case 2:
		{
			try
			{
				System.out.println("Enter the employee name");
				String name=scanner.nextLine();
				
			
				
             Map<String,Map<String,List<Double>>> detailsMap=company.getDetailsByDept(name);
				
				for(String dept_name: detailsMap.keySet())
				{
					System.out.println("-----------------------------------------");
					System.out.println("DEPARTMENT : "+dept_name);
					System.out.println("-----------------------------------------");
					
					Map<String,List<Double>> tempMap=detailsMap.get(dept_name);
					
					for(String emp_name:tempMap.keySet())
					{
						System.out.println("EMPLOYEE NAME : "+emp_name);
						
						List<Double> list=tempMap.get(emp_name);
						
						for(int i=0;i<list.size();i++)
						{
							double amount=list.get(i);
							
							System.out.println("AMOUNT : "+amount);
							 System.out.println();
						}
					}
				}
			}
			
				catch(CustomException e)
				{
					System.out.println(e.getMessage());
				}
				
				break;
			
		}
		case 3:
		{
			try
			{
				
			    System.out.println("Enter the department name");
				String name=scanner.nextLine();
				
				 Map<String,Map<String,List<Double>>> detailsMap=company.getDetailsByDept(name);
					
					for(String dept_name: detailsMap.keySet())
					{
						System.out.println("-----------------------------------------");
						System.out.println("DEPARTMENT : "+dept_name);
						System.out.println("-----------------------------------------");
						
						Map<String,List<Double>> tempMap=detailsMap.get(dept_name);
						
						for(String emp_name:tempMap.keySet())
						{
							System.out.println("EMPLOYEE NAME : "+emp_name);
							
							List<Double> list=tempMap.get(emp_name);
							
							for(int i=0;i<list.size();i++)
							{
								double amount=list.get(i);
								
								System.out.println("AMOUNT : "+amount);
								 System.out.println();
							}
						}
					}
				}
				
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			break;
		}
		
		case 4:
		{
			flag=false;
			break;
		}
		default:
		{
			System.out.println("Please Enter the number between 1-4");
			break;
		}
		}
		}
	}
	
	public void salDetails(int month)
	{
       boolean flag=true;
		
		while(flag)
		{
			
		System.out.println("Enter the choice");
		System.out.println("1. MIN");
		System.out.println("2. MAX");
		System.out.println("3. AVERAGE");
		System.out.println("4. EXIT");
		
		int choice=scanner.nextInt();
		scanner.nextLine();
		
		
		switch(choice)
		{
		case 1:
		{
			try
			{
			double amount=company.minimumAmount(month);
			
			System.out.println("MINIMUM AMOUNT : "+amount);
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 2:
		{
			try
			{
				double amount=company.maximumAmount(month);
				
				System.out.println("MAXIMUM AMOUNT : "+amount);
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 3:
		{
			try
			{
				double amount=company.averageAmount(month);
				
				System.out.println("AVERAGE : "+amount);
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 4:
		{
			flag=false;
			break;
		}
		default:
		{
			System.out.println("Please Enter the number between 1-4");
			break;
		}
		}
		}
	}
	
	public void dept_sal_details(String dept,int month)
	{
        boolean flag=true;
		
		while(flag)
		{
			
		System.out.println("Enter the choice");
		System.out.println("1. MIN");
		System.out.println("2. MAX");
		System.out.println("3. AVERAGE");
		System.out.println("4. EXIT");
		
		int choice=scanner.nextInt();
		scanner.nextLine();
		
		switch(choice)
		{
		case 1:
		{
			try
			{
				Map<String,Double> tempMap=company.minSalaryByDeptName(dept, month);
				
				for(String name : tempMap.keySet())
				{
					double amount=tempMap.get(name);
					
					System.out.println("DEPT NAME : "+name);
					System.out.println("MIN AMOUNT : "+amount);
					
				}
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 2:
		{
			try
			{
				Map<String,Double> tempMap=company.maxSalaryByDeptName(dept, month);
				
				for(String name : tempMap.keySet())
				{
					double amount=tempMap.get(name);
					
					System.out.println("DEPT NAME : "+name);
					System.out.println("MAX AMOUNT : "+amount);
					
				}
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 3:
		{
			try
			{
				Map<String,Double> tempMap=company.avgSalaryByDeptName(dept, month);
				
				for(String name : tempMap.keySet())
				{
					double amount=tempMap.get(name);
					
					System.out.println("DEPT NAME : "+name);
					System.out.println("AVG AMOUNT : "+amount);
					
				}
			}
			catch(CustomException e)
			{
				System.out.println(e.getMessage());
			}
			
			 break;
		}
		
		case 4:
		{
			flag=false;
			break;
		}
		default:
		{
			System.out.println("Please Enter the number between 1-4");
			break;
		}
		}
		}
	}
	
	public void user_sal_details(String emp_name,int month)
	{
		 boolean flag=true;
			
			while(flag)
			{
			System.out.println("Enter the choice");
			System.out.println("1. MIN");
			System.out.println("2. MAX");
			System.out.println("3. AVERAGE");
			System.out.println("4. EXIT");
		
			int choice=scanner.nextInt();
			scanner.nextLine();
			
			switch(choice)
			{
			case 1:
			{
				try
				{
					Map<String,Double> tempMap=company.minSalaryByUser(emp_name, month);
					
					for(String name : tempMap.keySet())
					{
						double amount=tempMap.get(name);
						
						System.out.println("EMPLOYEE NAME : "+emp_name);
						System.out.println("DEPT NAME : "+name);
						System.out.println("MIN AMOUNT : "+amount);
						
					}
				}
				catch(CustomException e)
				{
					System.out.println(e.getMessage());
				}
				
				 break;
			}
			
			case 2:
			{
				try
				{
					Map<String,Double> tempMap=company.maxSalaryByUser(emp_name, month);
					
					for(String name : tempMap.keySet())
					{
						double amount=tempMap.get(name);
						
						System.out.println("EMPLOYEE NAME : "+emp_name);
						System.out.println("DEPT NAME : "+name);
						System.out.println("MAX AMOUNT : "+amount);
						
					}
				}
				catch(CustomException e)
				{
					System.out.println(e.getMessage());
				}
				
				 break;
			}
			
			case 3:
			{
				try
				{
					Map<String,Double> tempMap=company.avgSalaryByUser(emp_name, month);
					
					for(String name : tempMap.keySet())
					{
						double amount=tempMap.get(name);
						
						System.out.println("EMPLOYEE NAME : "+emp_name);
						System.out.println("DEPT NAME : "+name);
						System.out.println("AVG AMOUNT : "+amount);
						
					}
				}
				catch(CustomException e)
				{
					System.out.println(e.getMessage());
				}
				
				 break;	
			}
			
			case 4:
			{
				flag=false;
				break;
			}
			default:
			{
				System.out.println("Please Enter the number between 1-4");
				break;
			}
			}
			}
	}
	
	public void salaryDetails()
	{
		int month=0;
	try
	{
	  System.out.println("Enter the month");
	  month=scanner.nextInt();
	  scanner.nextLine();
	  
	  validate.checkMonth(month);
	}
	catch(InputMismatchException e)
	{
		System.out.println("Input should be number");
		return;
	}
	catch(CustomException e)
	{
		System.out.println(e.getMessage());
		return;
	}
	  
	  
	  System.out.println("month : "+month);
	  boolean flag=true;
	  
	  while(flag)
	  {
		  
		System.out.println("Enter the option");  
	    System.out.println("1. GET SALARY BY DEPT NAME");
	    System.out.println("2. GET SALARY BY USER NAME");
	    System.out.println("3. TOTAL SALARY");
	    System.out.println("4. EXIT");
	    
	    int option=scanner.nextInt();
	    scanner.nextLine();
	    
	    
	    switch(option)
	    {
	    case 1:
	    {
	    	System.out.println("Enter Department name");
	    	String name=scanner.nextLine();
	    	
	    	dept_sal_details(name,month);
	    	break;
	    }
	    case 2:
	    {
	    	System.out.println("Enter employee name");
	    	String name=scanner.nextLine();
	    	
	    	user_sal_details(name,month);
	    	break;
	    }
	    case 3:
	    {
	    	salDetails(month);
	    	break;
	    }
	    
	    case 4:
	    {
	    	flag=false;
	    	break;
	    }
	    default:
		{
			System.out.println("Please Enter the number between 1-4");
			break;
		}
	    }
       
	}
		
	}
	
	
	public void closeConnection()
	{
		try
		{
			company.closeConnection();
		}
		catch(CustomException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public static void main(String[] args)
	{
		CompanyRunner runner=new CompanyRunner();
		
		boolean flag=true;
		
		while(flag)
		{
		System.out.println("Enter the option");
		System.out.println("1. SHOW ALL DEPARTMENTS");
		System.out.println("2. SHOW ALL EMPLOYEES");
		System.out.println("3. SHOW ALL SALARY");
		System.out.println("4. GET EMPLOYEE DETAILS BY NAME");
		System.out.println("5. GET DEPARTMENT DETAILS");
		System.out.println("6. SALARY DETAILS");
		System.out.println("7. EXIT");

		int option=scanner.nextInt();
		scanner.nextLine();
		
		switch(option)
		{
		case 1:
		{
			runner.showAllDept();
			break;
		}
		
		case 2:
		{
			runner.showAllEmployee();
			break;
		}
		
		case 3:
		{
			runner.showAllSalary();
			break;
		}
		
		case 4:
		{
			runner.getEmployeeByName();
			break;
		}
		
		case 5:
		{
			runner.getEmployeeByDept();
			break;
		}
		
		case 6:
		{
			runner.salaryDetails();
			break;
		}
		
		case 7:
		{
			runner.closeConnection();
			flag=false;
			System.out.println("Thank you! Application closed");
			break;
		}
		
		default:
		{
			System.out.println("Please Enter the number between 1-8");
			break;
		}
		}
		}
		
	}

}
