package NUALS.AMS.ACADEMIC.CENTER;

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
import NUALS.AMS.ACADEMIC.ACTIVITIES.RESOURCEPERSONS.ResourcePersonMaster;
@RequestMapping("/centerfund")
@RestController
public class CenterFundController {
	@Autowired
	CenterFundService centerService;
	//center fund code starts from here-----------------------------to be added in jayaram sir----
	@RequestMapping(value="/addCenteFundView")
	public ModelAndView addCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("CENTER/AddCenterFund");
		return mv;
	}
	
	@RequestMapping(value="/verifyCenteFundView")
	public ModelAndView verifyCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("CENTER/VerifyCenterFund");
		return mv;
	}
	
		@RequestMapping(value="/getAllCenterFundDetails")
	public List<CenterFund> getAllCenterFundDetails()
	{
		System.out.println("entrr in fund details");
		return centerService.getAllCenterFundDetails();
	}
		
		@RequestMapping(value="/getAllCenterFundDetailsinAllStatus")
	public List<CenterFund> getAllCenterFundDetailsinAllStatus()
	{
		System.out.println("entrr in fund details");
		return centerService.getAllCenterFundSubmittedInAllstatus();
	}
	
	@RequestMapping(value="/getAllCenterFundDetailsByCenterIdAndFinYear")
	public CenterFund getAllCenterFundDetailsByCenterIdAndFinYear(@RequestParam String currentfinyear,@RequestParam String centerId)
	{
		System.out.println("entrr in fund details");
		return centerService.getAllCenterFundDetailsByCenterIdAndFinYear(currentfinyear,centerId);
	}
	
	
	@RequestMapping(value="/getAllCenterFundDetailsByCenterIdsAndFinYear",method= RequestMethod.GET)
	public List<CenterFund>getAllCenterFundDetailsByCenterIdsAndFinYear(@RequestParam(value="centerarray") List<String> centerarray,@RequestParam String currentfinyear )
	{
		System.out.println("size of resource person array-------------"+centerarray.size());
		List<CenterFund> al=new ArrayList<CenterFund>();
//		resIdarrea.forEach((temp) -> {
//	            System.out.println(temp);
//	        });
		
		al=centerService.getAllCenterFundDetailsByCenterIdsAndFinYear(centerarray,currentfinyear);
		
		return al;
	}
	@RequestMapping(value="/updateActivityCenterFund",method= RequestMethod.GET)
	public List<CenterFund> updateActivityCenterFund(@RequestParam(value="centerarray") List<String> centerarray,@RequestParam String finyear)
	{
		System.out.println("entrr in fund details");
		
		
		  List<CenterFund> al=new ArrayList<CenterFund>();
		  al=centerService.updateCentrFund(centerarray,finyear);
		return al;
	}
	@RequestMapping(value="/verifyCenterFund",method= RequestMethod.POST)
	public String verifyCenterFund(@RequestBody CenterFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		CenterFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = centerService.verifyCenterFund(cfw,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="NOT VERIFIED-"+"Error in verifying Center Fund Details";
				
				
				
			}
			else
			{
			String display = "center fund details is successfully verified";
				msg="Verified-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/editCenterFund",method= RequestMethod.POST)
	public String editCenterFund(@RequestBody CenterFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		CenterFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = centerService.editCenterFund(cfw,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="NOT EDITED-"+"Error in editing Center Fund Details";
				
				
				
			}
			else
			{
			String display = "center fund details is successfully edited";
				msg="EDITED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at editing centre fund Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	@RequestMapping(value="/approveCenterFund",method= RequestMethod.POST)
	public String approveCenterFund(@RequestBody CenterFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		CenterFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = centerService.approveCenterFund(cfw,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="NOT APPROVED-"+"Error in approving Center Fund Details";
				
				
				
			}
			else
			{
			String display = "center fund details is successfully approved";
				msg="APPROVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	
	
	@RequestMapping(value="/saveCenterFund",method= RequestMethod.POST)
	public String saveCenterFund(@RequestBody CenterFundWrapper cfw,@RequestHeader MultiValueMap<String, String> headers) {
		
		CenterFund saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
		
			saved = centerService.saveCenterFund(cfw,userCode);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="NOTSAVED-"+"Error in saving Center Fund Details";
				
				
				
			}
			else
			{
			String display = "center fund details is successfully saved";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	@RequestMapping(value="/approveCenteFundView")
	public ModelAndView approveCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("CENTER/ApproveCenterFund");
		return mv;
	}
	@RequestMapping(value="/editCenteFundView")
	public ModelAndView editCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("CENTER/editCenterFund");
		return mv;
	}
}
