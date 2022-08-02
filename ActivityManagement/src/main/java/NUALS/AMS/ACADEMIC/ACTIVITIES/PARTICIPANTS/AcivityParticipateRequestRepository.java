package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AcivityParticipateRequestRepository  extends CrudRepository<ActivityParticipationRequest,ActivityParticipationRequestKey>{
	

}
