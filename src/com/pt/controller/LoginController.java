package com.pt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pt.domain.Users;
import com.pt.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
//	@Qualifier("abcdService")
	private UserService us;
	
	@RequestMapping("/userLogin")
	private ModelAndView userLogin(Users user,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String str = us.userValidate(user);
		if(str.equals("false")){
			mav.setViewName("index");
			mav.addObject("error", "用户名或密码不正确！");
			return mav;
		}else{
			session.setAttribute("username", str);
			mav.setViewName("/WEB-INF/jsps/user/index");
			return mav;
		}
	}
	
	/**
	 * 管理员登录系统
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminLogin")
	private ModelAndView adminLogin(Users user,HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		boolean flag = us.adminValidate(user);
		if(flag){
			session.setAttribute("username", user.getUsername());
			mav.setViewName("/WEB-INF/jsps/index");
			return mav;
		}else{
			mav.setViewName("admin");
			mav.addObject("error", "用户名或密码不正确！");
			return mav;
		}
	}
	
	
}
