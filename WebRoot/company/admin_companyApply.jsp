<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业申请审核</title>
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="style/common/userCSS/base.css"/>
    <link rel='icon' href='style/common/img/fivelogo.ico' type='image/x-ico'/>
    <!--[if lt IE 9]>-->
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function(){
			
			$.ajax({
				type : "POST",
				url : "admin_companyApply2.do",
				success : function(userData){
					if("false" != userData) {
						var data = eval("("+userData+")");
						var contents = "";
						AddRow(data.dataList); // j指的是那个table
						$("#adminUserApply").show(500);
						$("#countPage").html(data.totalPage); // 显示总页码
						$("#currentPage").html(data.currentPage); // 显示当前页码
						$("#countRows").html(data.totalRowCount); // 显示总行数
					}
					
				}
			});
			
		
			// 分页功能，点击下一页时 获得当前页+1，该值传到数据库中，则查找的是下一页
			$("#nextPage").click(function(){
				var num = $('#currentPage').html();
				$.ajax({
					type : "post",
					url : "admin_companyApply.do",
					data : { 
						"action" : "NextPage",
						"pageNum" : parseInt(num)+1,
						"rowsNum" : $("#rowsNum").val()
					},
//					error : function(){ alert("连接错误!"); },
					success : function(userData){
						if("false"==userData) alert("没有数据了");
						else{
							var data = $.parseJSON(userData);
							$("#countPage").html(data.totalPage); 
							$("#countRows").html(data.totalRowCount);
							$("#currentPage").html(data.currentPage);  // 这里很重要，必须把当前行赋值到页面中。
							if(data.dataList!="")
								AddRow(data.dataList); 
						}
					}
				});
			});
			// 分页功能，点击上一页时
			$("#beforePage").click(function(){
				var num = $("#currentPage").html();
				$.ajax({
					type : "post",
					url : "admin_companyApply.do",
					data : { 
						"action" : "BeforePage",
						"pageNum" : parseInt(num)-1,
						"rowsNum" : $("#rowsNum").val()
					},
					success : function(userData){
						if("false"==userData) alert("没有数据了");
						else{
							var data = $.parseJSON(userData);
							$("#countPage").html(data.totalPage); 
							$("#countRows").html(data.totalRowCount);
							$("#currentPage").html(data.currentPage);  // 这里很重要，必须把当前行赋值到页面中。
							if(data.dataList!="")
								AddRow(data.dataList); 
						}
					}
				});
			});
			// 分页 当输入转到某个页面时
			$("#toPage").keyup(function(){
				$.ajax({
					type : "post",
					url : "admin_companyApply.do",
					data : { 
						"action" : "ToPage",
						"pageNum" : $("#toPage").val() , 
						"rowsNum" : $("#rowsNum").val(),
					},
//					error : function(){ alert("连接错误!"); },
					success : function(userData){
						if("false"==userData) alert("没有数据了");
						else{
							var data = $.parseJSON(userData);
							$("#countPage").html(data.totalPage); 
							$("#countRows").html(data.totalRowCount);
							$("#currentPage").html(data.currentPage);  // 这里很重要，必须把当前行赋值到页面中。
							if(data.dataList!="")
								AddRow(data.dataList); 
						}
					}
				});
			});

			//企业审核点击时
			$("#approveSpan").click(function(){
				var applyFatherCompany="";
				var applyChildCompany="";
				var checkbox = document.getElementsByName("checkbox");
				var rows = document.getElementById("table1").rows; 
				var row = "";
				var type = "";
				var count =0;
				for(var i=0;i<checkbox.length;i++){
					if(checkbox[i].checked){
						count ++;
						// 根据table中的值得到申请类型
						row = checkbox[i].parentElement.parentElement.rowIndex; 
						type = rows[row].cells[2].innerHTML; // 获得整行数据的方法
						//alert(type)
						if(type == "申请成为上级单位"){
							applyFatherCompany += "," + checkbox[i].value+":"+rows[row].cells[1].innerHTML+":"+rows[row].cells[3].innerHTML;
						}else if(type == "申请成为下级单位"){ 
							applyChildCompany +=  "," + checkbox[i].value+":"+rows[row].cells[1].innerHTML+":"+rows[row].cells[3].innerHTML;
						}
					}
				}
				if(count == 0){return false;};
				applyFatherCompany = applyFatherCompany.substr(1) ;
				applyChildCompany = applyChildCompany.substr(1); 
				// 将选中的整行数据提交给后台
				$.ajax({
					type : "POST",
					url : "company/admin_companyApply.do",
					data : {
						"applyFatherCompany":applyFatherCompany, 
						"applyChildCompany":applyChildCompany
					},
					success : function(userData){
						location.replace(location);
					}
				}); 
			});
		});
	
		
		//表格增行操作
		function AddRow(data){
			var trHTML ="";
			$("#table1").children("tbody").children().remove();
			var chk="";
			var type1 = "";
			var trHTML = "";
			var applyCompany = "";
			var joinCompany = "";
			var apply = "";
			for(var i=0 ; i<data.length; i++){
				chk ="<input type='checkbox' id='"+i+"' name='checkbox' value='"+ data[i].pk_users_mycompany +"'/>";
				applyCompany = data[i].applyName==undefined?"":data[i].applyName;
				joinCompany = data[i].name==undefined?"":data[i].name;
				apply = data[i].apply_company;
				if(apply == 1){ 
					type1 = "申请成为上级单位";
				}
				if(apply == 2){ 
					type1 = "申请成为下级单位";
				}
				trHTML = "<tr><td>"+chk+"</td><td>"+applyCompany+"</td><td>"+type1+"</td><td>"+joinCompany+"</td></tr>";
				$(trHTML).appendTo($("#table1 tbody"));
			}
		}
		
		// 全选和反选操作
	    function checkboxAll(){
	    	var checkboxSelect = document.getElementById("checkboxSelect");
	    	var checkbox = document.getElementsByName("checkbox");
	    	if(checkboxSelect.checked){
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
	<div id="companyAdminDiv" style="">
		<div >
			<ul class="company">
				<li><a href="company/myCompany.jsp" id="admin_companyApply">用户申请审核</a></li>
				<li><a href="company/admin_companyApply.jsp" id="admin_companyApply">企业申请审核</a></li>
				<li><a href="company/admin_business.jsp" id="admin_business">企业业务申请</a></li>
				<li><a href="company/admin_companyInfo.jsp" id="admin_companyInfo">维护企业信息</a></li>
				<li><a href="company/admin_group.jsp" id="admin_group">群组设置</a></li>
				<li><a href="company/admin_userInfo.jsp" id="admin_userInfo">用户信息维护</a></li>
				<li><a href="company/admin_companySystem.jsp" id="admin_companySystem">企业系统维护</a></li>
			</ul>
		</div>
	</div>
	<div style=""> <span id="approveSpan">审核通过</span>   <span id="ignore">忽略</span>   <span id="filtrate">筛选</span></div>
		<table id="table1"  class="table table-hover table-bordered">
			<thead>
				<tr><th> <input type="checkbox" id="checkboxSelect" onclick="checkboxAll();" /> </th><th>管理单位</th><th>申请类型</th><th>申请单位</th></tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="pagingDiv" id="pagingDiv">
			每页显示<input type="text" size="5" name="rowsNum" id="rowsNum" value="10" />行&nbsp;
			当前<span id="currentPage"></span>页 &nbsp; 
			转到<input type="text" size="5" name="toPage" id="toPage" />&nbsp;&nbsp;
			共<span id="countPage"></span>页&nbsp;<span id="countRows"></span>条 
			<a id="beforePage">上一页</a> <a id="nextPage">下一页</a>
		</div>
	</div>
  </body>
</html>
