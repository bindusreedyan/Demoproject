package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFund;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFundRep;
import NUALS.AMS.ACADEMIC.ACTIVITIES.OrderGenaration;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
import NUALS.AMS.ACADEMIC.ADVANCE.AdvanceSettlementService;
import NUALS.AMS.ACADEMIC.ADVANCE.Esgeneration;


@RequestMapping("/academicactivity")
@RestController
public class ActivityMasterDataController {
	
	@Autowired
	ActiveMasterDataService  ams;
	@Autowired
	ActivityParticipateMemberService  apms;
	
	@Autowired
	ActivityParticipantRep aar;
	
	@Autowired
	ActiveMasterDataRepository adr;
	
	@Autowired 
	ActivityBudgetFundRep abff;
	
	@Autowired
	AdvanceSettlementService aas;
	
	
	
	@Autowired
	ParticipationEsorderRep per;
	
	@Autowired
	ParticipationAsOrderRep par;
	@RequestMapping(value="/saveActivityParticiaptionMasterData",method= RequestMethod.POST)
	public ActivityParticipateMasterData saveActivityParticipationMasterData(@RequestBody ActivityParticipateMasterData acacttype,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipateMasterData apm = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			String mobile=user.getMobile();
			System.out.println("User : "+userCode);	
			System.out.println("traveldateonwardstart"+acacttype.getTravelDateOnwardStart());
			acacttype.setEnteredBy(userCode);
		//	acacttype.setEnteredDate(new Date());
			acacttype.setParticipationRequestStatus("submitted");
			apm = ams.saveActivityParticipationRequestMasterData(acacttype,userCode,mobile);
			System.out.println(apm);
		
			
			if(apm == null)
			{
				
				msg="Error in adding Activity Type. Contact Admin";
			}
			else
			{
			String display = "ActivityParticipation Request: is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  apm;
		
		
	}
	
	@RequestMapping(value="/addMemberPersonDeatails",method= RequestMethod.POST)
	public String addMemberPersonDeatails(@RequestBody ActivityMemberFormFields am,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipateMember apm = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			
			apm = apms.saveActivityMemberDetails(am,userCode);
			
		
			
			if(apm == null)
			{
				
				msg="Error in adding Activity Type. Contact Admin";
			}
			else
			{
			String display = "ActivityParticipation Request: is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/getActivityParticipantDetails",method= RequestMethod.GET)
	public List<TeamActivityParticipateDetails> getActivityParticipantDetails() {
		List<TeamActivityParticipateDetails> participate=apms.getActivityParticipantDetails();
		return participate;
		
		
	}
	
	
	@RequestMapping(value="/getActivityParticipantDetailsbyRollno",method= RequestMethod.GET)
	public List<TeamActivityParticipateDetails> getActivityParticipantDetails(@RequestParam String rollno) {
		List<TeamActivityParticipateDetails> participate=apms.getActivityParticipantDetailsByRollno(rollno);
		return participate;
		
		
	}
	
	@RequestMapping(value="/getStudentActivitesBetweenDates",method= RequestMethod.GET)
	public List<TeamActivityParticipateDetailsByStudent> getStudentActivitesBetweenDates(@RequestParam String studentAdmissionCode,String fromDate,String toDate) {
		
		List<TeamActivityParticipateDetailsByStudent> participate=null;
		try
		{
			System.out.println("entrrrrrrrrrr getStudentActivitesBetweenDates"+studentAdmissionCode);
		
		 participate=apms.getStudentActivitesBetweenDates(studentAdmissionCode,fromDate,toDate);
		return participate;
		}
		catch(Exception e)
		{
			System.out.println("exception e"+e);
		}
		
		return participate;
		
	}
	
	
	
	
	
	@RequestMapping(value="/addMemberOnwardDetails",method= RequestMethod.POST)
	public String addMemberOnwardDetails(@RequestBody ParticipantJourneyTravelRequestWrapper amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivtyMemberOnwardTravelDetailsForm amotf = null;
		String msg ="";
		
		try
		{  
		
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.addActivityOnwardJourneyDetails(amtd)	;	
		
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/addMemberReturnDetails",method= RequestMethod.POST)
	public String addMemberReturnDetails(@RequestBody ParticipantJourneyReturnTravelRequestWrapper amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivtyMemberReturnTravelDetailsForm amotf = null;
		String msg ="";
		
		try
		{  
		
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			List<ActivtyMemberReturnTravelDetailsForm> created=new ArrayList<ActivtyMemberReturnTravelDetailsForm>();
			created=apms.addActivityReturnJourneyDetails(amtd)	;	
	
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/addMemberAccomadationDetails",method= RequestMethod.POST)
	public String addMemberAccomadationDetails(@RequestBody ParticipantJourneyAccomadationRequestWrapper amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		TeamParticipantAccomadationDetails amotf = null;
		String msg ="";
		
		try
		{  
		
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			List<TeamParticipantAccomadationDetails> created=new ArrayList<TeamParticipantAccomadationDetails>();
			created=apms.AddTeamParticipantAccomadationDetails(amtd)	;	
	
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	@RequestMapping(value="/addActivityParticipantOtherExpenses",method= RequestMethod.POST)
	public String addActivityParticipantOtherExpenses(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipationOtherExpenses created = null;
		String msg ="";
		
		try
		{  
		
		//	System.out.println()
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			System.out.println(amtd.getBindingAmntAdmit()+amtd.getBindingAmntSpent());
			System.out.println("User : "+userCode);	
			//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.addActivityParticipantOtherExpenses(amtd)	;	
	    if(created!=null)
	    {
	    	msg="success";
	    }
	    else
	    {
	    	msg="saving of otherdetails is failed";
	    }
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/ActivityParticipantOtherExpensesEdit",method= RequestMethod.POST)
	public String ActivityParticipantOtherExpensesEdit(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipationOtherExpenses created = null;
		String msg ="";
		
		try
		{  
		
		//	System.out.println()
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			System.out.println(amtd.getBindingAmntAdmit()+amtd.getBindingAmntSpent());
			System.out.println("User : "+userCode);	
			//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.ActivityParticipantOtherExpensesEdit(amtd)	;	
	    if(created!=null)
	    {
	    	msg="success";
	    }
	    else
	    {
	    	msg="saving of otherdetails is failed";
	    }
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	//add particiapnt other details
	@RequestMapping(value="/addActivityParticipantOtherDetails",method= RequestMethod.POST)
	public String addActivityParticipantOtherDetails(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipationOtherExpenses created = null;
		String msg ="";
		
		try
		{  
		
		   System.out.println("entrrrr in addActivityParticipantOtherDetails");
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode+"administrative sanction"+amtd.getPriorAdministrativeSanction());	
			//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.addActivityParticipantOtherDetails(amtd)	;	
	    if(created!=null)
	    {
	    	msg="success";
	    }
	    else
	    {
	    	msg="saving of otherexpenses is failed";
	    }
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	//add particiapnt other details
		@RequestMapping(value="/editActivityParticipantOtherDetails",method= RequestMethod.POST)
		public String editActivityParticipantOtherDetails(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityParticipationOtherExpenses created = null;
			String msg ="";
			
			try
			{  
			
			   System.out.println("entrrrr in addActivityParticipantOtherDetails");
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode+"administrative sanction"+amtd.getPriorAdministrativeSanction());	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.editActivityParticipantOtherDetails(amtd);	
		    if(created!=null)
		    {
		    	msg="success";
		    }
		    else
		    {
		    	msg="saving of otherexpenses is failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
	
	
	//add particiapnt other details
	@RequestMapping(value="/addActivityBankDetails",method= RequestMethod.POST)
	public String addActivityBankDetails(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipationOtherExpenses created = null;
		String msg ="";
		
		try
		{  
		
		   System.out.println("entrrrr in addActivityParticipantOtherDetails");
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.addActivityBankDetails(amtd)	;	
	    if(created!=null)
	    {
	    	msg="success";
	    }
	    else
	    {
	    	msg="saving of otherexpenses is failed";
	    }
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/ActivityBankDetailsEdit",method= RequestMethod.POST)
	public String ActivityBankDetailsEdit(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivityParticipationOtherExpenses created = null;
		String msg ="";
		
		try
		{  
		
		   System.out.println("entrrrr in addActivityParticipantOtherDetails");
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
			created=apms.ActivityBankDetailsEdit(amtd)	;	
	    if(created!=null)
	    {
	    	msg="success";
	    }
	    else
	    {
	    	msg="saving of otherexpenses is failed";
	    }
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	
	@RequestMapping(value="/activityParticipationRequestViewL1Check")
		public ModelAndView activityParticipationRequestViewL1Check(ModelAndView mv) {	
			System.out.println("entrrr in activityParticipationRequestViewL1Check");
			mv.setViewName("ACTIVITYPARTICIPATION/ParticiapationAccomadationL1Verification");
			return mv;
		}
	
	
	//to get the particiapterequestwith status submmitted
	@RequestMapping(value="/getAllParticipantRequestSubmitted",method= RequestMethod.GET)
	public List<ActivityParticipateMasterData> getAllParticipantRequestSubmitted() {
		List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("submitted");
		return participate;
		
		
	}
	
	
	
	//to get the particiapterequestwith status submmitted
	@RequestMapping(value="/getAllParticipantRequestForFacultyRecom",method= RequestMethod.GET)
	public List<ActivityParticipateMasterData> getAllParticipantRequestForFacultyRecom() {
		List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatusnot("finallyapproved");
		return participate;
		
		
	}

	
	@RequestMapping(value="/getAllParticipantRequestById",method= RequestMethod.GET)
	public ActivityParticipateMasterData getAllParticipantRequestById(@RequestParam String particiaptionRequestId) {
		int participateRequestId1=Integer.parseInt(particiaptionRequestId);
		
		ActivityParticipateMasterData participate=apms.getActivityParticipateRequestById(participateRequestId1);
		return participate;
		
		
	}
	//edit  ParticipationMaster Data
	
	@RequestMapping(value="/editActivityParticiaptionMasterData",method= RequestMethod.POST)
	public ActivityParticipateMasterData editActivityParticipationMasterData(@RequestBody ActivityParticipateMasterData acacttype,@RequestHeader MultiValueMap<String, String> headers) {
		ActivityParticipateMasterData apm = null;
		String msg ="";
		
		try
		{
			
			
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			//System.out.println("User : "+userCode);	
			//System.out.println("traveldateonwardstart"+acacttype.getTravelDateOnwardStart());
			//acacttype.setEnteredBy(userCode);
		//	acacttype.setEnteredDate(new Date());
			//acacttype.setParticipationRequestStatus("submitted");
			apm = ams.editActivityParticipationRequestMasterData(acacttype,userCode);
			System.out.println(apm);
		
			
			if(apm == null)
			{
				
				msg="Error in adding Activity Type. Contact Admin";
			}
			else
			{
			String display = "ActivityParticipation Request: is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  apm;
		
		
	}
	
	
	@RequestMapping(value="/getParticiapntDetailsByParticipantRequestId",method= RequestMethod.GET)
	public List<ActivityParticipateMember> getParticiapntDetailsByParticipantRequestId(@RequestParam String particiaptionRequestId) {
		
		List<ActivityParticipateMember> al=null;
		try
		{
		
		 
		 al=apms.getActivityParticipantDetailsById(Integer.parseInt(particiaptionRequestId));
		
		}
		catch(Exception e)
		{
			System.out.println("Exception  e"+e);
		}
		
		return al;
		
	}
	
	
	@RequestMapping(value="/getStudentPersonalDetails",method= RequestMethod.GET)
	public List<StudentPersonalIForm>getStudentPersonalDetails(@RequestParam(value="userCodeArray") List<String> userCodeArray ,@RequestParam String participationId)
	{

		List<StudentPersonalIForm> al=new ArrayList<StudentPersonalIForm>();
		userCodeArray.forEach((temp) -> {
            System.out.println(temp);
        });
		al=apms.getStudentPersonalDetails(userCodeArray,participationId);
		
		return al;
	}
	
	//get particiapnt other expenses details of particiapnt
	@RequestMapping(value="/getParticiapntOtherExpensesDetailsByParticipantRequestId",method= RequestMethod.GET)
	public ActivityParticipationOtherExpenses getParticiapntOtherExpensesDetailsByParticipantRequestId(@RequestParam String participantRequestId)
	{
		ActivityParticipationOtherExpenses ape=null;
		try
		{
			ape=apms.getParticiapntOtherExpensesDetailsByParticipantRequestId(participantRequestId);
		}
		catch(Exception e)
		{
			System.out.println("exception in ActivityParticipationOtherExpenses getting"+e);
		}
		return ape;
	}
	
	
	
	@RequestMapping(value="/editActivityParticipantOnwardJourneyDetails",method= RequestMethod.POST)
	public String  editActivityParticipantOnwardJourneyDetails(@RequestBody ActivtyMemberOnwardTravelDetailsForm acacttype,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivtyMemberOnwardTravelDetailsForm apm = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		   
			System.out.println("entrrrrrrrrrrrrrrrr in the editActivityParticipantOnwardJourneyDetails");
			 apm=apms.updateMemberOnwardJourneyDetails(acacttype);
			if(apm == null)
			{
				
				msg="Error in editing activity. Contact Admin";
			}
			else
			{
			String display = "ActivityParticipant Onward Journey Details is succesafully edited";
				msg="Edited-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	//editing particiapnt return journey details
	
	@RequestMapping(value="/editActivityParticipantReturnJourneyDetails",method= RequestMethod.POST)
	public String  editActivityParticipantReturnJourneyDetails(@RequestBody ActivtyMemberReturnTravelDetailsForm acacttype,@RequestHeader MultiValueMap<String, String> headers) {
		
		ActivtyMemberReturnTravelDetailsForm apm = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		   
			System.out.println("entrrrrrrrrrrrrrrrr in the editActivityParticipantReturndJourneyDetails");
			 apm=apms.updateMemberReturnJourneyDetails(acacttype);
			if(apm == null)
			{
				
				msg="Error in editing activity participant Journey details. Contact Admin";
			}
			else
			{
			String display = "ActivityParticipant Return Journey Details is succesafully edited";
				msg="Edited-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at editActivityParticipantReturnJourneyDetails Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	//editing particiapnt return journey details
	
		@RequestMapping(value="/editActivityAccomadationDetails",method= RequestMethod.POST)
		public String  editActivityAccomadationDetails(@RequestBody TeamParticipantAccomadationDetails acacttype,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityParticipateMember apm = null;
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
			   
				System.out.println("entrrrrrrrrrrrrrrrr in the editActivityParticipantReturndJourneyDetails");
				 apm=apms.updateActivityAccomadationDetails(acacttype);
				if(apm == null)
				{
					
					msg="Error in editing activity participant Accomadation Settlement details. Contact Admin";
				}
				else
				{
				String display = "Activity Participant Accomadation Settlement details is successfully edited";
					msg="Edited-"+display;
				}
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at editActivityParticipantReturnJourneyDetails Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		//get all rollnos of particiapant members
		@RequestMapping(value="/getAllParticipantDetailsByRequestId",method= RequestMethod.GET)
		public List<ActivityParticipateMember> getAllRollNosByActivityId(@RequestParam String particiaptionRequestId ) {
			
			List<ActivityParticipateMember> participateMembers=new ArrayList<ActivityParticipateMember>();
			
			int particiaptionRequestId1=Integer.parseInt(particiaptionRequestId);
			
			participateMembers=apms.getAllRollNosByActivityId(particiaptionRequestId1);
			return participateMembers;
			
			
		}
		
		
		
		//get all participated activities
		/*@RequestMapping(value="/getAllParticipatedActvities",method= RequestMethod.GET)
		public List<ActivityParticipateMember> getAllParticipatedActvities() {
			
			List<ActivityParticipateMember> participateMembers=new ArrayList<ActivityParticipateMember>();
			
			int activityId1=Integer.parseInt(activityId);
			
			ActivityParticipateMasterData participate=apms.getActivityParticipateRequestById(activityId1);
			return participateMembers;
			
			
		}*/
		
		
		
		
		@RequestMapping(value="/editActivityParticipantOtherExpenses",method= RequestMethod.POST)
		public String editActivityParticipantOtherExpenses(@RequestBody ActivityParticipationOtherExpenses amtd,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityParticipationOtherExpenses created = null;
			String msg ="";
			
			try
			{  
			
			//	System.out.println()
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				System.out.println(amtd.getBindingAmntAdmit()+amtd.getBindingAmntSpent());
				System.out.println("User : "+userCode);	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.editActivityParticipantOtherExpenses(amtd)	;	
		    if(created!=null)
		    {
		    	msg="success. Other Expenses are Updated";
		    }
		    else
		    {
		    	msg="saving of otherdetails is failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		//add particiapnt other details
		@RequestMapping(value="/addFacultyRecommedationDetails",method= RequestMethod.POST)
		public String addFacultyRecommedationDetails(@RequestBody Approvaldetails amtd,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityParticipateMasterData created = null;
			String msg ="";
			
			try
			{  
			
			   System.out.println("entrrrr i faulty recommendation");
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.addFacultyRecommedationDetails(amtd,userCode)	;	
		    if(created!=null)
		    {
		    	msg="Faculty recommended successfully";
		    }
		    else
		    {
		    	msg="faculty recommedation is failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		@RequestMapping(value="/addAdminstrativeDetails",method= RequestMethod.POST)
		public String addAdminstrativeDetails(@RequestBody Approvaldetails amtd,@RequestHeader MultiValueMap<String, String> headers) {
			ActivityParticipateMasterData created = null;
			String msg ="";
			try
			{  
			
			   System.out.println("entrrrr in addAdminstrativeDetails");
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.addAdminstrativeDetails(amtd,userCode)	;	
		           if(created!=null)
		             {
		             	msg="Administrative Approval successfully done";
		             }
		    else
		    {
		    	msg="Administrative Approval  failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		
		@RequestMapping(value="/getAllParticipantRequestFacultyRecom",method= RequestMethod.GET)
		public List<ActivityParticipateMasterData> getAllParticipantRequestFacultyRecom() {
			List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("recommended");
			return participate;
			
			
		}
		
		
		@RequestMapping(value="/getAllParticipantRequestAdministrativeApproved",method= RequestMethod.GET)
		public List<ActivityParticipateMasterData> getAllParticipantRequestAdministrativeApproved() {
			List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("adminstrativeapproved");
			return participate;
			
			
		}
		
		
		@RequestMapping(value="/addFinancialApprovalDetails",method= RequestMethod.POST)
		public String addFinancialApprovalDetails(@RequestBody Approvaldetails amtd,@RequestHeader MultiValueMap<String, String> headers) {
			ActivityParticipateMasterData created = null;
			String msg ="";
			try
			{  
			
			   System.out.println("entrrrr in addFinancialApprovalDetails");
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.addFinancialApprovalDetails(amtd,userCode)	;	
		           if(created!=null)
		             {
		             	msg="Financial Approval successfully done";
		             }
		    else
		    {
		    	msg="Financial Approval  failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		@RequestMapping(value="/getAllParticipantRequestFinanceApproved",method= RequestMethod.GET)
		public List<ActivityParticipateMasterData> getAllParticipantRequestFinanceApproved() {
			List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("financiallyapproved");
			return participate;
			
			
		}
		@RequestMapping(value="/addFinalApprovalDetails",method= RequestMethod.POST)
		public String addFinalApprovalDetails(@RequestBody Approvaldetails amtd,@RequestHeader MultiValueMap<String, String> headers) {
			ActivityParticipateMasterData created = null;
			String msg ="";
			try
			{  
			
			   System.out.println("entrrrr in addAdministrativeApprovalDetails");
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
				//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
				created=apms.addFinalApprovalDetails(amtd,userCode)	;	
		           if(created!=null)
		             {
		             	msg="Final Approval successfully done";
		             }
		    else
		    {
		    	msg="Final Approval  failed";
		    }
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		@RequestMapping(value="/getAllParticipantRequestFinalApproved",method= RequestMethod.GET)
		public List<ActivityParticipateMasterData> getAllParticipantRequestFinallyApproved(HttpServletRequest ht ) {
			
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("finallyapproved");
			return participate;
			
			
		}
		
		
		@RequestMapping(value="/getAllParticipantRequestForAdvance",method= RequestMethod.GET)
		public List<ActivityParticipateMasterData> getAllParticipantRequestForAdvance(HttpServletRequest ht ) {
			
			Gson gson = new Gson();
			String jsonObj=ht.getHeader("userauthdatastring");
			UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
			List<ActivityParticipateMasterData> participate=apms.getAllParticipantRequestForAdvance("finallyapproved",userAuthData.getUsercode());
			return participate;
			
			
		}
		
		
		//add particiapnt other details
				@RequestMapping(value="/addRemaks",method= RequestMethod.GET)
				public String addRemaks(@RequestParam String particiaptionRequestId,@RequestParam String remarks,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addRemaks(particiaptionRequestId,remarks,userAuthData.getUsercode())	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
		
				@RequestMapping(value="/getAllParticipantRequestFacultyRecomForAdminApproval",method= RequestMethod.GET)
				public List<ActivityParticipateMasterData> getAllParticipantRequestFacultyRecomForAdminApproval() {
					List<ActivityParticipateMasterData> participate=apms.getActivityParticipateRequestwithStatus("recommended");
					return participate;
					
					
				}
				@RequestMapping(value="/particiaptionExpenseSettlement")
				public ModelAndView particiaptionExpenseSettlement(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/ActivityParticiaptionSettlement");
					return mv;
				}
				


				
				@RequestMapping(value="/getAllParticipantRequestFinalApprovedByParticipant",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedByParticipant(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=apms.getAllParticipantRequestFinalApprovedByParticipant("finallyapproved",userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/facultyRecomParticiaptionExpenseSettlement")
				public ModelAndView facultyRecomParticiaptionExpenseSettlement(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/ParticiaptionExpenseFacultyRecommendation");
					return mv;
				}
				
				@RequestMapping(value="/AdminApprovalParticiaptionExpenseSettlement")
				public ModelAndView AdminApprovalParticiaptionExpenseSettlement(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/AdministrativeApprovalParticiaptionSettlement");
					return mv;
				}
				
				@RequestMapping(value="/financialApprovalParticiaptionExpenseSettlement")
				public ModelAndView financialApprovalParticiaptionExpenseSettlement(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/FinacialApprovalParticiaptionSettlement");
					return mv;
				}
				
				@RequestMapping(value="/finalApprovalParticiaptionExpenseSettlement")
				public ModelAndView finalApprovalParticiaptionExpenseSettlement(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/FinalActivityParticiaptionSettlement");
					return mv;
				}
				
				
				//add particiapnt other details
				@RequestMapping(value="/addSettlementRemaks",method= RequestMethod.GET)
				public String addSettlementRemaks(@RequestParam String particiaptionRequestId,@RequestParam String remarks,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addSettlementRemaks(particiaptionRequestId,remarks,userAuthData.getUsercode())	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=apms.getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom(userAuthData.getUsercode());
					return participate;
					
					
				}
				//add particiapnt other details
				@RequestMapping(value="/addSettlementFacultyRecom",method= RequestMethod.GET)
				public String addSettlementFacultyRecom(@RequestParam String particiaptionRequestId,@RequestParam String remarks, @RequestParam String status,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addSettlementFacultyRemaks(particiaptionRequestId,remarks,userAuthData.getUsercode(),status)	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantSettlementForAdministrativeApproved",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForAdministrativeApproved(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=apms.getAllParticipantSettlementForAdministrativeApproved();
					return participate;
					
					
				}
				
				
				//add particiapnt other details
				@RequestMapping(value="/addSettlementFinancialRecom",method= RequestMethod.GET)
				public String addSettlementFinancialRecom(@RequestParam String particiaptionRequestId,@RequestParam String remarks, @RequestParam String status,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addSettlementFinancialRecom(particiaptionRequestId,remarks,userAuthData.getUsercode(),status)	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				
				@RequestMapping(value="/addSettlementFinalRecom",method= RequestMethod.GET)
				public String addSettlementFinalRecom(@RequestParam String particiaptionRequestId,@RequestParam String remarks, @RequestParam String status,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addSettlementFinalRecom(particiaptionRequestId,remarks,userAuthData.getUsercode(),status)	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				@RequestMapping(value="/addSettlementAdministrativeRecom",method= RequestMethod.GET)
				public String addSettlementAdministrativeRecom(@RequestParam String particiaptionRequestId,@RequestParam String remarks, @RequestParam String status,HttpServletRequest ht) {
					
					ActivityParticipateMasterData created = null;
					String msg ="";
					
					try
					{  
					
					   System.out.println("entrrrr i faulty recommendation");
						Gson gson = new Gson();
						String jsonObj=ht.getHeader("userauthdatastring");
						UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
						
						//List<ActivtyMemberOnwardTravelDetailsForm> created=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
						created=apms.addSettlementAdministrativeRecom(particiaptionRequestId,remarks,userAuthData.getUsercode(),status)	;	
				    if(created!=null)
				    {
				    	msg="Remarks added successfully";
				    }
				    else
				    {
				    	msg="Error in adding remarks";
				    }
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantSettlementForFinancialApproved",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForFinancialApproved(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=apms.getAllParticipantSettlementForFinancialApproved();
					return participate;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantSettlementForFinalApproved",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantSettlementForFinalApproved(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=apms.getAllParticipantSettlementForFinalApproved();
					return participate;
					
					
				}

				@RequestMapping(value="/studentWiseparticipationReport")
				public ModelAndView studentWiseparticipationReport(ModelAndView mv) {	
					System.out.println("entrrr in getAsGeerationForm");
					mv.setViewName("ACTIVITYPARTICIPATION/StudentWiseReport");
					return mv;
				}
				@RequestMapping(value="/getStudentWiseReport",method= RequestMethod.GET)
				List<StudentWiseReportInterface> getStudentWiseReport(@RequestParam String rollNo)
				{
					
					List<StudentWiseReportInterface> participate=aar.getStudentWiseReport(rollNo);
					return participate;
				}
				
				@RequestMapping(value="/findParticipantRequestByRequestIdForAchievement",method= RequestMethod.GET)
				AchievmentPartDetails findParticipantRequestByRequestIdForAchievement(@RequestParam String prId)
				{
					int prtrqId=Integer.parseInt(prId);
					AchievmentPartDetails participate=adr.findParticipantRequestByRequestIdForAchievement(prtrqId);
					return participate;
				}
				
				
				@RequestMapping(value="/getAllParticipantRequestByParticipant",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestByParticipant(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantRequestByParticipant("submitted",userAuthData.getUsercode());
					return participate;
					
					
				}
			
				@RequestMapping(value="/getAllParticipantRequestByParticipantfinallyapproved",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestByParticipantfinallyapproved(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantRequestByParticipant("finallyapproved",userAuthData.getUsercode());
					return participate;
					
					
				}
				
				
				@RequestMapping(value="/getAllParticipantRequestRecommendedBy",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestRecommendedBy(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantRequestByEnteredBy(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantAdministrativeApprovedBy",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantAdministrativeApprovedBy(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantAdministrativeApprovedBy(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantAdministrativeApprovedByStatus",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantAdministrativeApprovedByStatus(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantAdministrativeApprovedByStatus(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/getAllParticipantFinancialApprovedByStatus",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinancialApprovedByStatus(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantFinanciallyApprovedByStatus(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				
				
				
				@RequestMapping(value="/getAllParticipantFinanciallyApprovedBy",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinanciallyApprovedBy(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantFinanciallyApprovedBy(userAuthData.getUsercode());
					return participate;
					
					
				}
				@RequestMapping(value="/getAllParticipantFinallyApprovedBy",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinallyApprovedBy(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantFinallyApprovedBy(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/participationAsOrder")
				public ModelAndView participationAsOrder(ModelAndView mv) {	
					System.out.println("entrrr in getAsGeerationForm");
					mv.setViewName("ACTIVITYPARTICIPATION/ParticipationAsGeneration");
					return mv;
				}
				
				@RequestMapping(value="/getAllParticipantRequestFinalApprovedForas",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantRequestFinalApprovedForas(HttpServletRequest ht ) {
					
					
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantRequestFinalApproved("finallyapproved");
					return participate;
					
					
				}
				
				
				@RequestMapping(value="/participationasOrderGeneration",method= RequestMethod.POST)
				public ParticipationAsOrder OrderGenaration(@RequestBody ParticipationAsOrder af,@RequestHeader MultiValueMap<String, String> headers) {
					ParticipationAsOrder saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = ams.asOrderGenaration(af,userCode);
						 

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
				@RequestMapping(value="/saveParticipationActivityBudgetFund",method= RequestMethod.POST)
				public String saveParticipationActivityBudgetFund(@RequestBody ActivityBudgetFund cfw,@RequestHeader MultiValueMap<String, String> headers) {
					
					ActivityBudgetFund saved = null;
					String msg ="";
					
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						System.out.println("User : "+userCode);	
					
						saved = aas.saveBudgetFund(cfw,userCode);
						System.out.println(saved);
						
						
						if(saved == null)
						{
							
							msg="NOTSAVED-"+"Error in adding Budget Fund Details against activity  ";
							
							
							
						}
						else
						{
						String display = "Budget Fund Mapping against an activity is successfully saved";
							msg="SAVED-"+display;
						}
						
						
					}
					catch(Exception ex)
					{
						System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
					}
					
					return  msg;
					
					
				}
				
				
				@RequestMapping(value="/getAllParticipationSettlementDetailsforEs",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipationSettlementDetailsforEs(HttpServletRequest ht ) {
					
					
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantRequestFinallySettled("finallyapproved");
					return participate;
					
					
				}	
				
				@RequestMapping(value="/participationEsOrder")
				public ModelAndView participationEsOrder(ModelAndView mv) {	
					System.out.println("entrrr in getAsGeerationForm");
					mv.setViewName("ACTIVITYPARTICIPATION/participationEsGeneration");
					return mv;
				}
				
				@RequestMapping(value="/participationEsOrderGeneration",method= RequestMethod.POST)
				public ParticipationEsGeneration participationEsOrderGeneration(@RequestBody ParticipationEsGeneration af,@RequestHeader MultiValueMap<String, String> headers) {
					ParticipationEsGeneration saved=null;
					 String msg=null;
					try
					{
						UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
						String userCode = user.getUsercode();
						 saved = ams.esOrderGenaration(af,userCode);
						
					}
					catch(Exception e)
					{
						
					}
					
					return saved;
					
					
				}
				//add advance request Details
				@RequestMapping(value="/getEsOrderGenerationParticipation",method= RequestMethod.GET)
				public ParticipationEsGeneration getEsOrderGenerationParticipation(@RequestParam String orderGenId) {
					ParticipationEsGeneration saved=null;
					
					try
					{
						
						saved=per.getEsOrderGenerationInformation(Integer.parseInt(orderGenId));
					}
					catch(Exception e)
					{
						
					}
					return saved;
				}	
				@RequestMapping(value="/getAllEsOrderGenerationInformation",method= RequestMethod.GET)
				 List<ParticipationEsGeneration>getAllEsOrderGenerationInformation(){
				
					 List<ParticipationEsGeneration>participate=per.getAllEsOrderGenerationInformation();
					return participate;
					
					
				}	
				@RequestMapping(value="/getAllAsOrderGenerationInformationByOrderId",method= RequestMethod.GET)
				 ParticipationAsOrder getAllAsOrderGenerationInformationByOrderId(@RequestParam String orderGenId){
				
					ParticipationAsOrder participate=par.getAllAsOrderGenerationInformationByOrderId(Integer.parseInt(orderGenId));
					return participate;
					
					
				}	
				@RequestMapping(value="/getAllAsOrderGenerationInformation",method= RequestMethod.GET)
				 List<ParticipationAsOrder>getAllAsOrderGenerationInformation(){
				
					 List<ParticipationAsOrder>participate=par.getAllAsOrderGenerationInformation();
					return participate;
					
					
				}	
				
				@RequestMapping(value="/getAllParticipantRequesForFacultyRecom",method= RequestMethod.GET)
				 List<FinallyApprovedParticipationDetailsByUser>getAllParticipantRequesForFacultyRecom(HttpServletRequest ht){
				
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					
					
					
					 List<FinallyApprovedParticipationDetailsByUser>participate=adr.getAllParticipantRequesForFacultyRecom(userAuthData.getUsercode());
					 
					return participate;
					
					
				}	
				@RequestMapping(value="/activityParticipationRequestEdit")
				public ModelAndView activityParticipationRequestEdit(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/ActivityParticipationEdit");
					return mv;
				}
				@RequestMapping(value="/activityParticipationFacultyEdit")
				public ModelAndView activityParticipationFacultyEdit(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/FacultyRecommendation.PrEdit");
					return mv;
				}
				
				@RequestMapping(value="/activityParticipationAdminEdit")
				public ModelAndView activityParticipationAdminEdit(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/adminpredit");
					return mv;
				}
				@RequestMapping(value="/activityParticipationFinancialEdit")
				public ModelAndView activityParticipationFinancialEdit(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/financialapprovaledit");
					return mv;
				}
				
				@RequestMapping(value="/activityParticipationFinalEdit")
				public ModelAndView activityParticipationFinalEdit(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/FinalApprovalPrEdit");
					return mv;
				}
			
				@RequestMapping(value="/getAllParticipantFinallyApprovedByForEdit",method= RequestMethod.GET)
				public List<FinallyApprovedParticipationDetailsByUser> getAllParticipantFinallyApprovedByForEdit(HttpServletRequest ht ) {
					
					Gson gson = new Gson();
					String jsonObj=ht.getHeader("userauthdatastring");
					UserauthObject userAuthData = gson.fromJson(jsonObj, UserauthObject.class);
					List<FinallyApprovedParticipationDetailsByUser> participate=adr.getAllParticipantFinallyApprovedByForEdit(userAuthData.getUsercode());
					return participate;
					
					
				}
				
				@RequestMapping(value="/activityParticipationAdministrativeOffice")
				public ModelAndView activityParticipationAdministrativeOffice(ModelAndView mv) {	
					System.out.println("entrrr in activityParticipationRequestViewL1Check");
					mv.setViewName("ACTIVITYPARTICIPATION/AdministrativeOfficeApprovePr");
					return mv;
				}
			
				
				
				
				
				
}
