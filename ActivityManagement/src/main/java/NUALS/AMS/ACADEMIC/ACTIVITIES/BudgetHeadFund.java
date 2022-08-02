package NUALS.AMS.ACADEMIC.ACTIVITIES;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
public class BudgetHeadFund{
	


			/*@Id
	        @GeneratedValue(strategy=GenerationType.AUTO)
	        private int budHeadFundId;*/
	
	        @EmbeddedId
		    private BudgetFundKey budgetFundKey;
            
	     
	        
	        private String enteredBy;
	        
	        private Date enteredDate;
	        
	        private String enteredRemarks;
	        
	        private String budgetFundStatus;
	        
	        
	        private String bfverifiedBy;
	        
	        private String bfverifiedRemarks;
	        
	        private Date bfverifiedDate;
	        
	        
            private String bfapprovedBy;
	        
	        private String bfapprovedRemarks;
	        
	        private Date bfapprovedDate;
	        
	        
             private String bfeditedBy;
	        
	        private String bfeditedRemarks;
	        
	        private Date bfeditedDate;
	        
	        
	        
	        
	        
            public String getBfverifiedBy() {
				return bfverifiedBy;
			}


			public void setBfverifiedBy(String bfverifiedBy) {
				this.bfverifiedBy = bfverifiedBy;
			}


			public String getBfverifiedRemarks() {
				return bfverifiedRemarks;
			}


			public void setBfverifiedRemarks(String bfverifiedRemarks) {
				this.bfverifiedRemarks = bfverifiedRemarks;
			}


			public Date getBfverifiedDate() {
				return bfverifiedDate;
			}


			public void setBfverifiedDate(Date bfverifiedDate) {
				this.bfverifiedDate = bfverifiedDate;
			}


			public String getBfapprovedBy() {
				return bfapprovedBy;
			}


			public void setBfapprovedBy(String bfapprovedBy) {
				this.bfapprovedBy = bfapprovedBy;
			}


			public String getBfapprovedRemarks() {
				return bfapprovedRemarks;
			}


			public void setBfapprovedRemarks(String bfapprovedRemarks) {
				this.bfapprovedRemarks = bfapprovedRemarks;
			}


			public Date getBfapprovedDate() {
				return bfapprovedDate;
			}


			public void setBfapprovedDate(Date bfapprovedDate) {
				this.bfapprovedDate = bfapprovedDate;
			}


			public String getBfeditedBy() {
				return bfeditedBy;
			}


			public void setBfeditedBy(String bfeditedBy) {
				this.bfeditedBy = bfeditedBy;
			}


			public String getBfeditedRemarks() {
				return bfeditedRemarks;
			}


			public void setBfeditedRemarks(String bfeditedRemarks) {
				this.bfeditedRemarks = bfeditedRemarks;
			}


			public Date getBfeditedDate() {
				return bfeditedDate;
			}


			public void setBfeditedDate(Date bfeditedDate) {
				this.bfeditedDate = bfeditedDate;
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


			public BudgetFundKey getBudgetFundKey() {
				return budgetFundKey;
			}


			public void setBudgetFundKey(BudgetFundKey budgetFundKey) {
				this.budgetFundKey = budgetFundKey;
			}


			@Column(nullable = false)
        	private double fundAmount;
			
			@Column(name="fundAmountBalance")
			private double fundAmountBalance;


		/*	public int getBudHeadFundId() {
				return budHeadFundId;
			}


			public void setBudHeadFundId(int budHeadFundId) {
				this.budHeadFundId = budHeadFundId;
			}*/


			


			public double getFundAmount() {
				return fundAmount;
			}


			public void setFundAmount(double fundAmount) {
				this.fundAmount = fundAmount;
			}


			public String getBudgetFundStatus() {
				return budgetFundStatus;
			}


			public void setBudgetFundStatus(String budgetFundStatus) {
				this.budgetFundStatus = budgetFundStatus;
			}


			public double getFundAmountBalance() {
				return fundAmountBalance;
			}


			public void setFundAmountBalance(double fundAmountBalance) {
				this.fundAmountBalance = fundAmountBalance;
			}


			@Override
			public String toString() {
				return "BudgetHeadFund [budgetFundKey=" + budgetFundKey + ", enteredBy=" + enteredBy + ", enteredDate="
						+ enteredDate + ", enteredRemarks=" + enteredRemarks + ", budgetFundStatus=" + budgetFundStatus
						+ ", bfverifiedBy=" + bfverifiedBy + ", bfverifiedRemarks=" + bfverifiedRemarks
						+ ", bfverifiedDate=" + bfverifiedDate + ", bfapprovedBy=" + bfapprovedBy
						+ ", bfapprovedRemarks=" + bfapprovedRemarks + ", bfapprovedDate=" + bfapprovedDate
						+ ", bfeditedBy=" + bfeditedBy + ", bfeditedRemarks=" + bfeditedRemarks + ", bfeditedDate="
						+ bfeditedDate + ", fundAmount=" + fundAmount + ", fundAmountBalance=" + fundAmountBalance
						+ "]";
			}
            
            
            

}
