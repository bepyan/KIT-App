package kumoh.core.model;

import java.io.Serializable;

public class Bed implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String subRecruit;
	private Integer roomNum;
	private String bedNum;
	private String valid;

	public String getSubRecruit() {
		return subRecruit;
	}

	public void setSubRecruit(String subRecruit) {
		this.subRecruit = subRecruit;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getBedNum() {
		return bedNum;
	}

	public void setBedNum(String bedNum) {
		this.bedNum = bedNum;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}
