
$(function(){
	
	navTab.init();//初始化 工作区间 
	
	if ($.taskBar) $.taskBar.init();//底部 弹窗口 任务拦
	
	//清理浏览器内存,只对IE起效
	if (/msie/i.test(navigator.userAgent)) {
		window.setInterval("CollectGarbage();", 10000);
	}
	initUI();//绑定事件
});

/**
 * 对js错误忽视，继续执行后面的js
 * @return {Boolean}
 */
window.onerror = function(err) {
	//return true;//返回true，则继续执行下面的js，忽略错误
	/*
	if($.messager){
		$.messager.progress('close');
		Msg.alert('JS错误提示','js执行错误:<br/>'+err,'error');
	}else{
		alert("js错误\n"+err);
	}
	*/
};
//更换主题
function changeEasyUITheme(themeName){
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	$easyuiTheme.attr('href', href);
	/*
	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}
	*/
	$.cookie('easyuiThemeName', themeName,{expires:7});
	
}


/**
 * 初始化组件，绑定事件
 * @param {} _box
 */
function initUI(_box){
	
	var $p = $(_box || document);
	
	if ($.fn.initMainTab) $(".maintabs", $p).initMainTab();
	
	//左侧菜单
	$(".ul-menu,.ul-submenu",$p).each(function(){
		var $this=$(this);
		$(">li",$this).each(function(){
			var $li=$(this);
			var $a=$(">a",$li);
			var $sub=$li.find(">.ul-submenu");
			if($sub.length==1){
				var $i=$("<i class='boot_icon-chevron-right f-right'></i>").appendTo($a);
				$a.css({"background-color":"#E0ECFF","color":"black"});
				$a.click(function(){
					if($sub.css("display")=="none"){
						$sub.show();
						$i.removeClass("boot_icon-chevron-right");
						$i.addClass("boot_icon-chevron-down");
						
					}else{
						$sub.hide();
						$i.removeClass("boot_icon-chevron-down");
						$i.addClass("boot_icon-chevron-right");
					}
				});
			}
		});
		
	});
	
	
	// 打开一个标签
	$("a[target=navTab],button[target=navTab]", $p).each(function(){
		$(this).click(function(event){
			event.preventDefault();
			var $this = $(this);
			var url=$this.attr("href");
			if(!url||url==""||url.indexOf("javascript:")==0)return false;
			if($this.attr("disabled")=="disabled")return false;
			var title = $this.attr("title");
			var tabid = $this.attr("rel") || "_newTab";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			
			if(url&&url.length>0){
				url=replaceTmByDatagrid($this);
				if(!url)return false;
				MUI.openCenterTab(title,url,tabid,external,fresh);
			}else{
				Msg.alert('提示','url不能为空','warning');
			}
		});
	});
	//打开一个窗口
	$("a[target=dialog],button[target=dialog]", $p).each(function(){
		$(this).click(function(event){
			event.preventDefault();
			var $this = $(this);
			var url=$this.attr("href");
			if(!url||url==""||url.indexOf("javascript:")==0)return false;
			if($this.attr("disabled")=="disabled")return false;
			//var title = $this.attr("title") || $this.text();
			var title = $this.attr("title");
			var rel = $this.attr("rel") || "_newDialog";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w) options.width = w;
			if (h) options.height = h;
			options.maximized = eval($this.attr("max") || "false");//是否最大化
			options.modal = eval($this.attr("modal") || "false");//是否遮蔽背景
			options.maximizable = eval($this.attr("canMax") || "true");//可最大化
			options.minimizable = eval($this.attr("canMin") || "true");//可最小化
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");//可以调整大小
			options.draggable = eval($this.attr("draggable") || "true");//可拖动
			options.close = eval($this.attr("close") || "");
			
			if($this.attr("param")){
				var param=$.parseJSON($this.attr("param"));
				if(param){
					$.extend(options,param);
				}
			}
			
			if(url&&url.length>0){
				url=replaceTmByDatagrid($this);
				if(!url)return false;
				MUI.openDialog(title,url,rel,options);
				
			}else{
				Msg.alert('提示','url不能为空','warning');
			}
		});
	});
	//ajax请求刷新面板 panel
	$("a[target=ajax],button[target=ajax]", $p).each(function(){
		$(this).click(function(event){
			event.preventDefault();
			var $this = $(this);
			var url=$this.attr("href");
			if(!url||url==""||url.indexOf("javascript:")==0)return false;
			if($this.attr("disabled")=="disabled")return false;
			var rel = $this.attr("rel");
			if (rel) {
				$("#"+rel).panel('refresh',url);
			}

			
		});
	});
	
	//ajax 请求
	$("a[target=ajaxTodo]", $p).ajaxTodo();
	//对表格多选进行操作 ajax 请求 导出 打印
	$("a[target=selectedTodo]", $p).selectedTodo();
	
	//datagrid 数据表格 清空勾引按钮操作 
	$("a.clearDgChecked",$p).each(function(){
		$(this).click(function(){
			var $dg=$("#"+$(this).attr("datagrid"));
			if($dg.length==1){
				$dg.datagrid('clearChecked');
			}
			
		});
	});
	
	
	//模糊查询使用方法提示
	$("i[selectLike]",$p).each(function(){
		$(this).css("cursor","pointer").tooltip({
		    position: 'bottom',
		    showEvent:'click',
		    content: '<span class="badge badge-info">1</span><span>当前查询条件支持模糊查询。</span><br/><span class="badge badge-info">2</span><span>可使用_，%进行模糊匹配。_ 可匹配单个任意字符;%可匹配任意0个或多个字符。</span><br/><span class="badge badge-info">3</span><span>由于_和%有特殊含义，当搜索内容是这两个字符时，可在字符前面加上/,例：/%,代表搜索 % 这个字符，而不是任意一个字符。_也同理。</span><br/><span class="badge badge-warning">例</span><span>王_，可以搜到王强，但是搜不到王小强，王%可以搜到王小强，因为_只能匹配一个字符。<br/></span><span class="badge badge-info">5</span><span>使用_,%这两个字符可以进行任意组合，搭配出更精确的查询。<br/><span class="badge badge-warning">例</span>_小%大%，意思是小前面只能有一个字符，小和大中间可以有多个字符,大之后可以有多个字符。',
		    onShow: function(){
		        $(this).tooltip('tip');
		    }
		});

	});
	
	if ($.fn.lookup) $("a[lookupGroup],input[lookupGroup],textarea[lookupGroup]", $p).lookup();
	
	if ($.fn.itemDetail) $("table.itemDetail", $p).itemDetail();	
	
	//字典值下拉列表 异步请求获取下拉列表内容
	$("select[selectOptions]",$p).each(function(){
		var $this=$(this);
		if($this.prop("disabled"))return false;
		if($this.attr("used"))return false;
		var value=$this.attr("sValue");//默认选中项
		$.ajax({
    		url:"list/getSelectOptions.do?type="+$this.attr("optionsType"),
			type:"get",
			cache: false,
			dataType:"json",
			success:function(json){
				var html="";
				$.each(json, function(i, m) {
					html+='<option value="'+m.value+'">'+m.name+'</option>'	
				});
				$this.append(html);
				$this.attr("used",true);//标记此元素已经初始化过
				if(value){
					$this.val(value);
				}
			}
		});
		
	});
	//通用select 下拉列表 异步请求获取下拉列表信息 
	// ajaxUrl 配置请求的地址，返回json数据{list:[{id:123,name:"xxx"}]}
	$("select[ajaxUrl]",$p).each(function(){
		var $this=$(this);
		if($this.prop("disabled"))return false;
		if($this.attr("used"))return false;
		
		var url=$this.attr("ajaxUrl");
		var value=$this.attr("sValue");//默认选中项
		$.ajax({
    		url:url,
			type:"get",
			cache: false,
			dataType:"json",
			success:function(json){
				var html="";
				if(json.list){
					$.each(json.list, function(i, m) {
						html+='<option value="'+m.id+'">'+m.name+'</option>'	
					});
					$this.append(html);
					$this.attr("used",true);//标记此元素已经初始化过
					if(value){
						$this.val(value);
					}
				}
			}
		});
		
	});
	//为下拉框赋初始值
	$("select[sValue]",$p).each(function(){
		$(this).val($(this).attr("sValue"));
	});
	
	//清空form表单内容
	$("button.clear",$p).each(function(){
		var $this=$(this);
		$this.click(function(){
			var $form=$this.closest("form");
			
			$form.find("input").each(function(){
				var type=$(this).attr("type");
				if(type==null||type==""||type=="hidden")return ;//隐藏域不会清空
				if(type=="text"||type=="password"){
					$(this).val("");
				}else if(type=="checkbox"){
					$(this).prop("checked",false);
				}

			})
			$form.find("textarea").val("");
			$form.find("select").each(function(){
				$(this).find("option").eq(0).prop("selected",true);
			});
			//附件处理
			$form.find("input[type='file']").each(function(){
				var $file=$(this).parent().prev();
				if($file.hasClass("file"))$file.val("");//自定义上传文件样式 清除文件名称
				$(this).after($(this).clone().val("")).remove();
				
			});
			
			//清楚查找带回的隐藏域
			$form.find("input[toName]").val("");
			
			//easyui 其他组件特殊处理
			$form.find(".numberbox-f").numberbox("clear");
			
		});
	});
	//查询带回清空按钮
	$("a.clearLookup",$p).each(function(){
		var $this=$(this);
		$this.click(function(){
			var group=$this.attr("clearLookup");
			var $box=$this.closest($this.attr("targetBox")||"form");
			$box.find("input[toName*='"+group+"']").val("");
			$box.find("textarea[toName*='"+group+"']").val("");
		});
		
	});
	//当改变下拉列表值时，提交表单
	$("select[submitForm]",$p).each(function(){
		var $this=$(this);
		$this.change(function(){
			$this.closest("form").submit();
		});
	});
	//将日期控件，查找带回 输入框光标呈现为指示链接的指针（一只手）
	$("input[readonly='readonly'],textarea[readonly='readonly']",$p).each(function(){
		var $this=$(this);
		if($this.attr("onFocus")||$this.attr("toName")){
			$this.css("cursor","pointer");
		}
		
	});
	
	
	
	// 这里放其他第三方jQuery插件...
	
	//百度编辑器
	
	$("textarea.editor", $p).each(function(){
		var $this=$(this);
		
		//var editorOption = DWZ.jsonEval($(this).attr("editorOption"));
		
		var options = {};
		
		if($this.attr("formSet")){
			
			//表单设计
			options.toolbars=[
	                   ['fullscreen', 'source', '|', 'undo', 'redo', '|',
	                       'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch','autotypeset','blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','selectall', 'cleardoc', '|',
	                       'rowspacingtop', 'rowspacingbottom','lineheight','|',
	                       'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	                       'directionalityltr', 'directionalityrtl', 'indent', '|',
	                       'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|','touppercase','tolowercase','|',
	                         'imagenone', 'imageleft', 'imageright','imagecenter', '|',
	                       'highlightcode','pagebreak','template','background', '|',
	                       'horizontal', 'date', 'time', 'spechars',  '|',
	                       'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
	                       'print', 'preview', 'searchreplace','addinput','addtextarea','addcheckbox','addradio','help']
	               ]
			
		}
		
		
		options.initialFrameWidth=$this.attr("width")||$this.width();
		options.initialFrameHeight=$this.attr("height")||220;
		
		var editor = new UE.ui.Editor(options);
		editor.render(this);
	});
	
	//ckeditor
	//$("textarea.editor", $p).each(function(){
	//	var $this=$(this);
	//	$this.ckeditor();
	//});
	
	//kindeditor
	//KindEditor.create('textarea.editor');

	
	
	//上传文件选择按钮美化   fileValue="123"  可以赋初始值
	if ($.fn.filestyle) $("input[fileupload='no']", $p).filestyle();
	

	//流程表单插件
	if ($.fn.initFlowForm) $("div[flowForm]", $p).initFlowForm();
	
	
}









