package kumoh.app;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Login_controller implements Initializable{

	@FXML private Pane root;
	@FXML private JFXTextField id;
	@FXML private JFXPasswordField pw;
	@FXML private ImageView close, login;
	@FXML private Label state;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		login.setOnMouseClicked(e -> login());
		id.setOnAction(e -> login());
		pw.setOnAction(e -> login());
		close.setOnMouseClicked(e -> System.exit(0));
		
	}
    public void login(){
    	
    	try {
			boolean res[] = App.network.login(id.getText(), pw.getText());
			if(res[0])
				App.goFade(res[1] ? App.apath + "main.fxml" : App.spath + "S_main.fxml");
			else
			{
				new FadeIn(root).setSpeed(2).play();
				state.setText("아이디와 비밀번호를 확인하세요");
			}
		} catch (Exception e) {
			App.go("fail.fxml");
		}
    }
}
