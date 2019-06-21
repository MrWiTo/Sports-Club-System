package Controllers;

import Exceptions.switchScreenException;
import Main.AppMainView;
import View.FieldsPane;
import View.GameInformationPane;
import View.InformationPane;
import View.LoginPane;
import View.MenuPane;
import View.PeoplePane;
import View.ScheduleBox;
import View.SchedulePane;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ScreenController {
	AppMainView app;

	public ScreenController(AppMainView app) {
		this.app = app;
	}

	public void switchScreen(Pane pane, ScreenController controller, String btnName) {

		switch (pane.getClass().toString()) {
		case "class View.LoadingPane":
			Platform.runLater(() -> app.primaryStage.setScene(new Scene(new LoginPane(this),
					app.primaryStage.getScene().getWidth(), app.primaryStage.getScene().getHeight())));
			break;
		case "class View.LoginPane":
			switchAnimation(pane, new MenuPane(this));
			break;
		case "class View.MenuPane":
			if (btnName.equals("peopleBtn"))
				switchAnimation(pane, new PeoplePane(this));
			else if (btnName.equals("scheduleBtn"))
				switchAnimation(pane, new SchedulePane(this));
			else if (btnName.equals("logoutBtn"))
				switchAnimation(pane, new LoginPane(this));
			else if (btnName.equals("fieldsBtn"))
				switchAnimation(pane, new FieldsPane(this));
			else if (btnName.equals("infoBtn"))
				switchAnimation(pane, new InformationPane(this));
			break;
		case "class View.PeoplePane":
			if (btnName.equals("menuBtn"))
				switchAnimation(pane, new MenuPane(this));
			break;
		case "class View.SchedulePane":
			if (btnName.equals("menuBtn"))
				switchAnimation(pane, new MenuPane(this));
			break;
		case "class View.GameInformationPane":
			if (btnName.equals("backBtn"))
				switchAnimation(pane, new SchedulePane(this));
			break;
		case "class View.FieldsPane":
			if (btnName.equals("menuBtn"))
				switchAnimation(pane, new MenuPane(this));
			break;
		case "class View.InformationPane":
			if (btnName.equals("menuBtn"))
				switchAnimation(pane, new MenuPane(this));
			break;
		default:
			try {
				throw new switchScreenException(pane.getClass().toString());
			} catch (switchScreenException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void switchScreen(Pane pane, ScreenController controller, String btnName, ScheduleBox scheduleBox) {

		switch (pane.getClass().toString()) {
		case "class View.SchedulePane":
			if (btnName.equals("gameInfo"))
				switchAnimation(pane, new GameInformationPane(this, scheduleBox));
			break;
		default:
			try {
				throw new switchScreenException(pane.getClass().toString());
			} catch (switchScreenException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void switchAnimation(Pane oldPane, Pane newPane) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), oldPane);

		ft.setToValue(0.6);
		ft.setOnFinished((ActionEvent event) -> {
			Platform.runLater(() -> app.primaryStage.setScene(new Scene(newPane, app.primaryStage.getScene().getWidth(),
					app.primaryStage.getScene().getHeight())));
		});
		ft.play();
	}
}