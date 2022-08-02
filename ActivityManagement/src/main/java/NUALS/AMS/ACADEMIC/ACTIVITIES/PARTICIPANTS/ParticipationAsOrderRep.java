package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParticipationAsOrderRep extends CrudRepository<ParticipationAsOrder,Integer> {
	 @Query(value="select count(as_number_prefix) from participation_as_order",nativeQuery=true)
	 int getCountofAsNo();
	 
	 
	 @Query(value="SELECT * FROM participation_as_order ",nativeQuery=true)
	 List<ParticipationAsOrder> getAllAsOrderGenerationInformation();
	 
	 
	 @Query(value="SELECT * FROM participation_as_order where order_gen_id=?1",nativeQuery=true)
	 ParticipationAsOrder getAllAsOrderGenerationInformationByOrderId(int orderId);
}
