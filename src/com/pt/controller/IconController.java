package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pt.domain.CompanyIcon;
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
	 */
	@RequestMapping("/publicIconInit")
	private void Init(HttpSession session, HttpServletResponse response)
			throws IOException {
		/*response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");*/
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		List<PublicIcon> list = publicIconService.findAll();
		out.print(gson.toJson(list));
			
	}
	/**
	 * 用户个人图标初始化就执行的方法，该方法将数据库中存放的图标返回到页面中，
	 * @author：songqi
	 * @param session 根据登录的用户查找对应的类
	 */
	@RequestMapping("/userIconInit")
	private void userInit(HttpSession session, HttpServletResponse response)
			throws IOException {
		/*response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");*/
		String userName = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", userName).getPk_users();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		List<UserIcon> list = userIconService.findAll(userId);
		if(null == list || list.size() < 1){
			return ;
		}else{
			out.print(gson.toJson(list));
		}
			
	}
	
	/**
	 * 将用户修改的图标顺序(包括删除)取到，并保存到数据库中 爱常用
	 * author：songqi
	 * @param param_id
	 */
	@RequestMapping("/userIconUpdate")
	private void userIconUpdate(HttpSession session, HttpServletResponse response, 
			String sortString) throws IOException{
		// 根据session得到用户的id
		String userName = (String) session.getAttribute("userName");
		if("".equals(userName) || null == userName){
			return;
		}else{
			// 将替换后的顺序保存到数据库中
			String userId = userService.findByProp("userName", userName).getPk_users();
			boolean flag = userIconService.update(userId,sortString);
			PrintWriter out = response.getWriter();
			if(flag){
				out.print(true);
			}else{
				out.print(false);
			}
		}
	}
	
	/**
	 * 通过图标的id，得到图标的内容
	 * @author：songqi
	 * @param iconId
	 */
	@RequestMapping("/findUserIconById")
	private void findUserIconById(HttpSession session, HttpServletResponse response, 
			String iconId) throws IOException{
		// 根据session得到用户的id
		Gson gson = new Gson();
		UserIcon icon = userIconService.findById(iconId);
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(icon));
	}
	
	/**
	 * 用户修改图标的名称和地址及logo，得到图标的内容
	 * @author：songqi
	 * @param iconId
	 */
	@RequestMapping("/userEditIcon")
	private void userEditIcon(HttpSession session, HttpServletResponse response, 
			UserIcon usericon) throws IOException{
		// 根据session得到用户的id
		Gson gson = new Gson();
		UserIcon icon = userIconService.findById(usericon.getPk_userIcon());
		icon.setName(usericon.getName());
		icon.setParam_href(usericon.getParam_href());
		//需要重写父类方法，因为涉及到两个数据库表
//		boolean flag = userIconService.update(icon, icon.getPk_userIcon());
//		PrintWriter out = response.getWriter();
//		out.print(flag);
		System.out.println("success");
	}
	
	/***
	 * 添加新的图标到页面的最后
	 * @author：songqi
	 * @param iconname
	 * @param iconaddress
	 */
	@RequestMapping("/userAddIcon")
	private void userAddIcon(HttpSession session, HttpServletResponse response, 
			UserIcon usericon) throws IOException{
		System.out.println("aaaa" + usericon.getParam_href());
		System.out.println("aaaa"+ usericon.getName());
	}
	
	/**
	 * 用户工作图标初始化就执行的方法，该方法将数据库中存放的图标返回到页面中，
	 * @author：songqi
	 */
	@RequestMapping("/workIconInit")
	private void workIconInit(HttpSession session, HttpServletResponse response, 
			String sortString) throws IOException{
		// 根据session得到用户的id
		String userName = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", userName).getPk_users();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		List<CompanyIcon> list = companyIconService.findAll(userId);
		if(null == list || list.size() < 1){
			return ;
		}else{
//			System.out.println(gson.toJson(list));
			out.print(gson.toJson(list));
		}
	}
	
	/**
	 * 将用户修改的图标顺序(包括删除)取到，并保存到数据库中 爱工作
	 * @author：songqi
	 * @param sortString
	 * @throws IOException
	 */
	@RequestMapping("/workIconUpdate")
	private void workIconUpdate(HttpSession session, HttpServletResponse response, 
			String sortString) throws IOException{
		// 根据session得到用户的id
		String userName = (String) session.getAttribute("userName");
		if("".equals(userName) || null == userName){
			return;
		}else{
			// 将替换后的顺序保存到数据库中
			String userId = userService.findByProp("userName", userName).getPk_users();
			boolean flag = companyIconService.update(userId,sortString);
			if(flag){
				System.out.println("数据返回成功！");
				return ;
			}else{
				PrintWriter out = response.getWriter();
				out.print(false);
			}
		}
	}
	
	/**
	 * 通过图标的id，得到图标的内容
	 * @author：songqi
	 * @param iconId
	 */
	@RequestMapping("/findCompanyIconById")
	private void findCompanyIconById(HttpSession session, HttpServletResponse response, 
			String iconId) throws IOException{
		// 根据session得到用户的id
		Gson gson = new Gson();
		UserIcon icon = userIconService.findById(iconId);
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(icon));
	}
	
	
/*	*//**
	 * 修改爱工作的图标的内容
	 * @author：songqi
	 * @param iconId
	 *//*
	@RequestMapping("/workIconEdit")
	private void workIconEdit(HttpSession session, HttpServletResponse response, 
			String iconId) throws IOException{
		// 根据session得到用户的id
//		boolean flag = false;
		Gson gson = new Gson();
		UserIcon icon1 = new UserIcon();
	}*/
	
	private static String a(){
		SimpleDateFormat format = new SimpleDateFormat("hhmmssss");
		String date = format.format(new Date());
		return UUID.randomUUID().toString();
	}
	private static String b(){
		SimpleDateFormat format = new SimpleDateFormat("hhmmssss");
		String date = format.format(new Date());
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(a());
		System.out.println(b());
	}
}
