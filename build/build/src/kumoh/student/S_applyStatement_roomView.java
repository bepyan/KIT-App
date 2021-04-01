package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Selection;

public class S_applyStatement_roomView {
	
	@FXML private Button button_exit;

	@FXML private Label dormName, roomNum, roomBed;

	@FXML
	private void initialize() {

		Selection selection = null;
		try {
			selection = App.network.getSelection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		dormName.setText(selection.getSubRecruit());
		roomNum.setText(selection.getRoomNum().toString());
		roomBed.setText(selection.getRoomBed());

		button_exit.setOnAction(event -> {
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});
	}
}
