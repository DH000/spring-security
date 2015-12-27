package com.lin.security.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.Assert;

/**
 * 
 * desc: 加密/解密
 * 
 * @author xuelin
 * @date Dec 17, 2015
 */
public final class EncodeUtils {
	
	/**
	 * Used to build output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static final String DEFAULT_ENCODE = "MD5";
	private static final int DEFAULT_REPEAT = 1;
	
	/**
	 * 
	 * desc: 字节数组转字符
	 * 
	 * @param data
	 * @return
	 */
	private static char[] encode(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		
		return out;
	}
	
	/**
	 * Encodes the specifed byte array to a character array and then returns
	 * that character array as a String.
	 *
	 * @param bytes
	 *            the byte array to Hex-encode.
	 * @return A String representation of the resultant hex-encoded char array.
	 */
	public static String encodeToString(byte[] bytes) {
		char[] encodedChars = encode(bytes);
		return new String(encodedChars);
	}
	
	/**
	 * 
	 * desc: 字符加盐加密
	 * @param source
	 * @param salt
	 * @param repeat
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encode(String source, String salt, String encodeType, Integer repeat) throws NoSuchAlgorithmException{
		Assert.hasText(source);
		Assert.hasText(encodeType);
		
		byte[] sourceByte = source.getBytes();
		byte[] saltByte = null == salt ? null : salt.getBytes();
		repeat = null == repeat ? DEFAULT_REPEAT : Math.max(repeat, DEFAULT_REPEAT);
		
		MessageDigest digest = MessageDigest.getInstance(encodeType);
		
		if(!ObjectUtils.isEmpty(saltByte)){
			digest.reset();
			digest.update(saltByte);
		}
		
		sourceByte = digest.digest(sourceByte);
		
		repeat = repeat - DEFAULT_REPEAT;
		for(int i=0; i<repeat; i++){
			digest.reset();
			sourceByte = digest.digest(sourceByte);
		}
		
		return encodeToString(sourceByte);
	}
	
	/**
	 * 
	 * desc: md5散列
	 * @param source
	 * @param salt
	 * @param repeat
	 * @return
	 */
	public static String md5Encode(String source, String salt, Integer repeat){
		try {
			return encode(source, salt, DEFAULT_ENCODE, repeat);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * desc: md5散列
	 * @param source
	 * @param salt
	 * @return
	 */
	public static String md5Encode(String source, String salt){
		return md5Encode(source, salt, null);
	}
	
	/**
	 * 
	 * desc: md5散列
	 * @param source
	 * @param repeat
	 * @return
	 */
	public static String md5Encode(String source, Integer repeat){
		return md5Encode(source, null, repeat);
	}
	
	/**
	 * 
	 * desc: md5散列
	 * @param source
	 * @return
	 */
	public static String md5Encode(String source){
		return md5Encode(source, null, null);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(encode("hhhhhh", "admin", "MD5", 2));
			System.out.println(encode("hhhhhh", "admin", "SHA", 2));
			System.out.println(encode("hhhhhh", "admin", "MD5", null));
			System.out.println(encode("hhhhhh", null, "MD5", 2));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		System.out.println(md5Encode("hhhhhh"));
		System.out.println(md5Encode("hhhhhh", 2));
		System.out.println(md5Encode("hhhhhh", "admin", 2));
		System.out.println(md5Encode("hhhhhh", "admin"));
		
		System.out.println(md5Encode("rememberMeCookie"));
	}
	
}
