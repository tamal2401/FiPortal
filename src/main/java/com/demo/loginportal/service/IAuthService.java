package com.demo.loginportal.service;

import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.session.SessionUserModel;

public interface IAuthService {

	public void storeSession(String sid, User currUser);
	
	public Long invalidateSession(String sid);
	
	public SessionUserModel getSessionUser(String sid);
}
