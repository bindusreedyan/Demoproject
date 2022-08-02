package NUALS.AMS.ACADEMIC.ADVANCE;

import java.sql.Date;

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

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AdvExpSettlement {

	@Id
	@Column(name = "setId", nullable = false, length = 50)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int setId;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "activityCode")
	private ActivityMaster ac;

	private String settlementType;// advance or exp or final

	@Column(nullable = false, length = 50)
	private String approvalObtained;// yesorno

	@Column(nullable = false, length = 50)
	private String refNo;

	@Column(nullable = false, length = 50)
	private String asObtained;// yesorno

	private String asNo;// yesorno

	private String uONo;

	private double sancAmt;

	@Column(nullable = true)
	private String advRec;// yesorno

	private double totalAmtRec;

	private double totalPaidBack;

	private String enteredBy;

	private java.util.Date enteredDate;

	private String recomendBy;

	private Date recomendDate;

	private String recomRemarks;

	private String verifiedL1By;

	private String verifiedL1Remarks;

	private Date verifiedL1Date;

	private String verifiedL2By;

	private String verifiedL2Remarks;

	private Date verifiedL2Date;
	
	private String financeverifiedL2By;

	private String financeverifiedL2Remarks;

	private java.util.Date financeverifiedL2Date;
	
	
	
	

	private String verifiedL3By;

	private String verifiedL3Remarks;

	private Date verifiedL3Date;
	
	
	private String setlmntRecommendedBy;
	private java.util.Date  setlmntRecommendedDate;
	private String setlmntRecomRemarks;
	
	private String editedBy;
	private java.util.Date  updatedDate;
	
	private String updatedRemark;
	
	
	
	 @Column(name="balAmountTobePaid",columnDefinition = "float default 0.0")
	private double balAmountTobePaid=0.0;

	    @Column(name="esOrdersIssued",columnDefinition = "varchar(15) default 'Not Issued'")
		   private String esOrdersIssued;

	public String getEsOrdersIssued() {
			return esOrdersIssued;
		}

		public void setEsOrdersIssued(String esOrdersIssued) {
			this.esOrdersIssued = esOrdersIssued;
		}

	public double getBalAmountTobePaid() {
		return balAmountTobePaid;
	}

	public void setBalAmountTobePaid(double balAmountTobePaid) {
		this.balAmountTobePaid = balAmountTobePaid;
	}

	public String getVerifiedL3By() {
		return verifiedL3By;
	}

	public void setVerifiedL3By(String verifiedL3By) {
		this.verifiedL3By = verifiedL3By;
	}

	public String getVerifiedL3Remarks() {
		return verifiedL3Remarks;
	}

	public void setVerifiedL3Remarks(String verifiedL3Remarks) {
		this.verifiedL3Remarks = verifiedL3Remarks;
	}

	public Date getVerifiedL3Date() {
		return verifiedL3Date;
	}

	public void setVerifiedL3Date(Date verifiedL3Date) {
		this.verifiedL3Date = verifiedL3Date;
	}

	private String approvedBy;

	private Date approvedDate;

	private String approvedRemarks;
	
	private String enteredRemarks;
	
	private String advSettlementStatus;
	
	private String esNo;
	
