package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kumoh.app.App;

public class PopUp {
	
	@FXML private Button ok, close;
	@FXML private Label text;
	
	@FXML public void initialize() {
		
		//text.setText(Recruit_managePasser_.popText);
		ok.setOnAction(e -> {
			try {
				App.network.validSelection2();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
		});
		close.setOnAction(e -> {
			((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
		});
	}
}
