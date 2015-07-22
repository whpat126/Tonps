package com.pt.controller;

import java.io.IOException;

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

import com.pt.domain.Users;
import com.pt.service.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService us;

	/**
	 * 用户注册信息验证
	 * author：songqi
	 * @param userName
	 * @return
	 */
	@RequestMapping("/userValidate")
	private ModelAndView userValidate(String userName){
//		System.out.println("bbbbbbbbbbbbb");
//		System.out.println("----------->" + userName);
		boolean flag = us.userValidate(userName);
		return null;
	}
	/**
	 * 用户登录验证，交service判断用户名密码是否正确
	 * author：songqi
	 * @param user
	 * @param request
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/userLogin")
	private void userLogin(Users user, HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
		ModelAndView mav = new ModelAndView();
//		System.out.println("aaaaaaaaaaaaaaaa");
		boolean flag = us.userLogin(user);
		if (!flag) {
			mav.setViewName("index");
			mav.addObject("error", "用户名或密码不正确！");

//			return mav;
		} 
		else {
			System.out.println("111111111111111");
			HttpSession session = request.getSession();
//			session.setAttribute("userName", user.getUsername());
//			Cookie cookieName = new Cookie("user", user.getId() + "?????!!!!!" + user.getPassword());
			session.setAttribute("userName", "zhangsan");
			Cookie cookieName = new Cookie("user", "1" + "@@@@" + "zhangsanpwd");
			// 保留7天
			cookieName.setMaxAge(7*24*3600);
			resp.addCookie(cookieName);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			
			resp.sendRedirect("index2.jsp");
			
//			mav.setViewName("/index2");
//			return mav;
		}
	}

	/**
	 * 管理员登录系统
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminLogin")
	private ModelAndView adminLogin(Users user, HttpSession session) {

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
