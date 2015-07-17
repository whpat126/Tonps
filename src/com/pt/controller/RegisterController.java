package com.pt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pt.domain.Users;
import com.pt.service.UserService;

@Controller
public class RegisterController{

	@Autowired
	@Qualifier("userService")
	private UserService us;
	
	/**
	 * 
	 * author：songqi
	 * @param user
	 * @return
	 */
	@RequestMapping("/userRegister")
	public ModelAndView userReg(Users user){
		ModelAndView mav = new ModelAndView();
		
		boolean str = us.save(user);
		
		if(str){
			mav.setViewName("index");
			mav.addObject("error", "用户已经存在");
		}else{
			mav.setViewName("/WEB-INF/jsps/user/index");
		}
		
		
		return mav;
		
	}
}
