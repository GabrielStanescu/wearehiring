package tema;

import java.time.LocalDate;
import java.util.ArrayList;

public class Information {
	private String name, surname, email, phone, gender;
	private LocalDate date_of_birth;
	private ArrayList<String> languages, languages_level;
	
	public Information() {
		name = "";
		surname = "";
		email = "";
		phone = "";
		gender = "";
		date_of_birth = null;
		languages = new ArrayList<String>();
		languages_level = new ArrayList<String>();
	}
	
	public void setName(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.date_of_birth = birthDate;
	}
	
	public void addLang(String lang, String level) {
		languages.add(lang);
		languages_level.add(level);
	}
	
	public String getName() {
		String s = "";
		s = s + name + " " + surname;
		return s;
	}
	
	public LocalDate getBday() {
		return date_of_birth;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getLangs() {
		String s = "";
		int size = languages.size();
		for (int i = 0; i < size; i++) {
			s = s + languages.get(i) + ": " + languages_level.get(i) + "\n";
		}
		if (s == "")
			return "No languages were added!";
		return s;
	}
	
	public String toString() {
		String s = "";
		s += getName() + "\n";
		s += getEmail() + "\n";
		s += getPhone() + "\n";
		s += "Date of birth: " + getBday() + "\n";
		s += "Gender: " + getGender() + "\n";
		s += "Languages:\n" + getLangs();
		return s;
	}
}
