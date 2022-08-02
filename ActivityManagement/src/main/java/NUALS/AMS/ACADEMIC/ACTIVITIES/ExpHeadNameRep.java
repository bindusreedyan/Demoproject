package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ExpHeadNameRep extends CrudRepository<ExpHead,Integer>{
	
	//to view all expheadscenters where its status is submitted
    @Query(value="select * from  exp_head where exp_head_status='submitted'",nativeQuery=true)
    List<ExpHead> getAllExpHeadSubmitted();
    
    @Query(value="select * from  exp_head",nativeQuery=true)
    List<ExpHead> getAllExpHead();
    
    
    @Query(value="select * from  exp_head where exp_head_name=?",nativeQuery=true)
    ExpHead getExpHeadDetails(String headId);
    
    @Query(value="select * from  exp_head where headId=?",nativeQuery=true)
    ExpHead getExpHeadDetailsbyHeadid(int headId);
    
   // @Query(value="select eh.head_id,eh.exp_head_name,ae.as_granted_amnt from exp_head eh ,activity_exp_estimate ae where ae.description=eh.exp_head_name and ae.estimated_exp!=0 and ae.activity_code=?1",nativeQuery=true)
    @Query(value="select eh.head_id,eh.exp_head_name,ae.as_granted_amnt from exp_head eh ,activity_exp_estimate ae where ae.description=eh.exp_head_name and ae.estimated_exp!=0 and ae.activity_code=?1",nativeQuery=true)
    List<EstimateHeads> getAllEstimateHeadsSubmitted(int activitycode);
}
