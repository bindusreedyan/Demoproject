package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.ACADEMIC.ADVANCE.ActivityAdvReq;

@Service
public class ProgramApprovalAdvancePaymentService {
	@Autowired
	ProgramApprovalAdvancePaymentRep paap;
	
	@Autowired
	ActivityAdvEstimateRepository aaer;
	
MasterDataTimeLineRepository mdtlr;
	
	public ProgramApprovalAdvancePayment savProgramApprovalAdvancePayment(ProgramApprovalAdvancePayment ehw,String userCode)
	{
		ProgramApprovalAdvancePayment saved = null;
		
		try
		{
			System.out.println(ehw.getAc().getActivityCode()+"finyear"+ehw.getFinYear()+"vouchar date="+ehw.getVoucharDate()+"vochar no"+ehw.getVoucharNo()+"purpose="+ehw.getPurpose()+"amnntt="+ehw.getAdvancePaid());
	
			
	
		 	ehw.setEnteredBy(userCode);
			ehw.setEnteredDate(new java.util.Date());
			
	        saved= paap.save(ehw);
	        
	    	int activityId=ehw.getAc().getActivityCode();
	    	System.out.println("activityIdddddddddddd"+activityId);
			String finyear=ehw.getFinYear();
			aaer.updateAdvePaid(activityId,finyear);
			
ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("advance payment");
			//mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getProgramApprovalAdvancePaymentId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("advance paid");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
			
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("savProgramApprovalAdvancePayment : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	List<ProgramApprovalAdvancePayment> getAllAdvanceRecieved( String activityId)
	{
		List<ProgramApprovalAdvancePayment> prap=null;
		try
		{
			int actId=Integer.parseInt(activityId);
			prap=(List<ProgramApprovalAdvancePayment>) paap.findAllAdvanceRecievedByActivityId(actId);
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		
		
		return prap;		
	}
	
	ProgramApprovalAdvancePayment getAdvancePaidByAdvancePaymentId(String activityId)
	{
	ProgramApprovalAdvancePayment prap=null;
		try
		{
			int actId=Integer.parseInt(activityId);
			prap= paap.getAdvancePaidByAdvancePaymentId(actId);
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		
		
		return prap;		
	}
	public ProgramApprovalAdvancePayment saveAdvanceSettlement(AdvanceSettlementClass  ehw,String userCode)
	{
		ProgramApprovalAdvancePayment saved = null;
		ProgramApprovalAdvancePayment savedafter=null;
		try
		{
			saved=paap.findById(ehw.getProgramApprovalAdvancePaymentId()).orElse(null);//;
			System.out.println("settled amnt"+saved.getSettledAmount());
			double settledAmnt=saved.getSettledAmount();
			double tobesettled=settledAmnt+ehw.getSettledamount();
	        saved.setSettledAmount(tobesettled);
			saved.setSettleStatus(ehw.getSettleStatus());
			saved.setSettlementRemarks(ehw.getSettlementRemarks());
			 savedafter= paap.save(saved);
			
			
			
			
	    
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("savProgramApprovalAdvancePayment : "+ex.getMessage());
		}
		
		return savedafter;
	}

}
