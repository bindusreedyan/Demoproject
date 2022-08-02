package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.activityResourcePerdetails;

@Repository
public interface ActvityResourcePersonRepository extends CrudRepository<ActivityResourcePerson,ActivityResourcePersonKey> {

	
	 @Query(value="SELECT * FROM activity_resource_person WHERE activity_code =:activityCode",nativeQuery=true)
	 List<ActivityResourcePerson> getResourcepersondetailofcurrentActivity(@Param("activityCode") int activityCode);
	 
	 
	 //activityResourcePerdetails
	 
	 @Query(value="select rpm.res_id,rpm.name,rpm.address,rpm.contact_name,rpm.email,rpm.designation,rpm.contact_phone,am.activity_code from activity_master am,resource_person_master rpm,activity_resource_person arp where rpm.res_id=arp.res_id and am.activity_code=arp.activity_code and rpm.status='valid' and am.activity_code=?1",nativeQuery=true)
	 List<activityResourcePerdetails> getResourcepersondetailByactivitycode(@Param("activityCode") int activityCode);
	 
}
