package View;

import Controllers.ScreenController;
import javafx.geometry.Insets;
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

public class InformationPane extends BorderPane {
	public InformationPane(ScreenController screenController) {
		
		//background
		Image background = new Image("file:files/bck.png");

		setCenter(new ImageView(background));
		BackgroundSize backgroundSize = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		Background background1 = new Background(backgroundImage);
		setBackground(background1);
		
		//center
		
		Label informations = new Label("Application was created mostly to show basic programming skills. "
				+ "The programme is based on MVC architecture pattern (Model-View-Controller). "
				+ "It's also extended about controller to change view (ScreenController). "
				+ "As a result creating new functions and making a changes is so much easier. "
				+ "The application needs the internet connection it is mainly used to work with database "
				+ "(www.remotemysql.com) but it is also required to get current weather from API Weather. "
				+ "To create this application i have been used generic methods, decorator pattern and new Exceptions.");
		informations.setStyle("fx-background-color: transparent; -fx-font-size: 20; -fx-text-fill: WHITE;");
		informations.setPrefWidth(500);
		informations.setWrapText(true);
		setCenter(informations);
		
			
		//button
		Button menuBtn = new Button("MENU");
		menuBtn.setOnAction(e -> {
			screenController.switchScreen(this, screenController, "menuBtn");
		});
		setBottom(menuBtn);
		setAlignment(menuBtn, Pos.CENTER);
		setMargin(menuBtn, new Insets(10,10,10,10));
	}
}