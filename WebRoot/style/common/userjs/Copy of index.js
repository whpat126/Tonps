$(function(){
	// 页面加载显示所有图标
	$.ajax({
		type : "POST",
		url : "IconInit.do",
		success : function(iconData){
			var data = eval("("+iconData+")");
			var $add = $("#ul1");
//			[{"name":"a","param_id":"1","param_title":"百度","param_href":"https://www.baidu.com"},{"name":"a","param_id":"2","param_title":"百度","param_href":"https://www.baidu.com"}]
			$.each(data, function(i, value){
				$add.append("<li id="+value.param_id+"><div> " +
						"<a href='"+value.param_href+"' title='"+value.param_title +"' target='_blank'> " +
						"<img src='"+value.icon_path+"' width='200' height='150' ></img> " +
						"<em>"+value.param_title+"</em></a></div> " +
						"<a id='del"+value.param_id+"'>del</a> " +
						"<a id='edit"+value.param_id+"'>edit</a> " +
						"</li>");
			});
			initSet();
		}
	});
	
	
	
	
	
	
});


/********************************************************
	this is drag!
********************************************************/
	function initSet() {
		var oUl= document.getElementById("ul1");//ul1元素
		var aLi = oUl.getElementsByTagName("li");//li元素
		var disX = 0;
		var disY = 0;
		var minZindex = 1;//层数
		var aPos =[];
		var ids=[];
		for(var i=0;i<aLi.length;i++){//li元素位置赋值
			var t = aLi[i].offsetTop;
			var l = aLi[i].offsetLeft;
			aLi[i].style.top = t+"px";
			aLi[i].style.left = l+"px";
			aPos[i] = {left:l,top:t};
			aLi[i].index = i;
			aLi[i].setAttribute("canPZ",1);//1代表 是可以参与计算是否碰撞
			
		}
		for(var i=0;i<aLi.length;i++){
			aLi[i].style.position = "absolute";
			aLi[i].style.margin = 0;
			setDrag(aLi[i]);//为每个元素注册拖拽事件
		}
		//拖拽事件的定义
		function setDrag(obj){
			obj.onmouseover = function(){//鼠标移动至上方
				obj.style.cursor = "pointer";
			
			}
			obj.onmousedown = function(event){//鼠标按下 onmousedown ondragstart
				var event = window.event || event;
				// 记录鼠标按下的位置
				var scrollTop = document.documentElement.scrollTop||document.body.scrollTop;
				var scrollLeft = document.documentElement.scrollLeft||document.body.scrollLeft;
				obj.style.zIndex = minZindex++;
				//当鼠标按下时计算鼠标与拖拽对象的距离，即鼠标所在对象的位置（x,y）
				disX = event.clientX +scrollLeft-obj.offsetLeft; // obj.offsetXXX指obj相对上层对象的位置
				disY = event.clientY +scrollTop-obj.offsetTop;
				/*mouse_down记录鼠标的坐标,mouse_up时如果鼠标没有移动过就响应click事件.*/
				
				// 计算鼠标按下时的位置
				var mouseDownTop = event.clientX;
				var mouseDownLeft = event.clientY;
//				console.log(scrollTop)
				document.onmousemove=function(event){
					var event = window.event || event;
					//当鼠标拖动时计算div的位置
					var l =  event.clientX -disX +scrollLeft;
					var t =  event.clientY -disY + scrollTop;
					obj.style.left = l + "px";
					obj.style.top = t + "px";
					for(var i=0;i<aLi.length;i++){
						aLi[i].className = "";
					}
					var oNear = findMin(obj);	
					if(oNear){ //只要不是0，null，undefined，false，都会被认为true
					//console.log(oNear.index+":"+oNear.timer);
						oNear.className = "active";
						//-----------
						if(obj.index <oNear.index){//从小到大
							var temobj=obj;
							for(var i=obj.index; i<oNear.index;i++){
								for(var ii=0;ii<aLi.length;ii++){//li元素位置赋值
									if(aLi[ii].index == i+1){
										temobj=aLi[ii];
										break;
									};
								}
								temobj.className = "";
								temobj.style.zIndex = minZindex++;
								obj.style.zIndex = minZindex++;
								startMove(temobj,aPos[i]);
								
								temobj.index -= 1;
								obj.index +=1;
							}
						}else{//从大到小
							var temobj=obj;
							for(var i=obj.index; i>oNear.index;i--){
								for(var ii=0;ii<aLi.length;ii++){//li元素位置赋值
									if(aLi[ii].index == i-1){
									temobj=aLi[ii];
									break;
									};
								}
								temobj.className = "";
								temobj.style.zIndex = minZindex++;
								obj.style.zIndex = minZindex++;
								startMove(temobj,aPos[obj.index]);
								temobj.index += 1;//交换index
								obj.index -= 1;
							}
						}
						//-----------
					}
				}
				document.onmouseup = function(event){//鼠标按下后抬起 onmouseup ondrop
					document.onmousemove = null;//当鼠标弹起时移出移动事件
					document.onmouseup = null;//移出up事件，清空内存
					
					var mouseUpTop = event.clientX;
					var mouseUpLeft = event.clientY;
					// alert(mouseDownTop + ", " + mouseUpTop);
					
					if(mouseDownTop == mouseUpTop && mouseDownLeft == mouseUpLeft){
						//console.log(obj.href);
						obj.removeAttribute("onClick");
						obj.href="http://www.163.com";
					}else{
						obj.setAttribute("onClick","return false");
					}
					
					//检测是否碰上，再交换位置
					var oNear = findMin(obj);
					if(oNear){
					//----------
								oNear.className = "";
								oNear.style.zIndex = minZindex++;
								obj.style.zIndex = minZindex++;
								startMove(oNear,aPos[obj.index]);
								startMove(obj,aPos[oNear.index]);
						
								var tempindex=obj.index;
								obj.index = oNear.index;
								oNear.index = tempindex;
					//-----------
						
					}else{
						startMove(obj,aPos[obj.index]);
					}
				
				}
				
				clearInterval(obj.timer);
				return false;//低版本出现禁止符号
			}
			
			
		
			
			
			
		}
		//碰撞检测
		function colTest(obj1,obj2){		
			var t1 = obj1.offsetTop;
			var r1 = obj1.offsetWidth+obj1.offsetLeft;
			//var b1 = obj1.offsetHeight+obj1.offsetTop;
			//var l1 = obj1.offsetLeft;

			var t2 = obj2.offsetTop;
			var r2 = obj2.offsetWidth+obj2.offsetLeft;
			//var b2 = obj2.offsetHeight+obj2.offsetTop;
			//var l2 = obj2.offsetLeft;
			//对象的x边距相差80px时,y边距相差70px,才开始考虑是否碰撞
			//避免抖动 disx和disy要比对象的宽度200和高度150的1/2要小即可
			var disx=80;var disy=70;
			//obj2.getAttribute("canPZ")==0 此处是为了避免抖动现象,移动中的对象真正停止之后才计算是否碰撞
			if(obj2.getAttribute("canPZ")==0||Math.abs(t1-t2)>disy||Math.abs(r1-r2)>disx){
				return false;
			}else{
			//console.log(obj2.index+":"+obj2.getAttribute("canPZ"));
				return true;
			}
		}
		//勾股定理求距离
		function getDis(obj1,obj2){
			var a = obj1.offsetLeft-obj2.offsetLeft;
			var b = obj1.offsetTop-obj2.offsetTop;
			return Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
		}
		//找到距离最近的
		function findMin(obj){
			var minDis = 999999999;
			var minIndex = -1;
			for(var i=0;i<aLi.length;i++){
				if(obj==aLi[i])continue;
				if(colTest(obj,aLi[i])){
					var dis = getDis(obj,aLi[i]);
					if(dis<minDis){
						minDis = dis;
						minIndex = i;
					}
				}
			}
			if(minIndex==-1){
				return null;
			}else{
				return aLi[minIndex];
			}
		}
		/************************
		 * 
		 * 点击时-------
		 * 
		 ************************/ 
		$(document).click(function (e) { 
			var	v_id = $(e.target).attr('id'); 
			if(v_id == null) return; // 避免v_id为null时，firebug报错
			if(v_id.substring(0,3) == "del") { // 
				var obj_child = $("#"+v_id)[0]; 
				var objIndex = $(obj_child).parent()[0].index;
				$(obj_child).parent().remove();
				// 删除之后，后面的内容向前移动一个单元
				console.log("objIndex:      "+objIndex);
				
				var temobj;
				for(var i=objIndex; i<aLi.length;i++){
					for(var j=0;j<aLi.length;j++){//元素位置赋值
						if(aLi[j].index == i+1){
							temobj=aLi[j];
							break;
						};
					}
					temobj.className = "";
					startMove(temobj,aPos[i]);
					temobj.index -= 1;
				}
				
				// 得到当前的排序
			/*	var currentSort = new Array();
				var sortString ="";
				console.log("aLi.length:      "+aLi.length);*/
				
				/*for(var i=0;i<aLi.length;i++){
					//-----------
					for(var j=0;j<aLi.length;j++){//元素位置赋值
						if(aLi[j].index == i+1){
							currentSort.push(aLi[j].id);
						};
					}
					
					//----------
					//currentSort.push(aLi[i].id);
					console.log("aLi["+i+"].id:      "+aLi[i].id);
					sortString += "," + currentSort[i];
				}*/
				/*sortString = sortString.substr(1);*/
				
				//获取最后的顺序输出给后台 开始
				var sortString="";
				for (var iindex = 0; iindex < aLi.length; iindex++) {
					// console.log("aLi["+iindex+"]     【"+aLi[iindex].index);
					for (var j = 0; j < aLi.length; j++) { //元素位置赋值
						if (aLi[j].index == iindex) {
							//ids[iindex]=aLi[j].id;
							sortString+=","+aLi[j].id;
							//console.log("index为["+iindex+"]的obj的id为  【"+aLi[j].index+"】id为   【"+aLi[j].id);
							break;
						};

					}
				}
				sortString=sortString.substr(1);
//				console.log("传给后台："+sortString);
				//获取最后的顺序输出给后台 结束
				
				$.ajax({
					type : "POST",
					url : "IconUpdate.do",
					data : { "sortString":sortString },
					success : function(iconData){
					}
				});
			}
			if(v_id.substring(0,3) == "edit"){
				
			}

				
		});
		
		
		
	}

	//通过class获取元素
	function getClass(cls){
	    var ret = [];
	    var els = document.getElementsByTagName("*");
	    for (var i = 0; i < els.length; i++){
	        //判断els[i]中是否存在cls这个className;.indexOf("cls")判断cls存在的下标，如果下标>=0则存在;
	        if(els[i].className === cls || els[i].className.indexOf("cls")>=0 || els[i].className.indexOf(" cls")>=0 || els[i].className.indexOf(" cls ")>0){
	            ret.push(els[i]);
	        }
	    }
	    return ret;
	}
	function getStyle(obj,attr){//解决JS兼容问题获取正确的属性值
		return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj,false)[attr];
	}
	function startMove(obj,json){
		clearInterval(obj.timer);
		obj.setAttribute("canPZ",0);
		obj.timer = setInterval(function(){
			var isStop = true;
			for(var attr in json){
				var iCur = 0;
				//判断运动的是不是透明度值
				if(attr=="opacity"){
					iCur = parseInt(parseFloat(getStyle(obj,attr))*100);
				}else{
					iCur = parseInt(getStyle(obj,attr));
				}
				var ispeed = (json[attr]-iCur)/8;
				//运动速度如果大于0则向下取整，如果小于0想上取整；
				ispeed = ispeed>0?Math.ceil(ispeed):Math.floor(ispeed);
				//判断所有运动是否全部完成
				if(iCur!=json[attr]){
					isStop = false;
				}
				//运动开始
				if(attr=="opacity"){
					obj.style.filter = "alpha:(opacity:"+(json[attr]+ispeed)+")";
					obj.style.opacity = (json[attr]+ispeed)/100;
				}else{
					obj.style[attr] = iCur+ispeed+"px";
				}obj.style[attr] 
				//console.log(obj.index+":"+obj.style[attr] );
			}
			//判断是否全部完成
			//console.log("isStop:"+isStop );
			//console.log("canPZ:"+canPZ );
			if(isStop){
				obj.setAttribute("canPZ",1);;
				clearInterval(obj.timer);		
			}
		},30);
	}
	
	
