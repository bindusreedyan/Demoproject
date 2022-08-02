package NUALS.AMS.ACADEMIC.ACTIVITIES;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Activity_Center {

	@EmbeddedId
	private ActivityCenterKey actvtyCenterKey;
	
	
	public ActivityCenterKey getActvtyCenterKey() {
		return actvtyCenterKey;
	}

	public void setActvtyCenterKey(ActivityCenterKey actvtyCenterKey) {
		this.actvtyCenterKey = actvtyCenterKey;
	}

	//@Column(columnDefinition = "default 0.0")
	private double Balance_exclude_unassignedGrant;
	
	//@Column(columnDefinition = "default 0.0")
	private double estimatedExp;
	
	/*@Column(columnDefinition = "default valid")
	private String activity_center_status;
	
	public String getActivity_center_status() {
		return activity_center_status;
	}

	public void setActivity_center_status(String activity_center_status) {
		this.activity_center_status = activity_center_status;
	}*/

	public double getBalance_exclude_unassignedGrant() {
		return Balance_exclude_unassignedGrant;
	}

	public void setBalance_exclude_unassignedGrant(double balance_exclude_unassignedGrant) {
		Balance_exclude_unassignedGrant = balance_exclude_unassignedGrant;
	}

	public double getEstimatedExp() {
		return estimatedExp;
	}

	public void setEstimatedExp(double estimatedExp) {
		this.estimatedExp = estimatedExp;
	}

	public double getExternalFund() {
		return externalFund;
	}

	public void setExternalFund(double externalFund) {
		this.externalFund = externalFund;
	}

	public double getUgcFund() {
		return ugcFund;
	}

	public void setUgcFund(double ugcFund) {
		this.ugcFund = ugcFund;
	}

	public int getNational_seminar_count() {
		return national_seminar_count;
	}

	public void setNational_seminar_count(int national_seminar_count) {
		this.national_seminar_count = national_seminar_count;
	}

	public int getExtension_lectures_count() {
		return extension_lectures_count;
	}

	public void setExtension_lectures_count(int extension_lectures_count) {
		this.extension_lectures_count = extension_lectures_count;
	}

	//@Column(columnDefinition = "default 0.0")
	private double externalFund;
	
	//@Column(columnDefinition = "default 0.0")
	private double ugcFund;
	
	// @Column(columnDefinition = "integer default 0")
	private int national_seminar_count;
	 
	// @Column(columnDefinition = "integer default 0")
	private int extension_lectures_count;
	
	 @Column(columnDefinition = "float default 0.0")
	private double expTillDate;


	public double getExpTillDate() {
		return expTillDate;
	}

	public void setExpTillDate(double expTillDate) {
		this.expTillDate = expTillDate;
	}
	
	
	 
	 
}
