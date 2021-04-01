package kumoh.core.model;

import java.io.Serializable;

public class DataPackage implements Serializable {
	private static final long serialVersionUID = 1L;

	private RecruitDate recruitDate;
	private Schedule[] schedules;
	private Recruit[] recruits;

	public RecruitDate getRecruitDate() {
		return recruitDate;
	}

	public void setRecruitDate(RecruitDate recruitDate) {
		this.recruitDate = recruitDate;
	}

	public Schedule[] getSchedules() {
		return schedules;
	}

	public void setSchedules(Schedule[] schedules) {
		this.schedules = schedules;
	}

	public Recruit[] getRecruits() {
		return recruits;
	}

	public void setRecruits(Recruit[] recruits) {
		this.recruits = recruits;
	}
}
