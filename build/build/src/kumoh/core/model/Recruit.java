package kumoh.core.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Recruit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String term;
	private String name;
	private String recruitSex;
	private String recruitYear;
	private Integer fee;
	private LocalDate start;
	private LocalDate end;
	private Meal[] meal;
	private String[] subRecruit;
	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecruitSex() {
		return recruitSex;
	}

	public void setRecruitSex(String recruitSex) {
		this.recruitSex = recruitSex;
	}

	public String getRecruitYear() {
		return recruitYear;
	}

	public void setRecruitYear(String recruitYear) {
		this.recruitYear = recruitYear;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Meal[] getMeal() {
		return meal;
	}

	public void setMeal(Meal[] meal) {
		this.meal = meal;
	}

	public String[] getSubRecruit() {
		return subRecruit;
	}

	public void setSubRecruit(String[] subRecruit) {
		this.subRecruit = subRecruit;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}