package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

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
public class ActivityParticipateMasterData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int particiaptionRequestId;
	
	

	@NotNull
	@ManyToOne
	@JoinColumn(name="activityCode")
    private ActivityMaster ac;
	private Date dateNotifction;
	private Date competionStart;
	private Date competionEnd;
	private Date travelDateOnwardStart;
	private Date travelDateOnwardReturn;
	private Date travelDateReturnStart;
	private Date travelDateReturnEnd;
	private String activityVenue;
	@Column(name = "hostingInstitution", nullable = true, length=50)
	private String hostingInstitution;
	private String competionType;
	@Column(name = "hostingCountry", nullable = true, length=50)
	private String hostingCountry;

	private String currencyNonINR;
	private double conversionRate;
	private double estVisaCharge;
	private double estLocalConv;
	private double estLoadging;
	private String teamParticipation;
    private String enteredBy;
    private Date enteredDate;
    private String enteredRemarks;
    private String recommendedBy;
	private String recomRemarks;
	private Date recomDate;
	
	private String financialImplications;
	
  private String administrativeApprovedBy;
  private Date adminApprovedDate;
  private String adminstrativeApproveRemarks;
  
  private String financialApprovedBy;
  private Date financialApprovedDate;
  private String financialApproveRemarks;
  private String approvedBy;
  private String approvedRemark;
  private Date approvedDate;
  
  
  private String administrativeOfficeApprovedBy;
  private Date adminOfficeApprovedDate;
  private String adminstrativeOfficeApproveRemarks;
  
  
  
  @Column(nullable = true)
  private double totalExpenseProgram;
  
  @Column(nullable = true)
  private double totalAdmitAmount;
  
  
  private String settlementUserRemark;
  private java.util.Date settlementUserDate;
  
  private String settlementEnteredBy;
  @Column(nullable=true)
  private String settlementStatus;
  
  
  private String rollNo;
  
  private String settleRecomRemark;
  private java.util.Date settlRecomDate;
  
  private String settlRecomBy;

  private String settleAdministrativeRemark;
  private java.util.Date settlAdministrativeDate;
  
  private String settlAdministrativeBy;
  
  private String settleFinancialRemark;
  private java.util.Date settlFinancialDate;
  
  private String settlFinanacialBy;
  
  private String settleFinalRemark;
  private java.util.Date settlFinalDate;
  
  private String settlFinalBy;
  
  public String asNo;
  public String esNo;
  
  
  
  
  public String getEsNo() {
	return esNo;
}
public void setEsNo(String esNo) {
	this.esNo = esNo;
}
@Column(name="asOrdersIssued",columnDefinition = "varchar(15) default 'Not Issued'")
  private String asOrdersIssued;
  
  @Column(name="eSOrdersIssued",columnDefinition = "varchar(15) default 'Not Issued'")
  private String eSOrdersIssued;
  
  
  
	public String getRollNo() {
	return rollNo;
}
public void setRollNo(String rollNo) {
	this.rollNo = rollNo;
}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getApprovedRemark() {
		return approvedRemark;
	}
	public void setApprovedRemark(String approvedRemark) {
		this.approvedRemark = approvedRemark;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	
    public String getRecommendedBy() {
		return recommendedBy;
	}
	public void setRecommendedBy(String recommendedBy) {
		this.recommendedBy = recommendedBy;
	}
	public String getRecomRemarks() {
		return recomRemarks;
	}
	public void setRecomRemarks(String recomRemarks) {
		this.recomRemarks = recomRemarks;
	}
	public Date getRecomDate() {
		return recomDate;
	}
	public void setRecomDate(Date recomDate) {
		this.recomDate = recomDate;
	}
	

	public int getParticiaptionRequestId() {
		return particiaptionRequestId;
	}
	public void setParticiaptionRequestId(int particiaptionRequestId) {
		this.particiaptionRequestId = particiaptionRequestId;
	}
	public Date getEnteredDate() {
		return enteredDate;
	}
	public void setEnteredDate(Date enteredDate) {
		this.enteredDate = enteredDate;
	}
	private String participationRequestStatus;
    public String getParticipationRequestStatus() {
		return participationRequestStatus;
	}
	public void setParticipationRequestStatus(String participationRequestStatus) {
		this.participationRequestStatus = participationRequestStatus;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getEntereredDate() {
		return entereredDate;
	}
	public void setEntereredDate(String entereredDate) {
		this.entereredDate = entereredDate;
	}
	private String entereredDate;
	
	public ActivityMaster getAc() {
		return ac;
	}
	public void setAc(ActivityMaster ac) {
		this.ac = ac;
	}
	public Date getDateNotifction() {
		return dateNotifction;
	}
	public void setDateNotifction(Date dateNotifction) {
		this.dateNotifction = dateNotifction;
	}
	public Date getCompetionStart() {
		return competionStart;
	}
	public void setCompetionStart(Date competionStart) {
		this.competionStart = competionStart;
	}
	public Date getCompetionEnd() {
		return competionEnd;
	}
	public void setCompetionEnd(Date competionEnd) {
		this.competionEnd = competionEnd;
	}
	public Date getTravelDateOnwardStart() {
		return travelDateOnwardStart;
	}
	public void setTravelDateOnwardStart(Date travelDateOnwardStart) {
		this.travelDateOnwardStart = travelDateOnwardStart;
	}
	public Date getTravelDateOnwardReturn() {
		return travelDateOnwardReturn;
	}
	public void setTravelDateOnwardReturn(Date travelDateOnwardReturn) {
		this.travelDateOnwardReturn = travelDateOnwardReturn;
	}
	public Date getTravelDateReturnStart() {
		return travelDateReturnStart;
	}
	public void setTravelDateReturnStart(Date travelDateReturnStart) {
		this.travelDateReturnStart = travelDateReturnStart;
	}
	public Date getTravelDateReturnEnd() {
		return travelDateReturnEnd;
	}
	public void setTravelDateReturnEnd(Date travelDateReturnEnd) {
		this.travelDateReturnEnd = travelDateReturnEnd;
	}
	public String getActivityVenue() {
		return activityVenue;
	}
	public void setActivityVenue(String activityVenue) {
		this.activityVenue = activityVenue;
	}
	public String getHostingInstitution() {
		return hostingInstitution;
	}
	public void setHostingInstitution(String hostingInstitution) {
		this.hostingInstitution = hostingInstitution;
	}
	public String getCompetionType() {
		return competionType;
	}
	public void setCompetionType(String competionType) {
		this.competionType = competionType;
	}
	public String getHostingCountry() {
		return hostingCountry;
	}
	public void setHostingCountry(String hostingCountry) {
		this.hostingCountry = hostingCountry;
	}
	public String getCurrencyNonINR() {
		return currencyNonINR;
	}
	public void setCurrencyNonINR(String currencyNonINR) {
		this.currencyNonINR = currencyNonINR;
	}
	public double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	public double getEstVisaCharge() {
		return estVisaCharge;
	}
	public void setEstVisaCharge(double estVisaCharge) {
		this.estVisaCharge = estVisaCharge;
	}
	public double getEstLocalConv() {
		return estLocalConv;
	}
	public void setEstLocalConv(double estLocalConv) {
		this.estLocalConv = estLocalConv;
	}
	public double getEstLoadging() {
		return estLoadging;
	}
	public void setEstLoadging(double estLoadging) {
		this.estLoadging = estLoadging;
	}
	public String getTeamParticipation() {
		return teamParticipation;
	}
	public void setTeamParticipation(String teamParticipation) {
		this.teamParticipation = teamParticipation;
	}
	
	public ActivityParticipateMasterData(String particiaptionRequestId) {
		super();
		this.particiaptionRequestId = Integer.parseInt(particiaptionRequestId);
	}
	public ActivityParticipateMasterData() {
		super();
		
	}
	public String getAdministrativeApprovedBy() {
		return administrativeApprovedBy;
	}
	public void setAdministrativeApprovedBy(String administrativeApprovedBy) {
		this.administrativeApprovedBy = administrativeApprovedBy;
	}
	public Date getAdminApprovedDate() {
		return adminApprovedDate;
	}
	public void setAdminApprovedDate(Date adminApprovedDate) {
		this.adminApprovedDate = adminApprovedDate;
	}
	public String getAdminstrativeApproveRemarks() {
		return adminstrativeApproveRemarks;
	}
	public void setAdminstrativeApproveRemarks(String adminstrativeApproveRemarks) {
		this.adminstrativeApproveRemarks = adminstrativeApproveRemarks;
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
	public String getFinancialApproveRemarks() {
		return financialApproveRemarks;
	}
	public void setFinancialApproveRemarks(String financialApproveRemarks) {
		this.financialApproveRemarks = financialApproveRemarks;
	}
	public String getFinancialImplications() {
		return financialImplications;
	}
	public void setFinancialImplications(String financialImplications) {
		this.financialImplications = financialImplications;
	}
	public double getTotalExpenseProgram() {
		return totalExpenseProgram;
	}
	public void setTotalExpenseProgram(double totalExpenseProgram) {
		this.totalExpenseProgram = totalExpenseProgram;
	}
	public String getEnteredRemarks() {
		return enteredRemarks;
	}
	public void setEnteredRemarks(String enteredRemarks) {
		this.enteredRemarks = enteredRemarks;
	}
	public double getTotalAdmitAmount() {
		return totalAdmitAmount;
	}
	public void setTotalAdmitAmount(double totalAdmitAmount) {
		this.totalAdmitAmount = totalAdmitAmount;
	}
	public String getSettlementUserRemark() {
		return settlementUserRemark;
	}
	public void setSettlementUserRemark(String settlementUserRemark) {
		this.settlementUserRemark = settlementUserRemark;
	}
	public java.util.Date getSettlementUserDate() {
		return settlementUserDate;
	}
	public void setSettlementUserDate(java.util.Date settlementUserDate) {
		this.settlementUserDate = settlementUserDate;
	}
	public String getSettlementEnteredBy() {
		return settlementEnteredBy;
	}
	public void setSettlementEnteredBy(String settlementEnteredBy) {
		this.settlementEnteredBy = settlementEnteredBy;
	}
	public String getSettlementStatus() {
		return settlementStatus;
	}
	public void setSettlementStatus(String settlementStatus) {
		this.settlementStatus = settlementStatus;
	}
	public String getSettleRecomRemark() {
		return settleRecomRemark;
	}
	public void setSettleRecomRemark(String settleRecomRemark) {
		this.settleRecomRemark = settleRecomRemark;
	}
	public java.util.Date getSettlRecomDate() {
		return settlRecomDate;
	}
	public void setSettlRecomDate(java.util.Date settlRecomDate) {
		this.settlRecomDate = settlRecomDate;
	}
	public String getSettlRecomBy() {
		return settlRecomBy;
	}
	public void setSettlRecomBy(String settlRecomBy) {
		this.settlRecomBy = settlRecomBy;
	}
	public String getSettleAdministrativeRemark() {
		return settleAdministrativeRemark;
	}
	public void setSettleAdministrativeRemark(String settleAdministrativeRemark) {
		this.settleAdministrativeRemark = settleAdministrativeRemark;
	}
	public java.util.Date getSettlAdministrativeDate() {
		return settlAdministrativeDate;
	}
	public void setSettlAdministrativeDate(java.util.Date settlAdministrativeDate) {
		this.settlAdministrativeDate = settlAdministrativeDate;
	}
	public String getSettlAdministrativeBy() {
		return settlAdministrativeBy;
	}
	public void setSettlAdministrativeBy(String settlAdministrativeBy) {
		this.settlAdministrativeBy = settlAdministrativeBy;
	}
	public String getSettleFinancialRemark() {
		return settleFinancialRemark;
	}
	public void setSettleFinancialRemark(String settleFinancialRemark) {
		this.settleFinancialRemark = settleFinancialRemark;
	}
	public java.util.Date getSettlFinancialDate() {
		return settlFinancialDate;
	}
	public void setSettlFinancialDate(java.util.Date settlFinancialDate) {
		this.settlFinancialDate = settlFinancialDate;
	}
	public String getSettlFinanacialBy() {
		return settlFinanacialBy;
	}
	public void setSettlFinanacialBy(String settlFinanacialBy) {
		this.settlFinanacialBy = settlFinanacialBy;
	}
	public String getSettleFinalRemark() {
		return settleFinalRemark;
	}
	public void setSettleFinalRemark(String settleFinalRemark) {
		this.settleFinalRemark = settleFinalRemark;
	}
	public java.util.Date getSettlFinalDate() {
		return settlFinalDate;
	}
	public void setSettlFinalDate(java.util.Date settlFinalDate) {
		this.settlFinalDate = settlFinalDate;
	}
	public String getSettlFinalBy() {
		return settlFinalBy;
	}
	public void setSettlFinalBy(String settlFinalBy) {
		this.settlFinalBy = settlFinalBy;
	}
	public String getAsNo() {
		return asNo;
	}
	public void setAsNo(String asNo) {
		this.asNo = asNo;
	}
	public String getAsOrdersIssued() {
		return asOrdersIssued;
	}
	public void setAsOrdersIssued(String asOrdersIssued) {
		this.asOrdersIssued = asOrdersIssued;
	}
	public String geteSOrdersIssued() {
		return eSOrdersIssued;
	}
	public void seteSOrdersIssued(String eSOrdersIssued) {
		this.eSOrdersIssued = eSOrdersIssued;
	}
	

}
