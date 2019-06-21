package DBObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonSalary {

	public Person person;
	public SimpleIntegerProperty salary;
	public SimpleStringProperty since;

	public PersonSalary(Person person, int salary, String fromDate) {
		this.person = person;
		this.salary = new SimpleIntegerProperty(salary);
		this.since = new SimpleStringProperty(fromDate);
	}

	public Person getPerson() {
		return person;
	}

	public int getSalary() {
		return salary.get();
	}

	public String getSince() {
		return since.get();
	}
}