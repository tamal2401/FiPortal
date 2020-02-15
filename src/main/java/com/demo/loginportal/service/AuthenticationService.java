package com.demo.loginportal.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.loginportal.exception.InvalidSessionException;
import com.demo.loginportal.model.auth.User;
import com.demo.loginportal.model.session.SessionUserModel;
import com.demo.loginportal.repository.SessionRepository;

@Service
public class AuthenticationService implements IAuthService{

	@Autowired
	private SessionRepository repo;

	@Override
	@Transactional(rollbackOn = InvalidSessionException.class)
	public void scoreSession(String sid, User currUser) throws InvalidSessionException {

		SessionUserModel user = new SessionUserModel(sid, currUser);
		System.out.println(user);
		try {
			SessionUserModel entity = repo.save(user);
			if(null!=entity) {
				System.out.println("session persisted for user ::"+entity.getUserName());
			}
		} catch (Exception e) {
			System.out.println("Error occured while persisting the session :: " + e.getMessage());
			throw new InvalidSessionException("User not saved ::"+currUser.getUserName());
		}
	}

	@Override
	@Transactional(rollbackOn = InvalidSessionException.class)
	public Long invalidateSession(String sid) throws InvalidSessionException {
		long deletedCount = 0;
		try {
			deletedCount = repo.deleteBySid(sid);
			if (0 == deletedCount) {
				System.out.println("No SessionDetails present for sid ::" + sid);
			} else {
				System.out.println(deletedCount + ":: users deleted");
			}
		}catch(Exception e) {
			System.out.println("error occured while deleting user for session id ".concat(sid).concat(e.getMessage()));
			throw new InvalidSessionException("Exception occured while deleting user");
		}
		return deletedCount;
	}

	@Override
	@Transactional(rollbackOn = InvalidSessionException.class)
	public SessionUserModel getSessionUser(String sid) throws InvalidSessionException {
		SessionUserModel model = null;
		try {
			 model = repo.findBySid(sid);
		}catch(Exception e) {
			System.out.println("Exception occured while getting existing user :: ".concat(sid).concat(e.getMessage()));
			throw new InvalidSessionException("Exception occured while getting existing user");
		}
		if(null!=model) {
			return model;
		}
		return new SessionUserModel();
	}

}
