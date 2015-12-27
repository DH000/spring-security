package com.lin.security.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.lin.security.type.user.SexType;

/**
 * 
 * desc: 用户
 * 
 * @author xuelin
 * @date Dec 17, 2015
 */
public class User implements Serializable {
	
	/**
	 * desc: TODO
	 */
	private static final long serialVersionUID = 6572065775761825729L;
	
	private Long id;
	private String username;
	private String password;
	private String salt;
	private String mobile;
	private String email;
	private SexType sexType;
	private Date birthdate;
	private Date registerTime;
	private Date lastLoginTime;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public SexType getSexType() {
		return sexType;
	}
	
	public void setSexType(SexType sexType) {
		this.sexType = sexType;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", mobile=" + mobile + ", email=" + email + ", sexType=" + sexType
				+ ", birthdate=" + birthdate + ", registerTime=" + registerTime + ", lastLoginTime=" + lastLoginTime + "]";
	}
	
}
