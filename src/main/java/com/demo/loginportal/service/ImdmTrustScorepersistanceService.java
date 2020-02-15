package com.demo.loginportal.service;

import java.util.List;

import com.demo.loginportal.model.mdmtrustscore.TrustScoreConfigModel;

public interface ImdmTrustScorepersistanceService {

	TrustScoreConfigModel storeData(TrustScoreConfigModel model, String createdBy);
	
	TrustScoreConfigModel findData(Long id);
	
	List<TrustScoreConfigModel> getRules();

	TrustScoreConfigModel updateRule(TrustScoreConfigModel ruleRequestModel, String updateBy);

	void removeRule(TrustScoreConfigModel ruleRequestModel);
}
