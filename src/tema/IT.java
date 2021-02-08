package tema;

import java.util.ArrayList;

public class IT extends Department {

	@Override
	public double getTotalSalaryBudget() {
		double total = 0;
		// nu exista impozit
		for (Employee e : getEmployees())
			total += e.salary;
		return total;
	}

}
