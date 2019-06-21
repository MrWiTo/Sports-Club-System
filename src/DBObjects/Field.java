package DBObjects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Field {
	SimpleIntegerProperty idField;
	SimpleStringProperty name;
	SimpleStringProperty website;

	public Field(int idField, String name, String website) {
		this.idField = new SimpleIntegerProperty(idField);
		this.name = new SimpleStringProperty(name);
		this.website = new SimpleStringProperty(website);
	}

	public int getIdField() {
		return idField.get();
	}

	public String getName() {
		return name.get();
	}

	public String getWebsite() {
		return website.get();
	}
}