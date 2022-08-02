package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;



@Embeddable
public class BudgetFundKey implements Serializable{

    @NotNull
	@ManyToOne
	@JoinColumn(name="budHeadId")
    private BudgetHead budHeadId;
    
    @Column(name = "finyear",  length=9,nullable=false)	
	private String finYear;

	public BudgetHead getBudHeadId() {
		return budHeadId;
	}

	public void setBudHeadId(BudgetHead budHeadId) {
		this.budHeadId = budHeadId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
    
    
}
