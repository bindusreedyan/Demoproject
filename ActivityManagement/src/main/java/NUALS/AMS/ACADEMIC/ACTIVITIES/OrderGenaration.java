package NUALS.AMS.ACADEMIC.ACTIVITIES;

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

@Entity
@EntityListeners(AuditingEntityListener.class)
public class OrderGenaration {
	       @Id
	       @GeneratedValue(strategy=GenerationType.AUTO)
	       private int orderGenId;
	
	        @NotNull
			@ManyToOne
			@JoinColumn(name="activityCode")
		    private ActivityMaster ac;
            
	  /*      @NotNull
	    	@ManyToOne
	    	@JoinColumn(name="budHeadId",unique=true)
	        private  BudgetHead bh;*/
	        

			private String sectionbHeading;
			
			
			private double estimExp;
			
			private double advReleased;
	        
			@Column(name="abstractDetails",length=2000,nullable=false)
	        private String abstractDetails;
	        
	        private String asNumberPrefix;
	        
	        private String asrefer;
	        
	        private String orderHeading;
	        
	        @Column(name="asOrderContent",length=2000,nullable=false)
	        private String asOrderContent;
	        
	        
	        
	        @Column(name="copyTo",length=500,nullable=false)
	        private String copyTo;
	        
	        private String externlFundType;
	        
	        private String externlFundAmnt;
	        
	        private String orderType;
	        
	        private String orderGenratedBy;
	        private java.util.Date orderGeneratedDate;
	        
	        @Column(name="budget",length=2000,nullable=true)
	        private String budgetInvolved;

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

			public String getSectionbHeading() {
				return sectionbHeading;
			}

			public void setSectionbHeading(String sectionbHeading) {
				this.sectionbHeading = sectionbHeading;
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
	        
	        
			public String getAsrefer() {
				return asrefer;
			}

			public void setAsrefer(String asrefer) {
				this.asrefer = asrefer;
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


			public String getBudgetInvolved() {
				return budgetInvolved;
			}

			public void setBudgetInvolved(String budgetInvolved) {
				this.budgetInvolved = budgetInvolved;
			}

			@Override
			public String toString() {
				return "OrderGenaration [orderGenId=" + orderGenId + ", ac=" + ac + ", sectionbHeading="
						+ sectionbHeading + ", estimExp=" + estimExp + ", advReleased=" + advReleased
						+ ", abstractDetails=" + abstractDetails + ", asNumberPrefix=" + asNumberPrefix + ", asrefer="
						+ asrefer + ", orderHeading=" + orderHeading + ", asOrderContent=" + asOrderContent
						+ ", copyTo=" + copyTo + ", externlFundType=" + externlFundType + ", externlFundAmnt="
						+ externlFundAmnt + ", orderType=" + orderType + ", orderGenratedBy=" + orderGenratedBy
						+ ", orderGeneratedDate=" + orderGeneratedDate + ", budgetInvolved=" + budgetInvolved + "]";
			}
			
			
	        
}
