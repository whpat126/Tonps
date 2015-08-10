<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="renderer" content="webkit"/>
    <meta name="keywords" content="平通,平通网,网址导航,网址大全,上网导航,portal,门户,云平台,信息平台,信息门户,导航,搜索,导航软件,信息集成,企业云,云服务,云"/>
    <meta name="description" content="平通网致力于打造企业及个人的信息门户，构建企业及个人的地址图谱，通过集成技术实现单点登录、信息集成，并与企业内部系统进行互通，实现信息共享，打造个人一个网页所有应用的平台(OPAA)"/>
    <meta http-equiv="imagetoolbar" content="no"/>
    <link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="style/common/userCSS/base.css"/>
    <link rel='icon' href='style/common/img/2.ico' type='image/x-ico'/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="style/common/userjs/index.js"></script>
    <style>
	#ul1 {width:880px; position:relative; margin:10px auto;}
	#ul1 li {width:200px; height:150px; float:left; list-style:none; margin:10px;}
	#ul1 li:hover{ border-color: #9a9fa4; box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.85);}
	#ul1 .active{ border:1px dashed red;}
	
    </style>

    <title>企业云信息门户（SSO）,网址导航,打造自己的独立桌面-平通网</title>
</head>
<body style="background-color: white">
<div  class="navbar-fixed-top" style="height: 106px;position: fixed;background-color: white"></div>
<div class="container">
    <!-- 导航条-->
    <div><%@ include file="ptdh.jsp" %></div>
    <!-- 最上方黑条  style="display:none;" -->
    <div><%@ include file="black_on_top.jsp" %></div>

    <!-- 搜索引擎 -->
    <div><%@ include file="ptss.jsp" %></div>
    <div id="funcMenu" class="appclass ">
        <!-- 后台生成，此处先写出来 -->
        <ul class="">
            <li class="btn-info">酷&nbsp;&nbsp;&nbsp;&nbsp;站</li>
            <li class="btn-info">爱常用</li>
            <li class="btn-info">爱工作</li>
            <li class="btn-info">爱生活</li>
            <li class="btn-info">平通云</li>
        </ul>
    </div>

    <!-- 主要内容展示区 -->
    <div>
        <!-- 未登录用户使用，由系统推送 -->
        <div style="position: relative;top: 180px;left: 80px;height:500px;">
            <div class="col-md-12">
            <div id="Container">
           		<div>
					<ul id="ul1">
					</ul>
					</div>
				<!-- +号，用于添加新的地址 -->
				<div><span id="addIcon" style="font-size: 30px">+</span></div>
				
				 <!-- 地址模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="modalTitle">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
					</div>
					<div class="modal-body">
						<span id="msg"></span>
					    <form action="" method="post">
					    	名称:<input type="text" id="iconname" /><br/>
					    	地址:<input type="text" id="iconaddress" /><br/>
						<div class="modal-footer">
							<input id="iconButton" class="btn primary" type="button" value="确定" />
						</div>
						</form>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
           	</div>
            </div>
<!--            <div class="co-md-6">
                <iframe src="http://news.sina.com.cn/" width="560px" height="1000px"></iframe>
                &lt;!&ndash;<img src="style/cust/sss.png" alt=""/>&ndash;&gt;
            </div>-->

<!--            <div class="media" style="position: absolute;top: 200px">
                <div id="none_login" >
                    <a href="http://www.ptsso.com" class=""><img src="style/cust/01.png" class="media-object" alt="平通网"></a>

                </div>
            </div>-->
        </div>

        <!-- 爱常用中内容 -->
        <div id="love_common" style="display:none;">

        </div>
        <div id="love_work" style="display:none;"></div>
        <div id="love_life" style="display:none;"></div>
        <div id="pt_yun" style="display:none;"></div>
    </div>
    
</div>

<!-- 登录的model -->
<%@ include file="login.jsp" %>

</body>
</html>
