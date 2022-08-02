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

import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ActivityMaster {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int activityCode;
	 
	  @NotNull
      @ManyToOne
      @JoinColumn(name="activity_type_code")
      private AcademicActivityTypes aat;
	 
	   @Column(name = "title", nullable = false, length=5000)
	   private String title;
	   
	   private Date dateFrom;
	   
	   private Date dateTo;
	   @Column(name = "financialYear", nullable = false, length=9)
	   private String finyear;

	   
	   private String brochureURL;
	   @Column(name="description",nullable=true,length=2000)
	   private String description;
	   @Column(name="targetGroup",nullable=true,length=2000)
	   private String targetGroup;
	   @Column(name="Outcome",nullable=true)
	   private String outcome;
	   
	   private String deviationJustification;
	   private String type;
	   
	   private String activityLevel;
	   private String financeImplied;
	   private String status;
	   @Column(nullable=true)
	   private String remarks;
	   @Column(name="enteredBy",length=50)
	   private String enteredBy;
	   private Date enteredDate;
	   
	   
	   @Column(name="adminApprovedBy",nullable=true)
	   private String adminApprovedBy;
	   @Column(name="adminApprovedDate",nullable=true)
	   private Date adminApprovedDate;
	   @Column(name="adminApprovalRemarks",nullable=true)
	   private String adminApprovalRemarks;
	   
	   @Column(name="topic",nullable=true,length=500)
	   private String topic;
	  
	   @Column(name="administrativeApprovedBy",nullable=true)
	   private String administrativeApprovedBy;
	   @Column(name="administrativeApprovedDate",nullable=true)
	   private Date administrativeApprovedDate;
	   
	   private String administrativeApprovalRemarks;
	   
	   
	   
	   
	   
	   
	   
	   
	   @Column(name="FinancialApprovedBy",length=50,nullable=true)
	   private String financialApprovedBy;
	   @Column(name="financialApprovedDate",nullable=true)
	   private Date financialApprovedDate;
	   @Column(name="financialApprovedRemarks",nullable=true)
	   private String financialApprovedRemarks;
	   
	   private String finalApprovedBy;
	   
	   private Date finalApprovedDate;
	   
	   private String finalApprovedRemarks;
	   
	   
	   
  private String finalOfficeApprovedBy;
	   
	   private Date finalOfficeApprovedDate;
	   
	   private String finalOfficeApprovedRemarks;
	   
	   private String asNO;
	   private int center1;
	   
	   private String adminApprovedNeed;
	   
	   private String facultyCode;
	   
	   private String adminofficeapprovesStatus;
	   
	   private String officeApprovedRemarks;
	   
	   
	   private String financeOfficeapprovesStatus;
	   private String financeOfficeApprovedRemarks;
	   private Date financeOfficeVerifiedDate;
	   
	   private String financeOfficeVerifiedBy;
	   private Date adminOfficeVerifiedDate;
	   
	   private String adminOfficeVerifiedBy;
	   
	   private String userType;
	   
	   
	   private String recommendedBy;
	   private Date recommendedDate;
	   private String recommendedRemarks;
	   
	   
	   private String cancelledBy;
	   private Date cancelledDate;
	   private String cancelledRemarks;
	   
	   
	   public String getRecommendedBy() {
		return recommendedBy;
	}


	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}


	public Date getRecommendedDate() {
		return recommendedDate;
	}


	public void setRecommendedDate(Date recommendedDate) {
		this.recommendedDate = recommendedDate;
	}


	public String getRecommendedRemarks() {
		return recommendedRemarks;
	}


	public void setRecommendedRemarks(String recommendedRemarks) {
		this.recommendedRemarks = recommendedRemarks;
	}


	public Date getAdminOfficeVerifiedDate() {
		return adminOfficeVerifiedDate;
	}


	public void setAdminOfficeVerifiedDate(Date adminOfficeVerifiedDate) {
		this.adminOfficeVerifiedDate = adminOfficeVerifiedDate;
	}


	public String getAdminOfficeVerifiedBy() {
		return adminOfficeVerifiedBy;
	}


	public void setAdminOfficeVerifiedBy(String adminOfficeVerifiedBy) {
		this.adminOfficeVerifiedBy = adminOfficeVerifiedBy;
	}


	public String getAdminofficeapprovesStatus() {
		return adminofficeapprovesStatus;
	}


	public void setAdminofficeapprovesStatus(String adminofficeapprovesStatus) {
		this.adminofficeapprovesStatus = adminofficeapprovesStatus;
	}


	public String getOfficeApprovedRemarks() {
		return officeApprovedRemarks;
	}


	public void setOfficeApprovedRemarks(String officeApprovedRemarks) {
		this.officeApprovedRemarks = officeApprovedRemarks;
	}


	public String getFinanceOfficeapprovesStatus() {
		return financeOfficeapprovesStatus;
	}


	public void setFinanceOfficeapprovesStatus(String financeOfficeapprovesStatus) {
		this.financeOfficeapprovesStatus = financeOfficeapprovesStatus;
	}


	public String getFinanceOfficeApprovedRemarks() {
		return financeOfficeApprovedRemarks;
	}


	public void setFinanceOfficeApprovedRemarks(String financeOfficeApprovedRemarks) {
		this.financeOfficeApprovedRemarks = financeOfficeApprovedRemarks;
	}

	@Column(name="asOrdersIssued",columnDefinition = "varchar(15) default 'Not Issued'")
	   private String asOrdersIssued;
	   
	   public String getAsOrdersIssued() {
		return asOrdersIssued;
	}


	public void setAsOrdersIssued(String asOrdersIssued) {
		this.asOrdersIssued = asOrdersIssued;
	}


	public String getFacultyCode() {
		return facultyCode;
	}

	   
	   public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}
	public void setFacultyCode(String facultyCode) {
		this.facultyCode = facultyCode;
	}

	public ActivityMaster()
	   {
		   
	   }
	   
	   public ActivityMaster(String activityCode)
	   {
		   this.activityCode=Integer.parseInt(activityCode);
	   }
	   public int getCenter1() {
		return center1;
	}

	public void setCenter1(int center1) {
		this.center1 = center1;
	}

	public int getCenter2() {
		return center2;
	}

	public void setCenter2(int center2) {
		this.center2 = center2;
	}

	public int getCenter3() {
		return center3;
	}

	public void setCenter3(int center3) {
		this.center3 = center3;
	}

	public int getCenter4() {
		return center4;
	}

	public void setCenter4(int center4) {
		this.center4 = center4;
	}

	public int getCenter5() {
		return center5;
	}

	public void setCenter5(int center5) {
		this.center5 = center5;
	}

	private int center2;
	   private int center3;
	   private int center4;
	   private int center5;
	   
	   
	   
	   //getters and setters form
	   
	   public int getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(int activityCode) {
		this.activityCode = activityCode;
	}

	public AcademicActivityTypes getAat() {
		return aat;
	}

	public void setAat(AcademicActivityTypes aat) {
		System.out.println("entrr in setaat");
		this.aat = aat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getFinyear() {
		return finyear;
	}

	public void setFinyear(String finyear) {
		this.finyear = finyear;
	}

	
	public String getBrochureURL() {
		return brochureURL;
	}

	public void setBrochureURL(String brochureURL) {
		this.brochureURL = brochureURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getDeviationJustification() {
		return deviationJustification;
	}

	public void setDeviationJustification(String deviationJustification) {
		this.deviationJustification = deviationJustification;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getFinanceImplied() {
		return financeImplied;
	}

	public void setFinanceImplied(String financeImplied) {
		this.financeImplied = financeImplied;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getAdminApprovalRemarks() {
		return adminApprovalRemarks;
	}

	public void setAdminApprovalRemarks(String adminApprovalRemarks) {
		this.adminApprovalRemarks = adminApprovalRemarks;
	}

	public String getFinancialApprovedBy() {
		return financialApprovedBy;
	}

	public void setFinancialApprovedBy(String financialApprovedBy) {
		this.financialApprovedBy = financialApprovedBy;
	}

	public Date getFinancialApprovedDate() {
		return financialApprovedDate;
	}

	public void setFinancialApprovedDate(Date financialApprovedDate) {
		this.financialApprovedDate = financialApprovedDate;
	}

	public String getFinancialApprovedRemarks() {
		return financialApprovedRemarks;
	}

	public void setFinancialApprovedRemarks(String financialApprovedRemarks) {
		this.financialApprovedRemarks = financialApprovedRemarks;
	}

	public String getFinalApprovedBy() {
		return finalApprovedBy;
	}

	public void setFinalApprovedBy(String finalApprovedBy) {
		this.finalApprovedBy = finalApprovedBy;
	}

	public Date getFinalApprovedDate() {
		return finalApprovedDate;
	}

	public void setFinalApprovedDate(Date finalApprovedDate) {
		this.finalApprovedDate = finalApprovedDate;
	}

	public String getFinalApprovedRemarks() {
		return finalApprovedRemarks;
	}

	public void setFinalApprovedRemarks(String finalApprovedRemarks) {
		this.finalApprovedRemarks = finalApprovedRemarks;
	}

	public String getAsNO() {
		return asNO;
	}

	public void setAsNO(String asNO) {
		this.asNO = asNO;
	}

	public String getAdministrativeApprovedBy() {
		return administrativeApprovedBy;
	}

	public void setAdministrativeApprovedBy(String administrativeApprovedBy) {
		this.administrativeApprovedBy = administrativeApprovedBy;
	}

	public Date getAdministrativeApprovedDate() {
		return administrativeApprovedDate;
	}

	public void setAdministrativeApprovedDate(Date administrativeApprovedDate) {
		this.administrativeApprovedDate = administrativeApprovedDate;
	}

	public String getAdministrativeApprovalRemarks() {
		return administrativeApprovalRemarks;
	}

	public void setAdministrativeApprovalRemarks(String administrativeApprovalRemarks) {
		this.administrativeApprovalRemarks = administrativeApprovalRemarks;
	}


	public String getAdminApprovedNeed() {
		return adminApprovedNeed;
	}


	public void setAdminApprovedNeed(String adminApprovedNeed) {
		this.adminApprovedNeed = adminApprovedNeed;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getCancelledBy() {
		return cancelledBy;
	}


	public void setCancelledBy(String cancelledBy) {
		this.cancelledBy = cancelledBy;
	}


	public Date getCancelledDate() {
		return cancelledDate;
	}


	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}


	public String getCancelledRemarks() {
		return cancelledRemarks;
	}


	public void setCancelledRemarks(String cancelledRemarks) {
		this.cancelledRemarks = cancelledRemarks;
	}


	public Date getFinanceOfficeVerifiedDate() {
		return financeOfficeVerifiedDate;
	}


	public void setFinanceOfficeVerifiedDate(Date financeOfficeVerifiedDate) {
		this.financeOfficeVerifiedDate = financeOfficeVerifiedDate;
	}


	public String getFinalOfficeApprovedBy() {
		return finalOfficeApprovedBy;
	}


	public void setFinalOfficeApprovedBy(String finalOfficeApprovedBy) {
		this.finalOfficeApprovedBy = finalOfficeApprovedBy;
	}


	public Date getFinalOfficeApprovedDate() {
		return finalOfficeApprovedDate;
	}


	public void setFinalOfficeApprovedDate(Date finalOfficeApprovedDate) {
		this.finalOfficeApprovedDate = finalOfficeApprovedDate;
	}


	public String getFinalOfficeApprovedRemarks() {
		return finalOfficeApprovedRemarks;
	}

	
	public String getFinanceOfficeVerifiedBy() {
		return financeOfficeVerifiedBy;
	}


	public void setFinanceOfficeVerifiedBy(String financeOfficeVerifiedBy) {
		this.financeOfficeVerifiedBy = financeOfficeVerifiedBy;
	}


	@Override
	public String toString() {
		return "ActivityMaster [activityCode=" + activityCode + ", aat=" + aat + ", title=" + title + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", finyear=" + finyear + ", brochureURL=" + brochureURL
				+ ", description=" + description + ", targetGroup=" + targetGroup + ", outcome=" + outcome
				+ ", deviationJustification=" + deviationJustification + ", type=" + type + ", activityLevel="
				+ activityLevel + ", financeImplied=" + financeImplied + ", status=" + status + ", remarks=" + remarks
				+ ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", adminApprovedBy=" + adminApprovedBy
				+ ", adminApprovedDate=" + adminApprovedDate + ", adminApprovalRemarks=" + adminApprovalRemarks
				+ ", topic=" + topic + ", administrativeApprovedBy=" + administrativeApprovedBy
				+ ", administrativeApprovedDate=" + administrativeApprovedDate + ", administrativeApprovalRemarks="
				+ administrativeApprovalRemarks + ", financialApprovedBy=" + financialApprovedBy
				+ ", financialApprovedDate=" + financialApprovedDate + ", financialApprovedRemarks="
				+ financialApprovedRemarks + ", finalApprovedBy=" + finalApprovedBy + ", finalApprovedDate="
				+ finalApprovedDate + ", finalApprovedRemarks=" + finalApprovedRemarks + ", finalOfficeApprovedBy="
				+ finalOfficeApprovedBy + ", finalOfficeApprovedDate=" + finalOfficeApprovedDate
				+ ", finalOfficeApprovedRemarks=" + finalOfficeApprovedRemarks + ", asNO=" + asNO + ", center1="
				+ center1 + ", adminApprovedNeed=" + adminApprovedNeed + ", facultyCode=" + facultyCode
				+ ", adminofficeapprovesStatus=" + adminofficeapprovesStatus + ", officeApprovedRemarks="
				+ officeApprovedRemarks + ", financeOfficeapprovesStatus=" + financeOfficeapprovesStatus
				+ ", financeOfficeApprovedRemarks=" + financeOfficeApprovedRemarks + ", financeOfficeVerifiedDate="
				+ financeOfficeVerifiedDate + ", adminOfficeVerifiedDate=" + adminOfficeVerifiedDate
				+ ", adminOfficeVerifiedBy=" + adminOfficeVerifiedBy + ", userType=" + userType + ", recommendedBy="
				+ recommendedBy + ", recommendedDate=" + recommendedDate + ", recommendedRemarks=" + recommendedRemarks
				+ ", cancelledBy=" + cancelledBy + ", cancelledDate=" + cancelledDate + ", cancelledRemarks="
				+ cancelledRemarks + ", asOrdersIssued=" + asOrdersIssued + ", center2=" + center2 + ", center3="
				+ center3 + ", center4=" + center4 + ", center5=" + center5 + "]";
	}


	public void setFinalOfficeApprovedRemarks(String finalOfficeApprovedRemarks) {
		this.finalOfficeApprovedRemarks = finalOfficeApprovedRemarks;
	}

	
	   
	   
	   
	   
	   
}
