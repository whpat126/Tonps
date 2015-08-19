package com.pt.domain;

import com.pt.base.BaseDomain;

/**
 * 有问题和回答
 * @author Administrator
 *
 */
public class Pwdqa extends BaseDomain{

	private String pk_pwdqa;
	private String pwdq;
	private String pwdqa;
	private String pk_users;
	
	public Pwdqa() {
		setPk_pwdqa(basedbo.genPk());
	}

	public String getPk_pwdqa() {
		return pk_pwdqa;
	}

	public void setPk_pwdqa(String pk_pwdqa) {
		this.pk_pwdqa = pk_pwdqa;
	}

	public String getPwdq() {
		return pwdq;
	}

	public void setPwdq(String pwdq) {
		this.pwdq = pwdq;
	}

	public String getPwdqa() {
		return pwdqa;
	}

	public void setPwdqa(String pwdqa) {
		this.pwdqa = pwdqa;
	}

	public String getPk_users() {
		return pk_users;
	}

	public void setPk_users(String pk_users) {
		this.pk_users = pk_users;
	}

	
}
