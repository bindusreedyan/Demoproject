package NUALS.AMS.ACADEMIC.CENTER;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class CenterFund {
	@EmbeddedId
	private CentreFundKey centreFundKey;
	
	@Column(nullable = false)
	private double fundAmount;
	
	
	
	public CentreFundKey getCentreFundKey() {
		return centreFundKey;
	}

	public void setCentreFundKey(CentreFundKey centreFundKey) {
		this.centreFundKey = centreFundKey;
	}

	public double getFundAmount() {
		return fundAmount;
	}

	public void setFundAmount(double fundAmount) {
		this.fundAmount = fundAmount;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public double getCommitedTotal() {
		return commitedTotal;
	}

	public void setCommitedTotal(double commitedTotal) {
		this.commitedTotal = commitedTotal;
	}

	public double getExpTillDate() {
		return expTillDate;
	}

	public void setExpTillDate(double expTillDate) {
		this.expTillDate = expTillDate;
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

	@Column
	private double currentBalance;
	
	@Column
	private double commitedTotal;
	
	@Column
	private double expTillDate;
	
	@Column(nullable=false)
	private String status;
	
	
	private String enteredBy;
	private Date enteredDate;
	
	
	
	private String fundVerifiedBy;
	
	private java.util.Date fundVerifiedDate;
	
	private String fundVerifiedRemarks;
	
	
	private String approvedBy;
	private Date approvedDate;
	
	private String approvedRemarks;
	
	
	private String approvedStatus;
	
	 public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	@Column(name="unAssignGrant",columnDefinition = "float default 0")
	 private double unAssignGrant;
	 
	 @Column(name="unAssignGrantBal",columnDefinition = "float default 0")
	 private double unAssignGrantBal;



	public double getUnAssignGrant() {
		return unAssignGrant;
	}

	public void setUnAssignGrant(double unAssignGrant) {
		this.unAssignGrant = unAssignGrant;
	}

	public double getUnAssignGrantBal() {
		return unAssignGrantBal;
	}

	public void setUnAssignGrantBal(double unAssignGrantBal) {
		this.unAssignGrantBal = unAssignGrantBal;
	}

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

	@Override
	public String toString() {
		return "CenterFund [centreFundKey=" + centreFundKey + ", fundAmount=" + fundAmount + ", currentBalance="
				+ currentBalance + ", commitedTotal=" + commitedTotal + ", expTillDate=" + expTillDate + ", status="
				+ status + ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", fundVerifiedBy="
				+ fundVerifiedBy + ", fundVerifiedDate=" + fundVerifiedDate + ", fundVerifiedRemarks="
				+ fundVerifiedRemarks + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate
				+ ", approvedRemarks=" + approvedRemarks + ", approvedStatus=" + approvedStatus + ", unAssignGrant="
				+ unAssignGrant + ", unAssignGrantBal=" + unAssignGrantBal + ", getCentreFundKey()="
				+ getCentreFundKey() + ", getFundAmount()=" + getFundAmount() + ", getCurrentBalance()="
				+ getCurrentBalance() + ", getCommitedTotal()=" + getCommitedTotal() + ", getExpTillDate()="
				+ getExpTillDate() + ", getStatus()=" + getStatus() + ", getEnteredBy()=" + getEnteredBy()
				+ ", getEnteredDate()=" + getEnteredDate() + ", getApprovedBy()=" + getApprovedBy()
				+ ", getApprovedDate()=" + getApprovedDate() + ", getApprovedRemarks()=" + getApprovedRemarks()
				+ ", getApprovedStatus()=" + getApprovedStatus() + ", getUnAssignGrant()=" + getUnAssignGrant()
				+ ", getUnAssignGrantBal()=" + getUnAssignGrantBal() + ", getFundVerifiedBy()=" + getFundVerifiedBy()
				+ ", getFundVerifiedDate()=" + getFundVerifiedDate() + ", getFundVerifiedRemarks()="
				+ getFundVerifiedRemarks() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
