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
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
    <title>创建企业</title>

  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
	<div style="color:red;font-size:14px;font-weight:bold;">${msgError }</div>
	<form action="company/createCompany.do" method="post">
	企业名称：<input type="text" name="name" /><br/>
 	联系方式：<input type="text" name="phone" /><br/>
 	组织机构代码证: <input type="text" name="zzjgdmz" /><br/>
 	企业注册号:<input type="text" name="companyReg" /><br/>
 	
 	以下为选择输入
 	企业地址：<input type="text" name="address" /><br/>
	<input type="submit" value="提交" />
	</form>
 </div>
  </body>
</html>
