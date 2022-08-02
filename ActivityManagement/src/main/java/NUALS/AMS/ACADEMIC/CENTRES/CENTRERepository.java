package NUALS.AMS.ACADEMIC.CENTRES;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CENTRERepository extends CrudRepository<CENTRE, Integer>
{
	@Query(value = "SELECT max(rowid) FROM CENTRE")
	BigDecimal getRowId();
	
	@Query(value="select * FROM CENTRE d WHERE d.centre_code = :centre_code",nativeQuery  = true)
	CENTRE findByCentre_code(String centre_code);
	
	
	List<CENTRE> findByCentrestatus(String sts);
	
	@Query(value="select * FROM CENTRE d WHERE d.centrestatus in (:sts)  ",nativeQuery  = true)
	List<CENTRE> findByMultipleCentrestatus(List<String> sts);
	
	
}
