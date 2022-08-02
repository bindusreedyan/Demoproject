package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


import NUALS.AMS.UserauthObject;


@RequestMapping("/expenditurehead")
@RestController
public class ActivityAdvEstimateController {
	
	
	@Autowired ActivityAdvEstimateService aases;
	@RequestMapping(value="/addActivityAdvanceEstimateRequest",method= RequestMethod.POST)
	public List<ActivityAdvEstimate> saveActivityAdvEstimate(@RequestBody ActivityAdvanceEstimateRequestWrapper activtyEstimateAdvncewrapper,
			@RequestHeader MultiValueMap<String, String> headers) {
              System.out.println("entrrrrrrrr saveActivityAdvEstimate"+activtyEstimateAdvncewrapper.getActivtyEstimateAdvncewrapper().size());
              List<ActivityAdvEstimate> created=new ArrayList<ActivityAdvEstimate>();
             
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
		String userCode = user.getUsercode();
		System.out.println("User : "+userCode);	
			
			
			
			created=aases.addActivityAdvEstimate(activtyEstimateAdvncewrapper,userCode)	;	
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  created;
		
		
	}
	@RequestMapping(value="/getActivityAdvanceRequest",method=RequestMethod.GET)
	public List<ActivityAdvEstimate>getActivityAdvanceRequest(@RequestParam String activityId,@RequestParam String finyear)
	{
		List<ActivityAdvEstimate> al=new ArrayList<ActivityAdvEstimate>();
		al=null;
		try
		{
			System.out.println("getActivityAdvanceRequestController"+"activityId="+activityId+"finyear="+finyear);
			al=aases.getActivityAdvanceRequest(activityId,finyear);
			
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceRequest"+e);
		}
		return al;
	}
	
	
	/*@PostMapping("/addExaminationFeeMapping")
	public List<ExamFeeMapAmount> addExaminationFeeMapping(@RequestBody ExaminationFeeHeadAmountREquestWrapper examfeemap,
	HttpServletRequest ht)  
	{	
		
		System.out.println(examfeemap.getExaminationFeeHeadAmountMapWrappers().size());
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		
		System.out.println("gggggggg"+examfeemap);
		UserAuthData userAuthData = gson.fromJson(jsonObj, UserAuthData.class) ;
		List<ExamFeeMapAmount> createdFeeMaps=sfsFeeHeadAmountMapService.addExaminationFeeAmount(examfeemap,userAuthData);
		return createdFeeMaps;
	}*/
	
	
	
	
	@RequestMapping(value="/updateActivityAdvanceEstimateRequest",method= RequestMethod.POST)
	public List<ActivityAdvEstimate>updateActivityAdvanceEstimateRequest(@RequestBody ActivityAdvanceEstimateRequestWrapper activtyEstimateAdvncewrapper,
			@RequestHeader MultiValueMap<String, String> headers) {
              System.out.println("entrrrrrrrr saveActivityAdvEstimate"+activtyEstimateAdvncewrapper.getActivtyEstimateAdvncewrapper().size());
              List<ActivityAdvEstimate> created=new ArrayList<ActivityAdvEstimate>();
             
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
		String userCode = user.getUsercode();
		System.out.println("User : "+userCode);	
			
			
			
			created=aases.updateActivityAdvanceEstimateRequest(activtyEstimateAdvncewrapper,userCode)	;	
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  created;
		
		
	}
	
	
	@RequestMapping(value="/getAllAdvanceRequestFinallyApproved",method=RequestMethod.GET)
	public List<AdvanceRequestFinalApproval>getAllAdvanceRequestFinallyApproved()
	{
		List<AdvanceRequestFinalApproval> al=new ArrayList<AdvanceRequestFinalApproval>();
		al=null;
		try
		{
			
			al=aases.getAllAdvanceRequestFinallyApproved();
			
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceRequest"+e);
		}
		return al;
	}
	
	@RequestMapping(value="/advanceReleasedDetailsPerActivity",method=RequestMethod.GET)
	public List<ActivityAdvEstimate>advanceReleasedDetailsPerActivity(@RequestParam String activityId)
	{
		 List<ActivityAdvEstimate> created=new ArrayList<ActivityAdvEstimate>();
		 try
		 {
			 created=aases.advanceReleasedDetailsPerActivity(activityId);
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in advance Release"+e);
		 }
		 return created;
	}
	
	@RequestMapping(value="/updateActivityAdvanceEstimateRequestForEditProgram",method= RequestMethod.POST)
	public List<ActivityAdvEstimate>updateActivityAdvanceEstimateRequestForEditProgram(@RequestBody ActivityAdvanceEstimateRequestWrapper activtyEstimateAdvncewrapper,
			@RequestHeader MultiValueMap<String, String> headers) {
              System.out.println("entrrrrrrrr saveActivityAdvEstimate"+activtyEstimateAdvncewrapper.getActivtyEstimateAdvncewrapper().size());
              List<ActivityAdvEstimate> created=new ArrayList<ActivityAdvEstimate>();
             
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
		String userCode = user.getUsercode();
		System.out.println("User : "+userCode);	
			
			
			
			created=aases.updateActivityAdvanceEstimateRequestForEditProgram(activtyEstimateAdvncewrapper)	;	
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  created;
		
		
	}
	
	
	
	

}
