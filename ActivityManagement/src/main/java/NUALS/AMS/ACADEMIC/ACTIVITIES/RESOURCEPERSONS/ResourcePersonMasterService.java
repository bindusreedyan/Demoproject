package NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMasterRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadMasterWrapper;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsKey;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpenditureHeadRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
@Service
public class ResourcePersonMasterService {

	@Autowired
	ResourcePersonMasterRepository erep;
	@Autowired
	ActvityResourcePersonRepository rpmr;
	
	@Autowired
	ActivityMasterRepository amr;
	
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public ResourcePersonMaster saveResourcePersonMaster(ResourcePersonMaster rpm,String userCode)
	{
		
		System.out.println("Resource person type"+rpm.getResType()+"pan"+rpm.getPanNo()+"pass"+rpm.getPassportNo());
		ResourcePersonMaster saved = null;
		
		 ResourcePersonMaster savedafter=null;
		
		try
		{
			saved =new ResourcePersonMaster();
	
	 saved.setName(rpm.getName());
	 saved.setDesignation(rpm.getDesignation());
	 saved.setOrganisation(rpm.getOrganisation());
	 saved.setDomain(rpm.getDomain());
	 saved.setCategory(rpm.getCategory());
	 saved.setAddress(rpm.getAddress());
	 saved.setContactName(rpm.getContactName());
	 saved.setContactPhone(rpm.getContactPhone());
	 saved.setEmail(rpm.getEmail());
	 saved.setPanNo(rpm.getPanNo());
	 saved.setAccountNo(rpm.getAccountNo());
	 saved.setBankName(rpm.getBankName());
	 saved.setBranch(rpm.getBranch());
	 saved.setAccountType(rpm.getAccountType());
	 saved.setIfsc(rpm.getIfsc());
	 saved.setStatus("valid");
	 saved.setResType(rpm.getResType());
	 saved.setPassportNo(rpm.getPassportNo());
	 savedafter=erep.save(saved);
	 
	
	 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("Resouce person add");
		mdtl.setMdtlDate(new java.util.Date());
		mdtl.setMdtlProcessId(String.valueOf(savedafter.getResId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("submitted");
		mdtl.setActivityLog(""+savedafter.toString());
		mdtlr.save(mdtl);
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return savedafter;
	}
	
	public List<ResourcePersonMaster>getResourcepersondetail()
	{
       List<ResourcePersonMaster> al=new ArrayList<ResourcePersonMaster>();
       erep.findAll().forEach(al::add);
		return al;
	}
	
	
	public List<ResourcePersonMaster>getResourcepersondetailsCurrentActivity(List<String>resIdarrea)
	{	
		List<ResourcePersonMaster> al=new ArrayList<ResourcePersonMaster>();
		
		 List<Integer> residarray = resIdarrea.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		
		return al=(List<ResourcePersonMaster>) erep.findResourcePersonByResIdList(residarray);
		
	}
	
	public ActivityResourcePerson saveActivityResource(int activityCode,int resId)
	{
		ActivityResourcePerson saved=null;
		ActivityResourcePersonKey fk=new ActivityResourcePersonKey();
		ActivityMaster am=amr.findActivityByActivityCode(activityCode);
		fk.setAm(am);
		ResourcePersonMaster rpm=erep.findResourcePersonByResId(resId);
		fk.setRpm(rpm);
		saved=new ActivityResourcePerson();
		saved.setActRePerKey(fk);
		rpmr.save(saved);
		return saved;
	}
	
	public List<ActivityResourcePerson>getResourcepersondetailofcurrentActivity(String activityId)
	{	
		List<ActivityResourcePerson> al=new ArrayList<ActivityResourcePerson>();
		
	    int activityCode=Integer.parseInt(activityId);
		 al=(List<ActivityResourcePerson>) rpmr.getResourcepersondetailofcurrentActivity(activityCode);
		 System.out.println(al.size());
		
		return al;
		
	}
	
	public ResourcePersonMaster updateResourcePersonMaster(ResourcePersonMaster rpm,String userCode)
	{
		ResourcePersonMaster saved = null;
		
		 ResourcePersonMaster savedafter=null;
		
		try
		{
			saved =erep.findById(rpm.getResId()).orElse(null);
	
	 saved.setName(rpm.getName());
	 saved.setDesignation(rpm.getDesignation());
	 saved.setOrganisation(rpm.getOrganisation());
	 saved.setDomain(rpm.getDomain());
	 saved.setCategory(rpm.getCategory());
	 saved.setAddress(rpm.getAddress());
	 saved.setContactName(rpm.getContactName());
	 saved.setContactPhone(rpm.getContactPhone());
	 saved.setEmail(rpm.getEmail());
	 saved.setPanNo(rpm.getPanNo());
	 saved.setAccountNo(rpm.getAccountNo());
	 saved.setBankName(rpm.getBankName());
	 saved.setBranch(rpm.getBranch());
	 saved.setAccountType(rpm.getAccountType());
	 saved.setIfsc(rpm.getIfsc());
	 saved.setStatus("valid");
	 savedafter=erep.save(saved);
	 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("Resouce person update");
		mdtl.setMdtlDate(new java.util.Date());
		mdtl.setMdtlProcessId(String.valueOf(savedafter.getResId()));
		mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("submitted");
		mdtl.setActivityLog(""+savedafter.toString());
		mdtlr.save(mdtl);
			 
	
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return savedafter;
	}
	public ResourcePersonMaster deleteResourcePersonMaster(int resid)
	{
		ResourcePersonMaster saved = null;
		
		 ResourcePersonMaster savedafter=null;
		
		try
		{
			saved =erep.findById(resid).orElse(null);
	
/*	 saved.setName(rpm.getName());
	 saved.setDesignation(rpm.getDesignation());
	 saved.setOrganisation(rpm.getOrganisation());
	 saved.setDomain(rpm.getDomain());
	 saved.setCategory(rpm.getDomain());
	 saved.setAddress(rpm.getAddress());
	 saved.setContactName(rpm.getContactName());
	 saved.setContactPhone(rpm.getContactPhone());
	 saved.setEmail(rpm.getEmail());
	 saved.setPanNo(rpm.getPanNo());
	 saved.setAccountNo(rpm.getAccountNo());
	 saved.setBankName(rpm.getBankName());
	 saved.setBranch(rpm.getBranch());
	 saved.setAccountType(rpm.getAccountType());
	 saved.setIfsc(rpm.getIfsc());*/
	 saved.setStatus("invalid");
	 savedafter=erep.save(saved);
	 
	 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
		mdtl.setMdtlProcessName("Resouce person delete");
		mdtl.setMdtlDate(new java.util.Date());
		mdtl.setMdtlProcessId(String.valueOf(savedafter.getResId()));
		//mdtl.setMdtllUserCode(userCode);
		mdtl.setMdtltlStatus("invalid");
		mdtl.setActivityLog(""+savedafter.toString());
		mdtlr.save(mdtl);
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return savedafter;
	}
	
}
