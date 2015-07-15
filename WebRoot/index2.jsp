<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  	
	
  </head>
  
  <body>
  <div style="background-image: url('style/css/userCSS/1.jpg');height: 1000px;">
  	<div class="login_href">
  		<a href="#">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">注册</a>
  	</div>
  </div>
  
  
  
  </body>
</html>
