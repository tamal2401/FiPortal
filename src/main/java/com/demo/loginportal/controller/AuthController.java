package com.demo.loginportal.controller;

import java.util.Date;
import java.util.List;

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
	@PostMapping(value = "/rule/storerule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String persistDqRuleObject(@RequestBody RuleRequestModel ruleModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DqRuleMOdel dqRuleModel = new DqRuleMOdel();
		SessionUserModel sessionUser = validateSsession(request, response);
		if (PropertyUtils.isNotNull(ruleModel)) {
			populateDqModel(ruleModel, dqRuleModel);
		}
		System.out.println(dqRuleModel);
		dqRuleModel = dqPersistenceService.storeData(dqRuleModel, sessionUser.getUserName());
		if(null==dqRuleModel.getRuleId()) {
			throw new Exception("db error occured");
		}
		return "New rule created";
	}
	
	@CrossOrigin
	@GetMapping(value = "/rule/getrules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<DqRuleMOdel> getAllRules(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		List<DqRuleMOdel> rules = dqPersistenceService.getRules();
		return rules;
	}
	
	@CrossOrigin
	@PostMapping(value = "/rule/updaterule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public DqRuleMOdel updateRule(@RequestBody DqRuleMOdel ruleRequestModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		DqRuleMOdel rule = dqPersistenceService.updateRule(ruleRequestModel, sessionUser.getUserName());
		return rule;
	}
	
	@CrossOrigin
	@PostMapping(value = "/rule/deleterule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public DqRuleMOdel deleteRule(@RequestBody DqRuleMOdel ruleRequestModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		dqPersistenceService.removeRule(ruleRequestModel);
		return null;
	}

	
}
