package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityAdvReqRep  extends CrudRepository<ActivityAdvReq, Integer>{
	
	
	@Query(value="select sum(sanctioned_amt) from activity_adv_req where activity_code= ?1",nativeQuery=true)
	double findTotalSanctionAmntByActivityId(int activityCode);
	
	@Query(value=" select * from activity_adv_req where activity_code= ?1 and user_code=?2",nativeQuery=true)
	List<ActivityAdvReq> findAdvReqByActivityCodeAndRollNo(int activityCode,String rollno);
	
	
	@Query(value=" select * from activity_adv_req where advance_req_status= ?1",nativeQuery=true)
	List<ActivityAdvReq> getAllAdvanceRequestRaised(String status);
	
	
	@Query(value=" select * from activity_adv_req where adv_req_id= ?1",nativeQuery=true)
	ActivityAdvReq getAllAdvanceRequestByRequestId(int requestId);
	
	@Query(value="select * from activity_adv_req where activity_code= ?1 and particiaption_request_id=?2 and user_code=?3",nativeQuery=true)
	List<ActivityAdvReq>  findTotalrequestedAmntByActivityIdAndPrcRqId(int activityCode,int participateRequestId,String userCode);
	
	@Query(value="select * from activity_adv_req where roll_no_capacity=?1 and (advance_req_status!='settled' or advance_req_status !='closed')",nativeQuery=true)
	List<ActivityAdvReq> getAdvRequestNotSettledOrClosed(String rollno);
	
	
	@Query(value="select aar.adv_req_id as advReqId,aar.activity_code as activityCode,am.title as title,aar.asno as asNO,aar.roll_no_capacity as rollNoCapacity,aar.sanctioned_amt as sanctionedAmt,aar.purpose as purpose from activity_adv_req as aar, activity_master as am where aar.advance_req_status=?1 and am.activity_code=aar.activity_code and aar.adv_paid=0 ",nativeQuery=true)
	List<AdvanceForadvpayment> getAllAdvanceRequestFinanciallyApprovedAndNotPaid(String status);
	
	/*@Query(value="select * from activity_adv_req where activity_code= ?1 and user_code=?2",nativeQuery=true)
	List<ActivityAdvReq>  findTotalRequestedAmntByFaculty(int activityCode,String userCode);*/
	
	@Query(value="select * from activity_adv_req where activity_code= ?1 and user_code=?2",nativeQuery=true)
	List<ActivityAdvReq>  findTotalRequestedAmntByFaculty(int activityCode,String userCode);
	
	
	@Query(value="select * from activity_adv_req where activity_code= ?1 and user_code=?2",nativeQuery=true)
	List<ActivityAdvReq>  findTotalRequestedAmntByActivityCode(int activityCode);

	
	@Query(value="select adr.adv_req_id,adr.particiaption_request_id,adr.roll_no_capacity,adr.advance_req_status, am.activity_code,am.title,adr.asNo,am.financial_year from activity_adv_req adr,activity_master am where adr.activity_code=am.activity_code and adr.advance_req_status=?1",nativeQuery=true)
	List<AdvReqDetailForm> getAllAdvanceRequestForAdminApproval(String status);
	
	@Query(value=" select * from activity_adv_req where advance_req_status= ?1 and req_rased_user_role='faculty'",nativeQuery=true)
	List<ActivityAdvReq> getAllActivitysubmitteddByFaculty(String status);
	
	
	@Query(value="select * from activity_adv_req where user_code=?1 and (advance_req_status!='settled' or advance_req_status !='closed')",nativeQuery=true)
	List<ActivityAdvReq> getAdvRequestNotSettledByFaculty(String userCode);
	
	@Query(value="select sp.student_personal_student_name as stname,adv.roll_no_capacity as rollno,\r\n" + 
			"adv.adv_required_fig as advfig, adv.adv_required_words as rswords, adv.purpose,\r\n" + 
			"adv.amt_sanctionedas as asamnt,adv.asno as asno, adv.final_adv_req as finalreqornot,\r\n" + 
			"adv.entered_remarks as enteredRemark, adv.recommended_remarks as recomRemark,\r\n" + 
			"adv.admin_approved_remarks as adminRemark,adv.comments_finance as finRemark \r\n" + 
			"from activity_adv_req as adv ,student_personal as sp where adv.adv_req_id=?1 \r\n" + 
			"and adv.user_code=sp.user_code",nativeQuery=true)
	 AdvanceReqReportClass getAllAdvRequestInformationByRequestId(int advReqId);
	//report for faculty
	@Query(value="select sp.student_personal_student_name as stname,adv.roll_no_capacity as rollno,\r\n" + 
			"adv.adv_required_fig as advfig, adv.adv_required_words as rswords, adv.purpose,\r\n" + 
			"adv.amt_sanctionedas as asamnt,adv.asno as asno, adv.final_adv_req as finalreqornot,\r\n" + 
			"adv.entered_remarks as enteredRemark, adv.recommended_remarks as recomRemark,\r\n" + 
			"adv.admin_approved_remarks as adminRemark,adv.comments_finance as finRemark \r\n" + 
			"from activity_adv_req as adv ,student_personal as sp where adv.adv_req_id=?1 \r\n" + 
			"and adv.user_code=sp.user_code",nativeQuery=true)
	 AdvanceReqReportClass getAllAdvRequestInformationByRequestIdForFaculty(int advReqId);
	
	
	
	
	@Query(value="select * from activity_adv_req where req_rased_user_role='student'",nativeQuery=true)
	 AdvanceReqReportClass getAllAdvRequestInformationByStudent(int advReqId);


	@Query(value=" select * from activity_adv_req where user_code=?2",nativeQuery=true)
	List<ActivityAdvReq> findAdvReqByRollNo(int activityCode,String rollno);
	
	
	@Query(value=" select * from activity_adv_req where user_code=?1 and advance_req_status='Raised'",nativeQuery=true)
	List<ActivityAdvReq> findAdvReqByRaisedBylogin(String rollno);
	
	@Query(value=" select * from activity_adv_req where user_code=?1",nativeQuery=true)
	List<ActivityAdvReq> findAdvReqByBylogin(String rollno);
	
	
	
	@Query(value=" select aar.adv_req_id , aar.particiaption_request_id,aar.asno,am.activity_code,am.title,am.financial_year,aar.roll_no_capacity,aar.advance_req_status from activity_adv_req as aar, activity_master as am where am.activity_code=aar.activity_code and am.faculty_code=?1 and aar.advance_req_status='Raised' and aar.req_rased_user_role='student'",nativeQuery=true)
	List<advReqStudentData> getAllAdvanceRequestRaisedForRecom(String usercode);
	
	
	@Query(value="select * from activity_adv_req where activity_code= ?1 and particiaption_request_id=?2 and roll_no_capacity=?3",nativeQuery=true)
	List<ActivityAdvReq>  findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent(int activityCode,int participateRequestId,String userCode);
	
	@Query(value=" select * from activity_adv_req where activity_code= ?1 and roll_no_capacity=?2",nativeQuery=true)
	List<ActivityAdvReq> findAdvReqByActivityCodeAndstRollNo(int activityCode,String rollno);
	
	
	
	//@Query(value=" select aar.adv_req_id , aar.particiaption_request_id,aar.asno,am.activity_code,am.title,am.financial_year,aar.roll_no_capacity,aar.advance_req_status,apr.entered_by from activity_adv_req as aar, activity_master as am, activity_participate_master_data apr where am.activity_code=aar.activity_code and am.faculty_code=?1 and aar.advance_req_status='Raised'",nativeQuery=true)
	//List<advReqStudentData> getAllAdvanceRequestRaisedForRecom(String usercode);
	
}
