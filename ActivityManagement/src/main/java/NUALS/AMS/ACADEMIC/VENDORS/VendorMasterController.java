package NUALS.AMS.ACADEMIC.VENDORS;

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
import NUALS.AMS.ACADEMIC.ACTIVITIES.ExpHeadsMaster;
import NUALS.AMS.ACADEMIC.ADVANCE.ActivityAdvReq;
import NUALS.AMS.ACADEMIC.ADVANCE.AdvanceReqReportClass;

@RestController
@RequestMapping(value="/vendors")
public class VendorMasterController {
	@Autowired
	VendorService vs;
	
	
	@Autowired 
	VendorRep vr;
	
	
	@RequestMapping(value="/showVendorDetailsAddForm")
	public ModelAndView showVendorDetailsAddForm(ModelAndView view)
	{
	view.setViewName("VENDORS/VendorMaster");
	return view;

}
	
	@RequestMapping(value="/showVendorDetailsAEditForm")
	public ModelAndView showVendorDetailsAEditForm(ModelAndView view)
	{
	view.setViewName("VENDORS/VendorMasterEdit");
	return view;

}

	@RequestMapping(value="/addVendorDetails",method= RequestMethod.POST)
	public String addVendorDetails(@RequestBody VendorMaster af,@RequestHeader MultiValueMap<String, String> headers)
	{
	   String msg=null;
	   VendorMaster saved=null;
	   
	   try
	   {
		    UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    msg=vs.addVendorDetails(af,userCode);
		  
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return msg;
	}
	@RequestMapping(value="/editVendorDetails",method= RequestMethod.POST)
	public String editVendorDetails(@RequestBody VendorMaster af,@RequestHeader MultiValueMap<String, String> headers)
	{
	   String msg=null;
	   VendorMaster saved=null;
	   
	   try
	   {
		  
		  
		    UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    msg=vs.editVendorDetails(af,userCode);
		  
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return msg;
	}
	
	@RequestMapping(value="/checkVendorDetails",method= RequestMethod.POST)
	public String checkVendorDetails(@RequestBody VendorMaster af,@RequestHeader MultiValueMap<String, String> headers)
	{
	   String msg=null;
	   VendorMaster saved=null;
	   
	   try
	   {
		  
		  
		    UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    msg=vs.checkVendorDetails(af,userCode);
		  
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return msg;
	}
	
	@RequestMapping(value="/verifyVendorDetails",method= RequestMethod.POST)
	public String verifyVendorDetails(@RequestBody VendorMaster af,@RequestHeader MultiValueMap<String, String> headers)
	{
	   String msg=null;
	   VendorMaster saved=null;
	   
	   try
	   {
		  
		  
		    UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    msg=vs.verifyVendorDetails(af,userCode);
		  
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return msg;
	}
	
	@RequestMapping(value="/approveVendorDetails",method= RequestMethod.POST)
	public String approveVendorDetails(@RequestBody VendorMaster af,@RequestHeader MultiValueMap<String, String> headers)
	{
	   String msg=null;
	   VendorMaster saved=null;
	   
	   try
	   {
		  
		  
		    UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
		    msg=vs.approveVendorDetails(af,userCode);
		  
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return msg;
	}
	
	@RequestMapping(value="/getAllVendorDetails")
	public List<VendorMaster> getAllVendorDetails(@RequestParam String vendorStatus)
	{
		System.out.println("entrr getAllVendorDetails");
		return vs.getAllVendorDetails(vendorStatus);
	}
	
	@RequestMapping(value="/getAllVendorDetailsWithoutStatus")
	public List<VendorMaster> getAllVendorDetailsWithoutStatus(@RequestParam String vendorStatus)
	{
		System.out.println("entrr getAllVendorDetails");
		return vr.getAllVendorDetailsWithoutStatus();
	}
	
	
	@RequestMapping(value="/getDetailsOfVendorById")
	public VendorMaster getDetailsOfVendorById(@RequestParam String vendorId)
	{
		System.out.println("entrr in getAllAdvanceRequestRaised");
		return vr.getDetailsOfVendorById(Integer.parseInt(vendorId));
	}
	

	@RequestMapping(value="/showVendorDetailsForCheck")
	public ModelAndView showVendorDetailsForCheck(ModelAndView view)
	{
	view.setViewName("VENDORS/VendorCehck");
	return view;

}
	
	@RequestMapping(value="/showVendorDetailsForVerification")
	public ModelAndView showVendorDetailsForVerification(ModelAndView view)
	{
	view.setViewName("VENDORS/VendorVerifiy");
	return view;

}
	
	
	@RequestMapping(value="/showVendorDetailsForApprove")
	public ModelAndView showVendorDetailsForApprove(ModelAndView view)
	{
	view.setViewName("VENDORS/VendorApprove");
	return view;

}
	
	
}
