package NUALS.AMS.ACADEMIC.CENTRES;

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

import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;



@Entity
@EntityListeners(AuditingEntityListener.class)
public class CENTRE
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int centre_id;

	@Column(name = "centre_code", nullable = false, length=30, unique=true)	
	private String centre_code;
	
	@Column(name = "centre_name", nullable = false, length=200, unique=true)
	private String  centre_name;
	
	@Column(name = "centre_address", nullable = false, length=500)
	private String centre_address ;
	
	@Column(name = "centre_activities", nullable = false, length=500)
	private String  centre_activities;
	
	@Column(name = "description", nullable = false, length=500)
	private String  description;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="centre_hod",referencedColumnName="emp_id")
	private Employee_Master centre_hod;
	

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
	
	
	
	@Column(name = "centrestatus", nullable = false,columnDefinition = "varchar(20) default 'submitted'")
	private String centrestatus;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDateTime;

    
	public CENTRE() {
	}

	public CENTRE(String centre_id)
	{
		this.centre_id  = Integer.parseInt(centre_id);
	}

	public int getCentre_id() {
		return centre_id;
	}

	public void setCentre_id(int centre_id) {
		this.centre_id = centre_id;
	}

	public String getCentre_code() {
		return centre_code;
	}

	public void setCentre_code(String centre_code) {
		this.centre_code = centre_code;
	}

	public String getCentre_name() {
		return centre_name;
	}

	public void setCentre_name(String centre_name) {
		this.centre_name = centre_name;
	}

	public String getCentre_address() {
		return centre_address;
	}

	public void setCentre_address(String centre_address) {
		this.centre_address = centre_address;
	}

	public String getCentre_activities() {
		return centre_activities;
	}

	public void setCentre_activities(String centre_activities) {
		this.centre_activities = centre_activities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee_Master getCentre_hod() {
		return centre_hod;
	}

	public void setCentre_hod(Employee_Master centre_hod) {
		this.centre_hod = centre_hod;
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

	public String getCentrestatus() {
		return centrestatus;
	}

	public void setCentrestatus(String centrestatus) {
		this.centrestatus = centrestatus;
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
		return "CENTRE [centre_id=" + centre_id + ", centre_code=" + centre_code + ", centre_name=" + centre_name
				+ ", centre_address=" + centre_address + ", centre_activities=" + centre_activities + ", description="
				+ description + ", centre_hod=" + centre_hod + ", maker=" + maker + ", level1Checker=" + level1Checker
				+ ", level1CheckDate=" + level1CheckDate + ", level1Remarks=" + level1Remarks + ", level2Checker="
				+ level2Checker + ", level2CheckDate=" + level2CheckDate + ", level2Remarks=" + level2Remarks
				+ ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", approvedRemarks="
				+ approvedRemarks + ", updateBy=" + updateBy + ", updateRemarks=" + updateRemarks + ", rowid=" + rowid
				+ ", centrestatus=" + centrestatus + ", createDateTime=" + createDateTime + ", updateDateTime="
				+ updateDateTime + "]";
	}
	
	

	
}
