package kumoh.admin;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Meal;

public class Meal_add {

    @FXML private TextField type, fee;
    @FXML private Button add, cancel;
    @FXML
	public void initialize() {
		App.handle = null;
		
    	add.setOnAction(event -> {
    		Meal meal = new Meal();
    		meal.setMealType(type.getText());
    		meal.setMealFee(Integer.parseInt(fee.getText()));
    		App.handle = meal;
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
    	
		cancel.setOnAction(event -> {
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
	}

}
