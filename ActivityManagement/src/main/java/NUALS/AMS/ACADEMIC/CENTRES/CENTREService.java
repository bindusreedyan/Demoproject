package NUALS.AMS.ACADEMIC.CENTRES;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;

@Service
public class CENTREService 
{
	@Autowired
	CENTRERepository centreRepository;
	
	public List<CENTRE> getAllCentres()
	{		
		List<CENTRE> allAccounts =  (List<CENTRE>) centreRepository.findAll();
		return allAccounts;
	}
	
	public List<CENTRE> getAllCentresByStatus(String sts)
	{		
		List<CENTRE> allAccounts =  (List<CENTRE>) centreRepository.findByCentrestatus(sts);
		return allAccounts;
	}
	
	public List<CENTRE> getAllCentresByMultipleStatus(List<String> sts)
	{		
		List<CENTRE> allAccounts =  (List<CENTRE>) centreRepository.findByMultipleCentrestatus(sts);
		return allAccounts;
	}
    
	public CENTRE addCentre(CENTRE dpt)
	{
		CENTRE saved = null;
		
		
		try
		{

			 BigDecimal roleRowid = centreRepository.getRowId();
			 if(roleRowid == null)
			 {
				 roleRowid = new BigDecimal(0);
			 }
			 else
			 {
				 roleRowid=roleRowid.add(new BigDecimal(1));
			 }
			 
			 int rowid = roleRowid.intValue();
			 dpt.setRowid(rowid);
			
			
			CENTRE dup = centreRepository.findByCentre_code(dpt.getCentre_code());
			
			if(dup == null)
			{
				 saved = centreRepository.save(dpt);
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
	
	
	/*
	public String DeptL1Check(CENTRE crs)
	{
		CENTRE saved = null;
		
		String msg ="";
		
		try
		{
			CENTRE cdata = centreRepository.findById(crs.getDept_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setDepstatus(crs.getDepstatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = centreRepository.save(cdata);
				
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
	
	public CENTRE updateDpartment(CENTRE desgn)
	{
		CENTRE saved = null;
		
		try
		{
			CENTRE dup = centreRepository.findById(desgn.getDept_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setDept_name(desgn.getDept_name());
				dup.setDescription(desgn.getDescription());
								
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				
				 saved = centreRepository.save(dup);
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
	
	
	public CENTRE approveDept(CENTRE desgn)
	{
		CENTRE saved = null;
		
		String msg ="";
		
		try
		{
			CENTRE dup = centreRepository.findById(desgn.getDept_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setDepstatus(desgn.getDepstatus());
				
				 saved = centreRepository.save(dup);
				 
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
	*/

}
