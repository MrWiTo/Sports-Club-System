package View;

import Controllers.MVCController;
import Controllers.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoginPane extends BorderPane {
	protected TextField loginTF;
	protected PasswordField passwordF;

	private final MVCController<Boolean> controller = new MVCController<Boolean>();

	public LoginPane(ScreenController screenController) {

		// BACKGROUND

		Image background = new Image("files/bck.png");

		setCenter(new ImageView(background));
		BackgroundSize backgroundSize = new BackgroundSize(960, 540, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

		Background background1 = new Background(backgroundImage);
		this.setBackground(background1);

		// CENTER

		loginTF = new TextField();
		loginTF.setText("admin");
		loginTF.setMaxWidth(250);

		passwordF = new PasswordField();
		passwordF.setText("admin");
		passwordF.setMaxWidth(250);

		VBox centerPane = new VBox();
		centerPane.setSpacing(5);

		centerPane.setAlignment(Pos.CENTER);
		centerPane.getChildren().add(loginTF);
		centerPane.getChildren().add(passwordF);
		centerPane.setMaxSize(350, 200);

		centerPane.setStyle("-fx-background-color: #7986CB");

		centerPane.setOpacity(0.7);
		Button loginBtn = new Button("LOG IN");
		loginBtn.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold;");
		loginBtn.setOnAction(es -> {

			if (controller.login(loginTF.getText(), passwordF.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS!");
				alert.setHeaderText(null);
				alert.setContentText("Login successful!");
				alert.showAndWait();
				screenController.switchScreen(this, screenController, "loginBtn");

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Access Denied");
				alert.setHeaderText(null);
				alert.setContentText("Wrong login or password!");
				alert.showAndWait();
			}
		});

		Pane spacer = new Pane();
		spacer.setPrefSize(92, 1);
		centerPane.getChildren().add(spacer);
		centerPane.getChildren().add(loginBtn);

		setCenter(centerPane);

	}
}