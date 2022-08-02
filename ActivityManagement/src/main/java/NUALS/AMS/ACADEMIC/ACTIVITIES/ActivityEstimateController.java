package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/expenditurehead")
@RestController
public class ActivityEstimateController {
	@Autowired
	ActivityExpEstimateService aes;
	
	@RequestMapping(value="/addActivityEstimate",method= RequestMethod.GET)
	public ActivityExpEstimate saveActivityEstimate(@RequestParam String activityId,@RequestParam String finyearexphead,@RequestParam(value="exheadarray") List<String> exheadarray,@RequestParam(value="allocationheadsarray") List<String> allocationheadsarray,@RequestParam(value="estimateheadsarray") List<String> estimateheadsarray,@RequestParam(value="deviationheadsarray") List<String> deviationheadsarray
			,@RequestParam(value="commentsheadsarray") List<String> commentsheadsarray) {
              System.out.println("entrrrrrrrr addActivityEstimate");
              ActivityExpEstimate saved = null;
		String msg ="";
		try
		{
			
			saved=aes.addActivityEstimate(activityId,finyearexphead,exheadarray,allocationheadsarray,estimateheadsarray,deviationheadsarray,commentsheadsarray);
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/getActivityExpHeadExpensesByActivityIdAndFinyear",method=RequestMethod.GET)
	public List<ActivityExpEstimate>getActivityExpHeadExpensesByActivityIdAndFinyear(@RequestParam String activityId,@RequestParam String finyear)
	{
		List<ActivityExpEstimate> al=new ArrayList<ActivityExpEstimate>();
		al=null;
		try
		{
			System.out.println("ActivityExpEstimateController"+"activityId="+activityId+"finyear="+finyear);
			al=aes.getActivityExpHeadExpensesByActivityIdAndFinyear(activityId,finyear);
			
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceRequest"+e);
		}
		return al;
	}
	
	
	
	
	@RequestMapping(value="/updateActivityEstimate",method= RequestMethod.GET)
	public ActivityExpEstimate updateActivityEstimate(@RequestParam String activityId,@RequestParam String finyearexphead,@RequestParam(value="exheadarray") List<String> exheadarray,@RequestParam(value="allocationheadsarray") List<String> allocationheadsarray,@RequestParam(value="estimateheadsarray") List<String> estimateheadsarray,@RequestParam(value="deviationheadsarray") List<String> deviationheadsarray
			,@RequestParam(value="commentsheadsarray") List<String> commentsheadsarray,@RequestParam(value="asAmntGrantArray") List<String> asAmntGrantArray) {
              System.out.println("entrrrrrrrr addActivityEstimate");
              ActivityExpEstimate saved = null;
		String msg ="";
		try
		{
			
			saved=aes.updateActivityEstimate(activityId,finyearexphead,exheadarray,allocationheadsarray,estimateheadsarray,deviationheadsarray,commentsheadsarray,asAmntGrantArray);
			
		}
		catch(Exception ex)
		{
			System.out.println("updateActivityEstimate: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/updateActivityEstimateForEditActivity",method= RequestMethod.GET)
	public ActivityExpEstimate updateActivityEstimateForEditActivity(@RequestParam String activityId,@RequestParam String finyearexphead,@RequestParam(value="exheadarray") List<String> exheadarray,@RequestParam(value="allocationheadsarray") List<String> allocationheadsarray,@RequestParam(value="estimateheadsarray") List<String> estimateheadsarray,@RequestParam(value="deviationheadsarray") List<String> deviationheadsarray
			,@RequestParam(value="commentsheadsarray") List<String> commentsheadsarray) {
              System.out.println("entrrrrrrrr addActivityEstimate");
              ActivityExpEstimate saved = null;
		String msg ="";
		try
		{
			
			saved=aes.updateActivityEstimateForEditActivity(activityId,finyearexphead,exheadarray,allocationheadsarray,estimateheadsarray,deviationheadsarray,commentsheadsarray);
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}

}
