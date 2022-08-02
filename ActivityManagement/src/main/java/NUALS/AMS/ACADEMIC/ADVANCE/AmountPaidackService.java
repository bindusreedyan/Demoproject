package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

@Service
public class AmountPaidackService {
	
	@Autowired 
	AmountPaidBackRep apbr;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public String saveAmountPaidBacktoUniversityDetails(AmountPaidBack as,String userCode)
	{
		
		AmountPaidBack saved=null;
		String str = null;
				
		try
		{
			
			as.setEnteredBy(userCode);
			as.setEnteteredDate(new java.util.Date());
			as.setEnteredRemarks(as.getEnteredRemarks());
			saved=apbr.save(as);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Add Amount paid back to university");
			mdtl.setMdtlDate(new java.util.Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getUniversityPaymentBackId()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
			if(saved!=null)
			{
				str="successfully inserted amount";
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
	
	public List<AmountPaidBack> loadAllAmountPaidBackBySettlementId(int setid)
	{
		List<AmountPaidBack> al=new ArrayList<AmountPaidBack>();
		try
		{
			al=apbr.loadAllAmountPaidBackBySettlementId(setid);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception e"+e);
			
		}
		
		return al;
	}

	public String editAmountPaidBacktoUniversityDetails(AmountPaidBack as,String userCode)
	{
		
		AmountPaidBack saved=null;
		String str = null;
				System.out.println("amount paid back id"+as.getUniversityPaymentBackId());
		try
		{
		saved=apbr.findById(as.getUniversityPaymentBackId()).orElse(null);
		saved.setPaidBackDescription(as.getPaidBackDescription());
		saved.setCostCenterCode(as.getCostCenterCode());
		saved.setVoucharNo(as.getVoucharNo());
		saved.setPaymentDate(as.getPaymentDate());
		saved.setAmountPaidBack(as.getAmountPaidBack());
		AmountPaidBack saved1=apbr.save(saved);
		System.out.println("saved amount"+saved1.getUniversityPaymentBackId());
		ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("Add Amount paid back to university");
		mdtl.setMdtlDate(new java.util.Date());
		mdtl.setMdtlProcessId(String.valueOf(saved.getUniversityPaymentBackId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("submitted");
		mdtl.setActivityLog(""+saved.toString());
		mdtlr.save(mdtl);
			if(saved1!=null)
			{
				str="successfully edited";
			}
			else
			{
			str="error in editing details";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception e"+e.getMessage());
			str=e.getMessage();
		}
		return str;
	}
}
