package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

@Service
public class GoodsReturnedService {
	
	@Autowired
	GoodsReturnRep aer;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public String saveGoodsDetails(GoodsReturned as,String userCode)
	{
		
		GoodsReturned saved=null;
		String str = null;
				
		try
		{
			
			as.setEnteredBy(userCode);
			as.setEnteredDate(new java.util.Date());
			as.setGoodsRemarks(as.getGoodsRemarks());
			saved=aer.save(as);
			
			if(saved!=null)
			{
				str="successfully Inserted Good Details";
				ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Goods Details Saving");
				mdtl.setMdtlDate(new java.util.Date());
				mdtl.setMdtlProcessId(String.valueOf(saved.getGoodsReturnId()));
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

	
	public List<GoodsReturned> loadAllGoodsReturnBySettlementId(String setid)
	{
		List<GoodsReturned> al=new ArrayList<GoodsReturned>();
		try
		{
			al=aer.loadAllGoodsReturnBySettlementId(Integer.parseInt(setid));
			
		}
		catch(Exception e)
		{
			System.out.println("Exception e"+e);
			
		}
		
		return al;
	}
}
