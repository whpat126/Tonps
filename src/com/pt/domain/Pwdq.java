package com.pt.domain;

import com.pt.base.BaseDomain;

/**
 * 密码问题库
 * @author Administrator
 *
 */
public class Pwdq extends BaseDomain{

	private String pk_pwdq;
	private String pwdq;
	
	public Pwdq() {
		setPk_pwdq(basedbo.genPk());
	}
	public String getPk_pwdq() {
		return pk_pwdq;
	}
	public void setPk_pwdq(String pk_pwdq) {
		this.pk_pwdq = pk_pwdq;
	}
	public String getPwdq() {
		return pwdq;
	}
	public void setPwdq(String pwdq) {
		this.pwdq = pwdq;
	}
	
}
