package NUALS.AMS.ACADEMIC.CENTER;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import NUALS.AMS.UserauthObject;
import NUALS.AMS.ACADEMIC.ACTIVITY_TYPES.AcademicActivityTypes;
@RestController
@RequestMapping("/center1")
public class CenterController {
	
	
	@Autowired
	CenterService centerService;
	@RequestMapping(value="/addCenteView")
	public ModelAndView CenterView(ModelAndView mv) {	
		System.out.println("entrrr in center add");
		mv.setViewName("CENTER/Add_Center");
		return mv;
	}
	
	@RequestMapping(value="/saveCenter",method= RequestMethod.POST)
	public String saveCenter(@RequestBody CenterMaster cm,@RequestHeader MultiValueMap<String, String> headers) {
		
		CenterMaster saved = null;
		String msg ="";
		
		try
		{
			UserauthObject user = new Gson().fromJson(headers.getFirst("userauthdatastring"), UserauthObject.class);
			String userCode = user.getUsercode();
			System.out.println("User : "+userCode);	
			cm.setEnteredBy(userCode);
			cm.setStatus("submitted");
			saved = centerService.saveCenter(cm);
			System.out.println(saved);
			
			
			if(saved == null)
			{
				
				msg="Error in adding Center Details. Contact Admin";
			}
			else
			{
			String display = "Center: "+cm.getCenterName()+"is succesafully added";
				msg="SAVED-"+display;
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	
	
	@RequestMapping(value="/getAllCenters")
	public List<CenterMaster> getAllCenters()
	{
		return centerService.getAllCenters();
	}
	
	//center fund code starts from here-----------------------------to be added in jayaram sir----
	@RequestMapping(value="/addCenteFundView")
	public ModelAndView addCenteFundView(ModelAndView mv) {	
		System.out.println("entrrr in center fund add");
		mv.setViewName("CENTER/AddCenterFund");
		return mv;
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
				
				msg="Error in adding Center Fund Details. Contact Admin";
			}
			else
			{
		//	String display = "Center: "+saved.getCentreFundKey().getCm().getCentre_name()+saved.getCentreFundKey().getFinYear()+saved.getFundAmount()+"is succesafully added";
				msg="SAVED-"+"display";
			}
			
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception at saveHostel Controller: "+ex.getMessage());
		}
		
		return  msg;
		
		
	}
	

	

}
