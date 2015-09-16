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
	  		data : { simpleData: { enable:true, "idKey":"pk_myCompany","pidKey":"pk_father"} },
	  		callback:{
	  			onClick:onClick
	  		}
	  	};

		$.ajax({
			type : "post",
			url : "company/queryCompany.do",
			success : function(znodes) {
				var nodes = eval("("+znodes+")")
				$.fn.zTree.init($("#demo"),setting, nodes);
			}
		});
		function onClick(event,treeId,treeNode,clickFlag){
			// alert("bbbbbbbbb")
			var pk_myCompany = treeNode.pk_myCompany;
			$.ajax({
				type : "post",
				url : "company/queryCompanyById.do",
				data : {"pk_myCompany": pk_myCompany},
				success : function(jsonData) {
					var data = eval("("+jsonData+")");
					$("#div1").show();
					$("#myCompanyName").val(data.name);
					$("#myCompanyPhone").val(data.phone);
				}
			});
		}
		
	});

	function submitAdminCompanyInfo(){
		// 判断必填项
		var zzjgdmz = document.getElementById("zzjgdmz");
		if(zzjgdmz.value == "" || zzjgdmz.value == null) {$("#companyInfoErrorMsg").html("必须上传文件");return false;}
				
		document.getElementById("adminCompanyInfoForm").submit();
	}
	</script>
	
  </head>
  
  <body>
  
  <jsp:include page="../ptdh.jsp"></jsp:include>
  <jsp:include page="../black_on_top.jsp"></jsp:include>
  <jsp:include page="../login.jsp"></jsp:include>
 <div class="navbar-fixed-top" style="margin-top: 106px;position: fixed;background-color: #f9f9f9;height: 60px;">
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
	<strong><span style="margin-left:150px;">${companyInfomsg }</span></strong>
 	<div style="position:absolute;left:0; width:150px; height:100%;border-right: red 1px solid; ">
		<!-- 这个是功能节点树的div -->
		<div id="demo" class="ztree" style="width:150px;"></div>
	</div>
	
	<div id="div1" style="margin-left:150px; height:100%; display: none;">
		<div id="adminCompanyInfo" style=";">
			<span id="companyInfoErrorMsg"></span>
			<form id="adminCompanyInfoForm" action="company/admin_companyInfo.do" method="post" enctype="multipart/form-data">
				企业名称：<input type="text" name="name" id="myCompanyName"  />
			 	联系方式：<input type="text" name="phone" id="myCompanyPhone"  />
		 		组织机构代码证:<input type="file" name="zzjgdmz" id="zzjgdmz"  />
			 	<!-- 企业注册号:<input type="text" name="" id=""  />
			 	
			 	以下为选择输入
			 	企业地址：<input type="text" name=""  />
			 	企业网址：<input type="text" name=""  /> -->
			 	<input type="button" value="确定" onclick="submitAdminCompanyInfo();"/>
			
			</form>
		</div>
	</div>
	
 	
 
 </div>
  </body>
</html>
