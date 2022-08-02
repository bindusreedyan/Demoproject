package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ActivityExpEstimateRepository extends CrudRepository<ActivityExpEstimate,ActivityExpHeadKey>{
	
	  @Query(value="SELECT * FROM activity_exp_estimate WHERE activity_code =?1 and fin_year=?2",nativeQuery=true)
	 List<ActivityExpEstimate> getActivityExpHeadExpenses( int activityId,String finYear);

}
