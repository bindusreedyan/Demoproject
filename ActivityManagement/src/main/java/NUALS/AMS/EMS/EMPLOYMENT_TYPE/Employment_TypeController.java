package NUALS.AMS.EMS.EMPLOYMENT_TYPE;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;

@RestController
@RequestMapping("/ems")
public class Employment_TypeController
{	
	
	@Autowired
	Employment_TypeService deptServ ;
	
	@RequestMapping(value="/getAllEmplmntTypes")
	public List<Employment_Type> getAllEmplmntTypes(ModelAndView mv) {
		
		List<Employment_Type> data = deptServ.getAllEmplmntTypes();
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/getAllEmplmntTypesByStatus")
	public List<Employment_Type> getAllEmplmntTypesByStatus(@RequestParam("emplmnttypestatus") String sts1) {
		
		ArrayList<String> statuses = new ArrayList<String>();
		sts1 = sts1.replace("[", "");
		sts1 = sts1.replace("]", "");
		sts1 = sts1.replace("\"", "");
		
		System.out.println("sts1: "+sts1);
		
		String[] splits = null;
		if(!sts1.equals(""))
		{
			splits = sts1.split(",");
		}
		
		
		for(String s:splits)
		{
			statuses.add(s);
		}
		
		List<Employment_Type> data = deptServ.getAllEmplmntTypes(statuses);
		
	   return data;
		
	}
	
	
	
	@RequestMapping(value="/addEmplmntType")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("EMS/Employment_Types");
		return mv;
	}
	
	@RequestMapping(value="/saveEmplmntType",method= RequestMethod.POST)
	public String saveDept(@RequestBody Employment_Type emplmntTy,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(emplmntTy.toString());
			
			Employment_Type savedFH = null;
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				emplmntTy.setMaker(userCode);
				
				savedFH = deptServ.addEmplmntType(emplmntTy);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "EmplmntType.Id : "+savedFH.getEmplmnt_type_id()+"<br/> Type.Name: "+savedFH.getEmplmnt_type_Name()+"<br/>";
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
	
		return msg;
	}
	
	@RequestMapping(value="/Verify_Edit_Employment_Types")
	public ModelAndView Verify_Edit_Employment_Types(ModelAndView mv) {	
		mv.setViewName("EMS/Verify_Edit_Employment_Types");
		return mv;
	}
	
	@RequestMapping(value="/emplmntL1Verify")
	public String emplmntL1Verify(@RequestParam("emplmnt_type_id") String emplmnt_type_id,@RequestParam("emplmnttypestatus") String emplmnttypestatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employment_Type ac = new Employment_Type();
			ac.setLevel1Checker(userCode);
			
			ac.setLevel1CheckDate(new Date());
			ac.setLevel1Remarks(level1Remarks);
			
			ac.setEmplmnt_type_id(Integer.parseInt(emplmnt_type_id));
			ac.setEmplmnttypestatus(emplmnttypestatus);
			
			
			String res = deptServ.emplmntL1Check(ac);
			System.out.println("Result from Service: "+res);
			if(res.equals("SAVED"))
			{
				msg = "SAVED-"+"Verified Record.Details<br/> ";
			}
			else
			{
				msg = "NOTSAVED"+"Error in Verification.<br/> Contact Admin..";
			}
			
		}
		catch(Exception ex)
		{
			
		}	
		
	   return msg;
		
	}
	
	@RequestMapping(value="/updateEmplmntType",method= RequestMethod.POST)
	public String updateEmplmntType(@RequestBody Employment_Type desg,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(desg.toString());
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				desg.setUpdateBy(userCode);
				
				Employment_Type savedFH = deptServ.updateEmplmntType(desg);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "Details Updated Successfully. <br/> Employment Type : "+savedFH.getEmplmnt_type_Name()+"<br/> Description: "+savedFH.getEmplmnt_type_description()+"<br/> UpdateRemarks: "+savedFH.getUpdateRemarks();
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
		return msg;
	}
	
	
	@RequestMapping(value="/Approve_Employment_Types")
	public ModelAndView Approve_Employment_Types(ModelAndView mv) {	
		mv.setViewName("EMS/Approve_Employment_Types");
		return mv;
	}
	
	@RequestMapping(value="/approveEmplmntType")
	public String approveEmplmntType(@RequestParam("emplmnt_type_id") String emplmnt_type_id,@RequestParam("emplmnttypestatus") String emplmnttypestatus,@RequestParam("approvedRemarks") String approvedRemarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employment_Type ac = new Employment_Type();
			ac.setApprovedBy(userCode);
			
			ac.setEmplmnt_type_id(Integer.parseInt(emplmnt_type_id));
			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setEmplmnttypestatus(emplmnttypestatus);
			
			Employment_Type savedFH = deptServ.approveEmplmntType(ac);
			
			System.out.println("Result from Service: "+savedFH);
			if(savedFH != null)
			{
				msg = "SAVED-"+"Approved Record.Details<br/> Employment Type : "+savedFH.getEmplmnt_type_Name()+"<br/> Description: "+savedFH.getEmplmnt_type_description()+"<br/> ApproveRemarks: "+savedFH.getApprovedRemarks();
			}
			else
			{
				msg = "NOTSAVED-"+"Error in Approval Process.<br/> Contact Admin..";
			}
			
		}
		catch(Exception ex)
		{
			
		}
		
	
		
	   return msg;
		
	}
	
	
}
