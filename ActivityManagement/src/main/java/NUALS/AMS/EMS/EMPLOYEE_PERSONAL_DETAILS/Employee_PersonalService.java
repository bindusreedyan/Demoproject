package NUALS.AMS.EMS.EMPLOYEE_PERSONAL_DETAILS;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_Master;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_MasterRepository;
import NUALS.AMS.EMS.EMPLOYEE_MASTER.Employee_MasterService;

@Service
public class Employee_PersonalService 
{
	@Autowired
	Employee_PersonalRepository emplPersRepository;
	
	@Autowired
	Employee_MasterService emplMasterServ;
	
	public List<FORM_FIELDS> getAllEmplPersonalDetails()
	{		
		List<Employee_Personal> allEmplmntTypes =  (List<Employee_Personal>) emplPersRepository.findAll();
		
		List<FORM_FIELDS>  data = new ArrayList<FORM_FIELDS>();
		
		ListIterator<Employee_Personal>  itr =  allEmplmntTypes.listIterator();
		
		while(itr.hasNext())
		{
			Employee_Personal ep = itr.next();
			
			FORM_FIELDS ff= new FORM_FIELDS();
			
			
			ff.setEmp_id(ep.getEmp_id());
			Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(ep.getEmp_id());
			ff.setEmp_name(em.getEmp_name());
			ff.setEmp_dob(ep.getEmp_dob());
			ff.setEmp_address(ep.getEmp_address());			
			ff.setEmp_state(ep.getEmp_state());
			ff.setEmp_district(ep.getEmp_district());
			ff.setEmp_city(ep.getEmp_city());			
			ff.setEmp_city_pincode(ep.getEmp_city_pincode());			
			ff.setEmp_mobile_land(ep.getEmp_mobile_land());			
			ff.setEmp_email_id(ep.getEmp_email_id());			
			ff.setEmp_ident_type(ep.getEmp_ident_type());
			ff.setEmp_ident_no(ep.getEmp_ident_no());
			ff.setPersonalstatus(ep.getPersonalstatus());
			
			data.add(ff);
			
			
		}
		
		
		return data;
	}
	
	public List<FORM_FIELDS> getAllEmplPersonalDetailsByMultipleStatus(List<String> sts)
	{		
		List<Employee_Personal> allEmplmntTypes =  (List<Employee_Personal>) emplPersRepository.findByMultipleStatuses(sts);
		
		List<FORM_FIELDS>  data = new ArrayList<FORM_FIELDS>();
		
		ListIterator<Employee_Personal>  itr =  allEmplmntTypes.listIterator();
		
		while(itr.hasNext())
		{
			Employee_Personal ep = itr.next();
			
			FORM_FIELDS ff= new FORM_FIELDS();
			
			
			ff.setEmp_id(ep.getEmp_id());
			Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(ep.getEmp_id());
			ff.setEmp_name(em.getEmp_name());
			ff.setEmp_dob(ep.getEmp_dob());
			ff.setEmp_address(ep.getEmp_address());			
			ff.setEmp_state(ep.getEmp_state());
			ff.setEmp_district(ep.getEmp_district());
			ff.setEmp_city(ep.getEmp_city());			
			ff.setEmp_city_pincode(ep.getEmp_city_pincode());			
			ff.setEmp_mobile_land(ep.getEmp_mobile_land());			
			ff.setEmp_email_id(ep.getEmp_email_id());			
			ff.setEmp_ident_type(ep.getEmp_ident_type());
			ff.setEmp_ident_no(ep.getEmp_ident_no());
			ff.setPersonalstatus(ep.getPersonalstatus());
			
			data.add(ff);
			
			
		}
		
		
		return data;
	}
	
	
	
