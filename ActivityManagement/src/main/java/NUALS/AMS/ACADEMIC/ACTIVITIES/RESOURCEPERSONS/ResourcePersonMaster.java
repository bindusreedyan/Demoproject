package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@EntityListeners(AuditingEntityListener.class)
public class ResourcePersonMaster {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private int resId;
	 
	 String name;
	 
	 String designation;
	 
	 String organisation;
	 
     String domain;
	 
	 String category;
	 
	 String address;
	 
	 String contactName;
	 
	 String contactPhone;
	 
	 String email;
	 
	 String panNo;
	 
	 String accountNo;
	 
	 String bankName;
	 
	 String branch;
	 
	 String accountType;
	 
	 String ifsc;
	 
	 String status;
	 
	 String resType;
	 
	 String passportNo;
	 
	 public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	@Override
	public String toString() {
		return "ResourcePersonMaster [resId=" + resId + ", name=" + name + ", designation=" + designation
				+ ", organisation=" + organisation + ", domain=" + domain + ", category=" + category + ", address="
				+ address + ", contactName=" + contactName + ", contactPhone=" + contactPhone + ", email=" + email
				+ ", panNo=" + panNo + ", accountNo=" + accountNo + ", bankName=" + bankName + ", branch=" + branch
				+ ", accountType=" + accountType + ", ifsc=" + ifsc + ", status=" + status + ", resType=" + resType
				+ ", passportNo=" + passportNo + "]";
	}

	
	 
	 
	 
	

}
