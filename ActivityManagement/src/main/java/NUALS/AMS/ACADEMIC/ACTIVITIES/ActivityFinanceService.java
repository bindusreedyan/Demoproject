package NUALS.AMS.ACADEMIC.ACTIVITIES;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.ActivityMasterDataTimeLine;
import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

@Service
public class ActivityFinanceService {
	
	
	@Autowired
	ActivityFinanceRepository afr;

	@Autowired
	ActivityMasterRepository amr;
	@Autowired
	MasterDataTimeLineRepository mdtlr;
	public ActivityFinance saveActivityFinance(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
			
			System.out.println("in activityFinance"+af.getAc().getActivityCode());
		saved=new ActivityFinance();
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		saved.setEnteredBy(usercode);
		saved.setEnteredDate(new Date());
		ActivityMaster am1=amr.findById(af.getAc().getActivityCode()).orElse(null);
		saved.setAc(am1);
		
		
		
			saved1=afr.save(saved);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Program Approval Finance Detail Saving");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
			System.out.println(saved1.getActivityFinanceCode());
			
		}
		catch(Exception e)
		{
			System.out.println("exception in add finance"+e);
		}
		return saved1;
	}
	
	public ActivityFinance getActivityFinance(String activityId)
	{
		ActivityFinance af=null;
		try
		{
			af=afr.getActivityFinanceByActivityId(Integer.parseInt(activityId));
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		return af;
	}
	
	public ActivityFinance updateActivityFinance(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
	//	saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		saved.setApprovedBy(usercode);
		saved.setApprovedDate(new java.util.Date());
		saved.setTotalAsgranted(af.getTotalAsgranted());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
			ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getApprovesStatus());	
			am1.setFinancialApprovedRemarks(af.getApprovedRemarks());
			am1.setFinancialApprovedDate(af.getApprovedDate());
			am1.setFinancialApprovedBy(usercode);
			amr.save(am1);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Program Approval Finance Details Updating");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	public ActivityFinance administrativeApproval(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		//saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		saved.setApprovedBy(usercode);
		saved.setApprovedDate(af.getApprovedDate());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		saved.setTotalAsgranted(af.getTotalAsgranted());
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
			ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getApprovesStatus());	
			am1.setAdministrativeApprovalRemarks(af.getApprovedRemarks());
			am1.setAdministrativeApprovedDate(new java.util.Date());
			am1.setAdministrativeApprovedBy(usercode);
			am1.setAdminApprovedNeed("no");
			amr.save(am1);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Saving Finance Details of Program in Admiistrative approval");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus(af.getFacultyApprovesStatus());
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	
	
	public ActivityFinance facultyApproval(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		//saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		//saved.setApprovedBy(usercode);
		//saved.setApprovedDate(af.getApprovedDate());
		//saved.setApprovedRemarks(af.getApprovedRemarks()); 
		saved.setTotalAsgranted(af.getTotalAsgranted());
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
			ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getFacultyApprovesStatus());	
			am1.setRecommendedRemarks(af.getRecommendedRemarks());
			am1.setRecommendedDate(new java.util.Date());
			am1.setRecommendedBy((usercode));
			am1.setAdminApprovedNeed("yes");
			amr.save(am1);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Faculty Approval of Finance Details of Program in Faculty Recommendation");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus(af.getFacultyApprovesStatus());
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ActivityFinance administrativeOfficeApproval(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		//saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		saved.setApprovedBy(usercode);
		saved.setApprovedDate(af.getApprovedDate());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		saved.setTotalAsgranted(af.getTotalAsgranted());
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
			ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getAdminofficeapprovesStatus());	
			am1.setOfficeApprovedRemarks(af.getOfficeApprovedRemarks());
			am1.setAdminOfficeVerifiedDate(new java.util.Date());
			am1.setAdminOfficeVerifiedBy(usercode);
			am1.setAdminApprovedNeed("yes");
			amr.save(am1);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("Updating Finance Details of Program in Administrative office Verification");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus(af.getFacultyApprovesStatus());
			mdtl.setActivityLog(""+saved.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	
	
	public ActivityFinance editActivityFinance(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		//saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		/*saved.setApprovedBy(usercode);
		saved.setApprovedDate(af.getApprovedDate());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		saved.setTotalAsgranted(af.getTotalAsgranted());*/
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
	/*		ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getApprovesStatus());	
			am1.setAdministrativeApprovalRemarks(af.getApprovedRemarks());
			am1.setAdministrativeApprovedDate(new java.util.Date());
			am1.setAdministrativeApprovedBy(usercode);
			am1.setAdminApprovedNeed("no");
			amr.save(am1);*/
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("program approval finance editing");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus("submitted");
			mdtl.setActivityLog(""+saved1.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	public ActivityFinance editActivityFinanceForAsRevision(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
		//saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotal_estimated(af.getTotal_estimated());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		/*saved.setApprovedBy(usercode);
		saved.setApprovedDate(af.getApprovedDate());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		saved.setTotalAsgranted(af.getTotalAsgranted());*/
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
	ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
	am1.setStatus("submitted");	
	amr.save(am1);
	/*			am1.setStatus(af.getApprovesStatus());	
			am1.setAdministrativeApprovalRemarks(af.getApprovedRemarks());
			am1.setAdministrativeApprovedDate(new java.util.Date());
			am1.setAdministrativeApprovedBy(usercode);
			am1.setAdminApprovedNeed("no");
			amr.save(am1);*/
	ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
	mdtl.setMdtlProcessName("as revision activity finance editing");
	mdtl.setMdtlDate(new Date());
	mdtl.setMdtlProcessId(String.valueOf(saved.getActivityFinanceCode()));
	mdtl.setMdtllUserCode(usercode);
	mdtl.setMdtltlStatus("submitted");
	mdtl.setActivityLog(""+saved1.toString());
	mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	
	public ActivityFinance updateActivityFinanceverification(ActivityFinance af,String usercode)
	{
		ActivityFinance saved = null;
		ActivityFinance saved1 = null;
		try
		{
		
		 saved=afr.findById(af.getActivityFinanceCode()).orElse(null);//;
		
		//saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		//saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setExternalFunding(af.getExternalFunding());
		saved.setApprovedFunding(af.getApprovedFunding());
	//	saved.setAnnexureFile(af.getAnnexureFile());
		saved.setUgcFunded(af.getUgcFunded());
		saved.setUnivBeneficiaries(af.getUnivBeneficiaries());
		saved.setLocalBeneficiaries(af.getLocalBeneficiaries());
		saved.setOutstnBeneficiaries(af.getOutstnBeneficiaries());
		saved.setIntNatBeneficiaries(af.getIntNatBeneficiaries());
		saved.setUnivResPerson(af.getUnivResPerson());
		saved.setLocalResPerson(af.getLocalResPerson());
		saved.setOutstnResPerson(af.getOutstnResPerson());
		saved.setIntNatResPerson(af.getIntNatResPerson());
		saved.setUnivBenTravel(af.getUnivBenTravel());
		saved.setLocalBenTravel(af.getLocalBenTravel());
		saved.setOutstnBenTravel(af.getOutstnBenTravel());
		saved.setIntNatBenTravel(af.getIntNatBenTravel());
		saved.setUnivResTravel(af.getUnivResTravel());
		saved.setLocalResTravel(af.getLocalResTravel());
		saved.setOutstnResTravel(af.getOutstnResTravel());
		saved.setIntNatResTravel(af.getIntNatResTravel());
		saved.setAirTravelResUniv(af.getAirTravelResIntnl());
		saved.setAirTravelResLocal(af.getAirTravelResLocal());
		saved.setAirTravelResOutstn(af.getAirTravelResOutstn());
		saved.setAirTravelResIntnl(af.getAirTravelResIntnl());
		saved.setHonorMore3000(af.getHonorMore3000());
		saved.setTotalEstExp(af.getTotalEstExp());
		saved.setTotalAdvanceReq(af.getTotalAdvanceReq());
		saved.setApprovedBy(usercode);
		saved.setApprovedDate(new java.util.Date());
		saved.setTotalAsgranted(af.getTotalAsgranted());
		saved.setApprovedRemarks(af.getApprovedRemarks()); 
		//ActivityMaster am=amr.findById(af.getAc().getActivityCode()).orElse(null);
		//am.setStatus("financially approved");
		//amr.save(am);
		// key.setAc(am);		
			saved1=afr.save(saved);
			ActivityMaster am1=amr.findById(saved1.getAc().getActivityCode()).orElse(null);
			am1.setStatus(af.getApprovesStatus());	
			am1.setFinanceOfficeApprovedRemarks(af.getApprovedRemarks());
			am1.setFinanceOfficeVerifiedDate(new java.util.Date());
			am1.setFinanceOfficeVerifiedBy(usercode);
		//	am1.setFinancialApprovedDate(af.getApprovedDate());
			am1.setFinancialApprovedBy(usercode);
			amr.save(am1);
			ActivityMasterDataTimeLine mdtl=new ActivityMasterDataTimeLine();
			mdtl.setMdtlProcessName("finance office Verified");
			mdtl.setMdtlDate(new Date());
			mdtl.setMdtlProcessId(String.valueOf(saved1.getActivityFinanceCode()));
			mdtl.setMdtllUserCode(usercode);
			mdtl.setMdtltlStatus(af.getApprovesStatus());
			mdtl.setActivityLog(""+saved1.toString());
			mdtlr.save(mdtl);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
}
