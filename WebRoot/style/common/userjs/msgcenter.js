
$(function(){
	// 页面加载默认显示未读消息
	$.ajax({
		type : "POST",
		url : "unreadMsg.do",
		success : function(msgdata){
			var data = eval("("+msgdata+")");
			var allMessageItem = $("#allMessageItem");
			
			$.each(data, function(i, value){
				allMessageItem.append("<li><div><a >"+value.contents+"</a></div></li>");
			});
		}	
	});
	// 点击全部消息时
	$("#allMessageDiv").click(function(){
		$("#allMessageItem li") .remove(); // 删除消息，避免用户多次重复点击内容重复增加的问题
		$.ajax({
			type : "POST",
			url : "unreadMsg.do",
			success : function(msgdata){
				var data = eval("("+msgdata+")");
				var allMessageItem = $("#allMessageItem");
				
				$.each(data, function(i, value){
					allMessageItem.append("<li><div><a >"+value.contents+"</a></div></li>");
				});
			}	
		});
	});
	
	// 点击未读消息
	$("#newMessageDiv").click(function(){
		var newMessageDiv = $("#newMessageDiv");
		$("#newMessageDiv li") .remove(); // 删除消息，避免用户多次重复点击内容重复增加的问题
		$.ajax({
			type : "POST",
			url : "newMsg.do",
			success : function(msgdata){
				var data = eval("("+msgdata+")");
				$.each(data, function(i, value){
					newMessageDiv.append("<li><div><a href="+ value.msg_href +">"+value.contents+"</a></div></li>");
				});
			}	
		});
		
	});
	
	
	
});