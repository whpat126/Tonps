
$(function(){
	// 判断用户是不是企业用户，同时判断用户是企业管理员还是普通用户
	$.ajax({
		type : "POST",
		url : "checkUser.do",
		success : function(data){
			if( "0" == data ){ // 不是企业用户
				$("#noCompanyDiv").show();
			}
			if("1" == data){ // 是企业用户
				var $myCompany = $("#myCompany");
				$("#companyUserDiv").show();
				$myCompany.show();
				displayUser();
				$("#companyAdminDiv").show();
				displayAdmin();
			}
			if("2" == data){
				alert("error");
			}
		}
	});
	
	function displayUser(){
		$.ajax({
			type : "POST",
			url : "userCompany.do",
			success : function(jsonData){
				var data = eval("("+jsonData+")");
				var contents = "";
				for(var i=0;i<data.length;i++){
					contents += "<li>" + data[i].name + 
					"<a href='applyAdmin.do?pk_myCompany="+data[i].pk_myCompany+"'>申请管理员</a>" +
					"<a href='outCompany.do?pk_myCompany="+data[i].pk_myCompany+"'>退出企业</a></li> <br/>";
				}
				$("#myCompany").append(contents);
			}
		});
	}
	
	// 分页功能，点击下一页时 获得当前页+1，该值传到数据库中，则查找的是下一页
	$("#nextPage").click(function(){
		var num = $('#currentPage').html();
		$.ajax({
			type : "post",
			url : "admin_userApply.do",
			data : { 
				"action" : "NextPage",
				"pageNum" : parseInt(num)+1,
				"rowsNum" : $("#rowsNum").val()
			},
//			error : function(){ alert("连接错误!"); },
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
			url : "admin_userApply.do",
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
			url : "admin_userApply.do",
			data : { 
				"action" : "ToPage",
				"pageNum" : $("#toPage").val() , 
				"rowsNum" : $("#rowsNum").val(),
			},
//			error : function(){ alert("连接错误!"); },
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
	
	
	function displayAdmin(){
		$.ajax({
			type : "POST",
			url : "admin_userApply.do",
			success : function(userData){
				if("false"==userData) {
					
				}else{
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
	}
	
	//表格增行操作
	function AddRow(data){
		var trHTML ="";
		$("#table1").children("tbody").children().remove();
		var chk="";
		var username1 = "";
		var phone1 = "";
		var type1 = "";
		var apply = "";
		var trHTML = "";
		
		for(var i=0 ; i<data.length; i++){
			chk ="<input type='checkbox' id='"+i+"' name='checkbox' value='"+ data[i].pk_users_mycompany +"'/>";
			username1 =  data[i].username ;
			//phone1 = data[i].phone==undefined?"":data[i].phone; // 三目运算强大！！！
			name1 = data[i].name;
			apply = data[i].apply;
			if(apply == 0){ // apply = "申请加入企业";
				//type1 = "  <a href='company/joinCompany.do?pk_company="+data[i].pk_mycompany+"&pk_users="+data[i].pk_users+"'>" + "加入本单位</a>";
				type1 = "加入本单位";
			}
			if(apply == 1){ // 申请成为管理员
				//type1 = "<a href='company/userToAdmin.do?pk_company="+data[i].pk_mycompany+"&pk_users="+data[i].pk_users+"'>" + "升级企业管理员</a>";
				type1 = "升级企业管理员";
			}
			
			trHTML = "<tr><td>"+chk+"</td><td>"+username1+"</td><td>"+name1+"</td><td>"+type1+"</td></tr>";
			$(trHTML).appendTo($("#table1 tbody"));
		}
	}
	// 批量审核通过table中多类型数据
	// 需要判断申请类型，是用户申请加入企业还是申请成为管理员
	// 用户申请加入企业：只需要得到users_mycompany表的主键即可
	$("#approve").click(function(){
		// alert("1");
		//判断选中的id
		var joinCompany="";
		var applyAdmin="";
		var checkbox = document.getElementsByName("checkbox");
		var rows = document.getElementById("table1").rows; 
		var row = "";
		var type = "";
		for(var i=0;i<checkbox.length;i++){
			if(checkbox[i].checked){
				// 根据table中的值得到申请类型
				row = checkbox[i].parentElement.parentElement.rowIndex; 
				type = rows[row].cells[3].innerHTML;
				if(type == "加入本单位"){ // 用户申请加入企业
					joinCompany +="," + checkbox[i].value ;
				}else if(type == "升级企业管理员"){ // 用户申请成为企业管理员
					applyAdmin +=  "," + checkbox[i].value ;
				}
			}
		}
		joinCompany = joinCompany.substr(1) ;
		applyAdmin = applyAdmin.substr(1);
		// 将选中的整行数据提交给后台
		$.ajax({
			type : "POST",
			url : "Users_Apply.do",
			data : {
				"joinCompany":joinCompany, 
				"applyAdmin":applyAdmin
			},
			success : function(userData){
				location.replace(location);
			}
		});
	
	});
	
	
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
