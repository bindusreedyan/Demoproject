package NUALS.AMS.EMS.EMPLOYEE_MASTER;

import java.sql.Date;
import java.time.LocalDateTime;

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

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;
import NUALS.AMS.EMS.EMPLOYMENT_TYPE.Employment_Type;




@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee_Master
{
	@Id	
	@Column(name = "emp_id", nullable = false, length=50)
	private String emp_id;
	
	@Column(name = "emp_name", nullable = false, length=100,columnDefinition = "varchar(100) default ''")
	private String emp_name;
	
	@Column(name = "gender", nullable = false, length=100,columnDefinition = "varchar(100) default ''")
	private String gender;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="designation",referencedColumnName="design_id")
	private DESIGNATION designation;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="emp_joining_dept",referencedColumnName="dept_id")	
	private DEPARTMENT emp_joining_dept;
	
	
	@Column(name = "joining_date", nullable = false)
	private Date joining_date;
	
	@Column(name = "emp_pan_no", nullable = true, length=50)
	private String emp_pan_no;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="empl_appoint_type",referencedColumnName="emplmnt_type_id")
	private Employment_Type empl_appoint_type;
	
	
	@Column(name = "emp_work_type", nullable = false, length=100)
	private String emp_work_type;
	
	
	
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

	
	@Column(name = "empmasterstatus", nullable = false,columnDefinition = "varchar(20) default 'submitted'")
	private String empmasterstatus;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDateTime;
    
    

	public Employee_Master() {
	}
	
	public Employee_Master(String emp_id)
	{
		this.emp_id = emp_id;
	}

	public Employee_Master(String emp_id, String emp_name, String gender, DESIGNATION designation,
			DEPARTMENT emp_joining_dept, Date joining_date, String emp_pan_no, Employment_Type empl_appoint_type,
			String emp_work_type, String maker, String level1Checker, java.util.Date level1CheckDate,
			String level1Remarks, String level2Checker, java.util.Date level2CheckDate, String level2Remarks,
			String approvedBy, java.util.Date approvedDate, String approvedRemarks, String updateBy,
			String updateRemarks, String empmasterstatus, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.gender = gender;
		this.designation = designation;
		this.emp_joining_dept = emp_joining_dept;
		this.joining_date = joining_date;
		this.emp_pan_no = emp_pan_no;
		this.empl_appoint_type = empl_appoint_type;
		this.emp_work_type = emp_work_type;
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
		this.empmasterstatus = empmasterstatus;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public DESIGNATION getDesignation() {
		return designation;
	}

	public void setDesignation(DESIGNATION designation) {
		this.designation = designation;
	}

	public DEPARTMENT getEmp_joining_dept() {
		return emp_joining_dept;
	}

	public void setEmp_joining_dept(DEPARTMENT emp_joining_dept) {
		this.emp_joining_dept = emp_joining_dept;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}

	public String getEmp_pan_no() {
		return emp_pan_no;
	}

	public void setEmp_pan_no(String emp_pan_no) {
		this.emp_pan_no = emp_pan_no;
	}

	public Employment_Type getEmpl_appoint_type() {
		return empl_appoint_type;
	}

	public void setEmpl_appoint_type(Employment_Type empl_appoint_type) {
		this.empl_appoint_type = empl_appoint_type;
	}

	public String getEmp_work_type() {
		return emp_work_type;
	}

	public void setEmp_work_type(String emp_work_type) {
		this.emp_work_type = emp_work_type;
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

	public String getEmpmasterstatus() {
		return empmasterstatus;
	}

	public void setEmpmasterstatus(String empmasterstatus) {
		this.empmasterstatus = empmasterstatus;
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
	
	

	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "Employee_Master [emp_id=" + emp_id + ", emp_name=" + emp_name + ", gender=" + gender + ", designation="
				+ designation + ", emp_joining_dept=" + emp_joining_dept + ", joining_date=" + joining_date
				+ ", emp_pan_no=" + emp_pan_no + ", empl_appoint_type=" + empl_appoint_type + ", emp_work_type="
				+ emp_work_type + ", maker=" + maker + ", level1Checker=" + level1Checker + ", level1CheckDate="
				+ level1CheckDate + ", level1Remarks=" + level1Remarks + ", level2Checker=" + level2Checker
				+ ", level2CheckDate=" + level2CheckDate + ", level2Remarks=" + level2Remarks + ", approvedBy="
				+ approvedBy + ", approvedDate=" + approvedDate + ", approvedRemarks=" + approvedRemarks + ", updateBy="
				+ updateBy + ", updateRemarks=" + updateRemarks + ", empmasterstatus=" + empmasterstatus
				+ ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + "]";
	}

	

	
	
}
