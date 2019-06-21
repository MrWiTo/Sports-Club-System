package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ScheduleBox extends AnchorPane {

	public Button bigButton;
	String date;
	String teamName;
	String place;
	String time;
	String fileName;

	public ScheduleBox(String date, String teamName, String place, String time, String fileName) {

		this.date = date;
		this.teamName = teamName;
		this.place = place;
		this.time = time;
		this.fileName = fileName;

		// Object properties
		setMaxSize(400, 120);

		// bigBox properties
		Pane bigBox = new Pane();

		bigBox.setPrefSize(350, 90);

		bigBox.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		bigBox.setStyle("-fx-background-color: #002992");

		// bigBox inside

		BorderPane bbInside = new BorderPane();

		// LEFT
		Label teamLabel = new Label(teamName);
		teamLabel.setStyle("-fx-text-fill: WHITE; -fx-font-size: 18px;");

		Label placeLabel = new Label(place);
		placeLabel.setStyle("-fx-text-fill: WHITE");
		VBox leftInside = new VBox();
		leftInside.setPrefWidth(225);
		leftInside.getChildren().addAll(teamLabel, placeLabel);
		leftInside.setSpacing(15);
		leftInside.setPadding((new Insets(15, 12, 15, 50)));
		bbInside.setLeft(leftInside);

		// RIGHT
		StackPane rightInside = new StackPane();
		ImageView logoImg = new ImageView(new Image("file:files/logo/" + fileName));
		logoImg.setFitHeight(100);
		logoImg.setFitWidth(100);
		rightInside.setAlignment(Pos.CENTER);
		rightInside.getChildren().add(logoImg);
		rightInside.setPadding((new Insets(-15, 10, 0, 10)));
		bbInside.setRight(rightInside);

		bigBox.getChildren().add(bbInside);

		// smallBox properties
		StackPane smallBox = new StackPane();

		smallBox.setBorder(new Border(
				new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		smallBox.setStyle("-fx-background-color: #3c3c3c");

		smallBox.setPrefSize(200, 25);

		Label dateLabel = new Label(date);
		dateLabel.setStyle("-fx-text-fill: WHITE");
		smallBox.setAlignment(Pos.CENTER);
		smallBox.getChildren().add(dateLabel);

		// coords of bigBox and smallBox
		bigBox.setLayoutX(10);
		bigBox.setLayoutY(20);
		smallBox.setLayoutX(20);
		smallBox.setLayoutY(-15);

		bigBox.getChildren().add(smallBox);
		getChildren().add(bigBox);
	}
}