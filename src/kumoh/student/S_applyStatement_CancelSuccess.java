package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kumoh.app.App;

public class S_applyStatement_CancelSuccess {
	
	@FXML
	private Button button_close;
	
	@FXML
	private void initialize() {
		button_close.setOnAction(event ->{
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});
	}
}
