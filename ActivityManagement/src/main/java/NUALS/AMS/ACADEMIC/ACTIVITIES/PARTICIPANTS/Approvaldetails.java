package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.io.Serializable;
import java.sql.Date;
public class Approvaldetails implements Serializable{
	private static final long serialVersionUID = 1L;
	private int participateRequestId;
	private String facultyRecomRemarks;
	private String recomStatus;
	private Date recomDate;
	private String administrativeApprovedBy;
	private Date adminApprovedDate;
	private String adminstrativeApproveRemarks;
    private String adminstrativeStatus;
    private String finalApprovedBy;
    private String finalApprovedRemark;
    private Date finalApprovedDate;
    private String finalApprovalStatus;
	  
	  
	  public String getFinalApprovalStatus() {
		return finalApprovalStatus;
	}
	public void setFinalApprovalStatus(String finalApprovalStatus) {
		this.finalApprovalStatus = finalApprovalStatus;
	}
	public String getFinalApprovedBy() {
		return finalApprovedBy;
	}
	public void setFinalApprovedBy(String finalApprovedBy) {
		this.finalApprovedBy = finalApprovedBy;
	}
	public String getFinalApprovedRemark() {
		return finalApprovedRemark;
	}
	public void setFinalApprovedRemark(String finalApprovedRemark) {
		this.finalApprovedRemark = finalApprovedRemark;
	}
	public Date getFinalApprovedDate() {
		return finalApprovedDate;
	}
	public void setFinalApprovedDate(Date finalApprovedDate) {
		this.finalApprovedDate = finalApprovedDate;
	}
	private String financiallyApprovedBy;
	  public String getFinanciallyApprovedBy() {
		return financiallyApprovedBy;
	}
	public void setFinanciallyApprovedBy(String financiallyApprovedBy) {
		this.financiallyApprovedBy = financiallyApprovedBy;
	}
	public Date getFinancialApprovedDate() {
		return financialApprovedDate;
	}
	public void setFinancialApprovedDate(Date financialApprovedDate) {
		this.financialApprovedDate = financialApprovedDate;
	}
	public String getFinancialApproveRemarks() {
		return financialApproveRemarks;
	}
	public void setFinancialApproveRemarks(String financialApproveRemarks) {
		this.financialApproveRemarks = financialApproveRemarks;
	}
	public String getFinancialStatus() {
		return financialStatus;
	}
	public void setFinancialStatus(String financialStatus) {
		this.financialStatus = financialStatus;
	}
	private Date financialApprovedDate;
	  private String financialApproveRemarks;
	  
	  private String financialStatus;
	
	
	public String getAdminstrativeStatus() {
		return adminstrativeStatus;
	}
	public void setAdminstrativeStatus(String adminstrativeStatus) {
		this.adminstrativeStatus = adminstrativeStatus;
	}
	public int getParticipateRequestId() {
		return participateRequestId;
	}
	public void setParticipateRequestId(int participateRequestId) {
		this.participateRequestId = participateRequestId;
	}
	public String getFacultyRecomRemarks() {
		return facultyRecomRemarks;
	}
	public void setFacultyRecomRemarks(String facultyRecomRemarks) {
		this.facultyRecomRemarks = facultyRecomRemarks;
	}
	public String getRecomStatus() {
		return recomStatus;
	}
	public void setRecomStatus(String recomStatus) {
		this.recomStatus = recomStatus;
	}
	public Date getRecomDate() {
		return recomDate;
	}
	public void setRecomDate(Date recomDate) {
		this.recomDate = recomDate;
	}
	public String getAdministrativeApprovedBy() {
		return administrativeApprovedBy;
	}
	public void setAdministrativeApprovedBy(String administrativeApprovedBy) {
		this.administrativeApprovedBy = administrativeApprovedBy;
	}
	public Date getAdminApprovedDate() {
		return adminApprovedDate;
	}
	public void setAdminApprovedDate(Date adminApprovedDate) {
		this.adminApprovedDate = adminApprovedDate;
	}
	public String getAdminstrativeApproveRemarks() {
		return adminstrativeApproveRemarks;
	}
	public void setAdminstrativeApproveRemarks(String adminstrativeApproveRemarks) {
		this.adminstrativeApproveRemarks = adminstrativeApproveRemarks;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
