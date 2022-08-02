package FILEUPLOAD_UTILITY;

import org.springframework.http.HttpStatus;

/**
 * @author Jose
 *
 */
public class StatusDTO {
private Integer statusCode;
private String message;


public String getMessage() {
    return message;
}
public void setMessage(String message) {
    this.message = message;
}
public Integer getStatusCode() {
    return statusCode;
}
public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
}


}
