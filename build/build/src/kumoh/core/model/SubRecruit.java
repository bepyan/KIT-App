package kumoh.core.model;

import java.io.Serializable;

public class SubRecruit implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String valid;
	private Bed[] beds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public Bed[] getBeds() {
		return beds;
	}

	public void setBeds(Bed[] beds) {
		this.beds = beds;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}