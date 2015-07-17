<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  	<link href="style/common/userCSS/index.css" rel="stylesheet" type="text/css"/>
  	
	
  </head>
  
  <body>
  <div class="header">
  	<div class="black_bar">
  		<div class="black_bar_left" >
	  		<ul class="fl">
	  			<li  class="userSet"><a href="index.jsp">加入收藏</a>
	  			</li>
	  			<li class="userSet"><a href="index.jsp">设为首页</a>
	  			</li>
	  			<li class="userSet"><a href="index.jsp">创建桌面快捷</a>
	  			</li>
	  		</ul>
  		</div>
  		<div class="black_bar_right" >
  			<ul class="fl" >
  				<li >您好，<span>xx</span>  <a href="javascript:;" title="退出系统">退出</a></li>
  			</ul>
  		</div>
  	</div>
  	<div class="mainNav">
  		<div class="logo"></div>
  		<div class="menu">
  			<ul>
  				<li><a href="#">网站首页</a></li>
  				<li><a href="#">我的社区</a></li>
  				<li><a href="#">帮助中心</a></li>
  			</ul>
  		</div>
  		<div class="loginAndReg">
  			<span>我的世界</span>
  		</div>
  	</div>
  </div>
  
  <div class="img_lunbo" style="background-image: url('style/css/userCSS/1.jpg');">
  	<div class="login_href">
  		<a href="#">登&nbsp;&nbsp;&nbsp;&nbsp;录</a>&nbsp;&nbsp;&nbsp;&nbsp;
  		<a href="#" style="background:rgba(0,0,0,0.2);">立即注册</a>
  	</div>
  </div>
  
  <div class="introduce">
  	<div class="slogan"> <span>连接无处不在，尽在平通互联</span></div>
  	<div class="app_main">
  		<span>主线</span>
  		<ul>
  			<li>平通云</li>
  			<li>爱常用</li>
  			<li>爱工作</li>
  			<li>爱生活</li>
			<li>爱娱乐</li>  			
  		</ul>
  	</div>
  </div>
  
  <div class="footer">
		<div class="footer-t">
			<div class="footer-inner wrap">
				<div class="footer-box">
					<ul>
						<li class="title">帮助中心</li>
						<li><a href="http://www.pinton.com.cn">新手指南</a></li>
						<li><a href="http://www.pinton.com.cn">主要功能</a></li>
						<li><a href="http://www.pinton.com.cn">常见问题</a></li>
					</ul>
				</div>
				<div class="footer-box">
					<ul>
						<li class="tetle">关于我们</li>
						<li><a href="http://www.pinton.com.cn">公司简介</a></li>
					</ul>
				</div>
				<div class="footer-box contact">
					<ul>
						<li class="title">产品咨询</li>
						<li class="telphone">0531-88974678</li>
						<li class="title">技术支持</li>
						<li class="telphone">0531-88974678</li>
						<li class="weixin">关注我们</li>
					</ul>
				</div>
			</div>
			<div class="footer-b">
				<div class="footer-inner wrap">
					<p>CopyRight © 2015-2020 www.pinton.com.cn,All Rights Reserved.
						版权所有 山东平通信息咨询有限公司</p>
					<p>
						增值电信业务经营许可证： 经营许可证编号： <a href="http://www.pinton.com.cn/"
							rel="nofollow" target="_blank"></a>
					</p>
				</div>
			</div>
		</div>

  </div>
 <%--  <div>
  ${error }
    <form action="userLogin.do">
    	username: <input type="text" name="username" /><br>
    	password: <input type="password" name="password" /><br>
    	<input type="submit" value="登录" />
    </form>
  </div> --%>
  
  
  
  </body>
</html>
