package tema;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Education implements Comparable {
	private LocalDate start_date, end_date;
	private String name, level;
	double grade;
	
	public Education(String start_date, String end_date, String name, String level, double grade) throws InvalidDatesException {
		this.start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		if (end_date != null) {
			this.end_date = LocalDate.parse(end_date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			// daca datele introduse sunt invalide, arunc o exceptie
			if (this.start_date.isAfter(this.end_date))
				throw new InvalidDatesException("Date introduse invalide!");
		}
		else
			this.end_date = null;
		this.name = name;
		this.level = level;
		this.grade = grade;
	}
	
	public LocalDate getStartDate() {
		return start_date;
	}
	
	public LocalDate getEndDate() {
		return end_date;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLevel() {
		return level;
	}
	
	@Override
	public int compareTo(Object arg0) {
		Education ed = (Education) arg0;
		
		if (end_date == null || ed.end_date == null) { // daca studiile sunt inca in desfasurare
			if (start_date.isAfter(ed.start_date)) // compar dupa data de inceput
				return 1;
			else
				return -1;
		}
		
		if (end_date == ed.end_date) { //daca data de absolvire este egala
			return (int)(ed.grade - grade); // compar dupa medie
		}
		else {
			if (end_date.isAfter(ed.end_date)) // compar dupa data de absolvire
				return -1;
			else
				return 1;
		}
	}
	
	public String toString() {
		String s = "";
		s += "Level: " + getLevel() + "\n"; 
		s += "Name: " + getName() + "\n";
		s += "Start date: " + getStartDate() + "\n";
		s += "End date: " + getEndDate() + "\n";
		s += "Grade: " + getGrade() + "\n";
		return s;
	}
	
}
