package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;

import org.springframework.data.jpa.repository.Query;
@Repository
public interface ActiveMasterDataRepository extends CrudRepository<ActivityParticipateMasterData,Integer>{

	@Query(value="select partmaster.activity_code,partmaster.travel_date_onward_start,partmaster.travel_date_return_end,member.roll_no from activity_participate_master_data partmaster,activity_participate_member member where partmaster.activity_code=member.activity_code and partmaster.participation_request_status='submitted'",nativeQuery=true)
	List<TeamActivityParticipateDetails> getAllAcitivityParticipantDetails();
	
	@Query(value="select partmaster.activity_code,partmaster.travel_date_onward_start,partmaster.travel_date_return_end,member.roll_no from activity_participate_master_data partmaster,activity_participate_member member where partmaster.activity_code=member.activity_code and partmaster.participation_request_status='submitted' and member.roll_no=:rollno",nativeQuery=true)
	List<TeamActivityParticipateDetails> getAllAcitivityParticipantDetailsByRollno(@Param("rollno") String rollno);
	
	 @Query(value="SELECT * FROM activity_participate_master_data WHERE particiaption_request_id =:prtrqId",nativeQuery=true)
	 ActivityParticipateMasterData findParticipantRequestByRequestId(@Param("prtrqId") int prtrqId);
	 
	 @Query(value="select student_admission_roll_no from student_admission where user_code = :userCode",nativeQuery=true)
	 String getRollNoAgainstStudentAdmissionCode(@Param("userCode") String userCode);
	 
	 
	 @Query(value="select * from activity_participate_master_data where participation_request_status = :status",nativeQuery=true)
	 List<ActivityParticipateMasterData> getAllAcitivityParticipantDetailsByStatus(@Param("status") String status);
	
	 @Query(value="select * from activity_participate_master_data where participation_request_status<> :status",nativeQuery=true)
	 List<ActivityParticipateMasterData> getActivityParticipateRequestwithStatusnot(@Param("status") String status);
	 
	 

	 @Query(value="select * from activity_participate_master_data where participation_request_status = :status and entered_by=:userCode",nativeQuery=true)
	 List<ActivityParticipateMasterData> getAllParticipantRequestForAdvance(@Param("status") String status,@Param("userCode") String userCode);
	 
	 @Query(value="select * from activity_participate_master_data where participation_request_status = :status and financial_implications='yes'",nativeQuery=true)
	 List<ActivityParticipateMasterData> getAllAcitivityParticipantDetailsByStatusForAdminApproval(@Param("status") String status);
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where apm.participation_request_status=?1 and apm.entered_by=?2 and am.activity_code=apm.activity_code and apm.settlement_status='Not Settled'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedByParticipant(String status,String usrCode);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.settlement_status as settlementStatus ,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where am.faculty_code=?1 and am.activity_code=apm.activity_code and apm.settlement_status='raised'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom(String usrCode);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.settlement_status as settlementStatus ,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where am.activity_code=apm.activity_code and apm.settlement_status='recommended'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForAdministrativeApproved();
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.settlement_status as settlementStatus ,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where am.activity_code=apm.activity_code and apm.settlement_status='Administrative Approved'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForFinancialApproved();
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.settlement_status as settlementStatus ,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where am.activity_code=apm.activity_code and apm.settlement_status='Financially Approved'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForFinalApproved();
	 
	/* @Query(value="select * from activity_participate_master_data where participation_request_status<> :status",nativeQuery=true)
	 List<ActivityParticipateMasterData> getActivityParticipateRequestwithStatusnot(@Param("status") String status);

	 */
	 
	 @Query(value="select am.title as compName,am.activity_level as actvtyLevel,pm.competion_type as comType,pm.hosting_institution as hi,\r\n" + 
	 		"pm.competion_start as fromDate, pm.competion_end as toDate,pm.activity_venue as venue,pm.team_participation as tp from activity_master as am,activity_participate_master_data pm where am.activity_code=pm.activity_code and pm.particiaption_request_id=:prtrqId",nativeQuery=true)
	 AchievmentPartDetails findParticipantRequestByRequestIdForAchievement(@Param("prtrqId") int prtrqId);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where apm.participation_request_status=?1 and apm.entered_by=?2 and am.activity_code=apm.activity_code",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestByParticipant(String status,String usrCode);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where  apm.recommended_by=?1 and am.activity_code=apm.activity_code",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestByEnteredBy(String usrCode);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title,am.faculty_code as facultyCode from activity_participate_master_data apm,activity_master as am where  apm.administrative_approved_by=?1 and am.activity_code=apm.activity_code",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantAdministrativeApprovedBy(String usrCode);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title,am.faculty_code as facultyCode from activity_participate_master_data apm,activity_master as am where  apm.administrative_approved_by=?1 and am.activity_code=apm.activity_code and apm.participation_request_status='adminstrativeapproved'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantAdministrativeApprovedByStatus(String usrCode);
	 
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title,am.faculty_code as facultyCode from activity_participate_master_data apm,activity_master as am where  apm.administrative_approved_by=?1 and am.activity_code=apm.activity_code and apm.participation_request_status='financiallyapproved'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinanciallyApprovedByStatus(String usrCode);
	 
	 
	 
	 
	 
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where  apm.financial_approved_by=?1 and am.activity_code=apm.activity_code",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinanciallyApprovedBy(String usrCode);
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where  apm.approved_by=?1 and am.activity_code=apm.activity_code",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinallyApprovedBy(String usrCode);
	 
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where  apm.approved_by=?1 and am.activity_code=apm.activity_code and apm.participation_request_status in('finallyapproved','finallyrejected')",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinallyApprovedByForEdit(String usrCode);
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where apm.participation_request_status=?1 and am.activity_code=apm.activity_code and apm.settlement_status='Not Settled' and apm.as_orders_issued='Not Issued'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApproved(String status);
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,apm.as_no as asNo,am.title as title from activity_participate_master_data apm,activity_master as am where apm.participation_request_status=?1 and am.activity_code=apm.activity_code and apm.settlement_status='Finally Approved' and apm.esorders_issued='Not Issued'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinallySettled(String status);
	 
	 
	 
	 @Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where am.faculty_code=?1 and am.activity_code=apm.activity_code and apm.settlement_status='Not Settled' and apm.participation_request_status='submitted'",nativeQuery=true)
	 List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequesForFacultyRecom(String usrCode);
	 
	 
	 //@Query(value="select apm.particiaption_request_id as participationRequestId,apm.participation_request_status as requestStatus,apm.activity_code as activityCode,am.title as title from activity_participate_master_data apm,activity_master as am where apm.participation_request_status in('finalapproved','preapproved') and am.activity_code=apm.activity_code and apm.settlement_status='Not Settled' and apm.as_orders_issued='Not Issued'",nativeQuery=true)
	 //List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedforas(String status);

}
