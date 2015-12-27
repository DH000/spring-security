package com.lin.security.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.security.service.user.UserService;

/**
 * 
 * desc:   首页
 * @author xuelin
 * @date   Dec 20, 2015
 */
@Controller
public class IndexController extends BaseController {
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * desc: 首页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest request, Map<String, Object> map){
		// 统计模块
		Long userCount = userService.count();
		map.put("userCount", userCount);
		
		return "index";
	}
	
}




