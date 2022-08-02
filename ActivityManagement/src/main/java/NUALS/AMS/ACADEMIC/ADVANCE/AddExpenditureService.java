package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

@Service
public class AddExpenditureService {
	
	@Autowired
	AddExpenditureRep aer;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public String saveExpenditureDetails(AdvExpenditure as,String userCode)
	{
		
		AdvExpenditure saved=null;
		String str = null;
				
		try
		{
			
			as.setEnteredBy(userCode);
			as.setEnteredDate(new java.util.Date());
			as.setEnteredRemarks(as.getEnteredRemarks());
			saved=aer.save(as);
		
			if(saved!=null)
			{
				str="successfully Inserted Expenditure Details";
				ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Expenditure Details Saving");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(saved.getExpId()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("submitted");
				mdtl.setActivityLog(""+saved.toString());
				mdtlr.save(mdtl);
			}
			else
			{
				str="error in adding details";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception e");
			str=e.getMessage();
		}
		return str;
	}
	
	
	public List<AdvExpenditure> loadAllExpenditureBySettlementId(String settlementId) {
		List<AdvExpenditure> al=new ArrayList<AdvExpenditure>();
		
		try
		{
		al=aer.loadAllExpenditureBySettlementId(Integer.parseInt(settlementId));	
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		
		return al;
		
		
	}
		
	
	
	public AdvExpenditure loadExpenditureByexpenditureId(String expenditureId) {
	AdvExpenditure al=null;
		
		try
		{
		al=aer.loadExpenditureByexpenditureId(Integer.parseInt(expenditureId));	
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		
		return al;
		
		
	}
	
	
	
	public String updateExpenditureDetails(AdvExpenditure as,String userCode)
	{
		
		AdvExpenditure saved=null;
		String str = null;
				
		try
		{
			
			saved=aer.findById(as.getExpId()).orElse(null);
			saved.setAdmissibleAmountAddedBy(userCode);
			saved.setAdmissibleAmountAddedDate(new java.util.Date());
			saved.setEnteredRemarks(as.getEnteredRemarks());
			saved.setAdmsAmt(as.getAdmsAmt());
			AdvExpenditure	saved1=aer.save(saved);
			if(saved1!=null)
			{
				str="successfully Inserted Expenditure Details";
			}
			else
			{
				str="error in adding details";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception e");
			str=e.getMessage();
		}
		return str;
	}
	public String editExpenditureDetails(AdvExpenditure as,String userCode)
	{
		
		AdvExpenditure saved=null;
		String str = null;
				
		try
		{
			
			saved=aer.findById(as.getExpId()).orElse(null);
		//	as.setAdmissibleAmountAddedBy(userCode);
			//saved.setAdmissibleAmountAddedBy(userCode);
		//	as.setAdmissibleAmountAddedDate(new java.util.Date());
			
			as.setEnteredBy(saved.getEnteredBy());
			as.setEnteredDate(saved.getEnteredDate());
			as.setEnteredRemarks(saved.getEnteredRemarks());
			//as.setAdmsAmt(as.getAdmsAmt());
			AdvExpenditure	saved1=aer.save(as);
			if(saved1!=null)
			{
				str="successfully edited Expenditure Details";
			}
			else
			{
				str="error in editing details";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception e");
			str=e.getMessage();
		}
		return str;
	}
	
	
	public List<AdvExpenditure> getToalAmountExpenditureBySettlementIdAndHeadId(String settlementId,String exphead) {
		List<AdvExpenditure> al=null;
		
		try
		{
			System.out.println("settlementId"+settlementId+"exphead"+exphead);
		al=aer.getToalAmountExpenditureBySettlementIdAndHeadId(Integer.parseInt(settlementId),Integer.parseInt(exphead));	
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		
		return al;
		
		
	}
	
	
	
}
