package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityBudgetFundRep extends CrudRepository<ActivityBudgetFund, Integer>{
	  
	  @Query(value="select * from  activity_budget_fund WHERE bud_head_id =?1 and finyear=?2",nativeQuery=true)
	  List<ActivityBudgetFund> getActivityBudgetHeadAmount(int budheadid,String finyear);
	 
	  
	  @Query(value="select * from  activity_budget_fund WHERE bud_head_id =?1 and finyear=?2 and activity_code=?3",nativeQuery=true)
	  ActivityBudgetFund getBudgetHeadAmountForAnactivity(int budheadid,String finyear,int activityCode);
	 	 
	  @Query(value="select * from  activity_budget_fund WHERE bud_head_id =?1 and finyear=?2",nativeQuery=true)
	  List<AsEsDifferenceInterface> getAsEsDifferenceForClosedProgram(int budheadid,String finyear);
	  
	  
	  @Query(value="select bh.bud_head_id,bh.bud_head_name,abf.as_amount,abf.activity_code,abf.es_amount from activity_budget_fund as abf, budget_head as bh where abf.activity_code=?1 and abf.bud_head_id=bh.bud_head_id",nativeQuery=true)
	  List<getActivityBudgetHeadDetails> getActivityBudHeadDetails(int activityCode);
	  
	  @Query(value="select bh.bud_head_id,bh.bud_head_name,abf.as_amount,abf.activity_code,abf.es_amount from activity_budget_fund as abf, budget_head as bh where abf.participate_request_id=?1 and abf.bud_head_id=bh.bud_head_id",nativeQuery=true)
	  List<getActivityBudgetHeadDetails> getActivityBudHeadDetailsbyParticipation(int pr);
	  
	//  @Query(value="select bh.bud_head_id,bh.bud_head_name,abf.as_amount,abf.activity_code,abf.es_amount from activity_budget_fund as abf, budget_head as bh where abf.participate_request_id=?1 and abf.bud_head_id=bh.bud_head_id",nativeQuery=true)
	//  List<getActivityBudgetHeadDetails> getActivityBudHeadDetailsForParticipation(int pr);
	  
	  @Query(value="select * from  activity_budget_fund WHERE bud_head_id =?1 and finyear=?2 and participate_request_id=?3",nativeQuery=true)
	  ActivityBudgetFund getBudgetHeadAmountForParticipation(int budheadid,String finyear,int pr);
	  
      @Query(value="select bh.bud_head_name as budheadname,ab.as_amount as asamount,ab.es_amount as esamount from budget_head bh,activity_budget_fund ab where ab.activity_code=?1 and bh.bud_head_id=ab.bud_head_id and ab.finyear=?2",nativeQuery=true)
	  List<asBudheadDetails> getActivityBudHeadForAs(int activity_code,String finyear);	  
			  
			  
}
