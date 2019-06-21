package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Whitelist;

import DBObjects.Person;
import DBObjects.PersonSalary;
import View.FieldBox;
import View.ScheduleBox;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Model<E> {

	public ObservableList<E> getObservableList(List<E> list) {
		return FXCollections.observableArrayList(list);
	}

	public boolean login(String login, String password) {
		Boolean isSuccessful = false;

		try {
			ResultSet result = connect().executeQuery(
					"Select * from Logowanie WHERE '" + login + "'=login AND password='" + password + "'");
			isSuccessful = (result.next()) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccessful;
	}

	public List<ScheduleBox> getScheduleBoxList() {
		List<ScheduleBox> list = new ArrayList<ScheduleBox>();

		try {
			ResultSet result = connect().executeQuery("SELECT * FROM Terminarz;");
			while (result.next())
				list.add(new ScheduleBox(result.getString(2), result.getString(3), result.getString(4),
						result.getString(5), result.getString(6)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<PersonSalary> getPeople() {
		List<PersonSalary> peopleList = new ArrayList<PersonSalary>();

		try {
			ResultSet result = connect()
					.executeQuery("SELECT * FROM Osoba INNER JOIN Zarobki ON Zarobki.Osoba_ID_Osoba=Osoba.ID_Osoba");

			while (result.next())
				peopleList.add(new PersonSalary(
						new Person(result.getString(2), result.getString(3), result.getString(4), result.getInt(5)),
						result.getInt(7), result.getString(8)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return peopleList;
	}

	public List<FieldBox> getFieldList() {
		List<FieldBox> fieldList = new ArrayList<FieldBox>();
		try {
			ResultSet result = connect().executeQuery("SELECT * FROM Boisko;");

			while (result.next()) {
				fieldList.add(new FieldBox(result.getInt(1), result.getString(2), result.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fieldList;
	}

	public Statement connect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/msEqnhBk17",
				"msEqnhBk17", "pjq3mx5525");
		Statement statement = connection.createStatement();
		return statement;
	}

	@SuppressWarnings("unchecked")
	public TableView<PersonSalary> getTableView(ObservableList<PersonSalary> obsList) {
		TableView<PersonSalary> tableView = new TableView<PersonSalary>();

		TableColumn<PersonSalary, String> nameCol = new TableColumn<PersonSalary, String>("Name:");
		nameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.24));

		TableColumn<PersonSalary, String> surNameCol = new TableColumn<PersonSalary, String>("Surname:");
		surNameCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

		TableColumn<PersonSalary, String> dateCol = new TableColumn<PersonSalary, String>("Birthday:");
		dateCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));

		TableColumn<PersonSalary, String> salaryCol = new TableColumn<PersonSalary, String>("Salary:");
		salaryCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.24));

		tableView.getColumns().addAll(nameCol, surNameCol, dateCol, salaryCol);

		tableView.setItems(obsList);

		nameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPerson().getName()));
		surNameCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPerson().getSurName()));
		dateCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPerson().getBrthDate()));
		salaryCol.setCellValueFactory(new PropertyValueFactory<PersonSalary, String>("salary"));

		return tableView;
	}

	public ObservableList<PersonSalary> searchValues(ObservableList<PersonSalary> obsList, TextField nameTF,
			TextField surNameTF, TextField birthdayTF) {
		obsList.clear();
		obsList.addAll((getPeople().stream()
				.filter(n -> (nameTF.getText().equals("") || nameTF.getText().equals("Name")) ? true
						: n.person.getName().equals(nameTF.getText()))
				.filter(n -> (surNameTF.getText().equals("") || surNameTF.getText().equals("Surname")) ? true
						: n.person.getSurName().equals(surNameTF.getText()))
				.filter(n -> (birthdayTF.getText().equals("") || birthdayTF.getText().equals("Birthday (YYYY-MM-DD)"))
						? true
						: n.person.getBrthDate().equals(birthdayTF.getText()))
				.collect(Collectors.toList())));
		return obsList;
	}

	public ListView<E> getListView(ObservableList<E> obsList) {
		return new ListView<E>(obsList);
	}

	public Map<String, String> getWeather(String city) {
		String key = "362e62d7e72f2cfb24119d6fdae3a819";
		String basicURL = "http://api.openweathermap.org/data/2.5/weather?q=";

		String url = basicURL + city + "&units=metric&appid=" + key;
		Map<String, String> weatherMap = new HashMap<String, String>();

		try {
			Document doc = Jsoup.connect(url).ignoreContentType(true).get();
			String data = Jsoup.clean(doc.html(), "", Whitelist.relaxed(), new OutputSettings().prettyPrint(false));

			JSONObject jsonObject = new JSONObject(data);
			String tmp = jsonObject.get("weather").toString();
			JSONObject jsonObject2 = new JSONObject(tmp.substring(1, tmp.length() - 1));

			weatherMap.put("temperature",
					new JSONObject(jsonObject.getJSONObject("main").toString()).get("temp").toString());
			weatherMap.put("description", jsonObject2.getString("description"));
			weatherMap.put("icon", jsonObject2.getString("icon"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return weatherMap;
	}
}