package com.demo.loginportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.loginportal.model.dqrule.DqRuleMOdel;

public interface PsqlRepository extends JpaRepository<DqRuleMOdel, Long> {

}
