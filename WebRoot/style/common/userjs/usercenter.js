
$(function(){
	// 点击修改密码
	$("#modifyPwd").click(function(){
		if($("#modifyPwddiv").is(":hidden"))
			$("#modifyPwddiv").css("display","block"); // 除使用.show()方法外的另一种写法
		else{
			$("#modifyPwddiv").css("display","none");
			location.replace(location);
		}
	});
	// 点击邮箱验证提交按钮
	$("#emailVerify").click(function(){
		if($("#emailVerifydiv").is(":hidden")){
			// 异步交互拿到邮箱内容
			$.ajax({
				type : "POST",
				url : "queryEmail.do",
				success : function(data){
					if("false" != data){
						$("#emailContent").val(data).attr("disabled","disabled");
						$("#evdmsg").html("您的邮箱已经验证通过");
					}else{
						$("#emailContent").val("");
					}
				}
			});
			$("#emailVerifydiv").show(1000);
		}else{
//			$("#emailVerifydiv").css("display","none");
			location.replace(location);
		}
	});
	// 点击密码问题
	$("#Security").click(function(){
		// 异步交互判断用户是否设置过密码问题
		$.ajax({
			type : "POST",
			url : "judgeUserQuestion.do",
			success : function(Jsondata){
				//此处判断json的个数，如果是一个则说明是设置过
				var data = eval("("+Jsondata+")");
				if(data.length>1){ 
					$("#noSecuritydiv").show();
					$("#nopwdquestion option").remove();
					$.each(data, function(i, value){
						$("#nopwdquestion").append("<option value='"+value.pk_pwdq+"'>"+value.pwdq+"</option>");
					});
				}else{
					$("#Securitydiv").show();
					$("#yesoldpwdquestion option").remove();
					$("#yesoldpwdquestion").append("<option value='"+data.pk_pwdq+"'>"+data.pwdq+"</option>");
				}
			}
		});
	});
	
	// 点击选择新问题，异步交互取得新问题 
	$("#yesnewpwdquestion").focus(function(){
		$.ajax({
			type : "POST",
			url : "QuestionList.do",
			success : function(Jsondata){
				var data = eval("("+Jsondata+")");
				if(data.length>1){ 
					$("#yesnewpwdquestion option").remove();
					$.each(data, function(i, value){
						$("#yesnewpwdquestion").append("<option value='"+value.pk_pwdq+"'>"+value.pwdq+"</option>");
					});
					
				}
			}
		});
	});
	
	// 未设置密码问题，点击提交按钮
	$("#nopwdbtn").click(function(){
		var nopwdQ = $("#nopwdquestion").val(); 
		var nopwdA = $("#nopwdanswer").val();
		var $nsmsg = $("#noSecuritymsg");
		if(nopwdA == "") {$nsmsg.html("必须填写");return false;}
		$.ajax({
			type : "POST",
			data : {"nopwdQ":nopwdQ, "nopwdA":nopwdA},
			url : "addSecurity.do",
			success : function(data){
				if("true" == data){
					alert("通过，确定后刷新页面，即可查看效果");
					location.replace(location);
				}else
					$nsmsg.html("出错了,尝试刷新页面再验证吧");
			}
		});
		
	});
	
	// 设置过密码问题，点击提交按钮
	$("#yespwdbtn").click(function(){
		var opwdq = $("#yesoldpwdquestion").val();
		var opwda = $("#yesoldpwdanswer").val();
		var npwdq = $("#yesnewpwdquestion").val();
		var npwda = $("#yesnewpwdanswer").val();
		var $sdmsg = $("#sdmsg");
		if(opwda == "") {$sdmsg.html("答案不能为空");return false;}
		if(npwda == "") {$sdmsg.html("答案不能为空");return false;}
		$.ajax({
			type : "POST",
			data : {"opwdq" : opwdq, "opwda" : opwda, "npwdq":npwdq,"npwda":npwda},
			url : "validatePwdQuestion.do",
			success : function(data){
				if("false" == data){
					$sdmsg.html("旧密保答案错误");
				}else if("success" == data){
					alert("修改成功！");
					location.replace(location);
				}else{
					$sdmsg.html("修改失败，请尝试刷新本界面重新保存");
				}
			}
		});
	});
	
	
	
	//修改用户密码
	$("#modifyPwdbtn").click(function(){
		var opwd = $.trim($("#oldpassword").val());
		var pwd = $.trim($("#new1password").val());
		var npwd = $.trim($("#new2password").val());
		if(opwd == "") {$("#mpdmsg").html("密码不能为空");return false;}
		if(pwd == "") {$("#mpdmsg").html("密码不能为空");return false;}
		if(pwd != npwd) {$("#mpdmsg").html("密码不一致");return false;}
		$.ajax({
			type : "POST",
			data : {"opwd" : opwd, "pwd" : pwd},
			url : "modifyPwd.do",
			success : function(data){
				if("false" == data){
					$("#mpdmsg").html("旧密码不正确");
				}else if("success" == data){
					$("#mpdmsg").html("修改成功");
					location.replace(location);
				}else{
					$("#mpdmsg").html("修改失败");
				}
			}
		});
	});
	
	
	// 邮箱验证
	$("#emailVerifybtn").click(function(){
		var email = $.trim($("#emailContent").val());
		var $msg = $("#evdmsg");
		var emailFormat = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
		if(email == "") {$msg.html("邮箱必须填写");return false;}
		if((!email.match(emailFormat)))	{$msg.html("邮箱格式不正确");return false;}
		$.ajax({
			type : "POST",
			data : {"email" : email},
			url : "validateEmail.do",
			success : function(data){
				if("false" == data){
					$("#evdmsg").html("邮件发送失败，请确认您邮箱的正确性");
				}else{
					$("#evdmsg").html("发送成功,请进入邮箱确认");
				}
			}
		});
	});
	
});