package NUALS.AMS.EMS.EMPLOYMENT_TYPE;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS.Employee_Personal;

@Service
public class Employment_TypeService 
{
	@Autowired
	Employment_TypeRepository emplmntRepository;
	
	public List<Employment_Type> getAllEmplmntTypes()
	{		
		List<Employment_Type> allEmplmntTypes =  (List<Employment_Type>) emplmntRepository.findAll();
		return allEmplmntTypes;
	}
	
	public List<Employment_Type> getAllEmplmntTypes(List<String> sts)
	{		
		List<Employment_Type> allEmplmntTypes =  (List<Employment_Type>) emplmntRepository.findByMultipleDepstatus(sts);
		return allEmplmntTypes;
	}
    
	public Employment_Type addEmplmntType(Employment_Type emplmntT)
	{
		Employment_Type saved = null;
		
		
		try
		{
			 BigDecimal roleRowid = emplmntRepository.getRowId();
			 if(roleRowid == null)
			 {
				 roleRowid = new BigDecimal(0);
			 }
			 else
			 {
				 roleRowid=roleRowid.add(new BigDecimal(1));
			 }
			 
			 int rowid = roleRowid.intValue();
			 emplmntT.setRowid(rowid);
			
			
			
			 Employment_Type dup = emplmntRepository.findByEmplmnt_type_Name(emplmntT.getEmplmnt_type_Name());
			
			if(dup == null)
			{
				 saved = emplmntRepository.save(emplmntT);
			}
			else
			{
				
			}
			
		}
		catch(Exception ex)
		{
			
		}
		
		
		
		return saved;
	}
	
	
	public String emplmntL1Check(Employment_Type crs)
	{
		Employment_Type saved = null;
		
		String msg ="";
		
		try
		{
			Employment_Type cdata = emplmntRepository.findById(crs.getEmplmnt_type_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setEmplmnttypestatus(crs.getEmplmnttypestatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = emplmntRepository.save(cdata);
				
				if(saved != null)
				{
					msg = "SAVED";
				}
				else
				{
					msg = "NOTSAVED";
				}
				
			}
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception: "+ex.getMessage());
		}
		
		return msg;
	}
	
	public Employment_Type updateEmplmntType(Employment_Type desgn)
	{
		Employment_Type saved = null;
		
		try
		{
			Employment_Type dup = emplmntRepository.findById(desgn.getEmplmnt_type_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setEmplmnt_type_Name(desgn.getEmplmnt_type_Name());
				dup.setEmplmnt_type_description(desgn.getEmplmnt_type_description());
								
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				
				 saved = emplmntRepository.save(dup);
			}
			else
			{
				System.out.println("not null loop...");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Execption at updateDesignation: "+ex.getMessage());
		}
			
		
		return saved;
	}
	
	public Employment_Type approveEmplmntType(Employment_Type desgn)
	{
		Employment_Type saved = null;
		
		String msg ="";
		
		try
		{
			Employment_Type dup = emplmntRepository.findById(desgn.getEmplmnt_type_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setEmplmnttypestatus(desgn.getEmplmnttypestatus());
				
				 saved = emplmntRepository.save(dup);
				 
				 msg="SAVED";
			}
			else
			{
				msg="NOTSAVED";
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at approveDesign : "+ex.getMessage());
		}
			
		
		return saved;
	}
}
