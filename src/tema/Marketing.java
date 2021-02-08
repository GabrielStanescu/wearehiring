package tema;

import java.util.ArrayList;

public class Marketing extends Department{

	@Override
	public double getTotalSalaryBudget() {
		double total = 0;
		for (Employee e : getEmployees())
			if (e.salary > 5000)
				total += e.salary + e.salary * 0.1;
			else
				if (e.salary < 3000)
					total += e.salary;
				else
					total += e.salary + e.salary * 0.16;
		return total;
	}
}
