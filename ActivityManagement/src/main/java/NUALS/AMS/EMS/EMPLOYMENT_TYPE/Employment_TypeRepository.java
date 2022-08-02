package NUALS.AMS.EMS.EMPLOYMENT_TYPE;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;

@Repository
public interface Employment_TypeRepository extends CrudRepository<Employment_Type, Integer>
{
	
	@Query(value = "SELECT max(rowid) FROM Employment_Type")
	BigDecimal getRowId();
	@Query(value="select * FROM Employment_Type e WHERE e.emplmnt_type_Name = :emplmntType",nativeQuery  = true)
	Employment_Type findByEmplmnt_type_Name(String emplmntType);
	
	@Query(value="select * FROM Employment_Type d WHERE d.emplmnttypestatus in (:sts)  ",nativeQuery  = true)
	List<Employment_Type> findByMultipleDepstatus(List<String> sts);
		
}
