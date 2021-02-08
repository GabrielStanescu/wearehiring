package tema;

public class Recruiter extends Employee{
	double rating_recruiter = 5;

	// consider ca scorul ar trebui sa fie de tip double, nu int
	public double evaluate(Job job, User user) {
		double score = rating_recruiter * user.getTotalScore();
		Application app = Application.getInstance();
		Company company = app.getCompany(this.company);
		rating_recruiter += 0.1;
		// am creat un request nou si il adaug in lista managerului
		Request<Job, Consumer> req = new Request<Job, Consumer>(job, user, this, score);
		company.manager.requests.add(req);
		return score;
	}
}
