package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;

@Embeddable
public class ActivityParticipationRequestKey implements Serializable{
	@NotNull
	@ManyToOne
	@JoinColumn(name="activityCode")
    private ActivityMaster ac;
	
	//@NotNull
	//@ManyToOne
	//@JoinColumn(name="particiaptionRequestId")
   // private ActivityParticipateMasterData pR;
	
  
   /*public ActivityParticipateMasterData getpR() {
		return pR;
	}

	public void setpR(ActivityParticipateMasterData pR) {
		this.pR = pR;
	}*/

String rollNo;

	public String getRollNo() {
	return rollNo;
}

public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}

	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

		

}
