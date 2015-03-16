package com.music.service;

import com.music.model.User;
import com.music.core.service.CommonService;

public interface UserService extends CommonService<User>{
	
	boolean isExist(User user);
	
}
