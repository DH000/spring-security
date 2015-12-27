package com.lin.security.type.user;

import com.lin.security.type.BaseEnumType;

/**
 * 
 * desc:   性别类型
 * @author xuelin
 * @date   Dec 17, 2015
 */
public enum SexType implements BaseEnumType {
	ALL(-1, "全部"), MALE(1, "男"), FEMALE(2, "女");

	private int no;
	private String name;
	
	private SexType() {
	}

	private SexType(int no) {
		this.no = no;
	}

	private SexType(int no, String name) {
		this.no = no;
		this.name = name;
	}

	@Override
	public int getNo() {
		return this.no;
	}

	public String getName() {
		return this.name;
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
