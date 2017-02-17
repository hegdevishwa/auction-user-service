/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 */
@Entity
public class User {

	@Id
	@Column(name = "userid")
	@GeneratedValue
	private int userId;

	@Column(name = "fname")
	private String firstName;

	@Column(name = "lname")
	private String lastName;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date_time")
	private Date createDate;

	/**
	 * @return user id.
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * set user id.
	 * 
	 * @param userId.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return username of given user.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * set username of given user.
	 * 
	 * @param userName.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return user password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set user password.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return user creation date.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * set user creation date
	 * 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;

		if (userId != other.userId) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ",  userName="
				+ userName + ", password=" + password + ", createDate=" + createDate + "]";
	}
}
