
$(function(){
	
	document.onkeydown=function(e){
			var currKey=0,e=e||event;
		    currKey=e.keyCode||e.which||e.charCode;
		    if(currKey==13){
		    	//回车按键
		    	submitForm();
		    }
		}
		
	checkBrowser();
	$("#loginBtn").click(function(){
		submitForm();
	});
	
	$("#vercode").click(function(){
		var $img=$("#img_vercode");
		
		if(!$img.attr("src")){
			$img.attr("src","sy_login/imgNum.do");
		}
	});
		
		
});
//点击登陆按钮
function submitForm(){
	if(checkBrowser())return false;
	var name=$("#username").val();
	var pwd=$("#pwd").val();
	var yzm=$("#vercode").val();
	if(name.length==0){
		$.dialog( {
			id:1,
			title : "提示",
			content :"用户名不能为空",
			icon:"warning"
		
		});
		
		return;
	}else if(pwd.length==0){
		$.dialog( {
			id:1,
			title : "提示",
			content :"密码不能为空",
			icon:"warning"
		
		});
		return;
	}
	/*else if(yzm.length==0){
		$.dialog( {
			id:1,
			title : "提示",
			content :"验证码不能为空",
			icon:"warning"
		
		});
		return;
	}*/
	else{
		
		$.ajax({
			url:"sy_login/getEncryption.do",
			cache: false,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.statusCode==200){
					var key = RSAUtils.getKeyPair(data.exponent, '', data.modulus);  
					var password = RSAUtils.encryptedString(key, encodeURIComponent(pwd)); 
					loginSystem(name,password,yzm);
				}else{
					$("#divcenter_login").show();
					$("#divcenter_loginin").hide();
					$.dialog( {
						lock: true,
						id:1,
						title : "提示",
						content :data.message,
						icon:"error"
					
					});
				}
			},
			error:function(){
				$("#divcenter_login").show();
				$("#divcenter_loginin").hide();
				$.dialog( {
					id:1,
					title : "提示",
					content :"网络连接异常",
					icon:"error"
				
				});
			},
			beforeSend:function(){
				$("#divcenter_login").hide();
				$("#divcenter_loginin").show();
			}
		});
		
	}
}
//登陆系统
function loginSystem(name,password,yzm){
	$.ajax({
		url:"sy_login/go.do",
		type:"post",
		dataType:"json",
		data:{name:name,password:password,vercode:yzm},
		success:function(data){
			if(data.statusCode==200){
				window.location.href="main.do";
			}else{
				$("#img_vercode").attr("src","sy_login/imgNum.do?_t="+new Date());
				$("#divcenter_login").show();
				$("#divcenter_loginin").hide();
				$.dialog( {
					lock: true,
					id:1,
					title : "提示",
					content :data.message,
					icon:"error"
				
				});
			}
		},
		error:function(){
			$("#divcenter_login").show();
			$("#divcenter_loginin").hide();
			$.dialog( {
				id:1,
				title : "提示",
				content :"网络连接异常",
				icon:"error"
			
			});
		},
		beforeSend:function(){
			$("#divcenter_login").hide();
			$("#divcenter_loginin").show();
		}
	});
}
function checkBrowser(){
	initBrowserInfo();
	if(BrowserVersion.ie&&BrowserVersion.ie<8){
		
		$.dialog( {
			id:1,
			lock:true,
			background:"red",
			title : "提示",
			content :"抱歉，您使用浏览器版本太低，本系统不支持IE8(内核)以下浏览器。",
			icon:"warning"
		
		});
		return true;
	}
}
//初始化浏览器类型 版本信息
var BrowserVersion = {};//浏览器类型
function initBrowserInfo(){
	 var userAgent=navigator.userAgent.toLowerCase();
     var s;
     (s = userAgent.match(/msie ([\d.]+)/)) ? BrowserVersion.ie = s[1] :
     (s = userAgent.match(/firefox\/([\d.]+)/)) ? BrowserVersion.firefox = s[1] :
     (s = userAgent.match(/chrome\/([\d.]+)/)) ? BrowserVersion.chrome = s[1] :
     (s = userAgent.match(/opera.([\d.]+)/)) ? BrowserVersion.opera = s[1] :
     (s = userAgent.match(/version\/([\d.]+).*safari/)) ? BrowserVersion.safari = s[1] : 0;
}

