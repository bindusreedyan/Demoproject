package NUALS.AMS.ACADEMIC.ACTIVITY_TYPES;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import NUALS.AMS.UserauthObject;

@RestController
@RequestMapping("/academicactivity")
public class AcademicActivityTypesController {
	@Autowired
	AcademicActivityTypeService activityTypeService ;

	@RequestMapping(value="/addActivityTypeView")
	public ModelAndView ActivityTypeView(ModelAndView mv) {	
		System.out.println("entrrr in activuty type add");
		mv.setViewName("ACADEMICS/Add_Activity_Type");
		return mv;
	}
	
	@RequestMapping(value="/saveAcademicActivityType",method= RequestMethod.POST)
	public String saveHostel(@RequestBody AcademicActivityTypes acacttype,@RequestHeader MultiValueMap<String, String> headers) {
		
		AcademicActivityTypes activitytype = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			acacttype.setEnteredBy(userCode);
		//	acacttype.setCreateDateTime(new Date());
			acacttype.setActivity_type_status("submitted");
			acacttype.setCreateDateTime(new Date());
			activitytype = activityTypeService.saveActivityType(acacttype);
			System.out.println(activitytype);
		
			
			if(activitytype == null)
			{
				
				msg="Error in adding Activity Type. Contact Admin";
			}
			else
			{
			String display = "Activity type: "+acacttype.getActivityTypeCode()+"is successfully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/getAllActivityTypes")
	public List<AcademicActivityTypes> getAllActivityTypes()
	{
		return activityTypeService.getAllActiivityTypes();
	}
	
	@RequestMapping(value="/getAllActivityTypesbyVerified")
	public List<AcademicActivityTypes> getAllActivityTypesbyVerified()
	{
		return activityTypeService.getAllActivityTypesbyVerified();
	}
	
	@RequestMapping(value="/getAllActivityTypesbyApproved")
	public List<AcademicActivityTypes> getAllActivityTypesbyApproved()
	{
		return activityTypeService.getAllActivityTypesbyApproved();
	}
	
	
	@RequestMapping(value="/updateActivityType",method= RequestMethod.POST)
	public String updateActivityType(@RequestBody AcademicActivityTypes actvtyType,@RequestHeader MultiValueMap<String, String> headers) {
		
		AcademicActivityTypes aat = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			actvtyType.setVerifiedBy(userCode);
			//System.out.println(hostelMstr.getHostelId()+"---"+hostelMstr.getHostelName()+"0000"+hostelMstr.getAddress()+"pppp"+hostelMstr.getHostelType()+"0000"+hostelMstr.getAreaOfHostel()+"000"+hostelMstr.getAmenities()+"000"+hostelMstr.getGeoCoordinates()+"0000"+hostelMstr.getRemarks()+hostelMstr.getUpdateRemarks());
			
			aat = activityTypeService.updateActivityType(actvtyType);
			
			
			if(aat == null)
			{
				
				msg="NOTVERIFIED";
			}
			else
			{
				String display = "ActivityTypeCode: "+aat.getActivityTypeCode()+" <br/>Activity Type Code: "+aat.getActivity_type_status();
				msg="verified-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at updateHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/editActivityType")
	public ModelAndView editActivityType(ModelAndView mv) {	
		System.out.println("entrrr in edit type add");
		mv.setViewName("ACADEMICS/Edit_Activity_Type");
		return mv;
	}
	
	
	@RequestMapping(value="/approveActivityType",method= RequestMethod.POST)
	public String approveActivityType(@RequestBody AcademicActivityTypes actvtyType,@RequestHeader MultiValueMap<String, String> headers) {
		
		AcademicActivityTypes aat = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			actvtyType.setApprovedBy(userCode);
			//System.out.println(hostelMstr.getHostelId()+"---"+hostelMstr.getHostelName()+"0000"+hostelMstr.getAddress()+"pppp"+hostelMstr.getHostelType()+"0000"+hostelMstr.getAreaOfHostel()+"000"+hostelMstr.getAmenities()+"000"+hostelMstr.getGeoCoordinates()+"0000"+hostelMstr.getRemarks()+hostelMstr.getUpdateRemarks());
			
			aat = activityTypeService.approveActivityType(actvtyType);
			
			
			if(aat == null)
			{
				
				msg="NOTAPPROVED";
			}
			else
			{
				String display = "ActivityTypeCode: "+aat.getActivityTypeCode()+" <br/>Activity Type Code: "+aat.getActivity_type_status();
				msg="approved-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at updateHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/approveActivityType")
	public ModelAndView approveActivityType(ModelAndView mv) {	
		System.out.println("entrrr in approve type add");
		mv.setViewName("ACADEMICS/Approve Activity Type");
		return mv;
	}
	
	@RequestMapping(value="/deactivateActivityType",method= RequestMethod.POST)
	public String deactivateActivityType(@RequestBody AcademicActivityTypes actvtyType,@RequestHeader MultiValueMap<String, String> headers) {
		
		AcademicActivityTypes aat = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			actvtyType.setDeactivateBy(userCode);
			//System.out.println(hostelMstr.getHostelId()+"---"+hostelMstr.getHostelName()+"0000"+hostelMstr.getAddress()+"pppp"+hostelMstr.getHostelType()+"0000"+hostelMstr.getAreaOfHostel()+"000"+hostelMstr.getAmenities()+"000"+hostelMstr.getGeoCoordinates()+"0000"+hostelMstr.getRemarks()+hostelMstr.getUpdateRemarks());
			
			aat = activityTypeService.deactivatActivityType(actvtyType);
			
			
			if(aat == null)
			{
				
				msg="NOTDEACTIVATED";
			}
			else
			{
				String display = "ActivityTypeCode: "+aat.getActivityTypeCode()+" <br/>Activity Type Code: "+aat.getActivity_type_status();
				msg="Deactivate-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at updateHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/deactivateActivityType")
	public ModelAndView deactivateActivityType(ModelAndView mv) {	
		System.out.println("entrrr in approve type add");
		mv.setViewName("ACADEMICS/disableActivityType");
		return mv;
	}

	@RequestMapping(value="/getAllActivityTypesByCategory")
	public List<AcademicActivityTypes> getAllActivityTypesByCategory(@RequestParam String cat) {	
		System.out.println("enterrr in getAllActivityTypesByCategory ");
		
		List<AcademicActivityTypes> al=null;
		try
		{
			al=activityTypeService.getAllActivityTypeByCatery(cat);
			System.out.println("size of getAllActivityTypesByCategory"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
		
	
	}
	
	


}
