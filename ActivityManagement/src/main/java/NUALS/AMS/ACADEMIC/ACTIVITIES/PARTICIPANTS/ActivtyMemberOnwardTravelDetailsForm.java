package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

public class ActivtyMemberOnwardTravelDetailsForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int participateRequestId;
	private String rollNo;
	private String onwardStartPoint;
	private Date onwardStartDate;
	private String onwardDestination;
	private Date onwardEndDate;
	private String onwardModeofTravel;
	private Double onWardEstExpenditure;
	private Double onwardAmountAdmitted;

	private Double onwardAmountClaimed;
	private int activityCode;
	public int getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}
	public int getParticipateRequestId() {
		return participateRequestId;
	}
	public void setParticipateRequestId(int participateRequestId) {
		this.participateRequestId = participateRequestId;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getOnwardStartPoint() {
		return onwardStartPoint;
	}
	public void setOnwardStartPoint(String onwardStartPoint) {
		this.onwardStartPoint = onwardStartPoint;
	}
	public Date getOnwardStartDate() {
		return onwardStartDate;
	}
	public void setOnwardStartDate(Date onwardStartDate) {
		this.onwardStartDate = onwardStartDate;
	}
	public String getOnwardDestination() {
		return onwardDestination;
	}
	public void setOnwardDestination(String onwardDestination) {
		this.onwardDestination = onwardDestination;
	}
	public Date getOnwardEndDate() {
		return onwardEndDate;
	}
	public void setOnwardEndDate(Date onwardEndDate) {
		this.onwardEndDate = onwardEndDate;
	}
	public String getOnwardModeofTravel() {
		return onwardModeofTravel;
	}
	public void setOnwardModeofTravel(String onwardModeofTravel) {
		this.onwardModeofTravel = onwardModeofTravel;
	}
	public Double getOnWardEstExpenditure() {
		return onWardEstExpenditure;
	}
	public void setOnWardEstExpenditure(Double onWardEstExpenditure) {
		this.onWardEstExpenditure = onWardEstExpenditure;
	}
	public Double getOnwardAmountAdmitted() {
		return onwardAmountAdmitted;
	}
	public void setOnwardAmountAdmitted(Double onwardAmountAdmitted) {
		this.onwardAmountAdmitted = onwardAmountAdmitted;
	}
	public Double getOnwardAmountClaimed() {
		return onwardAmountClaimed;
	}
	public void setOnwardAmountClaimed(Double onwardAmountClaimed) {
		this.onwardAmountClaimed = onwardAmountClaimed;
	}
	
		
	
	
}
