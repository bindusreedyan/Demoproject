package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class AcademicPrintReport implements Serializable{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	 private Date dateFrom;
	 private Date dateTo;
	 private String description;
	 private String targetGroup;
	 private String outcome;
	 private String deviationJustification;
	 private String adminApprovalRemarks;
	 private ArrayList<String>centres;
	 private ArrayList<String> resourcePersons;
	 
	 
	 
	 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTargetGroup() {
		return targetGroup;
	}
	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getDeviationJustification() {
		return deviationJustification;
	}
	public void setDeviationJustification(String deviationJustification) {
		this.deviationJustification = deviationJustification;
	}
	public String getAdminApprovalRemarks() {
		return adminApprovalRemarks;
	}
	public void setAdminApprovalRemarks(String adminApprovalRemarks) {
		this.adminApprovalRemarks = adminApprovalRemarks;
	}
	public ArrayList<String> getCentres() {
		return centres;
	}
	public void setCentres(ArrayList<String> centres) {
		this.centres = centres;
	}
	public ArrayList<String> getResourcePersons() {
		return resourcePersons;
	}
	public void setResourcePersons(ArrayList<String> resourcePersons) {
		this.resourcePersons = resourcePersons;
	}
	
	
	 
	 
	 
	 

}
