package NUALS.AMS.ACADEMIC.ACTIVITIES.PARTICIPANTS;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMaster;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityMasterRepository;
import NUALS.AMS.ACADEMIC.ACTIVITIES.OrderGenaration;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypeRepository;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
import NUALS.AMS.ACADEMIC.ADVANCE.AdvExpSettlement;
import NUALS.AMS.ACADEMIC.ADVANCE.Esgeneration;

@Service
public class ActiveMasterDataService {
	@Autowired
	ActiveMasterDataRepository activeMasterDataRep;
	@Autowired ActiveMasterDataRepository amdr;
	@Autowired
	ActiveMasterDataRepository amd;
	@Autowired
	ActivityMasterRepository amr;
	@Autowired
	ActivityParticipantRep aar;
	
	@Autowired ParticipationAsOrderRep por;
	
	@Autowired 
	ParticipationEsorderRep per;
	public ActivityParticipateMasterData saveActivityParticipationRequestMasterData(ActivityParticipateMasterData apm,String userCode,String mobile)
	{
		ActivityParticipateMasterData saved = null;
		
		try
		{
			
			apm.setSettlementStatus("Not Settled");
			apm.setAsOrdersIssued("Not Issued");
			apm.seteSOrdersIssued("Not Issued");
		    saved=activeMasterDataRep.save(apm);
			ActivityParticipationRequestKey apr=new ActivityParticipationRequestKey();
		    ActivityMaster am=amr.findActivityByActivityCode(apm.getAc().getActivityCode());
		    ActivityParticipateMasterData apmd=amdr.findParticipantRequestByRequestId(saved.getParticiaptionRequestId());
		    System.out.println("particiapate request id"+apmd.getParticiaptionRequestId());
			apr.setAc(am);
			apr.setRollNo(saved.getRollNo());
			// apr.setpR(apmd);
			 ActivityParticipateMember tobesaved=new ActivityParticipateMember();
			 tobesaved.setpR(apmd);
			 tobesaved.setActivityParticptnRequest(apr);
			 tobesaved.setOnwardStartDate(apm.getTravelDateOnwardStart());
			 tobesaved.setOnwardEndDate(apm.getTravelDateOnwardReturn());
			 tobesaved.setReturnStartDate(apm.getTravelDateReturnStart());
			 tobesaved.setReturnEndDate(apm.getTravelDateReturnEnd());
			 //tobesaved.setContactNumber(apm.getContactNo());
			// tobesaved.setpR(apmd);
			 tobesaved.setUserCode(apm.getEnteredBy());
			 tobesaved.setContactNumber(mobile);
			 
			 ActivityParticipateMember  saved1=aar.save(tobesaved);
			 System.out.println(saved1);
			
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public ActivityParticipateMasterData editActivityParticipationRequestMasterData(ActivityParticipateMasterData apm,String userCode)
	{
		ActivityParticipateMasterData saved = null;
		
		
		
		try
		{

	     ActivityParticipateMasterData participate=activeMasterDataRep.findById(apm.getParticiaptionRequestId()).orElse(null);
			/*apm.setLevelOneCheckedBy(userCode);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date levelOneVerifiedDate = new java.sql.Date(utilDate.getTime());
            
			apm.setLevelOneCheckDate(levelOneVerifiedDate);
			apm.setLevelOneCheckRemark(apm.getLevelOneCheckRemark());*/
	     
	     participate.setDateNotifction(apm.getDateNotifction());
	     participate.setHostingInstitution(apm.getHostingInstitution());
	     participate.setActivityVenue(apm.getActivityVenue());
	     participate.setCompetionStart(apm.getCompetionStart());
	     participate.setCompetionEnd(apm.getCompetionEnd());
	     participate.setTravelDateOnwardStart(apm.getTravelDateOnwardStart());
	     participate.setTravelDateOnwardReturn(apm.getTravelDateOnwardReturn());
	     participate.setTravelDateReturnStart(apm.getTravelDateReturnStart());
	     participate.setTravelDateReturnEnd(apm.getTravelDateReturnEnd());
	     participate.setCompetionType(apm.getCompetionType());
	     participate.setCurrencyNonINR(apm.getCurrencyNonINR());
	     participate.setEstVisaCharge(apm.getEstVisaCharge());
	     participate.setEstLoadging(apm.getEstLoadging());
	     participate.setHostingCountry(apm.getHostingCountry());
	     participate.setConversionRate(apm.getConversionRate());
	     participate.setEstLocalConv(apm.getEstLocalConv());
	    // participate.setLevelOneCheckRemark(apm.getLevelOneCheckRemark());
	     saved=activeMasterDataRep.save(participate);
	 
			 
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostelMaster : "+ex.getMessage());
		}
		
		return saved;
	}
	
	public ParticipationAsOrder asOrderGenaration(ParticipationAsOrder am,String userCode)
	{
		ParticipationAsOrder saved = null;
		String msg = null;
		
		try
		{
			
			int ascount=getCountofAsNo();
			System.out.println("asno"+ascount);
			String asprefix=am.getAsNumberPrefix();
			ActivityParticipateMasterData apm=activeMasterDataRep.findById(am.getParticipationRequestId()).orElse(null);
			
			if(ascount==0)
			{
				asprefix=asprefix+"/1";
				am.setAsNumberPrefix(asprefix);
				java.util.Date d=new java.util.Date();
				
				am.setOrderGeneratedDate(d);
				saved=por.save(am);
				if(saved!=null)
				{
				apm.setAsNo(saved.getAsNumberPrefix());
				apm.setAsOrdersIssued("Issued");
				activeMasterDataRep.save(apm);
				}

			}
			else
			{
				int nextvar=ascount+1;
				String next=Integer.toString(nextvar);
				asprefix=asprefix+"/"+next;
				am.setAsNumberPrefix(asprefix);
                java.util.Date d=new java.util.Date();
				
				am.setOrderGeneratedDate(d);
				saved=por.save(am);
				
				if(saved!=null)
				{
				apm.setAsNo(saved.getAsNumberPrefix());
				apm.setAsOrdersIssued("Issued");
				activeMasterDataRep.save(apm);
				}
				
				
			}

			 if(saved == null)
				{
					
					msg="NOTSAVED-"+"Error in AS Generation ";	
				}
				else
				{
				msg = "AS Order is successfully Generated for the Program Participation"+asprefix;
				}
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			saved=null;
			
			
		}
		System.out.println(saved);
		return saved;
	}
	
	public int getCountofAsNo()
	{
		int ascount=por.getCountofAsNo();
		return(ascount);
	}
	
	public ParticipationEsGeneration esOrderGenaration(ParticipationEsGeneration am,String userCode)
	{
		ParticipationEsGeneration saved = null;
		String msg = null;
		
		//AdvExpSettlement amster=null;
		
		try
		{
			
			int ascount=getCountofEsNo();
			System.out.println("asno"+ascount);
		
			ActivityParticipateMasterData apm=activeMasterDataRep.findById(am.getParticipateRequestid()).orElse(null);
			System.out.println("apm id"+apm.getParticiaptionRequestId());
			if(ascount==0)
			{
				
				
				String asprefix=am.getEsNumberPrefix()+"/1";
				am.setEsNumberPrefix(asprefix);
				am.setOrderGeneratedDate(new java.util.Date());
				saved=per.save(am);
				
				if(saved!=null)
				{
			//	apm.setEs(saved.getAsNumberPrefix());
				apm.seteSOrdersIssued("Issued");
				apm.setEsNo(asprefix);
				activeMasterDataRep.save(apm);
				}
				
				
				
				
				//amster=updateEsno(asprefix,(am.getAe().getSetId()));
				
			}
			else
			{
				int nextvar=ascount+1;
				String next=Integer.toString(nextvar);
				String asprefix=am.getEsNumberPrefix()+"/"+next;
				am.setEsNumberPrefix(asprefix);
				saved=per.save(am);
				if(saved!=null)
				{
			//	apm.setEs(saved.getAsNumberPrefix());
				apm.seteSOrdersIssued("Issued");
				apm.setEsNo(asprefix);
				activeMasterDataRep.save(apm);
				}
				//amster=updateEsno(asprefix,(am.getAe().getSetId()));
				
			}

			 if(saved == null)
				{
					
					msg="NOTSAVED-"+"Error in Es Generation ";	
				}
				else
				{
			//	msg = "ES Order is successfully Generated for the Program Participation"+saved.getAc().getActivityCode()+"with As No-"+amster.getEsNo();
				}
			
			}
		catch(Exception e)
		{
			System.out.println("exception"+e);
			msg="NOTSAVED-"+"Error in AS Generation ";
			saved=null;
			
			
		}
		System.out.println(saved);
		return saved;
	}
	
	public int getCountofEsNo()
	{
		int ascount=per.getCountofAsNo();
		return(ascount);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
