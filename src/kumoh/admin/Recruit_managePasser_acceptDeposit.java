package kumoh.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;

public class Recruit_managePasser_acceptDeposit {

	@FXML private TextField textField;
	@FXML private Button ok, back;

	@FXML
	private void initialize() {

		textField.setOnAction(e -> alter(e));
		ok.setOnAction(e -> alter(e));
		back.setOnAction(event ->((Stage) ((Node) (event.getSource())).getScene().getWindow()).close());

	}
	private void alter(ActionEvent e) {	
		String[] strings = new String[2];
		strings[0] = (String)App.handle;
		strings[1] = textField.getText();
		try {
			App.network.accpetInvoice(strings);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
	}
}
