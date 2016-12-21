/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.services;

import java.util.Map;

import com.sapient.auction.userservice.domain.model.User;
import com.sapient.auction.userservice.exception.ServiceException;

/**
 * @author avish9 Service for operating upon {@link User} objects.
 */
public interface UserService {

	/**
	 * @param user
	 *            create new user.
	 */
	public void createUser(User user) throws ServiceException;

	/**
	 * @param userid
	 * @return user details of given userid.
	 */
	public User getUser(Integer userid);

	/**
	 * @param userid
	 * @return user details of given user name.
	 */
	public User getUserByUserName(String userName);
	
	public Map<String, Object> login(final String userName, final String password);

}
