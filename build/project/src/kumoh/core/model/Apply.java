package kumoh.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Apply implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String term;
	private String id;
	private String GPA;
	private String point;
	private LocalDateTime applyDate;
	private LocalDateTime cancelDate;
	private String yearSubId;
	private String yearMeal;
	private String firstSubId;
	private String firstMeal;
	private String secondSubId;
	private String secondMeal;
	private String thirdSubId;
	private String thirdMeal;
	private Pledge pledge;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGPA() {
		return GPA;
	}

	public void setGPA(String gPA) {
		GPA = gPA;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public LocalDateTime getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDateTime applyDate) {
		this.applyDate = applyDate;
	}

	public LocalDateTime getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(LocalDateTime cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getYearSubId() {
		return yearSubId;
	}

	public void setYearSubId(String yearSubId) {
		this.yearSubId = yearSubId;
	}

	public String getYearMeal() {
		return yearMeal;
	}

	public void setYearMeal(String yearMeal) {
		this.yearMeal = yearMeal;
	}

	public String getFirstSubId() {
		return firstSubId;
	}

	public void setFirstSubId(String firstSubId) {
		this.firstSubId = firstSubId;
	}

	public String getFirstMeal() {
		return firstMeal;
	}

	public void setFirstMeal(String firstMeal) {
		this.firstMeal = firstMeal;
	}

	public String getSecondSubId() {
		return secondSubId;
	}

	public void setSecondSubId(String secondSubId) {
		this.secondSubId = secondSubId;
	}

	public String getSecondMeal() {
		return secondMeal;
	}

	public void setSecondMeal(String secondMeal) {
		this.secondMeal = secondMeal;
	}

	public String getThirdSubId() {
		return thirdSubId;
	}

	public void setThirdSubId(String thirdSubId) {
		this.thirdSubId = thirdSubId;
	}

	public String getThirdMeal() {
		return thirdMeal;
	}

	public void setThirdMeal(String thirdMeal) {
		this.thirdMeal = thirdMeal;
	}

	public Pledge getPledge() {
		return pledge;
	}

	public void setPledge(Pledge pledge) {
		this.pledge = pledge;
	}

}