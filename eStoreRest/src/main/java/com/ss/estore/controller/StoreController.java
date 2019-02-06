package com.ss.estore.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.estore.model.Address;
import com.ss.estore.model.Store;
import com.ss.estore.service.StoreService;

/**
 * Handles requests for the Store service.
 */
@Controller
public class StoreController {

	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	StoreService service;

	@RequestMapping(value = "/rest/store/test", method = RequestMethod.GET)
	public @ResponseBody Store getTestStore() {
		logger.info("Start get test Store");
		Address address1 = new Address();
		address1.setAddressType("S");
		address1.setAddrLine1("Street1");
		address1.setCity("Hyderabad");
		address1.setCountry("India");
		address1.setEmail1("test@test.com");
		address1.setFirstName("fName");
		address1.setLastName("lName");
		address1.setPinCode(500001);
		address1.setState("Telangana");
		address1.setStatus("P");

		Address address2 = new Address();
		address2.setAddressType("B");
		address2.setAddrLine1("Street1");
		address2.setCity("Hyderabad");
		address2.setCountry("India");
		address2.setEmail1("test@test.com");
		address2.setFirstName("fName");
		address2.setLastName("lName");
		address2.setPinCode(500001);
		address2.setState("Telangana");
		address2.setStatus("P");

		List<Address> addrList = new ArrayList<Address>();
		addrList.add(address1);
		addrList.add(address2);
		Store store = new Store();
		store.setStoreName("Test Store");
		store.setAddress(addrList);
		// store.setCreatedDate(new java.sql.Date(, arg1, arg2));
		return store;
	}

	@RequestMapping(value = "/rest/store/{id}", method = RequestMethod.GET)
	public @ResponseBody Store getStore(@PathVariable("id") int storeId) {
		logger.info("Start getStore. ID=" + storeId);
		return service.fetch(storeId);
	}

	@RequestMapping(value = "/rest/stores", method = RequestMethod.GET)
	public @ResponseBody List<Store> getAllStores() {
		logger.info("Start getAllStores.");
		return service.listAll();
	}

	@RequestMapping(value = "/rest/store/create", method = RequestMethod.POST)
	public @ResponseBody String createStore(@RequestBody Store store) {
		logger.info("Start createStore.");
		store.getAddress().forEach(address -> address.setStore(store));
		service.add(store);
		return "New store created";
	}

	@RequestMapping(value = "/rest/store/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody String deleteStore(@PathVariable("id") int storeId) {
		logger.info("Start deleteStore.");
		return "store deleted";
	}

}
