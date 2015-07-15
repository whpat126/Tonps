package com.pt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//��ȡ����url
		String url = request.getRequestURI();
		//�ж�url�Ƿ��ǹ�����ַ,
		if(url.indexOf("Login.do") >= 0){
			return true;
		}
		// �ж�session
		HttpSession session = request.getSession();
		// ��session��ȡ���û������Ϣ
		String username = (String) session.getAttribute("username");
		if(username != null){
			return true;
		}
		// �����ʾ�û������Ҫ��֤����ת��¼ҳ��
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return false;
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
