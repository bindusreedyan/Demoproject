package NUALS.AMS.EMS.EMPLOYEE_MASTER;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;



public interface LOGINS_INTERFACE {
	
	 String getUsercode();
	 String getUsername();
	 String getEmail();
	 String getMobile();
	 String getActive();
	
}
