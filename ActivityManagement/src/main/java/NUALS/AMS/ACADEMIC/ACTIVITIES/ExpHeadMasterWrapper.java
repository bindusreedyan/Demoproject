package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;
import java.util.Date;

public class ExpHeadMasterWrapper  implements Serializable{
	
	private String description;
	private String finYear;
	private String status;
	
	private String enteredBy;
	
	private Date enteredDate;
	
	private double allocationAmount;
	
	
	
private String expEditedBy;
	
	private String expEditedRemarks;
	
	
	private Date expEditedDate;
	
	
	
    private String expVerifiedBy;
	
	private String expVerifiedRemarks;
	
	private Date expVerifiedDate;
	
    private String expApprovedBy;
		
	private String expApprovedRemarks;
		
	private Date expApprovedDate;
	
	
	private String approvedStatus;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public double getAllocationAmount() {
		return allocationAmount;
	}

	public void setAllocationAmount(double allocationAmount) {
		this.allocationAmount = allocationAmount;
	}

	public String getExpEditedBy() {
		return expEditedBy;
	}

	public void setExpEditedBy(String expEditedBy) {
		this.expEditedBy = expEditedBy;
	}

	public String getExpEditedRemarks() {
		return expEditedRemarks;
	}

	public void setExpEditedRemarks(String expEditedRemarks) {
		this.expEditedRemarks = expEditedRemarks;
	}

	public Date getExpEditedDate() {
		return expEditedDate;
	}

	public void setExpEditedDate(Date expEditedDate) {
		this.expEditedDate = expEditedDate;
	}

	public String getExpVerifiedBy() {
		return expVerifiedBy;
	}

	public void setExpVerifiedBy(String expVerifiedBy) {
		this.expVerifiedBy = expVerifiedBy;
	}

	public String getExpVerifiedRemarks() {
		return expVerifiedRemarks;
	}

	public void setExpVerifiedRemarks(String expVerifiedRemarks) {
		this.expVerifiedRemarks = expVerifiedRemarks;
	}

	public Date getExpVerifiedDate() {
		return expVerifiedDate;
	}

	public void setExpVerifiedDate(Date expVerifiedDate) {
		this.expVerifiedDate = expVerifiedDate;
	}

	public String getExpApprovedBy() {
		return expApprovedBy;
	}

	public void setExpApprovedBy(String expApprovedBy) {
		this.expApprovedBy = expApprovedBy;
	}

	public String getExpApprovedRemarks() {
		return expApprovedRemarks;
	}

	public void setExpApprovedRemarks(String expApprovedRemarks) {
		this.expApprovedRemarks = expApprovedRemarks;
	}

	public Date getExpApprovedDate() {
		return expApprovedDate;
	}

	public void setExpApprovedDate(Date expApprovedDate) {
		this.expApprovedDate = expApprovedDate;
	}

	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

}
