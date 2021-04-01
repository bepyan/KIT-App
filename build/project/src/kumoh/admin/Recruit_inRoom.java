package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Recruit;

public class Recruit_inRoom {

	@FXML private Button run, cancel;

	@FXML
	private void initialize() {

		run.setOnAction(event -> {
			try {
				Recruit[] recruits = App.network.getRecruits();
				for (Recruit recruit : recruits)
					App.network.enrollBed(recruit.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});
		
		cancel.setOnAction(event -> {
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});
	}
}
