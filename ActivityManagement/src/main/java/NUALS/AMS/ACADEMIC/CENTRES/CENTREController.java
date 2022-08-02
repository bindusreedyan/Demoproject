package NUALS.AMS.ACADEMIC.CENTRES;

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
@RequestMapping("/academicactivity")
public class CENTREController
{	
	
	@Autowired
	CENTREService centreServ ;
	
	
	
	@RequestMapping(value="/getAllCentres")
	public List<CENTRE> getAllCentres(ModelAndView mv) {
		
		List<CENTRE> data = centreServ.getAllCentres();
		
	   return data;		
	}
	
	@RequestMapping(value="/getAllValidCentres")
	public List<CENTRE> getAllValidCentres(ModelAndView mv) {
		

		List<CENTRE> data = centreServ.getAllCentresByStatus("valid");
		
	   return data;	
	}
	
	
	
	@RequestMapping(value="/getAllCentresByStatus")
	public List<CENTRE> getAllCentresByStatus(@RequestParam("centrestatus") String sts) {
		
		List<CENTRE> data = centreServ.getAllCentresByStatus(sts);
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/getAllCentresByMultipleStatus")
	public List<CENTRE> getAllCentresByMultipleStatus(@RequestParam("centrestatus") String sts1) {
		
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
		List<CENTRE> data = centreServ.getAllCentresByMultipleStatus(statuses);
		
	   return data;
		
	}
	
	
	@RequestMapping(value="/addCentre")
	public ModelAndView addView(ModelAndView mv) {	
		mv.setViewName("ACADEMICS/Centres");
		return mv;
	}
	
	@RequestMapping(value="/saveCentre",method= RequestMethod.POST)
	public String saveDept(@RequestBody CENTRE centre,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(centre.toString());
			
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				centre.setMaker(userCode);
				
				
				
				CENTRE savedFH = centreServ.addCentre(centre);
				
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
	
	/*
	@RequestMapping(value="/verify_edit_centre")
	public ModelAndView verify_edit_department(ModelAndView mv) {	
		mv.setViewName("EMS/Verify_Edit_Centre");
		return mv;
	}
	
	
	@RequestMapping(value="/centreL1Verify")
	public String centreL1Verify(@RequestParam("dept_id") String dept_id,@RequestParam("depstatus") String depstatus,@RequestParam("level1Remarks") String level1Remarks,@RequestHeader MultiValueMap<String, String> headers) {
		
		String msg = "-";
		
		try
		{
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			CENTRE ac = new CENTRE();
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
	public String updateDept(@RequestBody CENTRE desg,@RequestHeader MultiValueMap<String, String> headers) {
		
			System.out.println(desg.toString());
			
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				desg.setUpdateBy(userCode);
				
				CENTRE savedFH = deptServ.updateDpartment(desg);
				
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
			
			CENTRE ac = new CENTRE();
			ac.setApprovedBy(userCode);
			
			ac.setDept_id(Integer.parseInt(dept_id));
			
			ac.setApprovedDate(new Date());			
			ac.setApprovedRemarks(approvedRemarks);
			
			ac.setDepstatus(depstatus);
			
			CENTRE res = deptServ.approveDept(ac);
			
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
	
	@RequestMapping(value="/report_dept")
	public List<CENTRE> report_dept() {	
		
		List<CENTRE> depts =  deptServ.getAllDepartments();
		
		Report_Centres rd = new Report_Centres(depts);
		
		return depts;
	}
	*/
	
	
	
	
}
