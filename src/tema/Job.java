package tema;

import java.util.ArrayList;

public class Job {
	// am adaugat string-ul department
	String jobName, companyName, department;
	boolean isOpen;
	Constraint graduation, experience, mean;
	ArrayList<User> candidates;
	int noPositions;
	double salary;
	
	public Job(String jobName, String companyName, double salary, int noPositions, Object minyrs, Object maxyrs, Object minxp, Object maxxp, Object ming, Object maxg) {
		this.jobName = jobName;
		this.companyName = companyName;
		this.salary = salary;
		isOpen = true;
		candidates = new ArrayList<User>();
		if (minyrs.toString().equals("null"))
			minyrs = 0;
		if (maxyrs.toString().equals("null"))
			maxyrs = 2021;
		if (minxp.toString().equals("null"))
			minxp = 0;
		if (maxxp.toString().equals("null"))
			maxxp = 100;
		if (ming.toString().equals("null"))
			ming = 0.0;
		if (maxg.toString().equals("null"))
			maxg = 10.0;
		graduation = new Constraint(Integer.parseInt(minyrs.toString()) , Integer.parseInt(maxyrs.toString()));
		experience = new Constraint(Integer.parseInt(minxp.toString()), Integer.parseInt(maxxp.toString()));
		mean = new Constraint(Double.parseDouble(ming.toString()), Double.parseDouble(maxg.toString()));
		this.noPositions = noPositions;
	}
	
	public void apply(User user) {
		Application app = Application.getInstance();
		Company company = app.getCompany(companyName);
		company.addObserver(user);

		// verific mai intai daca user-ul indeplineste cerintele
		if (meetsRequirments(user)) {
			// aleg recruiterul si apelez evaluate();
			Recruiter desiredRec = company.getRecruiter(user);
			desiredRec.evaluate(this, user);
		}
		else
			// altfel trimit o notificare userului
			user.update(new Notification(user.r.getInfo().getName() + " a fost respins/a la compania " + companyName + " pentru Job-ul " + jobName));
	}
	
	public boolean meetsRequirments(User user) {

		if (user.meanGPA() > mean.superior || user.meanGPA() < mean.inferior)
			return false;
		int years = (int) ((user.getTotalScore() - user.meanGPA()) / 1.5);
		if (years > experience.superior || years < experience.inferior)
			return false;
		return true;
	}
}
