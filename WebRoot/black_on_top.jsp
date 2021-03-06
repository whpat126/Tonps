<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript" src="style/other/comet4j.js"></script>
<!-- 最上方黑条 -->
<SCRIPT type="text/javascript">
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
		// 页面加载拉取消息个数
		var kbDom = document.getElementById("message");
		var userName = document.getElementById("userName");
        /* JS.Engine.on({
			"songqiupdate" : function(data){//侦听一个channel
            	kbDom.innerHTML = data;
			}
        });
        //JS.Engine.start("comm"); 
        
        JS.Engine.on(
        	'start',function(cId, channelList, engine){
	    		$.ajax({
					type : "POST",
					url : "AddIds.do",
					data : { "uname":userName.innerHTML, "cId":cId },
					success : function(data){
						 
					}
				});
        });
        JS.Engine.start("comm"); */
		// 鼠标划过该区域，显示消息的标题和时间
		$("#message").hover(function(){
			$.ajax({
				type : "POST",
				url : "msgList.do",
				success : function(msgData){
					var data = eval("("+msgData+")");
					var contents = "";
					
					for(var i=0 ; i<data.length; i++){
						contents += "<li><a href=>"+"</a></li>";
					}
					
					$("#msgList").append(contents);
				}
			});
		});
		
		// 模态框设置
		$('#myModal').modal({backdrop:"static", keyboard:false, show:false});
		$("#login").click(function(){
			$('#myModal').modal({backdrop:"static", keyboard:false, show:true});
		});
		// 退出系统
		$("#logout").click(function(){
			window.location.href="logout.jsp";
		});
		// 鼠标划过用户名自动弹出个人中心和退出
		dropdownOpen();
		function dropdownOpen() {
		    var $dropdownLi = $('li.dropdown');
		    $dropdownLi.mouseover(function() {
		        $(this).addClass('open');
		    }).mouseout(function() {
		        $(this).removeClass('open');
		    });
		}
		
		
		
		
		
		
		
    });
    
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
                    <li><a href="#"><span class="glyphicon glyphicon-envelope" style="color: red;"></span>
                    	消息(<span id="message" style="color: red">5</span>)</a>
                    	<ul id="msgList"></ul>	
                    
                    </li>
                    <li>&nbsp;</li>
                    <li><a id="login" href="javascript:void(0);" class="visible">登录</a></li>
                    <li id="userInfo" class="dropdown hidden">
						<a href="javascript:void(0);" id="userDown" class="dropdown-toggle" data-toggle="dropdown">
						<span id="userName"  style="color: white;"></span><span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
						    <li id="user-center"><a id="usercenter" target="_blank" href="${pageContext.request.contextPath }/menu_center.jsp" >个人中心</a></li>
						    <li id="quit"><a id="logout" href="javascript:;" title="退出系统">退出账号</a></li>
						</ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
