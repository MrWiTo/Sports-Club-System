package View;

import Controllers.MVCController;
import Controllers.ScreenController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class FieldsPane extends BorderPane {
	private MVCController<FieldBox> controller = new MVCController<FieldBox>();

	public FieldsPane(ScreenController screenController) {

		// browser
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("http://www.google.pl");
		browser.setPrefSize(700, 540);
		setRight(browser);

		// left site
		BorderPane leftBorderPane = new BorderPane();
		leftBorderPane.setPrefSize(280, 540);

		// STADIUMS
		VBox imageVBox = new VBox();

		ObservableList<FieldBox> fieldObsList = controller.getObservableList(controller.getFieldList());
		ListView<FieldBox> fieldListV = controller.getListView(fieldObsList);

		fieldListV.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends FieldBox> ov, FieldBox old_val, FieldBox new_val) -> {
					FieldBox selectedItem = fieldListV.getSelectionModel().getSelectedItem();
					webEngine.load(selectedItem.website);
				});

		fieldListV.setPrefSize(280, 470);
		fieldListV.setStyle(
				"-fx-control-inner-background: rgba(0,0,0,0); -fx-background-color: rgba(0,0,0,0);  -fx-selection-bar:transparent;  -fx-selection-bar-non-focused: transparent;");
		imageVBox.getChildren().add(fieldListV);

		leftBorderPane.setCenter(imageVBox);

		// MENU BUTTON
		Button menuBtn = new Button("MENU");
		leftBorderPane.setBottom(menuBtn);
		setAlignment(menuBtn, Pos.CENTER);
		setMargin(menuBtn, new Insets(10, 0, 10, 0));

		menuBtn.setOnAction(e -> {
			screenController.switchScreen(this, screenController, "menuBtn");
		});

		setLeft(leftBorderPane);

		// left background

		Image background = new Image("files/menu1.png");
		BackgroundSize backgroundSize = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		Background background1 = new Background(backgroundImage);
		setBackground(background1);
	}
}