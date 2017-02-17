/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.auction.userservice.constants.ApplicationConstants;
import com.sapient.auction.userservice.domain.model.User;
import com.sapient.auction.userservice.exception.ServiceException;
import com.sapient.auction.userservice.exception.UserDaoException;
import com.sapient.auction.userservice.services.UserService;

/**
 * @author avish9 Controller for {@link User} resources.
 */
@RestController
@RequestMapping("/api/userservice")
public class UserController {

	@Autowired
	private UserService userService;

	/** logger of user controller. */
	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	/**
	 * this method landed to you in registration page.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(path = ApplicationConstants.REGISTER_REQUEST, method = RequestMethod.GET)
	public String register(User user) {
		LOGGER.debug("return register page.");

		return ApplicationConstants.REGISTER_VIEW;
	}

	/**
	 * this method will create new user and landed to you in home page.
	 * 
	 * @param user
	 * @param bindingResult
	 * @return home page.
	 * @throws UserDaoException
	 */
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> userRegistration(@RequestBody User user) {

		try {
			userService.createUser(user);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<?> getUSerByName(@RequestParam(name = "username") String userName) {

		System.out.println(">>>>>>>>> " + userName);

		User user = userService.getUserByUserName(userName);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.FOUND).body(user);
		}
	}

}
