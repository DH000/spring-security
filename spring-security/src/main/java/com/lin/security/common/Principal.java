package com.lin.security.common;

import java.io.Serializable;
import java.util.List;

import com.lin.security.menu.Menu;

/**
 * 
 * desc:   认证用户身份信息
 * @author xuelin
 * @date   Dec 19, 2015
 */
public class Principal implements Serializable {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = -6125545414878971314L;
	private Long userId;
	private String username;
	private String imgPath;
	
	/**
	 * 用户菜单
	 */
	private List<Menu> menus;
	
	public Principal() {
		super();
	}

	public Principal(Long userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}





