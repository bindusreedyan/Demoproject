package FILEUPLOAD_UTILITY;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

//import NUALS.AMS.UserauthObject;
//import NUALS.AMS.ACADEMIC.COURSES.ACADEMIC_COURSE;


@RestController
@RequestMapping("/academic_public1")
public class FileUploadController {


	@RequestMapping(value="/commonFileUpload",method= RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public String commonFileUpload(@RequestParam("bucketName") String bucketName,@RequestParam("filePath") String filePath,@RequestParam("file") MultipartFile file,@RequestHeader MultiValueMap<String, String> headers) {
											
			String msg ="";
			
			try
			{			
							        
				FileManagementUtilityService fserv = new FileManagementUtilityService();
			
				if(!fserv.checkBucketAvailable(bucketName))
				{
					fserv.createBucket(bucketName);
				}
				
				/* uploading files thru  uploadFile method in  Minio... */
				//StatusDTO sdto = fserv.uploadFile(file, fileName, "nuals", "application/octet-stream");
				//System.out.println(sdto.getMessage());
				
				System.out.println("Bucket Name : "+bucketName);				
				/* uploading files thru  uploadStream in Minio.... */
				JsonNode jn = fserv.uploadStream(bucketName,filePath,file.getInputStream());
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception at Controller: "+ex.getMessage());
				System.out.println("Exception at Controller: "+ex.getStackTrace());
			}
		return msg;
	}
	
	

    /*
    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
    */
}