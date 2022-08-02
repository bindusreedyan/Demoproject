package NUALS.AMS.ACADEMIC.ACTIVITIES;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityFinanceRepository  extends CrudRepository<ActivityFinance,Integer>{ 
	
	  @Query(value="SELECT * FROM activity_finance WHERE activity_code =?1",nativeQuery=true)
	  ActivityFinance getActivityFinanceByActivityId( int activityId);
}
