/**
 * 定义MUI 
 * 
 * 提供统一的打开标签，打开弹出框的js方法
 * 
 * openCenterTab:function(title,url,id,external,fresh)
 * 
 * openDialog:function(title,url,id,param)
 * 
 */

var MUI = {
	// sbar: show sidebar
	keyCode: {
		ENTER: 13, ESC: 27, END: 35, HOME: 36,
		SHIFT: 16, TAB: 9,
		LEFT: 37, RIGHT: 39, UP: 38, DOWN: 40,
		DELETE: 46, BACKSPACE:8
	},
	statusCode: {ok:200, error:300, timeout:301},
	
	ajaxDone:function(json){
		
		if(json.statusCode == MUI.statusCode.error) {
			Msg.alert('提示',json.message,'error');
		} else if (json.statusCode == MUI.statusCode.timeout) {
			Msg.confirm('提示',json.message||"抱歉，您已登陆超时!请重新登陆！", function(r){
               if (r){
            	   window.location.href = "sy_login.do";
               }
       	    });
		} else if(json.statusCode == MUI.statusCode.ok) {
			if(json.message&&""!=json.message){
				Msg.topCenter({
					title:"消息",
					msg:json.message,
					msgType:"success"
				});
			}
		}else{
			if(json.message&&""!=json.message){
				Msg.topCenter({
					title:"消息",
					msg:json.message
				});
			}
		}
	},
	/**
	 * 在中间打开标签
	 * @param {} title
	 * @param {} url
	 * @param {} id
	 * @param {} external
	 * @param {} fresh
	 */
	openCenterTab:function(title,url,id,external,fresh){
		navTab.addTab(title,url,id,external,fresh);
	},
	/**
	 * 打开一个窗口
	 * @param {} title
	 * @param {} url
	 * @param {} id
	 * @param {json} param   {width:500,height:300} 
	 */
	openDialog:function(title,url,id,param){
			param.id=id;
			param.title=title;
			param.href=url;
			var $newDialog=$("#"+id);
			if($newDialog.length==0){
				$newDialog=$('<div id="'+id+'">').appendTo("body");
			}
			//修正宽度，高度超出浏览器大小
			if(param.width){
				var browserWidth = $(window).width();
				if(param.width>browserWidth){
					param.width=browserWidth;
				}
			}
			if(param.height){
				var browserHeight = $(window).height();
				if(param.height>browserHeight){
					param.height=browserHeight;
				}
			}
			
			$newDialog.dialog(param);  
			if($.taskBar&&!param.modal){
				$.taskBar.addDialog(id,title);
			}
			return $newDialog;
	}
	
	
};


