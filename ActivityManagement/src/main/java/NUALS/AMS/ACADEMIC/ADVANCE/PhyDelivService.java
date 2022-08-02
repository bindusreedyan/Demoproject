package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhyDelivService {
	
	@Autowired
	PhyDelivRep phrep;
	
	 public String physicalDelivarableDetails(PhyDeliv as,String userCode)
     {
	
	PhyDeliv saved=null;
	String str = null;
			
	try
	{
		
	    as.setEnteredBy(userCode);
	    as.setEnteredDate(new java.util.Date());
		saved=phrep.save(as);
		if(saved!=null)
		{
			str="successfully Inserted physical Deliverable Details";
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
		public List<PhyDeliv> loadAllphysicalDelivarablesDetails(String setid)
		{
			List<PhyDeliv> al=new ArrayList<PhyDeliv>();
			try
			{
				al=phrep.loadAllphysicalDelivarablesDetails(Integer.parseInt(setid));
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
				
			}
			
			return al;
		}
		
		
	
		public int editPhysicalDelivarableDetails(String expid,String recom,String recomRemarks)
		{
			int result=0;
			try
			{
				 result=phrep.editPhysicalDelivarableDetails(Integer.parseInt(expid),recom,recomRemarks);
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
				
			}
			
			return result;
		}
	 
}
