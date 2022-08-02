package NUALS.AMS.ACADEMIC.VENDORS;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class VendorMaster {

	 @Id
     @Column(name = "vendorId",nullable = false,  length=50)	
     @GeneratedValue(strategy=GenerationType.AUTO)
     private int vendorId;
	 
	 @Column(name = "businessName",nullable = false,  length=50)	
	 private String businessName;
	 
	 
	 @Column(name = "constitution",nullable = true,  length=50)	
	 private String constitution;
	 
	 @Column(name = "address",nullable = true,  length=100)	
	 private String address;
	 
	 @Column(name = "pinCode",nullable = true)	
	 private String pinCode;
	 
	 
	 @Column(name = "contactPerson",nullable = true)	
	 private String contactPerson;
	 
	 
	 @Column(name = "contactPhone",nullable = true)	
	 private String contactPhone;
	 
	 
	 @Column(name = "email",nullable = true)	
	 private String email;
	 
	 @Column(name = "panNo",nullable = true)	
	 private String panNo;
	 
	 
	 @Column(name = "gstNo",nullable = true)	
	 private String gstNo;
	 
	
	 @Column(name = "accountNo",nullable = true)	
	 private String accountNo;
	 
	 @Column(name = "bankName",nullable = true)	
	 private String bankName;
	 
	 @Column(name = "branch",nullable = true)	
	 private String branch;
	 
	 @Column(name = "accountType",nullable = true)	
	 private String accountType;
	 
	 @Column(name = "ifsc",nullable = true)	
	 private String ifsc;
	 
	 @Column(name = "vendorStatus",nullable = true)	
	 private String vendorStatus;
	 
	 private String enteredBy;
	 private String enteredRamarks;
	 private java.util.Date enteredDate;
	 
	 
	 private String vendorCheckedBy;
	 private java.util.Date vendorCheckedDate;
	 private  String vendorCheckedRemarks;
	 
	 
	 private java.util.Date vendorVerifiedDate;
	 private String vendorVerifiedRemarks;
	 private  String vendorVerifiedBy;
	 
	 
	 private java.util.Date vendorApprovedDate;
	 private String vendorApprovedRemarks;
	 private  String vendorApprovedBy;

	 private String approvedStatus;
	 
	 
	 
	 
	public String getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(String approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public VendorMaster(String vendorId) {
		super();
		this.vendorId = Integer.parseInt(vendorId);
	}

	public VendorMaster()
	{
		
	}
	
	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
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

	public String getVendorStatus() {
		return vendorStatus;
	}

	public void setVendorStatus(String vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	public String getEnteredRamarks() {
		return enteredRamarks;
	}

	public void setEnteredRamarks(String enteredRamarks) {
		this.enteredRamarks = enteredRamarks;
	}

	public java.util.Date getEnteredDate() {
		return enteredDate;
	}

	public void setEnteredDate(java.util.Date enteredDate) {
		this.enteredDate = enteredDate;
	}

	public String getVendorCheckedBy() {
		return vendorCheckedBy;
	}

	public void setVendorCheckedBy(String vendorCheckedBy) {
		this.vendorCheckedBy = vendorCheckedBy;
	}

	public java.util.Date getVendorCheckedDate() {
		return vendorCheckedDate;
	}

	public void setVendorCheckedDate(java.util.Date vendorCheckedDate) {
		this.vendorCheckedDate = vendorCheckedDate;
	}

	public String getVendorCheckedRemarks() {
		return vendorCheckedRemarks;
	}

	public void setVendorCheckedRemarks(String vendorCheckedRemarks) {
		this.vendorCheckedRemarks = vendorCheckedRemarks;
	}

	public java.util.Date getVendorVerifiedDate() {
		return vendorVerifiedDate;
	}

	public void setVendorVerifiedDate(java.util.Date vendorVerifiedDate) {
		this.vendorVerifiedDate = vendorVerifiedDate;
	}

	public String getVendorVerifiedRemarks() {
		return vendorVerifiedRemarks;
	}

	public void setVendorVerifiedRemarks(String vendorVerifiedRemarks) {
		this.vendorVerifiedRemarks = vendorVerifiedRemarks;
	}

	public String getVendorVerifiedBy() {
		return vendorVerifiedBy;
	}

	public void setVendorVerifiedBy(String vendorVerifiedBy) {
		this.vendorVerifiedBy = vendorVerifiedBy;
	}

	public java.util.Date getVendorApprovedDate() {
		return vendorApprovedDate;
	}

	public void setVendorApprovedDate(java.util.Date vendorApprovedDate) {
		this.vendorApprovedDate = vendorApprovedDate;
	}

	public String getVendorApprovedRemarks() {
		return vendorApprovedRemarks;
	}

	public void setVendorApprovedRemarks(String vendorApprovedRemarks) {
		this.vendorApprovedRemarks = vendorApprovedRemarks;
	}

	public String getVendorApprovedBy() {
		return vendorApprovedBy;
	}

	public void setVendorApprovedBy(String vendorApprovedBy) {
		this.vendorApprovedBy = vendorApprovedBy;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
