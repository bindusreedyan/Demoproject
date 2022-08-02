package NUALS.AMS.ACADEMIC.ACTIVITIES;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityAdvEstimate {

	

	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ActivityAdvEstimateSino;

	@EmbeddedId
	private ActivityExpHeadKey actvtyExpHdkey;
	
	
	
	private double amountAdvance;
	
	private String reasonAdvance;
	
	private double asGranted=0;
	
@Column(name = "adv_paid", nullable = true)
private int adv_paid=0;
	
	

	public int getAdv_paid() {
		return adv_paid;
	}

	public void setAdv_paid(int adv_paid) {
		this.adv_paid = adv_paid;
	}

	public double getAsGranted() {
		return asGranted;
	}

	public void setAsGranted(double asGranted) {
		this.asGranted = asGranted;
	}

	public int getActivityAdvEstimateSino() {
		return ActivityAdvEstimateSino;
	}

	public void setActivityAdvEstimateSino(int activityAdvEstimateSino) {
		ActivityAdvEstimateSino = activityAdvEstimateSino;
	}

	public ActivityExpHeadKey getActvtyExpHdkey() {
		return actvtyExpHdkey;
	}

	public void setActvtyExpHdkey(ActivityExpHeadKey actvtyExpHdkey) {
		this.actvtyExpHdkey = actvtyExpHdkey;
	}

	public double getAmountAdvance() {
		return amountAdvance;
	}

	public void setAmountAdvance(double amountAdvance) {
		this.amountAdvance = amountAdvance;
	}

	public String getReasonAdvance() {
		return reasonAdvance;
	}

	public void setReasonAdvance(String reasonAdvance) {
		this.reasonAdvance = reasonAdvance;
	}
	@Override
	public String toString() {
		return "ActivityAdvEstimate [ActivityAdvEstimateSino=" + ActivityAdvEstimateSino + ", actvtyExpHdkey="
				+ actvtyExpHdkey + ", amountAdvance=" + amountAdvance + ", reasonAdvance=" + reasonAdvance
				+ ", asGranted=" + asGranted + ", adv_paid=" + adv_paid + "]";
	}

}
