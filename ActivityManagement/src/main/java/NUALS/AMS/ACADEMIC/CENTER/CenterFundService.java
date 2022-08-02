package NUALS.AMS.ACADEMIC.CENTER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMasterRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.Activity_Center;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpenditureHeadRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.GetTotalEstimatedAsGrantByCenter;
import NUALS.AMS.ACADEMIC.ACTIVITIES.GetTotalEstimatedExpByCenter;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRE;
import NUALS.AMS.ACADEMIC.CENTRES.CENTRERepository;
@Service
public class CenterFundService {
	
	@Autowired
	CenterRepository crep;
	@Autowired
	CenterFundRepository cfr;
	@Autowired 
	CENTRERepository cenrep;
	
	@Autowired
	ExpenditureHeadRepository erep;
	
	@Autowired 
	ActivityMasterRepository am;
	
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public CenterMaster saveCenter(CenterMaster cm)
	{
		CenterMaster saved = null;
		
		
		
		try
		{
			int centerId=cm.getCentreCode();
			int existinrecord=crep.findCenterRecord(centerId);
			if(existinrecord==0)
			{
			
		 saved=crep.save(cm);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	
	public List<CenterMaster> getAllCenters()
	{
		List<CenterMaster> cmlist = new ArrayList<CenterMaster>();
		crep.getAllCenteresSubmitted().forEach(cmlist::add);
		return cmlist;
	}
    //center fund services starts from here
	//save center fund  -------------------to be added in jayaram sir----------
	public CenterFund saveCenterFund(CenterFundWrapper cfw,String userCode)
	{
		CenterFund saved = null;
		
		
		
		try
		{
			int centerCode=cfw.getCentreCode();
			System.out.println("centre code"+centerCode);
			int existinrecord=cfr.countCenterFundRecordCount(centerCode,cfw.getFinYear());
			System.out.println("size"+existinrecord);
			if(existinrecord==0)
			{
			
				CenterFund cf=new CenterFund();
				CentreFundKey cfk=new CentreFundKey();
				
				CENTRE cm = cenrep.findById(cfw.getCentreCode()).get();		
				System.out.println(cm.getCentre_id());
				cfk.setCm(cm);
				cfk.setFinYear(cfw.getFinYear());
				
				
				
				cf.setCentreFundKey(cfk);
				cf.setFundAmount(cfw.getFundAmount());
				cf.setEnteredBy(userCode);
				cf.setEnteredDate(new java.util.Date());
				cf.setCurrentBalance(cfw.getFundAmount());
				cf.setCommitedTotal(0.0);
				cf.setExpTillDate(0.0);
				cf.setStatus("submitted");
				
				
			//	ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear("Unassigned Grant",cf.getCentreFundKey().getFinYear());
				
				cf.setUnAssignGrant(cfw.getUnAssignGrant());
				cf.setUnAssignGrantBal(cfw.getUnAssignGrant());
		        saved=cfr.save(cf);
			}
			else
			{
				
				saved=null;
			}
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public List<CenterFund> getAllCenterFundDetails()
	{
		List<CenterFund> cmlist = new ArrayList<CenterFund>();
		cfr.getAllCenterFundSubmitted().forEach(cmlist::add);
		return cmlist;
	}
	
	public List<CenterFund> getAllCenterFundSubmittedInAllstatus()
	{
		List<CenterFund> cmlist = new ArrayList<CenterFund>();
		cfr.getAllCenterFundSubmittedInAllstatus().forEach(cmlist::add);
		return cmlist;
	}
	
	public CenterFund getAllCenterFundDetailsByCenterIdAndFinYear(String currentfinyear,String centerId)
	{
		CenterFund cf=null;
		
	
		System.out.println("centerId ddddddd"+centerId);
		System.out.println("CUREEEENT FINEAR"+currentfinyear);
		cf=cfr.findCenterRecordByFinyearAndCenterId(Integer.parseInt(centerId),currentfinyear);
		System.out.println("cfffffffffffff"+cf);
		
		return cf;
	}
	
	public List<CenterFund>getAllCenterFundDetailsByCenterIdsAndFinYear(List<String>centerarray,String currentfinyear)
	{	
		List<CenterFund> al=new ArrayList<CenterFund>();
		
		 List<Integer> centerarray1 = centerarray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
		
		return al=(List<CenterFund>) cfr.findCenterRecordByFinyearAndCenterIds(centerarray1,currentfinyear);
		
	}
	
   public List<CenterFund> updateCentrFund(List<String> centerarray,String finYear)
	{
	     double estimated=0.0;
	     List<CenterFund> al=new ArrayList<CenterFund>();
		 List<Integer> centerarray1 = centerarray.stream().map(Integer::parseInt)
		         .collect(Collectors.toList());
	 
		 for(int i=0;i<centerarray1.size();i++)
		 {
			 estimated=0;
			 
			 List<GetTotalEstimatedExpByCenter> al1=new ArrayList<GetTotalEstimatedExpByCenter>();
			 List<GetTotalEstimatedAsGrantByCenter> al2=new ArrayList<GetTotalEstimatedAsGrantByCenter>();
			
			al1=am.getEsimatedExpenseByCenterId(centerarray1.get(i),finYear);
			System.out.println("size of al"+al1.size());

			for(int j=0;j<al1.size();j++)
			{
				GetTotalEstimatedExpByCenter txp=al1.get(j);
				System.out.println("txp.getTes()"+txp.getTes());
				
				estimated=estimated+txp.getCestim();
			}
			
			al2=am.getEsimatedExpenseByCenterIdByApproved(centerarray1.get(i),finYear);
			System.out.println("size of al2"+al2.size());
    	      for(int k=0;k<al2.size();k++)
			     {
				
				
				    GetTotalEstimatedAsGrantByCenter txp1=al2.get(k);
				   System.out.println("txp.getTes()"+txp1.getTa());
				   estimated=estimated+txp1.getCestim();
			      }
	
              CenterFund cf=cfr.findCenterRecordByFinyearAndCenterId(centerarray1.get(i),finYear);
              System.out.println("estimated"+estimated);
			  cf.setExpTillDate(estimated);
			  
			  
			  double bal=cf.getFundAmount()-estimated-cf.getUnAssignGrant();
			  cf.setCurrentBalance(bal);
			  CenterFund cf1=cfr.save(cf);
			  if(cf1!=null)
			  {
			 System.out.println(cf1.getCentreFundKey().getCm().getCentre_id()+"total"+cf1.getCommitedTotal()+"bal"+cf1.getCurrentBalance());
			 
				al.add(cf1);
			  }
		
	     }
		 
		
		 return al;
	}
 
  	
   
   public CenterFund verifyCenterFund(CenterFundWrapper cfw,String userCode)
	{
		CenterFund cf = null;
		
		CenterFund saved = null;
		
		
		try
		{
			int centerCode=cfw.getCentreCode();
			System.out.println("centre code"+centerCode);
			
			CentreFundKey cfk=new CentreFundKey();
			CENTRE cm = cenrep.findById(cfw.getCentreCode()).get();		
			System.out.println(cm.getCentre_id());
			cfk.setCm(cm);
			cfk.setFinYear(cfw.getFinYear());
		
			cf=cfr.findCenterRecordByFinyearAndCenterId(cfw.getCentreCode(),cfw.getFinYear());
			cf.setFundAmount(cfw.getFundAmount());
			cf.setCurrentBalance(cfw.getFundAmount());
		    cf.setCommitedTotal(0.0);
			cf.setExpTillDate(0.0);
			cf.setStatus("verified");
				
				
			//	ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear("Unassigned Grant",cf.getCentreFundKey().getFinYear());
				
				cf.setUnAssignGrant(cfw.getUnAssignGrant());
				cf.setUnAssignGrantBal(cfw.getUnAssignGrant());
				cf.setFundVerifiedBy(userCode);
				cf.setFundVerifiedDate(new java.util.Date());
				cf.setFundVerifiedRemarks(cfw.getFundVerifiedRemarks());
				
				 saved=cfr.save(cf);
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
   
   public CenterFund approveCenterFund(CenterFundWrapper cfw,String userCode)
 	{
 		CenterFund cf = null;
 		
 		CenterFund saved = null;
 		
 		
 		try
 		{
 			int centerCode=cfw.getCentreCode();
 			System.out.println("centre code"+centerCode);
 			
 			CentreFundKey cfk=new CentreFundKey();
 			CENTRE cm = cenrep.findById(cfw.getCentreCode()).get();		
 			System.out.println(cm.getCentre_id());
 			cfk.setCm(cm);
 			cfk.setFinYear(cfw.getFinYear());
 		
 			cf=cfr.findCenterRecordByFinyearAndCenterId(cfw.getCentreCode(),cfw.getFinYear());
 			cf.setFundAmount(cfw.getFundAmount());
 			cf.setCurrentBalance(cfw.getFundAmount());
 		    cf.setCommitedTotal(0.0);
 			cf.setExpTillDate(0.0);
 			cf.setStatus("approved");
 				
 				
 			//	ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear("Unassigned Grant",cf.getCentreFundKey().getFinYear());
 				
 				cf.setUnAssignGrant(cfw.getUnAssignGrant());
 				cf.setUnAssignGrantBal(cfw.getUnAssignGrant());
 				cf.setApprovedBy(userCode);
 				cf.setApprovedDate(new java.util.Date());
 				cf.setApprovedRemarks(cfw.getApprovedRemarks());
 				
 				 saved=cfr.save(cf);
 	 
 			 
 		}
 		catch(Exception ex)
 		{
 			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
 		}
 		
 		return saved;
 	}
   
   public CenterFund editCenterFund(CenterFundWrapper cfw,String userCode)
  	{
  		CenterFund cf = null;
  		CenterFund saved = null;
  		try
  		{
  			int centerCode=cfw.getCentreCode();
  			System.out.println("centre code"+centerCode);
  			
  			CentreFundKey cfk=new CentreFundKey();
  			CENTRE cm = cenrep.findById(cfw.getCentreCode()).get();		
  			System.out.println(cm.getCentre_id());
  			cfk.setCm(cm);
  			cfk.setFinYear(cfw.getFinYear());
  		
  			cf=cfr.findCenterRecordByFinyearAndCenterId(cfw.getCentreCode(),cfw.getFinYear());
  			cf.setFundAmount(cfw.getFundAmount());
  			cf.setCurrentBalance(cfw.getFundAmount());
  		    cf.setCommitedTotal(0.0);
  			cf.setExpTillDate(0.0);
  			cf.setStatus("submitted");
  			//	ExpHeadsMaster em=erep.getExpHeadsrecordBydescriptionAndFinyear("Unassigned Grant",cf.getCentreFundKey().getFinYear());
  		    cf.setUnAssignGrant(cfw.getUnAssignGrant());
  			cf.setUnAssignGrantBal(cfw.getUnAssignGrant());
  				 				
  				
  				  saved=cfr.save(cf);
  				  ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
  					mdtl.setMdtlProcessName("center fund edit");
  					mdtl.setMdtlDate(new java.util.Date());
  					mdtl.setMdtlProcessId(String.valueOf(saved.getCentreFundKey().getCm().getCentre_id()+"-"+saved.getCentreFundKey().getFinYear()));
  					mdtl.setMdtllUserCode(userCode);
  					mdtl.setMdtltlStatus("submitted");
  					mdtl.setActivityLog(""+saved.toString());
  					mdtlr.save(mdtl);
  	 
  			 
  		}
  		catch(Exception ex)
  		{
  			System.out.println("Exception at saveCenterMaster : "+ex.getMessage());
  		}
  		
  		return saved;
  	}
 	
	
}
