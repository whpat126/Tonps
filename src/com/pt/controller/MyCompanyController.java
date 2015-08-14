package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.pt.domain.MyCompany;

@Controller
public class MyCompanyController {

	// @Autowired
	// @Qualifier("messageService")
	// private MessageService ms;
	/**
	 * 检查用户状态：未加入企业、企业用户、企业管理员，将结果返回到jsp页面。
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
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
	
	/**
	 * 未加入企业的用户，加入企业的方法，
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/joinCompany", method = { RequestMethod.GET })
	private void joinCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		System.out.println("qqqqqqq");

	}
	
	/**
	 * 普通用户的我的企业功能
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/userCompany")
	private void userCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setName("山东高速");
		mc1.setCompanyAdmin("高谦");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setName("山东平通");
		mc2.setCompanyAdmin("宋琪");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	/**
	 * 管理员： 用户申请审核
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_userApply")
	private void adminCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请加入企业");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("王五");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为管理员");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	/**
	 * 管理员：企业申请审核
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_companyApply")
	private void admin_companyApply(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请成为上级");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("王五");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为下级");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 管理员：业务申请审核
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_business")
	private void admin_business(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请成为上级");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("王五");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为下级");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 管理员维护企业信息
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_companyInfo")
	private void admin_companyInfo(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请成为上级");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		mc1.setName("中国集团");
		list.add(mc1);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 管理员群组设置
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_group")
	private void admin_group(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		
		
		

	}
	
	/**
	 * 管理员用户信息维护
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_userInfo")
	private void admin_userInfo(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		
		
		

	}
	
	/**
	 * 管理员企业系统维护
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin_companySystem")
	private void admin_companySystem(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		
		
		

	}

}
