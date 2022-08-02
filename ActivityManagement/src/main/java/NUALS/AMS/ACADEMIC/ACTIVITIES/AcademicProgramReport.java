package NUALS.AMS.ACADEMIC.ACTIVITIES;

public interface AcademicProgramReport {
	
	 String getProgramCat();
     String getProgramType();
	 String getTitle();
	 String getFrom();
	 String getTo();
	 double getTotalExp();
	 String getProgramStatus();

	 String getEmpName();
     int getActivityCode();
	
	 String getCenter();
	 
	 String getAsAmount();
	 
	 String getAsSanctionDate();
	 
	 String getSanctionedDate();
	 
	 double getExpAmountSanctioned();
	 
	
	
	//String str="select  aat.activity_type_group as programCat,activity_typedescription programType,am.title,am.date_from as from,am.date_to as to,am.status,af.total_estimated as totalExp,em.emp_name from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and em.emp_id=am.faculty_code";

	}
