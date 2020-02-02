package com.demo.loginportal.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.loginportal.exception.UserNotRegisteredException;
import com.demo.loginportal.model.UserAuth;
import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.auth.UserAuthData;
import com.demo.loginportal.service.DqDataPersistanceService;
import com.demo.loginportal.service.IAuthService;
import com.google.gson.Gson;

public class AbstractControllerUtil {

	private static final Logger log = LoggerFactory.getLogger(AbstractControllerUtil.class);
	
	@Autowired
	Gson gson;
	
	@Autowired
	UserAuthData userAuthData;
	
	@Autowired
	IAuthService authService;
	
	@Autowired
	DqDataPersistanceService dqPersistenceService;

	protected void storeSession(String sid, User currUser) {
		authService.storeSession(sid, currUser);
	}

	protected String getSession() {
		String sid = "";
		sid = createSession();
		if (null != sid)
			return sid;
		return "";
	}

	protected Gson getGson() {
		if (null != gson) {
			return gson;
		}
		return new Gson();
	}

	protected String createSession() {
		String sid = UUID.randomUUID().toString();
		return sid;
	}

	protected User getUser(UserAuth modelAuth, User currUser) throws UserNotRegisteredException {
		String user = modelAuth.getUserId().toLowerCase().trim();
		List<User> mapping = userAuthData.getUserRolesMapping();

		Optional<User> predicate = mapping.stream().filter(each -> each.getUserName().equalsIgnoreCase(user))
				.findFirst();

		if (predicate.isPresent()) {
			currUser = predicate.get();
		} else {
			throw new UserNotRegisteredException("User is not registered");
		}
		return currUser;
	}
	
	protected void checkPwdAndPersistSession(UserAuth modelAuth, HttpServletResponse response,
			User currUser) throws Exception {
		String pwd = modelAuth.getPwd().trim();
		if (pwd.equalsIgnoreCase(currUser.getPwd())) {

			String sid = getSession();
			storeSession(sid, currUser);
			response.addHeader("sid", sid);
			log.info("session created for user :: " + currUser.getUserName() + " :: with sessioId :: " + sid);
		} else {
			throw new Exception("plase provide correct password");
		}
	}
}
