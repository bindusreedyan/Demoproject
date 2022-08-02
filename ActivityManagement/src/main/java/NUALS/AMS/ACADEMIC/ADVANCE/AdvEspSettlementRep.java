package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityExpEstimate;

@Repository
public interface AdvEspSettlementRep  extends CrudRepository<AdvExpSettlement, Integer>{

	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status in('submitted','FacultyRecommended')",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsSubmitted();
	     
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status in('submitted','partially submitted')",nativeQuery=true)
	       List<AdvExpSettlement> loadAllExpenditureDetailsSubmittedForEdit();
	     
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE set_id =?1",nativeQuery=true)
		 AdvExpSettlement loadAllExpenditureDetailsSubmittedBySettlementId(int setId);
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='OfficeRecommended'",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsOfficeRecommended();
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='AdministrativeApproved'",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsAdministrativeApproved();
	     
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='FinanceOfficeApproved'",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsFinanceOfficeApproved();
	
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='FinalOfficeApproved'",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsFinalOfficeApproved();
	     
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='FinanceApproved'",nativeQuery=true)
	  		 List<AdvExpSettlement> loadAllExpenditureDetailsFinanceApproved();
	     
	     
	     @Query(value="SELECT * FROM adv_exp_settlement WHERE adv_settlement_status='sanctioned' and es_orders_issued='Not Issued'",nativeQuery=true)
		 List<AdvExpSettlement> loadAllExpenditureDetailsSanctioned();
	     
	     @Query(value="select count(es_number_prefix) from esgeneration",nativeQuery=true)
		 int getCountofAsNo();
}
