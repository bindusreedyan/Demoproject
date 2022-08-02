package NUALS.AMS.ACADEMIC.ACTIVITY_TYPES;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface AcademicActivityTypeRepository extends CrudRepository<AcademicActivityTypes, String>
{
	 @Query(value="SELECT COUNT(activity_type_code) FROM academic_activity_types WHERE activity_type_code =:actvtytypecode",nativeQuery=true)
	 int findActivityTypeRecord(@Param("actvtytypecode") int actvtytypecode);
	 
	 
	 @Query(value="SELECT COUNT(activity_type_code) FROM academic_activity_types WHERE activity_type_description =:des",nativeQuery=true)
	 int findActivityTypeRecordByDes(@Param("des") String des);
	 
	 
	 @Query(value="SELECT * FROM academic_activity_types WHERE activity_type_code =:actvtytypecode",nativeQuery=true)
	 AcademicActivityTypes findActivityTypeByActivityTypeCode(@Param("actvtytypecode") int actvtytypecode);
	 
	 
		@Query(value="select * from academic_activity_types where activity_type_status='submitted'",nativeQuery=true)
		List<AcademicActivityTypes> getAllActivityTypeSubmitted();
		
		@Query(value="select * from academic_activity_types where activity_type_status='verified'",nativeQuery=true)
		List<AcademicActivityTypes> getAllActivityVerified();
		
		@Query(value="select * from academic_activity_types where activity_type_status='approved'",nativeQuery=true)
		List<AcademicActivityTypes> getAllActivityApproved();
		
		@Query(value="select * from academic_activity_types where activity_type_group =? and activity_type_status =?",nativeQuery=true)
		List<AcademicActivityTypes> getAllActivityTypeByCategry(String cat,String status);
		
		
		
}
