package kumoh.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.SubRecruit;

public class Dorm_ implements Initializable{

    @FXML private ImageView back, home, logout;
    @FXML private Button add, manage;
    @FXML private TableView<SubRecruit> table;
    @FXML private TableColumn<?, ?> col;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		logout.setOnMouseClicked(e -> App.logout());
		home.setOnMouseClicked(e -> App.goFade(App.apath + "main.fxml"));
		back.setOnMouseClicked(e -> App.go(App.apath + "main.fxml"));

    	col.setCellValueFactory(new PropertyValueFactory<>("name"));
		try {
			table.getItems().setAll(App.network.getSubRecruits());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		manage.setOnAction(e -> {
			if(table.getSelectionModel().isEmpty())
				return;
			App.handle = table.getSelectionModel().getSelectedItem().getName();
			App.go(App.apath + "dorm_inRoom_.fxml");
		});
		add.setOnAction(e -> {
			App.pop(App.apath + "dorm_add.fxml");
			try {
				table.getItems().setAll(App.network.getSubRecruits());
			} catch (Exception e1) {
				System.err.println("getSubRecruits 전송 오류");
			}
		});
	}
}
