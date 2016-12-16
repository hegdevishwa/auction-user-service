/* Copyright (C) 2016 Sapient. All Rights Reserved. */
package com.sapient.auction.userservice.domain.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapient.auction.userservice.domain.dao.UserDao;
import com.sapient.auction.userservice.domain.model.User;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Adds a user record in the system.
	 *
	 * @param user
	 *            The person POJO to persist.
	 */
	@Override
	public User create(User user) {

		if (user.getUserId() == 0) {
			entityManager.persist(user);
			return user;
		} else {
			entityManager.merge(user);
			return user;
		}
	}

	/**
	 * return the user of given id.
	 */
	@Override
	public User getUser(Integer userid) {

		return entityManager.find(User.class, userid);
	}

	/**
	 * return the user details of given user name.
	 */
	@Override
	public User getUserByUserName(String userName) {
		System.out.println(">>>>>>>>>>>>>>>>>> "+userName);

		Query query = entityManager.createQuery("select u from User u where userName = :user_name");
		query.setParameter("user_name", userName);
		query.toString();
		System.out.println(query.getSingleResult());
		User user = (User) query.getSingleResult();
		System.out.println(user);
		return user;
	}

	@Override
	public boolean isUserExist(String userName) {

		Query query = entityManager.createQuery("select count(*) from User where userName=:userName");
		query.setParameter("userName", userName);
		Long cnt = (Long) query.getSingleResult();
		System.out.println(cnt);
		return cnt != null && cnt > 0;
	}

}
