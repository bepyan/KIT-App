package kumoh.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.Bed;

public class Dorm_inRoom_ implements Initializable{

    @FXML private ImageView back, home, logout;
    @FXML private Button add, alter, freeBed;
    @FXML private TableView<Bed> table;
    @FXML private TableColumn<?, ?> room, bed, usable;
    @FXML private Label name;
	public static String targetName;
	public static int selectedIndex;
	public static boolean change;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		refresh();
		
		logout.setOnMouseClicked(e -> App.logout());
		home.setOnMouseClicked(e -> App.goFade(App.apath + "main.fxml"));
		back.setOnMouseClicked(e -> App.go(App.apath + "dorm_.fxml"));

		alter.setOnAction(e -> {
			if(!table.getSelectionModel().isEmpty())
			{
				selectedIndex = table.getSelectionModel().getSelectedIndex();
				App.pop(App.apath + "dorm_inRoom_alter.fxml");
				try {
					table.getItems().setAll(App.network.getSubRecruit(targetName).getBeds());
				} catch (Exception ex) {
					System.err.println("데이터 변경 실패");
				}
			}
		});
		add.setOnAction(e -> {
			App.pop(App.apath + "dorm_inRoom_add.fxml");
			try {
				table.getItems().setAll(App.network.getSubRecruit(targetName).getBeds());
			} catch (Exception ex) {
				System.err.println("데이터 변경 실패");
			}
		});
		freeBed.setOnAction(e ->{
			Dorm_inRoom_.change = true;
			App.pop(App.apath + "dorm_inRoom_freeBed.fxml");
			if(!change)
				App.goFade(App.apath + "dorm_inRoom_.fxml");
			else
				refresh();
			}
		);
}
	
	private void refresh() {
		targetName = (String)App.handle;
		name.setText(targetName + " - 입실단위 관리");
		room.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		bed.setCellValueFactory(new PropertyValueFactory<>("bedNum"));
		usable.setCellValueFactory(new PropertyValueFactory<>("valid"));
		
		try {
			table.getItems().setAll(App.network.getSubRecruit(targetName).getBeds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
