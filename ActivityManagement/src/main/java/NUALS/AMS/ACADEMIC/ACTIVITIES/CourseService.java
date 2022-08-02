package NUALS.AMS.ACADEMIC.ACTIVITIES;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService 
{
	@Autowired
	CourseRepository courseRepository;

	public List<Course> getAllCourses()
	{
		List<Course> courses = new ArrayList<Course>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}
	
    public Course addCourse(Course course)
	{
		Course createdCourse=courseRepository.save(course);
		return createdCourse; 
	}
    
    public Course getCourseDetailsById(int courseId)
	{
		Course course=courseRepository.getCourseById(courseId);
		return course; 
	}
    
    
      
}
