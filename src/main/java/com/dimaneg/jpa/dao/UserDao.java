package com.dimaneg.jpa.dao;

import javax.persistence.EntityManager;

import com.dimaneg.jpa.entity.User;

public class UserDAO extends GenericDAO<User, Long> {

	public UserDAO(EntityManager em) {
		super(em);
	}

	
}
