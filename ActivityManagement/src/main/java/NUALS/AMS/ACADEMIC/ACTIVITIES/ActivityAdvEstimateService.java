package NUALS.AMS.ACADEMIC.ACTIVITIES;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;


@Service
public class ActivityAdvEstimateService {
	
	
	@Autowired
	ActivityMasterRepository amr;
	@Autowired
	ExpenditureHeadRepository erep;
	@Autowired
	ActivityExpEstimateRepository aeer;
	@Autowired
	ActivityAdvEstimateRepository aar;
	@Autowired
MasterDataTimeLineRepository mdtlr;
	
	public List<ActivityAdvEstimate> addActivityAdvEstimate(ActivityAdvanceEstimateRequestWrapper activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper,String userCode)
	{
		

		
		List<ActivityAdvEstimate> al=new ArrayList<ActivityAdvEstimate>();
		
		for(ActivityAdvEstimateWrapper fm:activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper.getActivtyEstimateAdvncewrapper())
		{
			int activityId=fm.getActivityId();
			String finyear=fm.getFinyearexphead();
			System.out.println("the finyearrrrrrrrrrrr"+finyear);
			List<String> expheads=fm.getExheadarray();
			List<String> reason=fm.getExpadvreqreasonarray();
	        List<Double>expadvreqarray=fm.getExpadvreqarray();
			for(int i=0;i<expheads.size();i++)
			{
				ActivityExpHeadKey key=new ActivityExpHeadKey();
				 ActivityMaster am=amr.findActivityByActivityCode(activityId);
				 key.setAc(am);
				 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(expheads.get(i), finyear);
				 key.setExp(em);
				 ActivityAdvEstimate aae=new ActivityAdvEstimate();
				 aae.setActvtyExpHdkey(key);
				 aae.setAmountAdvance(expadvreqarray.get(i));
				 aae.setReasonAdvance(reason.get(i));
				 System.out.println(aae.getActvtyExpHdkey().getAc().getActivityCode());
				 aae.getActvtyExpHdkey().getExp().getExpHeadsKey().getDescription();
				 ActivityAdvEstimate aae1= aar.save(aae);
					ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
					mdtl.setMdtlProcessName("Adding Advance Estimate for Program");
					mdtl.setMdtlDate(new Date());
					mdtl.setMdtlProcessId(String.valueOf(aae1.getActivityAdvEstimateSino()));
					mdtl.setMdtllUserCode(userCode);
					mdtl.setMdtltlStatus("submitted");
					mdtl.setActivityLog(""+aae1.toString());
					mdtlr.save(mdtl);
				// String log= aae.toString();
				 
				 al.add(aae);
				 
			}
			
			//update the status of the previous feeheds to invalid;
			
			
			}
		
	   return al;
		
	}
	
	public List<ActivityAdvEstimate> getActivityAdvanceRequest(String activityId,String finYear)
	
	{
		List<ActivityAdvEstimate> al=new ArrayList<ActivityAdvEstimate>();
		al=null;
		try
		{
			al=aar.getActivityAdvanceRequest(Integer.parseInt(activityId),finYear);
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceequestService"+e);
		}
		return al;
	}
	
