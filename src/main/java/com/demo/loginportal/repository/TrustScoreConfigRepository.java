package com.demo.loginportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.loginportal.model.mdmtrustscore.TrustScoreConfigModel;

public interface TrustScoreConfigRepository extends JpaRepository<TrustScoreConfigModel, Long>{

}
