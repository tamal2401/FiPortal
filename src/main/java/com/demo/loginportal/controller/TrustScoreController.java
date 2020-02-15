package com.demo.loginportal.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.loginportal.controller.utils.PropertyUtils;
import com.demo.loginportal.enumss.ActiveFlagEnum;
import com.demo.loginportal.model.mdmtrustscore.RequestTrustScoreModel;
import com.demo.loginportal.model.mdmtrustscore.TrustScoreConfigModel;
import com.demo.loginportal.model.session.SessionUserModel;
import com.demo.loginportal.service.ImdmTrustScorepersistanceService;

@RestController
@RequestMapping(value = "/trustscore")
public class TrustScoreController extends AbstractControllerUtil{

	@Autowired
	ImdmTrustScorepersistanceService scoreService;

	@CrossOrigin
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String persistDqRuleObject(@RequestBody RequestTrustScoreModel ruleRequestModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// SessionUserModel sessionUser = validateSsession(request, response);
		SessionUserModel sessionUser = validateSsession(request, response);
		TrustScoreConfigModel ruleModel = new TrustScoreConfigModel();
		if (PropertyUtils.isNotNull(ruleModel )) {
			populateStoreConfigModel(ruleModel, ruleRequestModel);
		}
		ruleModel = scoreService.storeData(ruleModel, sessionUser.getUserName());
		if(null==ruleModel.getRuleId()) {
			throw new Exception("db error occured");
		}
		return "New rule created";
	}

	@CrossOrigin
	@GetMapping(value = "/getstorerules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<TrustScoreConfigModel> getAllRules(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		List<TrustScoreConfigModel> rules = scoreService.getRules();
		return rules;
	}

	@CrossOrigin
	@PostMapping(value = "/updatestorerules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public TrustScoreConfigModel updateRule(@RequestBody TrustScoreConfigModel storeConfigModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		TrustScoreConfigModel rule = scoreService.updateRule(storeConfigModel, sessionUser.getUserName());
		return rule;
	}

	@CrossOrigin
	@PostMapping(value = "/deletestorerules", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRule(@RequestBody TrustScoreConfigModel storeConfigModel, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SessionUserModel sessionUser = validateSsession(request, response);
		scoreService.removeRule(storeConfigModel);
	}

	private void populateStoreConfigModel(TrustScoreConfigModel ruleModel, RequestTrustScoreModel ruleRequestModel) {

		ruleModel.setTableName(ruleRequestModel.getTableName())
		.setAttributeName(ruleRequestModel.getAttrName())
		.setSourceSystem(ruleRequestModel.getSrcSystem())
		.setTrustScore(ruleRequestModel.getScore())
		.setTimeStamp(new Date());
		if ("Yes".equalsIgnoreCase(ruleRequestModel.getaFlag())) {
			ruleModel.setActiveFlag(ActiveFlagEnum.Y);
		} else {
			ruleModel.setActiveFlag(ActiveFlagEnum.N);
		}
	}
}
