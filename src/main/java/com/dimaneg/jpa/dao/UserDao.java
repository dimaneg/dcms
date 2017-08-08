package com.dimaneg.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.dimaneg.jpa.entity.User;

public class UserDao {

	private EntityManager em;
	
	public UserDao(EntityManager em) {
		this.em = em;
	}

	public List<User> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> rootEntry = cq.from(User.class);
		CriteriaQuery<User> all = cq.select(rootEntry);
		TypedQuery<User> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
}
