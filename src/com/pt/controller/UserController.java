package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
	 * 用户在访问页面时，会使用自动登录功能,如果获得的session为null，则返回false author：songqi
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
	 * 用户点击修改密码后，页面通过controller跳转到web-inf下的jsp中 author：songqi
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/modifyPwd")
	private void modifyPwd(HttpServletResponse response, HttpSession session,
			String opwd, String pwd, String newpwd) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = us. //调用service的验证方法参数：username,opwd
		boolean flag = false;
		flag = true;
		if (!flag) { // 密码不正确 flag=false
			out.print(false);
		} else {
			// flag = us.u // 传入新密码
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
			HttpSession session, String opwd) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = // 调用发送邮件的方法
		boolean flag = false;
		flag = true;
		if (flag)
			out.print(true);
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
	@RequestMapping("queryEmail")
	private void queryEmail(HttpServletResponse response, HttpSession session,
			String opwd) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// String str = // 获取邮箱的方法
		String str = "abc.com";
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
	@RequestMapping("QuestionList")
	private void QuestionList(HttpServletResponse response, HttpSession session)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //去数据库中判断该用户是否设置过密保问题fals表示没有验证
		List<Pwdq> list = new ArrayList<Pwdq>();
		Pwdq question = new Pwdq();
		question.setPk_pwdq("1");
		question.setPwdq("你最爱的人是谁？");
		list.add(question);
		Pwdq question2 = new Pwdq();
		question2.setPk_pwdq("2");
		question2.setPwdq("你的名字是什么？");
		list.add(question2);
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
	@RequestMapping("judgeUserQuestion")
	private void judgeUserQuestion(HttpServletResponse response,
			HttpSession session) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //去数据库中判断该用户是否设置过密保问题fals表示没有验证
		List<Pwdq> list = new ArrayList<Pwdq>();
		Pwdq question = new Pwdq();
		question.setPk_pwdq("1");
		question.setPwdq("你最爱的人是谁？");
		list.add(question);
		Pwdq question2 = new Pwdq();
		question2.setPk_pwdq("2");
		question2.setPwdq("你的名字是什么？");
		list.add(question2);
		Gson gson = new Gson();
		boolean flag = false;
		flag = true;
		if (!flag) {
			// String str = // 获取数据库中存放的密码问题
			String str = gson.toJson(list);
			// System.out.println(str);
			out.print(str);
		} else {
			// String str = "" // 去数据库中交互得到用户设置的内容
			String str = gson.toJson(question2);
			System.out.println(str);
			out.print(str);
		}
	}

	/**
	 * 验证用户输入的老密码是否正确，如果正确则把新的密保问题及答案存储到数据库中，如果不正确则返回false给jsp。 author：songqi
	 * 
	 * @param response
	 * @param session
	 * @param opwd
	 * @throws IOException
	 */
	@RequestMapping("/validatePwdQuestion")
	private void validatePwdQuestion(HttpServletResponse response,
			HttpSession session, String opwdq, String opwda, String npwdq,
			String npwda) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //去数据库中判断旧密码问题和答案是否正确
		boolean flag = false;
		flag = true;
		if (!flag) {
			out.print(false);  // 如果不正确返回false
		} else {
			// String str = "" // 如果旧密码问题和答案都一致，则将其修改的新密保及问题存储到数据库中
			boolean blean = false;
			blean = true;
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
		// 把内容存储到数据库中方法
		
		boolean flag = true;
//		flag = false;
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
