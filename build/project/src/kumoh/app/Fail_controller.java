package kumoh.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Fail_controller implements Initializable{
	@FXML private Pane root;
	@FXML private ImageView close;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		close.setOnMouseClicked(e -> System.exit(0));
	}
	
}
