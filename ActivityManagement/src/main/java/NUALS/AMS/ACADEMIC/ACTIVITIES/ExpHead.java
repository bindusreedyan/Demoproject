package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ExpHead {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int headId;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String expHeadName;
	
	
	@NotBlank
	@Column(nullable = false)
	private String expHeadStatus;
	
	
	private String EnteredBy;
	
	private Date EnteredDate;
	
	
  private String approvedRemark;
  private String verifiedRemark;

	public int getHeadId() {
		return headId;
	}


	public void setHeadId(int headId) {
		this.headId = headId;
	}


	public String getExpHeadName() {
		return expHeadName;
	}


	public void setExpHeadName(String expHeadName) {
		this.expHeadName = expHeadName;
	}


	public String getExpHeadStatus() {
		return expHeadStatus;
	}


	public void setExpHeadStatus(String expHeadStatus) {
		this.expHeadStatus = expHeadStatus;
	}


	public String getEnteredBy() {
		return EnteredBy;
	}


	public void setEnteredBy(String enteredBy) {
		EnteredBy = enteredBy;
	}


	public Date getEnteredDate() {
		return EnteredDate;
	}


	public void setEnteredDate(Date enteredDate) {
		EnteredDate = enteredDate;
	}


	public ExpHead(String headId) {
		super();
		this.headId = Integer.parseInt(headId);
	}
	
	public ExpHead() {
		super();
		
	}


	public String getApprovedRemark() {
		return approvedRemark;
	}


	public void setApprovedRemark(String approvedRemark) {
		this.approvedRemark = approvedRemark;
	}


	public String getVerifiedRemark() {
		return verifiedRemark;
	}


	public void setVerifiedRemark(String verifiedRemark) {
		this.verifiedRemark = verifiedRemark;
	}


	@Override
	public String toString() {
		return "ExpHead [headId=" + headId + ", expHeadName=" + expHeadName + ", expHeadStatus=" + expHeadStatus
				+ ", EnteredBy=" + EnteredBy + ", EnteredDate=" + EnteredDate + ", getHeadId()=" + getHeadId()
				+ ", getExpHeadName()=" + getExpHeadName() + ", getExpHeadStatus()=" + getExpHeadStatus()
				+ ", getEnteredBy()=" + getEnteredBy() + ", getEnteredDate()=" + getEnteredDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
