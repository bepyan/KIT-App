package kumoh.core.model;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int USER_AUTH_STUDENT = 2;

	private String univType;
	private String id;
	private String pwd;
	private String name;
	private String sex;
	private String depCode;
	private String depName;
	private String grade;
	private String major;
	private String classType;
	private String stdZip;
	private String stdAddress;
	private String stdPhone;
	private String prtName;
	private String prtZip;
	private String prtAddress;
	private String prtPhone;
	private String prtRelation;

	public String getUnivType() {
		return univType;
	}

	public void setUnivType(String univType) {
		this.univType = univType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getStdZip() {
		return stdZip;
	}

	public void setStdZip(String stdZip) {
		this.stdZip = stdZip;
	}

	public String getStdAddress() {
		return stdAddress;
	}

	public void setStdAddress(String stdAddress) {
		this.stdAddress = stdAddress;
	}

	public String getStdPhone() {
		return stdPhone;
	}

	public void setStdPhone(String stdPhone) {
		this.stdPhone = stdPhone;
	}

	public String getPrtName() {
		return prtName;
	}

	public void setPrtName(String prtName) {
		this.prtName = prtName;
	}

	public String getPrtZip() {
		return prtZip;
	}

	public void setPrtZip(String prtZip) {
		this.prtZip = prtZip;
	}

	public String getPrtAddress() {
		return prtAddress;
	}

	public void setPrtAddress(String prtAddress) {
		this.prtAddress = prtAddress;
	}

	public String getPrtPhone() {
		return prtPhone;
	}

	public void setPrtPhone(String prtPhone) {
		this.prtPhone = prtPhone;
	}

	public String getPrtRelation() {
		return prtRelation;
	}

	public void setPrtRelation(String prtRelation) {
		this.prtRelation = prtRelation;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}