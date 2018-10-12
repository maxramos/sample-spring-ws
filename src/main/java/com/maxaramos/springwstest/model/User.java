package com.maxaramos.springwstest.model;

import java.io.Serializable;

import com.maxaramos.springwstest.user.AddUserRequest;
import com.maxaramos.springwstest.user.UpdateUserRequest;
import com.maxaramos.springwstest.user.UserType;

public class User implements Serializable {

	private static final long serialVersionUID = -4687159716006095110L;

	private Long id;
	private String username;
	private String password;

	public static User fromRequest(AddUserRequest request) {
		User user = new User();
		user.username = request.getUsername();
		user.password = request.getPassword();
		return user;
	}

	public static User fromRequest(UpdateUserRequest request) {
		User user = new User();
		user.id = request.getUser().getId();
		user.username = request.getUser().getUsername();
		user.password = request.getUser().getPassword();
		return user;
	}

	public UserType toUserType() {
		UserType userType = new UserType();
		userType.setId(id);
		userType.setUsername(username);
		userType.setPassword(password);
		return userType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
