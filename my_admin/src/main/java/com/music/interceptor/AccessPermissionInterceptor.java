package com.music.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.music.model.User;
import com.music.utils.ConstantUtil;

public class AccessPermissionInterceptor extends HandlerInterceptorAdapter{
	
	private Set<String> excludeUrls;
	
	/**
	 * 进入控制器之前的处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute(ConstantUtil.SESSION_USER);
		
		
		String reqUrl = request.getRequestURL().toString();
		for(String exclude : excludeUrls){
			if(reqUrl.contains(exclude)) return true;
		}
		if(user == null){
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

	public void setExcludeUrls(Set<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
}
