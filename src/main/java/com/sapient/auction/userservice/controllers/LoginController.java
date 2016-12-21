/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.auction.userservice.domain.model.User;
import com.sapient.auction.userservice.services.UserService;

@RestController
@RequestMapping("/api/userservice")
public class LoginController {

	/** logger of user controller. */
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("Method login");
		String userName = user.getUserName();
		String password = user.getPassword();
		if (userName == null || password == null) {
			return ResponseEntity.badRequest().body(new Error("User or password missing, or malformed request"));
		}
		try {
			Map<String, Object> data = userService.login(userName, password);
			return ResponseEntity.ok(data);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new Error(e.getMessage()));
		}
	}

}