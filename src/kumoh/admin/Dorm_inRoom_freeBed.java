package kumoh.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kumoh.app.App;

public class Dorm_inRoom_freeBed {
	
	@FXML private Button ok, close;
	@FXML private Label text;
    @FXML private ComboBox<String> select;
	
	@FXML public void initialize() {

		ObservableList<String> list = FXCollections.observableArrayList("ÇÐ±â", "1³â");
		select.setItems(list);
		
		ok.setOnAction(e -> {
			try {
				App.network.freeBeds(Dorm_inRoom_.targetName, select.getValue());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
		});
		close.setOnAction(e -> {
			Dorm_inRoom_.change = false;
			((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
		});
	}
}
	