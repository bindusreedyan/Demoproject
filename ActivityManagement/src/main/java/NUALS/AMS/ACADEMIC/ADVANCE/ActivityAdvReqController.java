package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import NUALS.AMS.UserauthObject;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ProgramApprovalAdvancePayment;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_MasterService;


@RequestMapping("/academicactivity")
@RestController
public class ActivityAdvReqController {
	@Autowired
	ActivityAdReqService aas;
	@Autowired
	ActivityAdvReqRep aar;
	
	@Autowired 
	Employee_MasterService ems;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	@RequestMapping(value="/addActivityAdvanceReq")
	public ModelAndView addActivityAdvanceReq(ModelAndView mv) {	
		System.out.println("entrrr in Advance Request");
		mv.setViewName("ACTIVITYADVANCE/AdvReq");
		return mv;
	}
	//add advance request Details
	@RequestMapping(value="/addAdvanceRequestDetails",method= RequestMethod.POST)
	public String addAdvanceRequestDetails(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
		ActivityAdvReq saved=null;
		 String msg=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			 saved = aas.saveActivityAdvanceRequestDetails(af,userCode);
			
			 if(saved == null)
				{
					
					msg="NOTSAVED-"+"Error in adding Advance Request Details ";	
				}
				else
				{
				String display = "Advance Request is succesafully added with RequestId:"+saved.getAdvReqId()+"";
					msg="SAVED-"+display;
				}
			 
		}
		catch(Exception e)
		{
			
		}
		
