package FILEUPLOAD_UTILITY;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import FILEUPLOAD_UTILITY.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidExpiresRangeException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;
import io.minio.messages.Bucket;
import io.minio.messages.Item;



/**
 * @author Jose
 *
 */

public class FileManagementUtilityService {

    /**
     * @param fileName
     * @return
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
   
    	
	  
	   @Value("${spring.servlet.multipart.max-file-size}")
	   private int maxFileSize;

	
	  

    /**
     * @param bucketName
     * @return
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws BucketNotExistException 
     * 
     * Returns list of files stored in the given bucketName
     * 
     */

    public List<FileDetailsDTO> getFileList(String bucketName) throws InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException,MinioException {
	 List<FileDetailsDTO> fileDetailsList=new ArrayList<FileDetailsDTO>();
	
	    // Check whether 'mybucket' exists or not.
	 MinioClient minioClient=getMinioClient();
	   // MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	    boolean found = minioClient.bucketExists(bucketName);
	   
	    if (found) {
	      // List objects from 'my-bucketname'
	      Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
	      for (Result<Item> result : myObjects) {
		  FileDetailsDTO fileDetailsDTO= new FileDetailsDTO();
	        Item item = result.get();
	        fileDetailsDTO.setObjectName(item.objectName()); //File Name
	        fileDetailsDTO.setFileSize(item.size());	//File size
	        fileDetailsDTO.setLastModified(item.lastModified()); //last modified time
	        fileDetailsList.add(fileDetailsDTO);
	        System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
	      }
	    } else {
		
	    
	    }
	  
	return fileDetailsList;
    }

    /**
     * @param fileName
     * @return
     * @throws Exception 
     *  
     *  Download given file from given bucket. The return entity is response entity 
     */
    public ResponseEntity<InputStreamResource> downloadFile( String bucketName, String objectName) throws Exception {
	
	
	
		System.out.println("bucketName "+bucketName);
		System.out.println("objectname "+objectName);
		
		 MinioClient minioClient=getMinioClient();
	  //  MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
		ObjectStat objectStat= minioClient.statObject(bucketName, objectName);
	    InputStream stream = minioClient.getObject(bucketName, objectName);
	    InputStreamResource inputStreamResource = new InputStreamResource(stream);
		     
		     return ResponseEntity.ok()
		            .header(HttpHeaders.CONTENT_DISPOSITION,
		                  "attachment;filename=" + objectName)
		            .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(objectStat.length())
		            .body(inputStreamResource);
		
		    }
		       
		       
    /**
     * @param fileName
     * @return
     * @throws Exception 
     * 
     * Download given document. The documentId is assumed to be in <bucketName>/<objectName>.  The return entity is response entity 
     */
    public ResponseEntity<InputStreamResource> downloadFile( String documentId) throws Exception {
	
		String bucketName=getBucketName(documentId);
		String objectName=getObjectName(documentId);
	
		//System.out.println("bucketName "+bucketName);
		//System.out.println("objectname "+objectName);
		
		 MinioClient minioClient=getMinioClient();
	
		ObjectStat objectStat= minioClient.statObject(bucketName, objectName);
		InputStream stream = minioClient.getObject(bucketName, objectName);
		InputStreamResource inputStreamResource = new InputStreamResource(stream);
		     
		     return ResponseEntity.ok()
		            .header(HttpHeaders.CONTENT_DISPOSITION,
		                  "attachment;filename=" + objectName)
		            .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(objectStat.length())
		            .body(inputStreamResource);
		
		    }
		       
		       
    
    
    /**
     * @param bucketName
     * @return
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * 
     * Create bucket with given name
     */
    public StatusDTO createBucket(String bucketName) throws MinioException, InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException  {
	
	StatusDTO message=new StatusDTO();
	
	      // Create a minioClient with the Minio Server name, Port, Access key and Secret key.
	 	MinioClient minioClient=getMinioClient();
	  //    MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);

	      // Check if the bucket already exists.
	      boolean isExist = minioClient.bucketExists(bucketName);
	      if(isExist) {
		  // Message bucket already exists
		  message.setStatusCode(HttpStatus.CONFLICT.value());
		  message.setMessage("Bucket already exists.");
	       
	      } else {
	        // Make a new bucket
	        minioClient.makeBucket(bucketName);
	        
	        message.setStatusCode(200);
	        message.setMessage("Sucess");
		  
	      }
	    return message;
	  }


