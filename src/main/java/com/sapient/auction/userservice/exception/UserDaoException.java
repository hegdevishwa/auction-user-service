/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.exception;

/**
 * @author avish9 user dao exception
 */
public class UserDaoException extends Exception {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * item dao exception constructor
	 * The cause is not initialized, and may subsequently be initialized by a call.
	 */
	public UserDaoException() {
		super();
	}

	/**
	 * Dao exception which take string message as argument
	 * 
	 * @param msg
	 */
	public UserDaoException(String msg) {
		super(msg);
	}

	/**
	 * Dao exception which takes throwable as arguments.
	 * 
	 * @param throwable
	 */
	public UserDaoException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Dao exception which takes string and throwable as arguments.
	 * 
	 * @param msg
	 * @param throwable
	 */
	public UserDaoException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
