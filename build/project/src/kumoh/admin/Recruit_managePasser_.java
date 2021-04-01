package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Selection;

public class Recruit_managePasser_ {

	@FXML private ImageView back;
	@FXML private Button showSeet, acceptDeposit, changeVaild;
	
	@FXML private TableView<Selection> table;
	@FXML private TableColumn<?, ?> sid, subRecruit, meal, room, bed, deposit, seet, type, vaild;

	@FXML
	private void initialize() {
		
		Selection[] selections = null;
		try {
			selections = App.network.getSelections();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sid.setCellValueFactory(new PropertyValueFactory<>("id"));
		subRecruit.setCellValueFactory(new PropertyValueFactory<>("subRecruit"));
		meal.setCellValueFactory(new PropertyValueFactory<>("meal"));
		room.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
		bed.setCellValueFactory(new PropertyValueFactory<>("roomBed"));
		deposit.setCellValueFactory(new PropertyValueFactory<>("depositDate"));
		seet.setCellValueFactory(new PropertyValueFactory<>("uploaded"));
		type.setCellValueFactory(new PropertyValueFactory<>("div"));
		vaild.setCellValueFactory(new PropertyValueFactory<>("valided"));
		table.getItems().setAll(selections);
		
		showSeet.setOnAction(e -> {
			if (table.getSelectionModel().getSelectedItem() == null)
				return;
			
			byte[] bytes = null;
			try {
				bytes = App.network.downloadFile(table.getSelectionModel().getSelectedItem().getId());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (bytes == null)
				return;
			App.handle = table.getSelectionModel().getSelectedItem().getId();
			App.pop(App.apath + "recruit_managePasser_acceptSeet.fxml");
			try {
				table.getItems().setAll(App.network.getSelections());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		
		acceptDeposit.setOnAction(e -> {
			if (table.getSelectionModel().isEmpty())
				return;
			App.handle = table.getSelectionModel().getSelectedItem().getId();
			App.pop(App.apath + "recruit_managePasser_acceptDeposit.fxml");
			try {
				table.getItems().setAll(App.network.getSelections());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		
		changeVaild.setOnAction(e -> {
			Selection selection = table.getSelectionModel().getSelectedItem();
			if (selection == null)
				return;

			String[] string = new String[2];
			string[0] = selection.getId();
			string[1] = selection.getValided().equals("Y") ? "N" : "Y";
			
			try {
				App.network.validSelection(string);
				table.getItems().setAll(App.network.getSelections());
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
		});
		
		back.setOnMouseClicked(e -> {
			((Stage) ((Node) (e.getSource())).getScene().getWindow()).close();
		});
		
		
	}
}
