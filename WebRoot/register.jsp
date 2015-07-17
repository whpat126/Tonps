<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    

  </head>
  
  <body>
    <form action="userRegister.do" method="post">
    	username:<input type="text" name="username" /><br/>
    	password:<input type="password" name="password" /><br/>
    	<input type="submit" value="提交" />
    </form>
  </body>
</html>
