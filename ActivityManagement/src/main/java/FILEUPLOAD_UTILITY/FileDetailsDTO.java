package FILEUPLOAD_UTILITY;

import java.util.Date;

/**
 * @author Jose
 *
 */
public class FileDetailsDTO {
   
    //private String fileUploadPath;
    private String objectName;
    private String bucketName;
    private long fileSize;
    private Date lastModified;
   // private String fileDownloadPath;
    
    public String getObjectName() {
        return objectName;
    }
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
   
   
   
    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
   
    public String getBucketName() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
   
   
    

}
