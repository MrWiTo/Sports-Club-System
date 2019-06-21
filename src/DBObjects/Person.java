package DBObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

	SimpleStringProperty name;
	SimpleStringProperty surName;
	SimpleStringProperty brthDate;
	SimpleIntegerProperty idTeam;

	public Person(String name, String surName, String brthDate, int idTeam) {
		this.name = new SimpleStringProperty(name);
		this.surName = new SimpleStringProperty(surName);
		this.brthDate = new SimpleStringProperty(brthDate);
		this.idTeam = new SimpleIntegerProperty(idTeam);
	}

	public String getName() {
		return name.get();
	}

	public String getSurName() {
		return surName.get();
	}

	public String getBrthDate() {
		return brthDate.get();
	}
}