package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

public class FORM_FIELDS implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String emp_id;
	private String emp_dependent_name;
	
	private String emp_name;
	
	private String deprelation;
	private String depgender;
	
	private Date emp_dependent_dob;
	private String emp_dependent_mobile;	
	private String emp_dependent_email_id;	
	private String emp_dependents_ident_type;	
	private String emp_dependents_ident_no;
	private String status;
	
	private String updateRemarks;
	
	
	public FORM_FIELDS() {
	}


	public FORM_FIELDS(String emp_id, String emp_dependent_name, String emp_name, String deprelation, String depgender,
			Date emp_dependent_dob, String emp_dependent_mobile, String emp_dependent_email_id,
			String emp_dependents_ident_type, String emp_dependents_ident_no, String status, String updateRemarks) {
		this.emp_id = emp_id;
		this.emp_dependent_name = emp_dependent_name;
		this.emp_name = emp_name;
		this.deprelation = deprelation;
		this.depgender = depgender;
		this.emp_dependent_dob = emp_dependent_dob;
		this.emp_dependent_mobile = emp_dependent_mobile;
		this.emp_dependent_email_id = emp_dependent_email_id;
		this.emp_dependents_ident_type = emp_dependents_ident_type;
		this.emp_dependents_ident_no = emp_dependents_ident_no;
		this.status = status;
		this.updateRemarks = updateRemarks;
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


	public String getEmp_dependent_name() {
		return emp_dependent_name;
	}


	public void setEmp_dependent_name(String emp_dependent_name) {
		this.emp_dependent_name = emp_dependent_name;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUpdateRemarks() {
		return updateRemarks;
	}

	public void setUpdateRemarks(String updateRemarks) {
		this.updateRemarks = updateRemarks;
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
		return "REPORT_FIELDS [emp_id=" + emp_id + ", emp_dependent_name=" + emp_dependent_name + ", emp_name=" + emp_name
				+ ", deprelation=" + deprelation + ", depgender=" + depgender + ", emp_dependent_dob="
				+ emp_dependent_dob + ", emp_dependent_mobile=" + emp_dependent_mobile + ", emp_dependent_email_id="
				+ emp_dependent_email_id + ", emp_dependents_ident_type=" + emp_dependents_ident_type
				+ ", emp_dependents_ident_no=" + emp_dependents_ident_no + ", status=" + status + ", updateRemarks="
				+ updateRemarks + "]";
	}
	
	
	
	
	
}
