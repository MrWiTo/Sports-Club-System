package Main;

import Controllers.ScreenController;
import View.LoadingPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMainView extends Application {
	public Stage primaryStage;
	public ScreenController screenController;

	@Override
	public void start(Stage arg0) throws Exception {
		primaryStage = new Stage();
		primaryStage.setTitle("Sports Club System");
		screenController = new ScreenController(this);
		primaryStage.setScene(new Scene(new LoadingPane(screenController), 960, 540));
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}