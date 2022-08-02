package NUALS.AMS.ACADEMIC.ADVANCE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import NUALS.AMS.UserauthObject;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFund;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ActivityBudgetFundRep;
import NUALS.AMS.ACADEMIC.ACTIVITIES.AdvanceSettlementClass;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetFundRep;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetFundWrapper;
import NUALS.AMS.ACADEMIC.ACTIVITIES.BudgetHeadFund;
import NUALS.AMS.ACADEMIC.ACTIVITIES.OrderGenaration;
import NUALS.AMS.ACADEMIC.ACTIVITIES.ProgramApprovalAdvancePayment;
import NUALS.AMS.ACADEMIC.ACTIVITIES.asBudheadDetails;
import NUALS.AMS.ACADEMIC.ACTIVITIES.getActivityBudgetHeadDetails;

import NUALS.AMS.ACADEMIC.ACTIVITIES.MASTERDATATIMELINE.MasterDataTimeLineRepository;

@RequestMapping("/academicactivity")
@RestController
public class AdvanceExpSettlementController {
	
	
	@Autowired 
	AdvEspSettlementRep aesr;
	
	@Autowired
	AdvanceSettlementService aas;
	
	@Autowired
	AddExpenditureService aes;
	
	@Autowired 
	AmountPaidackService aps;
	
	@Autowired
	GoodsReturnedService grs;
	@Autowired
	PhyDelivService phs;
	@Autowired
	AsComplianceService as;
	@Autowired
	ComplCheckListService cs;
	
	@Autowired 
	EsGenerationRep es;
	
	
	@Autowired 
	BudgetFundRep bfr;
	
	@Autowired 
	ActivityBudgetFundRep abff;
	
	
	@Autowired 
	AddExpenditureRep aer;
	
	@Autowired 
	AmountPaidBackRep apbr;

	
	@RequestMapping(value="/advanceExpSettlementView")
	public ModelAndView advanceExpSettlementView(ModelAndView mv) {	
		System.out.println("entrrr in advanceExpSettlementView");
		mv.setViewName("ACTIVITYADVANCE/AdvanceSettlementorSanction");
		return mv;
	}
	@RequestMapping(value="/facultyAdvanceExpSettlementView")
	public ModelAndView facultyAdvanceExpSettlementView(ModelAndView mv) {	
		System.out.println("entrrr in advanceExpSettlementView");
		mv.setViewName("ACTIVITYADVANCE/facultyRecomAdvSetlment");
		return mv;
	}
	
	
	@RequestMapping(value="/editAdvanceExpSettlementView")
	public ModelAndView editAdvanceExpSettlementView(ModelAndView mv) {	
		System.out.println("entrrr in advanceExpSettlementView");
		mv.setViewName("ACTIVITYADVANCE/AdvanceSettlementEdit");
		return mv;
	}
	
	
	
	//add advance request Details
	@RequestMapping(value="/saveAdvanceSettlementBasicDetails",method= RequestMethod.POST)
	public AdvExpSettlement saveAdvanceSettlementBasicDetails(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			saved=aas.saveAdvanceSettlementBasicDetails(af,userCode);
			}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	//add advance request Details
	@RequestMapping(value="/editAdvanceSettlementBasicDetails",method= RequestMethod.POST)
	public AdvExpSettlement editAdvanceSettlementBasicDetails(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			saved=aas.editAdvanceSettlementBasicDetails(af,userCode);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	
	
	@RequestMapping(value="/saveAmountPaidBacktoUniversityDetails",method= RequestMethod.POST)
	public String saveAmountPaidBacktoUniversityDetails(@RequestBody AmountPaidBack af,@RequestHeader MultiValueMap<String, String> headers) {
		String saved=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			
			
			saved=aps.saveAmountPaidBacktoUniversityDetails(af,userCode);
		}
		catch(Exception e)
		{
			
		}
		
		return saved;
		
	}
	
	@RequestMapping(value="/editAmountPaidBacktoUniversityDetails",method= RequestMethod.POST)
	public String editAmountPaidBacktoUniversityDetails(@RequestBody AmountPaidBack af,@RequestHeader MultiValueMap<String, String> headers) {
		String saved=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			saved=aps.editAmountPaidBacktoUniversityDetails(af,userCode);
		}
		catch(Exception e)
		{
			
		}
		
