package tema;

import java.util.ArrayList;
import java.util.Collections;

public class Manager extends Employee {
	ArrayList<Request<Job, Consumer>> requests;
	
	public Manager() {
		requests = new ArrayList<Request<Job, Consumer>>();
	}
	
	public void process(Job job) {
		ArrayList<Request<Job, Consumer>> sorted = sort(requests);
		Application app = Application.getInstance();
		Company currentComp = app.getCompany(this.company);

		// iterez prin requesturile sortate
		for (int i = 0; i < sorted.size() && job.noPositions > 0; i++) {
			// daca requestul contine jobul cautat
			if (sorted.get(i).getKey() == job) {
				// daca requestul contine user-ul
				if (app.users.contains(sorted.get(i).getValue1())) {
					// sterg user-ul respectiv
					app.users.remove(sorted.get(i).getValue1());
					// iterez prin departamentele companiei si caut job-ul
					for (int j = 0; j < currentComp.departments.size(); j++)
						if (currentComp.departments.get(j).availableJobs.contains(job)) {
							// sterg oberverii din companii
							for (Company company : app.getCompanies())
								company.observers.remove(sorted.get(i).getValue1());
							// creez un nou employee necesar convertirii
							Employee nextEmployee = ((User) sorted.get(i).getValue1()).convert();
							nextEmployee.company = job.companyName;
							nextEmployee.salary = job.salary;
							//adaug nou employee in departament
							app.getCompany(this.company).add(nextEmployee, currentComp.departments.get(j));
							// sterg request-ul
							requests.remove(sorted.get(i));
							job.noPositions--;
							break;
						}
				}
			}
		}

		// inchid job-ul daca este necesar si notific observatorii
		if (job.noPositions == 0) {
			job.isOpen = false;
			for (User user : app.users)
				if (user.interested_companies.contains(job.companyName))
					user.update(new Notification("Job-ul " + job.jobName + " de la compania " + job.companyName + " a fost inchis."));
		}
	}
	
	public ArrayList<Request<Job, Consumer>> sort(ArrayList<Request<Job,Consumer>> requests) {
		int n = requests.size();
		// sortez prin interschimbare requesturile
		for (int i = 0 ; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (requests.get(i).getScore() > requests.get(j).getScore())
					Collections.swap(requests, i, j);
		return requests;
	}
}
