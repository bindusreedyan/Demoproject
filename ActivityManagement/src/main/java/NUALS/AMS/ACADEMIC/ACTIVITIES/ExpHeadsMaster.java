package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import NUALS.AMS.ACADEMIC.CENTER.CentreFundKey;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ExpHeadsMaster {
	
	@Column(name = "expHeadId",nullable = false,  length=50)	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int expHeadId;
	
	@EmbeddedId
	private ExpHeadsKey ExpHeadsKey;
	
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
	
	
	private String status;
	
	public int getExpHeadId() {
		return expHeadId;
	}

	public void setExpHeadId(int expHeadId) {
		this.expHeadId = expHeadId;
	}

	public ExpHeadsKey getExpHeadsKey() {
		return ExpHeadsKey;
	}

	public void setExpHeadsKey(ExpHeadsKey expHeadsKey) {
		ExpHeadsKey = expHeadsKey;
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

	@Override
	public String toString() {
		return "ExpHeadsMaster [expHeadId=" + expHeadId + ", ExpHeadsKey=" + ExpHeadsKey + ", enteredBy=" + enteredBy
				+ ", enteredDate=" + enteredDate + ", allocationAmount=" + allocationAmount + ", expEditedBy="
				+ expEditedBy + ", expEditedRemarks=" + expEditedRemarks + ", expEditedDate=" + expEditedDate
				+ ", expVerifiedBy=" + expVerifiedBy + ", expVerifiedRemarks=" + expVerifiedRemarks
				+ ", expVerifiedDate=" + expVerifiedDate + ", expApprovedBy=" + expApprovedBy + ", expApprovedRemarks="
				+ expApprovedRemarks + ", expApprovedDate=" + expApprovedDate + ", status=" + status + "]";
	}

	
}
