package com.lin.security.mapper.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lin.security.entity.permission.Permission;
import com.lin.security.type.permission.PermissionType;

/**
 * 
 * desc:   权限
 * @author xuelin
 * @date   Dec 18, 2015
 */
public interface PermissionMapper {
	
	public Permission find(Integer id);
	
	public List<Permission> findByUser(Long userId);
	
	public int count(@Param("userId") Long userId, @Param("type") PermissionType type);
	
	public List<Permission> findByUserAndType(@Param("userId") Long userId, @Param("type") PermissionType type);
	
	public List<Permission> findForPage(@Param("userId") Long userId, @Param("type") PermissionType type, @Param("offset")int offset, @Param("length")short length);
	
}
