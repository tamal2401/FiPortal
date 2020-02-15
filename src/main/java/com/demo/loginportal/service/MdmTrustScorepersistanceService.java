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
import com.demo.loginportal.model.mdmtrustscore.TrustScoreConfigModel;
import com.demo.loginportal.repository.TrustScoreConfigRepository;

@Service
public class MdmTrustScorepersistanceService implements ImdmTrustScorepersistanceService{

	@Autowired
	TrustScoreConfigRepository storeRepo;

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public TrustScoreConfigModel storeData(TrustScoreConfigModel model, String createdBy) {
		model.setCreatedBy(createdBy);
		return storeRepo.save(model);
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public TrustScoreConfigModel findData(Long id) {
		Optional<TrustScoreConfigModel> model = storeRepo.findById(id);
		if (model.isPresent()) {
			return model.get();
		}
		return new TrustScoreConfigModel();
	}
	
	@Transactional(rollbackFor = Throwable.class)
	@Override
	public List<TrustScoreConfigModel> getRules() {
		List<TrustScoreConfigModel> rules = null;
		rules = storeRepo.findAll(getSort());
		if (null != rules) {
			return rules;
		}
		return new ArrayList<>();
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public TrustScoreConfigModel updateRule(TrustScoreConfigModel storeConfigModel, String updateBy) {
		storeConfigModel.setUpdatedBy(updateBy);
		storeConfigModel.setLastUpdated(new Date());
		storeConfigModel = storeRepo.save(storeConfigModel);
		return storeConfigModel;
	}

	@Transactional(rollbackFor = Throwable.class)
	@Override
	public void removeRule(TrustScoreConfigModel ruleRequestModel) {
		storeRepo.delete(ruleRequestModel);
	}

	private Sort getSort() {
		return new Sort(Sort.Direction.ASC, "ruleId");
	}

	

}
