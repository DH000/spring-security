package com.lin.security.mapper.user;

import com.lin.security.entity.user.User;

/**
 * 
 * desc:   用户
 * @author xuelin
 * @date   Dec 17, 2015
 */
public interface UserMapper {
	
	public User find(Long id);
	
	public User findByUsername(String username);
	
	public String findPasswordByUsername(String username);
	
	public long count();
	
}
