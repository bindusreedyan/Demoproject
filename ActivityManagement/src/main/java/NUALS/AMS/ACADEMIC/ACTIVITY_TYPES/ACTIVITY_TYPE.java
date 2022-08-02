package NUALS.AMS.ACADEMIC.ACTIVITY_TYPES;

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

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ACTIVITY_TYPE
{
	@Id
	private String code;

	@Column(name = "description", nullable = false, length=500)	
	private String description;
	
	@Column(name = "budgetCode", nullable = false, length=500)	
	private String budgetCode;
	
	
	@Column(name = "rowid", nullable = false)
    private int rowid;			
	
	@Column(name = "status", nullable = false,columnDefinition = "varchar(20) default 'valid'")
	private String status;

	@CreationTimestamp	
	@Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(nullable = true)
    private LocalDateTime updateDateTime;

    
	public ACTIVITY_TYPE() {
	}


	public ACTIVITY_TYPE(String code, String description, String budgetCode, int rowid, String status,
			LocalDateTime createDateTime, LocalDateTime updateDateTime) {
		this.code = code;
		this.description = description;
		this.budgetCode = budgetCode;
		this.rowid = rowid;
		this.status = status;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBudgetCode() {
		return budgetCode;
	}


	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}


	public int getRowid() {
		return rowid;
	}


	public void setRowid(int rowid) {
		this.rowid = rowid;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
		return "ACTIVITY_TYPE [code=" + code + ", description=" + description + ", budgetCode=" + budgetCode
				+ ", rowid=" + rowid + ", status=" + status + ", createDateTime=" + createDateTime + ", updateDateTime="
				+ updateDateTime + "]";
	}



	
	
}
