package com.demo.loginportal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;
import com.demo.loginportal.repository.PsqlRepository;

@Service
public class DqDataPersistanceService implements IdqPersistanceService {

	@Autowired
	PsqlRepository dqRepo;

	/**
	 * Saving the data in "mdm_dqrule" table
	 */
	@Override
	public DqRuleMOdel storeData(DqRuleMOdel model) {
		try {
			model = dqRepo.save(model);
		}catch(Exception e) {
			System.out.println("Error occcured :: "+e.getMessage());
		}
		return model;
	}

	/**
	 * not required as of now
	 */
	@Override
	public DqRuleMOdel findData(Long id) {
		try {
			Optional<DqRuleMOdel> model = dqRepo.findById(id);
			if(model.isPresent()) {
				return model.get();
			}
		}catch(Exception e) {
			System.out.println("Error occcured while retriving data :: "+e.getMessage());
		}
		return new DqRuleMOdel();
	}

	/**
	 * Unique identifier required
	 * based on which the data will be updated
	 */
	@Override
	public DqRuleMOdel updateData(DqRuleMOdel model) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * To get all rule Data
	 */
	@Override
	public List<DqRuleMOdel> getRules() {
		List<DqRuleMOdel> rules = null;
		try {
			// rules = dqRepo.findAllByOrderByRuleIdAsc();   
			rules = dqRepo.findAll(getSort());
		}catch(Exception e) {
			System.out.println("Error occcured while retriving data :: "+e.getMessage());
		}
		if(null!=rules) {
			return rules;
		}
		return new ArrayList<>();
	}
	
	private Sort getSort() {
		return new Sort(Sort.Direction.ASC, "ruleId");
	}


}
