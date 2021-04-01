package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class S_mainFail {
	
    @FXML
    private Button button_OK;

    @FXML
	private void initialize() {
    	button_OK.setOnAction(event -> {
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
    	});
    }
}
