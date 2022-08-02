package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReturnRep extends CrudRepository<GoodsReturned, Integer>{
	
	 @Query(value="SELECT * FROM goods_returned WHERE set_id =?1",nativeQuery=true)
	 List<GoodsReturned> loadAllGoodsReturnBySettlementId(int setId);

}
