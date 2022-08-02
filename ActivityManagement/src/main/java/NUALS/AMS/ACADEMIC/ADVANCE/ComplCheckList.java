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
public class ComplCheckList {
	
	 @Id
     @Column(name = "checkId",nullable = false,  length=50)	
     @GeneratedValue(strategy=GenerationType.AUTO)
     private int checkId;
	 
	    @NotNull
		@ManyToOne
		@JoinColumn(name="setId")
	    private AdvExpSettlement ac;
	    
	    
	    private String complDesc;
	    
	    private String included;

		public int getCheckId() {
			return checkId;
		}

		public void setCheckId(int checkId) {
			this.checkId = checkId;
		}

		public AdvExpSettlement getAc() {
			return ac;
		}

		public void setAc(AdvExpSettlement ac) {
			this.ac = ac;
		}



		public String getComplDesc() {
			return complDesc;
		}

		public void setComplDesc(String complDesc) {
			this.complDesc = complDesc;
		}

		public String getIncluded() {
			return included;
		}

		public void setIncluded(String included) {
			this.included = included;
		}
	    
	    
	    

}
