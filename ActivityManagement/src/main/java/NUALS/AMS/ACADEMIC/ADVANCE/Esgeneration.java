package NUALS.AMS.ACADEMIC.ADVANCE;

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
public class Esgeneration {

	    @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private int orderGenId;

        @NotNull
		@ManyToOne
		@JoinColumn(name="activityCode")
	    private ActivityMaster ac;
      
      
        @NotNull
		@ManyToOne
		@JoinColumn(name="setId")
	    private AdvExpSettlement ae;
      
		private String sectionbHeading;
		
		private double estimExp;
		
		private double advReleased;
		
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
      
        private String externlFundType;
      
        private String externlFundAmnt;
      
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

	public AdvExpSettlement getAe() {
		return ae;
	}

	public void setAe(AdvExpSettlement ae) {
		this.ae = ae;
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

	public double getAdvReleased() {
		return advReleased;
	}

	public void setAdvReleased(double advReleased) {
		this.advReleased = advReleased;
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

	public String getExternlFundType() {
		return externlFundType;
	}

	public void setExternlFundType(String externlFundType) {
		this.externlFundType = externlFundType;
	}

	public String getExternlFundAmnt() {
		return externlFundAmnt;
	}

	public void setExternlFundAmnt(String externlFundAmnt) {
		this.externlFundAmnt = externlFundAmnt;
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

	@Override
	public String toString() {
		return "Esgeneration [orderGenId=" + orderGenId + ", ac=" + ac + ", ae=" + ae + ", sectionbHeading="
				+ sectionbHeading + ", estimExp=" + estimExp + ", advReleased=" + advReleased + ", sanctionedAmount="
				+ sanctionedAmount + ", abstractDetails=" + abstractDetails + ", esNumberPrefix=" + esNumberPrefix
				+ ", esrefer=" + esrefer + ", orderHeading=" + orderHeading + ", esOrderContent=" + esOrderContent
				+ ", copyTo=" + copyTo + ", externlFundType=" + externlFundType + ", externlFundAmnt=" + externlFundAmnt
				+ ", orderType=" + orderType + ", orderGenratedBy=" + orderGenratedBy + ", orderGeneratedDate="
				+ orderGeneratedDate + ", budgetInvolved=" + budgetInvolved + "]";
	}


	   
       
}
