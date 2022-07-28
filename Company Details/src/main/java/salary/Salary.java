package salary;

public class Salary {
	
	private int emp_id;
	private int year;
	private int month;
	private double amount;
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "emp_id : " + emp_id + "\nyear : " + year + "\nmonth : " + month + "\namount : " + amount;
	}
	
	

}
