package com.pt.domain;

import com.pt.base.BaseDomain;

public class MyCompany extends BaseDomain{

	private String pk_myCompany;
	private String name;
	private String companyAdmin;
	private String outCompany;
	private String phone;
	
	/* 临时增加  用于jsp页面显示全 */
	private String userName;
	private String address;
	private String user_company;
	private String user_phone;
	private String type; // 0表示
	
	public MyCompany() {
		setPk_myCompany(basedbo.genPk());
	}
	
	public String getPk_myCompany() {
		return pk_myCompany;
	}
	public void setPk_myCompany(String pk_myCompany) {
		this.pk_myCompany = pk_myCompany;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyAdmin() {
		return companyAdmin;
	}
	public void setCompanyAdmin(String companyAdmin) {
		this.companyAdmin = companyAdmin;
	}
	public String getOutCompany() {
		return outCompany;
	}
	public void setOutCompany(String outCompany) {
		this.outCompany = outCompany;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUser_company() {
		return user_company;
	}
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
