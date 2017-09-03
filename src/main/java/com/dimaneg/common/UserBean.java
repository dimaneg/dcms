package com.dimaneg.common;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.dimaneg.jpa.dao.UserDao;
import com.dimaneg.jpa.dao.UserService;
import com.dimaneg.jpa.entity.User;

@ManagedBean
public class UserBean {

	@PersistenceContext(unitName = "dcms", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@EJB
	private UserService userService;

	private String firstName;

	private String lastName;

	private String email;

	@PostConstruct
	public void init() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void save() {

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);

		userService.save(user);

		firstName = null;
		lastName = null;
		email = null;
	}
}
