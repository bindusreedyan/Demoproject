package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS.FinallyApprovedParticipationDetailsByUser;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
@Repository
public interface ActivityMasterRepository extends CrudRepository<ActivityMaster,Integer> {
	
	 @Query(value="SELECT * FROM activity_master WHERE activity_code =:activityCode",nativeQuery=true)
	 ActivityMaster findActivityByActivityCode(@Param("activityCode") int activityCode);
	
	 @Query(value="select count(as_number_prefix) from order_genaration",nativeQuery=true)
	 int getCountofAsNo();
	 
	 @Query(value="SELECT * FROM activity_master WHERE status =:status",nativeQuery=true)
	 List<ActivityMaster> findAllActivityByStatus(@Param("status") String status);
	 
	 @Query(value="SELECT * FROM activity_master WHERE status ='studentsubmitted'",nativeQuery=true)
	 List<ActivityMaster> getAllActivityForStudentSubmitted(@Param("status") String status);
	 
	 
	 
	 
	 //@Query(value="SELECT * FROM activity_master WHERE status =:status and faculty_code =: facultyId",nativeQuery=true)
	 @Query(value="SELECT * FROM activity_master WHERE faculty_code =?1 and status in ('finalapproved','preapproved')",nativeQuery=true)
	 List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniator(String facultyId, String status1);
	 
	 //@Query(value="SELECT * FROM activity_master WHERE status =:status and faculty_code =: facultyId",nativeQuery=true)
	 @Query(value="select * from activity_master where user_type='student' and entered_by=?1 and status='finalapproved' ",nativeQuery=true)
	 List<ActivityMaster> getAllVerifiedActivitiesByStudent(String student);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE faculty_code =?1 and status=?2",nativeQuery=true)
	 List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniatorBystatus(String facultyId, String status1);
	 
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE entered_by =?1 and status in ('finalapproved','preapproved')",nativeQuery=true)
	 List<ActivityMaster> getAllActivityfinallyApprovedByUser(String facultyId, String status1);
	 
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE faculty_code =:facultyId",nativeQuery=true)
	 List<ActivityMaster> getAllActivitiesByFacultyCordniator(@Param("facultyId") String facultyId);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE status in ('finalapproved','preapproved')",nativeQuery=true)
	 List<ActivityMaster> findAllActivityApproved();
	 
	 
	// @Query(value="SELECT * FROM activity_master WHERE adminofficeapproves_status='administrative office verified' and admin_approved_need='yes'",nativeQuery=true)
	 @Query(value="SELECT * FROM activity_master WHERE admin_approved_need='yes'",nativeQuery=true)
	 List<ActivityMaster> getAllActivityForAdministrativeApproved();
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE status='administrative office verified'",nativeQuery=true)
	 List<ActivityMaster> getAllActivityForAdministrativeApprovedBylevel2();
	 
	 
	 
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE admin_approved_need='yes'",nativeQuery=true)
	 List<ActivityMaster> getAllActivityForAdministrativeOfficeApproved();
	 
	 
	 
	// SELECT * FROM activity_master WHERE activity_type_code in (SELECT activity_type_code  FROM academic_activity_types WHERE activity_type_group='academic') and status='submitted' and finance_implied='Y'
	 
	// @Query(value=" SELECT * FROM activity_master WHERE activity_type_code in (SELECT activity_type_code  FROM academic_activity_types WHERE activity_type_group='academic') and status='submitted'",nativeQuery=true)
	 @Query(value=" SELECT * FROM activity_master WHERE activity_type_code in (SELECT activity_type_code  FROM academic_activity_types WHERE activity_type_group='academic') and status in('submitted','faculty recommended')",nativeQuery=true)
	 List<ActivityMaster> getAllActivityForAcademicApproved();  
	 
	 
	 //@Query(value="SELECT * FROM activity_master WHERE status =:status and faculty_code =: facultyId",nativeQuery=true)
	 @Query(value="SELECT * FROM activity_master WHERE faculty_code =:facultyId and status =:status1 and finance_implied='Y'",nativeQuery=true)
	 List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniatorForAdvanceRequest(@Param("facultyId") String facultyId,@Param("status1") String status1);
	 
	 
	 @Query(value="SELECT COUNT(title) FROM activity_master WHERE title =:des",nativeQuery=true)
	 int findActivityRecordByDes(@Param("des") String des);
	 
