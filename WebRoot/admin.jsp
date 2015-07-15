<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>amdin.jsp</title>
   

  </head>
  
  <body>
  <span style="color: red;">${error }</span>
    <form action="adminLogin.do" method="post">
    	管理员：<input type="text" id="username" name="username" /><br>
    	密码：<input type="password" id="password" name="password" /><br>
    	<input type="submit" value="登录" /> 
    
    </form>
    
    
  </body>
</html>
