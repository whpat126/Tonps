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

</SCRIPT>
  <!--  <div class="col-md-12">
        <div class="black_bar navbar navbar-default navbar-fixed-top navbar-inverse nav" style="height: 28px;">
                 <div class="black_bar_left col-md-6">
                    <ul class="fl nav nav-tabs">
                        <li class="userSet"><a onClick="SetHome('http://ptsoo.com')" href="javascript:void(0)"
                                               title="设为首页"
                                               class="firstpage">设为首页</a>
                        </li>
                        <li class="userSet"><a onClick="AddFavorite('http://ptsoo.com','平通网')" href="javascript:void(0)"
                                               title="加入收藏" class="r">加入收藏</a>
                        </li>
                        <li class="userSet"><a id="creDesktop" href="javascript:void(0);">创建桌面快捷</a>
                        </li>
                    </ul>
                </div>

                &lt;!&ndash; 退出系统 &ndash;&gt;
                <div class="black_bar_right col-md-6 nav navbar navbar-right" >
                    &lt;!&ndash; 如果session存在则显示用户名，鼠标划过，下方显示链接；不存在则显示登录 &ndash;&gt;
                    <div id="userInfo" class="col-md-1 nav navbar navbar-right" style="display:block;">
                        <ul class="fl">
                            <li><span id="userName">${userName }用户</span></li>
                        </ul>
                    </div>
                    &lt;!&ndash; 个人设置 &ndash;&gt;
                    <div id="userSet" class=" col-md-5 nav navbar navbar-right" style="display:block;">
                        <ul class="nav nav-tabs">
                            <li><a href="#">个人中心</a></li>
                            <li><a href="#">消息中心</a></li>
                            <li><a id="logout" href="javascript:;" title="退出系统">退出</a></li>
                        </ul>
                    </div>
                </div>
             </div>
    </div>
-->
