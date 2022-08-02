package NUALS.AMS.EMS.EMPLOYEE_MASTER;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;


@Repository
public interface Employee_MasterRepository extends JpaRepository<Employee_Master, String>
{
	@Query(value = "SELECT max(rowid) FROM Employee_Master")
	BigDecimal getRowId();
	
	@Query(value="select * FROM Employee_Master cfm WHERE cfm.emp_work_type = :emp_work_type",nativeQuery  = true)
	List<Employee_Master> findByemp_work_type(@Param("emp_work_type") String emp_work_type);

	/*
	@Query(value="select new LOGINS(ui.usercode,ui.username,ui.email,ui.mobile,ui.active) FROM userinfo ui ",nativeQuery  = true)
	List<LOGINS> findAllLogins();
	*/
	
	@Query(value="select ui.usercode,ui.username,ui.email,ui.mobile,ui.active FROM userinfo ui ",nativeQuery  = true)
	List<LOGINS_INTERFACE> findAllLogins();
	
	
	@Query(value="select * FROM Employee_Master d WHERE d.empmasterstatus in (:sts)  ",nativeQuery  = true)
	List<Employee_Master> findByMultipleStatuses(List<String> sts);
	
	
}
