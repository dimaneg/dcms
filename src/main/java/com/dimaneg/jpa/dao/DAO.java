package com.dimaneg.jpa.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, ID extends Serializable> {  
	  
    T findById(ID id);  
  
    List<T> findAll();  
 
} 