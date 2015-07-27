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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pt.domain.User;
import com.pt.service.UserService;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService us;

	/**
	 * 用户注册信息验证 ，判断该用户是否存在
	 * 
	 * @author：songqi
	 * @param userName
	 * @return
	 */
	@RequestMapping("/userValidate")
	private void userValidate(String userName, HttpServletResponse resp)
			throws IOException {
		// System.out.println("bbbbbbbbbbbbb");
		System.out.println("用户验证的方法-------->" + userName);
		// flag 为 true 表示用户已经存在，false表示不存在，默认是true
		boolean flag = us.userValidate(userName);
		PrintWriter out = resp.getWriter();
		flag = false;
		if (flag) {
			out.print(false);
		} else {
			out.print(true);
		}

	}

	/**
	 * 用户注册的类，需要先判断用户是否存在，然后再判断是否添加成功
	 * 
	 * @author：songqi
	 * @return
	 */
	@RequestMapping("/userRegister")
	private void userRegister(String reusername, String repassword,
			HttpServletResponse resp, HttpSession session) throws IOException {
		PrintWriter out = resp.getWriter();
		System.out.println("===》用户注册的方法" + reusername);
		// 验证用户名是否存在 flag 为 true 表示用户已经存在，false表示不存在，默认是true
		boolean flag = us.userValidate(reusername);
		flag = false;
		if (flag) {
			out.print(true);
		} else {
			User user = new User();
			user.setUsername(reusername);
			user.setPassword(repassword);
			// 保存用户到数据库中
			// boolean bl = us.save(user);
			// System.out.println("userRegister方法中判断basedao返回的值：" + bl);
			boolean bl = false;
			bl = true;
			if (bl) {
				session.setAttribute("userName", reusername);
				out.print("success");
			} else {
				out.print(false);
			}

		}
	}

	/**
	 * 用户登录验证，交service判断用户名密码是否正确
	 * 
	 * @author：songqi
	 * @param user
	 * @param request
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/userLogin")
	private void userLogin(String username, String password, String remeber,
			HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		// System.out.println("userLogin......");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean flag = us.userLogin(user);
		PrintWriter out = resp.getWriter();
		// flag =false;
		if (!flag) {
			out.print(false);
			// return mav;
		} else if ("true".equals(remeber)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", "zhangsan"); // 要修改
			Cookie cookieName = new Cookie("user", "1" + "@@@@" + "zhangsanpwd"); // 要修改
			// 保留7天
			cookieName.setMaxAge(7 * 24 * 3600);
			resp.addCookie(cookieName);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			out.print(username);
			// mav.setViewName("/index2");
			// return mav;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("userName", "zhangsan"); // 要修改
			out.print(username);
		}
	}

	/**
	 * 用户在访问页面时，会使用自动登录功能
	 * author：songqi
	 * @param session
	 * @param request
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/autoLogin")
	private void autoLogin(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("autoLogin的session"
				+ session.getAttribute("userName"));
		String userName = (String) session.getAttribute("userName");
		PrintWriter out = resp.getWriter();
		if (userName == null || "".equals(userName)) {
			out.print(false);
		} else
			out.print(userName);
	}

	/**
	 * 用户使用qq登录系统
	 * author：songqi
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = "/qqLogin", method = {RequestMethod.GET,RequestMethod.POST})
	private void qqLogin(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
            System.out.println("进入qqLogin方法");
        } catch (QQConnectException e) {
            e.printStackTrace();
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
