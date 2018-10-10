package com.maxaramos.springwstest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.springwstest.service.UserService;
import com.maxaramos.springwstest.swst.AddUserRequest;
import com.maxaramos.springwstest.swst.AddUserResponse;
import com.maxaramos.springwstest.swst.UserType;

@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://springwstest.maxaramos.com/swst";

	@Autowired
	private UserService userService;

	@PayloadRoot(localPart = "AddUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
		 UserType user = userService.addUser(request.getUser());
		 AddUserResponse response = new AddUserResponse();
		 response.setUser(user);
		 return response;
	}

}
