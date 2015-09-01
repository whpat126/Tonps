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
    <script type="text/javascript" src="<%=basePath %>/company/joinCompany.js"></script>
    <title>加入企业</title>

  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
 	请输入企业名称：<input type="text" name="companyName" id="companyName" /><input type="button" id="check" title="搜索企业是否存在" value="搜索" /><br/>
 	<div id="companyInfo" style="display:none;">
 		<div>
 		<input type="hidden" id="pk_company" value="" />
 		<span id="name"></span>
 		<span id="address"></span>
 		<span id="phone"></span>
 	<input type="button" value="加入企业" id="joinCompany" /> <a href="<%=basePath %>/company/appealCompany.jsp" >企业申诉</a> <input type="reset" value="取消" />
 		</div>
 	</div>
	<div id="noCompany" style="display:none;">
		<span>没有找到该企业，您可以：<a href="${pageContext.request.contextPath }/company/createCompany.jsp">创建企业</a></span>
	</div>
 	
 	<p>企业申诉说明：1.。。。。2。。。。。3............。。</p>
 	
 
 </div>
  </body>
</html>
