package kumoh.admin;

import java.net.URL;
import java.util.ArrayList;
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

public class Recruit_enroll implements Initializable{

    @FXML private ImageView save, back, home, logout;
    @FXML private Button add, manage;

    @FXML private TextField year, term;
    @FXML private TextArea notice, invoice, pledge;
    
    @FXML private TableView<Schedule> schedule;
    @FXML private TableColumn<?, ?> schedule_name, schedule_start, schedule_end;
    @FXML private Button schedule_add, schedule_del;

    @FXML private TableView<Recruit> recruit;
    @FXML private TableColumn<?, ?> recruit_name, recruit_sex, recruit_year;
    @FXML private Button recruit_add, recruit_del;	

    @FXML private Button run, cancel;
    
	private List<Schedule> schedules = new ArrayList<Schedule>();
	private List<Recruit> recruits = new ArrayList<Recruit>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		save.setOnMouseClicked(e -> add());
		back.setOnMouseClicked(e -> App.go(App.apath + "recruit_.fxml"));
		logout.setOnMouseClicked(e -> App.logout());
		home.setOnMouseClicked(e -> App.goFade(App.apath + "main.fxml"));
		cancel.setOnAction(e -> App.go(App.apath + "recruit_.fxml"));
		run.setOnAction(e -> add());
		
		schedule_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		schedule_start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		schedule_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
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

		
		recruit_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		recruit_sex.setCellValueFactory(new PropertyValueFactory<>("recruitSex"));
		recruit_year.setCellValueFactory(new PropertyValueFactory<>("recruitYear"));
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
				App.network.createDataPackage(dataPackage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			App.go(App.apath + "recruit_.fxml");
	}
}