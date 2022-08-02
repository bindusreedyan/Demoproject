package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class ActivityExpHeadKey  implements Serializable{
	@NotNull
	@ManyToOne
	@JoinColumn(name="activityCode")
    private ActivityMaster ac;
	
	@NotNull
	@ManyToOne
	@JoinColumns({
	@JoinColumn(name="description"),
	@JoinColumn(name="finYear")})
    private ExpHeadsMaster exp;
	
	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

	public ExpHeadsMaster getExp() {
		return exp;
	}

	public void setExp(ExpHeadsMaster exp) {
		this.exp = exp;
	}


	

}
