package tema;
import java.util.*;

public abstract class Department {
	ArrayList<Employee> employees;
	ArrayList<Job> availableJobs;
	
	public Department() {
		employees = new ArrayList<Employee>();
		availableJobs = new ArrayList<>();
	}
	
	public abstract double getTotalSalaryBudget();
	
	public ArrayList<Job> getJobs() {
		return availableJobs;
	}
	
	public void add(Employee employee) {
		employees.add(employee);
	}
	
	public void remove(Employee employee) {
		employees.remove(employee);
	}
	
	public void add(Job job) {
		Application app = Application.getInstance();
		
		availableJobs.add(job);

		for (Company company : app.getCompanies()) {
			if (company.contains(this)) {
				for (User user: app.users) {
					if (user.interested_companies.contains(company.name))
						user.update(new Notification(company.name + " a adaugat un job nou."));
				}
			}
		}
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
}
