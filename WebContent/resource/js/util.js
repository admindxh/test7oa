

//文件上传验证
/*
function fileChange(target) {   
	    var fileSize = 0;   
	    
	    if (isIE && !target.files) {    
		      var filePath = target.value;   
		      var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
		      
			  if(!fileSystem.FileExists(filePath)){
			     alert("附件不存在，请重新输入！");
				 var file=document.getElementById(id); 
		       	 file.outerHTML=file.outerHTML;
		       	 return;
			  }
		      var file = fileSystem.GetFile (filePath);
		      fileSize = file.Size;   //ie安全问题
    	} else {   
	     	  fileSize = target.files[0].size; 
	    }  
	   
        var size = fileSize / 1024;   
        
        if(size>100000){ 
       		 alert("附件大小不能大于100M！"); 
       		 //var file=document.getElementById(id); 
       		// file.outerHTML=file.outerHTML
        }  
        if(size<=0){
        	alert("附件大小不能为0M！"); 
        	//var file=document.getElementById(id); 
       		// file.outerHTML=file.outerHTML
        } 
} 
*/


//获取页面传值 ?传值的参数
function Utilrequest(paras){    
		var url = location.href;     
		var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");     
		var paraObj = {}     
		for (i=0; j=paraString[i]; i++){     
			paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);     
		}     
		var returnValue = paraObj[paras.toLowerCase()];     
		if(typeof(returnValue)=="undefined"){     
			return "";     
		}else{     
			return returnValue;    
		}  
}
//时钟
function Clock() {
	var date = new Date();
	this.year = date.getFullYear();
	this.month = date.getMonth() + 1;
	this.date = date.getDate();
	this.day = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")[date.getDay()];
	this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

	this.toString = function() {
		return this.year + "年" + this.month + "月" + this.date + "日 " + this.hour + ":" + this.minute + ":" + this.second + " " + this.day; 
	};
	
	this.toSimpleDate = function() {
		return this.year + "-" + this.month + "-" + this.date;
	};
	
	this.toDetailDate = function() {
		return this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
	};
	
	this.display = function(ele) {
		var clock = new Clock();
		ele.innerHTML = clock.toString();
		window.setTimeout(function() {clock.display(ele);}, 1000);
	};
}
//时间人性格式化
function changetime(dateTimeStamp){
	var minute = 1000 * 60;
	var hour = minute * 60;
	var day = hour * 24;	 	
	var now = new Date();
	var old;
	var diffValue;
	if(!+[1,]){
		//"ie浏览器";
		dateTimeStampt=dateTimeStamp.replace("T"," ");
		
		old= new Date(Date.parse(dateTimeStamp.replace(/-/g,   "/")));
		diffValue = now.getTime() - (old.getTime()-3600000);
	}else{
		old=new Date(dateTimeStamp);
		diffValue = now.getTime() - old.getTime();
	}
	var dayC =diffValue/day;
	var hourC =diffValue/hour;
	var minC =diffValue/minute;
	 	
	 if(dayC>=3){
		  
		 if(now.getFullYear()==old.getFullYear()){
			 	time= dateTimeStamp.replace("T"," ");
				return time.substring(time.indexOf("-")+1,time.length);
			
			 
		 }else{
			 return dateTimeStamp.replace("T"," ");
		 }
	 }else if(dayC>=2){
		 return "前天 "+dateTimeStamp.split("T")[1];
	 }else if(dayC>=1){
		 return "昨天 "+dateTimeStamp.split("T")[1];
	 }else if(hourC>=1){
	 	return parseInt(hourC) +"小时前";
	 }else if(minC>=1){
	 	return parseInt(minC) +"分钟前";
	 }else{
	 	return Math.floor(diffValue/1000)+"秒前";
	 }	 
}


//重载  setTimeout 方法，提供参数传递, 定时提醒需要修改
/*var __sto = setTimeout;
window.setTimeout = function(callback,timeout,param){
	var args = Array.prototype.slice.call(arguments,2);
	var _cb = function(){
		callback.apply(null,args);
	}
	__sto(_cb,timeout);
}*/

/**
 * 自定义单选
 * @param input	单选元素this
 */
function myRadio(input){
	var $in=$(input);
	var $form=$in.closest("form");
	$("input[radioType='"+$in.attr("radioType")+"']",$form).prop("checked",false);
	$in.prop("checked",true);
}

/**
 * 对form表单需要加密的内容进行加密
 * 
 * @param {} $form form jquery对象
 */
function encryptePassword($form){
	
	var key = RSAUtils.getKeyPair($("#exponent",$form).val(), '', $("#modulus",$form).val());  
	
	$("[encryptTo]",$form).each(function(){
		var enName=$(this).attr("encryptTo");
		var password = RSAUtils.encryptedString(key, encodeURIComponent($(this).val())); 
		if($("input[name='"+enName+"']",$form).length>0){
			$("input[name='"+enName+"']",$form).val(password);
		}else{
			$(this).after('<input type="hidden" name="'+enName+'" value="'+password+'" />');
		}
		
		$(this).removeAttr("name");
	});
	return true;
}