package tema;

import java.util.ArrayList;

public class Management extends Department {

	@Override
	public double getTotalSalaryBudget() {
		double total = 0;
		// impozitul este 16%
		for (Employee e : getEmployees())
			total += e.salary + e.salary * 0.16;
		return total;
	}
}
