package View;

import Controllers.MVCController;
import Controllers.ScreenController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SchedulePane extends BorderPane {
	MVCController<ScheduleBox> controller = new MVCController<ScheduleBox>();

	public SchedulePane(ScreenController screenController) {

		// RIGHT - SCHEDULE LIST
		VBox rightVBox = new VBox();
		rightVBox.setAlignment(Pos.CENTER);
		ObservableList<ScheduleBox> obsList = controller.getObservableList(controller.getScheduleBoxList());

		ListView<ScheduleBox> teamsList = controller.getListView(obsList);

		teamsList.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends ScheduleBox> ov, ScheduleBox old_val, ScheduleBox new_val) -> {
					ScheduleBox selectedItem = teamsList.getSelectionModel().getSelectedItem();
					screenController.switchScreen(this, screenController, "gameInfo", selectedItem);
				});

		teamsList.setPrefSize(450, 465);
		teamsList.setStyle(
				"-fx-control-inner-background: rgba(0,0,0,0); -fx-background-color: rgba(0,0,0,0);  -fx-selection-bar:transparent;  -fx-selection-bar-non-focused: transparent;");

		rightVBox.getChildren().add(teamsList);
		setMargin(rightVBox, new Insets(0, 10, 0, 10));
		setRight(rightVBox);

		// BOTTOM BUTTON
		Button menuBtn = new Button("GO TO MENU");

		menuBtn.setOnAction(e -> {
			screenController.switchScreen(this, screenController, "menuBtn");
		});

		setBottom(menuBtn);

		// TOP - TITLE LABEL
		Label title = new Label("2019 FOOTBALL SCHEDULE");
		title.setStyle(
				"-fx-font-size: 40px; -fx-effect: dropshadow(one-pass-box, black, 8, 0.0, 2, 0); -fx-text-fill: white;");
		setAlignment(title, Pos.CENTER);
		setTop(title);

		// Background

		Image backgroundImg = new Image("files/test6.png");
		BackgroundSize bck = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bck);
		setBackground(new Background(backgroundImage));
	}
}