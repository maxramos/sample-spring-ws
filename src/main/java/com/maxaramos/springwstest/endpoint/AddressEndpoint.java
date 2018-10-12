package com.maxaramos.springwstest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.springwstest.address.AddAddressRequest;
import com.maxaramos.springwstest.address.AddAddressResponse;
import com.maxaramos.springwstest.address.DeleteAddressRequest;
import com.maxaramos.springwstest.address.DeleteAddressResponse;
import com.maxaramos.springwstest.address.GetAddressRequest;
import com.maxaramos.springwstest.address.GetAddressResponse;
import com.maxaramos.springwstest.address.UpdateAddressRequest;
import com.maxaramos.springwstest.address.UpdateAddressResponse;
import com.maxaramos.springwstest.model.Address;
import com.maxaramos.springwstest.service.AddressService;

@Endpoint
public class AddressEndpoint {

	private static final String NAMESPACE_URI = "http://springwstest.maxaramos.com/address";

	@Autowired
	private AddressService addressService;

	@PayloadRoot(localPart = "AddAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public AddAddressResponse addUser(@RequestPayload AddAddressRequest request) {
		Address address = addressService.addAddress(Address.fromRequest(request));
		AddAddressResponse response = new AddAddressResponse();
		response.setAddress(address.toAddressType());
		return response;
	}

	@PayloadRoot(localPart = "GetAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetAddressResponse getAddress(@RequestPayload GetAddressRequest request) {
		Address address = addressService.getAddress(request.getId());
		GetAddressResponse response = new GetAddressResponse();
		response.setAddress(address.toAddressType());
		return response;
	}

	@PayloadRoot(localPart = "UpdateAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public UpdateAddressResponse updateAddress(@RequestPayload UpdateAddressRequest request) {
		Address address = addressService.updateAddress(Address.fromRequest(request));
		UpdateAddressResponse response = new UpdateAddressResponse();
		response.setAddress(address.toAddressType());
		return response;
	}

	@PayloadRoot(localPart = "DeleteAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public DeleteAddressResponse deleteAddress(@RequestPayload DeleteAddressRequest request) {
		boolean result = addressService.deleteAddress(request.getId());
		DeleteAddressResponse response = new DeleteAddressResponse();
		response.setResult(result);
		return response;
	}

}
