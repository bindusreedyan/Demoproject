package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityAdvEstimate;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ProgramApprovalAdvancePayment;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ProgramApprovalAdvancePaymentRep;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

import java.sql.Date;

@Service
public class ActivityAdReqService {
	
	
	@Autowired 
	ActivityAdvReqRep advRep;
	@Autowired
	ProgramApprovalAdvancePaymentRep paap;
	@Autowired
MasterDataTimeLineRepository mdtlr;
	public ActivityAdvReq saveActivityAdvanceRequestDetails(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		try
		{
			as.setEnteredBy(userCode);
		    long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			as.setEnteredDate(date);
			as.setUserCode(userCode);
			as.setRollNoCapacity(as.getRollNoCapacity());
			as.setAdvanceReqStatus("Raised");
			saved=advRep.save(as);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Advance Request Add by faculty");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getAdvReqId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return saved;
	}
	public ActivityAdvReq saveActivityAdvanceRequestDetailsByStudents(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		try
		{
			as.setEnteredBy(userCode);
		    long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			as.setEnteredDate(date);
			as.setUserCode(userCode);
			as.setRollNoCapacity(as.getRollNoCapacity());
			as.setAdvanceReqStatus("Raised");
			saved=advRep.save(as);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Advance Request Add by student");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getAdvReqId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return saved;
	}
	public double findTotalSanctionAmnt(int activityCode)
	{
		double totalSanction=0.0;
		try
		{
			totalSanction=advRep.findTotalSanctionAmntByActivityId(activityCode);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return totalSanction;
		
	}
	
	public List<ActivityAdvReq> getAllAdvanceRequestRaised(String  activityCode,String rollNo)
	{
		List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>(); 
	
	   try
		{
			al=advRep.findAdvReqByActivityCodeAndRollNo(Integer.parseInt(activityCode),rollNo);
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	
	public List<ActivityAdvReq> getAllAdvanceRequestRaised(String status)
	{
		List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>(); 
	
	   try
		{
			al=advRep.getAllAdvanceRequestRaised(status);
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	public ActivityAdvReq getAllAdvanceRequestByRequestId(String requestedId)
	{
		ActivityAdvReq al=null;
	
	   try
		{
			al=advRep.getAllAdvanceRequestByRequestId(Integer.parseInt(requestedId));
			
		}
		catch(Exception e)
		{
			System.out.println("Exception"+ e);
			
		}
		
		return al;
	}
	
	
	
	public ActivityAdvReq facultyRecomAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeRecommended=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setRecommendedRemarks(as.getRecommendedRemarks());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setRecommendDate(date);
              saved.setRecommendBy(userCode);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setaSNo(as.getaSNo());
			  toBeRecommended=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Students Advance Request Faculty Recommendation");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeRecommended.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeRecommended.toString());
				mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeRecommended;
	}
	
	public ActivityAdvReq adminOfficeAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeAdminApproved=null;
		try
		{
			System.out.println("adminoffice approved remark"+as.getAdminOfficeApprovedRemark());
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setAdminOfficeApprovedRemark(as.getAdminOfficeApprovedRemark());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setAdminOfficeApprovedBy(userCode);
              saved.setAdminOfficeApprovedDate(date);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBeAdminApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Faculty Advance Request Faculty Administrative office approval");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeAdminApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeAdminApproved.toString());
				mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeAdminApproved;
	}
	
	public ActivityAdvReq adminAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeAdminApproved=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setAdminApprovedRemarks(as.getAdminApprovedRemarks());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setAdminApprovedBy(userCode);
              saved.setAdminApprovedDate(date);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBeAdminApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Admin Approval for  Advance Request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeAdminApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeAdminApproved.toString());
				mdtlr.save(mdtl);
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeAdminApproved;
	}
	public ActivityAdvReq financeOfficeAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeAdminApproved=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setFinanceOfficeApprovedRemark(as.getFinanceOfficeApprovedRemark());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setFinanceOfficeApprovedBy(userCode);
              saved.setFinanceOfficeApprovedDate(date);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBeAdminApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Finance Office Approval for Advance Request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeAdminApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeAdminApproved.toString());
				mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeAdminApproved;
	}
	
	public ActivityAdvReq adminAdvanceRequestEdit(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeAdminApproved=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setAdminApprovedRemarks(as.getAdminApprovedRemarks());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setAdminApprovedBy(userCode);
              saved.setAdminApprovedDate(date);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBeAdminApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Edit Student Advance Request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeAdminApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeAdminApproved.toString());
				mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeAdminApproved;
	}
	
	public ActivityAdvReq AdvanceRequestFacultyEdit(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeAdminApproved=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
           //   saved.setAdminApprovedRemarks(as.getAdminApprovedRemarks());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
         saved.setEditedBy(userCode);
       saved.setEditedDate(date);
       saved.setEditedRemark(as.getEnteredRemarks());
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
		// saved.setAdvanceReqStatus("Raised");
			//  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBeAdminApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Edit Faculty Advance Request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeAdminApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeAdminApproved.toString());
				mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeAdminApproved;
	}
	
	
	
	public List<ActivityAdvReq>   findTotalRequestedAmnt(int activityCode,int participateRequestId,String userCode)
	{
		List<ActivityAdvReq>  arq=null;
		try
		{
			arq=advRep.findTotalrequestedAmntByActivityIdAndPrcRqId(activityCode,participateRequestId,userCode);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return arq;
		
	}
	public List<ActivityAdvReq> getAdvRequestNotSettledOrClosed(String rollNo)
	{
		 List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>();
		try
		{
			al=advRep.getAdvRequestNotSettledOrClosed(rollNo);
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public List<ActivityAdvReq> getAdvRequestNotSettledByFaculty(String userCode)
	{
		 List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>();
		try
		{
			al=advRep.getAdvRequestNotSettledByFaculty(userCode);
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public ActivityAdvReq financeAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBefinApproved=null;
		try
		{
			System.out.println("advreqqq"+as.getFinalAdvReq());
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
			  System.out.println(saved.getAdvReqId());
              saved.setCommentsFinance(as.getCommentsFinance());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              System.out.println(userCode);
              saved.setSanctionedBy(userCode);
              saved.setSanctionDate(date);
              saved.setaSNo(as.getaSNo());
			 //saved.setAdvRequiredFig(as.getAdvRequiredFig());
		     //  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			 // saved.setPurpose(as.getPurpose());
			  //saved.setFinalAdvReq(as.getFinalAdvReq());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBefinApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("finance approval for advance request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBefinApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBefinApproved.toString());
				mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBefinApproved;
	}
	public ActivityAdvReq financeAdvanceRequestEdit(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBefinApproved=null;
		try
		{
			System.out.println("advreqqq"+as.getFinalAdvReq());
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
			  System.out.println(saved.getAdvReqId());
              saved.setCommentsFinance(as.getCommentsFinance());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              System.out.println(userCode);
              saved.setSanctionedBy(userCode);
              saved.setSanctionDate(date);
              saved.setaSNo(as.getaSNo());
			 //saved.setAdvRequiredFig(as.getAdvRequiredFig());
		     //  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			 // saved.setPurpose(as.getPurpose());
			  //saved.setFinalAdvReq(as.getFinalAdvReq());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  saved.setSanctionedAmt(as.getSanctionedAmt());
			  toBefinApproved=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Edit finance approval for advance request");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBefinApproved.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBefinApproved.toString());
				mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBefinApproved;
	}
	
	public ActivityAdvReq addAdvancePayment(ProgramApprovalAdvancePayment as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		try
		{
			int advReqId=Integer.parseInt(as.getAdvanceRequestId());
			
			ActivityAdvReq aae=advRep.findById(advReqId).orElse(null);
			System.out.println(aae.getAdvReqId());
			aae.setVoucherDate(as.getVoucharDate());
			aae.setVoucherNo(as.getVoucharNo());
			aae.setAdv_paid(1);
			
			saved=advRep.save(aae);
			System.out.println(as.getAdvanceRequestId()+"hhhhhh"+as.getPurpose()+"ffff"+as.getVoucharNo()+"sss"+as.getAc().getActivityCode());
		    as.setAdvanceRequestId(as.getAdvanceRequestId());
			as.setEnteredBy(userCode);
			as.setEnteredDate(new java.util.Date());
			as.setSettleStatus("Not Settled");
			//as.setAdvancePaid(1);
			
			ProgramApprovalAdvancePayment prgrm = paap.save(as);
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return saved;
	}
	
	
	
	List<AdvanceForadvpayment> getAllAdvanceRequestFinanciallyApprovedAndNotPaid()
	{
		List<AdvanceForadvpayment> al=new ArrayList<AdvanceForadvpayment>();
		try
		{
			al=advRep.getAllAdvanceRequestFinanciallyApprovedAndNotPaid("FinanciallyApproved");
			System.out.println("alsize"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	} 
	
	
	
	public List<ActivityAdvReq>   findTotalRequestedAmntByFaculty(int activityCode,String userCode)
	{
		List<ActivityAdvReq>  arq=null;
		try
		{
			arq=advRep.findTotalRequestedAmntByFaculty(activityCode,userCode);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		return arq;
		
	}
	
	
	public List<AdvReqDetailForm> getAllAdvanceRequestForAdminApproval(String status)
	{
		List<AdvReqDetailForm> al=new ArrayList<AdvReqDetailForm>(); 
	
	   try
		{
			al=advRep.getAllAdvanceRequestForAdminApproval(status);
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	public List<ActivityAdvReq> getAllActivitysubmitteddByFaculty(String status)
	{
		List<ActivityAdvReq> al=new ArrayList<ActivityAdvReq>(); 
	
	   try
		{
			al=advRep.getAllActivitysubmitteddByFaculty(status);
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	
	public AdvanceReqReportClass getAllAdvRequestInformationByRequestId(int advReqId)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return advRep.getAllAdvRequestInformationByRequestId(advReqId);
	}
	public AdvanceReqReportClass getAllAdvRequestInformationByRequestIdForFaculty(int advReqId)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return advRep.getAllAdvRequestInformationByRequestId(advReqId);
	}
	
	public ActivityAdvReq editAdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeRecommended=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
              saved.setRecommendedRemarks(as.getRecommendedRemarks());
              long millis=System.currentTimeMillis();  
              java.sql.Date date=new java.sql.Date(millis);  
              saved.setRecommendDate(date);
              saved.setRecommendBy(userCode);
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  toBeRecommended=advRep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName(" Edit Studentadvance request ");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(toBeRecommended.getAdvReqId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus(as.getAdvanceReqStatus());
				mdtl.setActivityLog(""+toBeRecommended.toString());
				mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeRecommended;
	}
	
	public ActivityAdvReq editdvanceRequest(ActivityAdvReq as,String userCode)
	{
		
		ActivityAdvReq saved=null;
		ActivityAdvReq toBeRecommended=null;
		try
		{
			
			  saved=advRep.findById(as.getAdvReqId()).orElse(null);
    
			  saved.setAdvRequiredFig(as.getAdvRequiredFig());
			  saved.setAdvRequiredWords(as.getAdvRequiredWords());
			  saved.setPurpose(as.getPurpose());
			  saved.setEnteredRemarks(as.getEnteredRemarks());
			//  saved.setAdvanceReqStatus(as.getAdvanceReqStatus());
			  toBeRecommended=advRep.save(saved);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in ActivityAdReqService"+e);
		}
		return toBeRecommended;
	}
}
