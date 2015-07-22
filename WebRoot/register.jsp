<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>平通云应用用户注册</title>
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
   	<script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script> 
	<script>
		$(function () { 
			$('#myModal').modal({backdrop:"static", keyboard:false, show:false});
		});
	</script>

  </head>
  
  <body>
  <!-- 点击后调用下面的model，登录 -->
  <div id="refLogin">已有账号，直接<button class="btn btn-primary" data-toggle="modal" data-target="#myModal">登录</button></div>
  <!-- 用户注册页面，该页面必须存在，因为登录就是一个弹窗了 -->
  <div>
  	<!-- 注册 -->
  	${msg }
    <form action="userRegister.do" method="post">
    	手机/邮箱:<input type="text" name="username" /><br/>
    	密码:<input type="password" name="password" /><br/>
    	确认密码:<input type="password" name="repassword" /><br/>
    	<input type="checkbox" name="agree" checked="checked" /><span>阅读并接受<a href="statement.html">《平通用户协议》</a></span><br/>
    	<input type="submit" value="注册" />
    </form>
    
    <!-- 登录模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="modalTitle">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
					</div>
					<div class="modal-body">
						${msg }
					    <form action="userLogin.do" method="post">
					    	手机/邮箱:<input type="text" name="username" /><br/>
					    	密码:<input type="password" name="password" /><br/>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary">登录</button>
						</div>
						</form>
					   
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
    
    
    
    
    
  </div>
  <!-- 社交工具登录 -->
  <div>
  	
  </div>
  </body>
</html>
