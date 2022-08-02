package NUALS.AMS.EMS.EMPLOYEE_MASTER;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;



public class LOGINS_CLASS implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String usercode;
	private String username;
	private String email;
	private String mobile;
	private String active;
	
	
	public LOGINS_CLASS() {
	}


	public LOGINS_CLASS(String usercode, String username, String email, String mobile, String active) {
		this.usercode = usercode;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.active = active;
	}


	public String getUsercode() {
		return usercode;
	}


	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "LOGINS [usercode=" + usercode + ", username=" + username + ", email=" + email + ", mobile=" + mobile
				+ ", active=" + active + "]";
	}

	
	
	
}
