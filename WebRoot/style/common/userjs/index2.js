
$(function() {
	$.ajax({
		type : "POST",
		url : "userLogin.do",
//		error : function(){alert("连接错误....");},
		success : function(data){
			
		}
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



