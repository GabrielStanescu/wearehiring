package tema;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Experience implements Comparable {
	private LocalDate start_date, end_date;
	private String position, company;
	
	public LocalDate getStartDate() {
		return start_date;
	}
	
	public LocalDate getEndDate() {
		return end_date;
	}
	
	public String getPos() {
		return position;
	}
	
	public String getCompName() {
		return company;
	}
	
	public Experience(String start_date, String end_date, String position, String company) throws InvalidDatesException {
		this.start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		if (end_date != null) {
			this.end_date = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			// daca datele sunt invalide, arunc o exceptie
			if (this.start_date.isAfter(this.end_date))
				throw new InvalidDatesException("Date introduse invalide!");
		}
		else
			this.end_date = null;
		this.position = position;
		this.company = company;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Experience xp = (Experience) arg0;
		
		if (end_date == null || xp.end_date == null)
			if (start_date.isAfter(xp.start_date)) //compar dupa data de inceput
				return 1;
			else
				return -1;
		
		if (end_date == xp.end_date) // daca datele de sfarsit sunt egale
			if (start_date.isAfter(xp.start_date)) // compar dupa data de inceput
				return -1;
			else 
				return 0;
		else
			return company.compareTo(xp.company);
	}
	
	public String toString() {
		String s = "";
		s += "Company: " + getCompName() + "\n";
		s += "Position: " + getPos() + "\n";
		s += "Start date: " + getStartDate() + "\n";
		s += "End date: ";
		if (getEndDate() == null)
			s += "Present\n";
		else
			s += getEndDate() + "\n";
		return s;
	}
	
}
