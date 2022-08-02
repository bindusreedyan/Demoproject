package NUALS.AMS.EMS.EMPLOYEE_DEPENDENTS_DETAILS;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_MasterService;
import NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS.Employee_Personal;


@Service
public class Employee_DependentsService 
{
	@Autowired
	Employee_DependentsRepository dependentsRepository;
	
	
	@Autowired
	Employee_MasterService emplMasterServ;
	
	
	public List<Employee_Dependents> getAllEmplDependentsDetails()
	{		
		List<Employee_Dependents> allEmplmntTypes =  (List<Employee_Dependents>) dependentsRepository.findAll();
		return allEmplmntTypes;
	}
	
	public List<FORM_FIELDS> getAllEmplDependentsDetailsWithName()
	{		
		List<FORM_FIELDS>  data = new ArrayList<FORM_FIELDS>();
		
		List<Employee_Dependents> dependents =  (List<Employee_Dependents>) dependentsRepository.findAll();
		
		
		ListIterator<Employee_Dependents>  itr =  dependents.listIterator();
		
		while(itr.hasNext())
		{
			Employee_Dependents ed = itr.next();
			
			FORM_FIELDS ff= new FORM_FIELDS();
			EMP_DEPENDENTS_PKEY pkey = ed.getEmp_dependents_id();
			
			ff.setEmp_id(pkey.getEmp_id());
			Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(pkey.getEmp_id());
			ff.setEmp_name(em.getEmp_name());
			ff.setEmp_dependent_name(pkey.getEmp_dependent_name());
			ff.setEmp_dependent_dob(ed.getEmp_dependent_dob());
			ff.setEmp_dependent_mobile(ed.getEmp_dependent_mobile());
			ff.setEmp_dependent_email_id(ed.getEmp_dependent_email_id());
			ff.setEmp_dependents_ident_type(ed.getEmp_dependents_ident_type());
			ff.setEmp_dependents_ident_no(ed.getEmp_dependents_ident_no());
			ff.setStatus(ed.getEmpdepstatus());
			
			data.add(ff);
			
			
		}
		
			
		
		return data;
	}
	
	public List<FORM_FIELDS> getAllEmplDependentsDetailsByMultipleStatus(List<String> sts)
	{		
		List<FORM_FIELDS>  data = new ArrayList<FORM_FIELDS>();
		
		List<Employee_Dependents> dependents =  (List<Employee_Dependents>) dependentsRepository.findByMultipleStatuses(sts);
		
		
		ListIterator<Employee_Dependents>  itr =  dependents.listIterator();
		
		while(itr.hasNext())
		{
			Employee_Dependents ed = itr.next();
			
			FORM_FIELDS ff= new FORM_FIELDS();
			EMP_DEPENDENTS_PKEY pkey = ed.getEmp_dependents_id();
			
			ff.setEmp_id(pkey.getEmp_id());
			Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(pkey.getEmp_id());
			ff.setEmp_name(em.getEmp_name());
			ff.setEmp_dependent_name(pkey.getEmp_dependent_name());
			ff.setEmp_dependent_dob(ed.getEmp_dependent_dob());
			ff.setEmp_dependent_mobile(ed.getEmp_dependent_mobile());
			ff.setEmp_dependent_email_id(ed.getEmp_dependent_email_id());
			ff.setEmp_dependents_ident_type(ed.getEmp_dependents_ident_type());
			ff.setEmp_dependents_ident_no(ed.getEmp_dependents_ident_no());
			ff.setStatus(ed.getEmpdepstatus());
			
			data.add(ff);
			
			
		}
		
			
		
		return data;
	}
	
	
	
	
	public FORM_FIELDS getEmplDependentsDetailsById(String empid,String depName)
	{		
		
		EMP_DEPENDENTS_PKEY pkey = new EMP_DEPENDENTS_PKEY();
		pkey.setEmp_id(empid);
		pkey.setEmp_dependent_name(depName);
		
		Employee_Dependents dependent =   dependentsRepository.findById(pkey).get();
		
		FORM_FIELDS ff= new FORM_FIELDS();
		
		ff.setEmp_id(empid);
		Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(pkey.getEmp_id());
		ff.setEmp_name(em.getEmp_name());
		ff.setEmp_dependent_name(depName);
		ff.setEmp_dependent_dob(dependent.getEmp_dependent_dob());
		ff.setEmp_dependent_mobile(dependent.getEmp_dependent_mobile());
		ff.setEmp_dependent_email_id(dependent.getEmp_dependent_email_id());
		ff.setEmp_dependents_ident_type(dependent.getEmp_dependents_ident_type());
		ff.setEmp_dependents_ident_no(dependent.getEmp_dependents_ident_no());
		ff.setStatus(dependent.getEmpdepstatus());
		
		
		return ff;
	}
	
	
	
	
    
	public Employee_Dependents addEmplDependentsDetails(Employee_Dependents emplmntT)
	{
		Employee_Dependents saved = null;
		
		
		
		try
		{
			
			 BigDecimal roleRowid = dependentsRepository.getRowId();
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
			
			
			Employee_Dependents dup = dependentsRepository.findById(emplmntT.getEmp_dependents_id()).orElse(null);
			
			if(dup == null)
			{
				 saved = dependentsRepository.save(emplmntT);
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
	
	
	public String depL1Check(Employee_Dependents crs)
	{
		Employee_Dependents saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Dependents cdata = dependentsRepository.findById(crs.getEmp_dependents_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setEmpdepstatus(crs.getEmpdepstatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = dependentsRepository.save(cdata);
				
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
	
	
	public Employee_Dependents updateEmplDependentsDetails(Employee_Dependents desgn)
	{
		Employee_Dependents saved = null;
		String msg ="";
		
		try
		{
			Employee_Dependents dup = dependentsRepository.findById(desgn.getEmp_dependents_id()).orElse(null);
			
			if(dup != null)
			{
				
				dup.setDeprelation(desgn.getDeprelation());
				dup.setDepgender(desgn.getDepgender());
				
				dup.setEmp_dependent_dob(desgn.getEmp_dependent_dob());
				dup.setEmp_dependent_mobile(desgn.getEmp_dependent_mobile());
				dup.setEmp_dependent_email_id(desgn.getEmp_dependent_email_id());
				dup.setEmp_dependents_ident_type(desgn.getEmp_dependents_ident_type());
				dup.setEmp_dependents_ident_no(desgn.getEmp_dependents_ident_no());
				dup.setEmpdepstatus(desgn.getEmpdepstatus());
							
						
				
				dup.setEmpdepstatus(desgn.getEmpdepstatus());
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				System.out.println("Employee personal details updating: "+dup.toString());
				
				saved = dependentsRepository.save(dup);
				
				
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
	
	public String approveDependent(Employee_Dependents desgn)
	{
		Employee_Dependents saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Dependents dup = dependentsRepository.findById(desgn.getEmp_dependents_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setEmpdepstatus(desgn.getEmpdepstatus());
				
				 saved = dependentsRepository.save(dup);
				 
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
