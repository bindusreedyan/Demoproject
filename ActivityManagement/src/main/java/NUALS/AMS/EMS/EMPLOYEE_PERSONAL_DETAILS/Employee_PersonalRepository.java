package NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;

@Repository
public interface Employee_PersonalRepository extends CrudRepository<Employee_Personal, String>
{
	@Query(value = "SELECT max(rowid) FROM Employee_Personal")
	BigDecimal getRowId();
	
	
	@Query(value="select * FROM Employee_Personal d WHERE d.personalstatus in (:sts)  ",nativeQuery  = true)
	List<Employee_Personal> findByMultipleStatuses(List<String> sts);
	
		
}
