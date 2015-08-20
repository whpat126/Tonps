package com.pt.domain;

import com.pt.base.BaseDomain;

/**
 * 
 * @author songqi
 *
 */
public class Message extends BaseDomain{

	private String pk_message;
	private String title;
	private String contents;
	private String msg_href;
	private String type; // 消息类型普通、实时、待办等
	private String source; // 消息来源 如系统推送和手工发送
	private String pk_user; // 
	private String state; // 消息状态 已读 未读
	private String dr; // 删除标志
	
	public Message() {
		setPk_message(basedbo.genPk());
	}
	
	public String getPk_message() {
		return pk_message;
	}
	public void setPk_message(String pk_message) {
		this.pk_message = pk_message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public String getMsg_href() {
		return msg_href;
	}
	public void setMsg_href(String msg_href) {
		this.msg_href = msg_href;
	}
	
}
