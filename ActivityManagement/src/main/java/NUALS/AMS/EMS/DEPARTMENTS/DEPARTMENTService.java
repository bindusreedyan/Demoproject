package NUALS.AMS.EMS.DEPARTMENTS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;

@Service
public class DEPARTMENTService 
{
	@Autowired
	DEPARTMENTRepository deptRepository;
	
	public List<DEPARTMENT> getAllDepartments()
	{		
		List<DEPARTMENT> allAccounts =  (List<DEPARTMENT>) deptRepository.findAll();
		return allAccounts;
	}
	
	public List<DEPARTMENT> getAllDepartmentsByStatus(String sts)
	{		
		List<DEPARTMENT> allAccounts =  (List<DEPARTMENT>) deptRepository.findByDepstatus(sts);
		return allAccounts;
	}
	
	public List<DEPARTMENT> getAllDepartmentsByMultipleStatus(List<String> sts)
	{		
		List<DEPARTMENT> allAccounts =  (List<DEPARTMENT>) deptRepository.findByMultipleDepstatus(sts);
		return allAccounts;
	}
    
	public DEPARTMENT addDepartment(DEPARTMENT dpt)
	{
		DEPARTMENT saved = null;
		
		
		try
		{

			 BigDecimal roleRowid = deptRepository.getRowId();
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
			
			
			DEPARTMENT dup = deptRepository.findByDept_name(dpt.getDept_name());
			
			if(dup == null)
			{
				 saved = deptRepository.save(dpt);
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
	
	
	
	public String DeptL1Check(DEPARTMENT crs)
	{
		DEPARTMENT saved = null;
		
		String msg ="";
		
		try
		{
			DEPARTMENT cdata = deptRepository.findById(crs.getDept_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setDepstatus(crs.getDepstatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = deptRepository.save(cdata);
				
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
	
	public DEPARTMENT updateDpartment(DEPARTMENT desgn)
	{
		DEPARTMENT saved = null;
		
		try
		{
			DEPARTMENT dup = deptRepository.findById(desgn.getDept_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setDept_name(desgn.getDept_name());
				dup.setDescription(desgn.getDescription());
								
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				
				 saved = deptRepository.save(dup);
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
	
	
	public DEPARTMENT approveDept(DEPARTMENT desgn)
	{
		DEPARTMENT saved = null;
		
		String msg ="";
		
		try
		{
			DEPARTMENT dup = deptRepository.findById(desgn.getDept_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setDepstatus(desgn.getDepstatus());
				
				 saved = deptRepository.save(dup);
				 
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
