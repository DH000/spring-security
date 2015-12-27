package com.lin.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lin.security.common.Constants;
import com.lin.security.exception.CaptchaException;

/**
 * 
 * desc:   验证码验证
 * @author xuelin
 * @date   Dec 25, 2015
 */
public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static final String DEFAULT_CPATCHA_NAME = "captcha";
	private String captchaNameParameter = DEFAULT_CPATCHA_NAME;
	
	public void setCaptchaNameParameter(String captchaNameParameter) {
		this.captchaNameParameter = captchaNameParameter;
	}

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		if(isCaptchaEnabled(request)){
			captchaValidate(request);
		}
		
		return super.attemptAuthentication(request, response);
	}
	
	protected boolean isCaptchaEnabled(HttpServletRequest request){
		HttpSession session = request.getSession();
		Boolean status = (Boolean) session.getAttribute(Constants.CAPTCHA_ENABLED);
		return (null != status && status);
	}
	
	private void captchaValidate(HttpServletRequest request){
		String webCaptcha = request.getParameter(this.captchaNameParameter);
		String captcha = getAndStatCaptcha(request);
		if(!captcha.equals(webCaptcha)){
			throw new CaptchaException("验证码不正确");
		}
	}
	
	private String getAndStatCaptcha(HttpServletRequest request){
		HttpSession session = request.getSession();
		Boolean stat = (Boolean) session.getAttribute(Constants.CAPTCHA_STATUS_ATTR);
		if(null == stat || stat){
			throw new CaptchaException("验证码已使用");
		}
		
		Object captcha = session.getAttribute(Constants.CAPTCHA_VALUE_ATTR);
		if(null == captcha){
			throw new CaptchaException("验证码未生成");
		}
		
		session.setAttribute(Constants.CAPTCHA_STATUS_ATTR, Boolean.TRUE);
		return captcha.toString();
	}
	
}
