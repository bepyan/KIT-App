package kumoh.student;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.RecruitDate;
import kumoh.core.model.Student;

public class S_main {

    @FXML private Label date, s_name, about;
    @FXML private ImageView select, logout;
    @FXML private JFXListView<String> listView;
	RecruitDate[] recruitDates;
	@FXML
	private void initialize() {

		// xxx �� ȯ���մϴ�
		Student student = null;
		try {	
			student = App.network.getStudent();
			s_name.setText(student.getName() + " �� ȯ���մϴ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			date.setText(App.network.queryServerTime().toLocalDate().toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		recruitDates = null;
		try {
			recruitDates = App.network.getRecruitDates();
		    ObservableList<String> list = FXCollections.observableArrayList();
			for(int i = 0; i < recruitDates.length; i++)
				list.add(recruitDates[i].getYear() + "�⵵ " + recruitDates[i].getTerm() + "�б�");
			listView.setItems(list);
		} catch (Exception e) {
			System.err.println("recruitDates ������ �ۼ��� ����");
		}

		logout.setOnMouseClicked(event -> App.logout());
		select.setOnMouseClicked(event -> {
			if(listView.getSelectionModel().getSelectedItem() == null)
				return;
			try {
				App.network.selectRecruitDate(recruitDates[listView.getSelectionModel().getSelectedIndex()]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.go(App.spath + "S_recruitDateMain.fxml");
		});
		about.setOnMouseClicked(e -> App.pop("app/about.fxml"));
	}
}
