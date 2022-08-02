package NUALS.AMS.ACADEMIC.ACTIVITIES;

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

@RequestMapping("/academicactivity")
@RestController
public class ProgramApprovalAdvancePaymentController {
	@Autowired
	ProgramApprovalAdvancePaymentService praservice;
	
	@RequestMapping(value="/addProgramApprovalAdvancePayment",method= RequestMethod.POST)
	public String addProgramApprovalAdvancePayment(@RequestBody ProgramApprovalAdvancePayment expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ProgramApprovalAdvancePayment saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = praservice.savProgramApprovalAdvancePayment(expHm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				
				
				msg="Error in Saving";
				
				
				
			}
			else
			{
			String display = "Advance Payment is Done: ";
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("addProgramApprovalAdvancePayment: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/getAllAdvanceRecieved",method= RequestMethod.GET)
	public List<ProgramApprovalAdvancePayment> getAllAdvanceRecieved(@RequestParam String activityId) {
		
		ProgramApprovalAdvancePayment saved = null;
		String msg ="";
		
		List<ProgramApprovalAdvancePayment> al=new ArrayList<ProgramApprovalAdvancePayment>();
		
		try
		{
			
		
			al = praservice.getAllAdvanceRecieved(activityId);
			System.out.println(saved);
			
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("addProgramApprovalAdvancePayment: "+ex.getMessage());
		}
		
		return  al;
		
		
	}
	
	@RequestMapping(value="/getAdvancePaidByAdvancePaymentId",method= RequestMethod.GET)
	public ProgramApprovalAdvancePayment getAdvancePaidByAdvancePaymentId(@RequestParam String advancePaymentId) {
		
		ProgramApprovalAdvancePayment saved = null;
		String msg ="";
		
	ProgramApprovalAdvancePayment al=null;
		
		try
		{
			
		
			al = praservice.getAdvancePaidByAdvancePaymentId(advancePaymentId);
			System.out.println(saved);
			
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("addProgramApprovalAdvancePayment: "+ex.getMessage());
		}
		
		return  al;
		
		
	}
	@RequestMapping(value="/saveAdvanceSettlement",method= RequestMethod.POST)
	public String saveAdvanceSettlement(@RequestBody AdvanceSettlementClass expHm,@RequestHeader MultiValueMap<String, String> headers) {
		ProgramApprovalAdvancePayment saved=null;
		String msg=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    saved=praservice.saveAdvanceSettlement(expHm,userCode);
		    if(saved!=null)
		    {
		    	msg="advance is settled";
		    }
		    else
		    {
		    	msg="Error in Settelement";
		    }
		}
		catch(Exception e)
		{
			
		}
		
		return msg;
		
	}
}
