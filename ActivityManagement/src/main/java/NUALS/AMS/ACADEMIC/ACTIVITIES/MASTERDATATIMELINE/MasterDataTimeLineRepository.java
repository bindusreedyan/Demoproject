package NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MasterDataTimeLineRepository extends CrudRepository<ActivityMasterDataTimeLine, Integer>
{
	//methods
	//getAllAccounts
	//getAccountsByType(String Type)
	//getAccountsByCrDrType(String crDr)
	//getAccountsByGroup(int groupId)
	//editAccountById(int id,string accName,string type,string crDrtype,int groupid)
	//select * from master_data_time_line where mdtl_process_id=?1 and mdtl_process_name=?2;
	@Query(value="select * from master_data_time_line where mdtl_process_id=?1 and mdtl_process_name=?2 order by mdtl_date desc",nativeQuery=true)
	List<ActivityMasterDataTimeLine> getMasterDataTimeLine(String processId,String processName);
	
}
