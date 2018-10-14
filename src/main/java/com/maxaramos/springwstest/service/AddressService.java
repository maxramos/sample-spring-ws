package com.maxaramos.springwstest.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.maxaramos.springwstest.model.Address;

@Service
public class AddressService {

	private Map<Long, Address> map = new LinkedHashMap<>();

	public List<Address> getAllAddress() {
		return new ArrayList<>(map.values());
	}

	public Address addAddress(Address address) {
		Long key = Instant.now().toEpochMilli();
		address.setId(key);
		map.put(key, address);
		return address;
	}

	public Address getAddress(Long id) {
		return map.get(id);
	}

	public Address updateAddress(Address address) {
		map.put(address.getId(), address);
		return address;
	}

	public boolean deleteAddress(Long id) {
		map.remove(id);
		return true;
	}

}
