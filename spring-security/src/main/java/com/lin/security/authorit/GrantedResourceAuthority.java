package com.lin.security.authorit;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

/**
 * 
 * desc:   资源权限
 * @author xuelin
 * @date   Dec 24, 2015
 */
public class GrantedResourceAuthority implements GrantedAuthority {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private final String resource;
	
	public GrantedResourceAuthority(String resource){
		Assert.hasText(resource, "资源不能为空");
		this.resource = resource;
	}

	@Override
	public String getAuthority() {
		return this.resource;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		
		if(obj instanceof GrantedResourceAuthority){
			return this.resource.equals(((GrantedResourceAuthority) obj).getAuthority());
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.resource.hashCode();
	}
	
	@Override
	public String toString() {
		return this.resource.toString();
	}
}






