package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsComplianceService {
	
	
	@Autowired
	AscomplianceRep asComRep;
	 public String complianceDetails(AsCompliance as,String userCode)
     {
	
		 AsCompliance saved=null;
	     String str = null;
			
	try
	{
		
		
		int setId=as.getAc().getSetId();
		int existinrecord1=asComRep.findActivityRecordByDes(setId,as.getExpHead());
		if(existinrecord1==0)
		{
		
		    as.setComplianceJustifiedBy(userCode);
		    as.setComplianceJustifiedDate(new java.util.Date());
			saved=asComRep.save(as);
		if(saved!=null)
		{
			str="successfully Inserted Compliance to Administrative Sanction details";
		}
		else
		{
			str="error in adding details";
		}
	}
		
		else
		{
			str="This record is already exist";
		}
	}
	catch(Exception e)
	{
		System.out.println("Exception e");
		str=e.getMessage();
	}
	return str;
}

	 public List<AsCompliance> loadAllCompliances(String setid)
		{
			List<AsCompliance> al=new ArrayList<AsCompliance>();
			try
			{
				al=asComRep.loadAllCompliances(Integer.parseInt(setid));
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
				
			}
			
			return al;
		}
	 
	 public String editCompliance(AsCompliance as,String userCode)
     {
	
		 AsCompliance saved=null;
	     String str = null;
			
	try
	{
		
		
		
		AsCompliance saved1=asComRep.findById(as.getComplId()).orElse(null);
		if(saved1!=null)
		{
			saved1.setComplianceJustification(as.getComplianceJustification());
			saved1.setComplianceJustificationRemarks(as.getComplianceJustificationRemarks());
			saved1.setComplianceJustifiedBy(userCode);
			saved1.setComplianceJustifiedDate(new java.util.Date());
			saved1.setDeviation(as.getDeviation());
			saved=asComRep.save(saved1);
			if(saved!=null)
			{
				str="Compliance to Administrative Sanction details are successfully edited";
			}
			else
			{
				str="Error in editing Details";
			}
		}
		
	
	}
	catch(Exception e)
	{
		System.out.println("Exception e");
		str=e.getMessage();
	}
	return str;
}
}
