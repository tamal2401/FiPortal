package com.demo.loginportal.service;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;

public interface IdqPersistanceService {

	DqRuleMOdel storeData(DqRuleMOdel model);
	
	DqRuleMOdel findData(Long id);
	
	DqRuleMOdel updateData(DqRuleMOdel model);
}