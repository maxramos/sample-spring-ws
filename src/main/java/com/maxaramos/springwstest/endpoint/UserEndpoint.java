package com.maxaramos.springwstest.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.springwstest.model.User;
import com.maxaramos.springwstest.service.UserService;
import com.maxaramos.springwstest.user.AddUserRequest;
import com.maxaramos.springwstest.user.AddUserResponse;
import com.maxaramos.springwstest.user.DeleteUserRequest;
import com.maxaramos.springwstest.user.DeleteUserResponse;
import com.maxaramos.springwstest.user.GetAllUserRequest;
import com.maxaramos.springwstest.user.GetAllUserResponse;
import com.maxaramos.springwstest.user.GetUserRequest;
import com.maxaramos.springwstest.user.GetUserResponse;
import com.maxaramos.springwstest.user.UpdateUserRequest;
import com.maxaramos.springwstest.user.UpdateUserResponse;
import com.maxaramos.springwstest.user.UserType;


@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://springwstest.maxaramos.com/user";

	@Autowired
	private Logger log;

	@Autowired
	private UserService userService;

	@PayloadRoot(localPart = "GetAllUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetAllUserResponse getAllUser(@RequestPayload GetAllUserRequest request) {
		List<User> users = userService.getAllUser();
		log.info("getAllUser: " + users);
		GetAllUserResponse response = new GetAllUserResponse();
		response.getUsers().addAll(users.stream().map(user -> fromUser(user)).collect(Collectors.toList()));
		return response;
	}

	@PayloadRoot(localPart = "AddUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
		User user = userService.addUser(fromRequest(request));
		log.info("addUser: " + user);
		AddUserResponse response = new AddUserResponse();
		response.setUser(fromUser(user));
		return response;
	}

	@PayloadRoot(localPart = "GetUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
		User user = userService.getUser(request.getId());
		log.info("getUser: " + user);
		GetUserResponse response = new GetUserResponse();
		response.setUser(fromUser(user));
		return response;
	}

	@PayloadRoot(localPart = "UpdateUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		User user = userService.updateUser(fromRequest(request));
		log.info("updateUser: " + user);
		UpdateUserResponse response = new UpdateUserResponse();
		response.setUser(fromUser(user));
		return response;
	}

	@PayloadRoot(localPart = "DeleteUserRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
		boolean deleted = userService.deleteUser(request.getId());
		log.info("deleteUser: " + deleted);
		DeleteUserResponse response = new DeleteUserResponse();
		response.setDeleted(deleted);
		return response;
	}

	public static User fromRequest(AddUserRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		return user;
	}

	public static User fromRequest(UpdateUserRequest request) {
		UserType userType = request.getUser();
		User user = new User();
		user.setId(userType.getId());
		user.setUsername(userType.getUsername());
		user.setPassword(userType.getPassword());
		return user;
	}

	public UserType fromUser(User user) {
		UserType userType = new UserType();
		userType.setId(user.getId());
		userType.setUsername(user.getUsername());
		userType.setPassword(user.getPassword());
		return userType;
	}

}
