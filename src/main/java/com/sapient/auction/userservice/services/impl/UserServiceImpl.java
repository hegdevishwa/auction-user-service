/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.auction.userservice.domain.dao.UserDao;
import com.sapient.auction.userservice.domain.model.User;
import com.sapient.auction.userservice.exception.ServiceException;
import com.sapient.auction.userservice.exception.UserDaoException;
import com.sapient.auction.userservice.security.JwtTokenService;
import com.sapient.auction.userservice.services.UserService;

/**
 * @author avish9 Basic {@link UserService} implementation.
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	/**
	 * user dao
	 */
	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtTokenService jwtService;

	/**
	 * create new user
	 */
	@Override
	@Transactional
	public void createUser(User user) throws ServiceException {
		LOGGER.debug("user is going to create");
		try {
			if (userDao.isUserExist(user.getUserName())) {
				LOGGER.debug("User alreday registered");
				throw new ServiceException("User Already exists. Please try with different user name");
			}
			String passHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(passHash);
			userDao.create(user);
		} catch (UserDaoException e) {
			LOGGER.error("User dao throw exception", e);
			throw new ServiceException("Error inserting data into database");
		}
	}

	/**
	 * return the user details of given id.
	 */
	@Override
	@Transactional
	public User getUser(Integer userid) {
		try {
			return userDao.getUser(userid);
		} catch (UserDaoException e) {
			LOGGER.error("User dao throw exception because userid is not present in DB", e);
			return new User();
		}
	}

	/**
	 * return the user details of given user name.
	 */
	@Override
	@Transactional
	public User getUserByUserName(String userName) {
		System.out.println(">>>>>>>>> " + userName);
		try {
			return userDao.getUserByUserName(userName);
		} catch (UserDaoException e) {
			LOGGER.error("User dao throw exception because user name is not present in DB", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> login(String userName, String password) {
		User user = null;
		try {
			user = userDao.getUserByUserName(userName);
		} catch (UserDaoException e) {
			LOGGER.error("User dao throw exception because user name is not present in DB", e);
		}

		if (user == null) {
			throw new AuthenticationCredentialsNotFoundException("Bad Username or Password");
		} else if (BCrypt.checkpw(password, user.getPassword())) {
			return (Map<String, Object>) new HashMap<>().put("token", jwtService.buildToken(userName));
		} else {
			throw new AuthenticationCredentialsNotFoundException("Bad Username or Password");
		}
	}
}
