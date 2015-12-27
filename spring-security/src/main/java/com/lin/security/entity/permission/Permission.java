package com.lin.security.entity.permission;

import java.io.Serializable;
import java.util.Date;

import com.lin.security.type.permission.PermissionType;

/**
 * 
 * desc: 权限
 * 
 * @author xuelin
 * @date Dec 18, 2015
 */
public class Permission implements Serializable {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = -2728288165766610212L;
	
	private Integer id;
	private String name;
	private PermissionType type;
	private String url;
	private Integer parentId;
	private String parentIds;
	private String alias;
	private String sort;
	private Short isDelete;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public PermissionType getType() {
		return type;
	}
	
	public void setType(PermissionType type) {
		this.type = type;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getParentIds() {
		return parentIds;
	}
	
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Short getIsDelete() {
		return isDelete;
	}
	
	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", parentId=" + parentId + ", parentIds=" + parentIds + ", alias=" + alias
				+ ", sort=" + sort + ", isDelete=" + isDelete + ", createTime=" + createTime + "]";
	}
	
}
