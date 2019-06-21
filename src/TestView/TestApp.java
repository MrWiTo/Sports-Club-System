package TestView;


import Controllers.ScreenController;
import Main.AppMainView;
import View.FieldsPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApp extends AppMainView{
	public Stage primaryStage;
	public ScreenController screenController;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		primaryStage = new Stage();
		primaryStage.setTitle("Sports Club System");
		screenController = new ScreenController(this);
		primaryStage.setScene(new Scene(new FieldsPane(screenController), 960, 540));	
		primaryStage.setResizable(false);
		primaryStage.show();	
	}
}