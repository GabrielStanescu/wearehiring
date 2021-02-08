package tema;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Finance extends Department{

	@Override
	public double getTotalSalaryBudget() {
		
		double total = 0;
		ArrayList<Employee> e = getEmployees();
		for (Employee employee : e) {
			long days = 0;
			LocalDate now = LocalDate.now();
			// calculez experienta in numar de zile
			for (Experience exp : employee.r.getXp()) {
				if (exp.getEndDate() == null)
					days += ChronoUnit.DAYS.between(exp.getStartDate(), now);
				else
					days += ChronoUnit.DAYS.between(exp.getStartDate(), exp.getEndDate());
			}
			// daca experienta e mai mica decat un an, impozitul este 10%
			if (days < 365)
				total += employee.salary + employee.salary * 0.1;
			else
				total += employee.salary + employee.salary * 0.16;
		}
		return total;
	}
}
