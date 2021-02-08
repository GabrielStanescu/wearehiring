package tema;

import java.util.ArrayList;

public class Company implements Subject {
	String name;
	Manager manager;
	ArrayList<Department> departments;
	ArrayList<Recruiter> recruiters;
	ArrayList<User> observers;
	
	public Company() {
		name = "";
		manager = new Manager();
		departments = new ArrayList<Department>();
		recruiters = new ArrayList<Recruiter>();
		observers = new ArrayList<User>();
	}
	
	public void add(Department department) {
		departments.add(department);
	}
	
	public void add(Recruiter recruiter) {
		recruiters.add(recruiter);
	}
	
	public void add(Employee employee, Department department) {
		department.add(employee);
	}
	
	public void remove(Employee employee) {
		for (int i = 0; i < departments.size(); i++)
			if (departments.get(i).getEmployees().contains(employee)) {
				departments.get(i).remove(employee);
				return;
			}
	}

	// folosesc metoda "remove(Employee)" din Department
	public void remove(Department department) {
		int n = department.getEmployees().size();
		for (int i = 0; i < n; i++) {
			department.remove(department.getEmployees().get(i));
		}
		departments.remove(department);
	}
	
	public void remove(Recruiter recruiter) {
		recruiters.remove(recruiter);
	}
	
	public void move(Department source, Department destination) {
		destination.employees.addAll(source.employees);
		source.employees.clear();
		destination.availableJobs.addAll(source.availableJobs);
		source.availableJobs.clear();
	}
	
	public void move(Employee employee, Department newDepartment) {
		remove(employee);
		newDepartment.add(employee);
	}
	
	public boolean contains(Department department) {
		if (departments.contains(department))
			return true;
		return false;
	}
	
	public boolean contains(Employee employee) {
		for (int i = 0; i < departments.size(); i++)
			if (departments.get(i).getEmployees().contains(employee))
				return true;
		return false;
	}
	
	public boolean contains(Recruiter recruiter) {
		if (recruiters.contains(recruiter))
			return true;
		return false;
	}
	
	public Recruiter getRecruiter(User user) {
		// max contine "distanta" maxima de la user la recruiter
		int max = 0, degree;
		ArrayList<Level> recruitersLevel = new ArrayList<Level>();

		// am aflat "distanta" de la user la toti recruiterii
		for (Recruiter r : recruiters) {
			degree = user.getDegreeInFriendship(r);
			if (max < degree) {
				max = degree;
				recruitersLevel.add(new Level(r, degree));
			}
		}

		// sterg toti recruiterii din lista care nu au distanta maxima
		for (int i = 0; i < recruitersLevel.size(); i++) {
			if (recruitersLevel.get(i).degree != max) {
				recruitersLevel.remove(i);
				i--;
			}
		}
		
		Recruiter desiredRec = null;
		// daca am ramas cu un recruiter, ii dau returnS
		if (recruitersLevel.size() == 1)
			return recruitersLevel.get(0).recruiter;
		// altfel, il aleg pe cel cu scor maxim
		else {
			double maxscore = 0;
			for (int i = 0; i < recruitersLevel.size(); i++) {
				if (recruitersLevel.get(i).recruiter.rating_recruiter > maxscore) {
					maxscore = recruitersLevel.get(i).recruiter.rating_recruiter;
					desiredRec = recruitersLevel.get(i).recruiter;
				}
			}
			return desiredRec;
		}
	}
	
	public ArrayList<Job> getJobs() {
		ArrayList<Job> jobs = new ArrayList<Job>();
		for (Department d : departments)
			jobs.addAll(d.getJobs());
		return jobs;
	}

	// clasa Level contine un recruiter si distanta acestuia catre user
	class Level {
		Recruiter recruiter;
		int degree;		
		
		public Level(Recruiter recruiter, int degree) {
			this.recruiter = recruiter;
			this.degree = degree;
		}
	}

	@Override
	public void addObserver(User user) {
		observers.add(user);
	}

	@Override
	public void removeObserver(User c) {
		observers.remove(c);
	}

	@Override
	public void notifyAllObservers() {
		for (User user : observers)
			user.update(new Notification("Toti userii au fost notificati de compania " + name));
	}
	
	public String toString() {
		String s = "";
		s += "Company name: " + name + "\n";
		s += "Manager name: " + manager.r.getInfo().getName() + "\n";
		s += "Number of departments: " + departments.size() + "\n";
		s += "Number of recruiters: " + recruiters.size() + "\n\n";
		return s;
	}
}
