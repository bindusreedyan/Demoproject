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
import NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS.ActivityParticipateMasterData;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityAdvReq {
	@Id
	@Column(name = "advReqId",nullable = false,  length=50)	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int advReqId;


	@ManyToOne
	@JoinColumn(name="particiaptionRequestId")
    private ActivityParticipateMasterData pr;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="activityCode")
    private ActivityMaster ac;
	
	@Column(nullable = false)	
	private double advRequiredFig;
	
	@Column(nullable = false)	
	private String advRequiredWords;
	
	
	@Column(nullable = false)	
	private String purpose;
	
	@Column(nullable = false)	
	private double amtSanctionedAS;
	
	@Column(nullable=false)
	private String aSNo;
	
    //yes or no
	private String finalAdvReq;
	
	
	@Column(nullable=true)
	private String reqBy;
	
	
	@Column(nullable=true)
	private String designation;
	
	@Column(nullable=true)
	private String rollNoCapacity;
	
	@Column(nullable=true)
	private String enteredBy;
	@Column(nullable=true)
	private Date enteredDate;

	
	@Column(nullable=true)
	private String recommendBy;
	
	@Column(nullable=true)
	private Date recommendDate;
	
	@Column(nullable=true)
	private String recommendedRemarks;
	


	@Column(nullable=true)
	private String adminApprovedBy;
	
	@Column(nullable=true)
	private Date adminApprovedDate;
	
	
	@Column(nullable=true)
	private String adminApprovedRemarks;
	
	@Column(nullable=true)
	private String approvedBy;
	
	
	@Column(nullable=true)
	private String sanctionedBy;
	
	@Column(nullable=true)
	private Date sanctionDate;
	
	//boolean
	@Column(nullable=true)
	private String pendingSettlement;
	
	
	@Column(nullable=true)
	private String suggestions;
	
	@Column(nullable=true)
	private String reqRasedUserRole;//student or faculty
	
	
	@Column(nullable=true)
	private double sanctionedAmt=0.0;
	
	@Column(nullable=true)
	private String commentsFinance;
	
	@Column(nullable=true)
	private String voucherNo;
	
	@Column(nullable=true)
	private Date voucherDate;
	
	@Column(nullable=true)
	private String comments;
	
	@Column(nullable=false)
	private String advanceReqStatus;//Raised/sanctioned/settled/closed
	
	@Column(nullable=true)
	private String userCode;
	
	
	@Column(nullable=true)
	private String enteredRemarks;//Raised/sanctioned/settled/closed
	
	
	@Column(nullable=true)
	private int adv_paid;
	
	
	private String editedBy;
	
	private java.util.Date editedDate;
	
	private String editedRemark;
	
