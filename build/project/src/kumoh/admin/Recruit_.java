package kumoh.admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.RecruitDate;

public class Recruit_ implements Initializable{

    @FXML private ImageView back, home, logout;
    @FXML private JFXListView<String> listView;
    @FXML private Button add, manage;
	RecruitDate[] recruitDates;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		logout.setOnMouseClicked(e -> App.logout());
		home.setOnMouseClicked(e -> App.goFade(App.apath + "main.fxml"));
		back.setOnMouseClicked(e -> App.go(App.apath + "main.fxml"));
		
		recruitDates = null;
		try {
			recruitDates = App.network.getRecruitDates();
		    ObservableList<String> list = FXCollections.observableArrayList();
			for(int i = 0; i < recruitDates.length; i++)
				list.add(recruitDates[i].getYear() + "년도 " + recruitDates[i].getTerm() + "학기");
			listView.setItems(list);
		} catch (Exception e) {
			System.err.println("recruitDates 데이터 송수신 실패");
		}

		add.setOnAction(e -> App.go(App.apath + "recruit_enroll.fxml"));
		manage.setOnAction(e -> {
			try {
				App.handle = recruitDates[listView.getSelectionModel().getSelectedIndex()];
				App.network.selectRecruitDate((RecruitDate)App.handle);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			App.go(App.apath + "recruit_manage.fxml");
		});
	}
    
    
}
