package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTER.CenterMaster;

@Repository
public interface ExpenditureHeadRepository  extends CrudRepository<ExpHeadsMaster,ExpHeadsKey>{
	
	 @Query(value="SELECT COUNT(exp_head_id) FROM exp_heads_master WHERE description =:description and fin_year=:finyear",nativeQuery=true)
	 int countexpHeadRecordCount(@Param("description") String description, @Param("finyear") String finyear);
	 
	//to view all expheadscenters where its status is submitted
      @Query(value="select * from  exp_heads_master where status='submitted'",nativeQuery=true)
      List<ExpHeadsMaster> getAllExpHeadSubmitted();
      
      @Query(value="select * from  exp_heads_master",nativeQuery=true)
      List<ExpHeadsMaster> getAllExpHeadDetailswithoutStatus();
      
      
      @Query(value="select * from  exp_heads_master where fin_year=:finyear",nativeQuery=true)
      List<ExpHeadsMaster>getAllExpHeadsByFinyear(@Param("finyear") String finyear);
      
      
      @Query(value="select * from  exp_heads_master WHERE description =?1 and fin_year=?2",nativeQuery=true)
      ExpHeadsMaster getExpHeadsrecordBydescriptionAndFinyear(String description,String finyear);
      
    

      
      

}