		return saved;
		
	}
	
	@RequestMapping(value="/advanceExpenditureAddForm")
	public ModelAndView advanceExpenditureAddForm(ModelAndView mv) {	
		System.out.println("entrrr in advanceExpenditureAddForm");
		mv.setViewName("ACTIVITYADVANCE/AdvanceSettlementorSanction");
		return mv;
	}
	
	
	@RequestMapping(value="/saveExpenditureDetails",method= RequestMethod.POST)
	public String saveExpenditureDetails(@RequestBody AdvExpenditure af,@RequestHeader MultiValueMap<String, String> headers) {
		String saved=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			
			
			saved=aes.saveExpenditureDetails(af,userCode);
		}
		catch(Exception e)
		{
			
		}
		
		return saved;
		
	}
	
	@RequestMapping(value="/saveGoodsDetails",method= RequestMethod.POST)
	public String saveGoodsDetails(@RequestBody GoodsReturned af,@RequestHeader MultiValueMap<String, String> headers) {
		String saved=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			
			
			saved=grs.saveGoodsDetails(af,userCode);
		}
		catch(Exception e)
		{
			
		}
		
		return saved;
		
	}
	
	@RequestMapping(value="/saveSettlementRemarks",method= RequestMethod.POST)
	public String saveSettlementRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		String msg=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			saved=aas.saveSettlementRemarks(af,userCode);
			if(saved!=null)
			{
				msg="settlement remarks are successfully added";
				
			}
			else
			{
				msg="error in adding remarks";
			}
		}
		catch(Exception e)
		{
			
		}
		
		return msg;
		
	}
	
	
	@RequestMapping(value="/editSettlementRemarks",method= RequestMethod.POST)
	public String editSettlementRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		String msg=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			saved=aas.saveSettlementRemarks(af,userCode);
			if(saved!=null)
			{
				msg="settlement remarks are successfully added";
				
			}
			else
			{
				msg="error in adding remarks";
			}
		}
		catch(Exception e)
		{
			
		}
		
		return msg;
		
	}
	
	
	
	
	@RequestMapping(value="/saveFacultyApprovalSettlementRemarks",method= RequestMethod.POST)
	public String saveFacultyApprovalSettlementRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		String msg=null;
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			
			saved=aas.facultyApprovalRemarks(af,userCode);
			if(saved!=null)
			{
				msg="Faculty remarks are successfully added";
				
			}
			else
			{
				msg="error in adding remarks";
			}
		}
		catch(Exception e)
		{
			
		}
		
		return msg;
		
	}
	
	//add advance request Details
	@RequestMapping(value="/loadAllExpenditureDetailsSubmitted",method= RequestMethod.GET)
	public List<AdvExpSettlement> loadAllExpenditureDetailsSubmitted(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			
			al=aas.loadAllExpenditureDetailsSubmitted();
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	@RequestMapping(value="/loadAllExpenditureDetailsSubmittedForEdit",method= RequestMethod.GET)
	public List<AdvExpSettlement> loadAllExpenditureDetailsSubmittedForEdit(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
		AdvExpSettlement saved=null;
		List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
		try
		{
			
			al=aesr.loadAllExpenditureDetailsSubmittedForEdit();
		}
		catch(Exception e)
		{
			
		}
		return al;
	}
	
	
	@RequestMapping(value="/advSettlementOfficeVerification")
	public ModelAndView advSettlementOfficeVerification(ModelAndView mv) {	
		System.out.println("entrrr in advSettlementOfficeVerification");
		mv.setViewName("ACTIVITYADVANCE/ExpenditureOfficeVerification");
		return mv;
	}
	
	//add advance request Details
	@RequestMapping(value="/loadAllExpenditureDetailsSubmittedBySettlementId",method= RequestMethod.GET)
	public AdvExpSettlement loadAllExpenditureDetailsSubmittedBySettlementId(@RequestParam String settlementId) {
		AdvExpSettlement saved=null;
		
		try
		{
			
			System.out.println("loadAllExpenditureDetailsSubmittedBySettlementId"+settlementId);
			int setid=Integer.parseInt(settlementId);
			
			saved=aas.loadAllExpenditureDetailsSubmittedBySettlementId(setid);
			System.out.println(saved);
		}
		catch(Exception e)
		{
			
		}
		return saved;
	}
	
	//add advance request Details
		@RequestMapping(value="/loadAllAmountPaidBackBySettlementId",method= RequestMethod.GET)
		public List<AmountPaidBack> loadAllAmountPaidBackBySettlementId(@RequestParam String settlementId) {
			List<AmountPaidBack> al=new ArrayList<AmountPaidBack>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				int setid=Integer.parseInt(settlementId);
				
				al=aps.loadAllAmountPaidBackBySettlementId(setid);
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureBySettlementId",method= RequestMethod.GET)
		public List<AdvExpenditure> loadAllExpenditureBySettlementId(@RequestParam String settlementId) {
			List<AdvExpenditure> al=new ArrayList<AdvExpenditure>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				
				
				al=aes.loadAllExpenditureBySettlementId(settlementId);
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		
		@RequestMapping(value="/loadExpenditureByexpenditureId",method= RequestMethod.GET)
		public AdvExpenditure loadExpenditureByexpenditureId(@RequestParam String expenditureId) {
			AdvExpenditure al=null;
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+expenditureId);
				
				
				al=aes.loadExpenditureByexpenditureId(expenditureId);
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		
		@RequestMapping(value="/updateExpenditureDetails",method= RequestMethod.POST)
		public String updateExpenditureDetails(@RequestBody AdvExpenditure af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=aes.updateExpenditureDetails(af,userCode);
			}
			catch(Exception e)
			{
				
			}
			
			return saved;
			
		}
		
		
		@RequestMapping(value="/editExpenditureDetails",method= RequestMethod.POST)
		public String editExpenditureDetails(@RequestBody AdvExpenditure af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=aes.editExpenditureDetails(af,userCode);
			}
			catch(Exception e)
			{
				
			}
			
			return saved;
			
		}
		
		
		@RequestMapping(value="/loadAllGoodsReturnBySettlementId",method= RequestMethod.GET)
		public List<GoodsReturned> loadAllGoodsReturnBySettlementId(@RequestParam String settlementId) {
			List<GoodsReturned> al=new ArrayList<GoodsReturned>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				
				
				al=grs.loadAllGoodsReturnBySettlementId(settlementId);
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		
		@RequestMapping(value="/getToalAmountExpenditureBySettlementIdAndHeadId",method= RequestMethod.GET)
		public List<AdvExpenditure> getToalAmountExpenditureBySettlementIdAndHeadId(@RequestParam String settlementId,@RequestParam String exphead) {
			List<AdvExpenditure> al=null;
			
			try
			{
				
				
				al=aes.getToalAmountExpenditureBySettlementIdAndHeadId(settlementId,exphead);
				//System.out.println("al"+al.getTotalAmt());
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		
		@RequestMapping(value="/physicalDelivarableDetails",method= RequestMethod.POST)
		public String physicalDelivarableDetails(@RequestBody PhyDeliv af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=phs.physicalDelivarableDetails(af,userCode);
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return saved;
		}
		
		
		
		@RequestMapping(value="/loadAllphysicalDelivarablesDetails",method= RequestMethod.GET)
		public List<PhyDeliv> loadAllphysicalDelivarablesDetails(@RequestParam String settlementId) {
			List<PhyDeliv> al=new ArrayList<PhyDeliv>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				al=phs.loadAllphysicalDelivarablesDetails(settlementId);
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		@RequestMapping(value="/editPhysicalDelivarableDetails",method= RequestMethod.GET)
		public String editPhysicalDelivarableDetails(@RequestParam String expId,@RequestParam String recom,@RequestParam String recomRemarks)
		{
			String msg=null;
			PhyDeliv al=null;
			try
			{
				int a=phs.editPhysicalDelivarableDetails(expId, recom, recomRemarks);
				if(a!=0)
				{
					msg="Physical Deliverables Justifcation Successfully Added";
				}
				else
				{
					msg="Error in adding Justification";
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return msg;
		}
		@RequestMapping(value="/addCompliance",method= RequestMethod.POST)
		public String complianceDetails(@RequestBody AsCompliance af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=as.complianceDetails(af,userCode);
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return saved;
		}
		@RequestMapping(value="/addProceduralcompliance",method= RequestMethod.POST)
		public String addProceduralcompliance(@RequestBody ComplCheckList af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=cs.addProceduralcompliance(af,userCode);
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return saved;
		}
		
		
		
		@RequestMapping(value="/editProceduralcompliance",method= RequestMethod.POST)
		public String editProceduralcompliance(@RequestBody ComplCheckList af,@RequestHeader MultiValueMap<String, String> headers) {
			String saved=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				
				
				saved=cs.editProceduralcompliance(af,userCode);
			}
			catch(Exception e)
			{
				System.out.println("Exception e"+e);
			}
			
			return saved;
		}
		
		
		@RequestMapping(value="/saveSettlementOfficerRemarks",method= RequestMethod.POST)
		public String saveSettlementOfficerRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementOfficerRemarks(af,userCode);
				if(saved!=null)
				{
					msg="settlement Officer remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}
		

		@RequestMapping(value="/saveSettlementFinanceOfficeRemarks",method= RequestMethod.POST)
		public String saveSettlementFinanceOfficeRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementOfficeFinancialRemarks(af,userCode);
				if(saved!=null)
				{
					msg="settlement finance Office Verification remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}
		
		
		
		@RequestMapping(value="/advSettlementAdmiistrativeVerification")
		public ModelAndView advSettlementAdmiistrativeVerification(ModelAndView mv) {	
			System.out.println("entrrr in advSettlementAdmiistrativeVerification");
			mv.setViewName("ACTIVITYADVANCE/ExpenditureSettlementAdministrativeApproval");
			return mv;
		}
		
		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureDetailsOfficeRecommended",method= RequestMethod.GET)
		public List<AdvExpSettlement> OfficeRecommended(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
			try
			{
				
				al=aas.loadAllExpenditureDetailsOfficeRecommended();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/loadAllCompliances",method= RequestMethod.GET)
		public List<AsCompliance> loadAllCompliances(@RequestParam String settlementId) {
			List<AsCompliance> al=new ArrayList<AsCompliance>();
			
			try
			{
				
				System.out.println("loadAllCompliances"+settlementId);
				al=as.loadAllCompliances(settlementId);
		
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/editCompliance",method= RequestMethod.POST)
		public String editCompliance(@RequestBody AsCompliance af,@RequestHeader MultiValueMap<String, String> headers)
		{
			String msg=null;
			AsCompliance al=null;
			try
			{
		
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
                msg=as.editCompliance(af,userCode);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			return msg;
		}
		
		
		@RequestMapping(value="/viewComplianceToProceduralRequirements",method= RequestMethod.GET)
		public List<ComplCheckList> viewComplianceToProceduralRequirements(@RequestParam String settlementId) {
			List<ComplCheckList> al=new ArrayList<ComplCheckList>();
			
			try
			{
				
				System.out.println("loadAllCompliances"+settlementId);
				//al=as.loadAllCompliances(settlementId);
				
				al=cs.viewComplianceToProceduralRequirements(settlementId);
		
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/saveSettlementAdministrativeRemarks",method= RequestMethod.POST)
		public String saveSettlementAdministrativeRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementAdministrativeRemarks(af,userCode);
				if(saved!=null)
				{
					msg="Administrative Approval remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}
		@RequestMapping(value="/saveSettlementFinancialRemarks",method= RequestMethod.POST)
		public String saveSettlementFinancialRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementFinancialRemarks(af,userCode);
				if(saved!=null)
				{
					msg="Financial Approval remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}
		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureDetailsAdministrativeApproved",method= RequestMethod.GET)
		public List<AdvExpSettlement> loadAllExpenditureDetailsAdministrativeApproved(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
			try
			{
				
				al=aas.loadAllExpenditureDetailsAdministrativeApproved();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		
		
		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureDetailsFinanceApproved",method= RequestMethod.GET)
		public List<AdvExpSettlement> loadAllExpenditureDetailsFinanceApproved(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
			try
			{
				
				al=aas.loadAllExpenditureDetailsFinanceApproved();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureDetailsFinalOfficeApproved",method= RequestMethod.GET)
		public List<AdvExpSettlement> loadAllExpenditureDetailsFinalOfficeApproved(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
			try
			{
				
				al=aesr.loadAllExpenditureDetailsFinalOfficeApproved();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		//add advance request Details
				@RequestMapping(value="/loadAllExpenditureDetailsFinanceOfficeApproved",method= RequestMethod.GET)
				public List<AdvExpSettlement> loadAllExpenditureDetailsFinanceOfficeApproved(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
					AdvExpSettlement saved=null;
					List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
					try
					{
						
						al=aesr.loadAllExpenditureDetailsFinanceOfficeApproved();
					}
					catch(Exception e)
					{
						
					}
					return al;
				}
		
		@RequestMapping(value="/advSettlementFinancialVerification")
		public ModelAndView advSettlementFinancialVerification(ModelAndView mv) {	
			System.out.println("entrrr in advSettlementAdmiistrativeVerification");
			mv.setViewName("ACTIVITYADVANCE/FinancialApprovalAdvSettlement");
			return mv;
		}
		
		@RequestMapping(value="/advSettlementFinalVerification")
		public ModelAndView advSettlementFinalVerification(ModelAndView mv) {	
			System.out.println("entrrr in advSettlementAdmiistrativeVerification");
			mv.setViewName("ACTIVITYADVANCE/FinalApprovalAdvSettlementjsp");
			return mv;
		}
		
		@RequestMapping(value="/saveSettlementFinalRemarks",method= RequestMethod.POST)
		public String saveSettlementFinalRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementFinalRemarks(af,userCode);
				if(saved!=null)
				{
					msg="Final Approval remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}

		@RequestMapping(value="/saveSettlementFinalOfficeRemarks",method= RequestMethod.POST)
		public String saveSettlementFinalOfficeRemarks(@RequestBody AdvanceSanctionRemarks af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				
				saved=aas.saveSettlementFinalOfficeRemarks(af,userCode);
				if(saved!=null)
				{
					msg="Final Office Approval remarks are successfully added";
					
				}
				else
				{
					msg="error in adding remarks";
				}
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}

		//add advance request Details
		@RequestMapping(value="/loadAllExpenditureDetailsSanctioned",method= RequestMethod.GET)
		public List<AdvExpSettlement> loadAllExpenditureDetailsSanctioned(@RequestBody AdvExpSettlement af,@RequestHeader MultiValueMap<String, String> headers) {
			AdvExpSettlement saved=null;
			List<AdvExpSettlement> al=new ArrayList<AdvExpSettlement>();
			try
			{
				
				al=aesr.loadAllExpenditureDetailsSanctioned();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/esGeneration")
		public ModelAndView esGeneration(ModelAndView mv) {	
			System.out.println("entrrr in advSettlementAdmiistrativeVerification");
			mv.setViewName("ACTIVITYADVANCE/EsGeneration");
			return mv;
		}
		
		
		@RequestMapping(value="/esOrderGeneration",method= RequestMethod.POST)
		public Esgeneration OrderGenaration(@RequestBody Esgeneration af,@RequestHeader MultiValueMap<String, String> headers) {
			Esgeneration saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = aas.esOrderGenaration(af,userCode);
				
			}
			catch(Exception e)
			{
				
			}
			
			return saved;
			
			
		}
		
		//add advance request Details
		@RequestMapping(value="/loadAllEsGeneration",method= RequestMethod.GET)
		public List<Esgeneration> loadAllEsGeneration() {
			Esgeneration saved=null;
			List<Esgeneration> al=new ArrayList<Esgeneration>();
			try
			{
				
				al=es.loadAllEsgenerationList();
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		//add advance request Details
		@RequestMapping(value="/getEsOrderGenerationInformation",method= RequestMethod.GET)
		public Esgeneration getEsOrderGenerationInformation(@RequestParam String orderGenId) {
			Esgeneration saved=null;
			
			try
			{
				
				saved=es.getEsOrderGenerationInformation(Integer.parseInt(orderGenId));
			}
			catch(Exception e)
			{
				
			}
			return saved;
		}
		
		@RequestMapping(value="/esOrderGenarationEdit",method= RequestMethod.POST)
		public String esOrderGenarationEdit(@RequestBody Esgeneration af ,@RequestHeader MultiValueMap<String, String> headers) {
			Esgeneration saved=null;
			 String msg=null;
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				 saved = aas.esOrderGenarationEdit(af,userCode);
				 

				 if(saved == null)
					{
						
						msg="NOTSAVED-"+"Error in Generating ES order ";	
					}
					else
					{
					String display = "ES Order is successfully edited ";
						msg="SAVED-"+display;
					}
				
				 
				 
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
			
		}
		@RequestMapping(value="/saveActivityBudgetFund",method= RequestMethod.POST)
		public String saveActivityBudgetFund(@RequestBody ActivityBudgetFund cfw,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityBudgetFund saved = null;
			String msg ="";
			
			try
			{
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
			
				saved = aas.saveBudgetFund(cfw,userCode);
				System.out.println(saved);
				
				
				if(saved == null)
				{
					
					msg="NOTSAVED-"+"Error in adding Budget Fund Details against activity  ";
					
					
					
				}
				else
				{
				String display = "Budget Fund Mapping against an activity is successfully saved";
					msg="SAVED-"+display;
				}
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		
		
		
		@RequestMapping(value="/updateActivityBudgetFund",method= RequestMethod.POST)
		public String updateActivityBudgetFund(@RequestBody ActivityBudgetFund cfw,@RequestHeader MultiValueMap<String, String> headers) {
			
			ActivityBudgetFund saved = null;
			String msg ="";
			
			try
			{
				
				System.out.println("updateActivityBudgetFund"+cfw.getActivityBudgetFundId());
				UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
				String userCode = user.getUsercode();
				System.out.println("User : "+userCode);	
			
				saved = aas.updateBudgetFund(cfw,userCode);
				System.out.println(saved);
				
				
				if(saved == null)
				{
					
					msg="NOTUPDATED-"+"Error in updating activity Budget Fund Details against activity  ";
					
					
					
				}
				else
				{
				String display = "Budget Fund Mapping against an activity is successfully updated";
					msg="UPDATED-"+display;
				}
				
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
			}
			
			return  msg;
			
			
		}
		
		
		@RequestMapping(value="/getBudgetFundAmount",method=RequestMethod.GET)
		public BudgetHeadFund getBudgetFundAmount(@RequestParam String budheadId,@RequestParam String finyear)
		{
			BudgetHeadFund bf=bfr.getBudgetFundAmount(Integer.parseInt(budheadId),finyear);
			System.out.println("bf=="+bf.getFundAmount());
			return bf;
					
		}
		
		
		
		@RequestMapping(value="/getBudgetHeadAmountForAnactivity",method=RequestMethod.GET)
		public ActivityBudgetFund getBudgetHeadAmountForAnactivity(@RequestParam String budheadId,@RequestParam String finyear,@RequestParam String activityId )
		{
			ActivityBudgetFund bf=abff.getBudgetHeadAmountForAnactivity(Integer.parseInt(budheadId),finyear,Integer.parseInt(activityId));
			
			return bf;
					
		}
		
		
		@RequestMapping(value="/getActivityBudHeadDetails",method=RequestMethod.GET)
		public List<getActivityBudgetHeadDetails> getActivityBudHeadDetails(@RequestParam String activityId)
		{
			//BudgetHeadFund bf=bfr.get(Integer.parseInt(budheadId),finyear);
			//System.out.println("bf=="+bf.getFundAmount());
			 List<getActivityBudgetHeadDetails> abf=null;
			 abf=abff.getActivityBudHeadDetails(Integer.parseInt(activityId));
			 return abf;
					
		}
		
		@RequestMapping(value="/getActivityBudHeadDetailsbyParticipation",method=RequestMethod.GET)
		public List<getActivityBudgetHeadDetails> getActivityBudHeadDetailsbyParticipation(@RequestParam String pr)
		{
			//BudgetHeadFund bf=bfr.get(Integer.parseInt(budheadId),finyear);
			//System.out.println("bf=="+bf.getFundAmount());
			 List<getActivityBudgetHeadDetails> abf=null;
			 abf=abff.getActivityBudHeadDetailsbyParticipation(Integer.parseInt(pr));
			 return abf;
					
		}
		
		
		
		
/*		@RequestMapping(value="/saveAdvanceSettlement",method= RequestMethod.POST)
		public String saveAdvanceSettlement(@RequestParam String programApprovalAdvancePaymentId,@RequestParam String settledAmnt,@RequestParam String remark,HttpServletRequest ht) {
			ProgramApprovalAdvancePayment saved=null;
			String msg=null;
			try
			{
			
			}
			catch(Exception e)
			{
				
			}
			
			return msg;
			
		}*/
		
		@RequestMapping(value="/getBudgetHeadAmountForParticipation",method=RequestMethod.GET)
		public ActivityBudgetFund getBudgetHeadAmountForParticipation(@RequestParam String budheadId,@RequestParam String finyear,@RequestParam String pr )
		{
			ActivityBudgetFund bf=abff.getBudgetHeadAmountForParticipation(Integer.parseInt(budheadId),finyear,Integer.parseInt(pr));
			
			return bf;
					
		}
		
		
		@RequestMapping(value="/loadAllExpenditureBySettlementIdByInteface",method= RequestMethod.GET)
		public List<expendituretb> loadAllExpenditureBySettlementIdByInteface(@RequestParam String settlementId) {
			List<expendituretb> al=new ArrayList<expendituretb>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				
				
				al=aer.loadAllExpenditureBySettlementIdByInteface(Integer.parseInt(settlementId));
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/loadAllExpenditureBySettlementIdByIntefaceForResourcePerson",method= RequestMethod.GET)
		public List<expendituretb> loadAllExpenditureBySettlementIdByIntefaceForResourcePerson(@RequestParam String settlementId) {
			List<expendituretb> al=new ArrayList<expendituretb>();
			
			try
			{
				
				System.out.println("loadAllAmountPaidBackBySettlementId"+settlementId);
				
				
				al=aer.loadAllExpenditureBySettlementIdByIntefaceForResourcePerson(Integer.parseInt(settlementId));
			
			}
			catch(Exception e)
			{
				
			}
			return al;
		}
		
		@RequestMapping(value="/getBudgetHeadAmountByactivityCode",method=RequestMethod.GET)
		public List<asBudheadDetails> getBudgetHeadAmountByactivityCode(@RequestParam String activityCode,@RequestParam String finyear )
		{
			List<asBudheadDetails> bf=abff.getActivityBudHeadForAs(Integer.parseInt(activityCode),finyear);
			
			return bf;
					
		}
		//add advance request Details
				@RequestMapping(value="/loadAllAmountPaidBackById",method= RequestMethod.GET)
				public List<AmountPaidBack> loadAllAmountPaidBackById(@RequestParam String paidId) {
					List<AmountPaidBack> al=new ArrayList<AmountPaidBack>();
					
					try
					{
						
						System.out.println("loadAllAmountPaidBackBySettlementId"+paidId);
						int paidId1=Integer.parseInt(paidId);
						
						al=apbr.loadAllAmountPaidBackById(paidId1);
					
					}
					catch(Exception e)
					{
						
					}
					return al;
				}
				
				
				@RequestMapping(value="/advSettlementEdit")
				public ModelAndView advSettlementEdit(ModelAndView mv) {	
					System.out.println("entrrr in advSettlementEdit");
					mv.setViewName("ACTIVITYADVANCE/AdvanceSettlementEdit");
					return mv;
				}
				
				@RequestMapping(value="/editOfficeAdvanceExpSettlementView")
				public ModelAndView editOfficeAdvanceExpSettlementView(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/ExpenditureOfficeVerificationEdit");
					return mv;
				}
				
				@RequestMapping(value="/editAdministrativeAdvanceExpSettlementView")
				public ModelAndView editAdministrativeAdvanceExpSettlementView(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/ExpendituresettlementAdministrativeApprovalEdit");
					return mv;
				}
				
				@RequestMapping(value="/editFinanceAdvanceExpSettlementView")
				public ModelAndView editFinanceAdvanceExpSettlementView(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/FinanceSettlementEdit");
					return mv;
				}
				
				@RequestMapping(value="/financeOfficeVerificationSettlement")
				public ModelAndView financeOfficeVerificationSettlement(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/FinanceAdvanceSettlementOfficeVerificationjsp");
					return mv;
				}
				
				@RequestMapping(value="/financeOfficeVerificationSettlementEdit")
				public ModelAndView financeOfficeVerificationSettlementEdit(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/FinanceOfficeVerificationSettlementEdit");
					return mv;
				}
				//this is original one
				@RequestMapping(value="/financeSettlementEdit")
				public ModelAndView financeSettlementEdit(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/financeApprovalSettlementEdit");
					return mv;
				}
				@RequestMapping(value="/finalOfficeSettlement")
				public ModelAndView finalOfficeSettlement(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/finalOfficer");
					return mv;
				}
				@RequestMapping(value="/finalOfficeSettlementEdit")
				public ModelAndView finalOfficeSettlementEdit(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/FinalOfficeVerificationExpSanctionEdit");
					return mv;
				}
				@RequestMapping(value="/finalSettlementEdit")
				public ModelAndView finalSettlementEdit(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/FinalSettlementEdit");
					return mv;
				}
				@RequestMapping(value="/esGenerationEdit")
				public ModelAndView esGenerationEdit(ModelAndView mv) {	
					System.out.println("entrrr in advanceExpSettlementView");
					mv.setViewName("ACTIVITYADVANCE/esgenerationEdit");
					return mv;
				}
}
