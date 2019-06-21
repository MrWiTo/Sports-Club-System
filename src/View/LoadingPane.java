package View;

import Controllers.ScreenController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class LoadingPane extends BorderPane {
	public LoadingPane(ScreenController screenController) {
		LoadingPane thisPane = this;
		this.setCenter(new ImageView(new Image("file:files/infinity.gif")));
		this.setStyle("-fx-background-color: #001E64");

		Thread tr = new Thread() {
			public void run() {
				try {
					sleep(2000);
					screenController.switchScreen(thisPane, screenController, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};
		tr.start();
	}
}