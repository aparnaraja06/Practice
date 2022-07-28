package employee;

public class Employee {
	
	private int emp_id;
	private int dept_id;
	private String emp_name;
	private String designation;
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "emp_id : " + emp_id + "\ndept_id : " + dept_id + "\nemp_name : " + emp_name + "\ndesignation : "
				+ designation;
	}
	
	

}
