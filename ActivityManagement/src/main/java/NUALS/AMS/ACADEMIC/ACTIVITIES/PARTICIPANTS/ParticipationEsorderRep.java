package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import NUALS.AMS.ACADEMIC.ADVANCE.Esgeneration;

public interface ParticipationEsorderRep extends CrudRepository<ParticipationEsGeneration,Integer>{
	 @Query(value="select count(es_number_prefix) from participation_es_generation",nativeQuery=true)
	 int getCountofAsNo();
	 
	 @Query(value="SELECT * FROM participation_es_generation where order_gen_id=?1",nativeQuery=true)
	 ParticipationEsGeneration getEsOrderGenerationInformation(int orderId);
	 
	 @Query(value="SELECT * FROM participation_es_generation ",nativeQuery=true)
	 List<ParticipationEsGeneration> getAllEsOrderGenerationInformation();

}
