package NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS;

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
public class Employee_Personal
{
	@Id	
	@Column(name = "emp_id", nullable = false, length=50)
	private String emp_id;

	/*
	@Column(name = "emp_name", nullable = false, length=100)
	private String emp_name;
	*/
	
	@Column(name = "emp_dob", nullable = false)
	private Date emp_dob;	
	
	@Column(name = "emp_address", nullable = false, length=100)
	private String emp_address;
	
	@Column(name = "emp_state", nullable = false, length=100)
	private String emp_state;
	
	@Column(name = "emp_district", nullable = false, length=100)
	private String emp_district;
	
	@Column(name = "emp_city", nullable = false, length=100)
	private String emp_city;
	
	@Column(name = "emp_city_pincode", nullable = false, length=100)
	private String emp_city_pincode;
		
	@Column(name = "emp_mobile_land", nullable = true, length=50)
	private String emp_mobile_land;
	
	@Column(name = "emp_email_id", nullable = true, length=50)
	private String emp_email_id;
	
	@Column(name = "emp_ident_type", nullable = false, length=50)
	private String emp_ident_type;
	
	@Column(name = "emp_ident_no", nullable = false, length=50)
	private String emp_ident_no;
	
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
		
	@Column(name = "personalstatus", nullable = false,columnDefinition = "varchar(20) default 'submit'")
	private String personalstatus;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDateTime;

	public Employee_Personal() {
	}
	

	public Employee_Personal(String emp_id)
	{
		this.emp_id = emp_id;
	}


	public Employee_Personal(String emp_id, Date emp_dob, String emp_address, String emp_state, String emp_district,
			String emp_city, String emp_city_pincode, String emp_mobile_land, String emp_email_id,
			String emp_ident_type, String emp_ident_no, String maker, String level1Checker,
			java.util.Date level1CheckDate, String level1Remarks, String level2Checker, java.util.Date level2CheckDate,
			String level2Remarks, String approvedBy, java.util.Date approvedDate, String approvedRemarks,
			String updateBy, String updateRemarks, int rowid, String personalstatus, LocalDateTime createDateTime,
			LocalDateTime updateDateTime) {
		this.emp_id = emp_id;
		this.emp_dob = emp_dob;
		this.emp_address = emp_address;
		this.emp_state = emp_state;
		this.emp_district = emp_district;
		this.emp_city = emp_city;
		this.emp_city_pincode = emp_city_pincode;
		this.emp_mobile_land = emp_mobile_land;
		this.emp_email_id = emp_email_id;
		this.emp_ident_type = emp_ident_type;
		this.emp_ident_no = emp_ident_no;
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
		this.personalstatus = personalstatus;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}


	public String getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}


	public Date getEmp_dob() {
		return emp_dob;
	}


	public void setEmp_dob(Date emp_dob) {
		this.emp_dob = emp_dob;
	}


	public String getEmp_address() {
		return emp_address;
	}


	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}


	public String getEmp_state() {
		return emp_state;
	}


	public void setEmp_state(String emp_state) {
		this.emp_state = emp_state;
	}


	public String getEmp_district() {
		return emp_district;
	}


	public void setEmp_district(String emp_district) {
		this.emp_district = emp_district;
	}


	public String getEmp_city() {
		return emp_city;
	}


	public void setEmp_city(String emp_city) {
		this.emp_city = emp_city;
	}


	public String getEmp_city_pincode() {
		return emp_city_pincode;
	}


	public void setEmp_city_pincode(String emp_city_pincode) {
		this.emp_city_pincode = emp_city_pincode;
	}


	public String getEmp_mobile_land() {
		return emp_mobile_land;
	}


	public void setEmp_mobile_land(String emp_mobile_land) {
		this.emp_mobile_land = emp_mobile_land;
	}


	public String getEmp_email_id() {
		return emp_email_id;
	}


	public void setEmp_email_id(String emp_email_id) {
		this.emp_email_id = emp_email_id;
	}


	public String getEmp_ident_type() {
		return emp_ident_type;
	}


	public void setEmp_ident_type(String emp_ident_type) {
		this.emp_ident_type = emp_ident_type;
	}


	public String getEmp_ident_no() {
		return emp_ident_no;
	}


	public void setEmp_ident_no(String emp_ident_no) {
		this.emp_ident_no = emp_ident_no;
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


	public String getPersonalstatus() {
		return personalstatus;
	}


	public void setPersonalstatus(String personalstatus) {
		this.personalstatus = personalstatus;
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


	@Override
	public String toString() {
		return "Employee_Personal [emp_id=" + emp_id + ", emp_dob=" + emp_dob + ", emp_address=" + emp_address
				+ ", emp_state=" + emp_state + ", emp_district=" + emp_district + ", emp_city=" + emp_city
				+ ", emp_city_pincode=" + emp_city_pincode + ", emp_mobile_land=" + emp_mobile_land + ", emp_email_id="
				+ emp_email_id + ", emp_ident_type=" + emp_ident_type + ", emp_ident_no=" + emp_ident_no + ", maker="
				+ maker + ", level1Checker=" + level1Checker + ", level1CheckDate=" + level1CheckDate
				+ ", level1Remarks=" + level1Remarks + ", level2Checker=" + level2Checker + ", level2CheckDate="
				+ level2CheckDate + ", level2Remarks=" + level2Remarks + ", approvedBy=" + approvedBy
				+ ", approvedDate=" + approvedDate + ", approvedRemarks=" + approvedRemarks + ", updateBy=" + updateBy
				+ ", updateRemarks=" + updateRemarks + ", rowid=" + rowid + ", personalstatus=" + personalstatus
				+ ", createDateTime=" + createDateTime + ", updateDateTime=" + updateDateTime + "]";
	}
	
	
	

	
	
}
