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
    <link rel="stylesheet" href="style/common/userCSS/base.css"/>
    <link rel='icon' href='style/common/img/fivelogo.ico' type='image/x-ico'/>
    <!--[if lt IE 9]>-->
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    
    <link rel="stylesheet" href="style/other/zTree/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="style/other/zTree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="style/other/zTree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	$(function(){
		var setting = {
	  		data : { simpleData: { enable:true, "idKey":"pk_group","pidKey":0} },
	  		callback:{onClick:onClick }
	  	};

		$.ajax({
			type : "post",
			url : "company/queryGroup.do",
			success : function(znodes) {
				var nodes = eval("("+znodes+")")
				$.fn.zTree.init($("#demo"),setting, nodes);
			}
		});
		function onClick(event,treeId,treeNode,clickFlag){
			var pk_group = treeNode.pk_group;
			$.ajax({
				type : "post",
				url : "company/queryGroupById.do",
				data : {"pk_group": pk_group},
				success : function(jsonData) {
					var data = eval("("+jsonData+")");
					$("#div1").show();
					$("#groupName").val(data.name);
					$("#groupUsers").val(data.groupUsers);
				}
			});
		}
		
	});

	function submitGroupInfo(){
		// 判断必填项
		
		document.getElementById("groupInfoForm").submit();
	}
	</script>
    <title>群组设置</title>

  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
 	
	<div id="companyAdminDiv" style=";">
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
	
	<strong><span style="margin-left:150px;">${groupInfomsg }</span></strong>
 	<div style="position:absolute;left:0; width:150px; height:100%;border-right: red 1px solid; ">
		<!-- 这个是功能节点树的div -->
		<div id="demo" class="ztree" style="width:150px;"></div>
	</div>
	
	<div id="div1" style="margin-left:150px; height:100%; display: none;">
		<div id="groupInfo" style=";">
			<span id="groupInfoErrorMsg"></span>
			<form id="groupInfoForm" action="company/admin_group.do" method="post" >
				群组名称：<input type="text" name="name" id="groupName"  />
			 	群组成员：<input type="text" name="groupUsers" id="groupUsers"  />
			 	<input type="button" value="确定" onclick="submitGroupInfo();"/>
			
			</form>
		</div>
	</div>
 	
 
 </div>
  </body>
</html>
