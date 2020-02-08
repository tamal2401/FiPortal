package com.demo.loginportal.service;

import java.util.List;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.model.dqrule.RuleRequestModel;

public interface IdqPersistanceService {

	DqRuleMOdel storeData(DqRuleMOdel model, String createdBy);
	
	DqRuleMOdel findData(Long id);
	
	List<DqRuleMOdel> getRules();

	DqRuleMOdel updateRule(DqRuleMOdel ruleRequestModel, String updateBy);

	void removeRule(DqRuleMOdel ruleRequestModel);
}
