package View;

import Controllers.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MenuPane extends BorderPane {

	public MenuPane(ScreenController screenController) {

		// buttons
		VBox buttonVBox = new VBox();
		buttonVBox.setAlignment(Pos.CENTER);
		buttonVBox.setSpacing(20);

		Button scheduleBtn = new Button("Schedule");
		scheduleBtn.setOnAction(es -> {
			screenController.switchScreen(this, screenController, "scheduleBtn");
		});
		scheduleBtn.setPrefSize(150, 50);
		scheduleBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold; -fx-font-size: 20px;");
		buttonVBox.getChildren().add(scheduleBtn);

		Button peopleBtn = new Button("People");
		peopleBtn.setOnAction(es -> {
			screenController.switchScreen(this, screenController, "peopleBtn");
		});
		peopleBtn.setPrefSize(150, 50);
		peopleBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold; -fx-font-size: 20px;");
		buttonVBox.getChildren().add(peopleBtn);

		Button fieldsBtn = new Button("Fields");
		fieldsBtn.setOnAction(es -> {
			screenController.switchScreen(this, screenController, "fieldsBtn");
		});
		fieldsBtn.setPrefSize(150, 50);
		fieldsBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold; -fx-font-size: 20px;");
		buttonVBox.getChildren().add(fieldsBtn);

		Button infoBtn = new Button("Information");
		infoBtn.setOnAction(es -> {
			screenController.switchScreen(this, screenController, "infoBtn");
		});
		infoBtn.setPrefSize(150, 50);
		infoBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold; -fx-font-size: 20px;");
		buttonVBox.getChildren().add(infoBtn);

		Button logoutBtn = new Button("Log Out");
		logoutBtn.setPrefSize(150, 50);
		logoutBtn.setOnAction(e -> {
			screenController.switchScreen(this, screenController, "logoutBtn");
		});
		logoutBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold; -fx-font-size: 20px;");
		buttonVBox.getChildren().add(logoutBtn);

		// right background
		Image backgroundImg1 = new Image("files/menu1.png");
		BackgroundSize bck1 = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImg1, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bck1);
		buttonVBox.setBackground(new Background(backgroundImage1));

		buttonVBox.setPrefSize(300, 540);
		setRight(buttonVBox);
		setAlignment(buttonVBox, Pos.CENTER);

		// background image
		Pane backgroundPane = new Pane();
		Image backgroundImg = new Image("files/menub.jpg");
		BackgroundSize bck = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bck);
		backgroundPane.setBackground(new Background(backgroundImage));
		backgroundPane.setPrefSize(700, 540);
		setLeft(backgroundPane);
	}
}