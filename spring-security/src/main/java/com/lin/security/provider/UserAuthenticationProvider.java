package com.lin.security.provider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lin.security.password.SaltPasswordEncoder;
import com.lin.security.userdetails.UserDetail;

/**
 * 
 * desc: 认证器
 * 
 * @author xuelin
 * @date Dec 24, 2015
 */
public class UserAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	private SaltPasswordEncoder userPasswordEncoder;
	private UserDetailsService userDetailsService;
	
	public void setUserPasswordEncoder(SaltPasswordEncoder userPasswordEncoder) {
		this.userPasswordEncoder = userPasswordEncoder;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// 认证
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		
		UserDetail saltUser = (UserDetail) userDetails;
		String presentedPassword = authentication.getCredentials().toString();
		
		if (!userPasswordEncoder.matches(presentedPassword, userDetails.getPassword(), saltUser.getSalt())) {
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}
	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		UserDetails loadedUser;
		
		try {
			loadedUser = userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException notFound) {
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		
		return loadedUser;
	}
	
}