		return msg;
		
		
	}
	
	@RequestMapping(value="/findTotalSanctionAmnt",method= RequestMethod.GET)
	public String findTotalSanctionAmnt(@RequestParam String activityCode)
	{
		System.out.println("entrrrrrrrrrrr in findTotalSanctionAmnt"+activityCode);
		String amnt=null;
		try
		{
			double sanctionAmnt=aas.findTotalSanctionAmnt(Integer.parseInt(activityCode));
			amnt=Double.toString(sanctionAmnt);
			System.out.println("settledAmnt"+amnt);
		}
		catch(Exception e)
		{
			System.out.println("Exception in find Total Sanction Amount"+e);
		}
		return amnt;
	}
	//get all raised advance request by activityId and rollno
	@RequestMapping(value="/getAllAdvanceRequestRaisedByRollNo")
	public List<ActivityAdvReq> getAllAdvanceRequestRaisedByRollNo(@RequestParam String activityId,@RequestParam String rollno)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return aas.getAllAdvanceRequestRaised(activityId,rollno);
	}
	
	//get all raised advance request by activityId and rollno
	@RequestMapping(value="/findAdvReqByActivityCodeAndstRollNo")
	public List<ActivityAdvReq> findAdvReqByActivityCodeAndstRollNo(@RequestParam String activityId,@RequestParam String rollno)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return aar.findAdvReqByActivityCodeAndstRollNo(Integer.parseInt(activityId),rollno);
	}
	
	
	
	
	//get all raised advance request
	@RequestMapping(value="/getAllAdvanceRequestRaised")
	public List<ActivityAdvReq> getAllAdvanceRequestRaise()
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return aas.getAllAdvanceRequestRaised("Raised");
	}
	
	
	//get all raised advance request
		@RequestMapping(value="/getAllAdvanceRequestfinanceOfficeApproved")
		public List<ActivityAdvReq> getAllAdvanceRequestfinanceOfficeApproved()
		{
			System.out.println("entrr in getAllAdvanceRequestRaised");
			return aas.getAllAdvanceRequestRaised("FinanceOfficeApproved");
		}
	
	
	//get all raised advance request for facultyrecom
	@RequestMapping(value="/getAllAdvanceRequestRaisedForRecom")
	public List<advReqStudentData> getAllAdvanceRequestRaisedForRecom(HttpServletRequest ht)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		Gson gson = new Gson();
		String jsonObj=ht.getHeader("userauthdatastring");
		UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
		System.out.println("usercodeeee"+userAuthData.getUsercode());
		return aar.getAllAdvanceRequestRaisedForRecom(userAuthData.getUsercode());
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/FacultyRecomAdvReq")
	public ModelAndView FacultyRecomAdvReq(ModelAndView mv) {	
		System.out.println("entrrr in Advance Request");
		mv.setViewName("ACTIVITYADVANCE/AdvanceRequestRecommendation");
		return mv;
	}
	
	//get all raied advance request with id
	@RequestMapping(value="/getAllAdvanceRequestRaisedByAdvanceRequestId")
	public ActivityAdvReq getAllAdvanceRequestRaisedByAdvanceRequestId(@RequestParam String advanceRequestId)
	{
		
		ActivityAdvReq al=null;
		try
		{
			al= aas.getAllAdvanceRequestByRequestId(advanceRequestId);
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	
	
	//add advance request Details
		@RequestMapping(value="/facultyRecomAdvanceRequest",method= RequestMethod.POST)
		public String facultyRecomAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
			ActivityAdvReq saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = aas.facultyRecomAdvanceRequest(af,userCode);
				
				 if(saved == null)
					{
						
						msg="NOTSAVED-"+"Error in recommending Advance Request Details ";	
					}
					else
					{
					String display = "Advance Request: "+saved.getAdvReqId()+"is successfully Recommended";
						msg="SAVED-"+display;
					}
				 
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
			
		}
		
		
		@RequestMapping(value="/adminOfficeApprovalAdvanceRequest",method= RequestMethod.POST)
		public String adminOfficeApprovalAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
			ActivityAdvReq saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = aas.adminOfficeAdvanceRequest(af,userCode);
				
				 if(saved == null)
					{
						
						msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
					}
					else
					{
					String display = "Advance Request is successfully approved with :"+saved.getAdvReqId()+"";
						msg="SAVED-"+display;
					}
				 
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
			
		}
		
		
		//add advance request Details
				@RequestMapping(value="/adminApprovalAdvanceRequest",method= RequestMethod.POST)
				public String adminApprovalAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.adminAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully approved with:"+saved.getAdvReqId()+"";
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				@RequestMapping(value="/financeOfficeApprovalAdvanceRequest",method= RequestMethod.POST)
				public String financeOfficeApprovalAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.financeOfficeAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully approved with:"+saved.getAdvReqId();
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				
				//add advance request Details
				@RequestMapping(value="/adminAdvanceRequestEdit",method= RequestMethod.POST)
				public String adminAdvanceRequestEdit(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.adminAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully approved with "+saved.getAdvReqId()+"";
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				
				
				//add advance request Details
				@RequestMapping(value="/AdvanceRequestFacultyEdit",method= RequestMethod.POST)
				public String AdvanceRequestFacultyEdit(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.adminAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in editing Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully edited with "+saved.getAdvReqId()+"";
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				
				@RequestMapping(value="/adminApprovalAdvReq")
				public ModelAndView adminApprovalAdvReq(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestAdminApproval");
					return mv;
				}
				
				//get all raised advance request
				@RequestMapping(value="/getAllAdvanceRequestRecommended")
				public List<ActivityAdvReq> getAllAdvanceRequestRecommended()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllAdvanceRequestRaised("Recommended");
				}
				
				//get all raised advance request
				@RequestMapping(value="/getAllAdvanceRequestAdminOfficeApproved")
				public List<ActivityAdvReq> getAllAdvanceRequestAdminOfficeApproved()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllAdvanceRequestRaised("AdminOfficeApproved");
				}
				
			
		
		
				@RequestMapping(value="/findTotalRequestedAmnt",method= RequestMethod.GET)
				public List<ActivityAdvReq> findTotalRequestedAmnt(@RequestParam String activityCode,@RequestParam String particiaptionRequestId,@RequestParam String userCode)
				{
					
					List<ActivityAdvReq>  arq=null;
					System.out.println("entrrrrrrrrrrr in findTotalRequestedAmnt"+activityCode+"particiapteRequestId"+particiaptionRequestId);
					double totalRequestedAmnt=0.0;
					
					try
					{
						arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId), userCode);
	 			
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return arq;
				}
				
				
				@RequestMapping(value="/findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent",method= RequestMethod.GET)
				public List<ActivityAdvReq> findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent(@RequestParam String activityCode,@RequestParam String particiaptionRequestId,@RequestParam String rollNo)
				{
					
					List<ActivityAdvReq>  arq=null;
					System.out.println("entrrrrrrrrrrr in findTotalRequestedAmnt"+activityCode+"particiapteRequestId"+particiaptionRequestId);
					double totalRequestedAmnt=0.0;
					
					try
					{
						arq=aar.findTotalrequestedAmntByActivityIdAndPrcRqIdforstudent(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId), rollNo);
	 			
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return arq;
				}
				
				
				
				@RequestMapping(value="/financialApproval")
				public ModelAndView financialApproval(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/FinancialApproval");
					return mv;
				}
				
			
				//get all raised advance request
				@RequestMapping(value="/getAllAdvanceRequestApproved")
				public List<ActivityAdvReq> getAllAdvanceRequestApproved()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllAdvanceRequestRaised("AdminApproved");
				}
				
				
				//get all raised advance request
				@RequestMapping(value="/getAllAdvanceRequestfinanceofficeapproved")
				public List<ActivityAdvReq> getAllAdvanceRequestfinanceofficeapproved()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllAdvanceRequestRaised("FinanceOfficeApproved");
				}
				
				//get all  advance request which are not settled or closed
				@RequestMapping(value="/getAdvRequestNotSettled",method= RequestMethod.GET)
			    public List<ActivityAdvReq>  getAdvRequestNotSettledOrClosed(@RequestParam String rollno)
				{
					 int count=0;
					 String obj="";
					 List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>();
					try
					{
						 System.out.println("entrrrrrrr in getAdvRequestNotSettled");
						 al=aas.getAdvRequestNotSettledOrClosed(rollno);
						 
						// GsonBuilder gsonBuilder = new GsonBuilder();
						 //Gson gson = gsonBuilder.create();
					     //obj = gson.toJson(count);
					     System.out.println("al.size()"+al.size());
					}
					catch(Exception e)
					{
						System.out.println("Exception e"+e);
					}
					
					return al;
					
				}
				//get faculty advance Request
				
				@RequestMapping(value="/facultyAdvReqForm")
				public ModelAndView facultyAdvReqForm(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestFaculty");
					return mv;
				}
				
				//add advance request Details
				@RequestMapping(value="/financialApprovalAdvanceRequest",method= RequestMethod.POST)
				public String financialApprovalAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.financeAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully approved:"+saved.getAdvReqId();
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						System.out.println("Exception"+e);
					}
					
					return msg;
					
					
				}
				
				
				
				@RequestMapping(value="/financialApprovalAdvanceRequestEdit",method= RequestMethod.POST)
				public String financialApprovalAdvanceRequestEdit(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.financeAdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in approving Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request is successfully approved:"+saved.getAdvReqId();
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						System.out.println("Exception"+e);
					}
					
					return msg;
					
					
				}
				
				@RequestMapping(value="/advancePayment")
				public ModelAndView advancePayment(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvancePayment");
					return mv;
				}
				
				@RequestMapping(value="/getAllAdvanceRequestFinanciallyApproved")
				public List<ActivityAdvReq> getAllAdvanceRequestFinanciallyApproved()
				{
					System.out.println("entrr in getAllAdvanceRequestFinanciallyApproved");
					return aas.getAllAdvanceRequestRaised("FinanciallyApproved");
				}
				
				
				//add advance request Details
				@RequestMapping(value="/addAdvancePayment",method= RequestMethod.POST)
				public String addAdvancePayment(@RequestBody ProgramApprovalAdvancePayment af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.addAdvancePayment(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in adding Advance Payment Details ";	
							}
							else
							{
							String display = "Advance Payment: of  "+saved.getAdvReqId()+"is succesafully added";
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				
				@RequestMapping(value="/getAllAdvanceRequestFinanciallyApprovedAndNotPaid")
				public List<AdvanceForadvpayment> getAllAdvanceRequestFinanciallyApprovedAndNotPaid()
				{
					System.out.println("entrr in getAllAdvanceRequestFinanciallyApproved");
					return aas.getAllAdvanceRequestFinanciallyApprovedAndNotPaid();
				}
				
				@RequestMapping(value="/addActivityAdvanceReqForFaculty")
				public ModelAndView addActivityAdvanceReqForFaculty(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestFaculty");
					return mv;
				}		
				@RequestMapping(value="/findTotalRequestedAmntByFaculty",method= RequestMethod.GET)
				public List<ActivityAdvReq> findTotalRequestedAmntByFaculty(@RequestParam String activityCode,HttpServletRequest ht)
				{
					
					List<ActivityAdvReq>  arq=null;
					
					double totalRequestedAmnt=0.0;
					try
					{
						
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						//arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId));
						
						arq=aas.findTotalRequestedAmntByFaculty(Integer.parseInt(activityCode),userAuthData.getUsercode());
				
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return arq;
				}
				
				@RequestMapping(value="/getUserInfo",method= RequestMethod.GET)
				public Employee_Master getUserInfo(HttpServletRequest ht)
				{
					
					Employee_Master em=null;
					
				//	double totalRequestedAmnt=0.0;
					try
					{
						
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						//arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId));
						System.out.println("userCode"+userAuthData.getUsercode());
						em=ems.getEmplmntDetByEmpid(userAuthData.getUsercode());
				
					}
					catch(Exception e)
					{
						System.out.println("Exception"+e);
					}
					return em;
				}
				
				//add advance request Details
				@RequestMapping(value="/addAdvanceRequestDetailsofFaculty",method= RequestMethod.POST)
				public String addAdvanceRequestDetailsofFaculty(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.saveActivityAdvanceRequestDetails(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in adding Advance Request Details ";	
							}
							else
							{
							String display ="Advance Request is succesafully added with RequestId:"+saved.getAdvReqId();
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}	
	
				//add advance request Details
				@RequestMapping(value="/addAdvanceRequestDetailsofStudent",method= RequestMethod.POST)
				public String addAdvanceRequestDetailsofStudent(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.saveActivityAdvanceRequestDetailsByStudents(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in adding Advance Request Details ";	
							}
							else
							{
							String display ="Advance Request is succesafully added with RequestId:"+saved.getAdvReqId();
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}	
				
				
				@RequestMapping(value="/getAllAdvanceRequestForAdminApproval")
				public List<AdvReqDetailForm> getAllAdvanceRequestForAdminApproval()
				{
					System.out.println("entrr in getAllAdvanceRequestForAdminApproval");
					return aas.getAllAdvanceRequestForAdminApproval("AdminApproved");
				}
				
				
				
				//get all raised advance request
				@RequestMapping(value="/getAllActivitysubmitteddByFaculty")
				public List<ActivityAdvReq> getAllActivitysubmitteddByFaculty()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllActivitysubmitteddByFaculty("Raised");
				}
				
				//get all raised advance request
				@RequestMapping(value="/getAllActivityAdminOfficeApprovedFaculty")
				public List<ActivityAdvReq> getAllActivityAdminOfficeApprovedFaculty()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllActivitysubmitteddByFaculty("AdminOfficeApproved");
				}
				
				@RequestMapping(value="/facultyAdvanceAdminApproval")
				public ModelAndView facultyAdvanceAdminApproval(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/FacultyAdvanceRequestAdministrativeApproval");
					return mv;
				}
				
				
				
				@RequestMapping(value="/getUserInfoByUserCode",method= RequestMethod.GET)
				public Employee_Master getUserInfoByUserCode(@RequestParam  String userCode)
				{
					
					Employee_Master em=null;
					
					double totalRequestedAmnt=0.0;
					try
					{
						
						//Gson gson = new Gson();
					//	String jsonObj=ht.getHeader("userauthdatastring");
						//UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						//arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId));
						
						em=ems.getEmplmntDetByEmpid(userCode);
				
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return em;
				}
				
				
				//get all  advance request which are not settled or closed
				@RequestMapping(value="/getAdvRequestNotSettledByFaculty",method= RequestMethod.GET)
			    public List<ActivityAdvReq>  getAdvRequestNotSettledByFaculty(@RequestParam String userCode)
				{
					 int count=0;
					 String obj="";
					 List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>();
					try
					{
						 System.out.println("entrrrrrrr in getAdvRequestNotSettled");
						 al=aas.getAdvRequestNotSettledByFaculty(userCode);
						 
						// GsonBuilder gsonBuilder = new GsonBuilder();
						 //Gson gson = gsonBuilder.create();
					     //obj = gson.toJson(count);
					     System.out.println("al.size()"+al.size());
					}
					catch(Exception e)
					{
						System.out.println("Exception e"+e);
					}
					
					return al;
					
				}
				@RequestMapping(value="/facultyAdvanceFinanceApproval")
				public ModelAndView facultyAdvanceFinanceApproval(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request facultyAdvanceFinanceApproval");
					mv.setViewName("ACTIVITYADVANCE/FinancialApprovalAdvReqFaculty");
					return mv;
				}
				
				@RequestMapping(value="/getAllActivityAdminiApprovedByFaculty")
				public List<ActivityAdvReq> getAllActivityAdminiApprovedByFaculty()
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllActivitysubmitteddByFaculty("AdminApproved");
				}
				
		/*		@RequestMapping(value="/getUserInfoStudent",method= RequestMethod.GET)
				public Employee_Master getUserInfoStudent(HttpServletRequest ht)
				{
				
					
					double totalRequestedAmnt=0.0;
					try
					{
						
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						//arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId));
						
						em=ems.getEmplmntDetByEmpid(userAuthData.getUsercode());
				
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return em;
				}*/
				
				@RequestMapping(value="/getAllAdvRequestInformationByRequestId")
				public AdvanceReqReportClass getAllAdvRequestInformationByRequestId(@RequestParam String advReqId)
				{
					System.out.println("entrr in getAllAdvanceRequestRaised");
					return aas.getAllAdvRequestInformationByRequestId(Integer.parseInt(advReqId));
				}
				
				@RequestMapping(value="/getAllAdvRequestInformationByRequestIdForFaculty")
				public AdvanceReqReportClass getAllAdvRequestInformationByRequestIdForFaculty(@RequestParam String advReqId)
				{
					System.out.println("entrr in getAllAdvRequestInformationByRequestIdForFaculty");
					return aas.getAllAdvRequestInformationByRequestIdForFaculty(Integer.parseInt(advReqId));
				}
				
				@RequestMapping(value="/getAllAdvanceRequestSubmittedByStudent")
				public AdvanceReqReportClass getAllAdvanceRequestSubmittedByStudent(@RequestParam String advReqId)
				{
					System.out.println("entrr in getAllAdvRequestInformationByRequestIdForFaculty");
					return aar.getAllAdvRequestInformationByStudent(Integer.parseInt(advReqId));
				}
				
				
				
				@RequestMapping(value="/findAdvReqByRaisedBylogin",method= RequestMethod.GET)
				public	List<ActivityAdvReq> findAdvReqByRaisedBylogin(HttpServletRequest ht)
				{
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			            return aar.findAdvReqByRaisedBylogin(userAuthData.getUsercode());
	
				}
				
				
				@RequestMapping(value="/advanceRequestEdit")
				public ModelAndView EditAdvRequestForStudent(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestEdit");
					return mv;
				}
				
				
				//add advance request Details
				@RequestMapping(value="/editAdvanceRequest",method= RequestMethod.POST)
				public String editAdvanceRequest(@RequestBody ActivityAdvReq af,@RequestHeader MultiValueMap<String, String> headers) {
					ActivityAdvReq saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = aas.editdvanceRequest(af,userCode);
						
						 if(saved == null)
							{
								
								msg="NOTSAVED-"+"Error in editing Advance Request Details ";	
							}
							else
							{
							String display = "Advance Request: "+saved.getAdvReqId()+"is successfully edited";
								msg="SAVED-"+display;
							}
						 
					}
					catch(Exception e)
					{
						
					}
					
					return msg;
					
					
				}
				
				@RequestMapping(value="/findTotalRequestedAmntByFacultyForApproval",method= RequestMethod.GET)
				public List<ActivityAdvReq> findTotalRequestedAmntByFacultyForApproval(@RequestParam String activityCode,@RequestParam String userCode)
				{
					
					List<ActivityAdvReq>  arq=null;
					
					double totalRequestedAmnt=0.0;
					try
					{
						
					
						//arq=aas.findTotalRequestedAmnt(Integer.parseInt(activityCode),Integer.parseInt(particiaptionRequestId));
						
						arq=aas.findTotalRequestedAmntByFaculty(Integer.parseInt(activityCode),userCode);
				
					}
					catch(Exception e)
					{
						System.out.println("Exception in find Total Sanction Amount"+e);
					}
					return arq;
				}
			
				@RequestMapping(value="/findAdvReqByBylogin",method= RequestMethod.GET)
				public	List<ActivityAdvReq> findAdvReqByBylogin(HttpServletRequest ht)
				{
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			            return aar.findAdvReqByBylogin(userAuthData.getUsercode());
	
				}	
				@RequestMapping(value="/FacultyAdvRequestEdit")
				public ModelAndView FacultyAdvRequestEdit(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestFacultyEdit");
					return mv;
				}
				@RequestMapping(value="/FacultyAdvRequestAdminEdit")
				public ModelAndView FacultyAdvRequestAdminEdit(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/AdvanceRequestAdminApprovalEdit");
					return mv;
				}
				
				@RequestMapping(value="/FacultyAdvRequestFinanceEdit")
				public ModelAndView FacultyAdvRequestFinanceEdit(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/financeApprovalAdvanceRequestFacultyEdit");
					return mv;
				}
				
				@RequestMapping(value="/FacultyAdvRequestAdminofficeVerification")
				public ModelAndView FacultyAdvRequestAdminofficeVerification(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/adminOfficeFacultyAdvnrequest");
					return mv;
				}	
				
				
				@RequestMapping(value="/FacultyAdvRequestFinanceofficeVerification")
				public ModelAndView FacultyAdvRequestFinanceofficeVerification(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/financeAdvanceRequestVerification");
					return mv;
				}	
				
				@RequestMapping(value="/FacultyAdvRequestAdminofficeVerificationEdit")
				public ModelAndView FacultyAdvRequestAdminofficeVerificationEdit(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/adminOfficeApprovlEdit");
					return mv;
				}	
				
				@RequestMapping(value="/FacultyAdvRequestFinanceofficeVerificationEdit")
				public ModelAndView FacultyAdvRequestFinanceofficeVerificationEdit(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/financeOfficeAdvanceRequestVerificationEdit");
					return mv;
				}
				@RequestMapping(value="/StudentAdvRequestForProgram")
				public ModelAndView StudentAdvRequestForProgram(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/advanceRequestStudentPr");
					return mv;
				}
				
				@RequestMapping(value="/StudentAdvRequestForProgramForrecommendation")
				public ModelAndView StudentAdvRequestForProgramForrecommendation(ModelAndView mv) {	
					System.out.println("entrrr in Advance Request");
					mv.setViewName("ACTIVITYADVANCE/advanceRequestStFacultyRecommendation");
					return mv;
				}
				
				
				
}
