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
public class ProgramApprovalAdvancePayment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int programApprovalAdvancePaymentId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="activityCode")
    private ActivityMaster ac;
	
	String advanceRequestId;
	
	private double advancePaid;
	
	private String finYear;
	
	private String voucharNo;
	
	private java.sql.Date voucharDate;
	
	private String enteredBy;
	
	private java.util.Date enteredDate;
	
	private String enteredRemarks;
	
	private String purpose;
	
	@Column(name="settleStatus",columnDefinition = "varchar(15) default 'Not Settled'")
	private String settleStatus;

    @Column(name="settledAmount",columnDefinition = "float default 0")
    private double settledAmount;


    @Column(name="settlementRemarks")
	private String settlementRemarks;
    
    
    private String settledBy;
    
    private java.util.Date settledDate;

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}

	public int getProgramApprovalAdvancePaymentId() {
		return programApprovalAdvancePaymentId;
	}

	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getVoucharNo() {
		return voucharNo;
	}

	public void setVoucharNo(String voucharNo) {
		this.voucharNo = voucharNo;
	}

	public java.sql.Date getVoucharDate() {
		return voucharDate;
	}

	public void setVoucharDate(java.sql.Date voucharDate) {
		this.voucharDate = voucharDate;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public java.util.Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(java.util.Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public String getEnteredRemarks() {
		return enteredRemarks;
	}

	public void setEnteredRemarks(String enteredRemarks) {
		this.enteredRemarks = enteredRemarks;
	}

	public double getAdvancePaid() {
		return advancePaid;
	}

	public void setAdvancePaid(double advancePaid) {
		this.advancePaid = advancePaid;
	}
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getAdvanceRequestId() {
		return advanceRequestId;
	}

	public void setAdvanceRequestId(String advanceRequestId) {
		this.advanceRequestId = advanceRequestId;
	}

	public void setProgramApprovalAdvancePaymentId(int programApprovalAdvancePaymentId) {
		this.programApprovalAdvancePaymentId = programApprovalAdvancePaymentId;
	}

	public double getSettledAmount() {
		return settledAmount;
	}

	public void setSettledAmount(double settledAmount) {
		this.settledAmount = settledAmount;
	}

	public String getSettlementRemarks() {
		return settlementRemarks;
	}

	public void setSettlementRemarks(String settlementRemarks) {
		this.settlementRemarks = settlementRemarks;
	}

	public String getSettledBy() {
		return settledBy;
	}

	public void setSettledBy(String settledBy) {
		this.settledBy = settledBy;
	}

	public java.util.Date getSettledDate() {
		return settledDate;
	}

	public void setSettledDate(java.util.Date settledDate) {
		this.settledDate = settledDate;
	}

	@Override
	public String toString() {
		return "ProgramApprovalAdvancePayment [programApprovalAdvancePaymentId=" + programApprovalAdvancePaymentId
				+ ", ac=" + ac + ", advanceRequestId=" + advanceRequestId + ", advancePaid=" + advancePaid
				+ ", finYear=" + finYear + ", voucharNo=" + voucharNo + ", voucharDate=" + voucharDate + ", enteredBy="
				+ enteredBy + ", enteredDate=" + enteredDate + ", enteredRemarks=" + enteredRemarks + ", purpose="
				+ purpose + ", settleStatus=" + settleStatus + ", settledAmount=" + settledAmount
				+ ", settlementRemarks=" + settlementRemarks + ", settledBy=" + settledBy + ", settledDate="
				+ settledDate + "]";
	}
	
	

}
