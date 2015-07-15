package com.pt.domain;

public class Database {

	private Long id;
	private String dbname;
	private String dbtype;
	private String dbaddress;
	private String dbuser;
	private String dbpassword;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
}
