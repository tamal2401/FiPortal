package com.demo.loginportal.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.loginportal.controller.utils.PropertyUtils;
import com.demo.loginportal.enumss.ActiveFlagEnum;
import com.demo.loginportal.model.UserAuth;
import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.model.dqrule.RuleRequestModel;
import com.demo.loginportal.model.session.SessionUserModel;

@RestController
public class AuthController extends AbstractControllerUtil {

	private static final String JSESSION_KEY = "JSESSIONID";

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	@CrossOrigin
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User userLogin(@RequestBody UserAuth modelAuth, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("incoming resource :: " + modelAuth.toString());
		User currUser = null;

		// getting user from configuration
		if (null != modelAuth.getUserId() && !StringUtils.containsWhitespace(modelAuth.getUserId())) {

			currUser = getUser(modelAuth, currUser);
			log.info("current user is :: " + getGson().toJson(currUser));
			checkPwdAndPersistSession(modelAuth, response, currUser);
		}

		System.out.println(currUser);
		return currUser;
	}

	@CrossOrigin
	@GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String logout(HttpServletRequest request) throws Exception {
		String sid = request.getHeader("sid");
		long count = 0;
		if (null != sid && !StringUtils.containsWhitespace(sid)) {
			count = authService.invalidateSession(sid);
		}

		if (0 == count) {
			return "No active session is present";
		}
		return "User is Logged Off";
	}

	@CrossOrigin
	@PostMapping(value = "/storerule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String persistDqRuleObject(@RequestBody RuleRequestModel ruleRequestModel, HttpServletRequest request)
			throws Exception {
		
		DqRuleMOdel dqRuleModel = new DqRuleMOdel();
		SessionUserModel sessionUser = validateSsession(request);
		if (PropertyUtils.isNotNull(ruleRequestModel)) {
			populateDqModel(ruleRequestModel, dqRuleModel);
		}
		System.out.println(dqRuleModel);
		dqRuleModel = dqPersistenceService.storeData(dqRuleModel);
		if(null==dqRuleModel.getRuleId()) {
			throw new Exception("db error occured");
		}
		return "New rule created";
	}

	private DqRuleMOdel populateDqModel(RuleRequestModel rModel, DqRuleMOdel model) {
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
		return model;
	}

	private SessionUserModel validateSsession(HttpServletRequest request) throws Exception {

		// todo SID validation
		SessionUserModel activeUser = new SessionUserModel();
		String sid = request.getHeader("sid");
		if (null != sid && !StringUtils.containsWhitespace(sid)) {
			activeUser = authService.getSessionUser(sid);
		}
		if (null == activeUser.getUserName() || StringUtils.isBlank(activeUser.getUserName())) {
			throw new Exception("Invalid User");
		}
		return activeUser;
	}
}
