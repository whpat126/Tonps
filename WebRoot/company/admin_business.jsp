<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业业务申请</title>
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
				check : { enable : true, chkStyle: "radio" },
		  		data : { simpleData: { enable:true, "idKey":"pk_myCompany","pidKey":"pk_father"} },
			};
			
			
			
			//查询单位是否存在
			$("#check").click(function(){
				$.ajax({
					type : "post",
					url : "checkCompany.do",
					data : { 
						"companyName" : $("#companyName").val()
					},
					success : function(data){
						if("false" == data){
							$("#companyMsg").html("没有找到该企业");
							$("#companyInfo").hide();
						}else{
							$("#companyMsg").hide();
							$("#companyInfo").show();
							var comdata = eval("("+data+")");
							$("#pk_company").val(comdata.pk_myCompany);
							$("#name").html(comdata.name);
							$("#address").html(comdata.address);
							$("#phone").html(comdata.phone);
						}
					}
				});
			});
			//申请上级或下级点击时,查找该申请用户身份是管理员的所有单位
			$("input[name='business']").click(function(){
				$.ajax({
					type : "post",
					url : "companyBusinessApply.do",
					dataType : "json",
					success : function(data){
						if(data != ""){
							$("#companyApplyDiv").show();
							$.fn.zTree.init($("#demo"),setting, data);
							
						}
					}
				});
				
			});
			
			// 将用户申请提交给管理员
			$("#submitCompanyApply").click(function(){
				var business = $("input[name='business']").val(); // 申请类型
				var joinCompany = $("#pk_company").val(); // 查找出来的企业 被申请
				var treeObj = $.fn.zTree.getZTreeObj("demo");
				var nodes = treeObj.getCheckedNodes(true);
				if(nodes.length == 0) {$("#companyMsg").html("请选择一个单位！");return false;}
				var applyCompany = nodes[0].pk_myCompany; // 管理员管理单位 提交申请
				if(joinCompany == applyCompany) {$("#companyMsg").html("不能选择同一个单位！");return false;}
				 $.ajax({
					type : "post",
					url : "submitCompanyApply.do",
					data : {"business":business, "joinCompany":joinCompany, "applyCompany":applyCompany },
					success : function(data){
						if(2 == data){
							alert("您的申请已经提交给管理员，请等待管理员审核通过！");
						}else if(1 == data){
							alert("您已经申请过了，无需重复申请！");
						}else if(4 == data){
							$("#companyMsg").html("当前单位已经存在上级单位，您可以<a href='appealCompany.jsp'>申诉</a>");
						}
					}
				}); 				
			});
			
			
		});
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
	<!-- 查询单位  申请成为该单位的上级单位 或 下级单位 -->
 	<div>
 		单位名称：<input type="text" id="companyName" /><input type="button" id="check" title="搜索企业是否存在" value="搜索" /><br/><br/>
		<div id="companyMsg" style="color:red;font-size:14px;font-weigth:bold; "></div>
		<div id="companyInfo" style="display:none;">
	 		名称：<span id="name"></span>
	 		地址：<span id="address"></span>
	 		电话：<span id="phone"></span>
 			<input type="hidden" id="pk_company" value="" />
			申请成为其上级：<input type="radio" name="business" value="1" />   
	 		申请成为其下级：<input type="radio" name="business" value="2" /><br/>
	 		<div id="companyApplyDiv" style="display:none;">
	 			请选择提出申请的单位：
	 			<span id="demo" class="ztree"></span>
		 		<input id="submitCompanyApply" type="button" value="确定" />
	 		</div>
	 	</div>
	 		
	 </div>
 </div>
  </body>
</html>
