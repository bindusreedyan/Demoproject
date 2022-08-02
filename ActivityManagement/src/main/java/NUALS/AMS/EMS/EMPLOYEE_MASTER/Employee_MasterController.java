package NUALS.AMS.EMS.EMPLOYEE_MASTER;

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
public class Employee_MasterController
{	
	
	@Autowired
	Employee_MasterService emplMaster ;
	
	@RequestMapping(value="/getAllEmplMasterDetails")
	public List<Employee_Master> getAllEmplMasterDetails(ModelAndView mv) {
		
		List<Employee_Master> data = emplMaster.getAllEmplMasterDetails();
		
	   return data;
		
}
	
	@RequestMapping(value="/getAllEmplMasterDetailsByMultipleStatus")
	public List<Employee_Master> getAllEmplMasterDetailsByMultipleStatus(@RequestParam("empmasterstatus") String sts1) {
		
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
		
		
		
		
		List<Employee_Master> data = emplMaster.getAllByMultipleStatuses(statuses);
		
	   return data;
		
}
	
	@RequestMapping(value="/getEmplmntDetByEmpid")
	public Employee_Master getEmplmntDetByEmpid(@RequestParam("empid") String empid,ModelAndView mv) {
		
		Employee_Master data = emplMaster.getEmplmntDetByEmpid(empid);
		
	   return data;
		
}
	
	@RequestMapping(value="/getAllFacultyMasterDetails")
	public List<Employee_Master> getAllFacultyMasterDetails(ModelAndView mv) {
		
		List<Employee_Master> data = emplMaster.getAllFacultyMasterDetails();
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/addEmplMasterDet")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("EMS/Employee_Master_Details");
		return mv;
	}
	
	@RequestMapping(value="/saveEmplMasterDet",method= RequestMethod.POST)
	public String saveEmplMasterDet(@RequestBody Employee_Master empMstr,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(empMstr.toString());
			Employee_Master savedFH = null;
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				empMstr.setMaker(userCode);
				
				savedFH = emplMaster.addEmplMasterDetails(empMstr);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "Empl.Id : "+savedFH.getEmp_id()+"<br/> Name: "+savedFH.getEmp_name()+"<br/>  Gender: "+savedFH.getGender();
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
		return msg;
	}
	
	@RequestMapping(value="/createLogin")
	public ModelAndView createLogin(ModelAndView mv) {
	
		mv.setViewName("EMS/Employee_Login");
			
		return mv;
	}
	
	@RequestMapping(value="/allLogins")
	public List<LOGINS_INTERFACE> allLogins() {
		
		List<LOGINS_INTERFACE> logins = emplMaster.getAllLogins();
		return logins;
	}
	
	
	@RequestMapping(value="/verify_edit_Employee")
	public ModelAndView verify_edit_Employee(ModelAndView mv) {	
		mv.setViewName("EMS/Verify_Edit_Employee_Details");
		return mv;
	}
	
	@RequestMapping(value="/emplMasterL1Verify")
	public String emplmntL1Verify(@RequestParam("emp_id") String emp_id,@RequestParam("empmasterstatus") String empmasterstatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Master ac = new Employee_Master();
			ac.setLevel1Checker(userCode);
			
			ac.setLevel1CheckDate(new Date());
			ac.setLevel1Remarks(level1Remarks);
			
			ac.setEmp_id(emp_id);
			ac.setEmpmasterstatus(empmasterstatus);
			
			
			String res = emplMaster.emplmntL1Check(ac);
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
	
	@RequestMapping(value="/updateEmplMaster",method= RequestMethod.POST)
	public String updateEmplMaster(@RequestBody Employee_Master desg,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(desg.toString());
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				desg.setUpdateBy(userCode);
				
				Employee_Master savedFH = emplMaster.updateEmplMaster(desg);
				
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
	
	@RequestMapping(value="/approve_Employee")
	public ModelAndView approve_Employee(ModelAndView mv) {	
		mv.setViewName("EMS/Approve_Employee_Details");
		return mv;
	}
	
	
	@RequestMapping(value="/approveEmplMaster")
	public String approveEmplMaster(@RequestParam("emp_id") String emp_id,@RequestParam("empmasterstatus") String empmasterstatus,@RequestParam("approvedRemarks") String approvedRemarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Master ac = new Employee_Master();
			ac.setApprovedBy(userCode);
			
			ac.setEmp_id(emp_id);
			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setEmpmasterstatus(empmasterstatus);
			
			String res = emplMaster.approveEmplMaster(ac);
			
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
