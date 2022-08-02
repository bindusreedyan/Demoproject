package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypeRepository;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
@Service
public class ActivityMasterService {
	@Autowired
	ActivityMasterRepository erep;
	@Autowired
	AcademicActivityTypeRepository activityTypeRepository;
	
	@Autowired
	OrderGenerationRep or;
	
	@Autowired
	ActivityFinanceRepository afr;
	
	
	@Autowired
	ActivityCenterRepository acr;
	
	@Autowired
	ActivityCenterService acs;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public String saveActivityMaster(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		String msg = null;
		
		int existinrecord1=erep.findActivityRecordByDes(am.getTitle());
		System.out.println("count of records"+existinrecord1);
		
		if(existinrecord1==0)
		{
		
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			saved=new ActivityMaster();
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(am.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			String activityType=aat.getActivityTypeGroup();
			System.out.println(activityType);
			if(activityType.equals("academic"))
			{
				saved.setAdminApprovedNeed("no");
				System.out.println("activityType"+activityType);
			}
			else if(activityType.equals("others"))
			{
				saved.setAdminApprovedNeed("yes");
				System.out.println("activityType"+activityType);
				System.out.println(activityType);
			}
			saved.setAat(aat);
			saved.setTitle(am.getTitle());
			saved.setDateFrom(am.getDateFrom());
			saved.setDateTo(am.getDateTo());
			saved.setFinyear(am.getFinyear());
			saved.setBrochureURL(am.getBrochureURL());
			saved.setDescription(am.getDescription());
			saved.setTargetGroup(am.getTargetGroup());
			saved.setOutcome(am.getOutcome());
			saved.setDeviationJustification(am.getDeviationJustification());
			saved.setType(am.getType());
			saved.setActivityLevel(am.getActivityLevel());
			saved.setFinanceImplied(am.getFinanceImplied());
			saved.setStatus("submitted");
			saved.setEnteredBy(userCode);
			saved.setEnteredDate(new java.util.Date());
			saved.setRemarks(am.getRemarks());
		saved.setCenter1(am.getCenter1());
		saved.setCenter2(am.getCenter2());
		saved.setCenter3(am.getCenter3());
		saved.setCenter4(am.getCenter4());
		saved.setCenter4(am.getCenter5());
		saved.setFacultyCode(am.getFacultyCode());
			saved.setUserType("faculty");
			saved=erep.save(saved);
			
			
			System.out.println("savedddddd"+saved);
			if(saved!=null)
			{
				
				
				msg="activityCode-"+saved.getActivityCode();
			
			}
			else
			{
				msg="Error in adding ActivityMaster Details. Contact Admin";
			}
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("ProgramApprovalAcademic");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityCode()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
	}
		else
		{
			msg="Activity Name is already Exist";
		}
		
		return msg;
	}
	
	public String saveActivityMasterByStudent(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		String msg = null;
		
		int existinrecord1=erep.findActivityRecordByDes(am.getTitle());
		System.out.println("count of records"+existinrecord1);
		
		if(existinrecord1==0)
		{
		
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			saved=new ActivityMaster();
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(am.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			String activityType=aat.getActivityTypeGroup();
			System.out.println(activityType);
			if(activityType.equals("academic"))
			{
				saved.setAdminApprovedNeed("no");
				System.out.println("activityType"+activityType);
			}
			else if(activityType.equals("others"))
			{
				saved.setAdminApprovedNeed("yes");
				System.out.println("activityType"+activityType);
				System.out.println(activityType);
			}
			saved.setAat(aat);
			saved.setTitle(am.getTitle());
			saved.setDateFrom(am.getDateFrom());
			saved.setDateTo(am.getDateTo());
			saved.setFinyear(am.getFinyear());
			saved.setBrochureURL(am.getBrochureURL());
			saved.setDescription(am.getDescription());
			saved.setTargetGroup(am.getTargetGroup());
			saved.setOutcome(am.getOutcome());
			saved.setDeviationJustification(am.getDeviationJustification());
			saved.setType(am.getType());
			saved.setActivityLevel(am.getActivityLevel());
			saved.setFinanceImplied(am.getFinanceImplied());
			saved.setStatus("studentsubmitted");
			saved.setEnteredBy(userCode);
			saved.setEnteredDate(am.getEnteredDate());
			saved.setRemarks(am.getRemarks());
		saved.setCenter1(am.getCenter1());
		saved.setCenter2(am.getCenter2());
		saved.setCenter3(am.getCenter3());
		saved.setCenter4(am.getCenter4());
		saved.setCenter4(am.getCenter5());
		saved.setFacultyCode(am.getFacultyCode());
		saved.setUserType("student");
			saved=erep.save(saved);
			System.out.println("savedddddd"+saved);
			if(saved!=null)
			{
				
				
				msg="activityCode-"+saved.getActivityCode();
			
			}
			else
			{
				msg="Error in adding ActivityMaster Details. Contact Admin";
			}
		
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("ProgramApprovalAcademicDetailsEntry By Student");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityCode()));
			mdtl.setMdtllUserCode(userCode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
	}
		else
		{
			msg="Activity Name is already Exist";
		}
		
		return msg;
	}
		
	public int getCountofAsNo()
	{
		int ascount=erep.getCountofAsNo();
		return(ascount);
	}
	
	public ActivityMaster updateActivityMasterAsno(String asno,int activityId)
	{
		ActivityMaster am=null;
		
		try
		{
			
			am=erep.findActivityByActivityCode(activityId);
			am.setAsNO(asno);
			am.setAsOrdersIssued("issued");
			
		    erep.save(am);
			
		}
		catch(Exception e)
		{
			
		}
		
		return am;
		
	}
	
	public ActivityMaster updateBrochureUrl(int activityId,String filename)
	{
		ActivityMaster am=null;
		
		try
		{
			
			am=erep.findActivityByActivityCode(activityId);
			am.setBrochureURL(filename);
		    erep.save(am);
			
		}
		catch(Exception e)
		{
			System.out.println("exception in upload brochure"+e);
		}
		return am;
		
	}
	
	
	public ActivityFinance updateAnnexureUrl(int activityId,String filename)
	{
		ActivityFinance af=null;
	
			
			
			try
			{
				af=afr.getActivityFinanceByActivityId(activityId);
				af.setAnnexureFile(filename);
			    afr.save(af);
			}
			catch(Exception e)
			{
				System.out.println("error"+e);
			}
		

	
		return af;
		
	}
	
	
	public List<ActivityMaster> getAllActivitiesApproved()
	{
		
		List<ActivityMaster> al=null;
		
		try
		{
			
		
			al=erep.findAllActivityApproved();
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	public List<ActivityMaster> getAllActivitiesByStatus(String status)
	{
		System.out.println("statussssss"+status);
		List<ActivityMaster> al=null;
		
		try
		{
			
			System.out.println("statussssssssssssssssssssssssssss in service"+status);
			al=erep.findAllActivityByStatus(status);
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	
	
	
	public List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniator(String facultyId,String status1)
	{
		System.out.println("statussssss"+status1);
		List<ActivityMaster> al=null;
		
		
		try
		{
		
		System.out.println("statussssssssssssssssssssssssssss in service"+facultyId+"statussssssssss"+status1);
		//al=erep.getAllVerifiedActivitiesByFacultyCordniator(facultyId,status);
		al=erep.getAllVerifiedActivitiesByFacultyCordniator(facultyId,status1);
		System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			System.out.println("exception e"+e);
		}
		return al;
	}
	public ActivityMaster getAllActivityById(String activityId)
	{
		System.out.println("activityId"+activityId);
		ActivityMaster al=null;
		
		try
		{
			al=erep.findActivityByActivityCode(Integer.parseInt(activityId));
			
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public ActivityMaster academicApproveActivityMaster(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		ActivityMaster saved1=null;
		
		
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			
			saved=new ActivityMaster();
			//ActivityMaster submitted=erep.findActivityByActivityCode(am.getActivityCode());
			
			ActivityMaster submitted=erep.findById(am.getActivityCode()).orElse(null);
			
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(submitted.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			submitted.setAat(am.getAat());
			submitted.setTitle(am.getTitle());
			submitted.setDateFrom(am.getDateFrom());
			submitted.setDateTo(am.getDateTo());
			submitted.setFinyear(am.getFinyear());
		//	submitted.setBrochureURL(am.getBrochureURL());
			submitted.setDescription(am.getDescription());
			submitted.setTargetGroup(am.getTargetGroup());
			submitted.setOutcome(am.getOutcome());
			submitted.setDeviationJustification(am.getDeviationJustification());
			submitted.setType(am.getType());
			submitted.setActivityLevel(am.getActivityLevel());
			submitted.setFinanceImplied(am.getFinanceImplied());
			submitted.setAdminApprovedDate(new java.util.Date());
			submitted.setAdminApprovedNeed("yes");
			//saved.setStatus("submitted");
			//saved.setEnteredBy(userCode);
			//saved.setEnteredDate(submitted.getEnteredDate());
			//saved.setRemarks(submitted.getRemarks());
		//saved.setCenter1(am.getCenter1());
	//	saved.setCenter2(am.getCenter2());
	//	saved.setCenter3(submitted.getCenter3());
	//	saved.setCenter4(submitted.getCenter4());
	//	saved.setCenter4(submitted.getCenter5());
			submitted.setAdminApprovalRemarks(am.getAdminApprovalRemarks());
		
			submitted.setAdminApprovedBy(userCode);
			submitted.setAdminApprovedDate(am.getAdminApprovedDate());
			String fi=submitted.getFinanceImplied();
	
			submitted.setStatus(am.getStatus());
			
	
			
      saved1=erep.save(submitted);
      
      ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("Academic approve");
		mdtl.setMdtlDate(new Date());
		mdtl.setMdtlProcessId(String.valueOf(saved1.getActivityCode()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("academicapproved");
		mdtl.setActivityLog(""+saved.toString());
		mdtlr.save(mdtl);
 
			System.out.println("saved11111111111"+saved1.getStatus());
		
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved1;
	}
	
	public ActivityMaster facultyacademicApproveActivityMaster(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		ActivityMaster saved1=null;
		
		
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			
			saved=new ActivityMaster();
			//ActivityMaster submitted=erep.findActivityByActivityCode(am.getActivityCode());
			
			ActivityMaster submitted=erep.findById(am.getActivityCode()).orElse(null);
			
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(submitted.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			submitted.setAat(am.getAat());
			submitted.setTitle(am.getTitle());
			submitted.setDateFrom(am.getDateFrom());
			submitted.setDateTo(am.getDateTo());
			submitted.setFinyear(am.getFinyear());
		//	submitted.setBrochureURL(am.getBrochureURL());
			submitted.setDescription(am.getDescription());
			submitted.setTargetGroup(am.getTargetGroup());
			submitted.setOutcome(am.getOutcome());
			submitted.setDeviationJustification(am.getDeviationJustification());
			submitted.setType(am.getType());
			submitted.setActivityLevel(am.getActivityLevel());
			submitted.setFinanceImplied(am.getFinanceImplied());
		//	submitted.setAdminApprovedDate(new java.util.Date());
			submitted.setAdminApprovedNeed("yes");
			//saved.setStatus("submitted");
			//saved.setEnteredBy(userCode);
			//saved.setEnteredDate(submitted.getEnteredDate());
			//saved.setRemarks(submitted.getRemarks());
		//saved.setCenter1(am.getCenter1());
	//	saved.setCenter2(am.getCenter2());
	//	saved.setCenter3(submitted.getCenter3());
	//	saved.setCenter4(submitted.getCenter4());
	//	saved.setCenter4(submitted.getCenter5());
		//	submitted.setAdminApprovalRemarks(am.getAdminApprovalRemarks());
			
			//submitted.setRecommendedRemarks(am.getRecommendedRemarks());
			//submitted.setRecommendedBy(userCode);
			//submitted.setRecommendedDate(new java.util.Date());
		
		//	submitted.setAdminApprovedBy(userCode);
		//	submitted.setAdminApprovedDate(am.getAdminApprovedDate());
			String fi=submitted.getFinanceImplied();
	
			//submitted.setStatus(am.getStatus());
			
	
			
      saved1=erep.save(submitted);
      ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
    		mdtl.setMdtlProcessName("Faculty Recommendation of Program Approval");
    		mdtl.setMdtlDate(new Date());
    		mdtl.setMdtlProcessId(String.valueOf(saved1.getActivityCode()));
    		mdtl.setMdtllUserCode(userCode);
    		mdtl.setMdtltlStatus("academicapproved");
    		mdtl.setActivityLog(""+saved.toString());
    		mdtlr.save(mdtl);
 
			System.out.println("saved11111111111"+saved1.getStatus());
		
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public ActivityMaster activityFinalApproval(String activityId,String finalApproval,String userCode)
	{
		ActivityMaster updated=null;
		try
		{
			System.out.println(activityId+"----------"+finalApproval);
			
			ActivityMaster am=erep.findById(Integer.parseInt(activityId)).orElse(null);
			am.setFinalApprovedRemarks(finalApproval);
			am.setFinalApprovedDate(new java.util.Date());
			am.setFinalApprovedBy(userCode);
			am.setStatus("finalapproved");
			am.setAsOrdersIssued("Not Issued");
			updated=erep.save(am);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Final approve");
				mdtl.setMdtlDate(new Date());
				mdtl.setMdtlProcessId(String.valueOf(updated.getActivityCode()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("final approved");
				mdtl.setActivityLog(""+updated.toString());
				mdtlr.save(mdtl);
			
			System.out.println(updated.getActivityCode());
		}
		catch(Exception e)
		{
			
		}
		return updated;
	}
	
	
	public ActivityMaster activityFinalApprovalOffice(String activityId,String finalApproval,String userCode)
	{
		ActivityMaster updated=null;
		try
		{
			System.out.println(activityId+"----------"+finalApproval);
			
			ActivityMaster am=erep.findById(Integer.parseInt(activityId)).orElse(null);
			am.setFinalOfficeApprovedRemarks(finalApproval);
			am.setFinalOfficeApprovedDate(new java.util.Date());
			am.setFinalOfficeApprovedBy(userCode);
			am.setStatus("final office approved");
			am.setAsOrdersIssued("Not Issued");
			updated=erep.save(am);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("Final Office approve");
				mdtl.setMdtlDate(new Date());
				mdtl.setMdtlProcessId(String.valueOf(updated.getActivityCode()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("final office approved");
				mdtl.setActivityLog(""+updated.toString());
				mdtlr.save(mdtl);
			System.out.println(updated.getActivityCode());
		}
		catch(Exception e)
		{
			
		}
		return updated;
	}
	
	
	public ActivityMaster activityCancel(String activityId,String cancelApproval,String userCode)
	{
		ActivityMaster updated=null;
		try
		{
			System.out.println(activityId+"----------"+cancelApproval);
			
			ActivityMaster am=erep.findById(Integer.parseInt(activityId)).orElse(null);
			am.setCancelledRemarks(cancelApproval);
			am.setCancelledDate(new java.util.Date());
			am.setCancelledBy(userCode);
			am.setStatus("cancelled");
			am.setAsOrdersIssued("cancelled");
			
			
			List<Activity_Center> cmlist=acs.getAllactiviticenters(Integer.parseInt(activityId));
			for(Activity_Center cm:cmlist)
			{
				//double estim=cm.getEstimatedExp();
				cm.setBalance_exclude_unassignedGrant(0);
				cm.setEstimatedExp(0);
				cm.setExpTillDate(0);
				cm.setExtension_lectures_count(0);
				cm.setExternalFund(0);
				cm.setExternalFund(0);
				cm.setNational_seminar_count(0);
				cm.setUgcFund(0);
				acr.save(cm);
			}
			updated=erep.save(am);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("program cancel");
				mdtl.setMdtlDate(new Date());
				mdtl.setMdtlProcessId(String.valueOf(updated.getActivityCode()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("canceled");
				mdtl.setActivityLog(""+updated.toString());
				mdtlr.save(mdtl);
			
			System.out.println(updated.getActivityCode());
		}
		catch(Exception e)
		{
			
		}
		return updated;
	}
	
	public ActivityMaster saveActivityMasterforPreApproved(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		ActivityMaster saved1 = null;
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			saved=new ActivityMaster();
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(am.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			saved.setAat(aat);
			saved.setTitle(am.getTitle());
			saved.setDateFrom(am.getDateFrom());
			saved.setDateTo(am.getDateTo());
			saved.setFinyear(am.getFinyear());
			saved.setBrochureURL(am.getBrochureURL());
			saved.setFacultyCode(am.getFacultyCode());
			saved.setActivityLevel(am.getActivityLevel());
			saved.setTopic(am.getTopic());
			saved.setRemarks(am.getRemarks());
			saved.setStatus("preapproved");
			saved1=erep.save(saved);
			  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
				mdtl.setMdtlProcessName("program approval for preapproved activity");
				mdtl.setMdtlDate(new Date());
				mdtl.setMdtlProcessId(String.valueOf(saved1.getActivityCode()));
				mdtl.setMdtllUserCode(userCode);
				mdtl.setMdtltlStatus("preapproved");
				mdtl.setActivityLog(""+saved.toString());
				mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
			
		}
		
		return saved1;
		
	}
	
	
	public List<ActivityMaster> getAllActivitySubmittedByFaculty(String status1,String facultyId)
	{
		System.out.println("statussssss"+status1);
		List<ActivityMaster> al=null;
		
		try
		{
			
			System.out.println("statussssssssssssssssssssssssssss in service"+status1);
			al=erep.getAllVerifiedActivitiesByFacultyCordniator(facultyId,status1);
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public List<ActivityMaster> getAllVerifiedActivitiesByFacultyCordniatorBystatus(String status1,String facultyId)
	{
		System.out.println("statussssss"+status1);
		List<ActivityMaster> al=null;
		
		try
		{
			
			System.out.println("statussssssssssssssssssssssssssss in service"+status1);
			al=erep.getAllVerifiedActivitiesByFacultyCordniatorBystatus(facultyId,status1);
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public List<ActivityMaster> getAllActivityByFaculty(String facultyId)
	{
		//System.out.println("statussssss"+status1);
		List<ActivityMaster> al=null;
		
		try
		{
			
		//	System.out.println("statussssssssssssssssssssssssssss in service"+status1);
			al=erep.getAllActivitiesByFacultyCordniator(facultyId);
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public List<ActivityMaster> getAllActivityForAdministrativeApproved()
	{
		
		List<ActivityMaster> al=null;
		
		try
		{
			
			al=erep.getAllActivityForAdministrativeApproved();
		//	al=erep.findAllActivityApproved();
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public List<ActivityMaster> getAllActivityForAcademicApproved()
	{
		
		List<ActivityMaster> al=null;
		
		try
		{
			
			al=erep.getAllActivityForAcademicApproved();
		//	al=erep.findAllActivityApproved();
			System.out.println("size"+al.size());
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	public OrderGenaration asOrderGenaration(OrderGenaration am,String userCode)
	{
		OrderGenaration saved = null;
		String msg = null;
		ActivityMaster amster=null;
		
		try
		{
			
			int ascount=getCountofAsNo();
			System.out.println("asno"+ascount);
			String asprefix=am.getAsNumberPrefix();
			
			if(ascount==0)
			{
				asprefix=asprefix+"/1";
				am.setAsNumberPrefix(asprefix);
				saved=or.save(am);
				 System.out.println("asnooooooooooooooooo"+saved.getAsNumberPrefix());
				 amster=updateActivityMasterAsno(asprefix,(am.getAc().getActivityCode()));
				 System.out.println(amster.getAsNO());
				 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
					mdtl.setMdtlProcessName("As order generation");
				//	mdtl.setMdtlDate(new Date());
					//mdtl.setMdtlProcessId(String.valueOf(saved.getOrderGenId()));
					//mdtl.setMdtllUserCode(userCode);
					//mdtl.setMdtltlStatus("submitted");
					//mdtl.setActivityLog(""+saved.toString());
					//mdtlr.save(mdtl);
			}
			else
			{
				int nextvar=ascount+1;
				String next=Integer.toString(nextvar);
				asprefix=asprefix+"/"+next;
				am.setAsNumberPrefix(asprefix);
				saved=or.save(am);
				amster=updateActivityMasterAsno(asprefix,am.getAc().getActivityCode());
				System.out.println("entereeee amster"+amster.getAsNO()+saved.getAsNumberPrefix());
				 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			//	//	mdtl.setMdtlProcessName("As order generation");
				//	mdtl.setMdtlDate(new Date());
				//	mdtl.setMdtlProcessId(String.valueOf(saved.getOrderGenId()));
				//	mdtl.setMdtllUserCode(userCode);
				//	mdtl.setMdtltlStatus("submitted");
				//	mdtl.setActivityLog(""+saved.toString());
					//mdtlr.save(mdtl);
				
			}

			
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			//saved=null;
			
			
		}
		System.out.println(saved);
		return saved;
	}
	
	public OrderGenaration asOrderGenarationEdit(OrderGenaration am,String userCode)
	{
		OrderGenaration saved = null;
		OrderGenaration saved1 = null;
		String msg = null;
		
		ActivityMaster amster=null;
		
		try
		{
			saved=or.findById(am.getOrderGenId()).orElse(null);
			System.out.println("ordergenId"+saved.getOrderGenId());
			
			saved.setExternlFundType(am.getExternlFundType());
			saved.setSectionbHeading(am.getSectionbHeading());
			saved.setAsNumberPrefix(am.getAsNumberPrefix());
			saved.setAbstractDetails(am.getAbstractDetails());
			
			saved.setAsrefer(am.getAsrefer());
			saved.setOrderHeading(am.getOrderHeading());
			
			saved.setAsOrderContent(am.getAsOrderContent());
			saved.setCopyTo(am.getCopyTo());
	
			saved1=or.save(saved);

			 if(saved1== null)
				{
					
					msg="NOTSAVED-"+"Error in AS order details Editing";	
					  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
						mdtl.setMdtlProcessName("As order generation Edit");
						mdtl.setMdtlDate(new Date());
						mdtl.setMdtlProcessId(String.valueOf(saved.getOrderGenId()));
						mdtl.setMdtllUserCode(userCode);
						mdtl.setMdtltlStatus("submitted");
						mdtl.setActivityLog(""+saved.toString());
						mdtlr.save(mdtl);
				}
				else
				{
				msg = "AS Order detaild is successfully edited";
				}
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			saved=null;
			
			
		}
		System.out.println(saved);
		return saved1;
	}
	
	
	
	public ActivityMaster administrativeApproveActivityMaster(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		ActivityMaster saved1=null;
		
		
		try
		{
			System.out.println("entrrrr in save activity master"+am.getAat().getActivityTypeCode());
			System.out.println(am.getAat().getActivityTypeCode());
			
			saved=new ActivityMaster();
			//ActivityMaster submitted=erep.findActivityByActivityCode(am.getActivityCode());
			
			ActivityMaster submitted=erep.findById(am.getActivityCode()).orElse(null);
			
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(submitted.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			submitted.setAat(am.getAat());
			submitted.setTitle(am.getTitle());
			submitted.setDateFrom(am.getDateFrom());
			submitted.setDateTo(am.getDateTo());
			submitted.setFinyear(am.getFinyear());
		//	submitted.setBrochureURL(am.getBrochureURL());
			submitted.setDescription(am.getDescription());
			submitted.setTargetGroup(am.getTargetGroup());
			submitted.setOutcome(am.getOutcome());
			submitted.setDeviationJustification(am.getDeviationJustification());
			submitted.setType(am.getType());
			submitted.setActivityLevel(am.getActivityLevel());
			submitted.setFinanceImplied(am.getFinanceImplied());
			submitted.setAdminApprovedDate(new java.util.Date());
			submitted.setAdminApprovedNeed("no");
			//saved.setStatus("submitted");
			//saved.setEnteredBy(userCode);
			//saved.setEnteredDate(submitted.getEnteredDate());
			//saved.setRemarks(submitted.getRemarks());
		//saved.setCenter1(am.getCenter1());
	//	saved.setCenter2(am.getCenter2());
	//	saved.setCenter3(submitted.getCenter3());
	//	saved.setCenter4(submitted.getCenter4());
	//	saved.setCenter4(submitted.getCenter5());
			submitted.setAdministrativeApprovalRemarks(am.getAdminApprovalRemarks());
		
			submitted.setAdministrativeApprovedBy(userCode);
			submitted.setAdministrativeApprovedDate(new java.util.Date());
			String fi=submitted.getFinanceImplied();
	
			submitted.setStatus(am.getStatus());
			
	
			
      saved1=erep.save(submitted);
        ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("As order generation");
		mdtl.setMdtlDate(new Date());
		mdtl.setMdtlProcessId(String.valueOf(saved1.getActivityCode()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("Administrative Approved");
		mdtl.setActivityLog(""+saved1.toString());
		mdtlr.save(mdtl);
 
			System.out.println("saved11111111111"+saved1.getStatus());
		
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved1;
	}
	
	
	public List<AcademicProgramReport> getAllAcademicProgramReport()
	{
		List<AcademicProgramReport> al=null;
		
		try
		{
			al=erep.getAllAcademicProgramReport();
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return al;
	}
	public List<AcademicProgramReport> getAllExpenditureSettled()
	{
		List<AcademicProgramReport> al=null;
		
		try
		{
			al=erep.getAllExpenditureSettled();
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return al;
	}
	
	public List<AcademicProgramReport> getAllProgramReportByfaculty(String userCode)
	{
		List<AcademicProgramReport> al=null;
		
		try
		{
			al=erep.getAllProgramReportByfaculty(userCode);
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return al;
	}
	
	
	public List<AdvanceRequestReportInterface> getAllPendingAdvanceSettlement()
	{
		List<AdvanceRequestReportInterface> al=null;
		
		try
		{
			al=erep.getAllPendingAdvanceSettlement();
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return al;
	}
	
	public List<AcademicProgramReport> getAllAcademicProgramReportByCenter(String centerId)
	{
		List<AcademicProgramReport> al=null;
		
		try
		{
			al=erep.getAllAcademicProgramReportByCenter(Integer.parseInt(centerId));
		}
		catch(Exception e)
		{
			System.out.println("Exception"+e);
		}
		return al;
	}
	
	public String editActivityMaster(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		String msg = null;
		
		int existinrecord1=erep.findActivityRecordByDes(am.getTitle());
		System.out.println("count of records"+existinrecord1);
		
		//if(existinrecord1==0)
		//{
		
		try
		{
			
			
            ActivityMaster submitted=erep.findById(am.getActivityCode()).orElse(null);
            
            System.out.println("submiteeeddddd"+submitted.getActivityCode());
            
            
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(submitted.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			String activityType=aat.getActivityTypeGroup();
			System.out.println(activityType);
			if(activityType.equals("academic"))
			{
				submitted.setAdminApprovedNeed("no");
				System.out.println("activityType"+activityType);
			}
			else if(activityType.equals("others"))
			{
				submitted.setAdminApprovedNeed("yes");
				System.out.println("activityType"+activityType);
				System.out.println(activityType);
			}
			submitted.setAat(am.getAat());
			submitted.setTitle(am.getTitle());
			submitted.setDateFrom(am.getDateFrom());
			submitted.setDateTo(am.getDateTo());
			submitted.setFinyear(am.getFinyear());
		    submitted.setBrochureURL(am.getBrochureURL());
			submitted.setDescription(am.getDescription());
			submitted.setTargetGroup(am.getTargetGroup());
			submitted.setOutcome(am.getOutcome());
			submitted.setDeviationJustification(am.getDeviationJustification());
			submitted.setType(am.getType());
			submitted.setActivityLevel(am.getActivityLevel());
			submitted.setFinanceImplied(am.getFinanceImplied());
		//	submitted.setEnteredBy(userCode);
		//	submitted.setEnteredDate(am.getEnteredDate());
			submitted.setCenter1(am.getCenter1());
			submitted.setCenter2(am.getCenter2());
			submitted.setCenter3(am.getCenter3());
			submitted.setCenter4(am.getCenter4());
			submitted.setCenter4(am.getCenter5());
			submitted.setRemarks(am.getRemarks());
		//	submitted.setStatus("submitted");
		    saved=erep.save(submitted);
 
		    if(saved!=null)
			{
				
				msg="activityCode-"+submitted.getActivityCode();
				 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
					mdtl.setMdtlProcessName("Program Approval Edit");
					mdtl.setMdtlDate(new Date());
					mdtl.setMdtlProcessId(String.valueOf(saved.getActivityCode()));
					mdtl.setMdtllUserCode(userCode);
					mdtl.setMdtltlStatus("submitted");
					mdtl.setActivityLog(""+saved.toString());
					mdtlr.save(mdtl);
			 
			
			}
			else
			{
				msg="Error in editing ActivityMaster Details. Contact Admin";
			}

	
	}
	catch(Exception e)
		{
		System.out.println("exception"+e);
		}
		//}
		/*else
		{
			msg="Activity Name is already Exist";
		}*/
		return msg;
	}
	public String editActivityMasterForAsRevision(ActivityMaster am,String userCode)
	{
		ActivityMaster saved = null;
		String msg = null;
		
		int existinrecord1=erep.findActivityRecordByDes(am.getTitle());
		System.out.println("count of records"+existinrecord1);
		
		//if(existinrecord1==0)
		//{
		
		try
		{
			
			
            ActivityMaster submitted=erep.findById(am.getActivityCode()).orElse(null);
            
            System.out.println("submiteeeddddd"+submitted.getActivityCode());
            
            
			AcademicActivityTypes aat=activityTypeRepository.findActivityTypeByActivityTypeCode(submitted.getAat().getActivityTypeCode());
			System.out.println(aat.getActivityTypeCode());
			String activityType=aat.getActivityTypeGroup();
			System.out.println(activityType);
			if(activityType.equals("academic"))
			{
				submitted.setAdminApprovedNeed("no");
				System.out.println("activityType"+activityType);
			}
			else if(activityType.equals("others"))
			{
				submitted.setAdminApprovedNeed("yes");
				System.out.println("activityType"+activityType);
				System.out.println(activityType);
			}
			submitted.setAat(am.getAat());
			submitted.setTitle(am.getTitle());
			submitted.setDateFrom(am.getDateFrom());
			submitted.setDateTo(am.getDateTo());
			submitted.setFinyear(am.getFinyear());
		    submitted.setBrochureURL(am.getBrochureURL());
			submitted.setDescription(am.getDescription());
			submitted.setTargetGroup(am.getTargetGroup());
			submitted.setOutcome(am.getOutcome());
			submitted.setDeviationJustification(am.getDeviationJustification());
			submitted.setType(am.getType());
			submitted.setActivityLevel(am.getActivityLevel());
			submitted.setFinanceImplied(am.getFinanceImplied());
		//	submitted.setEnteredBy(userCode);
		//	submitted.setEnteredDate(am.getEnteredDate());
			submitted.setCenter1(am.getCenter1());
			submitted.setCenter2(am.getCenter2());
			submitted.setCenter3(am.getCenter3());
			submitted.setCenter4(am.getCenter4());
			submitted.setCenter4(am.getCenter5());
			submitted.setRemarks(am.getRemarks());
	       submitted.setStatus("submitted");
		    saved=erep.save(submitted);
 
		    if(saved!=null)
			{
				
				
				msg="activityCode-"+submitted.getActivityCode();
				 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
					mdtl.setMdtlProcessName("As Revision");
					mdtl.setMdtlDate(new Date());
					mdtl.setMdtlProcessId(String.valueOf(saved.getActivityCode()));
					mdtl.setMdtllUserCode(userCode);
					mdtl.setMdtltlStatus("submitted");
					mdtl.setActivityLog(""+saved.toString());
					mdtlr.save(mdtl);
			 
			}
			else
			{
				msg="Error in editing ActivityMaster Details. Contact Admin";
			}

	
	}
	catch(Exception e)
		{
		System.out.println("exception"+e);
		}
		//}
		/*else
		{
			msg="Activity Name is already Exist";
		}*/
		return msg;
	}
}
