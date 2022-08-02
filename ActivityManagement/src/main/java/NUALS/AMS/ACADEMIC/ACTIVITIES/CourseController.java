package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;



@RestController
@RequestMapping("/course")
public class CourseController
{

	@Autowired
	//AccountRepository accountRep;
	CourseService courseService;
	
	@Autowired
	CourseRepository courseRepository; 
	

	@RequestMapping(value="/addEditCourse")
	public ModelAndView addView() {		
		return new ModelAndView("courseAndBatch/addEditCourse");
	}
	
	//getAllCourses
	@RequestMapping(value="/getAllCourses")//   /course/getAllCourses
	public List<Course> getAllCourses()
	{
		System.out.println("enter in coursesss");
		return courseService.getAllCourses();
	}
	
	@PostMapping("/addCourse")
	public Course addCourse(@RequestBody Course course)
	{
		Course createdCourse= courseService.addCourse(course);
		return createdCourse;
	}
	//getCourseById
	@RequestMapping(value="/getCourseDetailsById")
	public Course getCourseDetailsById(@RequestParam int courseId)
	{
		Course course=courseService.getCourseDetailsById(courseId);
		return course;
	}
	//getAllCoursesValidInvalid
	@RequestMapping(value="/getAllCoursesValidInvalid")
	public List<Course> getAllCoursesValidInvalid()
	{
		List<Course> courses=(List<Course>) courseRepository.findAll();
		return courses;
	}
	//added by bindu on 15/6/20200
	
	
	
	
	
	
	
	
	
	
	
}
