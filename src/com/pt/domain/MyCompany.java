package com.pt.domain;

public class MyCompany {

	private String pk_myCompany;
	private String name;
	private String companyAdmin;
	private String outCompany;
	
	/* 临时增加  用于jsp页面显示全 */
	private String userName;
	private String user_company;
	private String user_phone;
	private String type;
	
	
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
	
}
