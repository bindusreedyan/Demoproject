package NUALS.AMS.ACADEMIC.CENTER;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
@Repository
public interface CenterFundRepository extends CrudRepository <CenterFund, CentreFundKey> {
	
	 @Query(value="SELECT COUNT(centre_id) FROM center_fund WHERE centre_id =:centercode and finyear=:finyear",nativeQuery=true)
	 int countCenterFundRecordCount(@Param("centercode") int centercode, @Param("finyear") String finyear);
	 
	    //to view all centers where its status is submitted
	    @Query(value="select * from center_fund where status='submitted'",nativeQuery=true)
		List<CenterFund> getAllCenterFundSubmitted();
	 
	    //to view all centers where its status is submitted
	    @Query(value="select * from center_fund",nativeQuery=true)
		List<CenterFund> getAllCenterFundSubmittedInAllstatus();
	 
	 
	 @Query(value="SELECT * FROM center_fund WHERE centre_id =:centerId and finyear=:currentfinyear",nativeQuery=true)
	 CenterFund findCenterRecordByFinyearAndCenterId(@Param("centerId") int centercode, @Param("currentfinyear") String currentfinyear);

	 
	 @Query(value="SELECT * FROM center_fund WHERE centre_id IN :centerarray1 and finyear=:currentfinyear ",nativeQuery=true)
	 List<CenterFund> findCenterRecordByFinyearAndCenterIds(@Param("centerarray1") List<Integer> centerarray1,@Param("currentfinyear") String currentfinyear);
}
