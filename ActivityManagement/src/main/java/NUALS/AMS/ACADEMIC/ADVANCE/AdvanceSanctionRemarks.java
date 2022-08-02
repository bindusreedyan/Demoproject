package NUALS.AMS.ACADEMIC.ADVANCE;

import java.io.Serializable;

public class AdvanceSanctionRemarks implements Serializable{
	
	
	private int settlementId;
	
	private String enteredBy;
	
	
	private java.util.Date enteredDate;
	
	private String enteredRemarks;
	
	
	
	private String officeRecommendationRemark;
	
	private String officeRecommendedBy;
	
	private java.util.Date officeRecommendedDate;
	
	
     private String adminRecommendationRemark;
	
	private String adminRecommendedBy;
	
	private java.util.Date adminRecommendedDate;
	
	
     private String financialRecommendationRemark;
	
	private String financialRecommendedBy;
	
	private java.util.Date financialRecommendedDate;
	
	
    private String finalRecommendationRemark;
	
	private String finalRecommendedBy;
	
	private java.util.Date finalRecommendedDate;
	
	private String recommendationStatus;
	
	private double sanctionedAmnt;

	private String financialOfficeRecommendationRemark;
	
    private String finalOfficeRecommendationRemark;
    
    private java.util.Date finalOfficeRecommendedDate;
    
    private String finalOfficeRecommendedBy;
    
    private java.util.Date financialOfficeRecommendedDate;
    
    
	public String getFinalOfficeRecommendationRemark() {
		return finalOfficeRecommendationRemark;
	}

	public void setFinalOfficeRecommendationRemark(String finalOfficeRecommendationRemark) {
		this.finalOfficeRecommendationRemark = finalOfficeRecommendationRemark;
	}

	public java.util.Date getFinancialOfficeRecommendedDate() {
		return financialOfficeRecommendedDate;
	}

	public void setFinancialOfficeRecommendedDate(java.util.Date financialOfficeRecommendedDate) {
		this.financialOfficeRecommendedDate = financialOfficeRecommendedDate;
	}

	public double getSanctionedAmnt() {
		return sanctionedAmnt;
	}

	public void setSanctionedAmnt(double sanctionedAmnt) {
		this.sanctionedAmnt = sanctionedAmnt;
	}

	public String getRecommendationStatus() {
		return recommendationStatus;
	}

	public void setRecommendationStatus(String recommendationStatus) {
		this.recommendationStatus = recommendationStatus;
	}

	public int getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(int settlementId) {
		this.settlementId = settlementId;
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

	public String getOfficeRecommendationRemark() {
		return officeRecommendationRemark;
	}

	public void setOfficeRecommendationRemark(String officeRecommendationRemark) {
		this.officeRecommendationRemark = officeRecommendationRemark;
	}

	public String getOfficeRecommendedBy() {
		return officeRecommendedBy;
	}

	public void setOfficeRecommendedBy(String officeRecommendedBy) {
		this.officeRecommendedBy = officeRecommendedBy;
	}

	public java.util.Date getOfficeRecommendedDate() {
		return officeRecommendedDate;
	}

	public void setOfficeRecommendedDate(java.util.Date officeRecommendedDate) {
		this.officeRecommendedDate = officeRecommendedDate;
	}

	public String getAdminRecommendationRemark() {
		return adminRecommendationRemark;
	}

	public void setAdminRecommendationRemark(String adminRecommendationRemark) {
		this.adminRecommendationRemark = adminRecommendationRemark;
	}

	public String getAdminRecommendedBy() {
		return adminRecommendedBy;
	}

	public void setAdminRecommendedBy(String adminRecommendedBy) {
		this.adminRecommendedBy = adminRecommendedBy;
	}

	public java.util.Date getAdminRecommendedDate() {
		return adminRecommendedDate;
	}

	public void setAdminRecommendedDate(java.util.Date adminRecommendedDate) {
		this.adminRecommendedDate = adminRecommendedDate;
	}

	public String getFinancialRecommendationRemark() {
		return financialRecommendationRemark;
	}

	public void setFinancialRecommendationRemark(String financialRecommendationRemark) {
		this.financialRecommendationRemark = financialRecommendationRemark;
	}

	public String getFinancialRecommendedBy() {
		return financialRecommendedBy;
	}

	public void setFinancialRecommendedBy(String financialRecommendedBy) {
		this.financialRecommendedBy = financialRecommendedBy;
	}

	public java.util.Date getFinancialRecommendedDate() {
		return financialRecommendedDate;
	}

	public void setFinancialRecommendedDate(java.util.Date financialRecommendedDate) {
		this.financialRecommendedDate = financialRecommendedDate;
	}

	public String getFinalRecommendationRemark() {
		return finalRecommendationRemark;
	}

	public void setFinalRecommendationRemark(String finalRecommendationRemark) {
		this.finalRecommendationRemark = finalRecommendationRemark;
	}

	public String getFinalRecommendedBy() {
		return finalRecommendedBy;
	}

	public void setFinalRecommendedBy(String finalRecommendedBy) {
		this.finalRecommendedBy = finalRecommendedBy;
	}

	public java.util.Date getFinalRecommendedDate() {
		return finalRecommendedDate;
	}

	public void setFinalRecommendedDate(java.util.Date finalRecommendedDate) {
		this.finalRecommendedDate = finalRecommendedDate;
	}

	public String getFinancialOfficeRecommendationRemark() {
		return financialOfficeRecommendationRemark;
	}

	public void setFinancialOfficeRecommendationRemark(String financialOfficeRecommendationRemark) {
		this.financialOfficeRecommendationRemark = financialOfficeRecommendationRemark;
	}

	public java.util.Date getFinalOfficeRecommendedDate() {
		return finalOfficeRecommendedDate;
	}

	public void setFinalOfficeRecommendedDate(java.util.Date finalOfficeRecommendedDate) {
		this.finalOfficeRecommendedDate = finalOfficeRecommendedDate;
	}

	public String getFinalOfficeRecommendedBy() {
		return finalOfficeRecommendedBy;
	}

	public void setFinalOfficeRecommendedBy(String finalOfficeRecommendedBy) {
		this.finalOfficeRecommendedBy = finalOfficeRecommendedBy;
	}
	
	
	

}
