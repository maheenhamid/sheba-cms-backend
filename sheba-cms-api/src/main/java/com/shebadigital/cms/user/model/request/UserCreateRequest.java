package com.shebadigital.cms.user.model.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserCreateRequest {

		@NotBlank
	    private String username;
		
		@NotBlank
	    private String password;
		
		@NotBlank
	    private String nickName;
		
		@NotBlank
		private String mobileNo;
		
		@Size(min = 1)
	    private List<String> userRoles;
	    
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNickName() {
			return nickName;
		}
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public List<String> getUserRoles() {
			return userRoles;
		}
		public void setUserRoles(List<String> userRoles) {
			this.userRoles = userRoles;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
	    
	    
	    
}
