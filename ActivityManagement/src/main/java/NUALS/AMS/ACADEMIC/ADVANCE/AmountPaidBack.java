package NUALS.AMS.ACADEMIC.ADVANCE;

import java.sql.Date;

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
import NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS.ActivityParticipateMasterData;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AmountPaidBack {
	
	     @Id
	     @Column(name = "universityPaymentBackId",nullable = false,  length=50)	
	     @GeneratedValue(strategy=GenerationType.AUTO)
	     private int universityPaymentBackId;

        @NotNull
		@ManyToOne
		@JoinColumn(name="activityCode")
	    private ActivityMaster ac;
        
        @NotNull
    	@ManyToOne
    	@JoinColumn(name="setId")
        private AdvExpSettlement setId;
        
        public AdvExpSettlement getSetId() {
			return setId;
		}

		public void setSetId(AdvExpSettlement setId) {
			this.setId = setId;
		}

		@Column(name = "paidBackDescription", nullable = true, length=5000)
        private String paidBackDescription;
        
        private String costCenterCode;
        
        @Column(name = "voucharNo",nullable = true, unique=true, length=5000)
        private String voucharNo;
        
        private Date paymentDate;
        
        private Double amountPaidBack;
        
        
    	private String enteredBy;
    	private java.util.Date enteteredDate;
    	private String enteredRemarks;

		public int getUniversityPaymentBackId() {
			return universityPaymentBackId;
		}

		public void setUniversityPaymentBackId(int universityPaymentBackId) {
			this.universityPaymentBackId = universityPaymentBackId;
		}

		public String getEnteredBy() {
			return enteredBy;
		}

		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}

		public java.util.Date getEnteteredDate() {
			return enteteredDate;
		}

		public void setEnteteredDate(java.util.Date enteteredDate) {
			this.enteteredDate = enteteredDate;
		}

		public String getEnteredRemarks() {
			return enteredRemarks;
		}

		public void setEnteredRemarks(String enteredRemarks) {
			this.enteredRemarks = enteredRemarks;
		}

		public ActivityMaster getAc() {
			return ac;
		}

		public void setAc(ActivityMaster ac) {
			this.ac = ac;
		}

		public String getPaidBackDescription() {
			return paidBackDescription;
		}

		public void setPaidBackDescription(String paidBackDescription) {
			this.paidBackDescription = paidBackDescription;
		}

		public String getCostCenterCode() {
			return costCenterCode;
		}

		public void setCostCenterCode(String costCenterCode) {
			this.costCenterCode = costCenterCode;
		}

		public String getVoucharNo() {
			return voucharNo;
		}

		public void setVoucharNo(String voucharNo) {
			this.voucharNo = voucharNo;
		}

		public Date getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
		}

		public Double getAmountPaidBack() {
			return amountPaidBack;
		}

		public void setAmountPaidBack(Double amountPaidBack) {
			this.amountPaidBack = amountPaidBack;
		}
        
        

}
