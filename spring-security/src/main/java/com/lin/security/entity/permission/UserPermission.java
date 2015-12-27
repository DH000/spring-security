package com.lin.security.entity.permission;

import java.io.Serializable;

/**
 * 
 * desc: 用户权限关系
 * 
 * @author xuelin
 * @date Dec 19, 2015
 */
public class UserPermission implements Serializable {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = -6261367416452627116L;
	
	private Long id;
	private Long userId;
	private Integer permissionId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Integer getPermissionId() {
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	
}
