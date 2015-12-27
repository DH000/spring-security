package com.lin.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * desc:   加盐
 * @author xuelin
 * @date   Dec 24, 2015
 */
public interface SaltPasswordEncoder extends PasswordEncoder {
	
	public boolean matches(CharSequence rawPassword, String encodedPassword, String saltSource);
	
}