	public List<ActivityAdvEstimate> updateActivityAdvanceEstimateRequest(ActivityAdvanceEstimateRequestWrapper activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper,String userCode)
	{
	
		List<ActivityAdvEstimate> al=new ArrayList<ActivityAdvEstimate>();
		
		for(ActivityAdvEstimateWrapper fm:activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper.getActivtyEstimateAdvncewrapper())
		{
			int activityId=fm.getActivityId();
			String finyear=fm.getFinyearexphead();
			System.out.println("the finyearrrrrrrrrrrr"+finyear);
			List<String> expheads=fm.getExheadarray();
			List<String> reason=fm.getExpadvreqreasonarray();
	        List<Double>expadvreqarray=fm.getExpadvreqarray();
	        List<Double>asGrantedAdvAmntarray=fm.getAsGrantedAdvAmntarray();
	        
			for(int i=0;i<expheads.size();i++)
			{
				 ActivityExpHeadKey key=new ActivityExpHeadKey();
				 ActivityMaster am=amr.findActivityByActivityCode(activityId);
				 key.setAc(am);
				 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(expheads.get(i), finyear);
				 key.setExp(em);
				 ActivityAdvEstimate aae=aar.findById(key).orElse(null);

				 
				 
				// ActivityAdvEstimate aae=new ActivityAdvEstimate();
				 aae.setActvtyExpHdkey(key);
				 aae.setAmountAdvance(expadvreqarray.get(i));
				 aae.setReasonAdvance(reason.get(i));
				 aae.setAsGranted(asGrantedAdvAmntarray.get(i));
				 
				 System.out.println("asgrantedamnt"+ asGrantedAdvAmntarray.get(i));
				 System.out.println(aae.getActvtyExpHdkey().getAc().getActivityCode());
				 aae.getActvtyExpHdkey().getExp().getExpHeadsKey().getDescription();
				 ActivityAdvEstimate aae1=aar.save(aae);
				 al.add(aae);
				 ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
					mdtl.setMdtlProcessName("Adding Advance Estimate for Program");
					mdtl.setMdtlDate(new Date());
					mdtl.setMdtlProcessId(String.valueOf(aae1.getActivityAdvEstimateSino()));
					mdtl.setMdtllUserCode(userCode);
					mdtl.setMdtltlStatus("submitted");
					mdtl.setActivityLog(""+aae1.toString());
					mdtlr.save(mdtl);
				 
			}
			
			//update the status of the previous feeheds to invalid;
			
			
			}
		
	   return al;
		
	}
	
	
	public List<AdvanceRequestFinalApproval>getAllAdvanceRequestFinallyApproved()
	{
		List<AdvanceRequestFinalApproval> al=new ArrayList<AdvanceRequestFinalApproval>();
		al=null;
		try
		{
			
			al=aar.getAllAdvanceRequestFinallyApproved();
			System.out.println("size of the al"+al.size());
			
		}
		catch(Exception e)
		{
			System.out.println("exception in getActivityAdvanceRequest"+e);
		}
		return al;
	}
	
	
	
	public List<ActivityAdvEstimate> advanceReleasedDetailsPerActivity(String actvtyCode)
	{
		 List<ActivityAdvEstimate> created=new ArrayList<ActivityAdvEstimate>();
		 try
		 {
			 created=aar.advanceReleasedDetailsPerActivity(Integer.parseInt(actvtyCode));
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exception in advance Release"+e);
		 }
		 return created;
	}

	
	public List<ActivityAdvEstimate> updateActivityAdvanceEstimateRequestForEditProgram(ActivityAdvanceEstimateRequestWrapper activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper)
	{
	
		List<ActivityAdvEstimate> al=new ArrayList<ActivityAdvEstimate>();
		
		for(ActivityAdvEstimateWrapper fm:activtyEsactivtyEstimateAdvncewrappertimateAdvncewrapper.getActivtyEstimateAdvncewrapper())
		{
			int activityId=fm.getActivityId();
			String finyear=fm.getFinyearexphead();
			System.out.println("the finyearrrrrrrrrrrr"+finyear);
			List<String> expheads=fm.getExheadarray();
			List<String> reason=fm.getExpadvreqreasonarray();
	        List<Double>expadvreqarray=fm.getExpadvreqarray();
	        //List<Double>asGrantedAdvAmntarray=fm.getAsGrantedAdvAmntarray();
	        
			for(int i=0;i<expheads.size();i++)
			{
				 ActivityExpHeadKey key=new ActivityExpHeadKey();
				 ActivityMaster am=amr.findActivityByActivityCode(activityId);
				 key.setAc(am);
				 ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear(expheads.get(i), finyear);
				 key.setExp(em);
				 ActivityAdvEstimate aae=aar.findById(key).orElse(null);

				 
				 
				// ActivityAdvEstimate aae=new ActivityAdvEstimate();
				 aae.setActvtyExpHdkey(key);
				 aae.setAmountAdvance(expadvreqarray.get(i));
				 aae.setReasonAdvance(reason.get(i));
			//	 aae.setAsGranted(asGrantedAdvAmntarray.get(i));
				 
				 //System.out.println("asgrantedamnt"+ asGrantedAdvAmntarray.get(i));
				 System.out.println(aae.getActvtyExpHdkey().getAc().getActivityCode());
				 aae.getActvtyExpHdkey().getExp().getExpHeadsKey().getDescription();
				 aar.save(aae);
				 al.add(aae);
				 
			}
			
			//update the status of the previous feeheds to invalid;
			
			
			}
		
	   return al;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
