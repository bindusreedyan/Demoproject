package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityAdvEstimate;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityAdvEstimateWrapper;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityAdvanceEstimateRequestWrapper;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMasterRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;

@Service
public class ActivityParticipateMemberService {
	@Autowired
	ActivityParticipantRep aar;
	@Autowired
	ActivityMasterRepository amr;
	@Autowired
	ActivityParticipationOtherExpensesRepository othex;
	@Autowired
	ActiveMasterDataRepository amd;
	
	@Autowired
	ActivityParticipationOtherExpensesRepository apoer;
	@Autowired ActiveMasterDataRepository amdr;
	public ActivityParticipateMember saveActivityMemberDetails(ActivityMemberFormFields apm,String usercode)
	{
		ActivityParticipateMember saved = null;
		
		
		
		try
		{
			
			ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
		    ActivityMaster am=amr.findActivityByActivityCode(apm.getActivityCode());
		    
		     ActivityParticipateMasterData apmd=amdr.findParticipantRequestByRequestId(apm.getParticipateRequestId());
			 apr.setAc(am);
			 apr.setRollNo(apm.getRollno());
			// apr.setpR(apmd);
			
			 ActivityParticipateMember tobesaved=new ActivityParticipateMember();
			 tobesaved.setActivityParticptnRequest(apr);
			 tobesaved.setContactNumber(apm.getContactNo());
			 tobesaved.setpR(apmd);
			 tobesaved.setUserCode(apm.getUserCode());
		     saved=aar.save(tobesaved);
			
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public List<TeamActivityParticipateDetails> getActivityParticipantDetails()
	{
		
		
		List<TeamActivityParticipateDetails> participate=amdr.getAllAcitivityParticipantDetails();
		return participate;
		
	}

	public List<TeamActivityParticipateDetails>getActivityParticipantDetailsByRollno(String rollno)
	{
		
		
		List<TeamActivityParticipateDetails> participate=amdr.getAllAcitivityParticipantDetailsByRollno(rollno);
		return participate;
		
	}
	
	public List<TeamActivityParticipateDetailsByStudent> getStudentActivitesBetweenDates(String studentAdmissionCode,String fromDate,String toDate)
	{
		List<TeamActivityParticipateDetailsByStudent> participate=null;
		try
		{
			java.sql.Date startDate=java.sql.Date.valueOf(fromDate);
		//DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
		//java.util.Date startDate1 = (Date)formatter.parse(fromDate);
		//java.sql.Date startDate = new java.sql.Date(startDate1.getTime());
	
			java.sql.Date EndDate=java.sql.Date.valueOf(toDate);
		
			
		String rollNo=amdr.getRollNoAgainstStudentAdmissionCode(studentAdmissionCode);
		
		participate = aar.getStudentActivitesBetweenDates(studentAdmissionCode,startDate,EndDate);
		
		System.out.println("Res of  activity : "+participate.toString());
		
		 //String res= aar.getStudentActivitesBetweenDates(rollNo,startDate,EndDate);
		 
		 //System.out.println("Res of  activity : "+res);
		 
		return participate;
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return participate;
		
	}
	
	//add member onward journey detials
	public List<ActivtyMemberOnwardTravelDetailsForm> addActivityOnwardJourneyDetails(ParticipantJourneyTravelRequestWrapper participateJourneyTravedetails)
	{
		

		
		List<ActivtyMemberOnwardTravelDetailsForm> al=new ArrayList<ActivtyMemberOnwardTravelDetailsForm>();
		
		for(ActivtyMemberOnwardTravelDetailsForm fm:participateJourneyTravedetails.getParticipantJouneryTravelWrapper())
		{
			
			 ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
			 ActivityParticipateMasterData apmd=amdr.findParticipantRequestByRequestId(fm.getParticipateRequestId());
			 ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
			 apr.setAc(am);
			 apr.setRollNo(fm.getRollNo());
			// apr.setpR(apmd);
			//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
			  ActivityParticipateMember  apm=aar.findById(apr).orElse(null);
			  System.out.println(apm.getActivityParticptnRequest().getRollNo()+"Startpoint"+fm.getOnwardStartPoint()+"llllll"+fm.getOnwardStartDate()+"jjjjjj"+fm.getOnwardDestination()+"kkkkk"+fm.getOnwardEndDate()+"jjjjj"+fm.getOnWardEstExpenditure()+"kkkk"+fm.getOnWardEstExpenditure());				
			  apm.setOnwardStartPoint(fm.getOnwardStartPoint());
			  apm.setOnwardStartDate(fm.getOnwardStartDate());
			  apm.setOnwardDestination(fm.getOnwardDestination());
			  apm.setOnwardEndDate(fm.getOnwardEndDate());
			  apm.setOnWardEstExpenditure(fm.getOnWardEstExpenditure());
			  apm.setOnwardModeofTravel(fm.getOnwardModeofTravel());
			  apm.setOnwardAmountAdmitted(fm.getOnwardAmountAdmitted());
			  aar.save(apm);
			  al.add(fm);
			
			
		}
		   return al;
	}
	

	
	
	
	//saving return journey details
	public List<ActivtyMemberReturnTravelDetailsForm> addActivityReturnJourneyDetails(ParticipantJourneyReturnTravelRequestWrapper participateJourneyTravedetails)
	{
		

		
		List<ActivtyMemberReturnTravelDetailsForm> al=new ArrayList<ActivtyMemberReturnTravelDetailsForm>();
		
		for(ActivtyMemberReturnTravelDetailsForm fm:participateJourneyTravedetails.getParticipantJouneryReturnTravelWrapper())
		{
			
			ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
			ActivityParticipateMasterData apmd=amdr.findParticipantRequestByRequestId(fm.getParticipateRequestId());
			ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
			   
			 apr.setAc(am);
			 apr.setRollNo(fm.getRollNo());
			// apr.setpR(apmd);
			//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
			 
			 
			 ActivityParticipateMember  apm=aar.findById(apr).orElse(null);
		
			 
			 
				
				
			apm.setReturnStartPoint(fm.getReturnStarPoint());
			apm.setReturnStartDate(fm.getReturnStartDate());
			apm.setReturnDestination(fm.getReturnDestination());
			apm.setReturnEndDate(fm.getReturnEnddate());
			apm.setReturnEstExpenditure(fm.getReturnEstimExp());
			apm.setReturnAmountAdmitted(fm.getReturnAmntAdmt());
			apm.setReturnModeofTravel(fm.getReturnModeofTravel());
			
			aar.save(apm);
			
			
		}
		   return al;
	}
	
	
	
	
	
	
	
	//add accomadationDetails
	
	public List<TeamParticipantAccomadationDetails> AddTeamParticipantAccomadationDetails(ParticipantJourneyAccomadationRequestWrapper participateJourneyTravedetails)
	{
		System.out.println("entrrrrrrrrrrrrrrr in AddTeamParticipantAccomadationDetails");
		
		List<TeamParticipantAccomadationDetails> al=new ArrayList<TeamParticipantAccomadationDetails>();
		
		for(TeamParticipantAccomadationDetails fm:participateJourneyTravedetails.getParticipantAccomadationWrapper())
		{
			System.out.println(fm.getActivityCode());
			ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
			ActivityParticipateMasterData apmd=amdr.findParticipantRequestByRequestId(fm.getParticipateRequestId());
			ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
			   
			 apr.setAc(am);
			 apr.setRollNo(fm.getRollNo());
			// apr.setpR(apmd);
			//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
			 ActivityParticipateMember  apm=aar.findById(apr).orElse(null);
			 
			 System.out.println("apmmmmmmmmmmmm"+apm.getActivityParticptnRequest().getRollNo());
		System.out.println(fm.getExpendtureDetails()+"ooooooooooooooooo"+fm.getAccomStartDate()+"oooooooooooooooo"+fm.getAccomEndDate()+"000000000000"+fm.getBillNo()+"000000000000000000"+fm.getNoDays()+"ooooooooooo"+fm.getDailyRate()+"000000000000000"+fm.getEstimExp());
	apm.setAccomodationExpHead(fm.getExpendtureDetails());
	apm.setAccmdationStartDate(fm.getAccomStartDate());		
	apm.setAccmdationEndDate(fm.getAccomEndDate());
	apm.setBillNo(fm.getBillNo());
	apm.setNoOfDays(fm.getNoDays());
	apm.setDailyRate(fm.getDailyRate());
	apm.setAccmdtionEstimExpenditure(fm.getEstimExp());
	apm.setFreeOfCostAccomadation(fm.getFreeOfCostAccomadation());
	aar.save(apm);
			
			
		}
		   return al;
	}


	public ActivityParticipationOtherExpenses addActivityParticipantOtherExpenses(ActivityParticipationOtherExpenses apm)
	{
		ActivityParticipationOtherExpenses saved = null;
		
		
		try
		{
			saved=othex.save(apm);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return saved;
		
	}
	public ActivityParticipationOtherExpenses ActivityBankDetailsEdit(ActivityParticipationOtherExpenses apm)
	{
		ActivityParticipationOtherExpenses saved = null;
		
		
		try
		{
			
			ActivityParticipationOtherExpenses am=othex.findById(apm.getParticipationRequestId()).orElse(null);
			
			if(am!=null)
			{
			System.out.println("entrrrrrr in other expense"+am.getParticipationRequestId());
			//payment fields
			
			am.setModeOfPayment(apm.getModeOfPayment());
			am.setAuthRecName(apm.getAuthRecName());
			am.setBankName(apm.getBankName());
			am.setAccNo(apm.getAccNo());
			am.setIfsc(apm.getIfsc());
			am.setBranch(apm.getBranch());
			saved=othex.save(am);
			
			}
			
			else
			{
			
		 am=new ActivityParticipationOtherExpenses();
			am.setModeOfPayment(apm.getModeOfPayment());
			am.setAuthRecName(apm.getAuthRecName());
			am.setBankName(apm.getBankName());
			am.setAccNo(apm.getAccNo());
			am.setIfsc(apm.getIfsc());
			am.setBranch(apm.getBranch());
			saved=othex.save(am);
			}
			
			
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return saved;
		
	}
	
	public ActivityParticipationOtherExpenses ActivityParticipantOtherExpensesEdit(ActivityParticipationOtherExpenses apm)
	{
		
		
		ActivityParticipationOtherExpenses saved = null;
		ActivityParticipationOtherExpenses saved1 = null;
		
		try
		{
			saved=othex.findById(apm.getParticipationRequestId()).orElse(null);
			if(saved!=null)
			{
			
			saved.setRegFeeSpent(apm.getRegFeeSpent());
			saved.setRegFeeEstimExp(apm.getRegFeeEstimExp());
			saved.setRegFeeAdmitted(apm.getRegFeeAdmitted());
			
			System.out.println("nu1@nu.com apm.getRegFeeClaimed()"+apm.getRegFeeClaimed());
			saved.setRegFeeClaimed(apm.getRegFeeClaimed());
			
			saved.setPrintoutsMemPages(apm.getPrintoutsMemPages());
			saved.setPrintoutMemSpent(apm.getPrintoutMemSpent());
			saved.setPrintoutMemAmtEst(apm.getPrintoutMemAmtEst());
			saved.setPrintoutMemAdmitted(apm.getPrintoutMemAdmitted());
			System.out.println("printpoutmem"+apm.getPrintoutMemBillNo());
			saved.setPrintoutMemBillNo(apm.getPrintoutMemBillNo());
			saved.setPrintoutMemClaimed(apm.getPrintoutMemClaimed());
			
			
			saved.setPhotocopyMemPages(apm.getPhotocopyMemPages());
			saved.setPhotocopyMemSpent(apm.getPhotocopyMemSpent());
			saved.setPhotocopyMemAmtEst(apm.getPhotocopyMemAmtEst());
			saved.setPhotocopyMemAdmitted(apm.getPhotocopyMemAdmitted());
			saved.setPhotocopyMemClaimed(apm.getPhotocopyMemClaimed());
			saved.setPhotocopyMemBillNo(apm.getPhotocopyMemBillNo());
			
			
			saved.setBindingAmntSpent(apm.getBindingAmntSpent());
			saved.setBindingMemAmtEst(apm.getBindingMemAmtEst());
			saved.setBindingAmntAdmit(apm.getBindingAmntAdmit());

			saved.setBindingAmntClaimed(apm.getBindingAmntClaimed());
			saved.setBindingAmntBillNo(apm.getBindingAmntBillNo());
			
			
			saved.setCostMemPages(apm.getCostMemPages());
			saved.setCostMemSpent(apm.getCostMemSpent());
			saved.setCostMemAmtEst(apm.getCostMemAmtEst());
			saved.setCostemAdmitted(apm.getCostemAdmitted());
			saved.setCostMemClaimed(apm.getCostMemClaimed());
			saved.setCostMemBillNo(apm.getCostMemBillNo());
			
			saved.setPrintoutsComPages(apm.getPrintoutsComPages());
			saved.setPrintoutComSpent(apm.getPrintoutComSpent());
			saved.setPrintoutComAmtEst(apm.getPrintoutComAmtEst());
			saved.setPrintoutComAdmitted(apm.getPrintoutComAdmitted());
			saved.setPrintoutComBillNo(apm.getPrintoutComBillNo());
			saved.setPrintoutComClaimed(apm.getPrintoutComClaimed());
			

			saved.setPhotocopyComPages(apm.getPhotocopyComPages());
			saved.setPhotocopyComSpent(apm.getPhotocopyComSpent());
			saved.setPhotocopyComAdmitted(apm.getPhotocopyComAdmitted());
			saved.setPhotocopyComClaimed(apm.getPhotocopyComClaimed());
			saved.setPhotocopyComBillNo(apm.getPhotocopyComBillNo());
			saved.setPhotocopyComAmtEst(apm.getPhotocopyComAmtEst());
			
			
			saved.setBindingComSpent(apm.getBindingComSpent());
			saved.setBindingComAmtEst(apm.getBindingComAmtEst());
			saved.setBindingComAdmitted(apm.getBindingComAdmitted());
			saved.setBindingComClaimed(apm.getBindingComClaimed());
			saved.setBindingComBillNo(apm.getBindingComBillNo());
			
		
			
		
			saved.setPaperNoCom(apm.getPaperNoCom());
			saved.setCostPaperComSpent(apm.getCostPaperComSpent());
			saved.setCostOfPaperComEstmExp(apm.getCostOfPaperComEstmExp());
			saved.setCostPaperComAdmitted(apm.getCostPaperComAdmitted());
			saved.setCostPaperComBillNo(apm.getCostPaperComBillNo());
			saved.setCostPaperComClaimed(apm.getCostPaperComClaimed());
			
			saved.setCourierChargesAmntSpent(apm.getCourierChargesAmntSpent());
			saved.setCourierChargesEstmExp(apm.getCourierChargesEstmExp());
			saved.setCourierChargesAdmitted(apm.getCourierChargesAdmitted());
			saved.setCourierChargesAmntClaimed(apm.getCourierChargesAmntClaimed());
			saved.setCourierChargesBillNo(apm.getCourierChargesBillNo());
			//saved.set
			
			saved.setTotalAdmitAmount(apm.getTotalAdmitAmount());
			saved.setTotalClaimedAmount(apm.getTotalClaimedAmount());
			saved.setTotalExpenseProgram(apm.getTotalExpenseProgram());
			saved1=othex.save(saved);
			
			
			}
			else
			{
			saved1=othex.save(apm);
			
			}
		
		
		
		}
		catch(Exception e)
		{
		
		
		}
		
		return saved1;
	}
	
	
	
	public ActivityParticipationOtherExpenses addActivityParticipantOtherDetails(ActivityParticipationOtherExpenses apm)
	{
		ActivityParticipationOtherExpenses saved = null;
		
		
		try
		{
			
			ActivityParticipationOtherExpenses am=othex.findById(apm.getParticipationRequestId()).orElse(null);
		//.out.println("entrrrrrr in other expense"+am.getParticipationRequestId()+am.getPriorAdministrativeSanction());
			am.setNoMemorialsPetitioner(apm.getNoMemorialsPetitioner());
			am.setNoMemorialsRespondent(apm.getNoMemorialsRespondent());
			am.setPagesPetitioner(apm.getPagesPetitioner());
			am.setPagesRespondent(apm.getPagesRespondent());
			am.setCompendiums(apm.getCompendiums());
			am.setPagesCompendium(apm.getPagesCompendium());
			am.setPriorAdministrativeSanction(apm.getPriorAdministrativeSanction());
			am.setReceivedRegFee(apm.getReceivedRegFee());
			am.setCompendiumLibrary(apm.getCompendiumLibrary());
			am.setCompendiumReceipt(apm.getCompendiumReceipt());
			am.setReasonOnNoSubmission(apm.getReasonOnNoSubmission());		
			othex.save(am);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return saved;
		
	}
	public ActivityParticipationOtherExpenses editActivityParticipantOtherDetails(ActivityParticipationOtherExpenses apm)
	{
		ActivityParticipationOtherExpenses saved = null;
		
		
		try
		{
			
			ActivityParticipationOtherExpenses am=othex.findById(apm.getParticipationRequestId()).orElse(null);
		//.out.println("entrrrrrr in other expense"+am.getParticipationRequestId()+am.getPriorAdministrativeSanction());
			
			if(am!=null)
			{
			am.setNoMemorialsPetitioner(apm.getNoMemorialsPetitioner());
			am.setNoMemorialsRespondent(apm.getNoMemorialsRespondent());
			am.setPagesPetitioner(apm.getPagesPetitioner());
			am.setPagesRespondent(apm.getPagesRespondent());
			am.setCompendiums(apm.getCompendiums());
			am.setPagesCompendium(apm.getPagesCompendium());
			am.setPriorAdministrativeSanction(apm.getPriorAdministrativeSanction());
			am.setReceivedRegFee(apm.getReceivedRegFee());
			am.setCompendiumLibrary(apm.getCompendiumLibrary());
			am.setCompendiumReceipt(apm.getCompendiumReceipt());
			am.setReasonOnNoSubmission(apm.getReasonOnNoSubmission());		
			othex.save(am);
			}
			
			
			else
			{
				
				
				
				try
				{
					saved=othex.save(apm);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
			
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return saved;
		
	}
	
	
	
	public ActivityParticipationOtherExpenses addActivityBankDetails(ActivityParticipationOtherExpenses apm)
	{
		ActivityParticipationOtherExpenses saved = null;
		
		
		try
		{
			
			ActivityParticipationOtherExpenses am=othex.findById(apm.getParticipationRequestId()).orElse(null);
			System.out.println("entrrrrrr in other expense"+am.getParticipationRequestId());
			//payment fields
			
			am.setModeOfPayment(apm.getModeOfPayment());
			am.setAuthRecName(apm.getAuthRecName());
			am.setBankName(apm.getBankName());
			am.setAccNo(apm.getAccNo());
			am.setIfsc(apm.getIfsc());
			am.setBranch(apm.getBranch());
			saved=othex.save(am);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return saved;
		
	}
	
	//get all participaterequest with status submitted
	
	public List<ActivityParticipateMasterData>getActivityParticipateRequestwithStatus(String status)
	{
		
		List<ActivityParticipateMasterData> participate=amdr.getAllAcitivityParticipantDetailsByStatus(status);
		return participate;
		
	}
	
	//get all participaterequest with status submitted
	
	public List<ActivityParticipateMasterData>getActivityParticipateRequestwithStatusnot(String status)
	{
		
		List<ActivityParticipateMasterData> participate=amdr.getActivityParticipateRequestwithStatusnot(status);
		return participate;
		
	}
	

	public ActivityParticipateMasterData getActivityParticipateRequestById(int participateRequestId)
	{
	
		ActivityParticipateMasterData participate=amd.findById(participateRequestId).orElse(null);
		return participate;
	
	}
	
	public List<ActivityParticipateMember> getActivityParticipantDetailsById(int partipateRequestId)
	{
		
		
		List<ActivityParticipateMember> participate=aar.findPartipantDetailsByRequestId(partipateRequestId);
		return participate;
		
	}
	
	public List<StudentPersonalIForm>getStudentPersonalDetails(List<String>userCodeArray,String participationId)
	{	
		List<StudentPersonalIForm> al=new ArrayList<StudentPersonalIForm>();
		System.out.println(al.size());
		
		return al=(List<StudentPersonalIForm>) aar.findStudentPersonalByUsercode(userCodeArray,Integer.parseInt(participationId));
		
	}
	public ActivityParticipationOtherExpenses getParticiapntOtherExpensesDetailsByParticipantRequestId(String particiapntRequestId)
	{
		ActivityParticipationOtherExpenses ape=null;
		try
		{
		ape=apoer.findByParticiapntOtherExpenseRecord(Integer.parseInt(particiapntRequestId));
		System.out.println(ape);
		
		}
		catch(Exception e)
		{
			System.out.println("exception in getParticiapntOtherExpensesDetailsByParticipantRequestId"+e);
		}
		return ape;
	}
	
	
	//update member onward journey detials
	public ActivtyMemberOnwardTravelDetailsForm updateMemberOnwardJourneyDetails(ActivtyMemberOnwardTravelDetailsForm fm)
	{
		
		
		     //ActivtyMemberOnwardTravelDetailsForm memberOnwardJourneyDetails=null;
		     try
		     {
			 ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
			
			 System.out.println("participat requestId"+fm.getParticipateRequestId());
			 
			 ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
			 apr.setAc(am);
			 apr.setRollNo(fm.getRollNo());
			// apr.setpR(apmd);
			//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
			  ActivityParticipateMember  apm=aar.findById(apr).orElse(null);  
			  if(apm!=null)
			  {
			  System.out.println(apm.getActivityParticptnRequest().getRollNo()+"acccccccccc"+apm.getActivityParticptnRequest().getAc().getActivityCode());
			  //System.out.println(apm.getActivityParticptnRequest().getRollNo()+"Startpoint"+fm.getOnwardStartPoint()+"llllll"+fm.getOnwardStartDate()+"jjjjjj"+fm.getOnwardDestination()+"kkkkk"+fm.getOnwardEndDate()+"jjjjj"+fm.getOnWardEstExpenditure()+"kkkk"+fm.getOnWardEstExpenditure());		
			  System.out.println(apm.getOnwardStartPoint());
			  System.out.println(fm.getOnwardDestination()+"rrrrrrrrr"+fm.getOnwardModeofTravel()+"jjj"+fm.getOnwardStartPoint());
			  System.out.println(fm.getOnWardEstExpenditure()+"rrrrrrrrr"+fm.getOnwardEndDate()+"jjj"+fm.getOnwardStartPoint());
			  
			  apm.setOnwardStartPoint(fm.getOnwardStartPoint());
			  apm.setOnwardStartDate(fm.getOnwardStartDate());
			  apm.setOnwardDestination(fm.getOnwardDestination());
			  apm.setOnwardEndDate(fm.getOnwardEndDate());
			  apm.setOnWardEstExpenditure(fm.getOnWardEstExpenditure());
			  apm.setOnwardModeofTravel(fm.getOnwardModeofTravel());
			  apm.setOnwardAmountAdmitted(fm.getOnwardAmountAdmitted());
			  apm.setOnwardAmountClaimed(fm.getOnwardAmountClaimed());
			//  apm.setOnwardAmountAdmitted(fm.getOnwardAmountAdmitted());
			  
			
			  
			  aar.save(apm);
			  }
			  else
			  {
				  fm=null;
			  }
		     }
		     catch(Exception e)
		     {
		    	 System.out.println("exception in update updatemember on ward journey details"+e);
		    	 fm=null;
		     }
		
			return fm;
			
	
		   
	}
	
	//updateMemberReturnJourneyDetails
		public ActivtyMemberReturnTravelDetailsForm updateMemberReturnJourneyDetails(ActivtyMemberReturnTravelDetailsForm fm)
		{
			
			
			     //ActivtyMemberOnwardTravelDetailsForm memberOnwardJourneyDetails=null;
			     try
			     {
				 ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
				
				 System.out.println("participat requestId"+fm.getParticipateRequestId());
				 
				 ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
				 apr.setAc(am);
				 apr.setRollNo(fm.getRollNo());
				// apr.setpR(apmd);
				//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
				  ActivityParticipateMember  apm=aar.findById(apr).orElse(null);  
				  if(apm!=null)
				  {
				 
				  
				  apm.setReturnStartPoint(fm.getReturnStarPoint());
				  apm.setReturnStartDate(fm.getReturnStartDate());
				  apm.setReturnDestination(fm.getReturnDestination());
				  apm.setReturnEndDate(fm.getReturnEnddate());
				  apm.setReturnEstExpenditure(fm.getReturnEstimExp());
				  apm.setReturnModeofTravel(fm.getReturnModeofTravel());
				  apm.setReturnAmountAdmitted(fm.getReturnAmntAdmt());
				  apm.setReturnAmountClaimed(fm.getReturnAmntClaimed());
				 
				 
				  aar.save(apm);
				  }
				  else
				  {
					  fm=null;
				  }
			     }
			     catch(Exception e)
			     {
			    	 System.out.println("exception in update updatemember on ward journey details"+e);
			    	 fm=null;
			     }
			
				return fm;
				
		
			   
		}
		
		//updateMemberReturnJourneyDetails
				public ActivityParticipateMember updateActivityAccomadationDetails(TeamParticipantAccomadationDetails fm)
				{
					
					ActivityParticipateMember apm1=null;
					try
				     {
					 ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
					
					 System.out.println("participat requestId"+fm.getParticipateRequestId());
					 
					 ActivityMaster am=amr.findActivityByActivityCode(fm.getActivityCode());
					 apr.setAc(am);
					 apr.setRollNo(fm.getRollNo());
					 System.out.println("admitamnt"+fm.getAmntAdmt());
					 System.out.println("estim"+fm.getEstimExp());
					 System.out.println("claimed"+fm.getAmntClaimed());
					// apr.setpR(apmd);
					//ActivityParticipateMember  apm=aar.findByParticiapntRecord(fm.getRollNo(),fm.getParticipateRequestId());
					  ActivityParticipateMember  apm=aar.findById(apr).orElse(null);  
					  if(apm!=null)
					  {
					 
					  
					 apm.setAccmdationEndDate(fm.getAccomEndDate());
					 apm.setAccmdationStartDate(fm.getAccomStartDate());
					 apm.setAccmdtionEstimExpenditure(fm.getEstimExp());
					 apm.setAccomodationExpHead(fm.getExpendtureDetails());
					 apm.setBillNo(fm.getBillNo());
					 apm.setDailyRate(fm.getDailyRate());
					 apm.setFreeOfCostAccomadation(fm.getFreeOfCostAccomadation());
					 apm.setNoOfDays(fm.getNoDays());
					 
					 
					  apm.setAmountAdmitted(fm.getAmntAdmt());
					  apm.setAmountClaimed(fm.getAmntClaimed());
					 
					 apm1= aar.save(apm);
					  }
					  else
					  {
						  apm1=null;
					  }
				     }
				     catch(Exception e)
				     {
				    	 System.out.println("exception in update updatemember on ward journey details"+e);
				    	 fm=null;
				     }
				
					return apm1;
					
					
				}
					
				public List<ActivityParticipateMember> getAllRollNosByActivityId(int participateRequestId)
				{
					List<ActivityParticipateMember> participateMembers=new ArrayList<ActivityParticipateMember>();
					try
					{
				
					 participateMembers=aar.findPartipantDetailsByRequestId(participateRequestId);
				
					}
					catch(Exception e)
					{
						System.out.println("Exception  in getAllRollNosByActivityId"+e);
					}
					return participateMembers;
				
				}
				
				
				public ActivityParticipationOtherExpenses editActivityParticipantOtherExpenses(ActivityParticipationOtherExpenses apm)
				{
					ActivityParticipationOtherExpenses saved = null;
					ActivityParticipationOtherExpenses saved1 = null;
					
					try
					{
						
						saved=othex.findById(apm.getParticipationRequestId()).orElse(null);
						saved.setRegFeeSpent(apm.getRegFeeSpent());
						saved.setRegFeeEstimExp(apm.getRegFeeEstimExp());
						saved.setRegFeeAdmitted(apm.getRegFeeAdmitted());
						
						System.out.println("nu1@nu.com apm.getRegFeeClaimed()"+apm.getRegFeeClaimed());
						saved.setRegFeeClaimed(apm.getRegFeeClaimed());
						
						saved.setPrintoutsMemPages(apm.getPrintoutsMemPages());
						saved.setPrintoutMemSpent(apm.getPrintoutMemSpent());
						saved.setPrintoutMemAmtEst(apm.getPrintoutMemAmtEst());
						saved.setPrintoutMemAdmitted(apm.getPrintoutMemAdmitted());
						System.out.println("printpoutmem"+apm.getPrintoutMemBillNo());
						saved.setPrintoutMemBillNo(apm.getPrintoutMemBillNo());
						saved.setPrintoutMemClaimed(apm.getPrintoutMemClaimed());
						
						
						saved.setPhotocopyMemPages(apm.getPhotocopyMemPages());
						saved.setPhotocopyMemSpent(apm.getPhotocopyMemSpent());
						saved.setPhotocopyMemAmtEst(apm.getPhotocopyMemAmtEst());
						saved.setPhotocopyMemAdmitted(apm.getPhotocopyMemAdmitted());
						saved.setPhotocopyMemClaimed(apm.getPhotocopyMemClaimed());
						saved.setPhotocopyMemBillNo(apm.getPhotocopyMemBillNo());
						
						
						saved.setBindingAmntSpent(apm.getBindingAmntSpent());
						saved.setBindingMemAmtEst(apm.getBindingMemAmtEst());
						saved.setBindingAmntAdmit(apm.getBindingAmntAdmit());
			
						saved.setBindingAmntClaimed(apm.getBindingAmntClaimed());
						saved.setBindingAmntBillNo(apm.getBindingAmntBillNo());
						
						
						saved.setCostMemPages(apm.getCostMemPages());
						saved.setCostMemSpent(apm.getCostMemSpent());
						saved.setCostMemAmtEst(apm.getCostMemAmtEst());
						saved.setCostemAdmitted(apm.getCostemAdmitted());
						saved.setCostMemClaimed(apm.getCostMemClaimed());
						saved.setCostMemBillNo(apm.getCostMemBillNo());
						
						saved.setPrintoutsComPages(apm.getPrintoutsComPages());
						saved.setPrintoutComSpent(apm.getPrintoutComSpent());
						saved.setPrintoutComAmtEst(apm.getPrintoutComAmtEst());
						saved.setPrintoutComAdmitted(apm.getPrintoutComAdmitted());
						saved.setPrintoutComBillNo(apm.getPrintoutComBillNo());
						saved.setPrintoutComClaimed(apm.getPrintoutComClaimed());
						
	
						saved.setPhotocopyComPages(apm.getPhotocopyComPages());
						saved.setPhotocopyComSpent(apm.getPhotocopyComSpent());
						saved.setPhotocopyComAdmitted(apm.getPhotocopyComAdmitted());
						saved.setPhotocopyComClaimed(apm.getPhotocopyComClaimed());
						saved.setPhotocopyComBillNo(apm.getPhotocopyComBillNo());
						saved.setPhotocopyComAmtEst(apm.getPhotocopyComAmtEst());
						
						
						saved.setBindingComSpent(apm.getBindingComSpent());
						saved.setBindingComAmtEst(apm.getBindingComAmtEst());
						saved.setBindingComAdmitted(apm.getBindingComAdmitted());
						saved.setBindingComClaimed(apm.getBindingComClaimed());
						saved.setBindingComBillNo(apm.getBindingComBillNo());
						
					
						
					
						saved.setPaperNoCom(apm.getPaperNoCom());
						saved.setCostPaperComSpent(apm.getCostPaperComSpent());
						saved.setCostOfPaperComEstmExp(apm.getCostOfPaperComEstmExp());
						saved.setCostPaperComAdmitted(apm.getCostPaperComAdmitted());
						saved.setCostPaperComBillNo(apm.getCostPaperComBillNo());
						saved.setCostPaperComClaimed(apm.getCostPaperComClaimed());
						
						saved.setCourierChargesAmntSpent(apm.getCourierChargesAmntSpent());
						saved.setCourierChargesEstmExp(apm.getCourierChargesEstmExp());
						saved.setCourierChargesAdmitted(apm.getCourierChargesAdmitted());
						saved.setCourierChargesAmntClaimed(apm.getCourierChargesAmntClaimed());
						saved.setCourierChargesBillNo(apm.getCourierChargesBillNo());
						//saved.set
						
						saved.setTotalAdmitAmount(apm.getTotalAdmitAmount());
						saved.setTotalClaimedAmount(apm.getTotalClaimedAmount());
						saved.setTotalExpenseProgram(apm.getTotalExpenseProgram());
						saved1=othex.save(saved);
						
					}
					catch(Exception e)
					{
						System.out.println("Exception "+e);
					}
					return saved1;
					
				}
		
		
				public ActivityParticipateMasterData addFacultyRecommedationDetails(Approvaldetails apm,String userCode)
				{
					ActivityParticipateMasterData saved = null;
					
					
					try
					{
						System.out.println(apm.getParticipateRequestId()+apm.getFacultyRecomRemarks()+apm.getRecomStatus()+apm.getRecomDate());
						ActivityParticipateMasterData am=amd.findById(apm.getParticipateRequestId()).orElse(null);
						System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
						//payment fields
						long millis=System.currentTimeMillis();
						java.sql.Date date=new java.sql.Date(millis);
						am.setRecomDate(date);
						am.setRecommendedBy(userCode);
						am.setRecomRemarks(apm.getFacultyRecomRemarks());
						am.setParticipationRequestStatus(apm.getRecomStatus());
						saved=amd.save(am);
						
					}
					catch(Exception e)
					{
						System.out.println("Exception "+e);
					}
					return saved;
					
				}
	
				public ActivityParticipateMasterData addAdminstrativeDetails(Approvaldetails apm,String userCode)
				{
					ActivityParticipateMasterData saved = null;
					
					
					try
					{
						System.out.println(apm.getParticipateRequestId()+apm.getAdminstrativeStatus()+apm.getAdminstrativeApproveRemarks());
						ActivityParticipateMasterData am=amd.findById(apm.getParticipateRequestId()).orElse(null);
						System.out.println("entrrrrrr in addAdminstrativeDetails"+am.getParticiaptionRequestId());
						//payment fields
						long millis=System.currentTimeMillis();
						java.sql.Date date=new java.sql.Date(millis);
					   am.setAdminstrativeApproveRemarks(apm.getAdminstrativeApproveRemarks());
					   am.setAdminApprovedDate(date);
					   am.setAdministrativeApprovedBy(userCode);
					   am.setParticipationRequestStatus(apm.getAdminstrativeStatus());
				
						/*am.setRecomDate(apm.getRecomDate());
						am.setRecommendedBy(userCode);
						am.setRecomRemarks(apm.getFacultyRecomRemarks());
						am.setParticipationRequestStatus(apm.getRecomStatus());*/
						saved=amd.save(am);
						
					}
					catch(Exception e)
					{
						System.out.println("Exception "+e);
					}
					return saved;
					
				}
				
				public ActivityParticipateMasterData addFinancialApprovalDetails(Approvaldetails apm,String userCode)
				{
					ActivityParticipateMasterData saved = null;
					
					
					try
					{
						System.out.println(apm.getParticipateRequestId()+apm.getAdminstrativeStatus()+apm.getAdminstrativeApproveRemarks());
						ActivityParticipateMasterData am=amd.findById(apm.getParticipateRequestId()).orElse(null);
						System.out.println("entrrrrrr in addAdminstrativeDetails"+am.getParticiaptionRequestId());
						//payment fields
					
						
						long millis=System.currentTimeMillis();
						java.sql.Date date=new java.sql.Date(millis);
					   am.setFinancialApproveRemarks(apm.getFinancialApproveRemarks());
					   am.setFinancialApprovedDate(date);
					   am.setFinancialApprovedBy(userCode);
					   am.setParticipationRequestStatus(apm.getFinancialStatus());
				
						/*am.setRecomDate(apm.getRecomDate());
						am.setRecommendedBy(userCode);
						am.setRecomRemarks(apm.getFacultyRecomRemarks());
						am.setParticipationRequestStatus(apm.getRecomStatus());*/
						saved=amd.save(am);
						
					}
					catch(Exception e)
					{
						System.out.println("Exception "+e);
					}
					return saved;
					
				}
				public ActivityParticipateMasterData addFinalApprovalDetails(Approvaldetails apm,String userCode)
				{
					ActivityParticipateMasterData saved = null;
					
					
					try
					{
						System.out.println(apm.getParticipateRequestId()+apm.getAdminstrativeStatus()+apm.getAdminstrativeApproveRemarks());
						ActivityParticipateMasterData am=amd.findById(apm.getParticipateRequestId()).orElse(null);
						System.out.println("entrrrrrr in addAdminstrativeDetails"+am.getParticiaptionRequestId());
						//payment fields
						long millis=System.currentTimeMillis();
						java.sql.Date date=new java.sql.Date(millis);
					   am.setApprovedRemark(apm.getFinalApprovedRemark());
					   am.setApprovedDate(date);
					   am.setApprovedBy(userCode);
					   am.setParticipationRequestStatus(apm.getFinalApprovalStatus());
				
						/*am.setRecomDate(apm.getRecomDate());
						am.setRecommendedBy(userCode);
						am.setRecomRemarks(apm.getFacultyRecomRemarks());
						am.setParticipationRequestStatus(apm.getRecomStatus());*/
						saved=amd.save(am);
						
					}
					catch(Exception e)
					{
						System.out.println("Exception "+e);
					}
					return saved;
					
				}
				
				//get all participaterequest with status submitted
	
				
				
			public List<ActivityParticipateMasterData>getAllParticipantRequestForAdvance(String status,String userCode)
				{
					
					List<ActivityParticipateMasterData> participate=amdr.getAllParticipantRequestForAdvance(status,userCode);
					return participate;
					
				}
			public ActivityParticipateMasterData addSettlementRemaks(String participationReq,String remarks,String userCode)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in student remarks"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettlementUserRemark(remarks);
					am.setSettlementUserDate(new java.util.Date());
					am.setSettlementEnteredBy(userCode);
					am.setSettlementStatus("raised");
					saved=amd.save(am);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			public List<ActivityParticipateMasterData>getActivityParticipateRequestwithStatusForAdminApproval(String status)
			{
				
				List<ActivityParticipateMasterData> participate=amdr.getAllAcitivityParticipantDetailsByStatusForAdminApproval(status);
				return participate;
				
			}
			
			public List<FinallyApprovedParticipationDetailsByUser>getAllParticipantRequestFinalApprovedByParticipant(String status,String userCode)
			{
				
				List<FinallyApprovedParticipationDetailsByUser> participate=amdr.getAllParticipantRequestFinalApprovedByParticipant(status,userCode);
				return participate;
				
			}
				
			public ActivityParticipateMasterData addRemaks(String participationReq,String remarks,String userCode)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					
					java.util.Date d =new java.util.Date();
					java.sql.Date sqlDate = new java.sql.Date(d.getTime());
					am.setEnteredBy(userCode);
					am.setEnteredRemarks(remarks);
					am.setEnteredDate(sqlDate);
					
					saved=amd.save(am);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			public List<FinallyApprovedParticipationDetailsByUser>getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom(String userCode)
			{
				
				List<FinallyApprovedParticipationDetailsByUser> participate=amdr.getAllParticipantRequestFinalApprovedByParticipantForFacultyRecom(userCode);
				return participate;
				
			}
			
			public ActivityParticipateMasterData addSettlementFacultyRecom(String participationReq,String remarks,String userCode)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettleRecomRemark(remarks);
					am.setSettlRecomDate(new java.util.Date());
					am.setSettlRecomBy(userCode);
					am.setSettlementStatus("recommended");
					saved=amd.save(am);
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			public ActivityParticipateMasterData addSettlementFacultyRemaks(String participationReq,String remarks,String userCode,String status)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettleRecomRemark(remarks);
					am.setSettlRecomDate(new java.util.Date());
					am.setSettlRecomBy(userCode);
					am.setSettlementStatus(status);
					saved=amd.save(am);
					System.out.println("id"+saved.getParticiaptionRequestId()+"status");
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			
			public List<FinallyApprovedParticipationDetailsByUser>getAllParticipantSettlementForAdministrativeApproved()
			{
				
				List<FinallyApprovedParticipationDetailsByUser> participate=amdr.getAllParticipantSettlementForAdministrativeApproved();
				return participate;
				
			}
			
			public ActivityParticipateMasterData addSettlementAdministrativeRecom(String participationReq,String remarks,String userCode,String status)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettleAdministrativeRemark(remarks);
					am.setSettlAdministrativeDate(new java.util.Date());
					am.setSettlAdministrativeBy(userCode);
					am.setSettlementStatus(status);
					saved=amd.save(am);
					System.out.println("id"+saved.getParticiaptionRequestId()+"status");
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			public ActivityParticipateMasterData addSettlementFinalRecom(String participationReq,String remarks,String userCode,String status)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettleFinalRemark(remarks);
					am.setSettlFinalDate(new java.util.Date());
					am.setSettlFinalBy(userCode);
					am.setSettlementStatus(status);
					saved=amd.save(am);
					System.out.println("id"+saved.getParticiaptionRequestId()+"status");
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			public ActivityParticipateMasterData addSettlementFinancialRecom(String participationReq,String remarks,String userCode,String status)
			{
				ActivityParticipateMasterData saved = null;
				
				
				try
				{
					System.out.println(participationReq+remarks+userCode);
					ActivityParticipateMasterData am=amd.findById(Integer.parseInt(participationReq)).orElse(null);
					System.out.println("entrrrrrr in faculty recommedation"+am.getParticiaptionRequestId());
					//payment fields
					
					//am.setEnteredDate(new java.util.Date());
					am.setSettleFinancialRemark(remarks);
					am.setSettlFinancialDate(new java.util.Date());
					am.setSettlFinanacialBy(userCode);
					am.setSettlementStatus(status);
					saved=amd.save(am);
					System.out.println("id"+saved.getParticiaptionRequestId()+"status");
					
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
				return saved;
				
			}
			
			public List<FinallyApprovedParticipationDetailsByUser>getAllParticipantSettlementForFinancialApproved()
			{
				
				List<FinallyApprovedParticipationDetailsByUser> participate=amdr.getAllParticipantSettlementForFinancialApproved();
				return participate;
				
			}
			public List<FinallyApprovedParticipationDetailsByUser>getAllParticipantSettlementForFinalApproved()
			{
				
				List<FinallyApprovedParticipationDetailsByUser> participate=amdr.getAllParticipantSettlementForFinalApproved();
				return participate;
				
			}
			
}
