package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComplCheckListService {
	@Autowired
	ComplCheckLisRep compRep;
	
	 public String addProceduralcompliance(ComplCheckList as,String userCode)
     {
	
		 ComplCheckList saved=null;
	     String str = null;
			
	try
	{
		
		
		int setId=as.getAc().getSetId();
		int existinrecord1=compRep.findActivityRecordByDes(setId,as.getComplDesc());
		if(existinrecord1==0)
		{
		
		  
		saved=compRep.save(as);
		if(saved!=null)
		{
			str="successfully Procedural Requirement details";
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
	 
	 public String editProceduralcompliance(ComplCheckList as,String userCode)
     {
	
		 ComplCheckList saved=null;
	     String str = null;
			
	try
	{
		
		
		int checkId=as.getCheckId();
	  saved=compRep.findById(checkId).orElse(null);
	  saved.setComplDesc(as.getComplDesc());
	  saved.setIncluded(as.getIncluded());
		  
	  ComplCheckList saved1=compRep.save(saved);
		if(saved1!=null)
		{
			str="successfully Procedural Requirement details are edited";
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
	 
	 
	 public List<ComplCheckList> viewComplianceToProceduralRequirements(String setid)
		{
			List<ComplCheckList> al=new ArrayList<ComplCheckList>();
			try
			{
				al=compRep.viewComplianceToProceduralRequirements(Integer.parseInt(setid));
				
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
				
			}
			
			return al;
		}
	 

}
