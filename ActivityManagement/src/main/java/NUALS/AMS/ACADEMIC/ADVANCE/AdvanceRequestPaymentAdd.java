package NUALS.AMS.ACADEMIC.ADVANCE;

import java.io.Serializable;

public class AdvanceRequestPaymentAdd implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int advanceRequestId;
	
	String voucharNo;
	
	java.sql.Date voucharDate;
	
    private double advancePaid;
	
	private String finYear;
	
    private String enteredBy;
 	
	private java.util.Date enteredDate;
	
	private String enteredRemarks;
	
	private String purpose;

	public int getAdvanceRequestId() {
		return advanceRequestId;
	}

	public void setAdvanceRequestId(int advanceRequestId) {
		this.advanceRequestId = advanceRequestId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getAdvancePaid() {
		return advancePaid;
	}

	public void setAdvancePaid(double advancePaid) {
		this.advancePaid = advancePaid;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}



}
