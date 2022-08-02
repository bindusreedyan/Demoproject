package NUALS.AMS.ACADEMIC.ACTIVITIES;

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
import NUALS.AMS.ACADEMIC.CENTER.CenterFund;
import NUALS.AMS.ACADEMIC.CENTER.CenterFundService;
import NUALS.AMS.ACADEMIC.CENTER.CenterFundWrapper;
@RequestMapping("/expenditurehead")
@RestController
public class ExpHeadController {
	
	@Autowired
	ExpHeadService expHeadService;
	
	@Autowired
	ExpHeadNameRep expnr;
	
	@Autowired
	ExpHeadNameRep enr;
	
	
	@Autowired 
	ExpenditureHeadRepository ehr;
	
	
	
	@Autowired 
	BudgetFundRep bfr;
	//center fund code starts from here-----------------------------to be added in jayaram sir----
	@RequestMapping(value="/addExpenditureHead")
	public ModelAndView addCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/Add_Exp_Head");
		return mv;
		
		
	}
	
	@RequestMapping(value="/addExpenditureHeadName")
	public ModelAndView addExpHead( ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/ExpHeadAdd");
		return mv;
		
		
	}
	
	
	@RequestMapping(value="/addExpenditureHeadAmount",method= RequestMethod.POST)
	public String saveCenterFund(@RequestBody ExpHeadMasterWrapper expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHeadsMaster saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			 msg = expHeadService.saveExpHeadMaster(expHm,userCode);
			System.out.println("msg"+msg);
					
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/verifiyExpenditureHead",method= RequestMethod.POST)
	public String verifiyExpenditureHead(@RequestBody ExpHeadMasterWrapper expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHeadsMaster saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			 msg = expHeadService.verifyExpHeadMaster(expHm,userCode);
			//System.out.println(saved);
					
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/approveExpenditureHead",method= RequestMethod.POST)
	public String approveExpenditureHead(@RequestBody ExpHeadMasterWrapper expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHeadsMaster saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			 msg = expHeadService.approveExpHeadMaster(expHm,userCode);
			//System.out.println(saved);
					
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/updateExpenditureHead",method= RequestMethod.POST)
	public String updateExpenditureHead(@RequestBody ExpHeadMasterWrapper expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHeadsMaster saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			 msg = expHeadService.updateExpHeadMaster(expHm,userCode);
			//System.out.println(saved);
					
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	
	@RequestMapping(value="/getAllExpHeadDetails")
	public List<ExpHeadsMaster> getAllExpHeadDetails()
	{
		System.out.println("entrr in fund details");
		return expHeadService.getAllExpHeadDetails();
	}
	
	
	@RequestMapping(value="/getAllExpHeadDetailswithoutStatus")
	public List<ExpHeadsMaster> getAllExpHeadDetailswithoutStatus()
	{
		System.out.println("entrr in fund details");
		return expHeadService.getAllExpHeadDetailswithoutStatus();
	}
	
	
	
	@RequestMapping(value="/getExpenditureHeadsByFinYear")
	public List<ExpHeadsMaster> getExpenditureHeadsByFinYear(@RequestParam String finyear)
	{
		System.out.println("getExpenditureHeadsByFinYear");
		return expHeadService.getExpenditureHeadsByFinYear(finyear);
	}
	
	
	@RequestMapping(value="/getExpHeadsrecordBydescriptionAndFinyear")
	public ExpHeadsMaster getExpHeadsrecordBydescriptionAndFinyear(@RequestParam String description,@RequestParam String finyear)
	{
		System.out.println("getExpenditureHeadsByFinYear");
		return ehr.getExpHeadsrecordBydescriptionAndFinyear(description,finyear);
	}
	
	
	
	@RequestMapping(value="/getExpenditureHeadsUnassignGrantByFinYear")
	public ExpHeadsMaster getExpenditureHeadsUnassignGrantByFinYear(@RequestParam String finyear)
	{
		
		String des="Unassigned Grant";
		return expHeadService.getExpHeadDetails(des,finyear);
	}
	
	
	@RequestMapping(value="/addExpenditureHeadNames",method= RequestMethod.POST)
	public String addExpenditureHeadNames(@RequestBody ExpHead expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHead saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.saveExpHeadNames(expHm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				
				
				msg="NOTSAVED-"+"Error in adding Expenditure Head Details ";
				
				
				
			}
			else
			{
			String display = "Expenditure Head: "+saved.getExpHeadName()+"     is successfully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at save ExpHead Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/verifyExpenditureHeadNames",method= RequestMethod.POST)
	public String verifyExpenditureHeadNames(@RequestBody ExpHead expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHead saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.verifyExpHeadNames(expHm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				
				
				msg="Error in verifying Expenditure Head Details";
				
				
				
			}
			else
			{
			String display = "Verified Successfully";
				msg=display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at save ExpHead Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/approveExpenditureHeadNames",method= RequestMethod.POST)
	public String approveExpenditureHeadNames(@RequestBody ExpHead expHm,@RequestHeader MultiValueMap<String, String> headers) {
		
		ExpHead saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.approveExpHeadNames(expHm,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				
				
				msg="Error in verifying Expenditure Head Details";
				
				
				
			}
			else
			{
			String display = "Verified Successfully";
				msg=display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at save ExpHead Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	
	@RequestMapping(value="/getExpHeadDetailsbyHeadid")
	public ExpHead getExpHeadDetailsbyHeadid(@RequestParam String headId)
	{
		System.out.println("entrr in fund details");
		return enr.getExpHeadDetailsbyHeadid(Integer.parseInt(headId));
	}
	
	
	
	
	
	@RequestMapping(value="/loadAllExpHeads")
	public List<ExpHead> loadAllExpHeads()
	{
		System.out.println("entrr in fund details");
		return expHeadService.getAllExpHeadNameDetails();
	}
	
	@RequestMapping(value="/getAllExpHead")
	public List<ExpHead> getAllExpHead()
	{
		System.out.println("entrr in fund details");
		return enr.getAllExpHead();
	}
	
	@RequestMapping(value="/addbudHeadName")
	public ModelAndView addbudHeadName( ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/AddBudHeadName");
		return mv;
		
		
	}
	@RequestMapping(value="/addBudHeadDetails",method= RequestMethod.POST)
	public String addBudHeadDetails(@RequestBody BudgetHead bh,@RequestHeader MultiValueMap<String, String> headers) {
		
		BudgetHead saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.saveBudgetHead(bh, userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				
				
				msg="NOTSAVED-"+"Error in adding Budget Fund Details .This Record is already Exist";
				
				
				
			}
			else
			{
			String display = "Budget Head: "+saved.getBudHeadName()+"  in   "+saved.getBudHeadId()+"   is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	@RequestMapping(value="/getAllBudgetHeadDetails")
	public List<BudgetHead> getAllBudgetHeadDetails()
	{
		System.out.println("entrr in fund details");
		return expHeadService.getAllBudgetHeadDetails();
	}
	@RequestMapping(value="/addBudgetFund")
	public ModelAndView addBudgetFund(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/BudgetFundAdd");
		return mv;
		
		
	}
	
	@RequestMapping(value="/saveBudgetFund",method= RequestMethod.POST)
	public String saveBudgetFund(@RequestBody BudgetFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		BudgetHeadFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.saveBudgetFund(cfw,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="NOTSAVED-"+"Error in adding Budget Fund Details and This record is already exist ";
				
				
				
			}
			else
			{
			String display = "Budget Fund is successfully saved";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/verifyBudgetFunds",method= RequestMethod.POST)
	public String verifyBudgetFund(@RequestBody BudgetFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		BudgetHeadFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.verifyBudgetFund(cfw,userCode);
		
			System.out.println("savedddd"+saved);
			
			if(saved == null)
			{
				
				msg="Error in verifying Budget Fund Details  ";
				
				
				
			}
			else
			{
			String display = "Budget Fund is successfully verified";
				msg=display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
	
	}
	
	
	
	@RequestMapping(value="/approveBudgetFund",method= RequestMethod.POST)
	public String approveBudgetFund(@RequestBody BudgetFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		BudgetHeadFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.approveBudgetFund(cfw,userCode);
		
			System.out.println("savedddd"+saved);
			
			if(saved == null)
			{
				
				msg="Error in approving Budget Fund Details  ";
				
				
				
			}
			else
			{
			String display = "Budget Fund is successfully approved";
				msg=display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/editBudgetFunds",method= RequestMethod.POST)
	public String editBudgetFunds(@RequestBody BudgetFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		BudgetHeadFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = expHeadService.editBudgetFunds(cfw,userCode);
		
			System.out.println("savedddd"+saved);
			
			if(saved == null)
			{
				
				msg="Error in verifying Budget Fund Details  ";
				
				
				
			}
			else
			{
			String display = "Budget Fund is successfully edited";
				msg=display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value="/loadAllEstimateHeads")
	public List<EstimateHeads> loadAllEstimateHeads(@RequestParam int activityCode)
	{
		System.out.println("entrr in fund details");
		return enr.getAllEstimateHeadsSubmitted(activityCode);
	}
	/*@RequestMapping(value="/addBudgetFund")
	public ModelAndView budgetFund( ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/BudgetFundAdd");
		return mv;
		
		
	}*/
	
	@RequestMapping(value="/getBudgetFundAmount")
	public BudgetHeadFund getBudgetFundAmount(String budheadId,String finYear)
	{
		System.out.println("getExpenditureHeadsByFinYear");
		return expHeadService.getBudgetFundAmount(budheadId,finYear);
	}
	@RequestMapping(value="/getExpHeadDetails")
	public ExpHead getExpHeadDetails(@RequestParam String headId)
	{
		
		return expnr.getExpHeadDetails(headId);
	}
	
	@RequestMapping(value="/getAllBudgetFundDetails")
	public List<BudgetHeadFund> getAllBudgetFundDetails()
	{
		System.out.println("entrr in fund details");
		return bfr.getAllBudgetHeadDetails();
	}
	
	@RequestMapping(value="/getAllBudgetHeadDetailswithNostatus")
	public List<budheadinterface> getAllBudgetHeadDetailswithNostatus()
	{
		System.out.println("entrr in fund details");
		return bfr.getAllBudgetHeadDetailswithNostatus();
	}
	
	@RequestMapping(value="/verifyBudgetFund")
	public ModelAndView verifyBudgetFund(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/budgetFundVerify");
		return mv;
		
		
	}
	
	                      
	@RequestMapping(value="/approveBudgetFundView")
	public ModelAndView approveBudgetFundView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/ApproveBudgetFundAmount");
		return mv;
	}
	
	@RequestMapping(value="/editBudgetFundView")
	public ModelAndView editBudgetFundView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/editBudgetFund");
		return mv;
	}
	@RequestMapping(value="/verifyExpHeadView")
	public ModelAndView verifyExpHeadView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/verifyExpHeadAmount");
		return mv;

	}
	
	@RequestMapping(value="/approveExpHeadView")
	public ModelAndView approveExpHeadView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/approveExpHeadAmount");
		return mv;

	}
	
	@RequestMapping(value="/editExpHeadView")
	public ModelAndView editExpHeadView(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/EditExpHeadAmount");
		return mv;

	}	
	
	@RequestMapping(value="/verifybudHeadName")
	public ModelAndView verifybudHeadName(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/verifyExpheaf");
		return mv;

	}
	
	
	@RequestMapping(value="/approveExpdHeadName")
	public ModelAndView approveExpdHeadName(ModelAndView mv) {	
		System.out.println("entrrr in addExpenditureHead");
		mv.setViewName("ACADEMICS/approveExpheadName.jsp");
		return mv;

	}
	
}
