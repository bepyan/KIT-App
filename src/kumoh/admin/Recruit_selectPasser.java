package kumoh.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import kumoh.app.App;

public class Recruit_selectPasser {
	
    @FXML private ComboBox<String> comboBox;
    @FXML private Button run, cancel;
    
    @FXML
    private void initialize() {
    	
		ObservableList<String> list = FXCollections.observableArrayList("최초","1차","2차");
    	comboBox.setItems(list);
    	
    	run.setOnAction(event -> {
    		try {
				App.network.enrollSelection(comboBox.getValue());
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
