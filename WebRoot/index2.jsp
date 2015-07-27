<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta property="qc:admins" content="1220550751606061704214471645026230" />
	<link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
	
	<script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
   	<script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script> 
   	<script type="text/javascript" src="style/common/userjs/index2.js"></script>
   	
   	
  </head>
  <body>
  
	<div>
		<!-- 最上方黑条  style="display:none;" -->
		<div><%@ include file="black_on_top.jsp" %></div>
		
		
		<!-- 单位logo -->
		<div id="logo" class="logo"></div>
		
		<!-- 导航栏 -->
		<div id="menuBar" class="menuBar">
			<ul>
				<li>我的消息</li>
				<li>我的企业</li>
				<li>我的应用</li>
				<li>我的社区</li>
				<li>帮助中心</li>
			</ul>
		</div>
		
		
		<!-- 用户信息 -->
		<!-- 未登录 -->
		<div id="userLogin" style="display:none;"> <a id="login" href="javascript:void(0);">登录</a></div>
		
		
		<!-- 百度导航 -->
		<div id="baidu" class="baidu"> 
			
		</div>
		
		<div id="funcMenu">
			<!-- 后台生成，此处先写出来 -->
			<ul>
				<li><span id="">系统推荐（暂时）</span></li>
				<li><span id="common">爱常用</span></li>
				<li><span id="work">爱工作</span></li>
				<li><span id="life">爱生活</span></li>
				<li><span id="ptyun">平通云</span></li>
			</ul>
		</div>
		
		<!-- 主要内容展示区 -->
		<div id="mainContent" >
			<%--<!-- 未登录用户使用，由系统推送 -->
			<div id="none_login" style="display:none;">
				
			</div>
			<!-- 爱常用中内容 -->
			<div id="love_common" style="display:none;">
				
			</div>
			<div id="love_work" style="display:none;"></div>
			<div id="love_life" style="display:none;"></div>
			<div id="pt_yun" style="display:none;"></div>--%>
			
			<div id="div1" style="margin-left:150px; height:100%; display: none;">
				<iframe id="iframe1"  src="" height="100%" width="100%" scrolling="NO"></iframe>
			</div>
			
			
		</div>
		<!-- 侧边回到页首和帮助 -->
		<div id="side">
			<ul>
				<li></li>
			</ul>
		</div>
	</div>
	
  	<!-- 登录的model -->
   <%@ include file="login.jsp"  %>
  
  </body>
</html>
