package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AmountPaidBackRep  extends CrudRepository<AmountPaidBack, Integer>{
	
	 @Query(value="SELECT * FROM amount_paid_back WHERE set_id =?1",nativeQuery=true)
	 List<AmountPaidBack> loadAllAmountPaidBackBySettlementId(int setId);
	 
	 
	 @Query(value="SELECT * FROM amount_paid_back WHERE university_payment_back_id =?1",nativeQuery=true)
	 List<AmountPaidBack> loadAllAmountPaidBackById(int setId);

}
