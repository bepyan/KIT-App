package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.DataPackage;
import kumoh.core.model.RecruitDate;

public class S_applyStatement_invoiceView {

    @FXML private Button button_exit;

    @FXML private TextArea textArea_invoice;
    
    @FXML
    private void initialize() {
    	
    	/*������ ��ȸ*/
    	DataPackage dataPackage = null;
    	try {
			dataPackage = App.network.getDataPackage();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	RecruitDate recruitDates = dataPackage.getRecruitDate();
    	textArea_invoice.setText(recruitDates.getInvoice());
    	
    	/*�ݱ�*/
    	button_exit.setOnAction(event->{
			((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();

    	});
    }
}
