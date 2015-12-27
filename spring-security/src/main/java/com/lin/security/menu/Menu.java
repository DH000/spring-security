package com.lin.security.menu;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.lin.security.common.StringUtils;

/**
 * 
 * desc: 菜单
 * 
 * @author xuelin
 * @date Dec 20, 2015
 */
public class Menu implements Serializable, Comparable<Menu> {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = 6267850244246731196L;
	
	private Integer id;
	private Integer parentId;
	private String name;
	private String url;
	private String sort;
	
	private Set<Menu> subs;
	
	public Menu() {
		super();
		this.subs = new TreeSet<Menu>();
	}
	
	public Menu(Integer id, Integer parentId, String name, String url, String sort) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.url = url;
		this.subs = new TreeSet<Menu>();
		this.sort = sort;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Set<Menu> getSubs() {
		return subs;
	}
	
	public void setSubs(Set<Menu> subs) {
		this.subs = subs;
	}
	
	public void addSubMenu(Menu menu){
		if(null != menu){
			this.subs.add(menu);
		}
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public int compareTo(Menu o) {
		if(!StringUtils.hasText(o.getSort()) || !StringUtils.hasText(this.sort)){
			return 1;
		}
		return sort.compareTo(o.getSort());
	}
	
}
