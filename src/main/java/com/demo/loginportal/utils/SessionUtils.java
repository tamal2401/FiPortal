package com.demo.loginportal.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.demo.loginportal.model.UserAuth;

public class SessionUtils {

	public static Map<String, UserAuth> sessionMap = new HashMap<>();
	
	private SessionUtils() {
		
	}
	
	public static void createSession(String sid, UserAuth auth) {
		sessionMap.put(sid, auth);
	}
	
	public static String getUser(String sid) {
		Set<String> keySet = sessionMap.keySet();
		String userId = null;
		if(keySet.contains(sid)) {
			UserAuth user = sessionMap.get(sid);
			if(null != user.getUserId()) {
				userId = user.getUserId();
			}
		}
		return userId;
	}
	
	public static boolean isActiveSession(String sid) {
		Set<String> keySet = sessionMap.keySet();
		boolean isActive = false;
		if(keySet.contains(sid)) {
			isActive = true;
		}
		return isActive;
	}
	
	public static boolean deleteSession(String sid) throws Exception {
		boolean isSessionDeleted = false;
		Set<String> keySet = sessionMap.keySet();
		if(null != sid && keySet.contains(sid)) {
			UserAuth auth = sessionMap.remove(sid);
			if(null!=auth.getUserId()) {
				isSessionDeleted=true;
			}
		}else {
			throw new Exception("Invalid session");
		}
		return isSessionDeleted;
	}
}
