package com.lin.security.type;

/**
 * 
 * desc:   TODO
 * @author xuelin
 * @date   Dec 17, 2015
 */
public interface BaseEnumType {

	/**
	 * 
	 * desc: 获取编号
	 * @return
	 */
	public int getNo();
	
	/**
	 * 
	 * desc: 通过编号获取枚举类型
	 * @param no
	 * @return
	 */
	public BaseEnumType get(int no);
	
}
