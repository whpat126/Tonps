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
    <script type="text/javascript" src="company/myCompany.js"></script>
    <title>我的企业</title>

  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
	<!-- 未加入企业 -->
	<div id="noCompanyDiv" style="display:none;">
		您尚未加入企业，可以选择：
		<a href="${pageContext.request.contextPath }/company/joinCompany.jsp">加入企业</a>
		<a href="${pageContext.request.contextPath }/company/createCompany.jsp">创建企业</a>
	</div>
	
 	<!-- 已经加入企业：管理员 -->
 	<div id="adminUserApply" style="display:none;">
		<div id="companyAdminDiv" style="">
			<div >
				<ul class="company">
					<li><a href="company/myCompany.jsp" id="admin_userApply">用户申请审核</a></li>
					<li><a href="company/admin_companyApply.jsp" id="admin_companyApply">企业申请审核</a></li>
					<li><a href="company/admin_business.jsp" id="admin_business">企业业务申请</a></li>
					<li><a href="company/admin_companyInfo.jsp" id="admin_companyInfo">维护企业信息</a></li>
					<li><a href="company/admin_group.jsp" id="admin_group">群组设置</a></li>
					<li><a href="company/admin_userInfo.jsp" id="admin_userInfo">用户信息维护</a></li>
					<li><a href="company/admin_companySystem.jsp" id="admin_companySystem">企业系统维护</a></li>
				</ul>
			</div>
		</div>
		<div style=""> <span id="approve">审核通过</span>   <span id="ignore">忽略</span>   <span id="filtrate">筛选</span></div>
		<table id="table1"  class="table table-hover table-bordered">
			<thead>
				<tr><th> <input type="checkbox" id="checkboxSelect" onclick="checkboxAll();" /> </th><th>姓名</th><th>单位</th><th>类型</th></tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
		<div class="pagingDiv" id="pagingDiv">
			每页显示<input type="text" size="5" name="rowsNum" id="rowsNum" value="5" />行&nbsp;
			当前<span id="currentPage"></span>页 &nbsp; 
			转到<input type="text" size="5" name="toPage" id="toPage" />&nbsp;&nbsp;
			共<span id="countPage"></span>页&nbsp;<span id="countRows"></span>条 
			<a id="beforePage">上一页</a> <a id="nextPage">下一页</a>
		</div>
	</div>
	
	<!-- 已经加入企业：普通用户 -->
 	<div id="companyUserDiv" style="display:none;">
		<p> 使用bootstrap的选项卡，默认显示我的企业 </p>
		<ul>
			<li><a id="userMyCompany">我的企业</a></li>
			<li><a href="${pageContext.request.contextPath }/company/joinCompany.jsp">加入企业</a></li>
		</ul>
 	
		<div id="myCompany" >
				<span style="color:#666 ">每行三块内容 企业名称  申请管理员  退出企业</span>
			<ul>
			</ul>
		</div>
	</div>
 </div>
  </body>
</html>