	 @Query(value="select  aat.activity_type_group as programCat,aat.activity_type_description as programType,am.title,am.date_from as from, am.date_to as to, af.total_estimated as totalExp, em.emp_name as empName, am.activity_code as activityCode, am.status as programStatus,af.total_asgranted as asAmount, am.final_approved_date as asSanctionDate from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and am.faculty_code=cast(em.emp_id AS text)",nativeQuery=true)
	 List<AcademicProgramReport> getAllAcademicProgramReport();
	 
	 @Query(value="select  aat.activity_type_group as programCat,aat.activity_type_description as programtype,am.title,am.date_from as from, am.date_to as to, af.total_estimated as totalExp, em.emp_name as empName, am.activity_code as activityCode, am.status as programStatus,af.total_asgranted as asAmount, am.final_approved_date as asSanctionDate,adv_exp_settlement.bal_amount_tobe_paid as expAmountSanctioned,adv_exp_settlement.approved_date as sanctionedDate from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em,adv_exp_settlement where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and am.faculty_code=cast(em.emp_id AS text) and am.activity_code=adv_exp_settlement.activity_code and adv_exp_settlement.adv_settlement_status='sanctioned'",nativeQuery=true)
	 List<AcademicProgramReport> getAllExpenditureSettled();
	 
	 
	 @Query(value="select  aat.activity_type_group as programCat,aat.activity_type_description as programType,am.title,am.date_from as from, am.date_to as to, af.total_estimated as totalExp, em.emp_name as empName, am.activity_code as activityCode, am.status as programStatus,af.total_asgranted as asAmount, am.final_approved_date as asSanctionDate from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and am.faculty_code=cast(em.emp_id AS text) and am.faculty_code =?1",nativeQuery=true)
	 List<AcademicProgramReport> getAllProgramReportByfaculty(String facultyId);
	 
	 
	 
	 @Query(value="select  aat.activity_type_group as programCat,aat.activity_type_description as programType,am.title,am.date_from as from, am.date_to as to, af.total_estimated as totalExp, em.emp_name as empName, am.activity_code as activityCode, am.status as programStatus,af.total_asgranted as asAmount, am.final_approved_date as asSanctionDate,pr.advance_paid as advReqAmnt,pr.purpose as advPurpose,pr.entered_date as advanceIssuedDate from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em,program_approval_advance_payment as pr where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and am.faculty_code=cast(em.emp_id AS text) and am.activity_code=pr.activity_code",nativeQuery=true)
	 List<AdvanceRequestReportInterface> getAllPendingAdvanceSettlement();
	 
	 
	 @Query(value="select  aat.activity_type_group as programCat,aat.activity_type_description as programType,am.title,am.date_from as from, am.date_to as to, af.total_estimated as totalExp, em.emp_name as empName, am.activity_code as activityCode, am.status as programStatus,af.total_asgranted as asAmount, am.final_approved_date as asSanctionDate from activity_master am,academic_activity_types aat,activity_finance af,employee_master as em,activity_center where aat.activity_type_code=am.activity_type_code and am.activity_code=af.activity_code and am.faculty_code=cast(em.emp_id AS text) and activity_center.activity_code=am.activity_code and activity_center.centre_id=?1",nativeQuery=true)
	 List<AcademicProgramReport> getAllAcademicProgramReportByCenter(int centerId);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE status in ('finalapproved','preapproved') ",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinalAndPreApproved();

/*	 @Query(value="SELECT * FROM activity_master WHERE status in ('finalapproved') and as_orders_issued='Not Issued'",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinalApprovedForAsIssue();
	 */
	 
