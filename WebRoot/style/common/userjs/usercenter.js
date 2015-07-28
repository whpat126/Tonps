
//右侧浮动框中回到顶部
function goTop(){
	$('html,body').animate({'scrollTop':0},600);
}
window.onscroll = function () {
	if (document.documentElement.scrollTop + document.body.scrollTop > 50) {
		document.getElementById("sidetop").style.display = "block";
	}
	else {
		document.getElementById("sidetop").style.display = "none";
	}
}
$(function() {
	$.ajax({
		type : "POST",
		async: false,
		url : "autoLogin.do",
		error : function(){alert("连接错误....");},
		success : function(data){
			if(data == "false"){ // 表示用户不存在
			}else{
				$("#userName").html(data);
				$("#login").attr("class","hidden"); // 显示用户按钮
				$("#userInfo").attr("class","dropdown visible");
			}
		}
	});
	
	var userName = $("#userName").html();
	if(userName=="" || userName == null){ // 即用户不存在
		// alert(userName);
		$("#userLogin").show(); // 登录按钮显示
	}else{ // 用户已经登录
		// $("#userLogin").hide(); // 登录按钮yincang
		$("#userInfo").show(); // 显示用户按钮
		$("#userSet").show();
	}
	
	// 当点击登录按钮时
	$("#loginButton").click(function(){

	});






});


