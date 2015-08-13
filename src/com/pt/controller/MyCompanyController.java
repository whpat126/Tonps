package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyCompanyController {

	// @Autowired
	// @Qualifier("messageService")
	// private MessageService ms;

	@RequestMapping("/checkUser")
	private void checkUser(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //
		// 需要得到主键的方法
		
		// 根据主键去数据库中查询用户是：未加入企业用户、企业用户、企业管理员、
		
		//写死：未加入企业：两种方式，1 去数据库中查两次，即判断有没有加入企业和是否是管理员  2 去库查一次，返回三种内容String或int
		boolean flag = true;
		
		out.print(flag);

	}
	
	@RequestMapping(value = "/joinCompany", method = { RequestMethod.GET })
	private void joinCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		System.out.println("qqqqqqq");

	}
	
	

}
