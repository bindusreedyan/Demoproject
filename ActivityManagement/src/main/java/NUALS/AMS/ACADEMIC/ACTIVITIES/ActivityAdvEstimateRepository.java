package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ActivityAdvEstimateRepository extends CrudRepository<ActivityAdvEstimate,ActivityExpHeadKey>{
	
	  @Query(value="SELECT * FROM activity_adv_estimate WHERE activity_code =?1 and fin_year=?2",nativeQuery=true)
	  List<ActivityAdvEstimate> getActivityAdvanceRequest( int activityId,String finYear);
	  
	  @Query(value="select DISTINCT am.activity_code,am.title,am.asno, adv_esmte.fin_year from activity_adv_estimate adv_esmte ,activity_master am where am.activity_code=adv_esmte.activity_code and am.status='finalapproved' and adv_esmte.adv_paid=0",nativeQuery=true)
	  List<AdvanceRequestFinalApproval> getAllAdvanceRequestFinallyApproved();
	 
	  @Transactional
	  @Modifying
	  @Query(value="update activity_adv_estimate set adv_paid=1 where  activity_code =?1 and fin_year=?2",nativeQuery=true)
	  int updateAdvePaid( int activityId,String finYear);
	  
	  
	  @Query(value="select DISTINCT am.activity_code,am.title,am.asno, adv_esmte.fin_year from activity_adv_estimate adv_esmte ,activity_master am where am.activity_code=adv_esmte.activity_code and am.status='finalapproved' and adv_esmte.adv_paid=0",nativeQuery=true)
	  List<AdvanceRequestFinalApproval> getAllAdvanceRequestReleasedAmt();
	  
	  
	  @Query(value="select * from activity_adv_estimate where activity_code=?1 and adv_paid=1",nativeQuery=true)
	  List<ActivityAdvEstimate>  advanceReleasedDetailsPerActivity(int actvtyCode);
	  
	  
	  
	  
	}
