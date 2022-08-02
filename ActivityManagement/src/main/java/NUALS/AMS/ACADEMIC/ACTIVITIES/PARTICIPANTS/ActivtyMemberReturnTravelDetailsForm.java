package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

public class ActivtyMemberReturnTravelDetailsForm implements Serializable{
	private static final long serialVersionUID = 1L;

	private int participateRequestId;
	private String rollNo;
	private int activityCode;
	private String returnStarPoint;
	private Date returnStartDate;
	private String returnDestination;
	private Date returnEnddate;
	private String returnModeofTravel;
	private double returnEstimExp;
	private double returnAmntAdmt;
	@Column(nullable=true)
	private double returnAmntClaimed;
	
	
	
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

	public String getReturnStarPoint() {
		return returnStarPoint;
	}
	public void setReturnStarPoint(String returnStarPoint) {
		this.returnStarPoint = returnStarPoint;
	}
	public Date getReturnStartDate() {
		return returnStartDate;
	}
	public void setReturnStartDate(Date returnStartDate) {
		this.returnStartDate = returnStartDate;
	}
	public String getReturnDestination() {
		return returnDestination;
	}
	public void setReturnDestination(String returnDestination) {
		this.returnDestination = returnDestination;
	}
	public Date getReturnEnddate() {
		return returnEnddate;
	}
	public void setReturnEnddate(Date rireturnEnddate) {
		this.returnEnddate = rireturnEnddate;
	}
	public String getReturnModeofTravel() {
		return returnModeofTravel;
	}
	public void setReturnModeofTravel(String returnModeofTravel) {
		this.returnModeofTravel = returnModeofTravel;
	}
	public double getReturnEstimExp() {
		return returnEstimExp;
	}
	public void setReturnEstimExp(double returnEstimExp) {
		this.returnEstimExp = returnEstimExp;
	}
	public double getReturnAmntAdmt() {
		return returnAmntAdmt;
	}
	public void setReturnAmntAdmt(double returnAmntAdmt) {
		this.returnAmntAdmt = returnAmntAdmt;
	}
	public double getReturnAmntClaimed() {
		return returnAmntClaimed;
	}
	public void setReturnAmntClaimed(double returnAmntClaimed) {
		this.returnAmntClaimed = returnAmntClaimed;
	}
	
	
	
	
}
