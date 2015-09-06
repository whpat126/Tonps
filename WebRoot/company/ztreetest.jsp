<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>维护企业信息</title>
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <link rel='icon' href='style/common/img/fivelogo.ico' type='image/x-ico'/>
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="style/other/zTree/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="style/other/zTree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="style/other/zTree/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>/company/admin_companyInfo.js"></script>
  </head>
  
  <body>
  
 	<div style="position:absolute;left:0; width:150px; height:100%;border-right: red 1px solid; ">
		<!-- 这个是功能节点树的div -->
		<div id="demo" class="ztree" style="width:150px;"></div>
	</div>
	<div id="div1" style="margin-left:150px; height:100%; display: none;">
	<iframe id="iframe1"  src="" height="100%" width="100%" scrolling="NO"></iframe>
	</div>
  </body>
</html>
