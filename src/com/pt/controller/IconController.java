package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javassist.compiler.ast.InstanceOfExpr;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pt.domain.Icon;
import com.pt.service.IconService;

/**
 * 负责图标的controller
 * @author sq
 */
@Controller
public class IconController {
//	@Autowired
//	@Qualifier("IconService")
//	private IconService iconService;

	/**
	 * 图标初始化就执行的方法，该方法将数据库中存放的图标返回到页面中，
	 * author：songqi
	 * @param session 根据登录的用户查找对应的类
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping("/IconInit")
	private void Init(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Icon icon1 = new Icon();
		icon1.setParam_id("1");
		icon1.setName("a");
		icon1.setParam_dragableBox("true");
		icon1.setParam_href("https://www.baidu.com");
		icon1.setParam_title("1");
		icon1.setIcon_path("style/cust/01.png");
		Icon icon2 = new Icon();
		icon2.setParam_id("2");
		icon2.setName("a");
		icon2.setParam_dragableBox("true");
		icon2.setParam_href("http://www.sina.com.cn");
		icon2.setParam_title("2");
		icon2.setIcon_path("style/cust/02.png");

		Icon icon3 = new Icon();
		icon3.setParam_id("3");
		icon3.setName("3");
		icon3.setParam_dragableBox("true");
		icon3.setParam_href("http://www.163.com");
		icon3.setParam_title("3");
		icon3.setIcon_path("style/cust/03.png");

		Icon icon4 = new Icon();
		icon4.setParam_id("4");
		icon4.setName("4");
		icon4.setParam_dragableBox("true");
		icon4.setParam_href("http://www.126.com");
		icon4.setParam_title("4");
		icon4.setIcon_path("style/cust/04.png");

		Icon icon5 = new Icon();
		icon5.setParam_id("5");
		icon5.setName("5");
		icon5.setParam_dragableBox("true");
		icon5.setParam_href("http://www.taobao.com");
		icon5.setParam_title("5");
		icon5.setIcon_path("style/cust/05.png");

		Icon icon6 = new Icon();
		icon6.setParam_id("6");
		icon6.setName("6");
		icon6.setParam_dragableBox("true");
		icon6.setParam_href("http://www.tmall.com");
		icon6.setParam_title("6");
		icon6.setIcon_path("style/cust/06.png");

		Icon icon7 = new Icon();
		icon7.setParam_id("7");
		icon7.setName("7");
		icon7.setParam_dragableBox("true");
		icon7.setParam_href("http://www.jd.com");
		icon7.setParam_title("7");
		icon7.setIcon_path("style/cust/07.png");

		Gson gson = new Gson();
		List<Icon> list = new ArrayList<Icon>();
		list.add(icon1);
		list.add(icon2);
		list.add(icon3);
		list.add(icon4);
		list.add(icon5);
		list.add(icon6);
		list.add(icon7);
		
		String str = gson.toJson(list);
//		System.out.println(str);
		PrintWriter out = response.getWriter();
		out.print(str);
	}
	
	/**
	 * 将用户修改的图标顺序取到，并保存到数据库中
	 * author：songqi
	 * @param session
	 * @param response
	 * @param param_id
	 */
	@RequestMapping("/IconUpdate")
	private void iconUpdate(HttpSession session, HttpServletResponse response, 
			String sortString){
		// 根据session得到用户的id
//		
		// 将替换后的顺序保存到数据库中
		System.out.println("新的顺序："+sortString);
		
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
		Icon icon1 = new Icon();
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
