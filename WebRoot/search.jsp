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
	<div>
		<!-- 本站结果 -->
		<div></div>
		<!-- 百度结果 -->
		<div></div>
	</div>
  
  </body>
</html>
