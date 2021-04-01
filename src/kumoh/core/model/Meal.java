package kumoh.core.model;

import java.io.Serializable;

public class Meal implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String term;
	private String recruit;
	private String mealType;
	private Integer mealFee;

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

	public String getRecruit() {
		return recruit;
	}

	public void setRecruit(String name) {
		this.recruit = name;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public Integer getMealFee() {
		return mealFee;
	}

	public void setMealFee(Integer mealFee) {
		this.mealFee = mealFee;
	}

	@Override
	public String toString() {
		return this.getMealType();
	}
}