package com.maxaramos.springwstest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.springwstest.model.User;
import com.maxaramos.springwstest.service.UserService;

@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://springwstest.maxaramos.com/swst";

	@Autowired
	private UserService userService;

	@PayloadRoot(localPart = "addUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public User addUser(@RequestPayload User user) {
		return userService.addUser(user);
	}

}