    /**
     * @return
     * @throws InvalidPortException 
     * @throws InvalidEndpointException 
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws InternalException 
     * @throws ErrorResponseException 
     * @throws NoResponseException 
     * @throws InsufficientDataException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidBucketNameException 
     * @throws InvalidKeyException 
     * 
     * Returns bucket lists
     */
    public List<BucketDetailsDTO> listBuckets() throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, IOException, XmlPullParserException {
	 List<BucketDetailsDTO> bucketDetailsList=new ArrayList<BucketDetailsDTO>();
		
	    // Check whether 'mybucket' exists or not.
	 
	 MinioClient minioClient=getMinioClient();
	  //  MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	    
	    List<Bucket> bucketList = minioClient.listBuckets();
	    for (Bucket bucket : bucketList) {
		BucketDetailsDTO bucketDetails=new BucketDetailsDTO();
		bucketDetails.setBucketName(bucket.name());
		bucketDetails.setCreatedDate(bucket.creationDate());
		bucketDetailsList.add(bucketDetails);
	    }
	      return bucketDetailsList;
    }
    
    public boolean checkBucketAvailable(String bucketname) throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, IOException, XmlPullParserException {
   	 List<BucketDetailsDTO> bucketDetailsList=new ArrayList<BucketDetailsDTO>();
   		
   	    // Check whether 'mybucket' exists or not.
   	 boolean res = false;
   	 
   	 MinioClient minioClient=getMinioClient();
   	  //  MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
   	    
   	    List<Bucket> bucketList = minioClient.listBuckets();
   	    
   	    for(int i=0;i<bucketList.size();i++)
   	    {
   	    	Bucket b = bucketList.get(i);
   	    	if(b.name().equals(bucketname))
   	    	{
   	    		res = true;
   	    		break;
   	    	}
   	    }
   	    
   	      return res;
       }


    /**
     * @param bucketName
     * @return
     * @throws MinioException 
     * @throws InvalidEndpointException 
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * 
     * Removes the given bucket
     */
    public StatusDTO removeBucket(String bucketName) throws InvalidEndpointException, MinioException, InvalidKeyException, NoSuchAlgorithmException, IOException, XmlPullParserException {
	StatusDTO message=new StatusDTO();
	 MinioClient minioClient=getMinioClient();
	 //MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	    // Check if bucket exists before removing it.
	    boolean found = minioClient.bucketExists(bucketName);
	    if (found) {
	      // Remove bucket my-bucketname. This operation will succeed only if the bucket is empty.
	      minioClient.removeBucket(bucketName);
	      message.setStatusCode(200);
	      message.setMessage("Bucket Removed Sucessfully");
	     // System.out.println("mybucket is removed successfully");
	    } else {
		message.setStatusCode(HttpStatus.NOT_FOUND.value());
		message.setMessage("Bucket does not exist.");
		
	    }
	 return message;
	
    }
    
    
    /**
     * @param fileDetailsDTO
     * @return
     * @throws InvalidPortException 
     * @throws InvalidEndpointException 
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws InvalidArgumentException 
     * @throws InternalException 
     * @throws ErrorResponseException 
     * @throws NoResponseException 
     * @throws InsufficientDataException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidBucketNameException 
     * @throws InvalidKeyException 
     * 
     * remove given documentId . The document id is assumed as <bucketName>/<objectname>
     */
    public StatusDTO removeFile(String documentId) throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, InvalidArgumentException, IOException, XmlPullParserException {

	StatusDTO message=new StatusDTO();
	 MinioClient minioClient=getMinioClient();
	// MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	 String bucketName=getBucketName(documentId);
	 String objectName=getObjectName(documentId);
	 FileDetailsDTO fileDeatailsDTO= new FileDetailsDTO();
	 fileDeatailsDTO.setBucketName(bucketName);
	 fileDeatailsDTO.setObjectName(objectName);
	 
	 //String bucketName=fileDetailsDTO.getBucketName();
	// String ObjectName=fileDetailsDTO.getObjectName();
	
	      // Remove my-objectname from the bucket my-bucketname.
	      minioClient.removeObject(bucketName, objectName);
	      message.setStatusCode(HttpStatus.OK.value());
	      message.setMessage("Successfully removed");
	     
	
	
	return message;
    }

    


