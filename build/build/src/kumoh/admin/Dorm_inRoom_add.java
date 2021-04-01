package kumoh.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Bed;
import kumoh.core.model.SubRecruit;

public class Dorm_inRoom_add{

    @FXML private TextField room, bed, valid;
    @FXML private Button add, cancel;
    private SubRecruit target;

	@FXML
	public void initialize() {
    	try {
			target = App.network.getSubRecruit((String)App.handle);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	add.setOnAction(event -> {
    		Bed nbed = new Bed();
    		nbed.setSubRecruit(target.getName());
    		nbed.setRoomNum(Integer.parseInt(room.getText()));
    		nbed.setBedNum(bed.getText());
    		nbed.setValid(valid.getText());
    		
    		List<Bed> nbeds = new ArrayList<Bed>(Arrays.asList(target.getBeds()));
   	 		nbeds.add(nbed);
    		target.setBeds(nbeds.toArray(new Bed[0]));
    		
    		try {
				App.network.updateSubRecruit(target);
			} catch (Exception e) {
				e.printStackTrace();
			}
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
    	
		cancel.setOnAction(event -> {
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
	}
}
