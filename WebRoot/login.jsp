<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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