package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BudgetHeadRep extends CrudRepository<BudgetHead,Integer>
	{
	
	 @Query(value="SELECT COUNT(bud_head_id) FROM budget_head WHERE bud_head_name =:description",nativeQuery=true)
	 int countbudgetHeadRecordCount(@Param("description") String description);
	 
	 
	 
	 @Query(value="select * from  budget_head where bud_Status='submitted'",nativeQuery=true)
	    List<BudgetHead> getAllbudHeadSubmitted();
	 
	 
	 


}
