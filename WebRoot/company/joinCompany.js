
$(function(){
	// 点击搜索
	$("#check").click(function(){
		var companyName = $("#companyName").val();
		$.ajax({
			type : "POST",
			url : "checkCompany.do",
			data : {"companyName":companyName},
			success : function(data){
//				data = "true";
				if("false" == data){
					$("#noCompany").show();
				}else{
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
	
	// 点击加入企业
	$("#joinCompany").click(function(){
		var company = $("#pk_company").val();
		$.ajax({
			type : "POST",
			url : "joinCompany.do",
			data : {"pk_company":company},
			success : function(data){
				if("true" == data){
					alert("您的申请已经提交给管理员，请等待管理员审核通过！");
				}
			}
		});
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});