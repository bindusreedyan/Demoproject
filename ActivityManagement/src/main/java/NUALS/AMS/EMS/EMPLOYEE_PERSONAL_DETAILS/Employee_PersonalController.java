package NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS;

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
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;

@RestController
@RequestMapping("/ems")
public class Employee_PersonalController
{	
	
	@Autowired
	Employee_PersonalService emplPersonal ;
	
	@RequestMapping(value="/getAllEmplPersonalDetails")
	public List<FORM_FIELDS> getAllEmplPersonalDetails(ModelAndView mv) {
		
		List<FORM_FIELDS> data = emplPersonal.getAllEmplPersonalDetails();
		
	   return data;
		
	}
	
	@RequestMapping(value="/getAllEmplPersonalDetailsByMultipleStatus")
	public List<FORM_FIELDS> getAllEmplPersonalDetailsByMultipleStatus(@RequestParam("personalstatus") String sts1) {
		
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
		
		
		List<FORM_FIELDS> data = emplPersonal.getAllEmplPersonalDetailsByMultipleStatus(statuses);
		
	   return data;
		
	}
	
	@RequestMapping(value="/getPersonalDetByEmpid")
	public FORM_FIELDS getPersonalDetByEmpid(@RequestParam("empid") String empid,ModelAndView mv) {
		
		FORM_FIELDS data = emplPersonal.getEmplPersonalDetailsById(empid);
		
	   return data;
		
	}
	
	
	
	@RequestMapping(value="/addEmplPersonalDet")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("EMS/Employee_Master_Details");
		return mv;
	}
	
	@RequestMapping(value="/saveEmplPersonalDet",method= RequestMethod.POST)
	public String saveEmplPersonalDet(@RequestBody Employee_Personal empPers,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(empPers.toString());
			
			Employee_Personal savedFH = null;
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				empPers.setMaker(userCode);

				savedFH = emplPersonal.addEmplPersonalDetails(empPers);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "Empl.Id : "+savedFH.getEmp_id()+"<br/> DOB: "+savedFH.getEmp_dob()+"<br/>  Address: "+savedFH.getEmp_address();
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
	
		return msg;
	}
	
	@RequestMapping(value="/personalL1Verify")
	public String emplmntL1Verify(@RequestParam("emp_id") String emp_id,@RequestParam("personalstatus") String personalstatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Personal ac = new Employee_Personal();
			ac.setLevel1Checker(userCode);
			
			ac.setLevel1CheckDate(new Date());
			ac.setLevel1Remarks(level1Remarks);
			
			ac.setEmp_id(emp_id);
			ac.setPersonalstatus(personalstatus);
			
			
			String res = emplPersonal.personalL1Check(ac);
			System.out.println("Result from Service: "+res);
			if(res.equals("SAVED"))
			{
				msg = "SAVED-"+"Verified Record.<br/> ";
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
	
	@RequestMapping(value="/updatePersonal",method= RequestMethod.POST)
	public String updateEmplMaster(@RequestBody Employee_Personal pers,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(pers.toString());
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				pers.setUpdateBy(userCode);
				
				Employee_Personal savedFH = emplPersonal.updatePersonal(pers);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
									
					msg="SAVED-1";
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
		return msg;
	}
	
	
	
	@RequestMapping(value="/approvePersonal")
	public String approvePersonal(@RequestParam("emp_id") String emp_id,@RequestParam("personalstatus") String personalstatus,@RequestParam("approvedRemarks") String approvedRemarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Personal ac = new Employee_Personal();
			ac.setApprovedBy(userCode);
			
			ac.setEmp_id(emp_id);
			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setPersonalstatus(personalstatus);
			
			String res = emplPersonal.approvePersonal(ac);
			
			System.out.println("Result from Service: "+res);
			if(res.equals("SAVED"))
			{
				msg = "SAVED-"+"Approved Record.<br/> ";
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
