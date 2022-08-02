package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTER.CenterFundWrapper;
import NUALS.AMS.ACADEMIC.CENTER.CenterMaster;
import NUALS.AMS.ACADEMIC.CENTER.CenterRepository;
import NUALS.AMS.ACADEMIC.CENTER.CentreFundKey;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;

@Service
public class ExpHeadService {
	@Autowired
	ExpenditureHeadRepository erep;
	
	@Autowired 
	ExpHeadNameRep er;
	@Autowired 
	BudgetHeadRep bh;
	
	
	@Autowired 
	BudgetFundRep bfr;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	
	public String saveExpHeadMaster(ExpHeadMasterWrapper ehw,String userCode)
	{
		ExpHeadsMaster saved = null;
		
		String str="";
		
		
		
		try
		{
			String description=ehw.getDescription();
			System.out.println("headName"+description);
			int existinrecord=erep.countexpHeadRecordCount(description,ehw.getFinYear());
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				ExpHeadsMaster em=new ExpHeadsMaster();
				ExpHeadsKey ehk=new ExpHeadsKey();
				
		        ehk.setDescription(ehw.getDescription());
		        ehk.setFinYear(ehw.getFinYear());
		        em.setExpHeadsKey(ehk);
				em.setStatus("submitted");
				em.setAllocationAmount(ehw.getAllocationAmount());
				em.setEnteredBy(userCode);
				em.setEnteredDate(new java.util.Date());
		        saved=erep.save(em);
		        if(saved!=null)
		        {
		        	  String str1 = "Expenditure Head: "+saved.getExpHeadsKey().getDescription()+"  in   "+saved.getExpHeadsKey().getFinYear()+"   is successfully added";
						str="SAVED-"+str1;
		        }
		        
		        if(saved==null)
		        {
		        	str="NOTSAVED-"+"Error in adding Expenditure Fund Details.";
		        }
			}
			else
			   {
				
			    str = "NOTSAVED-"+"Expenditure Head: is already exist";
			   }
			
			
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return str;
	}
	
	
	public String verifyExpHeadMaster(ExpHeadMasterWrapper ehw,String userCode)
	{
		ExpHeadsMaster saved = null;
		
		String str="";
				
		try
		{
			String description=ehw.getDescription();
			System.out.println("headName"+description);
			ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(description,ehw.getFinYear());

					System.out.println("emmmmmmmmmmmmmm"+em.getExpHeadsKey().getDescription()+ehw.getAllocationAmount()+ehw.getExpVerifiedRemarks());
			//	ExpHeadsMaster em=new ExpHeadsMaster();
				ExpHeadsKey ehk=new ExpHeadsKey();
				
		      //  ehk.setDescription(ehw.getDescription());
		      //  ehk.setFinYear(ehw.getFinYear());
		       // em.setExpHeadsKey(ehk);
				em.setStatus("verified");
				em.setAllocationAmount(ehw.getAllocationAmount());
				em.setExpVerifiedBy(userCode);
				em.setExpVerifiedDate(new java.util.Date());
				em.setExpVerifiedRemarks(ehw.getExpVerifiedRemarks());
		        saved=erep.save(em);
		        //System.out.println("savedddd"+saved.getAllocationAmount());
		        if(saved!=null)
		        {
		        	 str="Verified Successfully";
		        }
		        else
		        {
		        	str="Error in verifiying Expenditure Fund Details.";
		        }
		
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return str;
	}
	
	
	public String updateExpHeadMaster(ExpHeadMasterWrapper ehw,String userCode)
	{
		ExpHeadsMaster saved = null;
		
		String str="";
				
		try
		{
			String description=ehw.getDescription();
			System.out.println("headName"+description);
			ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(description,ehw.getFinYear());

					System.out.println("emmmmmmmmmmmmmm"+em.getExpHeadsKey().getDescription()+ehw.getAllocationAmount()+ehw.getExpVerifiedRemarks());
			//	ExpHeadsMaster em=new ExpHeadsMaster();
				ExpHeadsKey ehk=new ExpHeadsKey();
				
		      //  ehk.setDescription(ehw.getDescription());
		      //  ehk.setFinYear(ehw.getFinYear());
		       // em.setExpHeadsKey(ehk);
				//em.setStatus("verified");
				em.setAllocationAmount(ehw.getAllocationAmount());
				em.setExpEditedBy(userCode);
				em.setExpEditedDate(new java.util.Date());
				em.setExpEditedRemarks(ehw.getExpVerifiedRemarks());
		        saved=erep.save(em);
		        //System.out.println("savedddd"+saved.getAllocationAmount());
		        if(saved!=null)
		        {
		        	 str="Edited Successfully";
		        }
		        else
		        {
		        	str="Error in Editing Expenditure Fund Details.";
		        }
		
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return str;
	}
	

	public String approveExpHeadMaster(ExpHeadMasterWrapper ehw,String userCode)
	{
		ExpHeadsMaster saved = null;
		
		String str="";
				
		try
		{
			String description=ehw.getDescription();
			System.out.println("headName"+description);
			ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(description,ehw.getFinYear());

					System.out.println("emmmmmmmmmmmmmm"+em.getExpHeadsKey().getDescription()+ehw.getAllocationAmount()+ehw.getExpVerifiedRemarks());
			//	ExpHeadsMaster em=new ExpHeadsMaster();
				ExpHeadsKey ehk=new ExpHeadsKey();
				
		      //  ehk.setDescription(ehw.getDescription());
		      //  ehk.setFinYear(ehw.getFinYear());
		       // em.setExpHeadsKey(ehk);
				em.setStatus(ehw.getApprovedStatus());
				//em.setAllocationAmount(ehw.getAllocationAmount());
				em.setExpApprovedBy(userCode);
				em.setExpApprovedDate(new java.util.Date());
				em.setExpApprovedRemarks(ehw.getExpVerifiedRemarks());
		        saved=erep.save(em);
		        //System.out.println("savedddd"+saved.getAllocationAmount());
		        if(saved!=null)
		        {
		        	 str="Approved Successfully";
		        }
		        else
		        {
		        	str="Error in Approving Expenditure Fund Details.";
		        }
		
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//to get the details of all expheads
	public List<ExpHeadsMaster> getAllExpHeadDetails()
	{
		List<ExpHeadsMaster> cmlist = new ArrayList<ExpHeadsMaster>();
		erep.getAllExpHeadSubmitted().forEach(cmlist::add);
		return cmlist;
	}
	
	public List<ExpHeadsMaster> getAllExpHeadDetailswithoutStatus()
	{
		List<ExpHeadsMaster> cmlist = new ArrayList<ExpHeadsMaster>();
		erep.getAllExpHeadDetailswithoutStatus().forEach(cmlist::add);
		return cmlist;
	}
	
	
public List<ExpHeadsMaster>getExpenditureHeadsByFinYear(String finyear)
{
	List<ExpHeadsMaster> cmlist = new ArrayList<ExpHeadsMaster>();
	erep.getAllExpHeadsByFinyear(finyear).forEach(cmlist::add);
	return cmlist;
}


public ExpHeadsMaster getExpenditureHeadsByDesAndFinyear(String finyear,String des)
{
   ExpHeadsMaster cmlist= null;
   cmlist=erep.getExpHeadsrecordBydescriptionAndFinyear(des,finyear);
	return cmlist;
}

public ExpHead saveExpHeadNames(ExpHead ehw,String userCode)
{
	ExpHead saved = null;
	
	
	
	try
	{
		java.util.Date d=new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		ehw.setEnteredDate(sqlDate);
		ehw.setEnteredBy(userCode);
		ehw.setExpHeadStatus("submitted");
	    saved=er.save(ehw);
	    ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("add expenditure head name");
		mdtl.setMdtlDate(new Date());
		mdtl.setMdtlProcessId(String.valueOf(saved.getHeadId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("submitted");
		mdtl.setActivityLog(""+saved.toString());
		mdtlr.save(mdtl);
 
		 
	}
	catch(Exception ex)
	{
		System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
	}
	
	return saved;
}

public ExpHead verifyExpHeadNames(ExpHead ehw,String userCode)
{
	ExpHead saved = null;
	
	
	
	try
	{
		java.util.Date d=new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
	//	ehw.setEnteredDate(sqlDate);
		//ehw.setEnteredBy(userCode);
		ehw.setExpHeadStatus("verified");
	    saved=er.save(ehw);
	    ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
	    mdtl.setMdtlProcessName("verify expenditure head name");
		mdtl.setMdtlDate(new Date());
		mdtl.setMdtlProcessId(String.valueOf(saved.getHeadId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("verified");
		mdtl.setActivityLog(""+saved.toString());
		mdtlr.save(mdtl);
 
		 
	}
	catch(Exception ex)
	{
		System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
	}
	
	return saved;
}
public ExpHead approveExpHeadNames(ExpHead ehw,String userCode)
{
	ExpHead saved = null;
	
	
	
	try
	{
		java.util.Date d=new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
	//	ehw.setEnteredDate(sqlDate);
		//ehw.setEnteredBy(userCode);
		ehw.setExpHeadStatus("approved");
		
	    saved=er.save(ehw);
	    ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
	    mdtl.setMdtlProcessName("approve expenditure head name");
		mdtl.setMdtlDate(new Date());
		mdtl.setMdtlProcessId(String.valueOf(saved.getHeadId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("approved");
		mdtl.setActivityLog(""+saved.toString());
		mdtlr.save(mdtl);
 
		 
	}
	catch(Exception ex)
	{
		System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
	}
	
	return saved;
}
//to get the details of all expheads
	public List<ExpHead> getAllExpHeadNameDetails()
	{
		List<ExpHead> cmlist = new ArrayList<ExpHead>();
		er.getAllExpHeadSubmitted().forEach(cmlist::add);
		return cmlist;
	}
	
	
	
	public BudgetHead saveBudgetHead(BudgetHead ehw,String userCode)
	{
		BudgetHead saved = null;
		
		
		
		try
		{
			String description=ehw.getBudHeadName();
			System.out.println("headName"+description);
			int existinrecord=bh.countbudgetHeadRecordCount(description);
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				ehw.setBudStatus("submitted");
				ehw.setEnteredBy(userCode);
			
			ehw.setEnteredDate(new java.util.Date());
				
		        saved=bh.save(ehw);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public List<BudgetHead> getAllBudgetHeadDetails()
	{
		List<BudgetHead> cmlist = new ArrayList<BudgetHead>();
		bh.getAllbudHeadSubmitted().forEach(cmlist::add);
		return cmlist;
	}
	
	public ExpHeadsMaster getExpHeadDetails(String headName,String finYear)
	{
		ExpHeadsMaster cmlist =null;
		cmlist=erep.getExpHeadsrecordBydescriptionAndFinyear(headName,finYear);
		return cmlist;
	}
	
	public BudgetHeadFund saveBudgetFund(BudgetFundWrapper cfw,String userCode)
	{
		BudgetHeadFund saved = null;
		
		
		
		try
		{
			int budhead=cfw.getBudHeadId();
			System.out.println("budget code"+budhead);
			int existinrecord=bfr.countBudgetFundRecordCount(budhead,cfw.getFinYear());
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				BudgetHeadFund cf=new BudgetHeadFund();
				BudgetFundKey cfk=new BudgetFundKey();
				
				BudgetHead cm = bh.findById(cfw.getBudHeadId()).get();		
				
				cfk.setBudHeadId(cm);
				cfk.setFinYear(cfw.getFinYear());
	
			cf.setBudgetFundKey(cfk);
			cf.setFundAmount(cfw.getFundAmount());
			cf.setEnteredBy(userCode);
			cf.setFundAmountBalance(cfw.getFundAmount());
			cf.setEnteredRemarks(cfw.getEnteredRemark());
			cf.setEnteredDate(new java.util.Date());
			cf.setBudgetFundStatus("submitted");
				
		
		        saved=bfr.save(cf);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public BudgetHeadFund getBudgetFundAmount(String budheadId,String finYear)
	{
		BudgetHeadFund cmlist= null;
	   cmlist=bfr.getBudgetFundAmount(Integer.parseInt(budheadId),finYear);
		return cmlist;
	}
	
	public BudgetHeadFund verifyBudgetFund(BudgetFundWrapper cfw,String userCode)
	{
		System.out.println("enter in verify budgetfund"+cfw.getBudHeadId());
		BudgetHeadFund cf = null;
		BudgetHeadFund saved = null;	
		try
		{
		
		BudgetFundKey cfk=new BudgetFundKey();
				
		BudgetHead cm = bh.findById(cfw.getBudHeadId()).get();		
		System.out.println("cm"+cm.getBudHeadId());
		cfk.setBudHeadId(cm);
		cfk.setFinYear(cfw.getFinYear());
	     cf=bfr.getBudgetFundAmount(cfw.getBudHeadId(),cfw.getFinYear());
	     System.out.println("cfffffffffff"+cf.getBudgetFundKey().getBudHeadId());
			cf.setBudgetFundKey(cfk);
			cf.setFundAmount(cfw.getFundAmount());
			cf.setBfverifiedBy(userCode);
			cf.setFundAmountBalance(cfw.getFundAmount());
			cf.setBfverifiedRemarks(cfw.getBfverifiedRemarks());
			cf.setBfverifiedDate(new java.util.Date());
			cf.setBudgetFundStatus("verified");
				
		
		        saved=bfr.save(cf);
			}
			
			 
	
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	
	public BudgetHeadFund editBudgetFunds(BudgetFundWrapper cfw,String userCode)
	{
		System.out.println("enter in verify budgetfund"+cfw.getBudHeadId());
		BudgetHeadFund cf = null;
		BudgetHeadFund saved = null;	
		try
		{
		
		BudgetFundKey cfk=new BudgetFundKey();
				
		BudgetHead cm = bh.findById(cfw.getBudHeadId()).get();		
		System.out.println("cm"+cm.getBudHeadId());
		cfk.setBudHeadId(cm);
		cfk.setFinYear(cfw.getFinYear());
	     cf=bfr.getBudgetFundAmount(cfw.getBudHeadId(),cfw.getFinYear());
	     System.out.println("cfffffffffff"+cf.getBudgetFundKey().getBudHeadId());
			cf.setBudgetFundKey(cfk);
			cf.setFundAmount(cfw.getFundAmount());
			cf.setBfeditedBy(userCode);
			cf.setFundAmountBalance(cfw.getFundAmount());
			cf.setBfeditedRemarks(cfw.getBfverifiedRemarks());
			cf.setBfeditedDate(new java.util.Date());
			//cf.setBudgetFundStatus("verified");
				
		
		        saved=bfr.save(cf);
			}
			
			 
	
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public BudgetHeadFund approveBudgetFund(BudgetFundWrapper cfw,String userCode)
	{
		BudgetHeadFund cf = null;
		BudgetHeadFund saved = null;	
		try
		{
		
			
			
				BudgetFundKey cfk=new BudgetFundKey();
				
				BudgetHead cm = bh.findById(cfw.getBudHeadId()).get();		
				
				cfk.setBudHeadId(cm);
				cfk.setFinYear(cfw.getFinYear());
	            cf=bfr.getBudgetFundAmount(cfw.getBudHeadId(),cfw.getFinYear());
			//cf.setBudgetFundKey(cfk);
		//	cf.setFundAmount(cfw.getFundAmount());
			cf.setBfapprovedBy(userCode);
			//cf.setFundAmountBalance(cfw.getFundAmount());
			cf.setBfapprovedRemarks(cfw.getBfapprovedRemarks());
			cf.setBfapprovedDate(new java.util.Date());
			cf.setBudgetFundStatus(cfw.getApprovedStatus());
		    saved=bfr.save(cf);
	 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	public BudgetHeadFund editBudgetFund(BudgetFundWrapper cfw,String userCode)
	{
		BudgetHeadFund saved = null;
		try
		{
			int budhead=cfw.getBudHeadId();
			System.out.println("budget code"+budhead);
			int existinrecord=bfr.countBudgetFundRecordCount(budhead,cfw.getFinYear());
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				BudgetHeadFund cf=new BudgetHeadFund();
				BudgetFundKey cfk=new BudgetFundKey();
				
				BudgetHead cm = bh.findById(cfw.getBudHeadId()).get();		
				
				cfk.setBudHeadId(cm);
				cfk.setFinYear(cfw.getFinYear());
	
			//cf.setBudgetFundKey(cfk);
			//cf.setFundAmount(cfw.getFundAmount());
			//cf.setEnteredBy(userCode);
			//cf.setFundAmountBalance(cfw.getFundAmount());
			//cf.setERemarks(cfw.getEnteredRemark());
			//cf.setEnteredDate(new java.util.Date());
			cf.setBudgetFundStatus(cfw.getApprovedStatus());
				
		
		        saved=bfr.save(cf);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
}
