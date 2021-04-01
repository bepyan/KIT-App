package kumoh.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kumoh.app.App;
import kumoh.core.model.Meal;
import kumoh.core.model.Recruit;
import kumoh.core.model.SubRecruit;

public class Recruit_add {

	@FXML private ImageView save, back;
	@FXML private TextField name, sex, year, fee;

	@FXML private TableView<Meal> meal;
	@FXML private TableColumn<?, ?> meal_type, meal_fee;
	@FXML private Button meal_add, meal_del;

	@FXML private TableView<SubRecruit> dorm; 
	@FXML private TableColumn<?, ?> dorm_name;
	@FXML private Button dorm_add, dorm_del;
	@FXML private TableView<SubRecruit> dormList; 
	@FXML private TableColumn<?, ?> dormList_name;
	
	private List<Meal> meals;
	private List<SubRecruit> dorms, dormsList;
	
	@FXML
	private void initialize() {

		save.setOnMouseClicked(e -> save(e));
		back.setOnMouseClicked(e -> {
			App.handle = null;
			((Stage)((Node)(e.getSource())).getScene().getWindow()).close();
		});
		
		meals = new ArrayList<Meal>();
		meal_type.setCellValueFactory(new PropertyValueFactory<>("mealType"));
		meal_fee.setCellValueFactory(new PropertyValueFactory<>("mealFee"));
		meal_add.setOnAction(event -> {
			App.pop("admin/view/meal_add.fxml");
			if (App.handle == null)
				return;
			meals.add((Meal) App.handle);
			meal.getItems().setAll(meals);
		});
		meal_del.setOnAction(event -> {
			meals.remove(meal.getSelectionModel().getSelectedItem());
			meal.getItems().setAll(meals);
		});


		dormList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		try {
	    	dormList_name.setCellValueFactory(new PropertyValueFactory<>("name"));
			dormsList = new ArrayList<SubRecruit>(Arrays.asList(App.network.getSubRecruits()));
			dormList.getItems().setAll(dormsList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		dorm.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		dorm_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		dorms = new ArrayList<SubRecruit>();
		dorm_add.setOnAction(event -> {
			if(dormList.getSelectionModel().isEmpty())
				return;
			ObservableList<SubRecruit> s = dormList.getSelectionModel().getSelectedItems();
			dorm.getItems().addAll(s);
			dormList.getItems().removeAll(s);
		});
		dorm_del.setOnAction(event -> {
			if(dorm.getSelectionModel().isEmpty())
				return;
			ObservableList<SubRecruit> s = dorm.getSelectionModel().getSelectedItems();
			dormList.getItems().addAll(s);
			dorm.getItems().removeAll(s);
		});
	}
	
	private void save(MouseEvent e) {
		Recruit recruit = new Recruit();
		recruit.setName(name.getText());
		recruit.setRecruitSex(sex.getText());
		recruit.setRecruitYear(year.getText());
		recruit.setFee(Integer.parseInt(fee.getText()));
		recruit.setStart(LocalDate.now());
		recruit.setEnd(LocalDate.parse("2022-03-01"));
		
		for (int i = 0; i < meals.size(); i++)
			meals.get(i).setRecruit(recruit.getName());
		recruit.setMeal(meals.toArray(new Meal[0]));
		
		String[] subRecruitNames = new String[dorms.size()];
		for (int i = 0; i < dorms.size(); i++)
			subRecruitNames[i] = dorms.get(i).getName();
		recruit.setSubRecruit(subRecruitNames);

		App.handle = recruit;
		((Stage)((Node)(e.getSource())).getScene().getWindow()).close();
	}
}
