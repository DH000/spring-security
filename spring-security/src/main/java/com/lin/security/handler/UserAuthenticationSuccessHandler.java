package com.lin.security.handler;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.lin.security.common.Constants;
import com.lin.security.menu.Menu;
import com.lin.security.userdetails.UserDetail;

/**
 * 
 * desc:   登录成功
 * @author xuelin
 * @date   Dec 24, 2015
 */
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		persistToSession(request, authentication);
		response.sendRedirect("/");
	}
	
	protected void persistToSession(HttpServletRequest request, Authentication authentication){
		HttpSession session = request.getSession();
		
		// 保存用户信息到session
		UserDetail userDetails = (UserDetail) authentication.getPrincipal();
		Set<Menu> menus = userDetails.getMenus();
		session.setAttribute(Constants.MENUS, menus);
		session.setAttribute(Constants.USERNAME, userDetails.getUsername());
	}
	
}
