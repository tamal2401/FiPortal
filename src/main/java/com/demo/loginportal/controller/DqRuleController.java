package com.demo.loginportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.loginportal.controller.utils.PropertyUtils;
import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.model.dqrule.RuleRequestModel;
import com.demo.loginportal.model.session.SessionUserModel;

@RestController
@RequestMapping(value = "/rule")
public class DqRuleController extends AbstractControllerUtil {

	@CrossOrigin
	@PostMapping(value = "/storerule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String persistDqRuleObject(@RequestBody RuleRequestModel ruleModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DqRuleMOdel dqRuleModel = new DqRuleMOdel();
		SessionUserModel sessionUser = validateSsession(request, response);
		if (PropertyUtils.isNotNull(ruleModel)) {
			populateDqModel(ruleModel, dqRuleModel);
		}
		System.out.println(dqRuleModel);
		dqRuleModel = dqPersistenceService.storeData(dqRuleModel, sessionUser.getUserName());
		if (null == dqRuleModel.getRuleId()) {
			throw new Exception("db error occured");
		}
		response.setStatus(HttpServletResponse.SC_ACCEPTED);
		return "New rule created";
	}

	@CrossOrigin
	@GetMapping(value = "/getrules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<DqRuleMOdel> getAllRules(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SessionUserModel sessionUser = validateSsession(request, response);
		List<DqRuleMOdel> rules = dqPersistenceService.getRules();
		return rules;
	}

	@CrossOrigin
	@PostMapping(value = "/updaterule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public DqRuleMOdel updateRule(@RequestBody DqRuleMOdel ruleRequestModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SessionUserModel sessionUser = validateSsession(request, response);
		System.out.println(ruleRequestModel.toString());
		DqRuleMOdel rule = dqPersistenceService.updateRule(ruleRequestModel, sessionUser.getUserName());
		return rule;
	}

	@CrossOrigin
	@PostMapping(value = "/deleterule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRule(@RequestBody DqRuleMOdel ruleRequestModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		System.out.println(ruleRequestModel.toString());
		dqPersistenceService.removeRule(ruleRequestModel);
	}
}
