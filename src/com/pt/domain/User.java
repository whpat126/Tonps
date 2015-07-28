package com.pt.domain;

import com.pt.base.BaseDomain;

public class User extends BaseDomain{

	private String pk_users;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String name;
	private String demo;
	
	public User() {
		setPk_users(basedbo.genPk());
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getPk_users() {
		return pk_users;
	}
	public void setPk_users(String pk_users) {
		this.pk_users = pk_users;
	}
	
}
