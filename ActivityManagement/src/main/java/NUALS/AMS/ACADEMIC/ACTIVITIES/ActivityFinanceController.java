package NUALS.AMS.ACADEMIC.ACTIVITIES;

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

@RequestMapping("/academicfinance")
@RestController
public class ActivityFinanceController {
	
	@Autowired
	ActivityFinanceService afs;
	
	@RequestMapping(value="/addActivityFinance",method= RequestMethod.POST)
	public ActivityFinance saveActivityFinance(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.saveActivityFinance(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/getActivityFinance",method=RequestMethod.GET)
	public ActivityFinance getActivityFinance(@RequestParam String activityId)
	{
		ActivityFinance af=null;
		try
		{
			af=afs.getActivityFinance(activityId);
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		return af;
	}
	
	@RequestMapping(value="/updateActivityFinance",method= RequestMethod.POST)
	public ActivityFinance updateActivityFinance(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.updateActivityFinance(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/administrativeApproval",method= RequestMethod.POST)
	public ActivityFinance administrativeApproval(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.administrativeApproval(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	@RequestMapping(value="/facultyApproval",method= RequestMethod.POST)
	public ActivityFinance facultyApproval(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.facultyApproval(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	@RequestMapping(value="/administrativeOfficeApproval",method= RequestMethod.POST)
	public ActivityFinance administrativeOfficeApproval(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.administrativeOfficeApproval(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/editActivityFinance",method= RequestMethod.POST)
	public ActivityFinance editActivityFinance(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.editActivityFinance(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in editing ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucessfully edited financial details"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}	
					
	@RequestMapping(value="/editActivityFinanceForAsRevision",method= RequestMethod.POST)
	public ActivityFinance editActivityFinanceForAsRevision(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.editActivityFinanceForAsRevision(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in editing ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucessfully edited financial details"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}	
	
	@RequestMapping(value="/updateActivityFinanceverification",method= RequestMethod.POST)
	public ActivityFinance updateActivityFinanceverification(@RequestBody ActivityFinance af,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityFinance saved = null;
		String msg ="";
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			System.out.println(af.getAirTravelResIntnl());
			System.out.println("eeeeeeee"+af.getTotalAdvanceReq());
			
			System.out.println("eeeeeeee"+af.getHonorMore3000());
			//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
		    saved = afs.updateActivityFinanceverification(af,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding ActivityFinance Details. Contact Admin";
			}
			else
			{

				msg="sucees"+af.getActivityFinanceCode();
				
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  saved;
		
		
	}

}
