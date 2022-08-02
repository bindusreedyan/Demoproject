package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

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
public class BudgetHead {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int budHeadId;
	
	@NotBlank
	@Column(nullable = false,unique = true)
	private String budHeadName;
	
	
	@NotBlank
	@Column(nullable = false)
	private String budStatus;
	
	
	private String EnteredBy;
	
	private Date EnteredDate;

	public int getBudHeadId() {
		return budHeadId;
	}

	public void setBudHeadId(int budHeadId) {
		this.budHeadId = budHeadId;
	}

	public String getBudHeadName() {
		return budHeadName;
	}

	public void setBudHeadName(String budHeadName) {
		this.budHeadName = budHeadName;
	}

	public String getBudStatus() {
		return budStatus;
	}

	public void setBudStatus(String budStatus) {
		this.budStatus = budStatus;
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

	public BudgetHead(String budHeadId) {
		super();
		this.budHeadId = Integer.parseInt(budHeadId);
	}

	public BudgetHead() {
		super();
	}

	@Override
	public String toString() {
		return "BudgetHead [budHeadId=" + budHeadId + ", budHeadName=" + budHeadName + ", budStatus=" + budStatus
				+ ", EnteredBy=" + EnteredBy + ", EnteredDate=" + EnteredDate + "]";
	}
	
	
	
}