    /**
     * @param fileDetailsDTO
     * @return
     * @throws InvalidPortException 
     * @throws InvalidEndpointException 
     * @throws XmlPullParserException 
     * @throws IOException 
     * @throws InvalidArgumentException 
     * @throws InternalException 
     * @throws ErrorResponseException 
     * @throws NoResponseException 
     * @throws InsufficientDataException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidBucketNameException 
     * @throws InvalidKeyException 
     * 
     * Removes files specified in fileDetailsDTO
     */
    public StatusDTO removeFile(FileDetailsDTO fileDetailsDTO) throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, InvalidArgumentException, IOException, XmlPullParserException {

	StatusDTO message=new StatusDTO();
	 MinioClient minioClient=getMinioClient();
	// MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	 
	 String bucketName=fileDetailsDTO.getBucketName();
	 String ObjectName=fileDetailsDTO.getObjectName();
	
	      // Remove my-objectname from the bucket my-bucketname.
	      minioClient.removeObject(bucketName, ObjectName);
	      message.setStatusCode(HttpStatus.OK.value());
	      message.setMessage("Successfully removed");
	     
	
	
	return message;
    }


    /**
     * @param names
     * @param files
     * @param bucketName
     * @return
     * @throws IOException 
     * @throws InvalidPortException 
     * @throws InvalidEndpointException 
     * @throws XmlPullParserException 
     * @throws InsufficientDataException 
     * @throws InvalidArgumentException 
     * @throws InternalException 
     * @throws ErrorResponseException 
     * @throws NoResponseException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidBucketNameException 
     * @throws InvalidKeyException 
     * 
     * Upload a file as given ObjectName(FileName) in given bucket  
     */
    public StatusDTO uploadFile(MultipartFile file, String fileName, String bucketName,String applicationStream) throws IOException, InvalidEndpointException, InvalidPortException,InvalidArgumentException,NoResponseException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, ErrorResponseException, InternalException, InsufficientDataException, XmlPullParserException {
	
	 StatusDTO message=new StatusDTO();
	 
	 if (file.getSize()>Integer.parseInt(getConfigValue("application.properties","spring.servlet.multipart.max-file-size")))
	 {
	     message.setStatusCode((HttpStatus.EXPECTATION_FAILED).value());
	     message.setMessage("File size exceeds maximum");
	 }
	 else
	 {
	 
	 MinioClient minioClient=getMinioClient();
	// MinioClient minioClient = new MinioClient(serverUrl, acessKey, secretKey);
	String name=file.getOriginalFilename();
	//System.out.println(name);
	
		byte[] bytes = file.getBytes();
		InputStream inputStream = new ByteArrayInputStream(bytes);
		//application/pdf"
		//minioClient.putObject(bucketName, fileName, inputStream,inputStream.available(), "application/octet-stream");
		minioClient.putObject(bucketName, fileName, inputStream,inputStream.available(), applicationStream);
		 
		inputStream.close();

		message.setStatusCode(200);
		message.setMessage("File "+ name +" uploaded successfully");
	 }
		return message;
}
	      
/**
 * 	      
 * @param bucketName
 * @param ObjectName
 * @param instream
 * @return
 * @throws InvalidEndpointException
 * @throws InvalidPortException
 * @throws IOException
 * @throws InvalidKeyException
 * @throws InvalidBucketNameException
 * @throws NoSuchAlgorithmException
 * @throws NoResponseException
 * @throws ErrorResponseException
 * @throws InternalException
 * @throws InvalidArgumentException
 * @throws InsufficientDataException
 * @throws XmlPullParserException
 * @throws InvalidExpiresRangeException
 * 
 * Upload an inputstream as given ObjectName(FileName) to given bucket and returns documentId as Json 
 */
    public JsonNode uploadStream(String bucketName, String ObjectName, InputStream instream) throws InvalidEndpointException, InvalidPortException, IOException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, NoResponseException, ErrorResponseException, InternalException, InvalidArgumentException, InsufficientDataException, XmlPullParserException, InvalidExpiresRangeException
    {
	MinioClient minioClient=getMinioClient();
	minioClient.putObject(bucketName, ObjectName, instream,instream.available(), "application/pdf");
	instream.close();
	ObjectMapper mapper = new ObjectMapper();
	//String jsonOut="{\"DownloadLink\":\""+ minioClient.presignedGetObject(bucketName, ObjectName)+"\"}";
	String jsonOut="{\"DocumentId\":\""+ bucketName+ "/"+ ObjectName +"\"}";
	//return jsonOut;
	  return mapper.readTree(jsonOut);
    }
    
    
    /**
     * 
     * @return
     * @throws InvalidEndpointException
     * @throws InvalidPortException
     * @throws IOException
     * 
     * Returns minio client with preset server URL, acessKey and secretKey
     */
	      private MinioClient  getMinioClient() throws InvalidEndpointException, InvalidPortException, IOException
	      
