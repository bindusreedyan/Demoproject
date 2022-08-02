package NUALS.AMS.EMS.EMPLOYMENT_TYPE;

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
public class Employment_Type
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int emplmnt_type_id;

	@Column(name = "emplmnt_type_Name", nullable = false, length=30, unique=true)
	@NotBlank(message = "Employment Type must not be blank")
	private String emplmnt_type_Name;
	
	@Column(name = "emplmnt_type_description", nullable = false, length=200)
	private String  emplmnt_type_description;
	

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
		
	@Column(name = "emplmnttypestatus", nullable = false,columnDefinition = "varchar(20) default 'submitted'")
	private String emplmnttypestatus;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDateTime;

    
	public Employment_Type() {
	}

	public Employment_Type(String emplmnt_type_id)
	{
		this.emplmnt_type_id = Integer.parseInt(emplmnt_type_id);
	}

	public Employment_Type(int emplmnt_type_id,
			@NotBlank(message = "Employment Type must not be blank") String emplmnt_type_Name,
			String emplmnt_type_description, String maker, String level1Checker, Date level1CheckDate,
			String level1Remarks, String level2Checker, Date level2CheckDate, String level2Remarks, String approvedBy,
			Date approvedDate, String approvedRemarks, String updateBy, String updateRemarks, int rowid,
			String emplmnttypestatus, LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		this.emplmnt_type_id = emplmnt_type_id;
		this.emplmnt_type_Name = emplmnt_type_Name;
		this.emplmnt_type_description = emplmnt_type_description;
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
		this.emplmnttypestatus = emplmnttypestatus;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}

	public int getEmplmnt_type_id() {
		return emplmnt_type_id;
	}

	public void setEmplmnt_type_id(int emplmnt_type_id) {
		this.emplmnt_type_id = emplmnt_type_id;
	}

	public String getEmplmnt_type_Name() {
		return emplmnt_type_Name;
	}

	public void setEmplmnt_type_Name(String emplmnt_type_Name) {
		this.emplmnt_type_Name = emplmnt_type_Name;
	}

	public String getEmplmnt_type_description() {
		return emplmnt_type_description;
	}

	public void setEmplmnt_type_description(String emplmnt_type_description) {
		this.emplmnt_type_description = emplmnt_type_description;
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

	public String getEmplmnttypestatus() {
		return emplmnttypestatus;
	}

	public void setEmplmnttypestatus(String emplmnttypestatus) {
		this.emplmnttypestatus = emplmnttypestatus;
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
		return "Employment_Type [emplmnt_type_id=" + emplmnt_type_id + ", emplmnt_type_Name=" + emplmnt_type_Name
				+ ", emplmnt_type_description=" + emplmnt_type_description + ", maker=" + maker + ", level1Checker="
				+ level1Checker + ", level1CheckDate=" + level1CheckDate + ", level1Remarks=" + level1Remarks
				+ ", level2Checker=" + level2Checker + ", level2CheckDate=" + level2CheckDate + ", level2Remarks="
				+ level2Remarks + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", approvedRemarks="
				+ approvedRemarks + ", updateBy=" + updateBy + ", updateRemarks=" + updateRemarks + ", rowid=" + rowid
				+ ", emplmnttypestatus=" + emplmnttypestatus + ", createDateTime=" + createDateTime
				+ ", updateDateTime=" + updateDateTime + "]";
	}

	


    
    
    
	
}
