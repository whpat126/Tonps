package com.pt.domain;

import com.pt.base.BaseDomain;

public class Database extends BaseDomain{

	private String pk_db;
	private String dbname;
	private String dbtype;
	private String dbaddress;
	private String dbuser;
	private String dbpassword;
	
	/**
	* <p>Title: 无参构造方法,用于生成主键</p>
	* <p>Description: </p>
	*/ 
	public Database() {
		setPk_db(basedbo.genPk());;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getDbaddress() {
		return dbaddress;
	}
	public void setDbaddress(String dbaddress) {
		this.dbaddress = dbaddress;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDbpassword() {
		return dbpassword;
	}
	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	public String getPk_db() {
		return pk_db;
	}
	public void setPk_db(String pk_db) {
		this.pk_db = pk_db;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
}
