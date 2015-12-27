package com.lin.security.password;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Utf8;

import com.lin.security.common.EncodeUtils;

/**
 * 
 * desc: 密码校验
 * 
 * @author xuelin
 * @date Dec 24, 2015
 */
public class UserPasswordEncoder implements SaltPasswordEncoder {
	protected static final String DEFAULT_ENCODE_NAME = "MD5";
	protected static final short DEFAULT_ENCODE_NUM = 1;
	
	private String encodeName = DEFAULT_ENCODE_NAME;
	private short encodeNum = DEFAULT_ENCODE_NUM;
	
	public void setEncodeName(String encodeName) {
		this.encodeName = encodeName;
	}
	
	public void setEncodeNum(short encodeNum) {
		this.encodeNum = encodeNum;
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return encode(rawPassword, null);
	}
	
	/**
	 * 
	 * desc: 加盐加密
	 * 
	 * @param rawPassword
	 * @param saltSource
	 * @return
	 */
	public String encode(CharSequence rawPassword, CharSequence saltSource) {
		try {
			return EncodeUtils.encode(rawPassword.toString(), saltSource.toString(), encodeName, (int) encodeNum);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return matches(rawPassword, encodedPassword, null);
	}
	
	/**
	 * 
	 * desc: 加盐校验
	 * 
	 * @param rawPassword
	 * @param encodedPassword
	 * @param saltSource
	 * @return
	 */
	public boolean matches(CharSequence rawPassword, String encodedPassword, String saltSource) {
		return equals(encodedPassword, encode(rawPassword, saltSource));
	}
	
	/**
	 * 
	 * desc: 校验算法
	 * 
	 * @param expected
	 * @param actual
	 * @return
	 */
	static boolean equals(String expected, String actual) {
		byte[] expectedBytes = bytesUtf8(expected);
		byte[] actualBytes = bytesUtf8(actual);
		int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
		int actualLength = actualBytes == null ? -1 : actualBytes.length;
		if (expectedLength != actualLength) {
			return false;
		}
		
		int result = 0;
		for (int i = 0; i < expectedLength; i++) {
			result |= expectedBytes[i] ^ actualBytes[i];
		}
		return result == 0;
	}
	
	private static byte[] bytesUtf8(String s) {
		if (s == null) {
			return null;
		}
		
		return Utf8.encode(s);
	}
	
}
