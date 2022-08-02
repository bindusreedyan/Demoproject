package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddExpenditureRep extends CrudRepository<AdvExpenditure, Integer> {
	
	 @Query(value="SELECT * FROM adv_expenditure WHERE set_id =?1",nativeQuery=true)
	 List<AdvExpenditure> loadAllExpenditureBySettlementId(int setId);
	 
	 
	 
	 @Query(value="select adv.exp_id as expId,adv.bill_no as billno ,adv.exp_amnt as expamnt ,adv.tax_amnt as taxamnt,adv.total_amt as advtotal,vm.business_name as payee,adv.paid_status as paidstatus,adv.mode_payment as modepayment,adv.adms_amt as admsamnt,vm.gst_no as gstno,vm.pan_no as panno,exphead.exp_head_name as headname from exp_head as exphead,adv_expenditure as adv,vendor_master as vm where vm.vendor_id=adv.vendor_id and exphead.head_id=adv.head_id and adv.set_id=?1 and adv.vendor_id!=0",nativeQuery=true)
	 List<expendituretb> loadAllExpenditureBySettlementIdByInteface(int setId);
	 
	 
	 @Query(value="select  adv.exp_id as expId,adv.bill_no as billno ,adv.exp_amnt as expamnt ,adv.tax_amnt as taxamnt,adv.total_amt as advtotal,rm.name as payee,adv.paid_status as paidstatus,adv.mode_payment as modepayment,adv.adms_amt as admsamnt,rm.pan_no as panno,exphead.exp_head_name as headname from exp_head as exphead,adv_expenditure as adv,resource_person_master as rm where rm.res_Id=adv.resource_id and exphead.head_id=adv.head_id and adv.set_id=? and adv.resource_id!=0",nativeQuery=true)
	 List<expendituretb> loadAllExpenditureBySettlementIdByIntefaceForResourcePerson(int setId);
	 
	 
	 @Query(value="SELECT * FROM adv_expenditure WHERE exp_id =?1",nativeQuery=true)
	 AdvExpenditure loadExpenditureByexpenditureId(int exp_id);
	 
	
	 @Query(value="SELECT * FROM adv_expenditure WHERE set_id =?1 and head_id=?2",nativeQuery=true)
	 List<AdvExpenditure> getToalAmountExpenditureBySettlementIdAndHeadId(int settlementId,int expHead);

	 
}
