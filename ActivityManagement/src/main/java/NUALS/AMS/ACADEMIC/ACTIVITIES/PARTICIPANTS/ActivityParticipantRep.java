package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityParticipantRep extends CrudRepository<ActivityParticipateMember,ActivityParticipationRequestKey>{
	
	 @Query(value="SELECT * FROM activity_participate_member WHERE roll_no =?1 and particiaption_request_id=?2",nativeQuery=true)
	 ActivityParticipateMember findByParticiapntRecord(String rollno,int participateRequestId);
	 
	 
	 @Query(value="SELECT * FROM activity_participate_member WHERE  particiaption_request_id=?1",nativeQuery=true)
	 List<ActivityParticipateMember> findPartipantDetailsByRequestId(int partipateRequestId);
	 
	 
	 @Query(value="select sp.student_personal_student_name,sadm.student_admission_roll_no,pm.contact_number,pm.onward_start_point,pm.onward_start_date,pm.onward_modeof_travel,pm.onward_end_date,pm.onward_destination,pm.on_ward_est_expenditure,pm.onward_amount_admitted,pm.onward_amount_claimed,pm.return_start_point,pm.return_start_date,pm.return_modeof_travel,pm.return_est_expenditure,pm.return_end_date,pm.return_destination,pm.return_amount_admitted, pm.return_amount_claimed,pm.accmdation_start_date, pm.accmdation_end_date,pm.accomodation_exp_head,pm.accmdtion_estim_expenditure,pm.bill_no,pm.daily_rate,pm.no_of_days,pm.free_of_cost_accomadation,pm.amount_admitted,pm.amount_claimed from student_admission sadm,student_personal sp, activity_participate_member pm where sadm.user_code=sp.user_code and sp.user_code=sadm.user_code and  pm.user_code=sadm.user_code and sadm.user_code IN  :userCodeArray and pm.particiaption_request_id=:participateId",nativeQuery=true)
	 List<StudentPersonalIForm> findStudentPersonalByUsercode( @Param("userCodeArray") List<String> userCodeArray,int participateId);
	 
	//select am.activity_code,am.title,apr.roll_no,apr.onward_start_date,apr.return_end_date from activity_master am,activity_participate_member apr where am.activity_code=apr.activity_code and am.status='adminapproved' and apr.roll_no = :rollno and (apr.onward_start_date between :startDate and  :endDate) and  (apr.return_end_date between :startDate and :endDate)  
		
	 //@Query(value="select distinct am.activity_code,am.title,apr.roll_no,apr.onward_start_date,apr.return_end_date from activity_master am,activity_participate_member apr where am.activity_code=apr.activity_code and am.status='finalapproved' and apr.roll_no = :rollno and (apr.onward_start_date between  :startDate and  :endDate) or  (apr.return_end_date between :startDate and  :endDate)",nativeQuery=true)
	// @Query(value="select distinct am.activity_code,am.title,apr.roll_no,apr.onward_start_date,apr.return_end_date from activity_master am,activity_participate_member apr where am.activity_code=apr.activity_code and am.status='finalapproved' and apr.roll_no = :rollno and apr.return_end_date >= :startDate and apr.onward_start_date<= :endDate",nativeQuery=true)
//	List<TeamActivityParticipateDetailsByStudent> getStudentActivitesBetweenDates(@Param("rollno") String rollno,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	 
	   // @Query(value="select distinct am.activity_code,am.title,apr.roll_no,apm.travel_date_onward_start,apm.travel_date_return_end from activity_master am,activity_participate_member apr,activity_participate_master_data apm where am.activity_code=apr.activity_code and apm.activity_code = am.activity_code and am.status='finalapproved' and apr.user_code = :studentAdmissionCode and apm.travel_date_return_end >= :startDate and apm.travel_date_onward_start<= :endDate",nativeQuery=true)
	    @Query(value="select distinct am.activity_code,am.title,apr.roll_no,apr.onward_start_date,apr.return_end_date,apm.participation_request_status from activity_master am,activity_participate_member apr,activity_participate_master_data apm where am.activity_code=apr.activity_code and apm.activity_code = am.activity_code and apr.user_code = :studentAdmissionCode and apr.return_end_date >= :startDate and apr.onward_start_date<= :endDate and apm.participation_request_status in('recommended','finallyapproved','adminstrativeapproved','financiallyapproved','preapproved')",nativeQuery=true)
		List<TeamActivityParticipateDetailsByStudent> getStudentActivitesBetweenDates(@Param("studentAdmissionCode") String studentAdmissionCode,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	    
	    

@Query(value="select apm.particiaption_request_id as participationRequestId,apm.activity_venue as av,apm.hosting_institution as hi,apm.financial_implications as fi, apm.participation_request_status as requestStatus,apm.activity_code as activityCode,apm.competion_start , apm.competion_end,am.title  from activity_participate_master_data apm,activity_master as am,activity_participate_member ap where ap.roll_no=?1 and am.activity_code=apm.activity_code and am.activity_code=ap.activity_code ",nativeQuery=true) 
List<StudentWiseReportInterface> getStudentWiseReport(String rollno);

	    
}
