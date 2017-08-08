package com.dimaneg.jpa.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserService {

	@PersistenceContext(unitName = "dcms")
	private EntityManager em;
	
	public UserDao getUserDao() {
		UserDao userDao = new UserDao(em);
		return userDao;
	}
}
