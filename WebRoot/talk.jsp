<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/"%>">
<meta charset="utf-8">
<title>聊天室</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<script src="style/other/ajax-pushlet-client.js"></script>
<style type="text/css">
.msgPanel {
	height: 300px;
	overflow-y: scroll;
}
</style>
</head>
<div id="talkDiglog" class="modal hide fade">
	<div class="modal-header">
		<h3>
			聊天室 <span style="font-size:14px;">(欢迎你 <span class="text-info">${userName}</span>)
			</span>
		</h3>
	</div>
	<div class="modal-body">
		<ul class="nav nav-tabs" id="areaTab">
			<li class="active"><a href="#wordMsg" data-toggle="tab">世界</a></li>
		</ul>
		<div class="tab-content">
			<div class="msgPanel tab-pane active" id="wordMsg"></div>
		</div>
	</div>
	<div class="modal-footer text-left">
		<div class="input-append input-prepend">
			<span class="add-on">@世界</span> <input style="width:400px" type="text" name="message" />
			<button id="sendBtn" class="btn btn-primary">发送</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#talkDiglog").modal({
			backdrop : "static"
		});

		PL.webRoot = "./";
		PL._init();
	/* 	PL.joinListen('/message/world'); */

		$("#sendBtn").click(function() {
			var msg = $("input[name='message']").val();
		/* 	$.post("./talkService.srv", {
				action : "sendMsg",
				message : msg
			}, function() {
				$("input[name='message']").val("");
			}); */
		});
	});

	function onData(event) {
		var message = event.get("message");
		var userName = event.get("userName");
		if (message != null && "" != message) {
			$("#wordMsg").append(
					"<div class='text-success message'><span class='text-info'>【"
							+ userName + "】说：</span>" + message + "</div>");
		}
	}
</script>
</body>
</html>