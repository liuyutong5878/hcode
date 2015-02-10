package com.music.service;

import com.music.model.WebUser;
import com.music.core.model.PageObject;
import com.music.core.service.CommonService;

public interface WebUserService extends CommonService<WebUser>{
	
	PageObject<WebUser> listByPage(Integer pageNow);
	
}
