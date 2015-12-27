package com.lin.security.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.lin.security.common.Constants;
import com.lin.security.common.ObjectUtils;
import com.lin.security.common.StringUtils;

public class BaseController {
	
	/**
	 * 
	 * desc: 参数只有单个值取回单个值，否则取回多个值
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		Map<String, Object> targetMap = new HashMap<String, Object>(params.size());
		for(Entry<String, String[]> entry : params.entrySet()){
			String key = entry.getKey();
			String[] vals = entry.getValue();
			boolean isEmpty = ObjectUtils.isEmpty(vals);
			
			if(!isEmpty && vals.length > 1){
				targetMap.put(key, vals);
			}else if(!isEmpty){
				targetMap.put(key, vals[0]);
			}else{
				targetMap.put(key, null);
			}
		}
		
		return targetMap;
	}
	
	/**
	 * 
	 * desc: 获取session
	 * @param request
	 * @return
	 */
	public static HttpSession getHttpSession(HttpServletRequest request){
		return request.getSession();
	}
	
	@ModelAttribute
	public void setPrincipal(HttpServletRequest request, HttpSession session, Map<String, Object> map){
		// 菜单高亮
		String reqUri = request.getRequestURI();
		String index = request.getParameter("index");
		if(StringUtils.hasText(index) || Constants.WEB_HOME.equals(reqUri)){
			session.setAttribute(Constants.MENU_INDEX, index);
			map.put("index", index);
		}else{
			index = (String) session.getAttribute(Constants.MENU_INDEX);
			map.put("index", index);
		}
	}
	
}
