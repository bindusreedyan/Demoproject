package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ComplCheckLisRep extends CrudRepository<ComplCheckList, Integer>{
	 @Query(value="SELECT COUNT(compl_desc) FROM compl_check_list WHERE compl_desc =?2  and set_id=?1",nativeQuery=true)
	 int findActivityRecordByDes(int setId,String exphead);
	 
	 
	 
	 @Query(value="SELECT * FROM compl_check_list WHERE set_id =?1",nativeQuery=true)
	 List<ComplCheckList> viewComplianceToProceduralRequirements(int setId);
}
