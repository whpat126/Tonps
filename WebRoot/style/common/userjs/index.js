//回到顶部
function goTop(){
	$('html,body').animate({'scrollTop':0},600);
}
window.onscroll = function () {
	if (document.documentElement.scrollTop + document.body.scrollTop > 500) {
		document.getElementById("sidediv").style.display = "block";
	}
	else {
		document.getElementById("sidediv").style.display = "none";
	}
}

$(function() {
	$(".side ul li").hover(function(){
		$(this).find(".sidebox").stop().animate({"width":"124px"},200).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#ae1c1c"})	
	},function(){
		$(this).find(".sidebox").stop().animate({"width":"54px"},200).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#000"})	
	});
	
});



