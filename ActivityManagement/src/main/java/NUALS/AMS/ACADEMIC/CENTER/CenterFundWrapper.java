package NUALS.AMS.ACADEMIC.CENTER;

import java.io.Serializable;
import java.util.Date;

public class CenterFundWrapper implements Serializable{
	private int centreCode;
	public int getCentreCode() {
		return centreCode;
	}
	public void setCentreCode(int centreCode) {
		this.centreCode = centreCode;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public double getFundAmount() {
		return fundAmount;
	}
	public void setFundAmount(double fundAmount) {
		this.fundAmount = fundAmount;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	
	
	public double getUnAssignGrant() {
		return unAssignGrant;
	}
	public void setUnAssignGrant(double unAssignGrant) {
		this.unAssignGrant = unAssignGrant;
	}


	private String finYear;
	private double fundAmount;
	private double unAssignGrant;
	private Date   enteredDate;
	
    private String fundVerifiedBy;
	
	private java.util.Date fundVerifiedDate;
	
	private String fundVerifiedRemarks;
	
	
	private String approvedBy;
	private Date approvedDate;
	
	private String approvedRemarks;
	
	private String approvedStatus;
	public String getFundVerifiedBy() {
		return fundVerifiedBy;
	}
	public void setFundVerifiedBy(String fundVerifiedBy) {
		this.fundVerifiedBy = fundVerifiedBy;
	}
	public java.util.Date getFundVerifiedDate() {
		return fundVerifiedDate;
	}
	public void setFundVerifiedDate(java.util.Date fundVerifiedDate) {
		this.fundVerifiedDate = fundVerifiedDate;
	}
	public String getFundVerifiedRemarks() {
		return fundVerifiedRemarks;
	}
	public void setFundVerifiedRemarks(String fundVerifiedRemarks) {
		this.fundVerifiedRemarks = fundVerifiedRemarks;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedRemarks() {
		return approvedRemarks;
	}
	public void setApprovedRemarks(String approvedRemarks) {
		this.approvedRemarks = approvedRemarks;
	}
	public String getApprovedStatus() {
		return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}
	
}
