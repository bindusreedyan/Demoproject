package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderGenerationRep extends CrudRepository<OrderGenaration, Integer>{
	
	 @Query(value="select * from  order_genaration where order_gen_id=?1",nativeQuery=true)
	 OrderGenaration getOrderGenerationInformation(int orderGenId);
	 
	 @Query(value="select * from  order_genaration",nativeQuery=true)
	 List<OrderGenaration> getOrderGenerationInformations();


}
