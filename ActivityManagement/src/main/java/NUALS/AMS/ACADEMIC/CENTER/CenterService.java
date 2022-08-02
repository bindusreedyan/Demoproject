package NUALS.AMS.ACADEMIC.CENTER;

import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRERepository;


import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CenterService {
	
	
	@Autowired
	CenterRepository crep;
	@Autowired
	CenterFundRepository cfr;
	@Autowired 
	CENTRERepository cenrep;
	
	public CenterMaster saveCenter(CenterMaster cm)
	{
		CenterMaster saved = null;
		
		
		
		try
		{
			int centerId=cm.getCentreCode();
			int existinrecord=crep.findCenterRecord(centerId);
			if(existinrecord==0)
			{
			
		 saved=crep.save(cm);
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
	
	
	public List<CenterMaster> getAllCenters()
	{
		List<CenterMaster> cmlist = new ArrayList<CenterMaster>();
		crep.getAllCenteresSubmitted().forEach(cmlist::add);
		return cmlist;
	}
    //center fund services starts from here
	//save center fund  -------------------to be added in jayaram sir----------
	public CenterFund saveCenterFund(CenterFundWrapper cfw,String userCode)
	{
		CenterFund saved = null;
		
		
		
		try
		{
			int centerCode=cfw.getCentreCode();
			System.out.println("centre code"+centerCode);
			int existinrecord=cfr.countCenterFundRecordCount(centerCode,cfw.getFinYear());
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				CenterFund cf=new CenterFund();
				CentreFundKey cfk=new CentreFundKey();
				
				CENTRE cm = cenrep.findById(cfw.getCentreCode()).get();		
				System.out.println(cm.getCentre_id());
				cfk.setCm(cm);
				cfk.setFinYear(cfw.getFinYear());
				
				
				
				cf.setCentreFundKey(cfk);
				cf.setFundAmount(cfw.getFundAmount());
				cf.setEnteredBy(userCode);
				cf.setEnteredDate(cfw.getEnteredDate());
				cf.setCurrentBalance(cfw.getFundAmount());
				cf.setCommitedTotal(0.0);
				cf.setExpTillDate(0.0);
				cf.setStatus("submitted");
		 saved=cfr.save(cf);
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
