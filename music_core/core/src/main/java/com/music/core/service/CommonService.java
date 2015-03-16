package com.music.core.service;

import java.util.List;

public interface CommonService<T> {

	int deleteById(Integer id);
	
	T update(T entity);
	
	T getById(Integer id);
	
	List<T> getListByCondition(T entity);
	
	List<T> getAll();
	
	T addByReturnKey(T entity);
}
