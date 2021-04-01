package kumoh.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.DataPackage;
import kumoh.core.model.Recruit;
import kumoh.core.model.RecruitDate;
import kumoh.core.model.Schedule;

public class Recruit_manage implements Initializable{
	
    @FXML private ImageView save, showApply, managePasser, selectPasser, inRoom, back;
    @FXML private Button run, cancel;

    @FXML private TextField year, term;
    @FXML private TextArea notice, invoice, pledge;
    
    @FXML private TableView<Schedule> schedule;
    @FXML private TableColumn<?, ?> schedule_name, schedule_start, schedule_end;
    @FXML private Button schedule_add, schedule_del;

    @FXML private TableView<Recruit> recruit;
    @FXML private TableColumn<?, ?> recruit_name, recruit_sex, recruit_year;
    @FXML private Button recruit_add, recruit_del;	

	private List<Schedule> schedules;
	private List<Recruit> recruits;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		save.setOnMouseClicked(e -> add());
		showApply.setOnMouseClicked(e -> App.pop(App.apath + "recruit_showApply.fxml"));
		managePasser.setOnMouseClicked(e -> App.pop(App.apath + "recruit_managePasser_.fxml"));
		selectPasser.setOnMouseClicked(e -> App.pop(App.apath + "recruit_selectPasser.fxml"));
		inRoom.setOnMouseClicked(e -> App.pop(App.apath + "recruit_inRoom.fxml"));
		back.setOnMouseClicked(e -> App.go(App.apath + "recruit_.fxml"));
		cancel.setOnAction(e -> App.go(App.apath + "recruit_.fxml"));		
		run.setOnAction(e -> add());
		
		// 현재 모집 정보를 얻는다
		RecruitDate recruitDate = (RecruitDate) App.handle;
		year.setText(recruitDate.getYear());
		term.setText(recruitDate.getTerm());
		notice.setText(recruitDate.getNotice());
		invoice.setText(recruitDate.getInvoice());
		pledge.setText(recruitDate.getPledge());
		
		DataPackage dataPackage = null;
		try {
			dataPackage = App.network.getDataPackage();
		} catch (Exception e1) {
			System.err.println("datapackage 송신 실패");
		}

		
		schedules = new ArrayList<Schedule>(Arrays.asList(dataPackage.getSchedules()));
		schedule_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		schedule_start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		schedule_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		schedule.getItems().setAll(schedules);
		schedule_add.setOnAction(e -> {
			App.pop(App.apath + "schedule_add.fxml");
			if(App.handle == null)
				return;
			schedules.add((Schedule)App.handle);
			schedule.getItems().setAll(schedules);
		});
		schedule_del.setOnAction(e -> {
			schedules.remove(schedule.getSelectionModel().getSelectedItem());
			schedule.getItems().setAll(schedules);
		});
		
		
		
		recruits =  new ArrayList<Recruit>(Arrays.asList(dataPackage.getRecruits()));
		recruit_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		recruit_sex.setCellValueFactory(new PropertyValueFactory<>("recruitSex"));
		recruit_year.setCellValueFactory(new PropertyValueFactory<>("recruitYear"));
		recruit.getItems().setAll(recruits);
		recruit_add.setOnAction(e ->{
			App.pop(App.apath + "recruit_add.fxml");
			if(App.handle == null)
				return;
			recruits.add((Recruit)App.handle);
			recruit.getItems().setAll(recruits);
		});
		recruit_del.setOnAction(e -> {
			recruits.remove(recruit.getSelectionModel().getSelectedItem());
			recruit.getItems().setAll(recruits);
		});
	}
	
	public void add() {
			DataPackage dataPackage = new DataPackage();
			RecruitDate recruitDate = new RecruitDate();
			recruitDate.setYear(year.getText());
			recruitDate.setTerm(term.getText());
			recruitDate.setNotice(notice.getText());
			recruitDate.setInvoice(invoice.getText());
			recruitDate.setPledge(pledge.getText());
			dataPackage.setRecruitDate(recruitDate);
			
			for (int i = 0; i < schedules.size(); i++) {
				schedules.get(i).setYear(recruitDate.getYear());
				schedules.get(i).setTerm(recruitDate.getTerm());
			}
			dataPackage.setSchedules(schedules.toArray(new Schedule[0]));
			
			for (int i = 0; i < recruits.size(); i++) {
				recruits.get(i).setYear(recruitDate.getYear());
				recruits.get(i).setTerm(recruitDate.getTerm());
				for (int j = 0; j < recruits.get(i).getMeal().length; j++) {
					recruits.get(i).getMeal()[j].setYear(recruitDate.getYear());
					recruits.get(i).getMeal()[j].setTerm(recruitDate.getTerm());
				}
			}
			dataPackage.setRecruits(recruits.toArray(new Recruit[0]));
			try {
				App.network.updateDataPackage(dataPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.go(App.apath + "recruit_.fxml");
	}
}
