<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 最上方黑条 -->
<SCRIPT language="javascript">
    //加入收藏
    function AddFavorite(sURL, sTitle) {
        sURL = encodeURI(sURL);
        try {
            window.external.addFavorite(sURL, sTitle);
        } catch (e) {
            try {
                window.sidebar.addPanel(sTitle, sURL, "");
            } catch (e) {
                alert("加入收藏失败,请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
            }
        }
    }
    //设为首页
    function SetHome(url) {
        if (document.all) {
            document.body.style.behavior = 'url(#default#homepage)';
            document.body.setHomePage(url);
        } else {
            window.location.replace("sethome/sethome.html");
//			alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
        }
    }

    $(function () {
    	$("#userDown").hover(function(){
    		$(this).dropdown("toggle");
    	});
    	$(".dropdown-menu").hover(function(){
    		$(this).dropdown("toggle");
    	});
    })

</SCRIPT>
<div class="row">
    <div class="black-bar navbar-fixed-top navbar-inverse" style="height: 28px;">
        <div class="black_bar_left col-sm-6">
            <ul class="nav-pills nav-tabs navbar-inverse">
                <li class="userSet"><a onClick="SetHome('http://ptsoo.com')" href="javascript:void(0)"
                                       title="设为首页" class="first-page">设为首页</a>
                </li>
                <li class="userSet"><a onClick="AddFavorite('http://ptsoo.com','平通网')" href="javascript:void(0)"
                                       title="加入收藏">加入收藏</a>
                </li>
                <li class="userSet"><a id="creDesktop" href="javascript:void(0);">桌面快捷方式</a>
                </li>
            </ul>
        </div>
        <!-- 退出系统 -->
        <div class="col-sm-6">
            <!-- 如果session存在则显示用户名，鼠标划过，下方显示链接；不存在则显示登录 -->
            <!-- 个人设置 -->
            <div id="userSet">
                <ul class="userSet nav-pills nav-tabs navbar-inverse navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-envelope" style="color: red;"></span>消息(<span style="color: red">5</span>)</a></li>
                    <li>&nbsp;</li>
                    <li><a id="login" href="javascript:void(0);" class="visible">登录</a></li>
                    <li id="userInfo" class="dropdown hidden">
						<a href="javascript:void(0);" id="userDown" class="dropdown-toggle" data-toggle="dropdown"><span id="userName"  style="color: white;"></span><span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
						    <li id="user-center"><a href="javascript:void(0);">个人中心</a></li>
						    <li id="quit"><a id="logout" href="javascript:;" title="退出系统">退出账号</a></li>
						</ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