(function($){
	
	$.fn.extend({
		hoverClass: function(className, speed){
			var _className = className || "hover";
			return this.each(function(){
				var $this = $(this), mouseOutTimer;
				$this.hover(function(){
					if (mouseOutTimer) clearTimeout(mouseOutTimer);
					$this.addClass(_className);
				},function(){
					mouseOutTimer = setTimeout(function(){$this.removeClass(_className);}, speed||10);
				});
			});
		},
		/**
		 * 判断当前元素是否已经绑定某个事件
		 * @param {Object} type
		 */
		isBind:function(type) {
			var _events = $(this).data("events");
			return _events && type && _events[type];
		}
	});
	
	/**
	 * 扩展String方法
	 */
	$.extend(String.prototype, {
		isPositiveInteger:function(){
			return (new RegExp(/^[1-9]\d*$/).test(this));
		},
		isInteger:function(){
			return (new RegExp(/^\d+$/).test(this));
		},
		isNumber: function(value, element) {
			return (new RegExp(/^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/).test(this));
		},
		trim:function(){
			return this.replace(/(^\s*)|(\s*$)|\r|\n/g, "");
		},
		startsWith:function (pattern){
			return this.indexOf(pattern) === 0;
		},
		endsWith:function(pattern) {
			var d = this.length - pattern.length;
			return d >= 0 && this.lastIndexOf(pattern) === d;
		},
		replaceSuffix:function(index){
			return this.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index);
		},
		trans:function(){
			return this.replace(/&lt;/g, '<').replace(/&gt;/g,'>').replace(/&quot;/g, '"');
		},
		encodeTXT: function(){
			return (this).replaceAll('&', '&amp;').replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;");
		},
		replaceAll:function(os, ns){
			return this.replace(new RegExp(os,"gm"),ns);
		},
		replaceTm:function($data){
			if (!$data) return this;
			return this.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				return $data[$1.replace(/[{}]+/g, "")];
			});
		},
		isFinishedTm:function(){
			return !(new RegExp("{[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		replaceTmById:function(_box){
			var $parent = _box || $(document);
			return this.replace(RegExp("({#[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
				var $input = $parent.find($1.replace(/[{}]+/g, ""));
				return $input.val() ? $input.val() : $1;
			});
		},
		isFinishedTmById:function(){
			return !(new RegExp("{#[A-Za-z_]+[A-Za-z0-9_]*}").test(this)); 
		},
		skipChar:function(ch) {
			if (!this || this.length===0) {return '';}
			if (this.charAt(0)===ch) {return this.substring(1).skipChar(ch);}
			return this;
		},
		isValidPwd:function() {
			return (new RegExp(/^([_]|[a-zA-Z0-9]){6,32}$/).test(this)); 
		},
		isValidMail:function(){
			return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(this.trim()));
		},
		isSpaces:function() {
			for(var i=0; i<this.length; i+=1) {
				var ch = this.charAt(i);
				if (ch!=' '&& ch!="\n" && ch!="\t" && ch!="\r") {return false;}
			}
			return true;
		},
		isPhone:function() {
			return (new RegExp(/(^([0-9]{3,4}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0-9]{3,4}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/).test(this));
		},
		isUrl:function(){
			return (new RegExp(/^[a-zA-z]+:\/\/([a-zA-Z0-9\-\.]+)([-\w .\/?%&=:]*)$/).test(this));
		},
		isExternalUrl:function(){
			return this.isUrl() && this.indexOf("://"+document.domain) == -1;
		}
	});
})(jQuery);


/**
 * 表格数据 修改一类请求，将url中{id}{name} 等占位符，
 * 替换成当前用户选择的行的数据
 * @param {} $this
 * @return {Boolean}
 */
function replaceTmByDatagrid($this){
	var url=$this.attr("href");
	if(url.search(/{#[A-Za-z_]+[A-Za-z0-9_]*}/)!=-1){
		url=url.replaceTmById($this.closest("form"));//先以id进行取值匹配
		if(url.search(/{#[A-Za-z_]+[A-Za-z0-9_]*}/)!=-1){
			Msg.topCenter({
				title:"提示",
				msg:$this.attr("warn") ||'请先选择一条数据!'
				,msgType:"warning"
			});
			return false;
		}
	}
	if(url.search(/{[A-Za-z_]+[A-Za-z0-9_]*}/)!=-1){
		
		var gid=$this.attr("datagrid");
		if(gid){
			var row=$("#"+gid).datagrid('getSelected');
			if(row){
				url=url.replace(RegExp("({[A-Za-z_]+[A-Za-z0-9_]*})","g"), function($1){
						var v=row[$1.replace(/[{}]+/g, "")];
						if(v){
							return  encodeURIComponent(v);//转义
						}else{
							return $1;
						}
				});
				if (!url.isFinishedTm()) {
					Msg.alert('提示','url未替换完需要的数据!原因：占位符错误。<br/>'+url,'warning');
					return false;
				}else{
					return url;
				}
			}else{
				Msg.topCenter({
					title:"提示",
					msg:$this.attr("warn") ||'请先选择一条数据!'
					,msgType:"warning"
				});
				return false;
			}
		}else{
			Msg.alert('提示','找不到url需要替换的数据，表格不存在!','warning');
			return false;
		}
	}
	return url;
	
}