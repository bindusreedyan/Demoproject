package NUALS.AMS.ACADEMIC.ACTIVITY_TYPES;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AcademicActivityTypes {
	@Id
	@Column(name = "activityTypeCode",nullable = false,  length=50)	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int activityTypeCode;

	
    String activityTypeGroup;
	
	public String getActivityTypeGroup() {
		return activityTypeGroup;
	}
	public void setActivityTypeGroup(String activityTypeGroup) {
		this.activityTypeGroup = activityTypeGroup;
	}
	@Column(name = "activity_type_description",  length=500, unique=true)	
	private String activity_type_description;
		
	
	@Column(name = "activity_type_status", nullable = false,columnDefinition = "varchar(20) default 'valid'")
	private String activity_type_status;
	

	@Column(name = "enteredDate", nullable = true)
    private Date createDateTime;
 
	@Column(name = "enteredBy", nullable = false, length=50)
	private String enteredBy;
	
	@Column(name = "verifiedBy", nullable = true, length=50)
	private String verifiedBy;
	
	@Column(name = "verifiedDate", nullable = true)
    private Date verifiedDate;
	@Column(name = "verifiedRemarks", nullable = true)
    private String verifiedRemarks;
	
	@Column(name = "approvedBy", nullable = true, length=50)
	private String approvedBy;
	
	@Column(name = "approvedDate", nullable = true)
    private Date approvedDate;
	@Column(name = "approvedRemarks", nullable = true)
    private String approvedRemarks;
	
	@Column(name = "deactivateBy", nullable = true, length=50)
	private String deactivateBy;
	

	@Column(name = "deactivateDate", nullable = true)
    private Date deactivateDate;
	@Column(name = "deactivateRemarks", nullable = true)
    private String deactivateRemarks;
	
	
	
	
		
	public AcademicActivityTypes()
	{
		
	}
	public AcademicActivityTypes(String id)
	{
		int acttypeid=Integer.parseInt(id);
		this.activityTypeCode=acttypeid;
	}
	public int getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(int activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	public String getActivity_type_description() {
		return activity_type_description;
	}
	public void setActivity_type_description(String activity_type_description) {
		this.activity_type_description = activity_type_description;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public Date getVerifiedDate() {
		return verifiedDate;
	}
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}
	public String getVerifiedRemarks() {
		return verifiedRemarks;
	}
	public void setVerifiedRemarks(String verifiedRemarks) {
		this.verifiedRemarks = verifiedRemarks;
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
	public String getDeactivateBy() {
		return deactivateBy;
	}
	public void setDeactivateBy(String deactivateBy) {
		this.deactivateBy = deactivateBy;
	}
	public Date getDeactivateDate() {
		return deactivateDate;
	}
	public void setDeactivateDate(Date deactivateDate) {
		this.deactivateDate = deactivateDate;
	}
	public String getDeactivateRemarks() {
		return deactivateRemarks;
	}
	public void setDeactivateRemarks(String deactivateRemarks) {
		this.deactivateRemarks = deactivateRemarks;
	}
	
	
	public String getActivity_type_status() {
		return activity_type_status;
	}
	public void setActivity_type_status(String activity_type_status) {
		this.activity_type_status = activity_type_status;
	}
	@Override
	public String toString() {
		return "AcademicActivityTypes [activityTypeCode=" + activityTypeCode + ", activity_type_description="
				+ activity_type_description + ", status=" + activity_type_status + ", createDateTime=" + createDateTime
				+ ", enteredBy=" + enteredBy + ", verifiedBy=" + verifiedBy + ", verifiedDate=" + verifiedDate
				+ ", verifiedRemarks=" + verifiedRemarks + ", approvedBy=" + approvedBy + ", approvedDate="
				+ approvedDate + ", approvedRemarks=" + approvedRemarks + ", deactivateBy=" + deactivateBy
				+ ", deactivateDate=" + deactivateDate + ", deactivateRemarks=" + deactivateRemarks + "]";
	}
		
	
	
	
	


 

}
