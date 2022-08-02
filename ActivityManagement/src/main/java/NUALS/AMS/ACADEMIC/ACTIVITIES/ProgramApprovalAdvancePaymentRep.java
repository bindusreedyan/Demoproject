package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramApprovalAdvancePaymentRep extends  CrudRepository<ProgramApprovalAdvancePayment,Integer>{
	
	//to view all expheadscenters where its status is submitted
    @Query(value="select * from  program_approval_advance_payment where activity_code=?1",nativeQuery=true)
    List<ProgramApprovalAdvancePayment> findAllAdvanceRecievedByActivityId(int actId);
    
	//to view all expheadscenters where its status is submitted
    @Query(value="select * from  program_approval_advance_payment where program_approval_advance_payment_id=?1",nativeQuery=true)
    ProgramApprovalAdvancePayment getAdvancePaidByAdvancePaymentId(int actId);


}
