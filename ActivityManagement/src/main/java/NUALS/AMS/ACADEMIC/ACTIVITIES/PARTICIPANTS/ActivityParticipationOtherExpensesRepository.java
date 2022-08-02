package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityParticipationOtherExpensesRepository extends CrudRepository<ActivityParticipationOtherExpenses,Integer>{ 

	

	 @Query(value="SELECT * FROM activity_participation_other_expenses WHERE participation_request_id=?1",nativeQuery=true)
	 ActivityParticipationOtherExpenses findByParticiapntOtherExpenseRecord(int participateRequestId);
	
	
}
