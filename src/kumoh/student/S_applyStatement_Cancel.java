package kumoh.student;

import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Apply;

public class S_applyStatement_Cancel {

    @FXML
    private Button button_yes, button_no;
    
    @FXML
    private void initialize() {

    	 button_yes.setOnAction(event->{
             
            Apply apply = null;
            try {
               apply = App.network.getApply();
            } catch (Exception e1) {
               e1.printStackTrace();
            }
            //������ ������ �Ѱ���
            //��Ȱ�� ��û ����� �� �ִ� ��� : ��û��, ������
            apply.setCancelDate(LocalDateTime.now());
            try {
               App.network.updateApply(apply);
            } catch (Exception e) {
               e.printStackTrace();
            }
            S_applyStatement.cancel = true;
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
            App.pop(App.spath + "S_applyStatement_CancelSuccess.fxml");
    	 });
          
        button_no.setOnAction(event->{
            S_applyStatement.cancel = false;
            ((Stage) ((Node) (event.getSource())).getScene().getWindow()).close();
         });
    }
}
