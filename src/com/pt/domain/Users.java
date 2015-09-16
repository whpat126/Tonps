package com.pt.domain;

import com.pt.base.BaseDomain;

public class Users extends BaseDomain{

	private String pk_users;
	private String username;
	private String password;
	private String email;
	private String phone;
//	private String name;
	private String demo;
	private String state; // 用户在企业中的状态 0不是企业用户 1普通企业用户 2企业管理员 
	
	private String pk_mycompany;
	
	public Users() {
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
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
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
	public String getPk_mycompany() {
		return pk_mycompany;
	}
	public void setPk_mycompany(String pk_mycompany) {
		this.pk_mycompany = pk_mycompany;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
