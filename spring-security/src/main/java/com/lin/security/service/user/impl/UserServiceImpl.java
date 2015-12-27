package com.lin.security.service.user.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lin.security.entity.user.User;
import com.lin.security.mapper.user.UserMapper;
import com.lin.security.service.user.UserService;

/**
 * 
 * desc:   用户业务逻辑
 * @author xuelin
 * @date   Dec 17, 2015
 */
public class UserServiceImpl implements UserService {
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * 
	 * desc: 获取用户信息
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public User find(Long id) {
		Assert.notNull(id, "用户id不能为null");
		return userMapper.find(id);
	}
	
	/**
	 * 
	 * desc: 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public User findByUername(String username){
		Assert.hasText(username, "用户名不能为空");
		return userMapper.findByUsername(username);
	}
	
	/**
	 * 
	 * desc: 根据用户名获取密码
	 * @param username
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public String findPasswordByUsername(String username){
		Assert.hasText(username);
		return userMapper.findPasswordByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public Long count() {
		return userMapper.count();
	}
	
}




