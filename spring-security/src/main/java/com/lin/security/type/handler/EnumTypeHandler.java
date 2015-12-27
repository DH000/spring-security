package com.lin.security.type.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lin.security.common.LogUtils;
import com.lin.security.common.ObjectUtils;
import com.lin.security.common.StringUtils;
import com.lin.security.type.BaseEnumType;

/**
 * 
 * desc:   枚举类型转换
 * @author xuelin
 * @date   Dec 17, 2015
 */
public class EnumTypeHandler extends BaseTypeHandler<BaseEnumType> {
	static Logger logger = LoggerFactory.getLogger(EnumTypeHandler.class);
	
	private Class<BaseEnumType> type;
	private BaseEnumType[] enumConstants;
	
	public EnumTypeHandler(Class<BaseEnumType> type){
		this.type = type;
		LogUtils.info(logger, "初始化枚举类型--" + type.getName());
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BaseEnumType parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getNo());
	}

	@Override
	public BaseEnumType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		return convertResultToEnum(value);
	}

	@Override
	public BaseEnumType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return convertResultToEnum(value);
	}

	@Override
	public BaseEnumType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return convertResultToEnum(value);
	}
	
	private BaseEnumType convertResultToEnum(String value){
		if(!StringUtils.hasText(value)){
			return null;
		}
		
		if(ObjectUtils.isEmpty(enumConstants)){
			enumConstants = type.getEnumConstants();
		}
		
		// 类型编号
		int no = Integer.valueOf(value);
		
		for(BaseEnumType enumType : enumConstants){
			if(no == enumType.getNo()){
				return enumType;
			}
		}
		
		return null;
	}
}
