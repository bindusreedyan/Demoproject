package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
@Repository
public interface ActivityCenterRepository  extends CrudRepository<Activity_Center,ActivityCenterKey>{

	 @Query(value="SELECT * FROM activity_center WHERE activity_code =:activityId",nativeQuery=true)
    List<Activity_Center> findActivityByActivityCode(@Param("activityId") int activityId);
    
    
    @Query(value="SELECT * FROM activity_center WHERE activity_code =?1 and centre_id=?2",nativeQuery=true)
    Activity_Center findActivityByActivityByActivityCodeAndCenterCode( int activityId, int centre_id);
    
	 @Query(value="SELECT * FROM activity_center WHERE centre_id IN :centerarray1 and activity_code=:activeId1 ",nativeQuery=true)
	 List<Activity_Center> findActivceCenterRecordByCenterIdsAndActiveId(@Param("centerarray1") List<Integer> centerarray1,@Param("activeId1") int activeId1);


	
	
	
	

}
