package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;


@Embeddable
public class ActivityResourcePersonKey implements Serializable{
	
	    @NotNull
		@ManyToOne
		@JoinColumn(name="activity_code")
	    private ActivityMaster am;
	 
	    @NotNull
		@ManyToOne
		@JoinColumn(name="res_id")
	    private ResourcePersonMaster rpm;

		public ActivityMaster getAm() {
			return am;
		}

		public void setAm(ActivityMaster am) {
			this.am = am;
		}

		public ResourcePersonMaster getRpm() {
			return rpm;
		}

		public void setRpm(ResourcePersonMaster rpm) {
			this.rpm = rpm;
		}
	 

}
