package NUALS.AMS.ACADEMIC.ACTIVITIES;

public class ActivityEstimateWrapper {
	
	int activityId;
	int description;
	String finYear;
	private double estimatedExp;
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public int getDescription() {
		return description;
	}
	public void setDescription(int description) {
		this.description = description;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public double getEstimatedExp() {
		return estimatedExp;
	}
	public void setEstimatedExp(double estimatedExp) {
		this.estimatedExp = estimatedExp;
	}
	public double getDeviation() {
		return deviation;
	}
	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFileupload() {
		return fileupload;
	}
	public void setFileupload(String fileupload) {
		this.fileupload = fileupload;
	}
	public double getNualsAllocationAmt() {
		return NualsAllocationAmt;
	}
	public void setNualsAllocationAmt(double nualsAllocationAmt) {
		NualsAllocationAmt = nualsAllocationAmt;
	}
	private double deviation;
	private String comment;
	private String fileupload;
	private double NualsAllocationAmt;

}
