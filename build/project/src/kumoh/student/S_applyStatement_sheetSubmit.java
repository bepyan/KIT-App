package kumoh.student;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kumoh.app.App;

public class S_applyStatement_sheetSubmit {

	@FXML private TextField sheetFilePath;
    @FXML private Button button_findSheet, button_uploadSheet, button_deleteSheet, button_exit;
    
    private FileChooser fileChooser;
    private File file;
    private FileChooser.ExtensionFilter fileExtension;
    
    @FXML
    private void initialize() {
    	
    	file = null;
    	
    	button_exit.setOnAction(event->{
    		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    	});
    	
    	
    	/*파일찾기 --> fileChooser 쓸수있을까 ?*/
    	button_findSheet.setOnAction(event->{
    		
    		fileChooser = new FileChooser();
    		fileExtension = new FileChooser.ExtensionFilter("JPG", "*.jpg");
    		fileChooser.getExtensionFilters().add(fileExtension);
    		
    		file = fileChooser.showOpenDialog(App.stage);
    		if (file!=null) sheetFilePath.setText(file.getAbsolutePath());
    	});
    	
    	/**/
    	button_uploadSheet.setOnAction(event->{
    		if (file == null) return;
    		try {
				App.network.uploadFile(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
    	});
    	
    	button_deleteSheet.setOnAction(event->{
    		file=null;
    		sheetFilePath.clear();
    		//추가작업
    	});
    	
    }
}
