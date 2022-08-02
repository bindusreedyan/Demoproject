package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMasterService;
import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRERepository;

@RequestMapping("/academicfinance")
@RestController
public class ActivityCenterController {
	
	@Autowired
	ActivityCenterService acs;
	
	@RequestMapping(value="/addActivityCenter",method= RequestMethod.GET)
	public Activity_Center saveActivityResource(@RequestParam String activityId,@RequestParam String centerId) {
     System.out.println("entrrrrrrrr saveActivityResource");
     Activity_Center saved = null;
		String msg ="";
		try
		{
			System.out.println("activityid="+activityId+"yyyy"+centerId);
			saved=acs.saveActivityCenter(Integer.parseInt(activityId),Integer.parseInt(centerId));
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	@RequestMapping(value="/getAllactiviticenters")
	public List<Activity_Center> getAllactiviticenters(@RequestParam String activityId)
	{
		System.out.println("entrr in fund details");
		return acs.getAllactiviticenters(Integer.parseInt(activityId));
	}

	@RequestMapping(value="/updateActivityCenterFinance",method= RequestMethod.GET)
	public String updateActivityCenterFinance(@RequestParam String activityId,@RequestParam(value="centerarray") List<String> centerarray,@RequestParam(value="balassignArray") List<String> balassignArray,@RequestParam(value="estimateHeadsArray") List<String> estimateHeadsArray,@RequestParam(value="expIncurredArray") List<String> expIncurred,@RequestParam(value="seminarHeadsArray") List<String> seminarHeadsArray,@RequestParam(value="lectureHeadsArray") List<String> lectureHeadsArray,@RequestParam(value="externlfundArray") List<String> externlfundArray,@RequestParam(value="ugcFundArray") List<String> ugcFundArray)
	{
		System.out.println("entrr in fund details");
		return acs.updateActivityCenterFinance(activityId,centerarray,balassignArray,estimateHeadsArray,expIncurred,seminarHeadsArray,lectureHeadsArray,externlfundArray,ugcFundArray);
	}
	//data capturing for activityfinance
	
	@RequestMapping(value="/getAllActiveCenterByCenterIdsAndactiviy",method= RequestMethod.GET)
	public List<Activity_Center>getAllActiveCenterByCenterIdsAndactiviy(@RequestParam(value="centerarray") List<String> centerarray,@RequestParam String activityId )
	{
		System.out.println("size of resource person array-------------"+centerarray.size());
		List<Activity_Center> al=new ArrayList<Activity_Center>();
//		resIdarrea.forEach((temp) -> {
//	            System.out.println(temp);
//	        });
		
		al=acs.getAllActiveCenterByCenterIdsAndactiviy(centerarray,activityId);
		
		return al;
	}
	
	
	public Activity_Center editActivityCenter(@RequestParam String activityId,@RequestParam String centerId) {
	     System.out.println("entrrrrrrrr editActivityCenter");
	     Activity_Center saved = null;
			String msg ="";
			try
			{
				System.out.println("activityid="+activityId+"yyyy"+centerId);
				saved=acs.saveActivityCenter(Integer.parseInt(activityId),Integer.parseInt(centerId));
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  saved;
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
