package NUALS.AMS.ACADEMIC.ACTIVITY_TYPES;


import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class AcademicActivityTypeService {
	
	@Autowired
	AcademicActivityTypeRepository activityTypeRepository;
	public AcademicActivityTypes saveActivityType(AcademicActivityTypes actvtytype)
	{
		AcademicActivityTypes saved = null;
		
		
		
		try
		{
			String des=actvtytype.getActivity_type_description();
			System.out.println("description"+des);
			int actvtytypecode=actvtytype.getActivityTypeCode();
			//int existinrecord=activityTypeRepository.findActivityTypeRecord(actvtytypecode);
			int existinrecord1=activityTypeRepository.findActivityTypeRecordByDes(des);
			if(existinrecord1==0)
			{

		 saved=activityTypeRepository.save(actvtytype);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public List<AcademicActivityTypes> getAllActiivityTypes()
	{
		List<AcademicActivityTypes> activityTypes = new ArrayList<AcademicActivityTypes>();
		activityTypeRepository.getAllActivityTypeSubmitted().forEach(activityTypes::add);
		return activityTypes;
	}
	
	public List<AcademicActivityTypes> getAllActivityTypesbyVerified()
	{
		List<AcademicActivityTypes> activityTypes = new ArrayList<AcademicActivityTypes>();
		activityTypeRepository.getAllActivityVerified().forEach(activityTypes::add);
		return activityTypes;
	}
	
	
	public AcademicActivityTypes updateActivityType(AcademicActivityTypes aat)
	{
		AcademicActivityTypes saved = null;
		
		try
		{
			System.out.println("entrrr in updateActivityType"+aat.getActivityTypeCode()+aat.getActivity_type_description()+"nn"+aat.getVerifiedBy()+"bb"+aat.getVerifiedRemarks());
			int actvtytypecode=aat.getActivityTypeCode();
			AcademicActivityTypes dup =	activityTypeRepository.findActivityTypeByActivityTypeCode(actvtytypecode);
			System.out.println("dupppp"+dup.getActivityTypeCode());
			dup.setActivity_type_description(aat.getActivity_type_description());
			dup.setVerifiedBy(aat.getVerifiedBy());
			dup.setVerifiedRemarks(aat.getVerifiedRemarks());
			dup.setVerifiedDate(aat.getVerifiedDate());
			dup.setActivity_type_status("verified");
			
		
			//dup.setUpdateDateTime(new LocalDateTime());
				 saved = activityTypeRepository.save(dup);
				 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	public AcademicActivityTypes approveActivityType(AcademicActivityTypes aat)
	{
		AcademicActivityTypes saved = null;
		
		try
		{
			System.out.println("entrrr in approve"+aat.getActivityTypeCode()+aat.getActivity_type_description()+"nn"+aat.getVerifiedBy()+"bb"+aat.getVerifiedRemarks());
			int actvtytypecode=aat.getActivityTypeCode();
			AcademicActivityTypes dup =	activityTypeRepository.findActivityTypeByActivityTypeCode(actvtytypecode);
			System.out.println("dupppp"+dup.getActivityTypeCode());
			dup.setActivity_type_description(aat.getActivity_type_description());
			dup.setApprovedBy(aat.getApprovedBy());
			dup.setApprovedRemarks(aat.getApprovedRemarks());
			dup.setApprovedDate(aat.getApprovedDate());
			dup.setActivity_type_status("approved");
			
		
			//dup.setUpdateDateTime(new LocalDateTime());
				 saved = activityTypeRepository.save(dup);
				 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	public List<AcademicActivityTypes> getAllActivityTypesbyApproved()
	{
		List<AcademicActivityTypes> activityTypes = new ArrayList<AcademicActivityTypes>();
		activityTypeRepository.getAllActivityApproved().forEach(activityTypes::add);
		return activityTypes;
	}
	
	
	public AcademicActivityTypes deactivatActivityType(AcademicActivityTypes aat)
	{
		AcademicActivityTypes saved = null;
		
		try
		{
			System.out.println("entrrr in deactivate"+aat.getActivityTypeCode()+aat.getActivity_type_description()+"nn"+aat.getVerifiedBy()+"bb"+aat.getVerifiedRemarks());
			int actvtytypecode=aat.getActivityTypeCode();
			AcademicActivityTypes dup =	activityTypeRepository.findActivityTypeByActivityTypeCode(actvtytypecode);
			System.out.println("dupppp"+dup.getActivityTypeCode());
			
			dup.setDeactivateBy(aat.getDeactivateBy());
			dup.setDeactivateRemarks(aat.getDeactivateRemarks());
			dup.setDeactivateDate(new Date());
			dup.setActivity_type_status("deactivate");
			
		
			//dup.setUpdateDateTime(new LocalDateTime());
				 saved = activityTypeRepository.save(dup);
				 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	public List<AcademicActivityTypes> getAllActivityTypeByCatery(String category)
	{
		
		List<AcademicActivityTypes> al=new ArrayList<AcademicActivityTypes>();
		String status="submitted";
		try
		{
			al=activityTypeRepository.getAllActivityTypeByCategry(category,status);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getAllActivityTypeByCatery"+e);
		}
		return al;
	}

}

