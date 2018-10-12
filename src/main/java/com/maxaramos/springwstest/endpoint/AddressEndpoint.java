package com.maxaramos.springwstest.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.springwstest.address.AddAddressRequest;
import com.maxaramos.springwstest.address.AddAddressResponse;
import com.maxaramos.springwstest.address.AddressType;
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
		Address address = addressService.addAddress(fromRequest(request));
		AddAddressResponse response = new AddAddressResponse();
		response.setAddress(fromAddress(address));
		return response;
	}

	@PayloadRoot(localPart = "GetAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetAddressResponse getAddress(@RequestPayload GetAddressRequest request) {
		Address address = addressService.getAddress(request.getId());
		GetAddressResponse response = new GetAddressResponse();
		response.setAddress(fromAddress(address));
		return response;
	}

	@PayloadRoot(localPart = "UpdateAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public UpdateAddressResponse updateAddress(@RequestPayload UpdateAddressRequest request) {
		Address address = addressService.updateAddress(fromRequest(request));
		UpdateAddressResponse response = new UpdateAddressResponse();
		response.setAddress(fromAddress(address));
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

	public static Address fromRequest(AddAddressRequest request) {
		Address address = new Address();
		address.setAddress1(request.getAddress1());
		address.setAddress2(request.getAddress2());
		address.setCity(request.getCity());
		address.setState(request.getState());
		address.setCountry(request.getCountry());
		address.setZipCode(request.getZipCode());
		return address;
	}

	public static Address fromRequest(UpdateAddressRequest request) {
		Address address = new Address();
		address.setId(request.getAddress().getId());
		address.setAddress1(request.getAddress().getAddress1());
		address.setAddress2(request.getAddress().getAddress2());
		address.setCity(request.getAddress().getCity());
		address.setState(request.getAddress().getState());
		address.setCountry(request.getAddress().getCountry());
		address.setZipCode(request.getAddress().getZipCode());
		return address;
	}

	public AddressType fromAddress(Address address) {
		AddressType addressType = new AddressType();
		addressType.setId(address.getId());
		addressType.setAddress1(address.getAddress1());
		addressType.setAddress2(address.getAddress2());
		addressType.setCity(address.getCity());
		addressType.setState(address.getState());
		addressType.setCountry(address.getCountry());
		addressType.setZipCode(address.getZipCode());
		return addressType;
	}

}
