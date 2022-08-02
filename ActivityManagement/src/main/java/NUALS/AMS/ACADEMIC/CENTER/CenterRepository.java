package NUALS.AMS.ACADEMIC.CENTER;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
@Repository
public interface CenterRepository extends CrudRepository<CenterMaster,Integer>
{
	 @Query(value="SELECT COUNT(centre_code) FROM center_master WHERE centre_code =:centerId",nativeQuery=true)
	 int findCenterRecord(@Param("centerId") int centercode);
	 
	 
	 //to view all centers where its status is submitted
	 @Query(value="select * from center_master where status='submitted'",nativeQuery=true)
		List<CenterMaster> getAllCenteresSubmitted();
	 //center fund repository code starts from here
	 
	
	 

}
