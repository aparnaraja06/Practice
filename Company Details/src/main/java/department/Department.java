package department;

public class Department {
	
	private int dept_id;
	private String dept_name;
	private String product_name;
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	@Override
	public String toString() {
		return "dept_id : " + dept_id + "\ndept_name : " + dept_name + "\nproduct_name : " + product_name;
	}
	
	
	
	

}
