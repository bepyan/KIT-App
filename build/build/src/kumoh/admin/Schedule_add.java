package kumoh.admin;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Schedule;

public class Schedule_add{

	@FXML private Button add, cancel;
    @FXML private DatePicker start_date, end_date;
    @FXML private TextField name, start_time, end_time;
	
	@FXML
	public void initialize() {
		App.handle = null;
		
    	add.setOnAction(event -> {
    		Schedule schedule = new Schedule();
    		schedule.setName(name.getText());
    		schedule.setStartDate(LocalDateTime.parse(start_date.getValue() +"T"+ start_time.getText()));
    		schedule.setEndDate(LocalDateTime.parse(end_date.getValue() +"T"+ end_time.getText()));
    		App.handle = schedule;
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
    	
    	
		cancel.setOnAction(event -> {
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
	}
}
