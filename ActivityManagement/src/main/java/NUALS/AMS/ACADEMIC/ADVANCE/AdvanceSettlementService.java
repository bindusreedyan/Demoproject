package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFund;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFundRep;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetFundKey;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetFundWrapper;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetHead;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetHeadFund;
import NUALS.AMS.ACADEMIC.ACTIVITIES.OrderGenaration;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS.ActivityParticipateMember;

@Service
public class AdvanceSettlementService {
	@Autowired 
	AdvEspSettlementRep asR;
	@Autowired 
	EsGenerationRep or;
	
	@Autowired
	ActivityBudgetFundRep abf;
	@Autowired
MasterDataTimeLineRepository mdtlr;
	public AdvExpSettlement saveAdvanceSettlementBasicDetails(AdvExpSettlement as,String userCode)
	{
		
		AdvExpSettlement saved=null;
				
		try
		{
			as.setEnteredBy(userCode);
			as.setEnteredDate(new java.util.Date());
			as.setAdvSettlementStatus("partially submitted");
			saved=	asR.save(as);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Add Advance/expenditure Sanction Basic Details");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception e");
		}
		return saved;
	}
	
	public AdvExpSettlement editAdvanceSettlementBasicDetails(AdvExpSettlement as,String userCode)
	{
		
		AdvExpSettlement saved=null;
				
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSetId()).orElse(null);
			as.setEnteredBy(apm.getEnteredBy());
			as.setEnteredDate(apm.getEnteredDate());
			as.setAdvSettlementStatus(apm.getAdvSettlementStatus());
			saved=	asR.save(as);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Edit Advance/expenditure Sanction Basic Details");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception e");
		}
		return saved;
	}
	
	
	
	public AdvExpSettlement saveSettlementRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			apm.setEnteredBy(userCode);
			apm.setEnteredDate(new java.util.Date());
			apm.setEnteredRemarks(as.getEnteredRemarks());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setAdvSettlementStatus("submitted");
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("save settlement Remark Details");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
/*	public AdvExpSettlement editSettlementRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			apm.setEditedBy(userCode);
			apm.setUpdatedDate(new java.util.Date());
			apm.setUpdatedRemark(as.getEnteredRemarks());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
		//	apm.setAdvSettlementStatus("submitted");
			af=asR.save(apm);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}*/
	
	public AdvExpSettlement facultyApprovalRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			apm.setSetlmntRecommendedBy(userCode);
			apm.setSetlmntRecommendedDate(new java.util.Date());
			apm.setSetlmntRecomRemarks(as.getOfficeRecommendationRemark());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Faculty Recommendation of Expenditure/advance santion");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	
	
	public AdvExpSettlement editSettlementRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			//apm.setSetlmntRecommendedBy(userCode);
			//apm.setSetlmntRecommendedDate(new java.util.Date());
			//apm.setSetlmntRecomRemarks(as.getOfficeRecommendationRemark());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setEnteredRemarks(as.getEnteredRemarks());
			//apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Edit Settlement Remarks");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	
	
	public List<AdvExpSettlement> loadAllExpenditureDetailsSubmitted()
	{
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			al=asR.loadAllExpenditureDetailsSubmitted();
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	
	public List<AdvExpSettlement> loadAllExpenditureDetailsOfficeRecommended()
	{
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			al=asR.loadAllExpenditureDetailsOfficeRecommended();
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	
	
	
	
	
	public AdvExpSettlement loadAllExpenditureDetailsSubmittedBySettlementId(int setid)
	{
		AdvExpSettlement al=null;
		try
		{
			al=asR.loadAllExpenditureDetailsSubmittedBySettlementId(setid);
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	

	public AdvExpSettlement saveSettlementOfficerRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			apm.setVerifiedL1By(userCode);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setVerifiedL1Remarks(as.getOfficeRecommendationRemark());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
		//	apm.setSancAmt(as.getSanctionedAmnt());
			
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement officer Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	public AdvExpSettlement saveSettlementAdministrativeRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			apm.setVerifiedL2By(userCode);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setVerifiedL2Remarks(as.getOfficeRecommendationRemark());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement Administrative Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	public AdvExpSettlement saveSettlementOfficeFinancialRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setFinanceverifiedL2By(userCode);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setFinanceverifiedL2Remarks(as.getFinancialOfficeRecommendationRemark());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement finance officer Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	public AdvExpSettlement saveSettlementFinancialRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setVerifiedL3By(userCode);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setVerifiedL3Remarks(as.getFinancialRecommendationRemark());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement Finance Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	public List<AdvExpSettlement> loadAllExpenditureDetailsAdministrativeApproved()
	{
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			al=asR.loadAllExpenditureDetailsAdministrativeApproved();
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	
	public List<AdvExpSettlement> loadAllExpenditureDetailsFinanceApproved()
	{
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			al=asR.loadAllExpenditureDetailsFinanceApproved();
			
		}
		catch(Exception e)
		{
			
		}
		
		return al;
	}
	public AdvExpSettlement saveSettlementFinalRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			
			System.out.println("apm.getSettlementType()"+apm.getSettlementType());
			
			
			if(apm.getSettlementType().equals("expenditure"))
			{
				apm.setAdvSettlementStatus("sanctioned");
			}
			
			if(apm.getSettlementType().equals("advance"))
			{
				apm.setAdvSettlementStatus("settled");
			}
			
			if(apm.getSettlementType().equals("finalsettlemnt"))
			{
				apm.setAdvSettlementStatus("closed");
				
			}
			
			apm.setEsOrdersIssued("Not Issued");
			apm.setApprovedBy(userCode);
			Date currentDate =new Date();
			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
			apm.setApprovedDate(sqlDate);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setApprovedRemarks(as.getFinalRecommendationRemark());
		//	apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement final Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	
	
	public AdvExpSettlement saveSettlementFinalOfficeRemarks(AdvanceSanctionRemarks as,String userCode)
	{
		AdvExpSettlement af=null;
		
		try
		{
		
			
			AdvExpSettlement  apm=asR.findById(as.getSettlementId()).orElse(null);
			System.out.println("advdSettlementID"+apm.getSetId());
			apm.setBalAmountTobePaid(as.getSanctionedAmnt());
			apm.setFinalOfficeRecommendedBy(userCode);
			//apm.setVerifiedL1Date(new java.util.Date());
			apm.setFinalOfficeRecommendationRemark(as.getFinalOfficeRecommendationRemark());
			apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName(" Settlement Finance office Remarks Add");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(af.getSetId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus(as.getRecommendationStatus());
			mdtl.setActivityLog(""+af.toString());
			mdtlr.save(mdtl);
			
			
			
			
		//	apm.setAdvSettlementStatus(as.getRecommendationStatus());
			af=asR.save(apm);
			 
		}
		catch(Exception e)
		{
			System.out.println("exception is"+e.getMessage());
		}
				return af;
				
		
	}
	
	
	
	
	
	
	
	
	
	
	public Esgeneration esOrderGenaration(Esgeneration am,String userCode)
	{
		Esgeneration saved = null;
		String msg = null;
		AdvExpSettlement amster=null;
		
		try
		{
			
			int ascount=getCountofAsNo();
			System.out.println("asno"+ascount);
		
			
			if(ascount==0)
			{
				String asprefix=am.getEsNumberPrefix()+"/1";
				am.setEsNumberPrefix(asprefix);
				saved=or.save(am);
				amster=updateEsno(asprefix,(am.getAe().getSetId()));
				
			}
			else
			{
				int nextvar=ascount+1;
				String next=Integer.toString(nextvar);
				String asprefix=am.getEsNumberPrefix()+"/"+next;
				am.setEsNumberPrefix(asprefix);
				saved=or.save(am);
				amster=updateEsno(asprefix,(am.getAe().getSetId()));
				
			}

			/* if(saved == null)
				{
					
					msg="NOTSAVED-"+"Error in Es Generation ";	
					
				}
				else
				{
				msg = "ES Order is successfully Generated for the Program with ActivityId"+saved.getAc().getActivityCode()+"with ES No-"+amster.getEsNo();
				ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName(" ES Order ");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(saved.getOrderGenId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("submitted");
				mdtl.setActivityLog(""+saved.toString());
				mdtlr.save(mdtl);
				}*/
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			saved=null;
			
			
		}
		System.out.println(saved);
		return saved;
	}
	
	
	
	
	public Esgeneration esOrderGenarationEdit(Esgeneration am,String userCode)
	{
		Esgeneration saved = null;
		Esgeneration saved1 = null;
		String msg = null;
		
		ActivityMaster amster=null;
		
		try
		{
			saved=or.findById(am.getOrderGenId()).orElse(null);
			System.out.println("ordergenId"+saved.getOrderGenId());
			
			saved.setExternlFundType(am.getExternlFundType());
			saved.setSectionbHeading(am.getSectionbHeading());
			saved.setEsNumberPrefix(am.getEsNumberPrefix());
			saved.setAbstractDetails(am.getAbstractDetails());
			
			saved.setEsrefer(am.getEsrefer());
			saved.setOrderHeading(am.getOrderHeading());
			
			saved.setEsOrderContent(am.getEsOrderContent());
			saved.setCopyTo(am.getCopyTo());
	
			saved1=or.save(saved);

			 if(saved1== null)
				{
				
				}
				else
				{
				msg = "AS Order detaild is successfully edited";
				
				msg="NOTSAVED-"+"Error in ES order details Editing";	
				ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName(" ES Order Edit");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(saved.getOrderGenId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("submitted");
				mdtl.setActivityLog(""+saved.toString());
				mdtlr.save(mdtl);
				}
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			saved=null;
			
			
		}
		System.out.println(saved);
		return saved1;
	}
	
	
	
	
	public int getCountofAsNo()
	{
		int ascount=asR.getCountofAsNo();
		return(ascount);
	}
	public AdvExpSettlement updateEsno(String esNo,int activityId)
	{
		AdvExpSettlement am=null;
		
		try
		{
			
			am=asR.loadAllExpenditureDetailsSubmittedBySettlementId(activityId);
			am.setEsNo(esNo);
			am.setEsOrdersIssued("issued");
			
		    asR.save(am);
			
		}
		catch(Exception e)
		{
			
		}
		
		return am;
		
	}
	public ActivityBudgetFund saveBudgetFund(ActivityBudgetFund cfw,String userCode)
	{
		ActivityBudgetFund saved = null;
		
		
		
		try
		{
			
				
			cfw.setEnteredBy(userCode);
			cfw.setEnteredDate(new java.util.Date());
			cfw.setBudgetFundStatus("submit");
				
		
		        saved=abf.save(cfw);
		        
		       
			
			}
	 
			 
	
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public ActivityBudgetFund updateBudgetFund(ActivityBudgetFund cfw,String userCode)
	{
		ActivityBudgetFund saved = null;
		ActivityBudgetFund tobesaved = null;
		
		
		
		try
		{
        saved=abf.findById(cfw.getActivityBudgetFundId()).orElse(null);
        System.out.println("savedddddddddd"+saved.getActivityBudgetFundId());
			saved.setAsAmount(cfw.getAsAmount());
			saved.setEsAmount(cfw.getEsAmount());
				
			//cfw.setEnteredBy(userCode);
			//cfw.setEnteredDate(new java.util.Date());
			//cfw.setBudgetFundStatus("submit");
				
		
			tobesaved=abf.save(saved);
		        
		       
			
			}
	 
			 
	
		catch(Exception ex)
		{
			System.out.println("Exception at update ActivityBudgetFund : "+ex.getMessage());
		}
		
		return tobesaved;
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
