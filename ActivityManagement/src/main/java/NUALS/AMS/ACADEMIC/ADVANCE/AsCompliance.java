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
public class AsCompliance {
	 @Id
     @Column(name = "complId",nullable = false,  length=50)	
     @GeneratedValue(strategy=GenerationType.AUTO)
     private int complId;
	 
	    @NotNull
		@ManyToOne
		@JoinColumn(name="setId")
	    private AdvExpSettlement ac;
	    
	    
	    private String expHead;
	    
	    private double asGranted;
	    
	    private double actual;
	    
	    private double deviation;
	    
	    private String complianceJustification;
	    
	    private String complianceJustifiedBy;
	    
	    private String complianceJustificationRemarks;
	    
	    public String getComplianceJustificationRemarks() {
			return complianceJustificationRemarks;
		}

		public void setComplianceJustificationRemarks(String complianceJustificationRemarks) {
			this.complianceJustificationRemarks = complianceJustificationRemarks;
		}

		private java.util.Date complianceJustifiedDate;

		public int getComplId() {
			return complId;
		}

		public void setComplId(int complId) {
			this.complId = complId;
		}

		public AdvExpSettlement getAc() {
			return ac;
		}

		public void setAc(AdvExpSettlement ac) {
			this.ac = ac;
		}

		public String getExpHead() {
			return expHead;
		}

		public void setExpHead(String expHead) {
			this.expHead = expHead;
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

	
		public String getComplianceJustification() {
			return complianceJustification;
		}

		public void setComplianceJustification(String complianceJustification) {
			this.complianceJustification = complianceJustification;
		}

		public String getComplianceJustifiedBy() {
			return complianceJustifiedBy;
		}

		public void setComplianceJustifiedBy(String complianceJustifiedBy) {
			this.complianceJustifiedBy = complianceJustifiedBy;
		}

		public java.util.Date getComplianceJustifiedDate() {
			return complianceJustifiedDate;
		}

		public void setComplianceJustifiedDate(java.util.Date complianceJustifiedDate) {
			this.complianceJustifiedDate = complianceJustifiedDate;
		}
	    
	    
	    
	    
	    

}
