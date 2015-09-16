<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
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
	$(function(){
		// 右侧浮动框
		$(".side ul li").hover(function(){
			$(this).find(".sidebox").stop()
			.animate({"width":"124px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#ae1c1c"})
		},function(){
			$(this).find(".sidebox").stop()
			.animate({"width":"54px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#000"})
		});
	});
  
   // window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"slide":{"type":"slide","bdImg":"2","bdPos":"right","bdTop":"200"}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];

</script>

<!-- ptdh -->
<div class="row">
    <div class="navbar-fixed-top" style="position: fixed;top: 28px;height: 42px;">
        <div class="col-md-3 logo-bar navbar-header">
            <a href="http://www.ptsso.com"><img src="style/common/img/pingtong_web.png" width="170px" alt="平通网"></a>
           <!--  <iframe width="328" scrolling="no" height="30" frameborder="0" allowtransparency="true"
                    src="http://i.tianqi.com/index.php?c=code&id=40&icon=1&num=3"></iframe> -->
        </div>
        <!-- 导航栏 -->
        <div id="menu-Bar" class="col-md-8">
            <ul class="nav navbar nav-tabs  navbar-right">
                <li><a href="#" >首页</a></li>
                <li><a href="#">我的企业</a></li>
                <li><a href="#">我的应用</a></li>
                <li><a href="#">我的社区</a></li>
                <li><a href="#">帮助中心</a></li>
            </ul>
        </div>
        <!-- 未登录 -->
    </div>
</div>

<!-- 侧边回到页首和帮助 -->
   <div id="sidediv" class="side">
       <ul>
           <!--<li><a href="javascript:void(0);"><div class="sidebox"><img src="style/common/img/side_icon01.png">客服中心</div></a></li>-->
           <!--<li><a href="javascript:void(0);"><div class="sidebox"><img src="style/common/img/side_icon02.png">客户案例</div></a></li>-->
           <!--<li><a href="javascript:void(0);" ><div class="sidebox"><img src="style/common/img/side_icon04.png">QQ客服</div></a></li>-->
           <li class="sidebox"><a href="javascript:void(0);" class="sidebox"><img src="style/common/img/side_icon01.png" title="客户服务"></a></li>
           <li style="border:none;display: none;" id="sidetop"><a href="javascript:goTop();" class="sidetop"><img src="style/common/img/side_icon05.png" title="回到顶端"></a></li>
       </ul>
   </div>
