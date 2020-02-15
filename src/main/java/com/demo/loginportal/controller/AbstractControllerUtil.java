package com.demo.loginportal.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.loginportal.enumss.ActiveFlagEnum;
import com.demo.loginportal.exception.InvalidSessionException;
import com.demo.loginportal.exception.UserNotRegisteredException;
import com.demo.loginportal.model.UserAuth;
import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.auth.UserAuthData;
import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.model.dqrule.RuleRequestModel;
import com.demo.loginportal.model.session.SessionUserModel;
import com.demo.loginportal.service.IAuthService;
import com.demo.loginportal.service.IdqPersistanceService;
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
	IdqPersistanceService dqPersistenceService;

	protected void storeSession(String sid, User currUser) throws InvalidSessionException {
		authService.scoreSession(sid, currUser);
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

	protected void populateDqModel(RuleRequestModel rModel, DqRuleMOdel model) {
		model.setObjName(rModel.getObjName())
		.setEntityName(rModel.getEntityName())
		.setHiveFilename(rModel.gethFilename())
		.setRuleName(rModel.getrName())
		.setTimeStamp(new Date());
		if ("Yes".equalsIgnoreCase(rModel.getaFlag())) {
			model.setActiveFlag(ActiveFlagEnum.Y);
		} else {
			model.setActiveFlag(ActiveFlagEnum.N);
		}
	}

	protected SessionUserModel validateSsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SessionUserModel activeUser = new SessionUserModel();
		String sid = request.getHeader("sid");
		if (null != sid && !StringUtils.containsWhitespace(sid)) {
			activeUser = authService.getSessionUser(sid);
		}
		if (null == activeUser.getUserName() || StringUtils.isBlank(activeUser.getUserName())) {
			throw new Exception("Invalid User");
		}
		response.addHeader("sid", sid);
		return activeUser;
	}
}
