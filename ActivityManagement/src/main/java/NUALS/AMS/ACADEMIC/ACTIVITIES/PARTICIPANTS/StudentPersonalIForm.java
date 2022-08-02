package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;

public interface StudentPersonalIForm {
	
	 String getStudent_personal_student_name();
	 String getStudent_admission_roll_no();
	 String getContact_number();
	 String getOnward_start_point();
	 Date getOnward_start_date();
	 String getOnward_modeof_travel();
	 Date getOnward_end_date();
	 String getOnward_destination();
	 String getOn_ward_est_expenditure();
	 
	 String getReturn_start_point();
	 Date getReturn_start_date();
	 String getReturn_modeof_travel();
	 String getReturn_end_date();
	 String getReturn_destination();
	 String getReturn_est_expenditure();

	Date getAccmdation_start_date();
	 Date getAccmdation_end_date();
	 String getAccomodation_exp_head();
	 double getAccmdtion_estim_expenditure();
	 String getBill_no();
	 String getDaily_rate();
	 String getNo_of_days();
	 String getFree_of_cost_accomadation();
	 double getOnward_amount_admitted();
	 double getReturn_amount_admitted();
	 double getAmount_admitted();
	 
	 double getOnward_amount_claimed();
	 double getReturn_amount_claimed();
	 double getAmount_claimed();
}
