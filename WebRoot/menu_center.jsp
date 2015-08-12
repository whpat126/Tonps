<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="style/common/userCSS/base.css"/>
    <link rel='icon' href='style/common/img/fivelogo.ico' type='image/x-ico'/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
    <title>menu_center.jsp</title>

  </head>
  
  <body>
  
  <jsp:include page="ptdh.jsp"></jsp:include>
  <jsp:include page="black_on_top.jsp"></jsp:include>
  <jsp:include page="login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
 	<!-- 左侧功能菜单 -->
 	<div>
   	<ul>
   		<li><a href="${pageContext.request.contextPath }/msgcenter.jsp">消息中心</a></li>
   		<li><a href="${pageContext.request.contextPath }/usercenter.jsp">个人信息</a></li>
   		<li><a href="${pageContext.request.contextPath }/myApply.jsp">我的应用</a></li>
   		<li><a>我的企业</a></li>
   		<li><a>安全中心</a></li>
   	</ul>
   </div>
  </div>

  <%--
 	<!-- 右侧具体内容 -->
 	<div id="modifyPwddiv" style="display:none;">
 		<div id=mpdmsg></div>
 		旧密码：<input type="password" id="oldpassword" /><br/>
 		新密码:<input type="password" id="new1password" /><br/>
 		确认:<input type="password" id="new2password" /><br/>
 		<input type="button" id="modifyPwdbtn" value="确认修改"/>
 	</div>
 	<div id="emailVerifydiv" style="display:none;">
 		<div id="evdmsg"></div>
 		输入email地址：<input type="text" id="emailContent" /><br/>
 		确认：<input type="button" id="emailVerifybtn" value="提交"/>
 		
 	</div>
 	<!-- 未设置密码问题 -->
 	<div id="noSecuritydiv" style="display:none;">
 		<div id="noSecuritymsg"></div>
 		请选择密码问题：<select id="nopwdquestion"></select><br/>
 		请输入答案:<input type="text" id="nopwdanswer" /><br/>
 		<input type="button" id="nopwdbtn" value="提交" />
 	</div>
 	<!-- 已经设置过密码问题 -->
 	<div id="Securitydiv" style="display:none;">
 		<div id="sdmsg"></div>
 		旧密保问题：<select id="yesoldpwdquestion"></select><br/>
 		请输入旧密保问题答案:<input type="text" id="yesoldpwdanswer" /><br/>
 		请选择新问题：<select id="yesnewpwdquestion"></select><br/>
 		请输入答案：<input type="text" id="yesnewpwdanswer" /><br/>
 		<input type="button" value="确认" id="yespwdbtn" />
 	</div>
 --%>
   
  </body>
</html>
