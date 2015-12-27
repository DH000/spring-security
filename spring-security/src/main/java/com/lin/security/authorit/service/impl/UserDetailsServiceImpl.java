package com.lin.security.authorit.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lin.security.authorit.GrantedResourceAuthority;
import com.lin.security.entity.permission.Permission;
import com.lin.security.menu.Menu;
import com.lin.security.service.permission.PermissionService;
import com.lin.security.service.user.UserService;
import com.lin.security.userdetails.UserDetail;

/**
 * 
 * desc:   security认证
 * @author xuelin
 * @date   Dec 24, 2015
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserService userService;
	private PermissionService permissionService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.lin.security.entity.user.User dbUser = userService.findByUername(username);
		if(null == dbUser){
			throw new UsernameNotFoundException("用户不存在");
		}
		
		Set<GrantedAuthority> authorities = this.loadGrantedResourceAuthorities(dbUser.getId());
		Set<Menu> menus = getMenus(dbUser.getId());
		return new UserDetail(username, dbUser.getPassword(), authorities, dbUser.getSalt(), menus);
	}
	
	protected Set<GrantedAuthority> loadGrantedResourceAuthorities(Long userId){
		List<Permission> permissions = permissionService.findByUser(userId);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(Permission permission : permissions){
			// 直接使用权限的别名
			GrantedAuthority authority = new GrantedResourceAuthority(permission.getAlias());
			authorities.add(authority);
		}
		
		return authorities;
	}
	
	private Set<Menu> getMenus(Long userId){
		// 获取用户菜单
		Set<Menu> menus = new TreeSet<Menu>();
		List<Permission> subMenuPermissions = new ArrayList<Permission>();
		List<Permission> menuPermissions = permissionService.findMenuByUser(userId);
		for(Permission menupermission : menuPermissions){
			Integer parentId = menupermission.getParentId();
			if(null == parentId){
				// 一级菜单
				Menu menu = new Menu(menupermission.getId(), parentId, menupermission.getName(), menupermission.getUrl(), menupermission.getSort());
				menus.add(menu);
			}else{
				// 二级菜单
				subMenuPermissions.add(menupermission);
			}
		}
		
		// 处理二级菜单
		for(Permission subMenuPermission : subMenuPermissions){
			for(Menu menu : menus){
				Integer parentId = subMenuPermission.getParentId();
				if(menu.getId().equals(parentId)){
					Menu subMenu = new Menu(subMenuPermission.getId(), parentId, subMenuPermission.getName(), subMenuPermission.getUrl(), subMenuPermission.getSort());
					menu.addSubMenu(subMenu);
				}
			}
		}
		
		return menus;
	}
	
}
