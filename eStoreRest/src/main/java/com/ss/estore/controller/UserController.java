package com.ss.estore.controller;

import java.sql.Date;
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
import com.ss.estore.model.User;
import com.ss.estore.service.UserService;

/**
 * Handles requests for the User service.
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	@RequestMapping(value = "/rest/user/test", method = RequestMethod.GET)
	public @ResponseBody User getTestUser() {
		logger.info("Start getTestUser");
		Address address1 = new Address();
		address1.setAddressType("S");
		address1.setAddrLine1("Shipping Street");
		address1.setCity("Hyderabad");
		address1.setCountry("India");
		address1.setEmail1("test@email.com");
		address1.setFirstName("fName");
		address1.setLastName("lName");
		address1.setPinCode(500001);
		address1.setState("Telangana");
		address1.setStatus("P");

		Address address2 = new Address();
		address2.setAddressType("B");
		address2.setAddrLine1("Billing Street");
		address2.setCity("Hyderabad");
		address2.setCountry("India");
		address2.setEmail1("test@email.com");
		address2.setFirstName("fName");
		address2.setLastName("lName");
		address2.setPinCode(500001);
		address2.setState("Telangana");
		address2.setStatus("P");

		List<Address> addrList = new ArrayList<Address>();
		addrList.add(address1);
		addrList.add(address2);
		User user = new User();
		user.setUserName("UniqueUserName");
		user.setPassword("*******");
		user.setUserId(0);
		user.setActive("Y");
		//user.setCreatedDate(new Date(2019, 12, 12));
		user.setAddress(addrList);
		return user;
	}

	@RequestMapping(value = "/rest/user/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("id") int userId) {
		logger.info("Start getUser. ID=" + userId);
		return service.fetch(userId);
	}

	@RequestMapping(value = "/rest/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		logger.info("Start getAllUsers.");
		return service.listAll();
	}

	@RequestMapping(value = "/rest/user/create", method = RequestMethod.POST)
	public @ResponseBody String createUser(@RequestBody User user) {
		logger.info("Start createUser.");
		user.getAddress().forEach(address -> address.setUser(user));
		service.add(user);
		return "New user created";
	}

	@RequestMapping(value = "/rest/user/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody String deleteUser(@PathVariable("id") int userId) {
		logger.info("Start deleteUser.");
		service.deleteUser(userId);
		return "user deleted";
	}

}
