package kumoh.admin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kumoh.app.App;

public class Recruit_managePasser_acceptSeet {

	@FXML private ImageView image;
	@FXML private Button ok, back;

	@FXML
	private void initialize() {

		byte[] bytes = null;
		try {
			bytes = App.network.downloadFile((String) App.handle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream targetStream = new ByteArrayInputStream(bytes);
		Image seet = new Image(targetStream);
		image.setImage(seet);
		
		ok.setOnAction(event -> {
			
			try {
				App.network.acceptFile(new String[] {(String)App.handle});
			} catch (Exception e) {
				e.printStackTrace();
			}
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});

		back.setOnAction(event -> {
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
		});

	}
}
