package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.io.Serializable;

public class ActivityMemberFormFields implements Serializable{
	
	
	private int activityCode;
	private String rollno;
	private String contactNo;
	private int participateRequestId;
	
	private String userCode;
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public int getParticipateRequestId() {
		return participateRequestId;
	}
	public void setParticipateRequestId(int participateRequestId) {
		this.participateRequestId = participateRequestId;
	}
	public int getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	

}
