package com.maxaramos.springwstest.model;

import java.io.Serializable;

import com.maxaramos.springwstest.address.schema.AddAddressRequest;
import com.maxaramos.springwstest.address.schema.AddressType;
import com.maxaramos.springwstest.address.schema.UpdateAddressRequest;

public class Address implements Serializable {

	private static final long serialVersionUID = -3927773470528750517L;

	private Long id;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	public static Address fromRequest(AddAddressRequest request) {
		Address address = new Address();
		address.address1 = request.getAddress1();
		address.address2 = request.getAddress2();
		address.city = request.getCity();
		address.state = request.getState();
		address.country = request.getCountry();
		address.zipCode = request.getZipCode();
		return address;
	}

	public static Address fromRequest(UpdateAddressRequest request) {
		Address address = new Address();
		address.id = request.getAddress().getId();
		address.address1 = request.getAddress().getAddress1();
		address.address2 = request.getAddress().getAddress2();
		address.city = request.getAddress().getCity();
		address.state = request.getAddress().getState();
		address.country = request.getAddress().getCountry();
		address.zipCode = request.getAddress().getZipCode();
		return address;
	}

	public AddressType toAddressType() {
		AddressType addressType = new AddressType();
		addressType.setId(id);
		addressType.setAddress1(address1);
		addressType.setAddress2(address2);
		addressType.setCity(city);
		addressType.setState(state);
		addressType.setCountry(country);
		addressType.setZipCode(zipCode);
		return addressType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
