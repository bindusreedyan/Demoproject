package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityParticipateMember {
	
	@EmbeddedId
	 private ActivityParticipationRequestKey activityParticptnRequest;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="particiaptionRequestId")
      private ActivityParticipateMasterData pR;
	
	 public ActivityParticipateMasterData getpR() {
		return pR;
	}

	public void setpR(ActivityParticipateMasterData pR) {
		this.pR = pR;
	}

	public String getReturnStartPoint() {
		return returnStartPoint;
	}

	public void setReturnStartPoint(String returnStartPoint) {
		this.returnStartPoint = returnStartPoint;
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

	public Date getReturnEndDate() {
		return returnEndDate;
	}

	public void setReturnEndDate(Date returnEndDate) {
		this.returnEndDate = returnEndDate;
	}

	public String getReturnModeofTravel() {
		return returnModeofTravel;
	}

	public void setReturnModeofTravel(String returnModeofTravel) {
		this.returnModeofTravel = returnModeofTravel;
	}

	public double getReturnEstExpenditure() {
		return returnEstExpenditure;
	}

	public void setReturnEstExpenditure(double returnEstExpenditure) {
		this.returnEstExpenditure = returnEstExpenditure;
	}

	public double getReturnAmountAdmitted() {
		return returnAmountAdmitted;
	}

	public void setReturnAmountAdmitted(double returnAmountAdmitted) {
		this.returnAmountAdmitted = returnAmountAdmitted;
	}

	public String getFreeOfCostAccomadation() {
		return freeOfCostAccomadation;
	}

	public void setFreeOfCostAccomadation(String freeOfCostAccomadation) {
		this.freeOfCostAccomadation = freeOfCostAccomadation;
	}

	public String getAccomodationExpHead() {
		return AccomodationExpHead;
	}

	public void setAccomodationExpHead(String accomodationExpHead) {
		AccomodationExpHead = accomodationExpHead;
	}

	public Date getAccmdationStartDate() {
		return AccmdationStartDate;
	}

	public void setAccmdationStartDate(Date accmdationStartDate) {
		AccmdationStartDate = accmdationStartDate;
	}

	public Date getAccmdationEndDate() {
		return AccmdationEndDate;
	}

	public void setAccmdationEndDate(Date accmdationEndDate) {
		AccmdationEndDate = accmdationEndDate;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public double getAccmdtionEstimExpenditure() {
		return accmdtionEstimExpenditure;
	}

	public void setAccmdtionEstimExpenditure(double accmdtionEstimExpenditure) {
		this.accmdtionEstimExpenditure = accmdtionEstimExpenditure;
	}

	public double getAmountAdmitted() {
		return amountAdmitted;
	}

	public void setAmountAdmitted(double amountAdmitted) {
		this.amountAdmitted = amountAdmitted;
	}

	
	private String userCode;
	
	 public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	private String contactNumber;
	 private String onwardStartPoint;
	 private Date onwardStartDate;
	 private String onwardDestination;
	 private Date onwardEndDate;
	 private String onwardModeofTravel;
	 private double onWardEstExpenditure;
	 private double onwardAmountAdmitted;
	
	 private double onwardAmountClaimed;
	 
	 private String returnStartPoint;
	 private Date returnStartDate;
	 private String returnDestination;
	 private Date returnEndDate;
	 private String returnModeofTravel;
	 private double returnEstExpenditure;
	 private double returnAmountAdmitted;
	 private double returnAmountClaimed;
	 
	 public double getReturnAmountClaimed() {
		return returnAmountClaimed;
	}

	public void setReturnAmountClaimed(double returnAmountClaimed) {
		this.returnAmountClaimed = returnAmountClaimed;
	}

	public double getAmountClaimed() {
		return amountClaimed;
	}

	public void setAmountClaimed(double amountClaimed) {
		this.amountClaimed = amountClaimed;
	}


	private String freeOfCostAccomadation;
	 private String AccomodationExpHead;
	 private Date AccmdationStartDate;
	 private Date AccmdationEndDate;
	 
	 private int noOfDays;
	 private double dailyRate;
	 private String billNo;
	 private double accmdtionEstimExpenditure;
	 private double amountAdmitted;
	 private double amountClaimed;
	 
	 
	 


	public String getOnwardStartPoint() {
		return this.onwardStartPoint;
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

	public double getOnWardEstExpenditure() {
		return onWardEstExpenditure;
	}

	public void setOnWardEstExpenditure(double onWardEstExpenditure) {
		this.onWardEstExpenditure = onWardEstExpenditure;
	}

	public double getOnwardAmountAdmitted() {
		return onwardAmountAdmitted;
	}

	public void setOnwardAmountAdmitted(double onwardAmountAdmitted) {
		this.onwardAmountAdmitted = onwardAmountAdmitted;
	}

	public ActivityParticipationRequestKey getActivityParticptnRequest() {
		return activityParticptnRequest;
	}

	public void setActivityParticptnRequest(ActivityParticipationRequestKey activityParticptnRequest) {
		this.activityParticptnRequest = activityParticptnRequest;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "ActivityParticipateMember [activityParticptnRequest=" + activityParticptnRequest + ", pR=" + pR
				+ ", contactNumber=" + contactNumber + ", onwardStartPoint=" + onwardStartPoint + ", onwardStartDate="
				+ onwardStartDate + ", onwardDestination=" + onwardDestination + ", onwardEndDate=" + onwardEndDate
				+ ", onwardModeofTravel=" + onwardModeofTravel + ", onWardEstExpenditure=" + onWardEstExpenditure
				+ ", onwardAmountAdmitted=" + onwardAmountAdmitted + ", returnStartPoint=" + returnStartPoint
				+ ", returnStartDate=" + returnStartDate + ", returnDestination=" + returnDestination
				+ ", returnEndDate=" + returnEndDate + ", returnModeofTravel=" + returnModeofTravel
				+ ", returnEstExpenditure=" + returnEstExpenditure + ", returnAmountAdmitted=" + returnAmountAdmitted
				+ ", freeOfCostAccomadation=" + freeOfCostAccomadation + ", AccomodationExpHead=" + AccomodationExpHead
				+ ", AccmdationStartDate=" + AccmdationStartDate + ", AccmdationEndDate=" + AccmdationEndDate
				+ ", noOfDays=" + noOfDays + ", dailyRate=" + dailyRate + ", billNo=" + billNo
				+ ", accmdtionEstimExpenditure=" + accmdtionEstimExpenditure + ", amountAdmitted=" + amountAdmitted
				+ "]";
	}

	public double getOnwardAmountClaimed() {
		return onwardAmountClaimed;
	}

	public void setOnwardAmountClaimed(double onwardAmountClaimed) {
		this.onwardAmountClaimed = onwardAmountClaimed;
	}
	
	
	
	

}