private String financialOfficeRecommendationRemark;
	
    private String finalOfficeRecommendationRemark;
    
    private java.util.Date financialOfficeRecommendedDate;
    

    private java.util.Date finalOfficeRecommendedDate;
    
    private String finalOfficeRecommendedBy;
	public String getAdvSettlementStatus() {
		return advSettlementStatus;
	}

	public void setAdvSettlementStatus(String advSettlementStatus) {
		this.advSettlementStatus = advSettlementStatus;
	}

	public AdvExpSettlement() {
		super();
	
	}
	
	public java.util.Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(java.util.Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public AdvExpSettlement(String setId) {
		super();
		this.setId = Integer.parseInt(setId);
	}

	public int getSetId() {
		return setId;
	}

	public void setSetId(int setId) {
		this.setId = setId;
	}

	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public String getApprovalObtained() {
		return approvalObtained;
	}

	public void setApprovalObtained(String approvalObtained) {
		this.approvalObtained = approvalObtained;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getAsObtained() {
		return asObtained;
	}

	public void setAsObtained(String asObtained) {
		this.asObtained = asObtained;
	}

	public String getAsNo() {
		return asNo;
	}

	public void setAsNo(String asNo) {
		this.asNo = asNo;
	}

	public String getuONo() {
		return uONo;
	}

	public void setuONo(String uONo) {
		this.uONo = uONo;
	}

	public double getSancAmt() {
		return sancAmt;
	}

	public void setSancAmt(double sancAmt) {
		this.sancAmt = sancAmt;
	}

	public String getAdvRec() {
		return advRec;
	}

	public void setAdvRec(String advRec) {
		this.advRec = advRec;
	}

	public double getTotalAmtRec() {
		return totalAmtRec;
	}

	public void setTotalAmtRec(double totalAmtRec) {
		this.totalAmtRec = totalAmtRec;
	}

	public double getTotalPaidBack() {
		return totalPaidBack;
	}

	public void setTotalPaidBack(double totalPaidBack) {
		this.totalPaidBack = totalPaidBack;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getRecomendBy() {
		return recomendBy;
	}

	public void setRecomendBy(String recomendBy) {
		this.recomendBy = recomendBy;
	}

	public Date getRecomendDate() {
		return recomendDate;
	}

	public void setRecomendDate(Date recomendDate) {
		this.recomendDate = recomendDate;
	}

	public String getRecomRemarks() {
		return recomRemarks;
	}

	public void setRecomRemarks(String recomRemarks) {
		this.recomRemarks = recomRemarks;
	}

	public String getVerifiedL1By() {
		return verifiedL1By;
	}

	public void setVerifiedL1By(String verifiedL1By) {
		this.verifiedL1By = verifiedL1By;
	}

	public String getVerifiedL1Remarks() {
		return verifiedL1Remarks;
	}

	public void setVerifiedL1Remarks(String verifiedL1Remarks) {
		this.verifiedL1Remarks = verifiedL1Remarks;
	}

	public Date getVerifiedL1Date() {
		return verifiedL1Date;
	}

	public void setVerifiedL1Date(Date verifiedL1Date) {
		this.verifiedL1Date = verifiedL1Date;
	}

	public String getVerifiedL2By() {
		return verifiedL2By;
	}

	public void setVerifiedL2By(String verifiedL2By) {
		this.verifiedL2By = verifiedL2By;
	}

	public String getVerifiedL2Remarks() {
		return verifiedL2Remarks;
	}

	public void setVerifiedL2Remarks(String verifiedL2Remarks) {
		this.verifiedL2Remarks = verifiedL2Remarks;
	}

	public Date getVerifiedL2Date() {
		return verifiedL2Date;
	}

	public void setVerifiedL2Date(Date verifiedL2Date) {
		this.verifiedL2Date = verifiedL2Date;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getApprovedRemarks() {
		return approvedRemarks;
	}

	public void setApprovedRemarks(String approvedRemarks) {
		this.approvedRemarks = approvedRemarks;
	}

	public String getEnteredRemarks() {
		return enteredRemarks;
	}

	public void setEnteredRemarks(String enteredRemarks) {
		this.enteredRemarks = enteredRemarks;
	}

	public String getEsNo() {
		return esNo;
	}

	public void setEsNo(String esNo) {
		this.esNo = esNo;
	}

	public String getSetlmntRecommendedBy() {
		return setlmntRecommendedBy;
	}

	public void setSetlmntRecommendedBy(String setlmntRecommendedBy) {
		this.setlmntRecommendedBy = setlmntRecommendedBy;
	}

	public java.util.Date getSetlmntRecommendedDate() {
		return setlmntRecommendedDate;
	}

	public void setSetlmntRecommendedDate(java.util.Date setlmntRecommendedDate) {
		this.setlmntRecommendedDate = setlmntRecommendedDate;
	}

	public String getSetlmntRecomRemarks() {
		return setlmntRecomRemarks;
	}

	public void setSetlmntRecomRemarks(String setlmntRecomRemarks) {
		this.setlmntRecomRemarks = setlmntRecomRemarks;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(java.util.Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedRemark() {
		return updatedRemark;
	}

	public void setUpdatedRemark(String updatedRemark) {
		this.updatedRemark = updatedRemark;
	}
	
	
	public java.util.Date getFinanceverifiedL2Date() {
		return  financeverifiedL2Date;
	}

	public void setFinanceverifiedL2Date(java.util.Date financeverifiedL2Date) {
		this.financeverifiedL2Date = financeverifiedL2Date;
	}

	public String getFinanceverifiedL2By() {
		return financeverifiedL2By;
	}

	public void setFinanceverifiedL2By(String financeverifiedL2By) {
		this.financeverifiedL2By = financeverifiedL2By;
	}

	public String getFinanceverifiedL2Remarks() {
		return financeverifiedL2Remarks;
	}

	public void setFinanceverifiedL2Remarks(String financeverifiedL2Remarks) {
		this.financeverifiedL2Remarks = financeverifiedL2Remarks;
	}

	public String getFinancialOfficeRecommendationRemark() {
		return financialOfficeRecommendationRemark;
	}

	public void setFinancialOfficeRecommendationRemark(String financialOfficeRecommendationRemark) {
		this.financialOfficeRecommendationRemark = financialOfficeRecommendationRemark;
	}

	public String getFinalOfficeRecommendationRemark() {
		return finalOfficeRecommendationRemark;
	}

	public void setFinalOfficeRecommendationRemark(String finalOfficeRecommendationRemark) {
		this.finalOfficeRecommendationRemark = finalOfficeRecommendationRemark;
	}

	public java.util.Date getFinancialOfficeRecommendedDate() {
		return financialOfficeRecommendedDate;
	}

	public void setFinancialOfficeRecommendedDate(java.util.Date financialOfficeRecommendedDate) {
		this.financialOfficeRecommendedDate = financialOfficeRecommendedDate;
	}

	public java.util.Date getFinalOfficeRecommendedDate() {
		return finalOfficeRecommendedDate;
	}

	public void setFinalOfficeRecommendedDate(java.util.Date finalOfficeRecommendedDate) {
		this.finalOfficeRecommendedDate = finalOfficeRecommendedDate;
	}

	public String getFinalOfficeRecommendedBy() {
		return finalOfficeRecommendedBy;
	}

	public void setFinalOfficeRecommendedBy(String finalOfficeRecommendedBy) {
		this.finalOfficeRecommendedBy = finalOfficeRecommendedBy;
	}
	
	
}
