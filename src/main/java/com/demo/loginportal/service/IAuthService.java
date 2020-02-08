package com.demo.loginportal.service;

import com.demo.loginportal.exception.InvalidSessionException;
import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.session.SessionUserModel;

public interface IAuthService {

	public void storeSession(String sid, User currUser) throws InvalidSessionException;
	
	public Long invalidateSession(String sid) throws InvalidSessionException;
	
	public SessionUserModel getSessionUser(String sid) throws InvalidSessionException;
}