	      {
		  
		  String serverURL=getConfigValue("application.properties","minio.serverURL");
		  String acessKey =getConfigValue("application.properties","minio.acessKey");
		  String secretKey=getConfigValue("application.properties","minio.secretKey");
		  		 
		 System.out.println(serverURL);
		  MinioClient minioClient = new MinioClient(serverURL, acessKey , secretKey);
		   return minioClient;
	      }
	      /**
	       * 
	       * @param resourceName
	       * @param key
	       * @return
	       * @throws IOException
	       * 
	       * return value in configuration file to the provided key 
	       */
		 private String getConfigValue(String resourceName,String key) throws IOException
		 {
		     Properties configFile=new java.util.Properties();
		     configFile.load(this.getClass().getClassLoader().getResourceAsStream(resourceName));
		     return configFile.getProperty(key);
		 }

		 /**
		  * 
		  * @param documentId
		  * @return
		  * @throws InvalidEndpointException
		  * @throws InvalidPortException
		  * @throws IOException
		  * @throws InvalidKeyException
		  * @throws InvalidBucketNameException
		  * @throws NoSuchAlgorithmException
		  * @throws InsufficientDataException
		  * @throws NoResponseException
		  * @throws ErrorResponseException
		  * @throws InternalException
		  * @throws InvalidExpiresRangeException
		  * @throws XmlPullParserException
		  * 
		  * Generate PresignedURL for the given documentId. The document id is assumed as <bucketName>/<objectname>
		  */
		 public JsonNode getPresignedURL(String documentId) throws InvalidEndpointException, InvalidPortException, IOException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, InvalidExpiresRangeException, XmlPullParserException
			{
		     
			    MinioClient minioClient=getMinioClient();
			    ObjectMapper mapper = new ObjectMapper();
			    String bucketName=getBucketName(documentId);
			    String objectName=getObjectName(documentId);
			     // HashMap <String, String> reqParms =new HashMap<String,String>();
			     // attachment; filename=\"your-filename.txt\
			   // reqParms.put("response-content-disposition", "inline; filename="+ObjectName);
			    String jsonOut="{\"DownloadLink\":\""+ minioClient.presignedGetObject(bucketName, objectName)+"\"}";
			    //String jsonOut="{\"DownloadLink\":\""+ minioClient.presignedGetObject(bucketName, ObjectName,7,reqParms)+"\"}";
			    return mapper.readTree(jsonOut);
			    //return minioClient.presignedGetObject(bucketName, ObjectName);
				    
			}
		 
		 /**
		  * 
		  * @param instr
		  * @return
		  * 
		  * return bucket Name from given string. Assumed string is as <bucketName>/<objectname>
		  */
		 private String getBucketName(String instr)
			{
			    return  instr.substring(0,instr.indexOf("/")) ;
			   
			}
			/**
			 * 
			 * @param instr
			 * @return
			 * 
			 * return Object Name from given string. Assumed string is as <bucketName>/<objectname>
			 */
			
			private String getObjectName (String instr)
			{
			    return  instr.substring(instr.indexOf("/")+1);
			}
	
    }
   

