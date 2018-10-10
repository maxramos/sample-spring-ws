package com.maxaramos.springwstest.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.maxaramos.springwstest.model.User;

@Service
public class UserService {

	private Map<Long, User> map = new HashMap<>();

	public User addUser(User user) {
		Long key = Instant.now().toEpochMilli();
		user.setId(key);
		map.put(key, user);
		return user;
	}

	public User getUser(Long id) {
		return map.get(id);
	}

	public User updateUser(User user) {
		map.put(user.getId(), user);
		return user;
	}

	public boolean deleteUser(Long id) {
		map.remove(id);
		return true;
	}

}
