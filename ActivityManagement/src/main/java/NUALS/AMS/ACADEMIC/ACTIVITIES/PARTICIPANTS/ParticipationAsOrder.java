package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ParticipationAsOrder {
	
	  @Id
      @GeneratedValue(strategy=GenerationType.AUTO)
      private int orderGenId;
      
	  private int  activityCode;
	  
	  private int participationRequestId;
       
	  public int getParticipationRequestId() {
		return participationRequestId;
	}

	public void setParticipationRequestId(int participationRequestId) {
		this.participationRequestId = participationRequestId;
	}

	private String sectionbHeading;
		
		
	  public int getOrderGenId() {
		return orderGenId;
	}

	public void setOrderGenId(int orderGenId) {
		this.orderGenId = orderGenId;
	}

	public int getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}

	public String getSectionbHeading() {
		return sectionbHeading;
	}

	public void setSectionbHeading(String sectionbHeading) {
		this.sectionbHeading = sectionbHeading;
	}

	public double getAsgrantedAmount() {
		return asgrantedAmount;
	}

	public void setAsgrantedAmount(double asgrantedAmount) {
		this.asgrantedAmount = asgrantedAmount;
	}

	public String getAbstractDetails() {
		return abstractDetails;
	}

	public void setAbstractDetails(String abstractDetails) {
		this.abstractDetails = abstractDetails;
	}

	public String getAsNumberPrefix() {
		return asNumberPrefix;
	}

	public void setAsNumberPrefix(String asNumberPrefix) {
		this.asNumberPrefix = asNumberPrefix;
	}

	public String getAsrefer() {
		return asrefer;
	}

	public void setAsrefer(String asrefer) {
		this.asrefer = asrefer;
	}

	public String getOrderHeading() {
		return orderHeading;
	}

	public void setOrderHeading(String orderHeading) {
		this.orderHeading = orderHeading;
	}

	public String getAsOrderContent() {
		return asOrderContent;
	}

	public void setAsOrderContent(String asOrderContent) {
		this.asOrderContent = asOrderContent;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderGenratedBy() {
		return orderGenratedBy;
	}

	public void setOrderGenratedBy(String orderGenratedBy) {
		this.orderGenratedBy = orderGenratedBy;
	}

	public java.util.Date getOrderGeneratedDate() {
		return orderGeneratedDate;
	}

	public void setOrderGeneratedDate(java.util.Date orderGeneratedDate) {
		this.orderGeneratedDate = orderGeneratedDate;
	}

	public String getBudgetInvolved() {
		return budgetInvolved;
	}

	public void setBudgetInvolved(String budgetInvolved) {
		this.budgetInvolved = budgetInvolved;
	}

	private double asgrantedAmount;
	  
	  @Column(name="abstractDetails",length=2000,nullable=false)
      private String abstractDetails;
       
      private String asNumberPrefix;
       
      private String asrefer;
       
      private String orderHeading;
       
       @Column(name="asOrderContent",length=2000,nullable=false)
       private String asOrderContent;
       
       
       @Column(name="copyTo",length=500,nullable=false)
       private String copyTo;
       
       private String orderType;
       
       private String orderGenratedBy;
       private java.util.Date orderGeneratedDate;
       
       @Column(name="budget",length=2000,nullable=true)
       private String budgetInvolved;


}
