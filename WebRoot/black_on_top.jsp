<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!-- 最上方黑条 -->
	  	<div class="black_bar">
	 		<div class="black_bar_left" >
		  		<ul class="fl">
		  			<li  class="userSet"><a id="collect" href="javascript:;">加入收藏</a>
		  			</li>
		  			<li class="userSet"><a id="setIndex" href="javascript:void(0);">设为首页</a>
		  			</li>
		  			<li class="userSet"><a id="creDesktop" href="javascript:void(0);">创建桌面快捷</a>
		  			</li>
		  		</ul>
	 		</div>
	 		<!-- 退出系统 -->
	 		<div class="black_bar_right" >
	 			<!-- 如果session存在则显示用户名，鼠标划过，下方显示链接；不存在则显示登录 -->
	 			<div id="userInfo" style="display:none;">
		 			<ul class="fl" >
		 				<li ><span id="userName">${userName }</span>  </li>
		 			</ul>
	 			</div>
	 			<!-- 个人设置 -->
	 			<div id="userSet" style="display:none;">
	 				个人中心
	 				消息中心
	 				<a id="logout" href="javascript:;" title="退出系统">退出</a>
	 			</div>
	 		</div>
 		</div>