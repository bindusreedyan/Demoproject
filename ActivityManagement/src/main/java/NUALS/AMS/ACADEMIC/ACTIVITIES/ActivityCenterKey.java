package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
@Embeddable
public class ActivityCenterKey implements Serializable{
	
		@NotNull
		@ManyToOne
		@JoinColumn(name="activityCode")
	    private ActivityMaster ac;
	    

		@NotNull
			@ManyToOne
			@JoinColumn(name="centre_id")
		    private CENTRE cm;
    
	            public ActivityMaster getAc() {
			return ac;
		}


		public void setAc(ActivityMaster ac) {
			this.ac = ac;
		}


		public CENTRE getCm() {
			return cm;
		}


		public void setCm(CENTRE cm) {
			this.cm = cm;
		}


	        
	 
	 
	 

}
