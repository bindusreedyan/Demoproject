package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS.ActivityParticipateMasterData;

@Service
public class ActivityExpEstimateService {
	
	
	@Autowired
	ActivityMasterRepository amr;
	@Autowired
	ExpenditureHeadRepository erep;
	@Autowired
	ActivityExpEstimateRepository aeer;
	public ActivityExpEstimate addActivityEstimate(String activityId,String finYear,List<String> exheadarray,List<String> allocationheadsarray, List<String> estimateheadsarray,List<String> deviationheadsarray, List<String> commentsheadsarray)
	{
		System.out.println("entrrr in updateActivityCenterFinance");
		ActivityExpEstimate saved=null;
		ActivityExpEstimate a1=new ActivityExpEstimate();
		 /*List<Integer> allocationheadsarray1 = allocationheadsarray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		 
		 List<Integer> estimateheadsarray = seminarHeadsArra.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		 
		 
		 List<Integer> lectureHeadsArray1 = lectureHeadsArray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());*/
		 
		 List<Double> allocationheadsarray1 = allocationheadsarray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 
		 List<Double> estimateheadsarray1 = estimateheadsarray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
		 List<Double> deviationheadsarray1 = deviationheadsarray.stream().map(Double::parseDouble)
		         .collect(Collectors.toList());
		 
	
		 
		 for(int i=0;i<exheadarray.size();i++)
		 {
			 ActivityExpHeadKey aed=new ActivityExpHeadKey();
			 ActivityMaster am=amr.findActivityByActivityCode(Integer.parseInt(activityId));
			 aed.setAc(am);
			 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(exheadarray.get(i), finYear);
			 System.out.println("exphead"+em.getExpHeadsKey().getDescription());
			 aed.setExp(em);
			 a1.setActvtyExpHdkey(aed);
			a1.setNualsAllocationAmt(allocationheadsarray1.get(i));
			a1.setEstimatedExp(estimateheadsarray1.get(i));
			a1.setDeviation(deviationheadsarray1.get(i));
			a1.setComment(commentsheadsarray.get(i));
			
			saved=aeer.save(a1);
			
		 }
		 
		 
		return saved;
		
	}

public List<ActivityExpEstimate> getActivityExpHeadExpensesByActivityIdAndFinyear(String activityId,String finYear)
	
	{
		List<ActivityExpEstimate> al=new ArrayList<ActivityExpEstimate>();
		al=null;
		try
		{
			al=aeer.getActivityExpHeadExpenses(Integer.parseInt(activityId),finYear);
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceequestService"+e);
		}
		return al;
	}
	



public ActivityExpEstimate updateActivityEstimate(String activityId,String finYear,List<String> exheadarray,List<String> allocationheadsarray, List<String> estimateheadsarray,List<String> deviationheadsarray, List<String> commentsheadsarray, List<String>asAmntGrantArray )
{
	System.out.println("entrrr in updateActivityCenterFinance");
	ActivityExpEstimate saved=null;
	ActivityExpEstimate a1=null;
	 /*List<Integer> allocationheadsarray1 = allocationheadsarray.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());
	 
	 List<Integer> estimateheadsarray = seminarHeadsArra.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());
	 
	 
	 List<Integer> lectureHeadsArray1 = lectureHeadsArray.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());*/
	 
	 List<Double> allocationheadsarray1 = allocationheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 
	 List<Double> estimateheadsarray1 = estimateheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 List<Double> deviationheadsarray1 = deviationheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 
	 List<Double> asAmntGrantArray1 = asAmntGrantArray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 

	 
	 for(int i=0;i<exheadarray.size();i++)
	 {
		 
		 ActivityExpHeadKey aed=new ActivityExpHeadKey();
		 ActivityMaster am=amr.findActivityByActivityCode(Integer.parseInt(activityId));
		 aed.setAc(am);
		 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(exheadarray.get(i), finYear);
		 aed.setExp(em);
		 //a1.setActvtyExpHdkey(aed);
		 saved=aeer.findById(aed).orElse(null);
		 saved.setNualsAllocationAmt(allocationheadsarray1.get(i));
		 saved.setEstimatedExp(estimateheadsarray1.get(i));
		 saved.setDeviation(deviationheadsarray1.get(i));
		 saved.setComment(commentsheadsarray.get(i));
		 System.out.println("asAmntGrantArray1.get(i)"+asAmntGrantArray1.get(i));
		 saved.setAsGrantedAmnt(asAmntGrantArray1.get(i));
		 a1=aeer.save(saved);
		
	 }
	 
	 
	return saved;
	
}

public ActivityExpEstimate updateActivityEstimateForEditActivity(String activityId,String finYear,List<String> exheadarray,List<String> allocationheadsarray, List<String> estimateheadsarray,List<String> deviationheadsarray, List<String> commentsheadsarray)
{
	System.out.println("entrrr in updateActivityCenterFinance");
	ActivityExpEstimate saved=null;
	ActivityExpEstimate a1=null;
	 /*List<Integer> allocationheadsarray1 = allocationheadsarray.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());
	 
	 List<Integer> estimateheadsarray = seminarHeadsArra.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());
	 
	 
	 List<Integer> lectureHeadsArray1 = lectureHeadsArray.stream().map(Integer::parseInt)
	         .collect(Collectors.toList());*/
	 
	 List<Double> allocationheadsarray1 = allocationheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 
	 List<Double> estimateheadsarray1 = estimateheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 List<Double> deviationheadsarray1 = deviationheadsarray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 
	 
	/* List<Double> asAmntGrantArray1 = asAmntGrantArray.stream().map(Double::parseDouble)
	         .collect(Collectors.toList());
	 */

	 
	 for(int i=0;i<exheadarray.size();i++)
	 {
		 
		 ActivityExpHeadKey aed=new ActivityExpHeadKey();
		 ActivityMaster am=amr.findActivityByActivityCode(Integer.parseInt(activityId));
		 aed.setAc(am);
		 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(exheadarray.get(i), finYear);
		 aed.setExp(em);
		 //a1.setActvtyExpHdkey(aed);
		 saved=aeer.findById(aed).orElse(null);
		 saved.setNualsAllocationAmt(allocationheadsarray1.get(i));
		 saved.setEstimatedExp(estimateheadsarray1.get(i));
		 saved.setDeviation(deviationheadsarray1.get(i));
		 saved.setComment(commentsheadsarray.get(i));
		// saved.setAsGrantedAmnt(asAmntGrantArray1.get(i));
		 a1=aeer.save(saved);
		
	 }
	 
	 
	return saved;
	
}










	
}
