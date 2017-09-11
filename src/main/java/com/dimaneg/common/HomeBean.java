package com.dimaneg.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dimaneg.jpa.dao.UserDAO;
import com.dimaneg.jpa.dao.UserService;
import com.dimaneg.jpa.entity.User;

@ManagedBean
public class HomeBean {

	private String value;
	
	//@EJB
	//private UserDao userDao;
	
	@EJB
	private UserService userService;

	@PostConstruct
	public void init() {
		value = "test-z";
		System.out.println("HomeBean initialized");
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		UserDAO userDao = userService.getUserDao();
		users = userDao.findAll();
		
		return users;
	}
}
