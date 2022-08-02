package FILEUPLOAD_UTILITY;

import java.util.Date;

/**
 * @author Jose
 *
 */
public class BucketDetailsDTO {

private String bucketName;
private Date createdDate;


public String getBucketName() {
    return bucketName;
}

public void setBucketName(String bucketName) {
    this.bucketName = bucketName;
}

public Date getCreatedDate() {
    return createdDate;
}

public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
}



}
