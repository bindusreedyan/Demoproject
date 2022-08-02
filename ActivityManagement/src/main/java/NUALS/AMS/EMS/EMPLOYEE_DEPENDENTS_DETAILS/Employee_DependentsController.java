package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;

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
import NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS.Employee_Personal;



@RestController
@RequestMapping("/ems")
public class Employee_DependentsController
{	
	
	@Autowired
	Employee_DependentsService emplDependents ;
	
	@RequestMapping(value="/getAllEmplDependentsDetails")
	public List<Employee_Dependents> getAllEmplDepDetails(ModelAndView mv) {
		
		List<Employee_Dependents> data = emplDependents.getAllEmplDependentsDetails();
		
	   return data;
		
	}
	
	@RequestMapping(value="/getAllEmplDependentsDetailsWithName")
	public List<FORM_FIELDS> getAllEmplDependentsDetailsWithName(ModelAndView mv) {
		
		List<FORM_FIELDS> data = emplDependents.getAllEmplDependentsDetailsWithName();
		
	   return data;
		
	}
	
	@RequestMapping(value="/getAllEmplDependentsDetailsByMultipleStatus")
	public List<FORM_FIELDS> getAllEmplDependentsDetailsByMultipleStatus(@RequestParam("empdepstatus") String sts1) {
		
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
		
		
		List<FORM_FIELDS> data = emplDependents.getAllEmplDependentsDetailsByMultipleStatus(statuses);
		
	   return data;
		
	}
	
	
	
	@RequestMapping(value="/getEmplDependentsDetailsByEmpId")
	public FORM_FIELDS getEmplDependentsDetailsByEmpId(@RequestParam("empid") String empid,@RequestParam("depName") String depName,ModelAndView mv) {
		
		FORM_FIELDS data = emplDependents.getEmplDependentsDetailsById(empid,depName);
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/addEmplDependentsDet")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("EMS/Employee_Master_Details");
		return mv;
	}
	
	@RequestMapping(value="/saveEmplDependentsDet",method= RequestMethod.POST)
	public String saveEmplDependentsDet(@RequestBody FORM_FIELDS fields,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(fields.toString());
			
			EMP_DEPENDENTS_PKEY pkey = new EMP_DEPENDENTS_PKEY();
			pkey.setEmp_id(fields.getEmp_id());
			pkey.setEmp_dependent_name(fields.getEmp_dependent_name());
				
			
			Employee_Dependents dep = new Employee_Dependents();
			dep.setEmp_dependents_id(pkey);
			
			dep.setDeprelation(fields.getDeprelation());
			dep.setDepgender(fields.getDepgender());
			
			dep.setEmp_dependent_dob(fields.getEmp_dependent_dob());
			dep.setEmp_dependent_mobile(fields.getEmp_dependent_mobile());
			dep.setEmp_dependent_email_id(fields.getEmp_dependent_email_id());
			dep.setEmp_dependents_ident_type(fields.getEmp_dependents_ident_type());
			dep.setEmp_dependents_ident_no(fields.getEmp_dependents_ident_no());
			dep.setEmpdepstatus(fields.getStatus());
			
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			dep.setMaker(userCode);
						
			Employee_Dependents savedFH = emplDependents.addEmplDependentsDetails(dep);
			
			String msg="";
			if(savedFH == null)
			{
				msg = "NOTSAVED-0";
			}
			else
			{
				System.out.println("From Controller Record: "+savedFH.toString());
				
				String  depdob = savedFH.getEmp_dependent_dob().toString();
				String[] splits = depdob.split("-");
				String finaldob = splits[2]+"/"+splits[1]+"/"+splits[0];
				
				String details = "Dep.Name : "+savedFH.getEmp_dependents_id().getEmp_dependent_name()+"<br/> Dep.DOB: "+finaldob;
				
				msg="SAVED-"+details;
			}
			
	
		return msg;
	}
	
	@RequestMapping(value="/depL1Verify")
	public String depL1Verify(@RequestParam("emp_id") String emp_id,@RequestParam("depname") String depname,@RequestParam("empdepstatus") String empdepstatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Dependents ac = new Employee_Dependents();
			ac.setLevel1Checker(userCode);
			
			ac.setLevel1CheckDate(new Date());
			ac.setLevel1Remarks(level1Remarks);
			
			EMP_DEPENDENTS_PKEY pkey = new EMP_DEPENDENTS_PKEY();
			pkey.setEmp_id(emp_id);
			pkey.setEmp_dependent_name(depname);
			
			ac.setEmp_dependents_id(pkey);
			
			ac.setEmpdepstatus(empdepstatus);
			
			String res = emplDependents.depL1Check(ac);
			
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
	
	@RequestMapping(value="/updateEmplDependentsDet",method= RequestMethod.POST)
	public String updateEmplDependentsDet(@RequestBody FORM_FIELDS fields,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(fields.toString());
			
			EMP_DEPENDENTS_PKEY pkey = new EMP_DEPENDENTS_PKEY();
			pkey.setEmp_id(fields.getEmp_id());
			pkey.setEmp_dependent_name(fields.getEmp_dependent_name());
				
			
			Employee_Dependents dep = new Employee_Dependents();
			dep.setEmp_dependents_id(pkey);
			
			dep.setDeprelation(fields.getDeprelation());
			dep.setDepgender(fields.getDepgender());
			
			dep.setEmp_dependent_dob(fields.getEmp_dependent_dob());
			dep.setEmp_dependent_mobile(fields.getEmp_dependent_mobile());
			dep.setEmp_dependent_email_id(fields.getEmp_dependent_email_id());
			dep.setEmp_dependents_ident_type(fields.getEmp_dependents_ident_type());
			dep.setEmp_dependents_ident_no(fields.getEmp_dependents_ident_no());
			dep.setEmpdepstatus(fields.getStatus());
			
			
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			dep.setUpdateBy(userCode);
			
			dep.setUpdateRemarks(fields.getUpdateRemarks());
						
			Employee_Dependents savedFH = emplDependents.updateEmplDependentsDetails(dep);
			
			String msg="";
			if(savedFH == null)
			{
				msg = "NOTSAVED-0";
			}
			else
			{
				System.out.println("From Controller Record: "+savedFH.toString());
				
				String details = "Dep.Name : "+savedFH.getEmp_dependents_id().getEmp_dependent_name()+"<br/> Dep.DOB: "+savedFH.getEmp_dependent_dob();
				
				msg="SAVED-"+details;
			}
			
	
		return msg;
	}
	
	@RequestMapping(value="/approveDependents")
	public String approveDependents(@RequestParam("emp_id") String emp_id,@RequestParam("depname") String depname,@RequestParam("empdepstatus") String empdepstatus,@RequestParam("approvedRemarks") String approvedRemarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			Employee_Dependents ac = new Employee_Dependents();
			
			EMP_DEPENDENTS_PKEY pkey = new EMP_DEPENDENTS_PKEY();
			pkey.setEmp_id(emp_id);
			pkey.setEmp_dependent_name(depname);
			
			ac.setEmp_dependents_id(pkey);
			
			ac.setApprovedBy(userCode);			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setEmpdepstatus(empdepstatus);
			
			String res = emplDependents.approveDependent(ac);
			
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
