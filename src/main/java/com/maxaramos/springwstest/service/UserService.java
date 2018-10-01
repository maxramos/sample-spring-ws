package com.maxaramos.springwstest.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.maxaramos.springwstest.swst.UserType;

@Service
public class UserService {

	private Map<Long, UserType> map = new HashMap<>();

	public UserType addUser(UserType user) {
		Long key = Instant.now().toEpochMilli();
		user.setId(key);
		map.put(key, user);
		return user;
	}

}
