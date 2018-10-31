package com.maxaramos.samplespringws.endpoint;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.maxaramos.samplespringws.address.AddAddressRequest;
import com.maxaramos.samplespringws.address.AddAddressResponse;
import com.maxaramos.samplespringws.address.AddressType;
import com.maxaramos.samplespringws.address.DeleteAddressRequest;
import com.maxaramos.samplespringws.address.DeleteAddressResponse;
import com.maxaramos.samplespringws.address.GetAddressRequest;
import com.maxaramos.samplespringws.address.GetAddressResponse;
import com.maxaramos.samplespringws.address.GetAllAddressRequest;
import com.maxaramos.samplespringws.address.GetAllAddressResponse;
import com.maxaramos.samplespringws.address.UpdateAddressRequest;
import com.maxaramos.samplespringws.address.UpdateAddressResponse;
import com.maxaramos.samplespringws.model.Address;
import com.maxaramos.samplespringws.service.AddressService;

@Endpoint
public class AddressEndpoint {

	private static final String NAMESPACE_URI = "http://samplespringws.maxaramos.com/address";

	@Autowired
	private Logger log;

	@Autowired
	private AddressService addressService;

	@PayloadRoot(localPart = "GetAllAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetAllAddressResponse getAllAddress(@RequestPayload GetAllAddressRequest request) {
		List<Address> addresses = addressService.getAllAddress();
		log.info("getAllAddress: " + addresses);
		GetAllAddressResponse response = new GetAllAddressResponse();
		response.getAddresses().addAll(addresses.stream().map(address -> fromAddress(address)).collect(Collectors.toList()));
		return response;
	}

	@PayloadRoot(localPart = "AddAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public AddAddressResponse addAddress(@RequestPayload AddAddressRequest request) {
		Address address = addressService.addAddress(fromRequest(request));
		log.info("addAddress: " + address);
		AddAddressResponse response = new AddAddressResponse();
		response.setAddress(fromAddress(address));
		return response;
	}

	@PayloadRoot(localPart = "GetAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public GetAddressResponse getAddress(@RequestPayload GetAddressRequest request) {
		Address address = addressService.getAddress(request.getId());
		log.info("getAddress: " + address);
		GetAddressResponse response = new GetAddressResponse();
		response.setAddress(fromAddress(address));
		return response;
	}

	@PayloadRoot(localPart = "UpdateAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public UpdateAddressResponse updateAddress(@RequestPayload UpdateAddressRequest request) {
		Address address = addressService.updateAddress(fromRequest(request));
		log.info("updateAddress: " + address);
		UpdateAddressResponse response = new UpdateAddressResponse();
		response.setAddress(fromAddress(address));
		return response;
	}

	@PayloadRoot(localPart = "DeleteAddressRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public DeleteAddressResponse deleteAddress(@RequestPayload DeleteAddressRequest request) {
		boolean deleted = addressService.deleteAddress(request.getId());
		log.info("deleteAddress: " + deleted);
		DeleteAddressResponse response = new DeleteAddressResponse();
		response.setDeleted(deleted);
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
		AddressType addressType = request.getAddress();
		Address address = new Address();
		address.setId(addressType.getId());
		address.setAddress1(addressType.getAddress1());
		address.setAddress2(addressType.getAddress2());
		address.setCity(addressType.getCity());
		address.setState(addressType.getState());
		address.setCountry(addressType.getCountry());
		address.setZipCode(addressType.getZipCode());
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
