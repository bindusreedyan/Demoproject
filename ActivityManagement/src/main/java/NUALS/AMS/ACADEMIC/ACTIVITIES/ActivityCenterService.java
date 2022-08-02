package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ActivityResourcePerson;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ActivityResourcePersonKey;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTER.CenterFundRepository;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRERepository;

@Service
public class ActivityCenterService {
	@Autowired 
	CENTRERepository cenrep;
	@Autowired
	ActivityCenterRepository acr;
	@Autowired
	ActivityMasterRepository amr;
	
	@Autowired
	CenterFundRepository cfr;
	public Activity_Center saveActivityCenter(int activityCode,int centerId)
	{
		Activity_Center saved=null;
		saved=new Activity_Center();
		ActivityCenterKey fk=new ActivityCenterKey();
		ActivityMaster am=amr.findActivityByActivityCode(activityCode);
		fk.setAc(am);
		CENTRE cm = cenrep.findById(centerId).get();	
		fk.setCm(cm);
		saved.setActvtyCenterKey(fk);
		acr.save(saved);
		return saved;
	}
	
	    //to get the details of all expheads
		public List<Activity_Center> getAllactiviticenters(int activityId)
		{
			List<Activity_Center> cmlist = new ArrayList<Activity_Center>();
			acr.findActivityByActivityCode(activityId).forEach(cmlist::add);
			return cmlist;
		}
		
	public String updateActivityCenterFinance(String activityId,List<String> centerarray,List<String> balassignArray, List<String> estimateHeadsArray, List<String>  expIncurred ,List<String> seminarHeadsArra, List<String> lectureHeadsArray,List<String> externlfundArray, List<String> ugcFundArray)
	{
		System.out.println("entrrr in updateActivityCenterFinance");
		Activity_Center saved=null;
		
		 List<Integer> centerarray1 = centerarray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		 
		 List<Integer> seminarHeadsArra1 = seminarHeadsArra.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		 
		 
		 List<Integer> lectureHeadsArray1 = lectureHeadsArray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		 
		 List<Double> balassignArray1 = balassignArray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 
		 List<Double> estimateHeadsArray1 = estimateHeadsArray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 List<Double> expIncurredArray1 = expIncurred.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 
		 List<Double> externlfundArray1 = externlfundArray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 List<Double> ugcFundArray1 = ugcFundArray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 for(int i=0;i<centerarray1.size();i++)
		 {
			Activity_Center cm=acr.findActivityByActivityByActivityCodeAndCenterCode(Integer.parseInt(activityId),centerarray1.get(i));
			cm.setBalance_exclude_unassignedGrant(balassignArray1.get(i));
			cm.setEstimatedExp(estimateHeadsArray1.get(i));
			cm.setExtension_lectures_count(lectureHeadsArray1.get(i));
			cm.setNational_seminar_count(seminarHeadsArra1.get(i));
			cm.setExpTillDate(expIncurredArray1.get(i));
			//int externlfundsize=externlfundArray1.size();
			//int ugcFundSize=ugcFundArray1.size();
			//if((externlfundsize==1)&&(externlfundArray1.get(0)==0))
			cm.setExternalFund(externlfundArray1.get(i));
			cm.setUgcFund(ugcFundArray1.get(i));
			saved=acr.save(cm);
			
		
			
			
			System.out.println("activity center object"+saved.getActvtyCenterKey().getAc().getActivityCode()+saved.getBalance_exclude_unassignedGrant());
		 }
		 
		 
		return null;
		
	}
	
	public List<Activity_Center>getAllActiveCenterByCenterIdsAndactiviy(List<String>centerarray,String activeId)
	{	
		List<Activity_Center> al=new ArrayList<Activity_Center>();
		 List<Integer> centerarray1 = centerarray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		
	    int activeId1=Integer.parseInt(activeId);
		
		return al=(List<Activity_Center>) acr.findActivceCenterRecordByCenterIdsAndActiveId(centerarray1,activeId1);
		
	}
	
	
	
	public Activity_Center editActivityCenter(int activityCode,int centerId)
	{
		Activity_Center saved=null;
		saved=new Activity_Center();
		ActivityCenterKey fk=new ActivityCenterKey();
		ActivityMaster am=amr.findActivityByActivityCode(activityCode);
		fk.setAc(am);
		CENTRE cm = cenrep.findById(centerId).get();	
		fk.setCm(cm);
		saved.setActvtyCenterKey(fk);
		
		//ActivityCenter ac=acr.find
		
		
		
		acr.save(saved);
		return saved;
	}
	
	
	

}
