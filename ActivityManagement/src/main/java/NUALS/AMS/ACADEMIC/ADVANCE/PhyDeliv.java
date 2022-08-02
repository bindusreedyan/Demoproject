package NUALS.AMS.ACADEMIC.ADVANCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class PhyDeliv {
	  @Id
	     @Column(name = "expId",nullable = false,  length=50)	
	     @GeneratedValue(strategy=GenerationType.AUTO)
	     private int expId;
	  
	    @NotNull
		@ManyToOne
		@JoinColumn(name="setId")
	    private AdvExpSettlement ac;
	     
	     private String delivarableDescription;
	     
	     private double asGranted=0;
	     
	     private double actual=0;
	     
	     private double deviation=0;
	     
	     private String justification;
	     
	     private String enteredBy;
	     
	     private java.util.Date enteredDate;
	     
	     private String physicalEnteredRemarks;
	     
	     private String recommendedBy;
	     
	     private String recommendedDate;
	     
	     private String recommended;//yes or no

		public int getExpId() {
			return expId;
		}

		public void setExpId(int expId) {
			this.expId = expId;
		}

		public AdvExpSettlement getAc() {
			return ac;
		}

		public void setAc(AdvExpSettlement ac) {
			this.ac = ac;
		}

		public String getDelivarableDescription() {
			return delivarableDescription;
		}

		public void setDelivarableDescription(String delivarableDescription) {
			this.delivarableDescription = delivarableDescription;
		}

		public double getAsGranted() {
			return asGranted;
		}

		public void setAsGranted(double asGranted) {
			this.asGranted = asGranted;
		}

		public double getActual() {
			return actual;
		}

		public void setActual(double actual) {
			this.actual = actual;
		}

		public double getDeviation() {
			return deviation;
		}

		public void setDeviation(double deviation) {
			this.deviation = deviation;
		}

		public String getJustification() {
			return justification;
		}

		public void setJustification(String justification) {
			this.justification = justification;
		}

		public String getEnteredBy() {
			return enteredBy;
		}

		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}

		public java.util.Date getEnteredDate() {
			return enteredDate;
		}

		public void setEnteredDate(java.util.Date enteredDate) {
			this.enteredDate = enteredDate;
		}

		

		public String getPhysicalEnteredRemarks() {
			return physicalEnteredRemarks;
		}

		public void setPhysicalEnteredRemarks(String physicalEnteredRemarks) {
			this.physicalEnteredRemarks = physicalEnteredRemarks;
		}

		public String getRecommendedBy() {
			return recommendedBy;
		}

		public void setRecommendedBy(String recommendedBy) {
			this.recommendedBy = recommendedBy;
		}

		public String getRecommendedDate() {
			return recommendedDate;
		}

		public void setRecommendedDate(String recommendedDate) {
			this.recommendedDate = recommendedDate;
		}

		public String getRecommended() {
			return recommended;
		}

		public void setRecommended(String recommended) {
			this.recommended = recommended;
		}
	     
	     
	     
	     

}
