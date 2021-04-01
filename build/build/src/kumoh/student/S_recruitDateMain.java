package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.Apply;
import kumoh.core.model.DataPackage;
import kumoh.core.model.RecruitDate;
import kumoh.core.model.Schedule;

public class S_recruitDateMain {

	@FXML private Label this_year, this_term;
	@FXML private TextArea notice;
	@FXML private Button apply;
	@FXML private ImageView applyStatement, back, logout;
	
	@FXML private TableView<Schedule> schedule;
	@FXML private TableColumn<?, ?> col_scheduleName, col_scheduleStart, col_scheduleEnd;

	@FXML
	private void initialize() {

		DataPackage dataPackage = null;
		try {
			dataPackage = App.network.getDataPackage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		RecruitDate recruitDates = dataPackage.getRecruitDate();
		notice.setText(recruitDates.getNotice());
		this_year.setText(recruitDates.getYear());
		this_term.setText(recruitDates.getTerm());

		Schedule[] schedules = dataPackage.getSchedules();
		col_scheduleName.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_scheduleStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		col_scheduleEnd.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		schedule.getItems().addAll(schedules);

		Apply apply_1 = null;
		try {
			apply_1 = App.network.getApply();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*��û���¹�ư�� ��û���°� ���ų� ����� ����� ������ ��Ȱ��ȭ��*/
		if (apply_1 == null || apply_1.getCancelDate() != null) 
			applyStatement.setDisable(true);
		
		apply.setOnAction(event -> {
			// '���� ��' && �� �������� '��Ȱ�������'�� ������ -->��û ����
			if(schedule.getSelectionModel().isEmpty())
				return;
			Apply apply = null;
			try {
				apply = App.network.getApply();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (App.network.checkSchedule("��û�Ⱓ") && (apply == null || apply.getCancelDate() != null))
				{
					App.pop(App.spath + "S_apply.fxml");
					App.go(App.spath + "S_recruitDateMain.fxml");
				}
				else 
					App.pop(App.spath + "S_mainFail.fxml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		back.setOnMouseClicked(event -> App.go(App.spath + "S_main.fxml"));
		logout.setOnMouseClicked(e -> App.logout());
		
		/*��û ���� ����*/
		applyStatement.setOnMouseClicked(event->{
			App.go(App.spath + "S_applyStatement.fxml");
		});
		
	}

}