private String adminOfficeApprovedBy;
	
	private java.util.Date adminOfficeApprovedDate;
	
	private String adminOfficeApprovedRemark;
	
      private String financeOfficeApprovedBy;
	
	private java.util.Date financeOfficeApprovedDate;
	
	private String financeOfficeApprovedRemark;
	
	private String userType;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAdminOfficeApprovedBy() {
		return adminOfficeApprovedBy;
	}

	public void setAdminOfficeApprovedBy(String adminOfficeApprovedBy) {
		this.adminOfficeApprovedBy = adminOfficeApprovedBy;
	}

	public java.util.Date getAdminOfficeApprovedDate() {
		return adminOfficeApprovedDate;
	}

	public void setAdminOfficeApprovedDate(java.util.Date adminOfficeApprovedDate) {
		this.adminOfficeApprovedDate = adminOfficeApprovedDate;
	}

	public String getAdminOfficeApprovedRemark() {
		return adminOfficeApprovedRemark;
	}

	public void setAdminOfficeApprovedRemark(String adminOfficeApprovedRemark) {
		this.adminOfficeApprovedRemark = adminOfficeApprovedRemark;
	}

	public int getAdv_paid() {
		return adv_paid;
	}

	public void setAdv_paid(int adv_paid) {
		this.adv_paid = adv_paid;
	}

	public String getEnteredRemarks() {
		return enteredRemarks;
	}

	public void setEnteredRemarks(String enteredRemarks) {
		this.enteredRemarks = enteredRemarks;
	}

	public int getAdvReqId() {
		return advReqId;
	}

	public void setAdvReqId(int advReqId) {
		this.advReqId = advReqId;
	}

	public ActivityMaster getAc() {
		return ac;
	}

	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}

	public double getAdvRequiredFig() {
		return advRequiredFig;
	}

	public void setAdvRequiredFig(double advRequiredFig) {
		this.advRequiredFig = advRequiredFig;
	}

	public String getAdvRequiredWords() {
		return advRequiredWords;
	}

	public void setAdvRequiredWords(String advRequiredWords) {
		this.advRequiredWords = advRequiredWords;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public double getAmtSanctionedAS() {
		return amtSanctionedAS;
	}

	public void setAmtSanctionedAS(double amtSanctionedAS) {
		this.amtSanctionedAS = amtSanctionedAS;
	}

	public String getaSNo() {
		return aSNo;
	}

	public void setaSNo(String aSNo) {
		this.aSNo = aSNo;
	}

	public String getFinalAdvReq() {
		return finalAdvReq;
	}

	public void setFinalAdvReq(String finalAdvReq) {
		this.finalAdvReq = finalAdvReq;
	}

	public String getReqBy() {
		return reqBy;
	}

	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRollNoCapacity() {
		return rollNoCapacity;
	}

	public void setRollNoCapacity(String rollNoCapacity) {
		this.rollNoCapacity = rollNoCapacity;
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

	public String getRecommendBy() {
		return recommendBy;
	}

	public void setRecommendBy(String recommendBy) {
		this.recommendBy = recommendBy;
	}

	public Date getRecommendDate() {
		return recommendDate;
	}

	public void setRecommendDate(Date recommendDate) {
		this.recommendDate = recommendDate;
	}

	public String getAdminApprovedBy() {
		return adminApprovedBy;
	}

	public void setAdminApprovedBy(String adminApprovedBy) {
		this.adminApprovedBy = adminApprovedBy;
	}

	public Date getAdminApprovedDate() {
		return adminApprovedDate;
	}

	public void setAdminApprovedDate(Date adminApprovedDate) {
		this.adminApprovedDate = adminApprovedDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getSanctionedBy() {
		return sanctionedBy;
	}

	public void setSanctionedBy(String sanctionedBy) {
		this.sanctionedBy = sanctionedBy;
	}

	public Date getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public String getPendingSettlement() {
		return pendingSettlement;
	}

	public void setPendingSettlement(String pendingSettlement) {
		this.pendingSettlement = pendingSettlement;
	}

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public String getReqRasedUserRole() {
		return reqRasedUserRole;
	}

	public void setReqRasedUserRole(String reqRasedUserRole) {
		this.reqRasedUserRole = reqRasedUserRole;
	}

	public double getSanctionedAmt() {
		return sanctionedAmt;
	}

	public void setSanctionedAmt(double sanctionedAmt) {
		this.sanctionedAmt = sanctionedAmt;
	}

	public String getCommentsFinance() {
		return commentsFinance;
	}

	public void setCommentsFinance(String commentsFinance) {
		this.commentsFinance = commentsFinance;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAdvanceReqStatus() {
		return advanceReqStatus;
	}

	public void setAdvanceReqStatus(String advanceReqStatus) {
		this.advanceReqStatus = advanceReqStatus;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRecommendedRemarks() {
		return recommendedRemarks;
	}

	public void setRecommendedRemarks(String recommendedRemarks) {
		this.recommendedRemarks = recommendedRemarks;
	}
	public ActivityParticipateMasterData getPr() {
		return pr;
	}

	public void setPr(ActivityParticipateMasterData pr) {
		this.pr = pr;
	}
	public String getAdminApprovedRemarks() {
		return adminApprovedRemarks;
	}

	public void setAdminApprovedRemarks(String adminApprovedRemarks) {
		this.adminApprovedRemarks = adminApprovedRemarks;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public java.util.Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(java.util.Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getEditedRemark() {
		return editedRemark;
	}

	public void setEditedRemark(String editedRemark) {
		this.editedRemark = editedRemark;
	}

	public String getFinanceOfficeApprovedBy() {
		return financeOfficeApprovedBy;
	}

	public void setFinanceOfficeApprovedBy(String financeOfficeApprovedBy) {
		this.financeOfficeApprovedBy = financeOfficeApprovedBy;
	}

	public java.util.Date getFinanceOfficeApprovedDate() {
		return financeOfficeApprovedDate;
	}

	public void setFinanceOfficeApprovedDate(java.util.Date financeOfficeApprovedDate) {
		this.financeOfficeApprovedDate = financeOfficeApprovedDate;
	}

	public String getFinanceOfficeApprovedRemark() {
		return financeOfficeApprovedRemark;
	}

	public void setFinanceOfficeApprovedRemark(String financeOfficeApprovedRemark) {
		this.financeOfficeApprovedRemark = financeOfficeApprovedRemark;
	}

	@Override
	public String toString() {
		return "ActivityAdvReq [advReqId=" + advReqId + ", pr=" + pr + ", ac=" + ac + ", advRequiredFig="
				+ advRequiredFig + ", advRequiredWords=" + advRequiredWords + ", purpose=" + purpose
				+ ", amtSanctionedAS=" + amtSanctionedAS + ", aSNo=" + aSNo + ", finalAdvReq=" + finalAdvReq
				+ ", reqBy=" + reqBy + ", designation=" + designation + ", rollNoCapacity=" + rollNoCapacity
				+ ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", recommendBy=" + recommendBy
				+ ", recommendDate=" + recommendDate + ", recommendedRemarks=" + recommendedRemarks
				+ ", adminApprovedBy=" + adminApprovedBy + ", adminApprovedDate=" + adminApprovedDate
				+ ", adminApprovedRemarks=" + adminApprovedRemarks + ", approvedBy=" + approvedBy + ", sanctionedBy="
				+ sanctionedBy + ", sanctionDate=" + sanctionDate + ", pendingSettlement=" + pendingSettlement
				+ ", suggestions=" + suggestions + ", reqRasedUserRole=" + reqRasedUserRole + ", sanctionedAmt="
				+ sanctionedAmt + ", commentsFinance=" + commentsFinance + ", voucherNo=" + voucherNo + ", voucherDate="
				+ voucherDate + ", comments=" + comments + ", advanceReqStatus=" + advanceReqStatus + ", userCode="
				+ userCode + ", enteredRemarks=" + enteredRemarks + ", adv_paid=" + adv_paid + ", editedBy=" + editedBy
				+ ", editedDate=" + editedDate + ", editedRemark=" + editedRemark + ", adminOfficeApprovedBy="
				+ adminOfficeApprovedBy + ", adminOfficeApprovedDate=" + adminOfficeApprovedDate
				+ ", adminOfficeApprovedRemark=" + adminOfficeApprovedRemark + ", financeOfficeApprovedBy="
				+ financeOfficeApprovedBy + ", financeOfficeApprovedDate=" + financeOfficeApprovedDate
				+ ", financeOfficeApprovedRemark=" + financeOfficeApprovedRemark + "]";
	}
	
	
}
