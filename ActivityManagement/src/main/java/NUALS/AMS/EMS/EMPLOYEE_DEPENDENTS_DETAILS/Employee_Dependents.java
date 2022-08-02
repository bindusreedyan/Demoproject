package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;
import NUALS.AMS.EMS.EMPLOYMENT_TYPE.Employment_Type;
//import NUALS.AMS.FEE.FH_MAPPING_GROUP.SFS_FH_MAPPING_GROUP_ID;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee_Dependents
{
	@EmbeddedId
	 private EMP_DEPENDENTS_PKEY emp_dependents_id;
	
	
	@Column(name = "deprelation", nullable = false, length=50,columnDefinition="VARCHAR(50) DEFAULT 'father'")
	private String deprelation;
	
	@Column(name = "depgender", nullable = false, length=50,columnDefinition="VARCHAR(50) DEFAULT 'male'")
	private String depgender;
	
	@Column(name = "emp_dependent_dob", nullable = false)
	private Date emp_dependent_dob;	
	
	@Column(name = "emp_dependent_mobile", nullable = true, length=100)
	private String emp_dependent_mobile;
	
	@Column(name = "emp_dependent_email_id", nullable = true, length=100)
	private String emp_dependent_email_id;
	
	@Column(name = "emp_dependents_ident_type", nullable = false, length=100)
	private String emp_dependents_ident_type;
	
	@Column(name = "emp_dependents_ident_no", nullable = false, length=100)
	private String emp_dependents_ident_no;
	/************* Start:   Maker, Checker  related... **********/
	@Column(name = "maker", nullable = false, length=50,columnDefinition="VARCHAR(50) DEFAULT 'admin'")
	private String maker;

	@Column(name = "level1Checker", nullable = true, length=50)
	private String level1Checker;
	@Column(name = "level1CheckDate", nullable = true)
	private java.util.Date level1CheckDate;
	@Column(name = "level1Remarks", nullable = true, length=500)
	private String level1Remarks;
	
	@Column(name = "level2Checker", nullable = true, length=50)
	private String level2Checker;
	@Column(name = "level2CheckDate", nullable = true)
	private java.util.Date level2CheckDate;
	@Column(name = "level2Remarks", nullable = true, length=500)
	private String level2Remarks;
	
	@Column(name = "approvedBy", nullable = true, length=50)
	private String approvedBy;
	@Column(name = "approvedDate", nullable = true)
	private java.util.Date approvedDate;
	@Column(name = "approvedRemarks", nullable = true, length=500)
	private String approvedRemarks;
	
	@Column(name = "updateBy", nullable = true, length=50)
	private String updateBy;
	
	@Column(name = "updateRemarks", nullable = true, length=500)
	private String updateRemarks;
	
	/************* End:   Maker, Checker  related... **********/


	
	@Column(name = "rowid", nullable = false,columnDefinition="int DEFAULT 0")
    private int rowid;
			
	@Column(name = "empdepstatus", nullable = false,columnDefinition = "varchar(20) default 'submitted'")
	private String empdepstatus;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDateTime;

    
	public Employee_Dependents() {
	}


	public Employee_Dependents(EMP_DEPENDENTS_PKEY emp_dependents_id, Date emp_dependent_dob,
			String emp_dependent_mobile, String emp_dependent_email_id, String emp_dependents_ident_type,
			String emp_dependents_ident_no, String maker, String level1Checker, java.util.Date level1CheckDate,
			String level1Remarks, String level2Checker, java.util.Date level2CheckDate, String level2Remarks,
			String approvedBy, java.util.Date approvedDate, String approvedRemarks, String updateBy,
			String updateRemarks, int rowid, String status, LocalDateTime createDateTime,
			LocalDateTime updateDateTime) {
		this.emp_dependents_id = emp_dependents_id;
		this.emp_dependent_dob = emp_dependent_dob;
		this.emp_dependent_mobile = emp_dependent_mobile;
		this.emp_dependent_email_id = emp_dependent_email_id;
		this.emp_dependents_ident_type = emp_dependents_ident_type;
		this.emp_dependents_ident_no = emp_dependents_ident_no;
		this.maker = maker;
		this.level1Checker = level1Checker;
		this.level1CheckDate = level1CheckDate;
		this.level1Remarks = level1Remarks;
		this.level2Checker = level2Checker;
		this.level2CheckDate = level2CheckDate;
		this.level2Remarks = level2Remarks;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
		this.approvedRemarks = approvedRemarks;
		this.updateBy = updateBy;
		this.updateRemarks = updateRemarks;
		this.rowid = rowid;
		this.empdepstatus = status;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}


	public EMP_DEPENDENTS_PKEY getEmp_dependents_id() {
		return emp_dependents_id;
	}


	public void setEmp_dependents_id(EMP_DEPENDENTS_PKEY emp_dependents_id) {
		this.emp_dependents_id = emp_dependents_id;
	}


	public Date getEmp_dependent_dob() {
		return emp_dependent_dob;
	}


	public void setEmp_dependent_dob(Date emp_dependent_dob) {
		this.emp_dependent_dob = emp_dependent_dob;
	}


	public String getEmp_dependent_mobile() {
		return emp_dependent_mobile;
	}


	public void setEmp_dependent_mobile(String emp_dependent_mobile) {
		this.emp_dependent_mobile = emp_dependent_mobile;
	}


	public String getEmp_dependent_email_id() {
		return emp_dependent_email_id;
	}


	public void setEmp_dependent_email_id(String emp_dependent_email_id) {
		this.emp_dependent_email_id = emp_dependent_email_id;
	}


	public String getEmp_dependents_ident_type() {
		return emp_dependents_ident_type;
	}


	public void setEmp_dependents_ident_type(String emp_dependents_ident_type) {
		this.emp_dependents_ident_type = emp_dependents_ident_type;
	}


	public String getEmp_dependents_ident_no() {
		return emp_dependents_ident_no;
	}


	public void setEmp_dependents_ident_no(String emp_dependents_ident_no) {
		this.emp_dependents_ident_no = emp_dependents_ident_no;
	}


	public String getMaker() {
		return maker;
	}


	public void setMaker(String maker) {
		this.maker = maker;
	}


	public String getLevel1Checker() {
		return level1Checker;
	}


	public void setLevel1Checker(String level1Checker) {
		this.level1Checker = level1Checker;
	}


	public java.util.Date getLevel1CheckDate() {
		return level1CheckDate;
	}


	public void setLevel1CheckDate(java.util.Date level1CheckDate) {
		this.level1CheckDate = level1CheckDate;
	}


	public String getLevel1Remarks() {
		return level1Remarks;
	}


	public void setLevel1Remarks(String level1Remarks) {
		this.level1Remarks = level1Remarks;
	}


	public String getLevel2Checker() {
		return level2Checker;
	}


	public void setLevel2Checker(String level2Checker) {
		this.level2Checker = level2Checker;
	}


	public java.util.Date getLevel2CheckDate() {
		return level2CheckDate;
	}


	public void setLevel2CheckDate(java.util.Date level2CheckDate) {
		this.level2CheckDate = level2CheckDate;
	}


	public String getLevel2Remarks() {
		return level2Remarks;
	}


	public void setLevel2Remarks(String level2Remarks) {
		this.level2Remarks = level2Remarks;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public java.util.Date getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(java.util.Date approvedDate) {
		this.approvedDate = approvedDate;
	}


	public String getApprovedRemarks() {
		return approvedRemarks;
	}


	public void setApprovedRemarks(String approvedRemarks) {
		this.approvedRemarks = approvedRemarks;
	}


	public String getUpdateBy() {
		return updateBy;
	}


	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}


	public String getUpdateRemarks() {
		return updateRemarks;
	}


	public void setUpdateRemarks(String updateRemarks) {
		this.updateRemarks = updateRemarks;
	}


	public int getRowid() {
		return rowid;
	}


	public void setRowid(int rowid) {
		this.rowid = rowid;
	}


	

	public String getEmpdepstatus() {
		return empdepstatus;
	}


	public void setEmpdepstatus(String empdepstatus) {
		this.empdepstatus = empdepstatus;
	}


	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}


	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}


	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	
	public String getDeprelation() {
		return deprelation;
	}


	public void setDeprelation(String deprelation) {
		this.deprelation = deprelation;
	}


	public String getDepgender() {
		return depgender;
	}


	public void setDepgender(String depgender) {
		this.depgender = depgender;
	}


	@Override
	public String toString() {
		return "Employee_Dependents [emp_dependents_id=" + emp_dependents_id + ", emp_dependent_dob="
				+ emp_dependent_dob + ", emp_dependent_mobile=" + emp_dependent_mobile + ", emp_dependent_email_id="
				+ emp_dependent_email_id + ", emp_dependents_ident_type=" + emp_dependents_ident_type
				+ ", emp_dependents_ident_no=" + emp_dependents_ident_no + ", maker=" + maker + ", level1Checker="
				+ level1Checker + ", level1CheckDate=" + level1CheckDate + ", level1Remarks=" + level1Remarks
				+ ", level2Checker=" + level2Checker + ", level2CheckDate=" + level2CheckDate + ", level2Remarks="
				+ level2Remarks + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", approvedRemarks="
				+ approvedRemarks + ", updateBy=" + updateBy + ", updateRemarks=" + updateRemarks + ", rowid=" + rowid
				+ ", status=" + empdepstatus + ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime
				+ "]";
	}



	
	
}