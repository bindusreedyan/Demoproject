package NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class FORM_FIELDS implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	
	private String emp_id;	
	private String emp_name;	
	private Date emp_dob;		
	private String emp_address;	
	private String emp_state;	
	private String emp_district;
	private String emp_city;	
	private String emp_city_pincode;	
	private String emp_mobile_land;
	private String emp_email_id;	
	private String emp_ident_type;	
	private String emp_ident_no;	
	private String personalstatus;
	
	
	public FORM_FIELDS() {
	}

	public FORM_FIELDS(String emp_id, String emp_name, Date emp_dob, String emp_address, String emp_state,
			String emp_district, String emp_city, String emp_city_pincode, String emp_mobile_land, String emp_email_id,
			String emp_ident_type, String emp_ident_no, String status) {
		this.emp_id = emp_id;
		this.emp_name = emp_name;
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
		this.personalstatus = status;
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



	public String getPersonalstatus() {
		return personalstatus;
	}

	public void setPersonalstatus(String personalstatus) {
		this.personalstatus = personalstatus;
	}

	@Override
	public String toString() {
		return "HOSTEL_ALLOTMENT_FORM_FIELDS [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_dob=" + emp_dob + ", emp_address="
				+ emp_address + ", emp_state=" + emp_state + ", emp_district=" + emp_district + ", emp_city=" + emp_city
				+ ", emp_city_pincode=" + emp_city_pincode + ", emp_mobile_land=" + emp_mobile_land + ", emp_email_id="
				+ emp_email_id + ", emp_ident_type=" + emp_ident_type + ", emp_ident_no=" + emp_ident_no + ", status="
				+ personalstatus + "]";
	}
	
	
}
