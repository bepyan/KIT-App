package kumoh.student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Apply;
import kumoh.core.model.DataPackage;
import kumoh.core.model.Meal;
import kumoh.core.model.Pledge;
import kumoh.core.model.Recruit;
import kumoh.core.model.RecruitDate;
import kumoh.core.model.Student;

public class S_apply {

	@FXML private TextArea textArea_pledge;

	@FXML private CheckBox check_agree;
	@FXML private ComboBox<Recruit> recruit_oneYear, recruit_first, recruit_second, recruit_third;
	@FXML private ComboBox<Meal> meal_oneYear, meal_first, meal_second, meal_third;
	
	@FXML private Label cost_oneYear, cost_first, cost_second, cost_third;
	@FXML private Button button_apply, button_exit;
	public static String popText = "";
	
	@FXML
	private void initialize() {

		Student student = null;
		DataPackage dataPackage = null;

		try {
			student = App.network.getStudent();
			App.handle = student;
			dataPackage = App.network.getDataPackage();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* 서약서 내용 */
		RecruitDate recruitDate = dataPackage.getRecruitDate();
		textArea_pledge.setText(recruitDate.getPledge());

		/* 콤보박스 set */
		Recruit[] list = dataPackage.getRecruits();
		List<Recruit> oneYears = new ArrayList<>();
		List<Recruit> normals = new ArrayList<>();

		for (int i = 0; i < list.length; i++) {
			if (student.getSex().equals(list[i].getRecruitSex())) {
				if (list[i].getRecruitYear().equals("Y")) {
					oneYears.add(list[i]);
				} else
					normals.add(list[i]);
			}
		}
		ObservableList<Recruit> oneYearList = FXCollections.observableArrayList(oneYears);
		recruit_oneYear.setItems(oneYearList);
		recruit_oneYear.getItems().add(new Recruit("선택 안함"));
		
		ObservableList<Recruit> firstList = FXCollections.observableArrayList(normals);
		recruit_first.setItems(firstList);
		ObservableList<Recruit> secondList = FXCollections.observableArrayList(normals);
		recruit_second.setItems(secondList);
		ObservableList<Recruit> thirdList = FXCollections.observableArrayList(normals);
		recruit_third.setItems(thirdList);

		/* 1년기숙 콤보박스 */
		recruit_oneYear.setOnAction(event -> {
			Recruit recruit = recruit_oneYear.getSelectionModel().getSelectedItem();
			ObservableList<Meal> mealList = FXCollections.observableArrayList(recruit.getMeal());
			meal_oneYear.setItems(mealList);
		});
		meal_oneYear.setOnAction(event -> {
			cost_oneYear.setText(recruit_oneYear.getSelectionModel().getSelectedItem().getFee()
					+ meal_oneYear.getSelectionModel().getSelectedItem().getMealFee() + "");
		});

		/* 1지망 콤보박스 */
		recruit_first.setOnAction(event -> {
			Recruit recruit = recruit_first.getSelectionModel().getSelectedItem();
			ObservableList<Meal> mealList = FXCollections.observableArrayList(recruit.getMeal());
			meal_first.setItems(mealList);
		});
		meal_first.setOnAction(event -> {
			cost_first.setText(recruit_first.getSelectionModel().getSelectedItem().getFee()
					+ meal_first.getSelectionModel().getSelectedItem().getMealFee() + "");
		});

		/* 2지망 콤보박스 */
		recruit_second.setOnAction(event -> {
			Recruit recruit = recruit_second.getSelectionModel().getSelectedItem();
			ObservableList<Meal> mealList = FXCollections.observableArrayList(recruit.getMeal());
			meal_second.setItems(mealList);
		});
		meal_second.setOnAction(event -> {
			cost_second.setText(recruit_second.getSelectionModel().getSelectedItem().getFee()
					+ meal_second.getSelectionModel().getSelectedItem().getMealFee() + "");
		});

		/* 3지망 콤보박스 */
		recruit_third.setOnAction(event -> {
			Recruit recruit = recruit_third.getSelectionModel().getSelectedItem();
			ObservableList<Meal> mealList = FXCollections.observableArrayList(recruit.getMeal());
			meal_third.setItems(mealList);
		});
		meal_third.setOnAction(event -> {
			cost_third.setText(recruit_third.getSelectionModel().getSelectedItem().getFee()
					+ meal_third.getSelectionModel().getSelectedItem().getMealFee() + "");
		});

		/* 신청버튼 */
		button_apply.setOnAction(event -> {

			//입사서약서 동의 여부
			if(check_agree.isSelected() == false) {
				popText = "입사서약서에 동의해주세요";
				App.pop(App.spath + "S_applyPop.fxml");
				return;
			}
            //모집 모두 선택 여부
			if(recruit_oneYear.getSelectionModel().isEmpty() 
					|| recruit_first.getSelectionModel().isEmpty()
					|| recruit_second.getSelectionModel().isEmpty() 
					|| recruit_third.getSelectionModel().isEmpty()){
				popText = "모집명을 선택해주세요";
				App.pop(App.spath + "S_applyPop.fxml");
				return;
			}
            //식사 모두 선택 여부
            if (meal_oneYear.getSelectionModel().isEmpty()
                  || meal_first.getSelectionModel().isEmpty()
                  || meal_second.getSelectionModel().isEmpty()
                  || meal_third.getSelectionModel().isEmpty()){
            	popText = "식사를 선택해주세요";
            	App.pop(App.spath + "S_applyPop.fxml");
            	return;
            }
            
			/* [서약서동의여부] 서버에 보내줌 */
			Pledge pledge = new Pledge();
			pledge.setYear(recruitDate.getYear());
			pledge.setTerm(recruitDate.getTerm());
			pledge.setId(((Student) App.handle).getId());
			pledge.setPrivacyAgree("Y");
			pledge.setPledgeAgree("Y");// 서약서동의

			/* 나의 신청정보와 필요한 데이터들을 [신청]해 서버에 보내줌 */
			Apply apply = new Apply();
			apply.setYear(recruitDate.getYear());
			apply.setTerm(recruitDate.getTerm());
			apply.setId(((Student) App.handle).getId());
			apply.setApplyDate(LocalDateTime.now());
			apply.setPledge(pledge);

			apply.setYearSubId(recruit_oneYear.getSelectionModel().getSelectedItem().toString().equals("선택 안함") ? null : recruit_oneYear.getSelectionModel().getSelectedItem().toString());
			apply.setYearMeal(meal_oneYear.getSelectionModel().getSelectedItem().toString().equals("없음")? null : meal_oneYear.getSelectionModel().getSelectedItem().toString());
			apply.setFirstSubId(recruit_first.getSelectionModel().getSelectedItem().toString());
			apply.setFirstMeal(meal_first.getSelectionModel().getSelectedItem().toString());
			apply.setSecondSubId(recruit_second.getSelectionModel().getSelectedItem().toString());
			apply.setSecondMeal(meal_second.getSelectionModel().getSelectedItem().toString());
			apply.setThirdSubId(recruit_third.getSelectionModel().getSelectedItem().toString());
			apply.setThirdMeal(meal_third.getSelectionModel().getSelectedItem().toString());
			
			try {
					App.network.createApply(apply);
			} catch (Exception e) {
				e.printStackTrace();
			}
            //양식에 맞게 잘 쓴 학생은 신청(성공) 잘 되었음
			popText = "생활관 신청이 완료되었습니다,";
			App.pop(App.spath + "S_applyPop.fxml");
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
		
		/* 닫기버튼 */
		button_exit.setOnAction(event -> {
			((Stage)((Node)(event.getSource())).getScene().getWindow()).close();
		});
	}

}
