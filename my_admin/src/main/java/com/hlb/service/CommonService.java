package com.hlb.service;

import java.util.List;

public interface CommonService<T> {

	int add(T entity);
	
	int deleteById(Integer id);
	
	int update(T entity);
	
	T getById(Integer id);
	
	List<T> getListByCondition(T entity);
	
	List<T> getAll();
}
