package com.pt.domain;

import com.pt.base.BaseDomain;

public class MyCompany extends BaseDomain{

	private String pk_myCompany;
	private String name;
	private String companyAdmin;//企业管理员
	private String phone;// 企业联系方式
	private String zzjgdmz; // 组织机构代码证
	private String pk_father;
	private String companyReg; // 企业注册码
	private String state; // 0 说明是未加入企业 1说明是企业用户  2说明是管理员 
	private String apply; //0 用户申请加入企业  1普通用户申请成为管理员
	
	/* 临时增加  用于jsp页面显示全 */
	private String userName; // 申诉人姓名 
	private String address; // 企业地址
	private String user_company; // 
	private String user_phone; // 企业联系电话
	
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZzjgdmz() {
		return zzjgdmz;
	}

	public void setZzjgdmz(String zzjgdmz) {
		this.zzjgdmz = zzjgdmz;
	}

	public String getCompanyReg() {
		return companyReg;
	}

	public void setCompanyReg(String companyReg) {
		this.companyReg = companyReg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPk_father() {
		return pk_father;
	}

	public void setPk_father(String pk_father) {
		this.pk_father = pk_father;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	
	
	
}
