package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.google.gson.Gson;
import com.pt.domain.Pwdq;
import com.pt.domain.Pwdqa;
import com.pt.domain.Users;
import com.pt.service.PwdqService;
import com.pt.service.PwdqaService;
import com.pt.service.UsersService;
import com.pt.utils.PtMail;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UsersService userService;
	@Autowired
	@Qualifier("pwdqService")
	private PwdqService pwdqService;
	@Autowired
	@Qualifier("pwdqaService")
	private PwdqaService pwdqaService;
	
	/**
	 * 用户注册信息验证 ，判断该用户是否存在
	 * 
	 * @author：songqi
	 * @param userName
	 * @return 用户存在 返回true 用户不存在 返回false
	 */
	@RequestMapping("/userValidate")
	private void userValidate(String userName, HttpServletResponse resp)
			throws IOException {
//		System.out.println("用户验证的方法-------->" + userName);
		boolean flag = userService.userValidate(userName);
		PrintWriter out = resp.getWriter();
//		flag = false;
		if (flag) {
			out.print(true);
		} else {
			out.print(false);
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
//		System.out.println("===》用户注册的方法" + reusername);
		boolean flag = userService.userValidate(reusername);
//		flag = false;
		if (flag) {
			out.print(true); // 用户已经存在
		} else {
			Users user = new Users();
			user.setUsername(reusername);
			user.setPassword(repassword);
			
			// 保存用户到数据库中
			 boolean bl = userService.save(user);
			 System.out.println(user.getUsername());
//			boolean bl = false;
//			bl = true;
			if (bl) { // true 表示注册成功
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
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		boolean flag = userService.userLogin(user);
		PrintWriter out = resp.getWriter();
		// flag =false;
		if (!flag) {
			out.print(false);
			// return mav;
		} else if ("true".equals(remeber)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", username); // 要修改
			Cookie cookieName = new Cookie("user", "1" + "@@@@" + username); // 要修改
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
			session.setAttribute("userName", username); // 要修改
			out.print(username);
		}
	}

	/**
	 * 用户在访问页面时，会使用自动登录功能,如果获得的session为null，则返回false 
	 * @author songqi
	 * 
	 * @param session
	 * @param request
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/autoLogin")
	private void autoLogin(HttpSession session, HttpServletRequest request,
			HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("autoLogin的session" + session.getAttribute("userName"));
		String userName = (String) session.getAttribute("userName");
		PrintWriter out = resp.getWriter();
		if (userName == null || "".equals(userName)) {
			out.print(false);
		} else
			out.print(userName);
	}

	/**
	 * 用户使用qq登录系统 author：songqi
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = "/qqLogin", method = { RequestMethod.GET,
			RequestMethod.POST })
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
	 * 用户点击修改密码后，页面通过controller跳转到jsp中 
	 * author：songqi
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/modifyPwd")
	private void modifyPwd(HttpServletResponse response, HttpSession session,
			String opwd, String pwd) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Users user = userService.findByProp("username", username);
		if (!opwd.equals(user.getPassword())) { // 密码不正确 flag=false
			out.print(false);
		} else {
			user.setPassword(pwd);
			boolean flag = userService.update(user, user.getPk_users()); // 传入新密码
			if (flag) {
				out.print("success");
			} else {
				out.print("fail");
			}
		}

	}

	/**
	 * 验证邮箱，给指定邮箱发送邮件 author：songqi
	 * 
	 * @param response
	 * @param session
	 * @param opwd
	 * @throws IOException
	 */
	@RequestMapping("/validateEmail")
	private void validateEmail(HttpServletResponse response,
			HttpSession session, String email) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Users user = userService.findByProp("username", username);
		user.setEmail(email);
		boolean flag = PtMail.sendAccountActivateEmail(user); // 调用发送邮件的方法
		if (flag){
			userService.update(user,user.getPk_users());
			out.print(true);
		}
		else
			out.print(false);
	}

	/**
	 * 查询邮箱是否已经验证，如果已经验证则返回到jsp，如果没有验证则让用户输入进行验证 author：songqi
	 * 
	 * @param response
	 * @param session
	 * @param opwd
	 * @throws IOException
	 */
	@RequestMapping("/queryEmail")
	private void queryEmail(HttpServletResponse response, HttpSession session,
			String opwd) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String str = userService.findByProp("username", username).getEmail(); // 获取邮箱的方法
		if ("".equals(str) || null == str)
			out.print(false);
		else
			out.print(str);
	}

	/**
	 * 取得库里存放的所有问题 author：songqi
	 * 
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("/QuestionList")
	private void QuestionList(HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<Pwdq> list = pwdqService.findAll();
		Gson gson = new Gson();
		String str = gson.toJson(list);
		out.print(str);
	}

	/**
	 * 判断用户是否设置过密保问题。 author：songqi
	 * 
	 * @param response
	 * @param session
	 * @param opwd
	 * @throws IOException
	 */
	@RequestMapping("/judgeUserQuestion")
	private void judgeUserQuestion(HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Users user = userService.findByProp("username", username);
		Gson gson = new Gson();
		Pwdqa pwdqa = pwdqaService.findByProp("pk_users", user.getPk_users()); //去数据库中判断该用户是否设置过密保问题，如果为null表示没有设置过
		if (null == pwdqa || pwdqa.equals("")) { 
			List<Pwdq> list = pwdqService.findAll();  // 获取数据库中存放的密码问题
			String str = gson.toJson(list);
			// System.out.println(str);
			out.print(str);
		} else {
			Pwdq pwdq = pwdqService.findById(pwdqa.getPwdq());
			String str = gson.toJson(pwdq);
//			System.out.println(str);
			out.print(str);
		}
	}

	/**
	 * 验证用户输入的老密码是否正确，如果正确则把新的密保问题及答案存储到数据库中，如果不正确则返回false给jsp。
	 * author：songqi
	 * @param response
	 * @param session
	 * @param opwdq 旧密码问题
	 * @param opwda 旧密码答案
	 * @param npwdq 新密码问题
	 * @param npwda 新密码答案
	 * @throws IOException
	 */
	@RequestMapping("/validatePwdQuestion")
	private void validatePwdQuestion(HttpServletResponse response,
			HttpSession session, String opwdq, String opwda, String npwdq,
			String npwda) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Users user = userService.findByProp("username", username);
		String sqlwhere = " pwdq= '" + opwdq + "' and pwdqa='"+opwda+"' and pk_users='"+user.getPk_users()+"' ";
		int num = pwdqaService.getTotal(sqlwhere);
		if (num == 0) {
			out.print(false);  // 如果不正确返回false
		} else {
			 // 如果旧密码问题和答案都一致，则将其修改的新密保及问题存储到数据库中
			Pwdqa pwdqa = pwdqaService.findByProp("pk_users", user.getPk_users());
			pwdqa.setPk_users(user.getPk_users());
			pwdqa.setPwdq(npwdq);
			pwdqa.setPwdqa(npwda);
			boolean blean = pwdqaService.update(pwdqa, pwdqa.getPk_pwdqa());
			if(blean)
				out.print("success");
			else
				out.print("fail");
		}
	}

	/**
	 * 添加密保
	 * author：songqi
	 * @param response
	 * @param session
	 * @param nopwdQ
	 * @param nopwdA
	 * @throws IOException
	 */
	@RequestMapping("/addSecurity")
	private void addSecurity(HttpServletResponse response,
			HttpSession session, String nopwdQ, String nopwdA) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //
		// 需要得到主键的方法
		Users user = userService.findByProp("username", username);
		// 把内容存储到数据库中方法
		Pwdqa pwdqa = new Pwdqa();
		pwdqa.setPwdq(nopwdQ);
		pwdqa.setPwdqa(nopwdA);
		pwdqa.setPk_users(user.getPk_users());
		boolean flag = pwdqaService.save(pwdqa);
		out.print(flag);
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
		boolean flag = userService.adminLogin(user);
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
