package View;

import java.util.Map;

import Controllers.MVCController;
import Controllers.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameInformationPane extends BorderPane {
	private MVCController<Map<String, String>> controller = new MVCController<Map<String, String>>();

	public GameInformationPane(ScreenController screenController, ScheduleBox scheduleBox) {
		Map<String, String> weatherMap = controller.getWeather(scheduleBox.place);

		// Background
		Image background = new Image("file:files/back3.png");

		BackgroundSize backgroundSize = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		Background background1 = new Background(backgroundImage);
		this.setBackground(background1);

		// Image and temperature
		HBox temp = new HBox();

		ImageView weatherIcon = new ImageView(new Image("file:files/weather/" + weatherMap.get("icon") + ".png"));
		Label temperatureLb = new Label(weatherMap.get("temperature") + "°C");
		temperatureLb.setStyle("-fx-font-size: 40; -fx-text-fill: WHITE");
		temp.getChildren().addAll(weatherIcon, temperatureLb);
		temp.setAlignment(Pos.CENTER);

		// LEFT - INFORMATIONS
		VBox leftBox = new VBox();
		leftBox.getChildren().add(temp);

		Label weatherLb = new Label(weatherMap.get("description"));
		weatherLb.setStyle("-fx-font-size: 40; -fx-text-fill: WHITE");
		leftBox.getChildren().add(weatherLb);

		Label timeLb = new Label(scheduleBox.date + " " + scheduleBox.time);
		timeLb.setStyle("-fx-font-size: 40; -fx-text-fill: WHITE");
		leftBox.getChildren().add(timeLb);

		Label placeLb = new Label(scheduleBox.place);
		placeLb.setStyle("-fx-font-size: 40; -fx-text-fill: WHITE");
		leftBox.getChildren().add(placeLb);

		leftBox.setPrefSize(480, 540);
		leftBox.setAlignment(Pos.CENTER);

		setLeft(leftBox);

		// RIGHT - TEAM IMAGE

		StackPane rightPane = new StackPane();
		rightPane.setPrefSize(480, 540);
		ImageView teamImg = new ImageView(new Image("File:files/lineup/" + scheduleBox.teamName + ".jpg"));
		teamImg.setFitHeight(450);
		teamImg.setFitWidth(350);
		rightPane.getChildren().add(teamImg);
		rightPane.setAlignment(Pos.CENTER);

		setRight(rightPane);

		// BACK BUTTON
		Button backBtn = new Button("BACK");
		backBtn.setOnAction(e -> {
			screenController.switchScreen(this, screenController, "backBtn");
		});

		setBottom(backBtn);
	}
}