package tema;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class User extends Consumer implements Observer{
	ArrayList<String> interested_companies = new ArrayList<String>();
	private ArrayList<Notification> notifications = new ArrayList<Notification>();
	
	public Employee convert() {
		Employee e = new Employee();
		// mut resumeul si cunostintele
		e.r = this.r;
		e.friends = this.friends;
		return e;
	}
	
	public Double getTotalScore() {
		int years;
		long days = 0;
		double mean = meanGPA(), score;
		LocalDate now = LocalDate.now();

		// calculez zilele de experienta
		for (Experience xp : r.getXp()) {
			if (xp.getEndDate() != null)
				days += ChronoUnit.DAYS.between(xp.getStartDate(), xp.getEndDate());
			else
				days += ChronoUnit.DAYS.between(xp.getStartDate(), now);
		}

		// daca experienta e mai mare decat o luna, se considera a fi un an
		if (days < 30)
			years = 0;
		else
			years = (int)(days / 365 + 1);
		
		score = years * 1.5 + mean;
		
		return score;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	
	@Override
	public void update(Notification notification) {
		notifications.add(notification);
	}
}
