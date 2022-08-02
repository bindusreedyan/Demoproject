package NUALS.AMS.ACADEMIC.CENTER;

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

import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;



@Embeddable
public class CentreFundKey implements Serializable {
	
	        @NotNull
			@ManyToOne
			@JoinColumn(name="centre_id")
		    private CENTRE cm;
	        
	        @Column(name = "finyear",  length=9,nullable=false)	
	    	private String finYear;

			public CENTRE getCm() {
				return cm;
			}

			public void setCm(CENTRE cm) {
				this.cm = cm;
			}

			public String getFinYear() {
				return finYear;
			}

			public void setFinYear(String finYear) {
				this.finYear = finYear;
			}

}

