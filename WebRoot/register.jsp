<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>平通云应用用户注册</title>
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
   	<script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath }/style/common/userjs/register.js"></script> 
	<script>
		$(function () { 
			$('#myModal').modal({backdrop:"static", keyboard:false, show:false});
		});
	</script>

  </head>
  
  <body>
  
  <jsp:include page="black_on_top.jsp"></jsp:include>
  
  <!-- 点击后调用下面的model，登录 -->
  <div id="refLogin">已有账号，直接<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">登录</button></div>
  
  <!-- 用户注册页面，该页面必须存在，因为登录就是一个弹窗了 -->
<div>
  	<!-- 注册 -->
  	<div>
	  	${msg }
	    <span id="remsg"></span>
	    <form id="form1">
	    	手机/邮箱:<input type="text" name="reusername" id="reusername" /> <a href="javascript:;" id="validate" title="检测用户是否存在">图标</a><br/>
	    	密码:<input type="password" name="password" id="repassword" /><br/>
	    	确认密码:<input type="password" name="repassword" id="rerepassword" /><br/>
	    	<input type="checkbox" name="agree" id="agree" /><span>阅读并接受<a href="statement.html">《平通用户协议》</a></span><br/>
	    	<input type="button" id="register" class="btn " value="注册" />
	    </form>
	    <!-- 社交工具登录 -->
		<div>
	  	
		</div>
    </div>
    <!-- 登录的model -->
   	<%@ include file="login.jsp"  %>
    
</div>
	
  </body>
</html>
