package com.sapient.auction.userservice.exception;

public class ServiceException extends Exception {

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * item dao exception constructor The cause is not initialized, and may
	 * subsequently be initialized by a call.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Dao exception which take string message as argument
	 * 
	 * @param msg
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Dao exception which takes throwable as arguments.
	 * 
	 * @param throwable
	 */
	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Dao exception which takes string and throwable as arguments.
	 * 
	 * @param msg
	 * @param throwable
	 */
	public ServiceException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
