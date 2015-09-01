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
    <title>企业申请审核</title>
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
	 // 全选和反选操作
	    function checkboxAll(){
	    	var checkboxSelect = document.getElementById("checkboxSelect");
	    	var checkbox = document.getElementsByName("checkbox");
	    	if(checkboxAll.checked){
	    		for(var i=0;i<checkbox.length;i++){
	    			checkbox[i].checked=true;
	    		}
	    	}else{
	    		for(var i=0;i<checkbox.length;i++){
	    			checkbox[i].checked=false;
	    		}
	    	}
	    	
	    }
	</script>
  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
 	<!-- 已经加入企业：管理员 -->
	<span style="color:#666 ">企业管理员（删除）</span>
	<div id="companyAdminDiv" style="">
		<div >
			<ul>
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
	
 	<div id="adminCompanyApply" style=";">
		<table id="table2"  class="table table-hover table-bordered">
			<thead>
				<tr><th></th><th>申请类型</th><th>姓名</th><th>单位</th><th>电话</th></tr>
			</thead>
			
			<tbody>
			<!-- 为试验效果添加 -->
			<tr>
				<td>
				<input type="checkbox" name="checkbox" value="1">
				</td>
				<td>申请成为我的上级</td>
				<td>宋琪</td>
				<td>山东平通</td>
				<td>111222333</td>
				</tr>
				<tr>
				<td>
				<input type="checkbox" name="checkbox" value="2">
				</td>
				<td>申请成q</td>
				<td>王五</td>
				<td>山东平通</td>
				<td>222333444</td>
			</tr>
			</tbody>
		</table>
	</div>
 
 </div>
  </body>
</html>
