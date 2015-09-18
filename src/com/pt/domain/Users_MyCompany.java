package com.pt.domain;

import com.pt.base.BaseDomain;

public class Users_MyCompany extends BaseDomain{

	private String pk_users_mycompany;
	private String pk_users;
	private String pk_mycompany;
	private String state;
	private String apply;

	private String apply_company;
	private String apply_pk_mycompany;
	private String applyName;

	private String username;
	private String name;
	
	public Users_MyCompany() {
		setPk_users_mycompany(basedbo.genPk());
	}


	public String getPk_users_mycompany() {
		return pk_users_mycompany;
	}


	public void setPk_users_mycompany(String pk_users_mycompany) {
		this.pk_users_mycompany = pk_users_mycompany;
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


	public String getApply() {
		return apply;
	}


	public void setApply(String apply) {
		this.apply = apply;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getApply_company() {
		return apply_company;
	}


	public void setApply_company(String apply_company) {
		this.apply_company = apply_company;
	}


	public String getApply_pk_mycompany() {
		return apply_pk_mycompany;
	}


	public void setApply_pk_mycompany(String apply_pk_mycompany) {
		this.apply_pk_mycompany = apply_pk_mycompany;
	}


	public String getApplyName() {
		return applyName;
	}


	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
}
