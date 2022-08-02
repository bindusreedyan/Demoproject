package NUALS.AMS.ACADEMIC.ACTIVITIES;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityExpEstimate {
	
	@EmbeddedId
	private ActivityExpHeadKey actvtyExpHdkey;
	private double estimatedExp;
	private double deviation;
	private String comment;
	private String fileupload;
	private double NualsAllocationAmt;
	
	@Column(nullable=false)
	private double asGrantedAmnt=0;

	
	public double getAsGrantedAmnt() {
		return asGrantedAmnt;
	}
	public void setAsGrantedAmnt(double asGrantedAmnt) {
		this.asGrantedAmnt = asGrantedAmnt;
	}
	public double getEstimatedExp() {
		return estimatedExp;
	}
	public void setEstimatedExp(double estimatedExp) {
		this.estimatedExp = estimatedExp;
	}

	public ActivityExpHeadKey getActvtyExpHdkey() {
		return actvtyExpHdkey;
	}
	public void setActvtyExpHdkey(ActivityExpHeadKey actvtyExpHdkey) {
		this.actvtyExpHdkey = actvtyExpHdkey;
	}
	public double getNualsAllocationAmt() {
		return NualsAllocationAmt;
	}
	public void setNualsAllocationAmt(double nualsAllocationAmt) {
		NualsAllocationAmt = nualsAllocationAmt;
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

}
