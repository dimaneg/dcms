package com.dimaneg.jpa.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dimaneg.jpa.entity.User;

@Stateless
public class UserService {

	@PersistenceContext(unitName = "dcms")
	private EntityManager em;
	
	public void save(User user) {
		getUserDao().save(user);
	}
	
	public UserDAO getUserDao() {
		UserDAO userDao = new UserDAO(em);
		return userDao;
	}
}
