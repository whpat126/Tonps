package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pt.domain.User;
import com.pt.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService us;

	/**
	 * 用户注册信息验证 author：songqi
	 * 
	 * @param userName
	 * @return
	 */
	@RequestMapping("/userValidate")
	private ModelAndView userValidate(String userName) {
		// System.out.println("bbbbbbbbbbbbb");
		// System.out.println("----------->" + userName);
		boolean flag = us.userValidate(userName);
		return null;
	}

	/**
	 * 用户登录验证，交service判断用户名密码是否正确 
	 * @author：songqi
	 * @param user
	 * @param request
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/userLogin")
	private void userLogin(String username,String password,String remeber, HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("userLogin......");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean flag = us.userLogin(user);
		PrintWriter out = resp.getWriter();
//		flag =false;
		if (!flag) {
			out.print(false);
			// return mav;
		} else if("true".equals(remeber)){
			HttpSession session = request.getSession();
			session.setAttribute("userName", "zhangsan"); //要修改
			Cookie cookieName = new Cookie("user", "1" + "@@@@" + "zhangsanpwd"); // 要修改
			// 保留7天
			cookieName.setMaxAge(7 * 24 * 3600);
			resp.addCookie(cookieName);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			out.print(username);
			// mav.setViewName("/index2");
			// return mav;
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("userName", "zhangsan"); //要修改
			out.print(username);
		}
	}

	@RequestMapping("/autoLogin")
	private void autoLogin(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("autoLogin的session" + session.getAttribute("userName"));
		String userName = (String) session.getAttribute("userName");
		PrintWriter out = resp.getWriter();
		if(userName == null || "".equals(userName)){
			out.print(false);
		}else
			out.print(userName);
	}

	/**
	 * 管理员登录系统
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminLogin")
	private ModelAndView adminLogin(User user, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		boolean flag = us.adminLogin(user);
		if (flag) {
			session.setAttribute("username", user.getUsername());
			mav.setViewName("/WEB-INF/jsps/index");
			return mav;
		} else {
			mav.setViewName("admin");
			mav.addObject("error", "用户名或密码不正确！");
			return mav;
		}
	}

}
