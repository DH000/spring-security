package com.lin.security.service.permission.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.lin.security.entity.permission.Permission;
import com.lin.security.mapper.permission.PermissionMapper;
import com.lin.security.page.PageInfo;
import com.lin.security.service.permission.PermissionService;
import com.lin.security.type.permission.PermissionType;

/**
 * 
 * desc:   权限
 * @author xuelin
 * @date   Dec 18, 2015
 */
public class PermissionServiceImpl implements PermissionService {
	private PermissionMapper permissionMapper;
	
	public void setPermissionMapper(PermissionMapper permissionMapper) {
		this.permissionMapper = permissionMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public Permission find(Integer id) {
		Assert.notNull(id, "权限id不恩功能为null");
		return permissionMapper.find(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Permission> findByUser(Long userId){
		Assert.notNull(userId, "用户id不能为null");
		return permissionMapper.findByUser(userId);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Permission> findMenuByUser(Long userId){
		Assert.notNull(userId, "用户id不能为null");
		return permissionMapper.findByUserAndType(userId, PermissionType.MENU);
	}
	
	@Transactional(readOnly = true)
	@Override
	public int count(){
		return this.count(null);
	}
	
	@Transactional(readOnly = true)
	@Override
	public int count(Long userId){
		return this.count(userId, null);
	}
	
	@Transactional(readOnly = true)
	@Override
	public int count(Long userId, PermissionType type){
		return permissionMapper.count(userId, type);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<Permission> findForPage(int currPage, short pageSize){
		return this.findForPage(null, currPage, pageSize);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<Permission> findForPage(Long userId, int currPage, short pageSize){
		return this.findForPage(userId, null, currPage, pageSize);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PageInfo<Permission> findForPage(Long userId, PermissionType type, int currPage, short pageSize){
		int total = this.count(userId, type);
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(currPage, pageSize, total);
		
		int offset = pageInfo.getOffset().intValue();
		List<Permission> permissions = permissionMapper.findForPage(userId, type, offset, pageSize);
		pageInfo.setList(permissions);
		
		return pageInfo;
	}
	
}





