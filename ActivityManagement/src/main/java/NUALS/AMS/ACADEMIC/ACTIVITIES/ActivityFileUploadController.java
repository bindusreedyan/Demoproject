package NUALS.AMS.ACADEMIC.ACTIVITIES;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import FILEUPLOAD_UTILITY.FileManagementUtilityService;
import io.minio.MinioClient;
import io.minio.ObjectStat;
@RequestMapping("/academicactivity_public")
@RestController
public class ActivityFileUploadController {
	
	
	
	@Autowired ActivityMasterService ams;
	@Value("${bucketName}")
	String bucketName;
	
	@Value("${minio.serverURL}")
	String minioserverURL;
	
	
	@Value("${brochureSyllabusPath}")
	String brochurePath;
	
	
	@Value("${annexurePath}")
	String annexurePath;

@RequestMapping(value="/saveDocs",method= RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
public String saveDocs(@RequestParam Map<String,String> allRequestParams,@RequestParam("file") MultipartFile file,@RequestHeader MultiValueMap<String, String>headers) 
	{
	String flag="failed";
		
		try
		{
			
			
			Set<String> keys = headers.keySet();
			
			Iterator<String> itr =  keys.iterator();
			
			while(itr.hasNext())
			{
				String k = itr.next();
				System.out.println("Key:"+k);
				System.out.println("Value:"+ headers.get(k));
			}
			
			Set<String> keys1 = allRequestParams.keySet();
			
			Iterator<String> itr1 =  keys1.iterator();
			
			while(itr1.hasNext())
			{
				String k = itr1.next();
				System.out.println("Key:"+k);
				System.out.println("Value:"+ allRequestParams.get(k));
			}
			
			
			
			java.util.Date dt = new Date();
	        long time = dt.getTime();
	        
	        String fileName = file.getOriginalFilename();
	        System.out.println("filenameeeeeeee"+fileName);
	        fileName =  allRequestParams.get("activityId")+"_brochure_"+"_"+time+"_"+fileName;
	        System.out.println("filenameeeeeeee"+fileName);
	        fileName=brochurePath+"/"+fileName;
		        
			FileManagementUtilityService fserv = new FileManagementUtilityService();
		
			if(!fserv.checkBucketAvailable(bucketName))
			{
				fserv.createBucket(bucketName);
			}
			System.out.println("Bucket Name : "+bucketName);				
			/* uploading files thru  uploadStream in Minio.... */
			JsonNode jn = fserv.uploadStream(bucketName,fileName,file.getInputStream());
			
			String downloadLink = minioserverURL+"/"+bucketName+fileName;
			System.out.println("Download Link : "+downloadLink);
			flag="succes";
			int actid=Integer.parseInt(allRequestParams.get("activityId"));
			ActivityMaster as=ams.updateBrochureUrl(actid,fileName);
			
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return flag;
	}

@RequestMapping(value="/saveFinanceDocs",method= RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
public String saveFinanceDocs(@RequestParam Map<String,String> allRequestParams,@RequestParam("file") MultipartFile file,@RequestHeader MultiValueMap<String, String>headers) 
	{
	String flag="failed";
		
		try
		{
			
			
			Set<String> keys = headers.keySet();
			
			Iterator<String> itr =  keys.iterator();
			
			while(itr.hasNext())
			{
				String k = itr.next();
				System.out.println("Key:"+k);
				System.out.println("Value:"+ headers.get(k));
			}
			
			Set<String> keys1 = allRequestParams.keySet();
			
			Iterator<String> itr1 =  keys1.iterator();
			
			while(itr1.hasNext())
			{
				String k = itr1.next();
				System.out.println("Key:"+k);
				System.out.println("Value:"+ allRequestParams.get(k));
			}
			
			
			
			java.util.Date dt = new Date();
	        long time = dt.getTime();
	        
	        String fileName = file.getOriginalFilename();
	        System.out.println("filenameeeeeeee"+fileName);
	        fileName =  allRequestParams.get("activityId")+"_annexure_"+"_"+time+"_"+fileName;
	        System.out.println("filenameeeeeeee"+fileName);
	        fileName=annexurePath+"/"+fileName;
		        
			FileManagementUtilityService fserv = new FileManagementUtilityService();
		
			if(!fserv.checkBucketAvailable(bucketName))
			{
				fserv.createBucket(bucketName);
			}
			System.out.println("Bucket Name : "+bucketName);				
			/* uploading files thru  uploadStream in Minio.... */
			JsonNode jn = fserv.uploadStream(bucketName,fileName,file.getInputStream());
			
			String downloadLink = minioserverURL+"/"+bucketName+fileName;
			System.out.println("Download Link : "+downloadLink);
			flag="succes";
			int actid=Integer.parseInt(allRequestParams.get("activityId"));
			ActivityFinance as=ams.updateAnnexureUrl(actid,fileName);
		
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return flag;
	}




}
