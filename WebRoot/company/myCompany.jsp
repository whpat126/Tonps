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
    <script type="text/javascript" src="style/common/userjs/myCompany.js"></script>
    <title>我的企业</title>

  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
	<!-- 未加入企业 -->
	<div id="noCompanyDiv" >
		您尚未加入企业，可以选择：
		<a href="${pageContext.request.contextPath }/company/joinCompany.jsp">加入企业</a>
		<a href="${pageContext.request.contextPath }/company/createCompany.jsp">创建企业</a>
	</div>
  <!-- 已经加入企业：普通用户 -->
 	企业普通用户（需要删除）：<div id="companyUserDiv" >
		<p> 使用bootstrap的选项卡，默认显示我的企业 </p>
		<ul>
			<li><a id="userMyCompany">我的企业</a></li>
			<li><a href="${pageContext.request.contextPath }/company/joinCompany.jsp">加入企业</a></li>
		</ul>
 	</div>
 	
 	<div id="myCompany">
 			<span>每行三块内容 企业名称  申请管理员  退出企业</span>
 		<ul>
 		</ul>
 	</div>
 	
 	
 	
 	<!-- 已经加入企业：管理员 -->
	企业管理员（删除）<div id="companyAdminDiv" >
		<ul>
		
		</ul>
	</div>
 </div>
  </body>
</html>
