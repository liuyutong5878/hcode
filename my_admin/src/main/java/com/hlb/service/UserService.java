package com.hlb.service;

import com.hlb.model.User;

public interface UserService extends CommonService<User>{
	
	boolean isExist(User user);
	
}
