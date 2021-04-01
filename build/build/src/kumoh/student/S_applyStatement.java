package kumoh.student;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import kumoh.app.App;
import kumoh.core.model.Apply;
import kumoh.core.model.Recruit;
import kumoh.core.model.Selection;

public class S_applyStatement {

	@FXML private Label recruit_oneYear, meal_oneYear;
	@FXML private Label cost_oneYear, cost_first, cost_second, cost_third;
	@FXML private Label recruit_first, recruit_second, recruit_third;
	@FXML private Label meal_first, meal_second, meal_third;
	@FXML private Label selectionView, sheetView;	// 합격여부, 결핵딘단서 제출 여부

	@FXML private ImageView invoiceView, roomView, applyCancel, sheetSubmit, back, logout;
	@FXML
	private void initialize() {

//		DataPackage dataPackage = null;
//		try {
//			dataPackage = Client.network.getDataPackage();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		
//		RecruitDate recruitDate = dataPackage.getRecruitDate();	
		
		Apply apply = null;
		try {
			apply = App.network.getApply();
		} catch (Exception e) {
			e.printStackTrace();
		}
		App.handle = apply;
		
		recruit_oneYear.setText(apply.getYearSubId());
		recruit_first.setText(apply.getFirstSubId());
		recruit_second.setText(apply.getSecondSubId());
		recruit_third.setText(apply.getThirdSubId());

		meal_oneYear.setText(apply.getYearMeal());
		meal_first.setText(apply.getFirstMeal());
		meal_second.setText(apply.getSecondMeal());
		meal_third.setText(apply.getThirdMeal());

		Recruit[] recruits = null;
		try {
			recruits = App.network.getRecruits();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*총 비용 view*/
		if (apply.getYearSubId() != null) {
			int oneYearCost = 0;
			for (int i = 0; i < recruits.length; i++)
				if (apply.getYearSubId().equals(recruits[i].getName())) {
					oneYearCost += recruits[i].getFee();
					for (int j = 0; j < recruits[i].getMeal().length; j++)
						if (apply.getYearMeal().equals(recruits[i].getMeal()[j].getMealType()))
							oneYearCost += recruits[i].getMeal()[j].getMealFee();
				}
			cost_oneYear.setText(Integer.toString(oneYearCost) + "");
		}

		if (apply.getFirstSubId() != null) {
			int firstCost = 0;
			for (int i = 0; i < recruits.length; i++)
				if (apply.getFirstSubId().equals(recruits[i].getName())) {
					firstCost += recruits[i].getFee();
					for (int j = 0; j < recruits[i].getMeal().length; j++)
						if (apply.getFirstMeal().equals(recruits[i].getMeal()[j].getMealType()))
							firstCost += recruits[i].getMeal()[j].getMealFee();
				}
			cost_first.setText(firstCost + "");
		}

		if (apply.getSecondSubId() != null) {
			int secondCost = 0;
			for (int i = 0; i < recruits.length; i++)
				if (apply.getSecondSubId().equals(recruits[i].getName())) {
					secondCost += recruits[i].getFee();
					for (int j = 0; j < recruits[i].getMeal().length; j++)
						if (apply.getFirstMeal().equals(recruits[i].getMeal()[j].getMealType()))
							secondCost += recruits[i].getMeal()[j].getMealFee();
				}
			cost_second.setText(secondCost + "");
		}

		if (apply.getThirdSubId() != null) {
			int thirdCost = 0;
			for (int i = 0; i < recruits.length; i++)
				if (apply.getThirdSubId().equals(recruits[i].getName())) {
					thirdCost += recruits[i].getFee();
					for (int j = 0; j < recruits[i].getMeal().length; j++)
						if (apply.getThirdMeal().equals(recruits[i].getMeal()[j].getMealType()))
							thirdCost += recruits[i].getMeal()[j].getMealFee();
				}
			cost_third.setText(thirdCost + "");
		}
		
		
		
		/*합격,불합격여부*/
		Selection selection = null;
		try {
			selection = App.network.getSelection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		App.handle = selection;
		
		
		if(selection != null) {
			selectionView.setText(selection.getValided().equals("Y") ? selection.getSubRecruit() + " [ 합격 ]" : " [ 대기 ]");
			sheetView.setText(selection.getUploaded().equals("Y") ? selection.getUploadDate().toString() + "에 등록했습니다." : "제출된 진단서가 없습니다.");
		}
		else {
			selectionView.setText("아직 선발을 시행하지 않았습니다.");
			sheetView.setText("");
		}
		
		/* 뒤로가기 */
		back.setOnMouseClicked(event -> App.go(App.spath + "S_recruitDateMain.fxml"));
		logout.setOnMouseClicked(event -> App.logout());
		
		/***------------버튼비활성화 확인 !----------------------------***/
		if (selection == null || selection.getValided().equals("N")) {
			roomView.setDisable(true);
			sheetSubmit.setDisable(true);
			invoiceView.setDisable(true);
		}
		
		/*생활관 호실조회*/
		roomView.setOnMouseClicked(event -> App.pop(App.spath + "S_applyStatement_roomView.fxml"));
		
		/*결핵진단서 제출*/
		sheetSubmit.setOnMouseClicked(event -> App.pop(App.spath + "S_applyStatement_sheetSubmit.fxml"));
		
		/*고지서조회*/
		invoiceView.setOnMouseClicked(event -> App.pop(App.spath + "S_applyStatement_invoiceView.fxml"));
		
		
		/*취소기간 아니면 신청 비활성화*/
		try {
			if (!App.network.checkSchedule("신청기간")) 
				applyCancel.setDisable(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/*취소버튼 누를 시*/
		applyCancel.setOnMouseClicked(event-> {
			App.pop(App.spath + "S_applyStatement_Cancel.fxml");
			if(App.handle != null)
				App.goFade(App.spath + "S_recruitDateMain.fxml");
		});
	}


}
