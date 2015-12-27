package com.lin.security.userdetails;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import com.lin.security.menu.Menu;

/**
 * 
 * desc:   带盐
 * @author xuelin
 * @date   Dec 24, 2015
 */
public class UserDetail extends User {

	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
	private String salt;
	private Set<Menu> menus;
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, String salt) {
		super(username, password, authorities);
		this.salt = salt;
	}
	
	public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, String salt, Set<Menu> menus) {
		super(username, password, authorities);
		this.salt = salt;
		this.menus = menus;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
