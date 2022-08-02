package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;




import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsKey;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
@Repository
public interface ResourcePersonMasterRepository  extends CrudRepository<ResourcePersonMaster,Integer>{
	
	 @Query(value="SELECT * FROM resource_person_master WHERE res_id =:resId",nativeQuery=true)
	 ResourcePersonMaster findResourcePersonByResId(@Param("resId") int resId);
	 
	 @Query(value="SELECT * FROM resource_person_master WHERE res_id IN :residarray and status='valid' ",nativeQuery=true)
	 List<ResourcePersonMaster> findResourcePersonByResIdList(@Param("residarray") List<Integer> residarray);
	 
	 @Query(value="SELECT * FROM resource_person_master WHERE activity_code =:activityCode",nativeQuery=true)
	 List<ResourcePersonMaster> getResourcepersondetailofcurrentActivity(@Param("activityCode") int activityCode);
	 
	 
	 @Query(value="SELECT * FROM resource_person_master WHERE res_id=?1",nativeQuery=true)
	 ResourcePersonMaster getResourcepersondetailofById(int resId);
	 
	
}
