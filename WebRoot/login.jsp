<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){

		$.ajax({
			type : "POST",
			async: false,
			url : "autoLogin.do",
			error : function(){alert("连接错误....");},
			success : function(data){
				if(data == "false"){ // 表示用户不存在
//					alert("aaaa")
//					$("#login").attr("class","visible");
				}else{
					$("#userName").html(data);
					$("#login").attr("class","hidden"); // 显示用户按钮
					$("#userInfo").attr("class","dropdown visible");
//					$("#userSet").show();
				}
			}
		/*
		 * error:function(data){ alert("获得用户信息失败，但不影响系统业务的使用。"); }
		 */
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
			var username = $.trim($("#username").val());
			var password = $.trim($("#password").val());
			var remeber = $("#remeber").is(":checked"); // 判断checkbox是否被选中
			if(username == "") {$("#msg").html("用户名不能为空");return false;}
			if(password == "") {$("#msg").html("密码不能为空");return false;}
			$.ajax({
				type : "POST",
				async: false,
				url : "userLogin.do",
				data : { "username" : username, "password" : password, "remeber" :remeber },
				/* error : function(){alert("获得用户信息失败，但不影响系统业务的使用。");}, */
				success : function(data){
					if(data == "false"){
						$("#msg").html("用户名或密码不正确");
					}else{
						$("#userName").html(data); // 显示用户姓名
						// $("#userInfo").show(); // 显示用户按钮
						// $("#userSet").show(); // 用户设置
						location.replace("index.jsp");
					}
				}
			});

		});

	//模态框设置
	$('#myModal').modal({backdrop:"static", keyboard:false, show:false});
	$("#login").click(function(){
		$('#myModal').modal({backdrop:"static", keyboard:false, show:true});
	});
});
</script>

 <!-- 登录模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="modalTitle">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
					</div>
					<div class="modal-body">
						<span id="msg"></span>
						${msg }
					    <form action="userLogin.do" method="post">
					    	手机/邮箱:<input type="text" id="username" name="username" /><br/>
					    	密码:<input type="password" id="password" name="password" /><br/>
							<input type="checkbox" id="remeber" name="remeber" checked /> 7日内免登录 <a href="javascript:;">忘记密码</a>
						<div class="modal-footer">
							<button id="loginButton" type="button" class="btn btn-primary">登录</button>
						</div>
						<!-- 注册 -->
						<div><a href="${pageContext.request.contextPath }/register.jsp" target="_blank">9秒注册</a></div>
						<!-- 社交工具登录 -->
						<div id="otherLogin">
							<span>其他登录：</span><br/>
							<a id="login_qq" href="qqLogin.do"><img src="${pageContext.request.contextPath }/style/common/userCSS/img/Connect_logo_1.png"></a> 
						</div>
						</form>
					   
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->