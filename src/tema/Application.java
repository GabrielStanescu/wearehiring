package tema;

import java.util.ArrayList;
import java.util.List;

public final class Application {
	private static Application INSTANCE;
	public ArrayList<Company> companies = new ArrayList<Company>();
	ArrayList<User> users = new ArrayList<User>();
	
	private Application() {
	}
	
	public static Application getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Application();
		return INSTANCE;
	}
	
	public ArrayList<Company> getCompanies() {
		return companies;
	}

	// iterez prin lista de companii
	public Company getCompany(String name) {
		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).name.equals(name))
				return companies.get(i);
		}
		return null;
	}
	
	public void add(Company company) {
		companies.add(company);
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public boolean remove(Company company) {
		return companies.remove(company);
	}
	
	public boolean remove(User user) {
		return users.remove(user);
	}

	// am iterat prin lista de companii
	public ArrayList<Job> getJobs(ArrayList<String> companies) {
		int n = companies.size(), i;
		Company auxC;
		ArrayList<Job> jobs = new ArrayList<Job>();
		for (i = 0; i < n; i++) {
			auxC = getCompany(companies.get(i));
			// adaug in jobs toate joburile dintr-o companie
			jobs.addAll(auxC.getJobs());
		}
		return jobs;
	}
	
	public String toString() {
		String s = companies.toString();
		s += "\n" + "Utilizatori: " + users.size();
		return s;
	}
}
