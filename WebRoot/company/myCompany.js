
$(function(){
	// 判断用户是不是企业用户，同时判断用户是企业管理员还是普通用户
	$.ajax({
		type : "POST",
		url : "checkUser.do",
		success : function(data){
//			 data = "noCompanyUser";
			 data = "companyUser";
//			data = "companyAdmin";
			if( "noCompanyUser" == data ){ // 不是企业用户
				$("#noCompanyDiv").show();
			}
			if("companyUser" == data){ // 是企业用户
				var $myCompany = $("#myCompany");
				$("#companyUserDiv").show();
				$myCompany.show();
				displayUser();
				$("#companyAdminDiv").show();
				displayAdmin();
			}
			/*if("companyAdmin" == data){
				
			}*/
		}
	});
	
	/*// 管理员用户申请审核
	$("#admin_userApply").click(function(){
		// location.replace(location);
		$("#adminCompanyApply").hide();
		$("#adminBusiness").hide();
		$("#adminCompanyInfo").hide();
		$("#adminUserApply").show(500);
		displayAdmin();
	});
	
	// 管理员企业申请审核
	$("#admin_companyApply").click(function(){
		// location.replace(location);
		$("#adminBusiness").hide();
		$("#adminCompanyInfo").hide();
		$("#adminUserApply").hide();
		$.ajax({
			type : "POST",
			url : "admin_companyApply.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				AddRow(data,"table2"); // j指的是那个table
				$("#adminCompanyApply").show(500);
			}
		});
	});
	
	// 管理员企业业务申请审核
	$("#admin_business").click(function(){
		// location.replace(location);
		$("#adminCompanyApply").hide();
		$("#adminCompanyInfo").hide();
		$("#adminUserApply").hide();
		$.ajax({
			type : "POST",
			url : "admin_business.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				AddRow(data,"table3"); // j指的是那个table
				$("#adminBusiness").show(500);
			}
		});
	});
	
	// 管理员维护企业信息
	$("#admin_companyInfo").click(function(){
		// location.replace(location);
		$("#adminCompanyApply").hide();
		$("#adminBusiness").hide();
		$("#adminUserApply").hide();
		$.ajax({
			type : "POST",
			url : "admin_companyInfo.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				AddRow(data,"table4"); // j指的是那个table
				$("#adminCompanyInfo").show(500);
			}
		});
	});*/
	
	
	function displayUser(){
		$.ajax({
			type : "POST",
			url : "userCompany.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				var contents = "";
				for(var i=0;i<data.length;i++){
					contents += "<li>" + data[i].name + 
					"<a href='applyAdmin.do?pk_myCompany="+ data[i].pk_myCompany +"' >申请管理员</a>" +
					"<a href='outCompany.do?pk_myCompany="+ data[i].pk_myCompany +"'>退出企业</a></li> <br/>";
				}
				$("#myCompany").append(contents);
			}
		});
	}
	
	function displayAdmin(){
		
		$.ajax({
			type : "POST",
			url : "admin_userApply.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				var contents = "";
				AddRow(data,"table1"); // j指的是那个table
				$("#adminUserApply").show(500);

			}
		});
	}
	
	//表格增行操作
	function AddRow(data,j){
		var trHTML ="";
		var aaa = $("#"+j);
		$(aaa).children("tbody").children().remove();
		for(var i=0 ; i<data.length; i++){
			var chk="<input type='checkbox' name='checkbox' value='"+ data[i].pk_myCompany +"'/>";
			var type = data[i].type ;
			var userName = data[i].userName;
			var user_company = data[i].user_company;
			var user_phone = data[i].user_phone;
			trHTML += "<tr><td>"+chk+"</td><td>"+type+"</td><td>"+userName+"</td><td>"+user_company+"</td><td>"+user_phone+"</td></tr>";
			// $(trHTML).insertAfter($("#table1 tr:eq("+[i]+")"));
		}
		
		// alert($(aaa).children("tbody"));
		// $(trHTML).appendTo($("#table1 tbody"));
		$(trHTML).appendTo($(aaa).children("tbody"));
	}
	
	
	
});


// 全选和反选操作
function checkboxAll() {
	var checkboxSelect = document.getElementById("checkboxSelect");
	var checkbox = document.getElementsByName("checkbox");
	if (checkboxSelect.checked) {
		for (var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = true;
		}
	} else {
		for (var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = false;
		}
	}

}
















