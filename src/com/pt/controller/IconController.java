package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pt.domain.PublicIcon;
import com.pt.domain.UserIcon;
import com.pt.service.CompanyIconService;
import com.pt.service.PublicIconService;
import com.pt.service.UserIconService;
import com.pt.service.UsersService;

/**
 * 负责图标的controller
 * @author sq
 */
@Controller
public class IconController {
	@Autowired
	@Qualifier("userIconService")
	private UserIconService userIconService;
	@Autowired
	@Qualifier("publicIconService")
	private PublicIconService publicIconService;
	@Autowired
	@Qualifier("companyIconService")
	private CompanyIconService companyIconService;
	@Autowired
	@Qualifier("userService")
	private UsersService userService;
	/**
	 * 图标初始化就执行的方法，该方法将数据库中存放的图标返回到页面中，
	 * author：songqi
	 * @param session 根据登录的用户查找对应的类
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping("/publicIconInit")
	private void Init(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		List<PublicIcon> list = publicIconService.findAll();
		out.print(gson.toJson(list));
			
	}
	/**
	 * 用户个人图标初始化就执行的方法，该方法将数据库中存放的图标返回到页面中，
	 * author：songqi
	 * @param session 根据登录的用户查找对应的类
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping("/userIconInit")
	private void userInit(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", userName).getPk_users();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		List<PublicIcon> list = userIconService.findAll(userId);
		if(null == list || list.size() < 1){
			return ;
		}else{
			
			out.print(gson.toJson(list));
		}
			
	}
	
	
	/**
	 * 将用户修改的图标顺序取到，并保存到数据库中
	 * author：songqi
	 * @param session
	 * @param response
	 * @param param_id
	 */
	@RequestMapping("/userIconUpdate")
	private void iconUpdate(HttpSession session, HttpServletResponse response, 
			String sortString){
		// 根据session得到用户的id
		String userName = (String) session.getAttribute("userName");
		if("".equals(userName) || null == userName){
			return;
		}else{
			// 将替换后的顺序保存到数据库中
			String userId = userService.findByProp("userName", userName).getPk_users();
			boolean flag = userIconService.update(userId,sortString);
			
			
		}
		
	}
	/***
	 * 添加新的图标到页面的最后
	 * author：songqi
	 * @param session
	 * @param response
	 * @param iconname
	 * @param iconaddress
	 */
	@RequestMapping("/AddIcon")
	private void iconAdd(HttpSession session, HttpServletResponse response, 
			String iconname, String iconaddress) throws IOException{
		// 根据session得到用户的id
//		通过用户ID将图标信息保存到数据库中
//		boolean flag = false;
		PrintWriter out = response.getWriter();
		out.print(true);
		
	}
	
	/**
	 * 通过图标的id，得到图标的内容
	 * author：songqi
	 * @param session
	 * @param response
	 * @param iconId
	 * @throws IOException
	 */
	@RequestMapping("/IconEdit")
	private void iconEdit(HttpSession session, HttpServletResponse response, 
			String iconId) throws IOException{
		// 根据session得到用户的id
		
//		boolean flag = false;
		Gson gson = new Gson();
		UserIcon icon1 = new UserIcon();
		icon1.setParam_id("1");
		icon1.setName("a");
		icon1.setParam_dragableBox("true");
		icon1.setParam_href("https://www.baidu.com");
		icon1.setParam_title("1");
		String str = gson.toJson(icon1);
		System.out.println(str);
		PrintWriter out = response.getWriter();
		out.print(str);
		
	}
	
	
	
	
	
	
	
}