	 @Query(value="SELECT * FROM activity_master WHERE status in ('finalapproved')",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinalApprovedForAsIssue();
	 
	 @Query(value="select activity_finance.total_estimated as tes ,activity_master.activity_code as ac,activity_center.estimated_exp as cestim  from activity_finance,activity_master,activity_center where activity_center.centre_id=?1 and activity_center.activity_code=activity_master.activity_code and activity_finance.activity_code=activity_master.activity_code and activity_master.status='submitted' and activity_master.financial_year=?2",nativeQuery=true)
     List<GetTotalEstimatedExpByCenter> getEsimatedExpenseByCenterId(int centerId,String finyear);
	 
	 @Query(value="select activity_finance.total_asgranted as ta,activity_master.activity_code as ac,activity_center.estimated_exp as cestim from activity_finance, activity_master,activity_center where activity_center.centre_id=?1 and activity_center.activity_code=activity_master.activity_code and activity_finance.activity_code=activity_master.activity_code and activity_master.status in ('finalapproved','administrativeapproved','financially approved') and activity_master.financial_year=?2",nativeQuery=true)
	  List<GetTotalEstimatedAsGrantByCenter> getEsimatedExpenseByCenterIdByApproved(int centerId,String finyear);
	 
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE admin_approved_by=?1",nativeQuery=true)
	 List<ActivityMaster> findAllActivityAcadmicApprovedByLogin(String usercode);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE administrative_approved_by=?1 and status in ('administrativeapproved') ",nativeQuery=true)
	 List<ActivityMaster> findAllActivityAdministrativeApprovedByLogin(String usercode);
	 
	 @Query(value="SELECT * FROM activity_master WHERE financial_approved_by=?1 and  status in ('financially approved','financially rejected') ",nativeQuery=true)
	 List<ActivityMaster> findAllActivityFinancialApprovedByLogin(String usercode);
	 
	 @Query(value="SELECT * FROM activity_master WHERE finance_office_verified_by=?1 and  status in ('finance office verified','finance office rejected') ",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinancialOfficeApprovedByLogin(String usercode);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE final_office_approved_by=?1 and  status in ('final office approved') ",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinalOfficeApprovedByLogin(String usercode);
	 
	 
	 @Query(value="SELECT * FROM activity_master WHERE final_approved_by=?1 and  status in ('finalapproved','finally rejected') ",nativeQuery=true)
	 List<ActivityMaster> getAllActivityFinallyApprovedBuLogin(String usercode);
	 
	 
	 @Query(value="select am.activity_level as activityLevel,aat.activity_type_code as activityTypeCode,aat.activity_type_description as typeDescription from academic_activity_types aat,activity_master am, activity_center ac   where am.activity_type_code=aat.activity_type_code\r\n" + 
		 		"\r\n" + 
		 		"and am.activity_level='national' and aat.activity_type_description in('Seminars & Workshops','Seminar') and ac.centre_id=?1 and am.financial_year=?2 and ac.activity_code=am.activity_code",nativeQuery=true)
		 List<ProgramCount> getActivityCountBasedOnactivitytype(int centreId,String finYear);
	 
	 
	 
	 @Query(value="select am.activity_level as activityLevel,aat.activity_type_code as activityTypeCode,aat.activity_type_description as typeDescription from academic_activity_types aat,activity_master am, activity_center ac   where am.activity_type_code=aat.activity_type_code\r\n" + 
		 		"\r\n" + 
		 		"aat.activity_type_description in('Lecture') and ac.centre_id=?1 and am.financial_year=?2 and ac.activity_code=am.activity_code",nativeQuery=true)
		 List<ProgramCount> getActivityCountBasedOnactivitytypeLecture(int centreId,String finYear);
	 
	 
	 @Query(value="select activity_code as activitycode,financial_year as finyear,title as title,faculty_code as facultycode,status from \r\n" + 
	 		"activity_master where activity_code not in(select activity_code from program_approval_advance_payment) and activity_code not in(select activity_code from adv_exp_settlement) and status='finalapproved' ",nativeQuery=true)
	 List<ReApprovedActivityInterface> getAllActivitesForRevision();
	  
	 @Query(value="select activity_code as activitycode,financial_year as finyear,title as title,faculty_code as facultycode,status from \r\n" + 
		 		"activity_master where activity_code not in(select activity_code from program_approval_advance_payment) and activity_code not in(select activity_code from adv_exp_settlement) and status in('submitted','finalapproved')",nativeQuery=true)
      List<ReApprovedActivityInterface> getAllActivitesForCancel();
	 
	 
}
