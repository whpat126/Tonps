<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta name="renderer" content="webkit" />
<meta name="keywords" content="平通,平通网,网址导航,网址大全,上网导航,portal,门户,云平台,信息平台,信息门户,导航,搜索,导航软件,信息集成,企业云,云服务,云" />
<meta name="description" content="平通网致力于打造企业及个人的信息门户，构建企业及个人的地址图谱，通过集成技术实现单点登录、信息集成，并与企业内部系统进行互通，实现信息共享，打造个人一个网页所有应用的平台(OPAA)" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="baidu-site-verification" content="GZU70K0VRo" />
<link rel="stylesheet" href="style/other/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="style/common/userCSS/base.css" />
<link rel='icon' href='style/common/img/fivelogo.ico' type='image/x-ico' />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript" src="style/other/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="style/other/bootstrap/bootstrap.min.js"></script>
<style type="text/css">
body {
	background-color: #f9f9f9;
	font-family: 'Microsoft Yahei', 'Arial';
}

#talkDiglog {
	min-width: 600px;
}

.msgPanel {
	height: 300px;
	overflow-y: scroll;
}

.ask {
	clear: both;
	float: right;
}

.ans {
	clear: both;
	float: left;
	position: relative;
	top: 20px;
}

.user {
	float: left;
}

.reply-content-box {
	float: left;
}

.reply-time {
	color: #e1912d;
	font-size: .85em;
	display: inline-block;
	width: 100%;
}

.ask .user {
	clear: both;
	float: right;
}

.user-name {
	color: #999;
	margin-top: 5px;
	display: block;
	text-align: center;
	width: 50px;
	white-space: nowrap;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis;
	overflow: hidden;
}

.avatar_ {
	border-radius: 50%;
	margin-top: 15px;
	opacity: 1;
	filter: alpha(opacity = 100);
	transition: all 0.3s ease-in 0s;
	-moz-transition: all 0.3s ease-in 0s;
	-webkit-transition: all 0.3s ease-in 0s;
	-o-transition: all 0.3s ease-in 0s;
}

.ask .reply-content {
	border: 1px solid #B4EB7C;
	background-color: #B4EB7C;
	box-shadow: 0 0 5px #B4EB7C;
}

.user:hover .avatar_ {
	border-radius: 0;
	transition: all 0.3s ease-in 0s;
	-moz-transition: all 0.3s ease-in 0s;
	-webkit-transition: all 0.3s ease-in 0s;
	-o-transition: all 0.3s ease-in 0s;
	opacity: .8;
	filter: alpha(opacity = 80);
}

.reply-content-box {
	margin: 0px auto;
	min-width: 60px;
	max-width: 400px;
}

.ask .reply-content-box {
	margin-right: 12px;
}

.ans .reply-content-box {
	margin-left: 12px;
}

.reply-content {
	border: 1px solid #fcfcfc;
	padding: 0.6em;
	background-color: #fcfcfc;
	border-radius: 4px;
	box-shadow: 0 0 5px #ccc;
	word-wrap: break-word;
	word-break: break-all;
	/* overflow: hidden;*/ /*这个参数根据需要来决定要不要*/
}

.ask .reply-time {
	text-align: right;
}

.ans .reply-time {
	text-align: left;
}

.ans .arrow {
	width: 0;
	height: 0;
	line-height: 0;
	font-size: 0;
	border-color: transparent #fcfcfc transparent transparent;
	border-width: 12px;
	border-style: dashed solid dashed dashed;
	display: block;
	position: absolute;
	top: 8px;
	left: -24px;
	z-index: 999;
}

.ask .arrow {
	width: 0;
	height: 0;
	line-height: 0;
	font-size: 0;
	border-color: transparent transparent transparent #B4EB7C;;
	border-width: 12px;
	border-style: dashed dashed dashed solid;
	display: block;
	position: absolute;
	top: 8px;
	right: -24px;
	z-index: 999;
}

.pr {
	position: relative;
}
</style>
<title>客服页面</title>
</head>
<!--使用图灵机器人API，需要到官网申请API KEY-->
<!--html代码-->
<body>
	<div id="talkDiglog">
		<div class="modal-header">
			<h3>
				机器人问答<span style="font-size:14px;">(欢迎你 <span class="text-info">${userName}</span>)
				</span>
			</h3>
		</div>
		<div class="modal-body">
			<ul class="nav nav-tabs" id="areaTab">
				<li class="active"><a data-toggle="tab">小i</a></li>
			</ul>
			<div class="tab-content">
				<div class="msgPanel tab-pane active" id="res" name="res"></div>
			</div>
		</div>
		<div class="modal-footer text-left">
			<div class="input-append input-prepend">
				<span class="add-on">@${userName}</span> <input style="width:400px" type="text" name="info" id="info" " />
				<button id="sendBtn" class="btn btn-primary" onclick="doSubmit()">发送</button>
			</div>
		</div>
	</div>


</body>

<!--JS代码-->

<script type="text/javascript">
	$(function() {
		$("#info").keydown(function(event) {
			if (event.keyCode == 13) {
				doSubmit();
			}
		})
	});

	function doSubmit() {
		var info = $.trim($('#info').val());
		//alert(info);
		if(info==''){
			return;
		}
		$('#info').val('');
		//alert(info);
		var myDate1 = new Date();
		var askinfo = "<div class='ask'>";
		askinfo += "<a class='user' href='#'><img class='img-responsive avatar_' src='style/cust/avatar.png' alt=''><span class='user-name'>${userName}</span></a>";
		askinfo += "<div class='reply-content-box'>";
		askinfo += "<span class='reply-time'>" + myDate1.toLocaleTimeString()
				+ "</span>";
		askinfo += "<div class='reply-content pr'>";
		askinfo += "<span class='arrow'>&nbsp;</span>";
		askinfo += info;
		askinfo += "</div></div></div>";
		$("#res").append(askinfo);
		var url = 'kefu.do';
		$
				.post(
						url,
						{
							info : info
						},
						function(data) {
							if (data != null) {
								//alert(data);
								var myDate = new Date();
								var replyinfo = "<div class='ans'>";
								replyinfo += "<a class='user' href='#'><img class='img-responsive avatar_' src='style/cust/avatar-1.png' alt=''><span class='user-name'>小i</span></a>";
								replyinfo += "<div class='reply-content-box'>";
								replyinfo += "<span class='reply-time'>"
										+ myDate.toLocaleTimeString()
										+ "</span>";
								replyinfo += "<div class='reply-content pr'>";
								replyinfo += "<span class='arrow'>&nbsp;</span>";
								replyinfo += data;
								replyinfo += "</div></div></div>";
								//$("#res").append("${userName}:"+data + "<br/>");
								$("#res").append(replyinfo);
							} else {
								$("#res").append('无返回值');
							}
							$("#res").get(0).scrollTop = $("#res").get(0).scrollHeight;
						});

	};
</script>
</body>
</html>