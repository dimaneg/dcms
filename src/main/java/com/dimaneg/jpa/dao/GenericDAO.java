package com.dimaneg.jpa.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class GenericDAO<T, ID extends Serializable> implements DAO<T, ID> {

	private Class<T> persistentClass; 
	
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(EntityManager em) {
		this.em = em;
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()  
                .getGenericSuperclass()).getActualTypeArguments()[0];  
	}
	
	public Class<T> getPersistentClass() {  
        return persistentClass;  
    }
	
	public EntityManager getEntityManager() {
		return em;
	}

	public List<T> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getPersistentClass());
		Root<T> rootEntry = cq.from(getPersistentClass());
		CriteriaQuery<T> all = cq.select(rootEntry);
		TypedQuery<T> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}
	
	public void save(T t) {
		em.persist(t);
	}
	
	public T findById(ID id) {
		return em.find(getPersistentClass(), id);
	}
}
