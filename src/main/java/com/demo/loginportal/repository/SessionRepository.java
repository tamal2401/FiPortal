package com.demo.loginportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.loginportal.model.session.SessionUserModel;

public interface SessionRepository extends JpaRepository<SessionUserModel, Long> {

	long deleteBySid(String sid);
	
	SessionUserModel findBySid(String sid);

}
