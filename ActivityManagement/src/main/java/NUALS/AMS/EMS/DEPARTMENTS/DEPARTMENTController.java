package NUALS.AMS.EMS.DEPARTMENTS;

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
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;

@RestController
@RequestMapping("/ems")
public class DEPARTMENTController
{	
	
	@Autowired
	DEPARTMENTService deptServ ;
	
	@RequestMapping(value="/getAllDept")
	public List<DEPARTMENT> getAllDepartments(ModelAndView mv) {
		
		List<DEPARTMENT> data = deptServ.getAllDepartments();
		
	   return data;		
	}
	
	@RequestMapping(value="/getAllDeptByStatus")
	public List<DEPARTMENT> getAllDeptByStatus(@RequestParam("depstatus") String sts) {
		
		List<DEPARTMENT> data = deptServ.getAllDepartmentsByStatus(sts);
		
	   return data;
		
	}
	
	/*
	@RequestMapping(value="/getAllDeptByMultipleStatus")
	public List<DEPARTMENT> getAllDeptByMultipleStatus(@RequestParam("depstatus1") String sts1,@RequestParam("depstatus2") String sts2) {
		
		ArrayList<String> statuses = new ArrayList<String>();
		statuses.add(sts1);
		statuses.add(sts2);
		
		List<DEPARTMENT> data = deptServ.getAllDepartmentsByMultipleStatus(statuses);
		
	   return data;
		
	}
	*/
	
	@RequestMapping(value="/getAllDeptByMultipleStatus")
	public List<DEPARTMENT> getAllDeptByMultipleStatus(@RequestParam("depstatus1") String sts1) {
		
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
		
		System.out.println("statuses length: "+statuses.size());
		List<DEPARTMENT> data = deptServ.getAllDepartmentsByMultipleStatus(statuses);
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/addDept")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("EMS/Department");
		return mv;
	}
	
	@RequestMapping(value="/saveDept",method= RequestMethod.POST)
	public String saveDept(@RequestBody DEPARTMENT dept,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(dept.toString());
			
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				dept.setMaker(userCode);
				
				DEPARTMENT savedFH = deptServ.addDepartment(dept);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "Dept.Id : "+savedFH.getDept_id()+"<br/> Dept.Name: "+savedFH.getDept_name()+"<br/>";
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
		return msg;
	}
	
	
	@RequestMapping(value="/verify_edit_department")
	public ModelAndView verify_edit_department(ModelAndView mv) {	
		mv.setViewName("EMS/Verify_Edit_Department");
		return mv;
	}
	
	
	@RequestMapping(value="/deptL1Verify")
	public String designL1Verify(@RequestParam("dept_id") String dept_id,@RequestParam("depstatus") String depstatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			DEPARTMENT ac = new DEPARTMENT();
			ac.setLevel1Checker(userCode);
			
			ac.setLevel1CheckDate(new Date());
			ac.setLevel1Remarks(level1Remarks);
			
			ac.setDept_id(Integer.parseInt(dept_id));
			ac.setDepstatus(depstatus);
			
			
			String res = deptServ.DeptL1Check(ac);
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
	
	@RequestMapping(value="/updateDept",method= RequestMethod.POST)
	public String updateDept(@RequestBody DEPARTMENT desg,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(desg.toString());
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				desg.setUpdateBy(userCode);
				
				DEPARTMENT savedFH = deptServ.updateDpartment(desg);
				
				if(savedFH == null)
				{
					msg = "NOTSAVED-0";
				}
				else
				{
					System.out.println("From Controller Record: "+savedFH.toString());
					
					String details = "Details Updated Successfully. <br/> Dept.ID : "+savedFH.getDept_id()+"<br/> DeptName: "+savedFH.getDept_name()+"<br/> Description: "+savedFH.getDescription();
					
					msg="SAVED-"+details;
				}
				
			}
			catch(Exception ex)
			{
				
			}
			
		return msg;
	}
	
	
	@RequestMapping(value="/approve_department")
	public ModelAndView approve_department(ModelAndView mv) {	
		mv.setViewName("EMS/Approve_Department");
		return mv;
	}
	
	@RequestMapping(value="/approveDept")
	public String approveDept(@RequestParam("dept_id") String dept_id,@RequestParam("depstatus") String depstatus,@RequestParam("approvedRemarks") String approvedRemarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			DEPARTMENT ac = new DEPARTMENT();
			ac.setApprovedBy(userCode);
			
			ac.setDept_id(Integer.parseInt(dept_id));
			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setDepstatus(depstatus);
			
			DEPARTMENT res = deptServ.approveDept(ac);
			
			System.out.println("Result from Service: "+res);
			if(res != null)
			{
				msg = "SAVED-"+"Approved Record.Details<br/> Dept.ID: "+dept_id+"<br/> Dept.Name : "+res.getDept_name()+"<br/> Description: "+res.getDescription()+"<br/> Approve Remarks: "+res.getApprovedRemarks();
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
	
/*	@RequestMapping(value="/report_dept")
	public List<DEPARTMENT> report_dept() {	
		
		List<DEPARTMENT> depts =  deptServ.getAllDepartments();
		
		Report_Departments rd = new Report_Departments(depts);
		
		return depts;
	}*/
	
	
	
	
	
}
