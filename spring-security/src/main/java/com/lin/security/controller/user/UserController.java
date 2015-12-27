package com.lin.security.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.security.controller.BaseController;
import com.lin.security.type.AuthenticateStatus;

/**
 * 
 * desc:   用户控制器
 * @author xuelin
 * @date   Dec 17, 2015
 */
@Controller
public class UserController extends BaseController {
	
	/**
	 * 
	 * desc: 认证失败才会进来
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/user/login")
	public String login(HttpServletRequest request, Map<String, Object> map, @Param("status") AuthenticateStatus status){
		if(AuthenticateStatus.CAPTCHA_FAILURE.equals(status)){
			map.put("alert_msg", "验证码不正确");
		}else if(AuthenticateStatus.FAILURE.equals(status)){
			map.put("alert_msg", "帐号或密码不正确");
		}
		
		return "login";
	}
	
}






