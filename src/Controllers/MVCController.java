package Controllers;

import java.util.List;
import java.util.Map;

import DBObjects.PersonSalary;
import Main.AppMainView;
import Models.Model;
import View.FieldBox;
import View.ScheduleBox;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MVCController<E> {
	private Model<E> model;
	@SuppressWarnings("unused")
	private AppMainView view;
	
	public MVCController() {
		model = new Model<E>();
		view = new AppMainView();
	}
	
	public MVCController(Model<E> m, AppMainView v) {
		model = m;
		view = v;			
	}	
	
	public Boolean login(String login, String password) {
		return model.login(login, password);
	}
	
	public List<PersonSalary> getPeople(){
		return model.getPeople();
	}
	
	public TableView<PersonSalary> getTableView(ObservableList<PersonSalary> obsList) {
		return model.getTableView(obsList);
	}
	
	public ObservableList<E> getObservableList(List<E> list){
		return model.getObservableList(list);
	}
	
	public ObservableList<PersonSalary> searchValues(ObservableList<PersonSalary> obsList, TextField nameTF,TextField surNameTF,TextField birthdayTF) {
		return model.searchValues(obsList, nameTF, surNameTF, birthdayTF);
	}
	
	public List<ScheduleBox> getScheduleBoxList(){
		return model.getScheduleBoxList();
	}
	public ListView<E> getListView(List<E> list){
		return model.getListView(model.getObservableList(list));
	}
	
	public Map<String,String> getWeather(String city){
		return model.getWeather(city);
	}
	
	public List<FieldBox> getFieldList(){
		return model.getFieldList();
	}
}