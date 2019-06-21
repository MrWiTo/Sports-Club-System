package View;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class FieldBox extends BorderPane {

	int idField;
	String name;
	String website;

	public FieldBox(int idField, String name, String website) {
		this.idField = idField;
		this.name = name;
		this.website = website;

		setPrefSize(250, 120);

		ImageView imageV = new ImageView(new Image("files/stadiums/" + name + ".jpg", 240, 120, false, false));
		setMargin(imageV, new Insets(10, 5, 10, 5));
		setCenter(imageV);
	}
}