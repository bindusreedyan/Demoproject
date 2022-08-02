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

import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHead;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
import NUALS.AMS.ACADEMIC.VENDORS.VendorMaster;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AdvExpenditure {
	     @Id
	     @Column(name = "expId",nullable = false,  length=50)	
	     @GeneratedValue(strategy=GenerationType.AUTO)
	     private int expId;
	     
	     
	        @NotNull
			@ManyToOne
			@JoinColumn(name="setId")
		    private AdvExpSettlement ac;
	        
	        
	        @NotNull
			@ManyToOne
			@JoinColumn(name="headId")
		    private ExpHead headId;
	        
	        
	        @Column(nullable = true)
			//@ManyToOne
			//@JoinColumn(name="vendorId")
		    private int vendorId;
			
			 public int getResourceId() {
				return resourceId;
			}

			public void setResourceId(int resourceId) {
				this.resourceId = resourceId;
			}

			public String getVendorType() {
				return vendorType;
			}

			public void setVendorType(String vendorType) {
				this.vendorType = vendorType;
			}

			@Column(nullable = true)
		    private int resourceId;
	        
	        
	        @Column(name = "billNo", nullable = true, length=5000)
	        private String billNo;
	        
	        @Column(name = "expAmnt" )
	        private double expAmnt;
	        
	        
	        @Column(name = "taxAmnt" )
	        private double tax;
	        
	        @Column(name = "payeeName" )
	        private String payeeName;
	        
	        
	        @Column(name="totalAmt")
	        private double totalAmt;
	        
	        @Column(name="paidStatus")
	        private String paidStatus;
	        
	        @Column(name="modePayment")
	        public String modePayment;
	        
	        @Column(name="expHead")
	        public String expHead;
	        
	        @Column(name="admsAmt")
	        public double admsAmt;
	        
	        
	        private String enteredBy;
	        
	        private java.util.Date enteredDate;
	        
	        private String enteredRemarks;
	        
	        private String admissibleAmountAddedBy;
	        
	        private java.util.Date admissibleAmountAddedDate;
	        
	        
	        private String vendorType;

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

		

			public ExpHead getHeadId() {
				return headId;
			}

			public void setHeadId(ExpHead headId) {
				this.headId = headId;
			}

			public int getVendorId() {
				return vendorId;
			}

			public void setVendorId(int vendorId) {
				this.vendorId = vendorId;
			}

			public String getBillNo() {
				return billNo;
			}

			public void setBillNo(String billNo) {
				this.billNo = billNo;
			}

		

			

			public double getExpAmnt() {
				return expAmnt;
			}

			public void setExpAmnt(double expAmnt) {
				this.expAmnt = expAmnt;
			}

			public double getTax() {
				return tax;
			}

			public void setTax(double tax) {
				this.tax = tax;
			}

			public double getTotalAmt() {
				return totalAmt;
			}

			public void setTotalAmt(double totalAmt) {
				this.totalAmt = totalAmt;
			}

			public String getPaidStatus() {
				return paidStatus;
			}

			public void setPaidStatus(String paidStatus) {
				this.paidStatus = paidStatus;
			}

			public String getModePayment() {
				return modePayment;
			}

			public void setModePayment(String modePayment) {
				this.modePayment = modePayment;
			}

			public String getExpHead() {
				return expHead;
			}

			public void setExpHead(String expHead) {
				this.expHead = expHead;
			}

			public double getAdmsAmt() {
				return admsAmt;
			}

			public void setAdmsAmt(double admsAmt) {
				this.admsAmt = admsAmt;
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

			public String getEnteredRemarks() {
				return enteredRemarks;
			}

			public void setEnteredRemarks(String enteredRemarks) {
				this.enteredRemarks = enteredRemarks;
			}

			public String getPayeeName() {
				return payeeName;
			}

			public void setPayeeName(String payeeName) {
				this.payeeName = payeeName;
			}

			public String getAdmissibleAmountAddedBy() {
				return admissibleAmountAddedBy;
			}

			public void setAdmissibleAmountAddedBy(String admissibleAmountAddedBy) {
				this.admissibleAmountAddedBy = admissibleAmountAddedBy;
			}

			public java.util.Date getAdmissibleAmountAddedDate() {
				return admissibleAmountAddedDate;
			}

			public void setAdmissibleAmountAddedDate(java.util.Date admissibleAmountAddedDate) {
				this.admissibleAmountAddedDate = admissibleAmountAddedDate;
			}

			@Override
			public String toString() {
				return "AdvExpenditure [expId=" + expId + ", ac=" + ac + ", headId=" + headId + ", vendorId=" + vendorId
						+ ", resourceId=" + resourceId + ", billNo=" + billNo + ", expAmnt=" + expAmnt + ", tax=" + tax
						+ ", payeeName=" + payeeName + ", totalAmt=" + totalAmt + ", paidStatus=" + paidStatus
						+ ", modePayment=" + modePayment + ", expHead=" + expHead + ", admsAmt=" + admsAmt
						+ ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", enteredRemarks="
						+ enteredRemarks + ", admissibleAmountAddedBy=" + admissibleAmountAddedBy
						+ ", admissibleAmountAddedDate=" + admissibleAmountAddedDate + ", vendorType=" + vendorType
						+ "]";
			}
	        
	        

	        
	        
	        
	     
	     

}
