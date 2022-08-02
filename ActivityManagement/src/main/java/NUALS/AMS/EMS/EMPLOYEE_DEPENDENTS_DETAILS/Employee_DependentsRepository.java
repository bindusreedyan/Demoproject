package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;

@Repository
public interface Employee_DependentsRepository extends CrudRepository<Employee_Dependents, EMP_DEPENDENTS_PKEY>
{
	@Query(value = "SELECT max(rowid) FROM Employee_Dependents")
	BigDecimal getRowId();
	
	
	@Query(value="select * FROM Employee_Dependents d WHERE d.empdepstatus in (:sts)  ",nativeQuery  = true)
	List<Employee_Dependents> findByMultipleStatuses(List<String> sts);
	
}
