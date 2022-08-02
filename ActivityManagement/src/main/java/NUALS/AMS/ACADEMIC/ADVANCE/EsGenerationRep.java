package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.OrderGenaration;

public interface EsGenerationRep extends CrudRepository<Esgeneration, Integer>{
	@Query(value="SELECT * FROM esgeneration",nativeQuery=true)
	 List<Esgeneration> loadAllEsgenerationList();
	
	@Query(value="SELECT * FROM esgeneration where order_gen_id=?1",nativeQuery=true)
	 Esgeneration getEsOrderGenerationInformation(int orderId);
	
	

}
