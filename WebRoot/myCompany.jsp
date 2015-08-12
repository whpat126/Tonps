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
  
  <jsp:include page="ptdh.jsp"></jsp:include>
  <jsp:include page="black_on_top.jsp"></jsp:include>
  <jsp:include page="login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
	<!-- 未加入企业 -->
	<div id="noCompanyDiv" style="display:none;">
		您尚未加入企业，可以选择：<a>加入企业</a><a>创建企业</a>
	</div>

  <!-- 已经加入企业：普通用户 -->
 	<div id="companyUserDiv" style="display:none;">
		 		
 	</div>
 	
 	<!-- 加入企业：管理员 -->
	<div id="companyAdminDiv" style="display:none;">
	
	</div>
     </div>
  </body>
</html>
