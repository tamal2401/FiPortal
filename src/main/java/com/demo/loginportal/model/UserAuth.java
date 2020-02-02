package com.demo.loginportal.model;

public class UserAuth {

	private String userId;
	
	private String pwd;

	public UserAuth() {
		
	}
	
	public UserAuth(String userId, String pwd) {
		this.userId=userId;
		this.pwd=pwd;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	@Override
	public String toString() {
		return "UserAuth [userId=" + userId + ", pwd= ******** ]";
	}
	
	

}
