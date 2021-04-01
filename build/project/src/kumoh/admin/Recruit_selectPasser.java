package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;

public class Recruit_selectPasser {
	
    @FXML private TextField textField;
    @FXML private Button run, cancel;
    
    @FXML
    private void initialize() {
    	
    	run.setOnAction(event -> {
    		try {
				App.network.enrollSelection(textField.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
    		((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
    	});
    	
    	cancel.setOnAction(event -> {
    		((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
    	});
    	
    }

}
