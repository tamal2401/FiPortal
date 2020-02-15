package com.demo.loginportal.service;

import java.util.List;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.model.dqrule.RuleRequestModel;

public interface IdqPersistanceService {

	DqRuleMOdel storeData(DqRuleMOdel model, String createdBy) throws Exception;
	
	DqRuleMOdel findData(Long id) throws Exception;
	
	List<DqRuleMOdel> getRules() throws Exception;

	DqRuleMOdel updateRule(DqRuleMOdel ruleRequestModel, String updateBy) throws Exception;

	void removeRule(DqRuleMOdel ruleRequestModel) throws Exception;

}
