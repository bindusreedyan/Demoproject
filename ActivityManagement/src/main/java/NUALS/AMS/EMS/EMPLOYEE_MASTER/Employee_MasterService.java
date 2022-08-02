package NUALS.AMS.EMS.EMPLOYEE_MASTER;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.DEPARTMENTS.DEPARTMENT;
import NUALS.AMS.EMS.DESIGNATIONS.DESIGNATION;

@Service
public class Employee_MasterService 
{
	@Autowired
	Employee_MasterRepository emplmasterRepository;
	
	public List<Employee_Master> getAllEmplMasterDetails()
	{		
		List<Employee_Master> allEmplmntTypes =  (List<Employee_Master>) emplmasterRepository.findAll();
		return allEmplmntTypes;
	}
	
	public List<Employee_Master> getAllByMultipleStatuses(List<String> sts)
	{		
		List<Employee_Master> allEmplmntTypes = null;
		allEmplmntTypes = (List<Employee_Master>) emplmasterRepository.findByMultipleStatuses(sts);
		return allEmplmntTypes;
	}
	
	public Employee_Master getEmplmntDetByEmpid(String empid)
	{		
		Employee_Master emplmntDet =  emplmasterRepository.findById(empid).get();
		System.out.println(emplmntDet.toString());
		return emplmntDet;
	}
	
	public List<Employee_Master> getAllFacultyMasterDetails()
	{		
		List<Employee_Master> allEmplmntTypes =  (List<Employee_Master>) emplmasterRepository.findByemp_work_type("Teaching");
		return allEmplmntTypes;
	}
    
    
	public Employee_Master addEmplMasterDetails(Employee_Master emplmntT)
	{
		Employee_Master saved = null;
		
		try
		{
			 BigDecimal roleRowid = emplmasterRepository.getRowId();
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
			
			
			Employee_Master dup = emplmasterRepository.findById(emplmntT.getEmp_id()).orElse(null);
			
			if(dup == null)
			{
				 saved = emplmasterRepository.save(emplmntT);
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
	public List<LOGINS_CLASS> getAllLogins()
	{		
		List<LOGINS_CLASS> allLogins =  (List<LOGINS_CLASS>) emplmasterRepository.findAllLogins();
		return allLogins;
	}
    */
	
	public List<LOGINS_INTERFACE> getAllLogins()
	{		
		List<LOGINS_INTERFACE> allLogins =  (List<LOGINS_INTERFACE>) emplmasterRepository.findAllLogins();
		return allLogins;
	}
	
	public String emplmntL1Check(Employee_Master crs)
	{
		Employee_Master saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Master cdata = emplmasterRepository.findById(crs.getEmp_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setEmpmasterstatus(crs.getEmpmasterstatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = emplmasterRepository.save(cdata);
				
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
	
	
	public Employee_Master updateEmplMaster(Employee_Master desgn)
	{
		Employee_Master saved = null;
		
		try
		{
			Employee_Master dup = emplmasterRepository.findById(desgn.getEmp_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setEmp_name(desgn.getEmp_name());
				dup.setGender(desgn.getGender());
				dup.setDesignation(desgn.getDesignation());
				dup.setEmp_joining_dept(desgn.getEmp_joining_dept());
				dup.setJoining_date(desgn.getJoining_date());
				dup.setEmp_pan_no(desgn.getEmp_pan_no());
				dup.setEmpl_appoint_type(desgn.getEmpl_appoint_type());
				dup.setEmp_work_type(desgn.getEmp_work_type());
				
				dup.setEmpmasterstatus(desgn.getEmpmasterstatus());
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				System.out.println("Employee master details updating: "+dup.toString());
				
				saved = emplmasterRepository.save(dup);
			}
			else
			{
				System.out.println("not null loop...");
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Execption at updateEmplMaster: "+ex.getMessage());
		}
			
		
		return saved;
	}
	
	
	public String approveEmplMaster(Employee_Master desgn)
	{
		Employee_Master saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Master dup = emplmasterRepository.findById(desgn.getEmp_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setEmpmasterstatus(desgn.getEmpmasterstatus());
				
				 saved = emplmasterRepository.save(dup);
				 
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
			
		
		return msg;
	}
	
	
	

}
