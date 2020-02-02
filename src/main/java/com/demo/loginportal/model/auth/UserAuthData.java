package com.demo.loginportal.model.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("userauthdata")
public class UserAuthData {

	private List<String> roles = new ArrayList<>();
	private List<User> userRolesMapping;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> hosts) {
		this.roles = hosts;
	}
	
	public List<User> getUserRolesMapping() {
		return userRolesMapping;
	}

	public void setUserRolesMapping(List<User> userRolesMapping) {
		this.userRolesMapping = userRolesMapping;
	}

	@Override
	public String toString() {
		return "Config [roles=" + roles + ", userRolesMapping=" + userRolesMapping + "]";
	}
	
}
