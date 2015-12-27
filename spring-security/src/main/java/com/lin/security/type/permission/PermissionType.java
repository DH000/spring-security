package com.lin.security.type.permission;

import com.lin.security.type.BaseEnumType;
import com.lin.security.type.user.SexType;

public enum PermissionType implements BaseEnumType {
	ALL(-1, "全部"), MENU(1, "菜单"), BUTTON(2, "按钮"), LINK(3, "链接");

	private int no;
	private String name;
	
	private PermissionType() {
	}

	private PermissionType(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int getNo() {
		return this.no;
	}

	@Override
	public BaseEnumType get(int no) {
		for(SexType type : SexType.values()){
			if(no == type.getNo()){
				return type;
			}
		}
		return null;
	}
	
}
