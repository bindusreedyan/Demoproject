package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;

import javax.persistence.Column;

public class TeamParticipantAccomadationDetails {
	
	private String rollNo="";
	private String expendtureDetails="";
	private Date accomStartDate;
	private int participateRequestId;
	private String 	billNo="";
	private Date accomEndDate;
	private int  noDays=0;
	private Double dailyRate=0.0;
	private Double 	amntAdmt=0.0;
	private Double 	estimExp=0.0;
	private int activityCode;
	private String freeOfCostAccomadation="";
	@Column(nullable=true)
	private Double 	amntClaimed=0.0;
	
	public Double getAmntClaimed() {
		return amntClaimed;
	}
	public void setAmntClaimed(Double amntClaimed) {
		this.amntClaimed = amntClaimed;
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

	public String getExpendtureDetails() {
		return expendtureDetails;
	}
	public void setExpendtureDetails(String expendtureDetails) {
		this.expendtureDetails = expendtureDetails;
	}
	public Date getAccomStartDate() {
		return accomStartDate;
	}
	public void setAccomStartDate(Date accomStartDate) {
		this.accomStartDate = accomStartDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getAccomEndDate() {
		return accomEndDate;
	}
	public void setAccomEndDate(Date accomEndDate) {
		this.accomEndDate = accomEndDate;
	}
	public int getNoDays() {
		return noDays;
	}
	public void setNoDays(int noDays) {
		this.noDays = noDays;
	}
	public Double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(Double dailyRate) {
		this.dailyRate = dailyRate;
	}
	public Double getAmntAdmt() {
		return amntAdmt;
	}
	public void setAmntAdmt(Double amntAdmt) {
		this.amntAdmt = amntAdmt;
	}
	public Double getEstimExp() {
		return estimExp;
	}
	public void setEstimExp(Double estimExp) {
		this.estimExp = estimExp;
	}
	public int getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}

	public String getFreeOfCostAccomadation() {
		return freeOfCostAccomadation;
	}
	public void setFreeOfCostAccomadation(String freeOfCostAccomadation) {
		this.freeOfCostAccomadation = freeOfCostAccomadation;
	}
	

	}
