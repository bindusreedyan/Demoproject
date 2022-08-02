package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AscomplianceRep extends CrudRepository<AsCompliance, Integer>{
	
	
	 @Query(value="SELECT COUNT(exp_head) FROM as_compliance WHERE exp_head =?2  and set_id=?1",nativeQuery=true)
	 int findActivityRecordByDes(int setId,String exphead);
	 
	 @Query(value="SELECT * FROM as_compliance WHERE set_id =?1",nativeQuery=true)
	 List<AsCompliance> loadAllCompliances(int setId);

}
