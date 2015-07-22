
$(function() {
	$.ajax({
		type : "POST",
		async: false,
		url : "autoLogin.do",
//		error : function(){alert("连接错误....");},
		success : function(data){
			if(data == "false"){
				$("#userLogin").show();
			}else{
				$("#userName").html(data);
				$("#userInfo").show(); // 显示用户按钮
				$("#userSet").show();
			}
		},
		error:function(data){
			alert("123");
		}
	});
	
//	var userName = $("#userName").html();
//	if(userName=="" || userName == null){ // 即用户不存在
////		alert(userName);
//		$("#userLogin").show(); // 登录按钮显示
//	}else{ //用户已经登录
////		$("#userLogin").hide(); // 登录按钮yincang
//		$("#userInfo").show(); // 显示用户按钮
//		$("#userSet").show();
//	}
	
	
	
	
	// 模态框设置
	$('#myModal').modal({backdrop:"static", keyboard:false, show:false});
	$("#login").click(function(){
		$('#myModal').modal({backdrop:"static", keyboard:false, show:true});
	});
	
	//加入收藏
	$("#collect").click(function(){
		alert("abc");
		collect("ptxx","http://www.pinton.com.cn");
	});
	
	// 退出系统
	$("#logout").click(function(){
		window.location.href="/pt2all/logout.jsp";
		
	});
	
	
	
	
	
	
	// 网址加入收藏
	function collect(sTitle,sURL){
		try{
			// IE
			window.external.addFavorite(sURL,sTile);
			alert("已收藏，请打开收藏查看！");
		}catch(e){
			try{
				//ff
				window.sidebar.addPanel(sTitle,sURL,"");
				alert("已收藏，请打开收藏查看！");
			}catch(e){
				alert("收藏失败！");
			}
		}
	}
	
});



