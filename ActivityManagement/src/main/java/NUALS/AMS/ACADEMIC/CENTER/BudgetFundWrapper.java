package NUALS.AMS.ACADEMIC.CENTER;

import java.util.Date;

public class BudgetFundWrapper {
	
	private int budHeadId;
	private String finYear;
	private double fundAmount;
	private String enteredBy;
	private Date enteredDate;
	private String enteredRemark;

	public int getBudHeadId() {
		return budHeadId;
	}

	public void setBudHeadId(int budHeadId) {
		this.budHeadId = budHeadId;
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

	public String getEnteredRemark() {
		return enteredRemark;
	}

	public void setEnteredRemark(String enteredRemark) {
		this.enteredRemark = enteredRemark;
	}
	
	

}
