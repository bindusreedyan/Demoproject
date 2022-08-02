package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;
import java.util.Date;

public class AdvanceSettlementClass implements Serializable{
	
	private int programApprovalAdvancePaymentId;
	public int getProgramApprovalAdvancePaymentId() {
		return programApprovalAdvancePaymentId;
	}
	public void setProgramApprovalAdvancePaymentId(int programApprovalAdvancePaymentId) {
		this.programApprovalAdvancePaymentId = programApprovalAdvancePaymentId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getVoucharNo() {
		return voucharNo;
	}
	public void setVoucharNo(String voucharNo) {
		this.voucharNo = voucharNo;
	}
	public Date getVoucharDate() {
		return voucharDate;
	}
	public void setVoucharDate(Date voucharDate) {
		this.voucharDate = voucharDate;
	}
	public double getAdvancePaid() {
		return advancePaid;
	}
	public void setAdvancePaid(double advancePaid) {
		this.advancePaid = advancePaid;
	}
	public double getSettledamount() {
		return settledamount;
	}
	public void setSettledamount(double settledamount) {
		this.settledamount = settledamount;
	}
	public String getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	public String getSettlementRemarks() {
		return settlementRemarks;
	}
	public void setSettlementRemarks(String settlementRemarks) {
		this.settlementRemarks = settlementRemarks;
	}
	private String purpose;
	private String voucharNo;
	private Date voucharDate;
	private double advancePaid;
	
	private double settledamount;
	private String settleStatus;
	private String settlementRemarks;
	
	
	

}
