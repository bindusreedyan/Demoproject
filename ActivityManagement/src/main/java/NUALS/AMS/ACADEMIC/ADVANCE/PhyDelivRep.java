package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PhyDelivRep extends CrudRepository<PhyDeliv, Integer>{
	@Query(value="SELECT * FROM phy_deliv WHERE set_id =?1",nativeQuery=true)
	 List<PhyDeliv> loadAllphysicalDelivarablesDetails(int setId);
	
	
	@Transactional
	@Modifying
	@Query(value="update phy_deliv set  recommended=?2,justification=?3 WHERE exp_id =?1",nativeQuery=true)
	 int editPhysicalDelivarableDetails(int expId,String recommend,String recomRemarks);
	
}
