package com.pt.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pt.domain.User;
import com.pt.service.UserService;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	/**
	 * 业务处理请求之前被调用 
	 * author:宋琪
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/*
		 * 自动登录实现
		 * 1. 获取用户的session中的AuthenToken
		 * 		存在：不执行任何操作
		 * 不存在：
		 * 2. 获取cookie中的用户ID，存在：获取该用户的详细信息，保存到session中
		 * 不存在：
		 * 3. 获取当前访问url
		 * 4. 获取web.xml中放行的地址
		 * 5. 如果访问的url不是放行的地址，跳转到登录页面
		 */
		
		HttpSession session = request.getSession();
		String userName = (String)session.getAttribute("userName");
//		System.out.println("=========>loginInterceptor   "+ userName);
		if(null == userName || "".equals(userName)){ // session 不存在
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0 ){
				String usernameAndPwd = "";
				for(Cookie cookie : cookies){
					
					if("user".equals(cookie.getName())){
//						System.out.println("1233211234567");
//						String userId = cookie.getValue();
//						Users user2 = baseUserService.findById(Long.parseLong(userId));
						usernameAndPwd = cookie.getValue();
						String[] str = usernameAndPwd.split("@@@@");
					 	System.out.println("loginInterceptor的for循环中" + str[0] +  " :  " + str[1]);
//						Users user = userService.findByProp(str[0], str[1]);
//						Users user = new Users();
//						if(user != null){
							session.setAttribute("userName", str[1]);
							return true;
//						}
					}
				}
			}
			
			//获取请求url
			String url = request.getRequestURI();
			if(url.indexOf("userLogin.do") >= 0 || url.indexOf("userValidate.do") >=0 ){
				System.out.println("进入url判断---------------");
				return true;
			}
			
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
//			return false;
		}
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
