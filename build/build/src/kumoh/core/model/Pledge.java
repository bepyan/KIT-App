package kumoh.core.model;

import java.io.Serializable;

public class Pledge implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String term;
	private String id;
	private String privacyAgree;
	private String pledgeAgree;

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

	public String getPrivacyAgree() {
		return privacyAgree;
	}

	public void setPrivacyAgree(String privacyAgree) {
		this.privacyAgree = privacyAgree;
	}

	public String getPledgeAgree() {
		return pledgeAgree;
	}

	public void setPledgeAgree(String pledgeAgree) {
		this.pledgeAgree = pledgeAgree;
	}

}
