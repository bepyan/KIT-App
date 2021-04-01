package kumoh.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Selection implements Serializable {
	private static final long serialVersionUID = 1L;

	private String year;
	private String term;
	private String id;
	private String div;
	private String subRecruit;
	private String meal;
	private LocalDateTime depositDate;
	private LocalDateTime uploadDate;
	private String uploaded; // ½ÂÀÎ¿©ºÎ
	private String uploadUrl;
	private String valided;
	private Integer roomNum;
	private String roomBed;

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

	public String getSubRecruit() {
		return subRecruit;
	}

	public void setSubRecruit(String subRecruit) {
		this.subRecruit = subRecruit;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public LocalDateTime getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(LocalDateTime depositDate) {
		this.depositDate = depositDate;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public String getValided() {
		return valided;
	}

	public void setValided(String valided) {
		this.valided = valided;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomBed() {
		return roomBed;
	}

	public void setRoomBed(String roomBed) {
		this.roomBed = roomBed;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

}