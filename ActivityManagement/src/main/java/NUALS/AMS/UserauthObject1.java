package NUALS.AMS;

import java.io.Serializable;

public class UserauthObject1 implements Serializable {
private static final long serialVersionUID = 7193229671028824231L;
	
	int userId;
	String username;
	String usercode;
	String mobile;
	String email;
	long iat;
	String usertype;
	
	
	
	public UserauthObject1() {
	}



	public UserauthObject1(int userId, String username, String usercode, String mobile, String email, long iat,String userType) {
		this.userId = userId;
		this.username = username;
		this.usercode = usercode;
		this.mobile = mobile;
		this.email = email;
		this.iat = iat;
		this.usertype=userType;
	}



	public String getUserType() {
		return usertype;
	}



	public void setUserType(String userType) {
		this.usertype = userType;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getUsercode() {
		return usercode;
	}



	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public long getIat() {
		return iat;
	}



	public void setIat(long iat) {
		this.iat = iat;
	}



	@Override
	public String toString() {
		return "UserauthObject [userId=" + userId + ", username=" + username + ", usercode=" + usercode + ", mobile="
				+ mobile + ", email=" + email + ", iat=" + iat + "]";
	}
	
}
