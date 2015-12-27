package com.lin.security.service.user;

import com.lin.security.entity.user.User;

/**
 * 
 * desc:   用户业务逻辑
 * @author xuelin
 * @date   Dec 17, 2015
 */
public interface UserService {
	
	/**
	 * 
	 * desc: 获取用户信息
	 * @param id
	 * @return
	 */
	public User find(Long id);
	
	/**
	 * 
	 * desc: 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	public User findByUername(String username);
	
	/**
	 * 
	 * desc: 根据用户名获取密码
	 * @param username
	 * @return
	 */
	public String findPasswordByUsername(String username);
	
	public Long count();
	
}
