package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Bed;
import kumoh.core.model.SubRecruit;

public class Dorm_inRoom_alter {

    @FXML private TextField room, bed, valid;
    @FXML private Button alter, cancel;
    private SubRecruit target;
    private Bed cur;

	@FXML
	public void initialize() {
    	try {
			target = App.network.getSubRecruit((String)App.handle);
			cur = target.getBeds()[Dorm_inRoom_.selectedIndex];
			room.setText(cur.getRoomNum().toString());
			bed.setText(cur.getBedNum());
			valid.setText(cur.getValid());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	alter.setOnAction(event -> {
    		Bed nbed = new Bed();
    		nbed.setSubRecruit(target.getName());
    		nbed.setRoomNum(Integer.parseInt(room.getText()));
    		nbed.setBedNum(bed.getText());
    		nbed.setValid(valid.getText());
    		target.getBeds()[Dorm_inRoom_.selectedIndex] = nbed;
    		
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
