package com.demo.loginportal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.repository.PsqlRepository;

@Service
public class DqDataPersistanceService implements IdqPersistanceService {

	@Autowired
	PsqlRepository dqRepo;

	/**
	 * Saving the data in "mdm_dqrule" table
	 */
	@Transactional(rollbackFor = Throwable.class)
	@Override
	public DqRuleMOdel storeData(DqRuleMOdel model, String createdBy) {
		model.setCreatedBy(createdBy);
		return dqRepo.save(model);
	}

	/**
	 * not required as of now
	 */
	@Transactional(rollbackFor = Throwable.class)
	@Override
	public DqRuleMOdel findData(Long id) {
		Optional<DqRuleMOdel> model = dqRepo.findById(id);
		if (model.isPresent()) {
			return model.get();
		}
		return new DqRuleMOdel();
	}

	/**
	 * To get all rule Data
	 */
	@Transactional(rollbackFor = Throwable.class)
	@Override
	public List<DqRuleMOdel> getRules() {
		List<DqRuleMOdel> rules = null;
		rules = dqRepo.findAll(getSort());
		if (null != rules) {
			return rules;
		}
		return new ArrayList<>();
	}

	private Sort getSort() {
		return new Sort(Sort.Direction.ASC, "ruleId");
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public DqRuleMOdel updateRule(DqRuleMOdel newruleModel, String updateBy) {
		newruleModel.setUpdatedBy(updateBy);
		newruleModel.setLastUpdated(new Date());
		newruleModel = dqRepo.save(newruleModel);
		return newruleModel;
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public void removeRule(DqRuleMOdel ruleRequestModel) {
		dqRepo.delete(ruleRequestModel);
	}
}
