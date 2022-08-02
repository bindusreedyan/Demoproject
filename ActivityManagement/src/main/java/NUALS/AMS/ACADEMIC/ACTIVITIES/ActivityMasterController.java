package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import NUALS.AMS.UserauthObject;
import NUALS.AMS.UserauthObject1;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ActivityResourcePerson;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMasterService;
import NUALS.AMS.ACADEMIC.ADVANCE.ActivityAdvReq;

@RequestMapping("/academicactivity")
@RestController
public class ActivityMasterController {
	
	@Autowired
	ActivityMasterService ams;
	@Autowired
	ActivityCenterService acs;
	@Autowired
	ResourcePersonMasterService amsr;
	
	@Autowired
	OrderGenerationRep oG;
	
	@Autowired 
	ActivityMasterRepository amr;
	
	
	@Autowired 
	ActivityBudgetFundRep abf;
	@RequestMapping(value="/addActivityAcademicApproval")
	public ModelAndView addCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("ACADEMICS/ActivityMaster");
		return mv;
	}
	
	
	
	@RequestMapping(value="/addProgramAProvalForStudent")
	public ModelAndView addProgramAProvalForStudent(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("ACADEMICS/studentprogramapprovalform");
		return mv;
	}
	
	
	@RequestMapping(value="/facultyRecommendStudent")
	public ModelAndView facultyRecommendStudent(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("ACADEMICS/FacultyRecomProgramApproval");
		return mv;
	}
	
	@RequestMapping(value="/addActivityMaster",method= RequestMethod.POST)
	public String saveActivityMaster(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityMaster saved = null;
		String msg ="";
		try
		{
			//UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			
			UserauthObject1 user1 = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject1.class);
			String userCode = user1.getUsercode();
			String userType=user1.getUserType();
			System.out.println("User : "+userCode);	
			System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
			msg = ams.saveActivityMaster(am,userCode);
			System.out.println(msg);
			System.out.println("entrrrr in facutly code   activity master"+am.getFacultyCode());
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
	}
	
	@RequestMapping(value="/addActivityMasterByStudent",method= RequestMethod.POST)
	public String saveActivityMasterByStudent(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityMaster saved = null;
		String msg ="";
		try
		{
			//UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			
			UserauthObject1 user1 = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject1.class);
			String userCode = user1.getUsercode();
			String userType=user1.getUserType();
			System.out.println("User : "+userCode);	
			System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
			msg = ams.saveActivityMasterByStudent(am,userCode);
			System.out.println(msg);
			System.out.println("entrrrr in facutly code   activity master"+am.getFacultyCode());
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
	}
	
    //to get the details of all expheads
	@RequestMapping(value="/GetActivityMaster",method= RequestMethod.GET)
	public ActivityMaster updateAsNOofActivityMaster(@RequestParam String activityId)
	{
		ActivityMaster amster=null;
		String asnoformat;
		String asprefix="NUALS/ACCD/CPGIS/AC2/52/2016";
		System.out.println("NUALS/ACCD/CPGIS/AC2/52/2016"+activityId);
		try
		{
			amster=new ActivityMaster();
	//System.out.println("activity Iddddddd"+am.getActivityCode());
		
		int ascount=ams.getCountofAsNo();
		System.out.println("asno"+ascount);
		if(ascount==0)
		{
			asprefix=asprefix+"/1";
			amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
		}
		else
		{
			int nextvar=ascount+1;
			String next=Integer.toString(nextvar);
			asprefix=asprefix+"/"+next;
			amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
		}
		}
		catch(Exception e)
		{
			System.out.println("exception "+e);
		}
		return amster;
	}
	
	/*@RequestMapping(value="/updateAsNOofActivityMaster",method= RequestMethod.POST)
	public ActivityMaster updateAsNOofActivityMaster(@RequestBody Map<String,Object> body)
	{
		ActivityMaster amster=null;
		String asnoformat;
		String asprefix="NUALS/ACCD/CPGIS/AC2/52/2016";
		System.out.println("NUALS/ACCD/CPGIS/AC2/52/2016"+body.get("activityCode").toString());
		try
		{
			amster=new ActivityMaster();
	//	System.out.println("activity Iddddddd"+am.getActivityCode());
		int ascount=ams.getCountofAsNo();
		if(ascount==0)
		{
			asprefix=asprefix+"1";
			amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(body.get("activityCode").toString()));
		}
		else
		{
			int nextvar=ascount+1;
			String next=Integer.toString(nextvar);
			asprefix=next;
			amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(body.get("activityCode").toString()));
		}
		}
		catch(Exception e)
		{
			System.out.println("exception "+e);
		}
		return amster;
	}*/
	
	//to show academic approval form
	
	
	@RequestMapping(value="/acadamicApprovalView")
	public ModelAndView acadamicApprovalView(ModelAndView mv) {	
		System.out.println("entrrr in acadamicApprovalView");
		mv.setViewName("ACADEMICS/AcademicApproval");
		return mv;
	}
	
	
	@RequestMapping(value="/getAllActivitySubmitted")
	public List<ActivityMaster> getAllactivitisubmitted()
	{
		System.out.println("entrr in getAllActivitySubmitted");
		return ams.getAllActivitiesByStatus("submitted");
	}
	
	
	@RequestMapping(value="/getAllActivityForStudentSubmitted")
	public List<ActivityMaster> getAllActivityForStudentSubmitted()
	{
		System.out.println("entrr in getAllActivitySubmitted");
		return amr.getAllActivityForStudentSubmitted("studentsubmitted");
	}
	
	
	
	
	@RequestMapping(value="/getAllActivitySubmittedByFaculty")
	public List<ActivityMaster> getAllActivitySubmittedByFaculty(HttpServletRequest ht)
	{
		
		System.out.println("entrr in getAllActivitySubmitted");
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
		return ams.getAllActivitySubmittedByFaculty("submitted",userAuthData.getUsercode());
	
		
	}
	
	
	
	@RequestMapping(value="/getAllActivitySubmittedByStudent")
	public List<ActivityMaster> getAllActivitySubmittedByStudent(HttpServletRequest ht)
	{
		
		System.out.println("entrr in getAllActivitySubmitted");
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
		return amr.getAllVerifiedActivitiesByStudent(userAuthData.getUsercode());
	
		
	}
	
	@RequestMapping(value="/getAllVerifiedActivitiesByFacultyCordniatorBystatus")
	public List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniatorBystatus(HttpServletRequest ht)
	{
		
		System.out.println("entrr in getAllActivitySubmitted");
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
		return ams.getAllVerifiedActivitiesByFacultyCordniatorBystatus("submitted",userAuthData.getUsercode());
	
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/getAllActivityByFaculty")
	public List<ActivityMaster> getAllActivityByFaculty(HttpServletRequest ht)
	{
		
		System.out.println("entrr in getAllActivitySubmitted");
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
		return ams.getAllActivityByFaculty(userAuthData.getUsercode());
	
		
	}
	
	 //to get the details of all expheads
		@RequestMapping(value="/getAllActivityById",method= RequestMethod.GET)
		public ActivityMaster getAllActivityById(@RequestParam String activityId)
		{
			ActivityMaster amster=null;
			try
			{
				amster=ams.getAllActivityById(activityId);
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			return amster;
		}
	
		
		 //to get the details of all expheads
		@RequestMapping(value="/activityAcademicApproval",method= RequestMethod.POST)
		public ActivityMaster activityAcademicApproval(@RequestParam String activityId)
		{
			ActivityMaster amster=null;
			try
			{
				amster=ams.getAllActivityById(activityId);
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			return amster;
		}
		
		@RequestMapping(value="/AcademicApprove",method= RequestMethod.POST)
		public ActivityMaster AcademicApprove(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				
				
				
				saved = ams.academicApproveActivityMaster(am,userCode);
				System.out.println(saved);
				
				
				if(saved == null)
				{
					
					msg="Error in adding ActivityMaster Details. Contact Admin";
				}
				else
				{
				String display = "Center: "+saved.getDescription()+"in"+saved.getFinyear()+"is succesafully added";
					msg="SAVED-"+display;
				}
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  saved;
			
		}
		
		@RequestMapping(value="/facultyacademicApproveActivityMaster",method= RequestMethod.POST)
		public ActivityMaster facultyacademicApproveActivityMaster(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				
				
				
				saved = ams.facultyacademicApproveActivityMaster(am,userCode);
				System.out.println(saved);
				
				
				if(saved == null)
				{
					
					msg="Error in adding ActivityMaster Details. Contact Admin";
				}
				else
				{
				String display = "Center: "+saved.getDescription()+"in"+saved.getFinyear()+"is succesafully added";
					msg="SAVED-"+display;
				}
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  saved;
			
		}
		
		
			
		
		
		//financial approve

		@RequestMapping(value="/administrativeofficeverification")
		public ModelAndView administrativeofficeverification(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/AdministrativeOfficeVerification");
			return mv;
		}
		@RequestMapping(value="/financeofficeverification")
		public ModelAndView financeofficeverification(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/FinanceOfficeVerification");
			return mv;
		}
		
		@RequestMapping(value="/financeApprovalView")
		public ModelAndView financialApprovalView(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/ActivityFinanceApproval");
			return mv;
		}
		
		
		
		@RequestMapping(value="/getAllActivityAdminApproved")
		public List<ActivityMaster> getAllActivityAdminApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivitiesByStatus("academicapproved");
		}
		
		
		//get the list of activities of particualr facultyId
		
		@RequestMapping(value="/getAllVerifiedActivitiesByFacultyCordniator")
		public List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniator(@RequestParam String facultyId)
		{
			System.out.println("entrr in  getAllVerifiedActivitiesByFacultyCordniator");
			String status="finalapproved";
			return ams.getAllVerifiedActivitiesByFacultyCordniator(facultyId,status);
		}
		@RequestMapping(value="/getAllActivityFinancialApproved")
		public List<ActivityMaster> getAllActivitiesFinancialApproved()
		{
			System.out.println("entrr in financially approved");
			return ams.getAllActivitiesByStatus("financially approved");
		}
		
		
		@RequestMapping(value="/getAllActivitiesFinalOfficeApproved")
		public List<ActivityMaster> getAllActivitiesFinalOfficeApproved()
		{
			System.out.println("entrr in financially approved");
			return ams.getAllActivitiesByStatus("final office approved");
		}
		
		
		
		
		//final approving
		@RequestMapping(value="/finalActivityApprovalView")
		public ModelAndView finalActivityApprovalView(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/AcademicFinalApproval");
			return mv;
		}
		
		@RequestMapping(value="/activityFinalApproval")
		public String activityFinalApproval(@RequestParam String activityId,@RequestParam String finalApprovalRemarks,HttpServletRequest ht)
		{
			String successData="";
			try
			{
				
				Gson gson = new Gson();
				String jsonObj=ht.getHeader("userauthdatastring");
				UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
				System.out.println("entered in activityFinalApproval");
				ActivityMaster am=ams.activityFinalApproval(activityId,finalApprovalRemarks,userAuthData.getUsercode());
				if(am!=null)
				{
//				ActivityMaster amster=null;
//				String asnoformat;
//				String asprefix="NUALS/ACCD/CPGIS/AC2/52/2016";
//				System.out.println("NUALS/ACCD/CPGIS/AC2/52/2016"+activityId);
//				amster=new ActivityMaster();
//				
//					
//					int ascount=ams.getCountofAsNo();
//					System.out.println("asno"+ascount);
//					if(ascount==0)
//					{
//						asprefix=asprefix+"/1";
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//					}
//					else
//					{
//						int nextvar=ascount+1;
//						String next=Integer.toString(nextvar);
//						asprefix=asprefix+"/"+next;
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//						
//					}
					
					successData="Program is successfully Aprroved";
			}
				if(am==null)
				{
					successData="Error in approval";
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return successData;
		}
	
		
		
		@RequestMapping(value="/activityFinalApprovalOffice")
		public String activityFinalApprovalOffice(@RequestParam String activityId,@RequestParam String finalApprovalRemarks,HttpServletRequest ht)
		{
			String successData="";
			try
			{
				
				Gson gson = new Gson();
				String jsonObj=ht.getHeader("userauthdatastring");
				UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
				System.out.println("entered in activityFinalApproval");
				ActivityMaster am=ams.activityFinalApprovalOffice(activityId,finalApprovalRemarks,userAuthData.getUsercode());
				if(am!=null)
				{
//				ActivityMaster amster=null;
//				String asnoformat;
//				String asprefix="NUALS/ACCD/CPGIS/AC2/52/2016";
//				System.out.println("NUALS/ACCD/CPGIS/AC2/52/2016"+activityId);
//				amster=new ActivityMaster();
//				
//					
//					int ascount=ams.getCountofAsNo();
//					System.out.println("asno"+ascount);
//					if(ascount==0)
//					{
//						asprefix=asprefix+"/1";
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//					}
//					else
//					{
//						int nextvar=ascount+1;
//						String next=Integer.toString(nextvar);
//						asprefix=asprefix+"/"+next;
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//						
//					}
					
					successData="Program is successfully Aprroved";
			}
				if(am==null)
				{
					successData="Error in approval";
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return successData;
		}
		@RequestMapping(value="/activityCancel")
		public String activityCancel(@RequestParam String activityId,@RequestParam String cancelRemarks,HttpServletRequest ht)
		{
			String successData="";
			try
			{
				
				Gson gson = new Gson();
				String jsonObj=ht.getHeader("userauthdatastring");
				UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
				System.out.println("entered in activityFinalApproval");
				ActivityMaster am=ams.activityFinalApproval(activityId,cancelRemarks,userAuthData.getUsercode());
				if(am!=null)
				{
//				ActivityMaster amster=null;
//				String asnoformat;
//				String asprefix="NUALS/ACCD/CPGIS/AC2/52/2016";
//				System.out.println("NUALS/ACCD/CPGIS/AC2/52/2016"+activityId);
//				amster=new ActivityMaster();
//				
//					
//					int ascount=ams.getCountofAsNo();
//					System.out.println("asno"+ascount);
//					if(ascount==0)
//					{
//						asprefix=asprefix+"/1";
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//					}
//					else
//					{
//						int nextvar=ascount+1;
//						String next=Integer.toString(nextvar);
//						asprefix=asprefix+"/"+next;
//						amster=ams.updateActivityMasterAsno(asprefix,Integer.parseInt(activityId));
//						
//					}
					
					successData="Program is successfully cancelled";
			}
				if(am==null)
				{
					successData="Error in cancel";
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return successData;
		}
		
		
		
		//activities of finally approved activites
		@RequestMapping(value="/getAllActivityFinalApproved")
		public List<ActivityMaster> getAllActivityFinalApproved()
		{
			System.out.println("entrr in financially approved");
			return ams.getAllActivitiesByStatus("finalapproved");
		}
		
		@RequestMapping(value="/getAllActivityApproved")
		public List<ActivityMaster> getAllActivityApproved()
		{
			System.out.println("entrr in financially approved");
			return ams.getAllActivitiesApproved();
			
		}
		
		@RequestMapping(value="/administrativeApprovalView")
		public ModelAndView administrativeApprovalView(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/AdministrativeApproval");
			return mv;
		}
		
		@RequestMapping(value="/getAllActivityAdministativeApproved")
		public List<ActivityMaster> getAllActivityAdministativeApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivitiesByStatus("administrativeapproved");
		}
		
		
		@RequestMapping(value="/getAllActivityFinanceOfficepproved")
		public List<ActivityMaster> getAllActivityFinanceOfficepproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivitiesByStatus("finance office verified");
		}
		@RequestMapping(value="/getPreapprovedActivity")
		public ModelAndView getPreapprovedActivity(ModelAndView mv) {	
			System.out.println("entrrr in acadamicApprovalView");
			mv.setViewName("ACADEMICS/PreapprovedActivity");
			return mv;
		}

		
		
		@RequestMapping(value="/addActivityMasterforPreapproved",method= RequestMethod.POST)
		public ActivityMaster addActivityMasterforPreapproved(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				//System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				saved = ams.saveActivityMasterforPreApproved(am,userCode);
				System.out.println(saved);
				System.out.println("entrrrr in facutly code   activity master"+am.getFacultyCode());
				
				if(saved == null)
				{
					
					msg="Error in adding ActivityMaster Details. Contact Admin";
				}
				else
				{
				String display = "Center: "+saved.getDescription()+"in"+saved.getFinyear()+"is succesafully added";
					msg="SAVED-"+display;
				}
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  saved;
			
		}
		
		@RequestMapping(value="/getAllActivityForAdministrativeApproved")
		public List<ActivityMaster> getAllActivityForAdministrativeApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivityForAdministrativeApproved();
		}
		
		@RequestMapping(value="/getAllActivityForAdministrativeApprovedBylevel2")
		public List<ActivityMaster> getAllActivityForAdministrativeApprovedBylevel2()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return amr.getAllActivityForAdministrativeApprovedBylevel2();
		}
		
		
		
		
		@RequestMapping(value="/getAllActivityForAdministrativeOfficeApproved")
		public List<ActivityMaster> getAllActivityForAdministrativeOfficeApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return amr.getAllActivityForAdministrativeOfficeApproved();
		}
		
		@RequestMapping(value="/getAllActivityForAcademicApproved")
		public List<ActivityMaster> getAllActivityForAcademicApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivityForAcademicApproved();
		}
		
		@RequestMapping(value="/getAllActivityAcademicallyApproved")
		public List<ActivityMaster> getAllActivityAcademicallyApproved()
		{
			System.out.println("entrr in getAllActivitySubmitted");
			return ams.getAllActivitiesByStatus("academicapproved");
		}
		
		
		@RequestMapping(value="/getAllActivityfinallyApprovedByFaculty")
		public List<ActivityMaster> getAllActivityfinallyApprovedByFaculty(HttpServletRequest ht)
		{
			
			System.out.println("entrr in getAllActivitySubmitted");
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return ams.getAllActivitySubmittedByFaculty("finalapproved",userAuthData.getUsercode());
		
			
		}
		
		@RequestMapping(value="/getAllActivityfinallyApprovedByUser")
		public List<ActivityMaster> getAllActivityfinallyApprovedByUser(HttpServletRequest ht)
		{
			
			System.out.println("entrr in getAllActivityfinallyApprovedByUser");
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			System.out.println("userCode"+userAuthData.getUsercode());
			return amr.getAllActivityfinallyApprovedByUser(userAuthData.getUsercode(),"finalapproved");
		
			
		}
		
		@RequestMapping(value="/getActivityDetails")
		public ModelAndView getActivityDetails(ModelAndView mv,@RequestParam String activityId) {	
			System.out.println("activityId"+activityId);
			//mv.setViewName("ACADEMICS/ActivityMaster");
			return mv;
		}
		
		@RequestMapping(value="/getAsGenerationForm")
		public ModelAndView getAsGeerationForm(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/AsGeneration");
			return mv;
		}
		
		@RequestMapping(value="/asOrderGeneration",method= RequestMethod.POST)
		public OrderGenaration OrderGenaration(@RequestBody OrderGenaration af,@RequestHeader MultiValueMap<String, String> headers) {
			OrderGenaration saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = ams.asOrderGenaration(af,userCode);
				System.out.println("saved order"+saved.getOrderGenId());

				/* if(saved == null)
					{
						
						msg="NOTSAVED-"+"Error in Generating AS order ";	
					}
					else
					{
					String display = "Advance Request is succesafully added with RequestId:"+saved.getAdvReqId()+"";
						msg="SAVED-"+display;
					}*/
				
				 
				 
			}
			catch(Exception e)
			{
				
			}
			
			return saved;
			
			
		}
		
		@RequestMapping(value="/asOrderGenarationEdit",method= RequestMethod.POST)
		public String asOrderGenarationEdit(@RequestBody OrderGenaration af,@RequestHeader MultiValueMap<String, String> headers) {
			OrderGenaration saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = ams.asOrderGenarationEdit(af,userCode);
				 

				 if(saved == null)
					{
						
						msg="NOTSAVED-"+"Error in Generating AS order ";	
					}
					else
					{
					String display = "As Order is successfully edited ";
						msg="SAVED-"+display;
					}
				
				 
				 
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
			
		}
		
		
		@RequestMapping(value="/printProgramDetails")
		public ModelAndView printProgramDetails(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/ProgramApprovalReport");
			return mv;
		}
		@RequestMapping(value="/administrativeApprovalReportView")
		public ModelAndView administrativeApprovalReportView(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/AdministrativeApprovalReport");
			return mv;
		}
		
		
		
		
		
		@RequestMapping(value="/getAllAcademicApprovalDetailsReport")
		public AcademicPrintReport getAllAcademicApprovalDetailsReport(@RequestParam String activityId)
		{
			
			System.out.println("getAllAcademicApprovalDetailsReport"+activityId);
			AcademicPrintReport ar=new AcademicPrintReport();
			try
			{
			
			ActivityMaster amster =ams.getAllActivityById(activityId);
			ar.setTitle(amster.getTitle());
			ar.setDateFrom(amster.getDateFrom());
			ar.setDateTo(amster.getDateTo());
			ar.setDescription(amster.getDescription());
			ar.setTargetGroup(amster.getTargetGroup());
			ar.setOutcome(amster.getOutcome());
			ar.setDeviationJustification(amster.getDeviationJustification());
			ar.setAdminApprovalRemarks(amster.getAdminApprovalRemarks());
			List<Activity_Center> al=acs.getAllactiviticenters(Integer.parseInt(activityId));
			ArrayList<String> cen=new ArrayList<String>();
			for(int i=0;i<al.size();i++)
			{
				Activity_Center a=al.get(i);
				cen.add(a.getActvtyCenterKey().getCm().getCentre_code());
			}
			
			ar.setCentres(cen);
			
			List<ActivityResourcePerson> rp=amsr.getResourcepersondetailofcurrentActivity(activityId);
			
			ArrayList<String> res=new ArrayList<String>();
			for(int i=0;i<rp.size();i++)
			{
				ActivityResourcePerson a=rp.get(i);
				System.out.println(a.getActRePerKey().getRpm().getName()+" "+a.getActRePerKey().getRpm().getAddress()+a.getActRePerKey().getRpm().getDesignation());
				res.add(a.getActRePerKey().getRpm().getName()+" "+a.getActRePerKey().getRpm().getAddress()+a.getActRePerKey().getRpm().getDesignation());
			}
			
			ar.setCentres(cen);
			ar.setResourcePersons(res);
			
			System.out.println(ar.getTitle()+"  "+ar.getTargetGroup());
			}
			catch(Exception e)
			{
				
			}
			return ar;
		}
		
		@RequestMapping(value="/AdministrativeApprove",method= RequestMethod.POST)
		public ActivityMaster AdministrativeApprove(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				
				
				
				saved = ams.administrativeApproveActivityMaster(am,userCode);
				System.out.println(saved);
				
				
				if(saved == null)
				{
					
					msg="Error in adding ActivityMaster Details. Contact Admin";
				}
				else
				{
				String display = "Center: "+saved.getDescription()+"in"+saved.getFinyear()+"is succesafully added";
					msg="SAVED-"+display;
				}
				
				
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  saved;
			
		}
		@RequestMapping(value="/getAllAcademicProgramReport")
		public List<AcademicProgramReportClass> getAllAcademicProgramReport()
		{
			List<AcademicProgramReport> al=null;
			List<AcademicProgramReportClass> al1=new ArrayList<AcademicProgramReportClass>();
			try
			{
			
				al=ams.getAllAcademicProgramReport();
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AcademicProgramReport apm=al.get(i);
				AcademicProgramReportClass apr=new AcademicProgramReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apr.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		
		@RequestMapping(value="/academicProgramReportView")
		public ModelAndView academicProgramReportView(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/AcademicProgramReport");
			return mv;
		}
		
		@RequestMapping(value="/asgrantedReportView")
		public ModelAndView asgrantedReportView(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/AsIssueReport");
			return mv;
		}
		
		
		@RequestMapping(value="/getAllExpenditureSettled")
		public List<AcademicProgramReportClass> getAllExpenditureSettled()
		{
			List<AcademicProgramReport> al=null;
			List<AcademicProgramReportClass> al1=new ArrayList<AcademicProgramReportClass>();
			try
			{
			
				al=ams.getAllExpenditureSettled();
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AcademicProgramReport apm=al.get(i);
				AcademicProgramReportClass apr=new AcademicProgramReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apm.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
			
				apr.setAsSanctionDate(apm.getSanctionedDate());
				apr.setSanctionAmnt(apm.getExpAmountSanctioned());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		
		@RequestMapping(value="/expSettledReport")
		public ModelAndView expSettledReport(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/ExpSanctionedReport");
			return mv;
		}
		
		@RequestMapping(value="/facultyWiseReport")
		public ModelAndView facultyWiseReport(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACADEMICS/FacultyWiseReport");
			return mv;
		}
		
		
		@RequestMapping(value="/getAllProgramReportByfaculty")
		public List<AcademicProgramReportClass> getAllProgramReportByfaculty(HttpServletRequest ht)
		{
			List<AcademicProgramReport> al=null;
			List<AcademicProgramReportClass> al1=new ArrayList<AcademicProgramReportClass>();
			try
			{
				System.out.println("entrr in getAllActivitySubmitted");
				Gson gson = new Gson();
				String jsonObj=ht.getHeader("userauthdatastring");
				UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			
				al=ams.getAllProgramReportByfaculty(userAuthData.getUsercode());
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AcademicProgramReport apm=al.get(i);
				AcademicProgramReportClass apr=new AcademicProgramReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apm.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		
		
		@RequestMapping(value="/getAllPendingAdvanceSettlement")
		public List<AdvanceRequestReportClass> getAllPendingAdvanceSettlement()
		{
			List<AdvanceRequestReportInterface> al=null;
			List<AdvanceRequestReportClass> al1=new ArrayList<AdvanceRequestReportClass>();
			try
			{
				System.out.println("entrr in getAllPendingAdvanceSettlement");
			
			
				al=ams.getAllPendingAdvanceSettlement();
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AdvanceRequestReportInterface apm=al.get(i);
				AdvanceRequestReportClass apr=new AdvanceRequestReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apm.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
				
				apr.setAdvPurpose(apm.getAdvPurpose());
				apr.setAdvanceIssuedDate(apm.getAdvanceIssuedDate());
				apr.setAdvReqAmnt(apm.getAdvReqAmnt());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		
		
		@RequestMapping(value="/advancePendingReport")
		public ModelAndView advancePendingReport(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("ACTIVITYADVANCE/AdvancePending");
			return mv;
		}
		
		@RequestMapping(value="/centerWiseReport")
		public ModelAndView centerWiseReport(ModelAndView mv) {	
			System.out.println("entrrr in getAsGeerationForm");
			mv.setViewName("CENTER/CenterWise");
			return mv;
		}
		
		@RequestMapping(value="/getAllAcademicProgramReportByCenter")
		public List<AcademicProgramReportClass> getAllAcademicProgramReportByCenter(@RequestParam String center)
		{
			List<AcademicProgramReport> al=null;
			List<AcademicProgramReportClass> al1=new ArrayList<AcademicProgramReportClass>();
			try
			{
	
				al=ams.getAllAcademicProgramReportByCenter(center);
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AcademicProgramReport apm=al.get(i);
				AcademicProgramReportClass apr=new AcademicProgramReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apr.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		
		@RequestMapping(value="/ProgramReportByFaculty")
		public ModelAndView ProgramReportByFaculty(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/FacultyWiseProgram");
			return mv;
		}
		
		
		@RequestMapping(value="/getAllProgramReportByFaculty")
		public List<AcademicProgramReportClass> getAllProgramReportByfaculty(@RequestParam String facultyId)
		{
			List<AcademicProgramReport> al=null;
			List<AcademicProgramReportClass> al1=new ArrayList<AcademicProgramReportClass>();
			try
			{
	
				al=ams.getAllProgramReportByfaculty(facultyId);
				for(int i=0;i<al.size();i++)
				{
				String cen="";
				AcademicProgramReport apm=al.get(i);
				AcademicProgramReportClass apr=new AcademicProgramReportClass();
				apr.setActivityCode(apr.getActivityCode());
				apr.setProgramCat(apm.getProgramCat());
				apr.setProgramType(apr.getProgramType());
				apr.setTitle(apm.getTitle());
				apr.setFrom(apm.getFrom());
				apr.setTo(apm.getTo());
				apr.setTotalExp(apm.getTotalExp());
				apr.setProgramStatus(apm.getProgramStatus());
				apr.setEmpName(apm.getEmpName());
				apr.setAsAmount(apm.getAsAmount());
				apr.setAsSanctionDate(apm.getAsSanctionDate());
				List<Activity_Center> ac=acs.getAllactiviticenters(apm.getActivityCode());
				for(int j=0;j<ac.size();j++)
				{
					Activity_Center a=ac.get(j);
					
					cen=cen+a.getActvtyCenterKey().getCm().getCentre_code();
					apr.setCenter(cen);
				}	
				al1.add(apr);
				}
			
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			}
			
			return al1;
		}
		@RequestMapping(value="/getOrderGenerationInformation")
		public OrderGenaration getOrderGenerationInformation(String orderGenId)	
		{
			OrderGenaration og=null;
			try
			{
				og=oG.getOrderGenerationInformation(Integer.parseInt(orderGenId));
			}
			catch(Exception e)
			{
				
			}
			return og;
		}
		
		
		@RequestMapping(value="/getAllActivityFinalAndPreApproved")
		public List<ActivityMaster> getAllActivityFinalAndPreApproved()
		{
			System.out.println("entrr in financially approved");
			return amr.getAllActivityFinalAndPreApproved();
		}
		
		@RequestMapping(value="/getAllActivityFinalApprovedForAsIssue")
		public List<ActivityMaster> getAllActivityFinalApprovedForAsIssue()
		{
			System.out.println("entrr in financially approved");
			return amr.getAllActivityFinalApprovedForAsIssue();
		}
		
		
		
		
		
		
		@RequestMapping(value="/getAllActivityAcadmicApprovedByLogin")
		public List<ActivityMaster> findAllActivityAcadmicApprovedByLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.findAllActivityAcadmicApprovedByLogin(userAuthData.getUsercode());
		}
		
		@RequestMapping(value="/findAllActivityAdministrativeApprovedByLogin")
		public List<ActivityMaster> findAllActivityAdministrativeApprovedByLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.findAllActivityAdministrativeApprovedByLogin(userAuthData.getUsercode());
		}
		@RequestMapping(value="/findAllActivityFinancialApprovedByLogin")
		public List<ActivityMaster> findAllActivityFinancialApprovedByLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.findAllActivityFinancialApprovedByLogin(userAuthData.getUsercode());
		}
		
		
		@RequestMapping(value="/getOrderGenerationInformations")
		public List<OrderGenaration> getOrderGenerationInformations(String orderGenId)	
		{
			List<OrderGenaration> og=null;
			try
			{
				og=oG.getOrderGenerationInformations();
			}
			catch(Exception e)
			{
				
			}
			return og;
		}
		
		
		@RequestMapping(value="/getAllActivityFinancialOfficeApprovedByLogin")
		public List<ActivityMaster> getAllActivityFinancialOfficeApprovedByLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getAllActivityFinancialOfficeApprovedByLogin(userAuthData.getUsercode());
		}
		
		@RequestMapping(value="/getAllActivityFinalOfficeApprovedByLogin")
		public List<ActivityMaster> getAllActivityFinalOfficeApprovedByLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getAllActivityFinancialOfficeApprovedByLogin(userAuthData.getUsercode());
		}
		
		
		
		
		
		
		
		
		@RequestMapping(value="/getAllVerifiedActivitiesByFacultyCordniatorsubmitted")
		public List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniatorsubmitted(HttpServletRequest ht)
		{
			
			
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			
			System.out.println("entrr in  getAllVerifiedActivitiesByFacultyCordniator");
			String status="submitted";
			return ams.getAllVerifiedActivitiesByFacultyCordniator(userAuthData.getUsercode(),status);
		}
		
		@RequestMapping(value="/EditActivityMaster")
		public ModelAndView EditActivityMaster(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/EditActivityMaster");
			return mv;
		}
		
		
		@RequestMapping(value="/editActivityMaster",method= RequestMethod.POST)
		public String editActivityMaster(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				msg = ams.editActivityMaster(am,userCode);
				System.out.println(msg);
				System.out.println("entrrrr in facutly code   activity master"+am.getFacultyCode());
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
		}
		
		
		
		
		
		
		@RequestMapping(value="/editActivityMasterForAsRevision",method= RequestMethod.POST)
		public String editActivityMasterForAsRevision(@RequestBody ActivityMaster am,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityMaster saved = null;
			String msg ="";
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				System.out.println(am.getCenter1()+"nnn"+am.getCenter2()+"nnn"+am.getCenter3()+"nnn"+am.getCenter4()+am.getCenter5());
				msg = ams.editActivityMasterForAsRevision(am,userCode);
				System.out.println(msg);
				System.out.println("entrrrr in facutly code   activity master"+am.getFacultyCode());
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
		}
		
		
		
		
		@RequestMapping(value="/EditAdministrativeApproval")
		public ModelAndView EditAdministrativeApproval(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/AdministrativeApprovalEdit");
			return mv;
		}
		
		@RequestMapping(value="/getActivityBudgetHeadAmount",method=RequestMethod.GET)
		public List<ActivityBudgetFund> getBudgetFundAmount(@RequestParam String budheadId,@RequestParam String finyear)
		{
			List<ActivityBudgetFund> og=null;
			try
			{
				og=abf.getActivityBudgetHeadAmount(Integer.parseInt(budheadId), finyear);
				
				
				
				
			}
			catch(Exception e)
			{
				
			}
			return og;
					
		}
		
		@RequestMapping(value="/getAsEsDifferenceForClosedProgram",method=RequestMethod.GET)
		public List<AsEsDifferenceInterface> getAsEsDifferenceForClosedProgram(@RequestParam String budheadId,@RequestParam String finyear)
		{
			List<AsEsDifferenceInterface> og=null;
			try
			{
				og=abf.getAsEsDifferenceForClosedProgram(Integer.parseInt(budheadId), finyear);
				
				
				
				
			}
			catch(Exception e)
			{
				
			}
			return og;
					
		}
		@RequestMapping(value="/EditFinancialApproval")
		public ModelAndView EditFinancialApproval(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/financialApprovalEdit");
			return mv;
		}
		
		
		@RequestMapping(value="/finalProgrmEdit")
		public ModelAndView finalProgrmEdit(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/Finalprogramapprovaledit");
			return mv;
		}
		
		
		@RequestMapping(value="/getAllActivityFinallyApprovedBuLogin")
		public List<ActivityMaster> getAllActivityFinallyApprovedBuLogin(HttpServletRequest ht)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getAllActivityFinallyApprovedBuLogin(userAuthData.getUsercode());
		}
		
		@RequestMapping(value="/getActivityCountBasedOnactivitytype")
		public List<ProgramCount> getActivityCountBasedOnactivitytype(HttpServletRequest ht,@RequestParam String centreId,@RequestParam String finYear)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getActivityCountBasedOnactivitytype(Integer.parseInt(centreId),finYear);
		}
		@RequestMapping(value="/getActivityCountBasedOnactivitytypeLecture")
		public List<ProgramCount> getActivityCountBasedOnactivitytypeLecture(HttpServletRequest ht,@RequestParam String centreId,@RequestParam String finYear)
		{
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getActivityCountBasedOnactivitytype(Integer.parseInt(centreId),finYear);
		}
		
		@RequestMapping(value="/getAllActivitesForRevision")
		public List<ReApprovedActivityInterface> getAllActivitesForRevision(HttpServletRequest ht)
		{
			
			System.out.println("entrr in getAllActivitySubmitted");
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getAllActivitesForRevision();
		
			
		}
		@RequestMapping(value="/asRevise")
		public ModelAndView asRevise(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/AsRevision");
			return mv;
		}
		
		@RequestMapping(value="/getAllActivitesForCancel")
		public List<ReApprovedActivityInterface> getAllActivitesForCancel(HttpServletRequest ht)
		{
			
			System.out.println("entrr in getAllActivitySubmitted");
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			return amr.getAllActivitesForRevision();
		
			
		}
		
		@RequestMapping(value="/programCancel")
		public ModelAndView programCancel(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/ProgramCancel");
			return mv;
		}
		
		@RequestMapping(value="/programFinalApprovalOffice")
		public ModelAndView programFinalApprovalOffice(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/FinalProgramVerification");
			return mv;
		}
		
		@RequestMapping(value="/asOrderEdit")
		public ModelAndView asOrderEdit(ModelAndView mv) {	
			System.out.println("entrrr in ProgramReportByFaculty");
			mv.setViewName("ACADEMICS/asOrderEdit");
			return mv;
		}
		
}
