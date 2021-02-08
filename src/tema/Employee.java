package tema;

public class Employee extends Consumer {
	String company;
	double salary;
	
	public Employee() {
		super();
		company = "";
		salary = 0;
	}
	
	public Employee(String company, double salary) {
		super();
		this.company = company;
		this.salary = salary;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public String getCompany() {
		return company;
	}
	
	public double getSalary() {
		return salary;
	}
	
}
