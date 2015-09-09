package com.pt.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	/**
	 * 管理员没有登录页面
	 * @author：songqi
	 * @return
	 */
	@RequestMapping("/admin")
	private String admin(){
		return "WEB-INF/admin/index";
	}
	
	@RequestMapping("/admin/adminLogin")
	private String adminLogin(){
		
		return "WEB-INF/admin/";
	}
	public static void main(String[] args) {
		
	}
}
