package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Apply;

public class Recruit_showApply {
	
	@FXML private ImageView back;
	@FXML private TableView<Apply> table;
	@FXML private TableColumn<?, ?> sid, gpa, distance, year, first, second, third, vaild;

	@FXML
	private void initialize() {
		Apply[] applies = null;
		try {
			applies = App.network.getApplies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sid.setCellValueFactory(new PropertyValueFactory<>("id"));
		gpa.setCellValueFactory(new PropertyValueFactory<>("GPA"));
		distance.setCellValueFactory(new PropertyValueFactory<>("point"));
		year.setCellValueFactory(new PropertyValueFactory<>("yearSubId"));
		first.setCellValueFactory(new PropertyValueFactory<>("firstSubId"));
		second.setCellValueFactory(new PropertyValueFactory<>("secondSubId"));
		third.setCellValueFactory(new PropertyValueFactory<>("thirdSubId"));
		vaild.setCellValueFactory(new PropertyValueFactory<>("cancelDate"));
		table.getItems().setAll(applies);
		
		back.setOnMouseClicked(e -> ((Stage) ((Node) (e.getSource())).getScene().getWindow()).close());
	}

}
