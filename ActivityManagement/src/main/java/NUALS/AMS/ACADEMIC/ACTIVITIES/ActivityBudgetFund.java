package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

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
public class ActivityBudgetFund {
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private int activityBudgetFundId;
	
	    
		
		@NotNull
		@ManyToOne
		@JoinColumn(name="activityCode")
	    private ActivityMaster ac;
		
		@Column(name="participateRequestId",columnDefinition = "float default 0")
		private int participateRequestId;
		
	    public int getParticipateRequestId() {
			return participateRequestId;
		}

		public void setParticipateRequestId(int participateRequestId) {
			this.participateRequestId = participateRequestId;
		}

		@NotNull
		@ManyToOne
		@JoinColumn(name="budHeadId")
	    private BudgetHead budHeadId;
	    
	    @Column(name = "finyear",  length=9,nullable=false)	
		private String finYear;
	
	    private String enteredBy;
      
        private Date enteredDate;
      
        private String enteredRemarks;
      
        private String budgetFundStatus;
        
        @Column(name="asAmount",columnDefinition = "float default 0")
        private double asAmount;
        
        
        public double getAsAmount() {
			return asAmount;
		}

		public void setAsAmount(double asAmount) {
			this.asAmount = asAmount;
		}

		public double getEsAmount() {
			return esAmount;
		}

		public void setEsAmount(double esAmount) {
			this.esAmount = esAmount;
		}

		@Column(name="esAmount",columnDefinition = "float default 0")
        private double esAmount;

        
        
        
		public int getActivityBudgetFundId() {
			return activityBudgetFundId;
		}

		public void setActivityBudgetFundId(int activityBudgetFundId) {
			this.activityBudgetFundId = activityBudgetFundId;
		}

		public ActivityMaster getAc() {
			return ac;
		}

		public void setAc(ActivityMaster ac) {
			this.ac = ac;
		}

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

		public String getEnteredBy() {
			return enteredBy;
		}

		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}

		public Date getEnteredDate() {
			return enteredDate;
		}

		public void setEnteredDate(Date enteredDate) {
			this.enteredDate = enteredDate;
		}

		public String getEnteredRemarks() {
			return enteredRemarks;
		}

		public void setEnteredRemarks(String enteredRemarks) {
			this.enteredRemarks = enteredRemarks;
		}

		public String getBudgetFundStatus() {
			return budgetFundStatus;
		}

		public void setBudgetFundStatus(String budgetFundStatus) {
			this.budgetFundStatus = budgetFundStatus;
		}
        
        
}
