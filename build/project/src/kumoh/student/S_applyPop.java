package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class S_applyPop {

    @FXML private Button button_exit;
    @FXML private Label failText;
    @FXML
	private void initialize() {
    	failText.setText(S_apply.popText);
    	button_exit.setOnAction(event->{
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
    	});
    }
}
