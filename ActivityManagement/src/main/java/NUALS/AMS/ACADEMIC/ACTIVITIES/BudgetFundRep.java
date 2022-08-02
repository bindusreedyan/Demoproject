package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTER.CentreFundKey;

@Repository
public interface BudgetFundRep extends CrudRepository <BudgetHeadFund, BudgetFundKey> {
	
	 @Query(value="SELECT COUNT(bud_head_id) FROM budget_head_fund WHERE bud_head_id =?1 and finyear=?2",nativeQuery=true)
	 int countBudgetFundRecordCount( int budheadid,  String finyear);
	 
	  @Query(value="select * from  budget_head_fund WHERE bud_head_id =?1 and finyear=?2",nativeQuery=true)
	  BudgetHeadFund getBudgetFundAmount(int budheadid,String finyear);
	  
	  
	   @Query(value="select * from  budget_head_fund where budget_fund_status='submitted'",nativeQuery=true)
	   List<BudgetHeadFund> getAllBudgetHeadDetails();
		 
	    @Query(value="select bhf.bud_head_id as budHeadId,bh.bud_head_name as budHeadName,bhf.finyear as finYear,bhf.fund_amount as fundAmount,bhf.budget_fund_status as budStatus from budget_head_fund bhf,budget_head bh where bh.bud_head_id =bhf.bud_head_id",nativeQuery=true)
		List<budheadinterface> getAllBudgetHeadDetailswithNostatus();

}
