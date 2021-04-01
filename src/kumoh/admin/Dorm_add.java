package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Bed;
import kumoh.core.model.SubRecruit;

public class Dorm_add {

	@FXML private TextField name;
	@FXML private Button add, cancel;

	@FXML
	private void initialize() {
		add.setOnAction(event -> {
			SubRecruit subRecruit = new SubRecruit();
			subRecruit.setName(name.getText());
			subRecruit.setValid("Y");
			subRecruit.setBeds(new Bed[0]);
			try {
				App.network.createSubRecruit(subRecruit);
			} catch (Exception e) {
				System.err.println("createSubRecruit ½ÇÆÐ");
			}
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
		
		
		cancel.setOnAction(e -> {
			((Stage)((Node)(e.getSource())).getScene().getWindow()).close();
		});
	}
}
