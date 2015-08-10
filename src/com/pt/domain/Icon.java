package com.pt.domain;

public class Icon {

	private String pk_icon; // 图标的主键
	private String name; // icon name
	private String param_id; // icon id param
	private String param_title; 
	private String param_href;
	private String param_dragableBox; //icon dragableBox param,use drag
	private String icon_path;
	
	public String getPk_icon() {
		return pk_icon;
	}
	public void setPk_icon(String pk_icon) {
		this.pk_icon = pk_icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParam_id() {
		return param_id;
	}
	public void setParam_id(String param_id) {
		this.param_id = param_id;
	}
	public String getParam_title() {
		return param_title;
	}
	public void setParam_title(String param_title) {
		this.param_title = param_title;
	}
	public String getParam_href() {
		return param_href;
	}
	public void setParam_href(String param_href) {
		this.param_href = param_href;
	}
	public String getParam_dragableBox() {
		return param_dragableBox;
	}
	public void setParam_dragableBox(String param_dragableBox) {
		this.param_dragableBox = param_dragableBox;
	}
	public String getIcon_path() {
		return icon_path;
	}
	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}
	
}
