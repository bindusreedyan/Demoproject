package NUALS.AMS.ACADEMIC.ACTIVITIES;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>
{
	//methods
	//getAllAccounts
	//getAccountsByType(String Type)
	//getAccountsByCrDrType(String crDr)
	//getAccountsByGroup(int groupId)
	//editAccountById(int id,string accName,string type,string crDrtype,int groupid)

	@Query(value="select * from course where course_id=?1",nativeQuery=true)
	Course getCourseById(int id);
}