	public FORM_FIELDS getEmplPersonalDetailsById(String empid)
	{		
		Employee_Personal ep =  emplPersRepository.findById(empid).get();
		
		FORM_FIELDS ff= new FORM_FIELDS();
			
			
			ff.setEmp_id(ep.getEmp_id());
			Employee_Master em = emplMasterServ.getEmplmntDetByEmpid(ep.getEmp_id());
			ff.setEmp_name(em.getEmp_name());
			ff.setEmp_dob(ep.getEmp_dob());
			ff.setEmp_address(ep.getEmp_address());			
			ff.setEmp_state(ep.getEmp_state());
			ff.setEmp_district(ep.getEmp_district());
			ff.setEmp_city(ep.getEmp_city());			
			ff.setEmp_city_pincode(ep.getEmp_city_pincode());			
			ff.setEmp_mobile_land(ep.getEmp_mobile_land());			
			ff.setEmp_email_id(ep.getEmp_email_id());			
			ff.setEmp_ident_type(ep.getEmp_ident_type());
			ff.setEmp_ident_no(ep.getEmp_ident_no());
			ff.setPersonalstatus(ep.getPersonalstatus());
			
		return ff;
	}
    
	public Employee_Personal addEmplPersonalDetails(Employee_Personal emplmntT)
	{
		Employee_Personal saved = null;
		try
		{
			 BigDecimal roleRowid = emplPersRepository.getRowId();
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
			
			
			
			Employee_Personal dup = emplPersRepository.findById(emplmntT.getEmp_id()).orElse(null);
			
			if(dup == null)
			{
				 saved = emplPersRepository.save(emplmntT);
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
	
	
	public String personalL1Check(Employee_Personal crs)
	{
		Employee_Personal saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Personal cdata = emplPersRepository.findById(crs.getEmp_id()).orElse(null);
			
			if(cdata != null)
			{
				cdata.setPersonalstatus(crs.getPersonalstatus());
				cdata.setLevel1Checker(crs.getLevel1Checker());
				cdata.setLevel1CheckDate(crs.getLevel1CheckDate());
				cdata.setLevel1Remarks(crs.getLevel1Remarks());
				
				saved = emplPersRepository.save(cdata);
				
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
	
	public Employee_Personal updatePersonal(Employee_Personal desgn)
	{
		Employee_Personal saved = null;
		
		try
		{
			Employee_Personal dup = emplPersRepository.findById(desgn.getEmp_id()).orElse(null);
			
			if(dup != null)
			{
							
				dup.setEmp_dob(desgn.getEmp_dob());
				dup.setEmp_address(desgn.getEmp_address());
				dup.setEmp_state(desgn.getEmp_state());
				dup.setEmp_district(desgn.getEmp_district());				
				dup.setEmp_mobile_land(desgn.getEmp_mobile_land());				
				dup.setEmp_email_id(desgn.getEmp_email_id());
				dup.setEmp_ident_type(desgn.getEmp_ident_type());
				dup.setEmp_ident_no(desgn.getEmp_ident_no());
				dup.setEmp_city(desgn.getEmp_city());
				dup.setEmp_city_pincode(desgn.getEmp_city_pincode());			
				
				dup.setPersonalstatus(desgn.getPersonalstatus());
				dup.setUpdateBy(desgn.getUpdateBy());
				dup.setUpdateRemarks(desgn.getUpdateRemarks());
				
				System.out.println("Employee personal details updating: "+dup.toString());
				
				saved = emplPersRepository.save(dup);
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
	
	public String approvePersonal(Employee_Personal desgn)
	{
		Employee_Personal saved = null;
		
		String msg ="";
		
		try
		{
			Employee_Personal dup = emplPersRepository.findById(desgn.getEmp_id()).orElse(null);
			
			if(dup != null)
			{
				dup.setApprovedBy(desgn.getApprovedBy());
				dup.setApprovedDate(desgn.getApprovedDate());
				dup.setApprovedRemarks(desgn.getApprovedRemarks());
				
				dup.setPersonalstatus(desgn.getPersonalstatus());
				
				 saved = emplPersRepository.save(dup);
				 
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
