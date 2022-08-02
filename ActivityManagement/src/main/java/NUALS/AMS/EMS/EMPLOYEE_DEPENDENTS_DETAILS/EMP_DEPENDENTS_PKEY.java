package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//import NUALS.AMS.FEE.FEE_HEADS.SFS_FEE_HEAD;
//import NUALS.AMS.FEE.FEE_HEADS_GROUP.SFS_FEE_HEAD_GROUP;

@Embeddable
public class EMP_DEPENDENTS_PKEY  implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String emp_id;
	private String emp_dependent_name;
	
		
	public EMP_DEPENDENTS_PKEY() {
	}


	public EMP_DEPENDENTS_PKEY(String emp_id, String emp_dependent_name) {
		this.emp_id = emp_id;
		this.emp_dependent_name = emp_dependent_name;
	}


	public String getEmp_id() {
		return emp_id;
	}


	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}


	public String getEmp_dependent_name() {
		return emp_dependent_name;
	}


	public void setEmp_dependent_name(String emp_dependent_name) {
		this.emp_dependent_name = emp_dependent_name;
	}


	@Override
	public String toString() {
		return "EMP_DEPENDENTS_PKEY [emp_id=" + emp_id + ", emp_dependent_name=" + emp_dependent_name + "]";
	}
	
	

}
