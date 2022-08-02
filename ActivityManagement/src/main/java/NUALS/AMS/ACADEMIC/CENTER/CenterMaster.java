package NUALS.AMS.ACADEMIC.CENTER;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class CenterMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int centreCode;

	@Column(name = "centerName",  length=500,nullable=false)	
	private String centerName;
		
	
	public int getCentreCode() {
		return centreCode;
	}

	public void setCentreCode(int centreCode) {
		this.centreCode = centreCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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



	@Column(name = "centeraddress", nullable = false,length=1000)
	private String address;
	
	@Column(name = "centerhod", nullable = false,length=50)
	private String hod;
	
	
	@Column(name = "status", nullable = false,length=50)
	private String status;
	

	@Column(name = "enteredDate", nullable = false)
    private Date createDateTime;
	
	@Column(name = "enteredBy", nullable = false)
    private String enteredBy;

}
