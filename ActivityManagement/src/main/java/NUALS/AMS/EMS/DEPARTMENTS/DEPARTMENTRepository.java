package NUALS.AMS.EMS.DEPARTMENTS;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DEPARTMENTRepository extends CrudRepository<DEPARTMENT, Integer>
{
	@Query(value = "SELECT max(rowid) FROM DEPARTMENT")
	BigDecimal getRowId();
	
	@Query(value="select * FROM DEPARTMENT d WHERE d.dept_name = :dept_name",nativeQuery  = true)
	DEPARTMENT findByDept_name(String dept_name);
	
	
	List<DEPARTMENT> findByDepstatus(String sts);
	
	@Query(value="select * FROM DEPARTMENT d WHERE d.depstatus in (:sts)  ",nativeQuery  = true)
	List<DEPARTMENT> findByMultipleDepstatus(List<String> sts);
}
