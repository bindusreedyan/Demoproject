package NUALS.AMS.ACADEMIC.ACTIVITIES;

public class ActivityEstimateFormFields {
	
	private int expHeadId;
	public int getExpHeadId() {
		return expHeadId;
	}
	public void setExpHeadId(int expHeadId) {
		this.expHeadId = expHeadId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
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
	private int activityId;
	private double estimatedExp;
	private double deviation;
	private String comment;
	private String fileupload;

}
