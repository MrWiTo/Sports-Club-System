package View;

import Controllers.MVCController;
import Controllers.ScreenController;
import DBObjects.Person;
import DBObjects.PersonSalary;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PeoplePane extends BorderPane{
	MVCController<Person> controllerP = new MVCController<Person>();
	MVCController<PersonSalary> controllerPSalary = new MVCController<PersonSalary>();
	public PeoplePane(ScreenController screenController) {
		//Background
		Image background = new Image("files/bck.png");

		
		BackgroundSize backgroundSize = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		Background background1 = new Background(backgroundImage);
		this.setBackground(background1);		
		
		//TOP - TableView
		TableView<PersonSalary> tableView = controllerP.getTableView(controllerPSalary.getObservableList(controllerP.getPeople()));
		tableView.setMaxHeight(305);
		setTop(tableView);
		
		//Bottom
		
		VBox tFBox = new VBox();
		
		TextField nameTF = new TextField("Name");		
		TextField surNameTF = new TextField("Surname");
		TextField birthdayTF = new TextField("Birthday (YYYY-MM-DD)");
		
		HBox btnBox = new HBox();
		Button searchBtn = new Button("Search");
		searchBtn.setOnAction(e->{
			tableView.setItems(controllerP.searchValues(tableView.getItems(), nameTF, surNameTF, birthdayTF));		
		});
		
		Button showAllBtn = new Button("Show All");
		showAllBtn.setOnAction(e->{
			tableView.setItems(controllerPSalary.getObservableList(controllerP.getPeople()));						
		});
		
		Button menuBtn = new Button("Menu");
		menuBtn.setOnAction(e->{
			screenController.switchScreen(this, screenController, "menuBtn");
		});
		
		btnBox.getChildren().addAll(searchBtn, showAllBtn, menuBtn);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.setSpacing(30);
			
		tFBox.getChildren().addAll(nameTF, surNameTF, birthdayTF, btnBox);
		tFBox.setSpacing(15);
		tFBox.setPadding(new Insets(0, 300, 0, 300));
		
		tFBox.setAlignment(Pos.CENTER);
		
		setCenter(tFBox);
	}
}