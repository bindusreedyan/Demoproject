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
public class ParticipationEsGeneration {
	
	   @Id
       @GeneratedValue(strategy=GenerationType.AUTO)
       private int orderGenId;

       @NotNull
		@ManyToOne
		@JoinColumn(name="activityCode",unique=true)
	    private ActivityMaster ac;
     
     
        private int participateRequestid;
     
		private String sectionbHeading;
		
		private double estimExp;
		
	//	private double advReleased;
		
		private double sanctionedAmount;


		@Column(name="abstractDetails",length=2000,nullable=false)
       private String abstractDetails;
     
       private String esNumberPrefix;
     
       private String esrefer;
     
       private String orderHeading;
     
       @Column(name="esOrderContent",length=2000,nullable=false)
       private String esOrderContent;
     
       @Column(name="copyTo",length=500,nullable=false)
       private String copyTo;
   
      private String orderType;
     
      private String orderGenratedBy;
      private java.util.Date orderGeneratedDate;
     
      @Column(name="budget",length=2000,nullable=true)
      private String budgetInvolved;

	public int getOrderGenId() {
		return orderGenId;
	}

	public void setOrderGenId(int orderGenId) {
		this.orderGenId = orderGenId;
	}

	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

	public int getParticipateRequestid() {
		return participateRequestid;
	}

	public void setParticipateRequestid(int participateRequestid) {
		this.participateRequestid = participateRequestid;
	}

	public String getSectionbHeading() {
		return sectionbHeading;
	}

	public void setSectionbHeading(String sectionbHeading) {
		this.sectionbHeading = sectionbHeading;
	}

	public double getEstimExp() {
		return estimExp;
	}

	public void setEstimExp(double estimExp) {
		this.estimExp = estimExp;
	}

	public double getSanctionedAmount() {
		return sanctionedAmount;
	}

	public void setSanctionedAmount(double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}

	public String getAbstractDetails() {
		return abstractDetails;
	}

	public void setAbstractDetails(String abstractDetails) {
		this.abstractDetails = abstractDetails;
	}

	public String getEsNumberPrefix() {
		return esNumberPrefix;
	}

	public void setEsNumberPrefix(String esNumberPrefix) {
		this.esNumberPrefix = esNumberPrefix;
	}

	public String getEsrefer() {
		return esrefer;
	}

	public void setEsrefer(String esrefer) {
		this.esrefer = esrefer;
	}

	public String getOrderHeading() {
		return orderHeading;
	}

	public void setOrderHeading(String orderHeading) {
		this.orderHeading = orderHeading;
	}

	public String getEsOrderContent() {
		return esOrderContent;
	}

	public void setEsOrderContent(String esOrderContent) {
		this.esOrderContent = esOrderContent;
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
      

}
