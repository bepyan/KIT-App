package kumoh.admin;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import kumoh.app.*;

public class Main implements Initializable{

    @FXML private Pane root;

    @FXML private Label date, name, recruit, dorm, logout;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		name.setText("관리자 님 환영합니다");
		SimpleDateFormat today = new SimpleDateFormat ( "yyyy.MM.dd");
		date.setText(today.format(System.currentTimeMillis()));
		
		recruit.setOnMouseClicked(e -> App.go(App.apath + "recruit_.fxml"));
		dorm.setOnMouseClicked(e -> App.go(App.apath + "dorm_.fxml"));
		logout.setOnMouseClicked(e -> App.logout());
	}

}
