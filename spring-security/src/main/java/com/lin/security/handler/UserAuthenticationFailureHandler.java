package com.lin.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.lin.security.common.Constants;
import com.lin.security.exception.CaptchaException;
import com.lin.security.type.AuthenticateStatus;

/**
 * 
 * desc:   认证失败
 * @author xuelin
 * @date   Dec 24, 2015
 */
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String status = AuthenticateStatus.FAILURE.toString();
		if(exception instanceof CaptchaException){
				status = AuthenticateStatus.CAPTCHA_FAILURE.toString();
		}else{
			// 激活验证码
			openCaptchaValidate(request);
		}
		response.sendRedirect("/user/login?status=" + status);
	}
	
	protected void openCaptchaValidate(HttpServletRequest request){
		if(isCaptchaEnabled(request)){
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(Constants.CAPTCHA_ENABLED, Boolean.TRUE);
	}
	
	protected boolean isCaptchaEnabled(HttpServletRequest request){
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute(Constants.CAPTCHA_ENABLED);
		return (null != status && status);
	}
	
}
