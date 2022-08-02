package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import NUALS.AMS.UserauthObject;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMasterService;
import NUALS.AMS.ACADEMIC.ACTIVITIES.activityResourcePerdetails;
import NUALS.AMS.ACADEMIC.ADVANCE.AdvExpenditure;

@RequestMapping("/resource")
@RestController
public class ResourcePersonMasterController {
	
	@Autowired
	ResourcePersonMasterService ams;
	
	@Autowired
	ResourcePersonMasterRepository rpr;
	
	
	@Autowired
	ActvityResourcePersonRepository rpmr;
	
	@RequestMapping(value="/addResource",method= RequestMethod.POST)
	public ResourcePersonMaster saveActivityMaster(@RequestBody ResourcePersonMaster rpm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ResourcePersonMaster saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			saved = ams.saveResourcePersonMaster(rpm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ResourcePersonMaster Details. Contact Admin";
			}
			else
			{
			String display = "Center: "+saved.getName()+"is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}

	@RequestMapping(value="/getResourcepersondetail",method= RequestMethod.GET)
	public List<ResourcePersonMaster>getResourcepersondetail()
	{
		List<ResourcePersonMaster> al=new ArrayList<ResourcePersonMaster>();
		
		al=ams.getResourcepersondetail();
		return al;
	}

	
	@RequestMapping(value="/getResourcepersondetailofcurrentActivity",method= RequestMethod.GET)
	public List<ResourcePersonMaster>getResourcepersondetailofcurrentActivity(@RequestParam(value="resIdarrea") List<String> resIdarrea )
	{
		System.out.println("size of resource person array-------------"+resIdarrea.size());
		List<ResourcePersonMaster> al=new ArrayList<ResourcePersonMaster>();
		resIdarrea.forEach((temp) -> {
	            System.out.println(temp);
	        });
		
		al=ams.getResourcepersondetailsCurrentActivity(resIdarrea);
		
		return al;
	}
	
	
	
	
	@RequestMapping(value="/addActivityResource",method= RequestMethod.GET)
	public ResourcePersonMaster saveActivityResource(@RequestParam String activityId,@RequestParam String resId) {
     System.out.println("entrrrrrrrr saveActivityResource");
		ResourcePersonMaster saved = null;
		String msg ="";
		try
		{
			System.out.println("activityid="+activityId+"yyyy"+resId);
			ams.saveActivityResource(Integer.parseInt(activityId),Integer.parseInt(resId));
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/getResourcepersondetailofcurrentActivityByActivityId",method= RequestMethod.GET)
	public List<ActivityResourcePerson>getResourcepersondetailofcurrentActivityByActivityId(@RequestParam String activityId)
	{
		System.out.println("ActivityId"+activityId);
		List<ActivityResourcePerson> al=new ArrayList<ActivityResourcePerson>();

		al=ams.getResourcepersondetailofcurrentActivity(activityId);
		
		return al;
	}
	
	
	
	
	
	@RequestMapping(value="/updateResourcePersons",method= RequestMethod.POST)
	public ResourcePersonMaster updateResourcePersons(@RequestBody ResourcePersonMaster rpm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ResourcePersonMaster saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			saved = ams.updateResourcePersonMaster(rpm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in editing ResourcePersonMaster Details. Contact Admin";
			}
			else
			{
			String display = "Resource Person is successfully updated";
				msg="Updated-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
			
	@RequestMapping(value="/getResourcepersondetailofById",method= RequestMethod.GET)
	public ResourcePersonMaster getResourcepersondetailofById(@RequestParam String resId )
	{
		System.out.println("resource person-------------"+resId);
	ResourcePersonMaster al=null;
		
		
		al=rpr.getResourcepersondetailofById(Integer.parseInt(resId));
		
		return al;
	}
			
	@RequestMapping(value="/deleteResourcePersonMaster",method= RequestMethod.GET)
	public String deleteResourcePersons(@RequestParam String resId) {
		
		ResourcePersonMaster saved = null;
		String msg ="";
		try
		{
			
			
			saved = ams.deleteResourcePersonMaster(Integer.parseInt(resId));
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in deleting ResourcePersonMaster Details. Contact Admin";
			}
			else
			{
			String display = "Resource Person is Deleted";
				msg="Deleted-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/getResourcepersondetailsById",method= RequestMethod.GET)
	public List<activityResourcePerdetails>getResourcepersondetailByactivitycode(@RequestParam String activityId)
	{
		System.out.println("ActivityId"+activityId);
		List<activityResourcePerdetails> al=new ArrayList<activityResourcePerdetails>();

		al=rpmr.getResourcepersondetailByactivitycode(Integer.parseInt(activityId));
		
		return al;
	}
	
}

