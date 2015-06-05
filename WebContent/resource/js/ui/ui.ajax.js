
/**
 * 查询 刷新 datagrid数据
 * @param {} form
 * @param {} datagrid
 */
function datagridSearch(form,datagrid){
	var $dg=$('#'+datagrid);
	var $form=$(form);
	var options=$dg.datagrid('options');
	var queryParams = options.queryParams;//获取原查询参数
	var mustParamNames=options["mustParamNames"];//获取每次查询必须添加的参数名称,拓展属性，非easyui属性
	var param={};//刷新数据需要的参数
	if(mustParamNames){
		var names=mustParamNames.split(",");
		for(var i=0,k=names.length;i<k;i++){
			var name=names[i];
			param[name]=queryParams[name];//添加必须参数
		}
	}
	var filterId=$form.attr("queryFilterId");
	var formatFilterData=$form.attr("formatFilterData");//添加此属性说明将表单查询内容按照过滤器的规则封装数据
	
	if(filterId&&$.fn.queryFilter){
		 //高级查询 有查询规则过滤器的
		if(formatFilterData){
			//需要将表单内的数据也添加到过滤规则里面，将条件添加在第一分组条件
			var d=$("#"+filterId).queryFilter("getData");
			if(d["rules"]&&d["rules"].length>0){
				d["rules"].push(formatData());
			}else{
				d["rules"]=formatData();
			}
			param['searchRules']=JSON.stringify(d);
		}else{
			param['searchRules']=JSON.stringify($("#"+filterId).queryFilter("getData"));
		}
	}else{
		//delete queryParams['searchRules'];
		//普通条件查询, 序列化form表单的搜索条件
		if(formatFilterData){
			//需将表单数据封装成json格式规则条件
			param['searchRules']=JSON.stringify({"rules":formatData()});
			
		}else{
			$.each($form.serializeArray(), function(index) {
				if (param[this['name']]) {
					param[this['name']] = param[this['name']] + "," + this['value'];
				} else {
					param[this['name']] = this['value'];
				}
			});
		}
		
	}
	function formatData(){
		var rules=[];
		$("[name]",$form).each(function(){
			 var $this=$(this);
			 var v=$this.val().trim();
     		 if(v!=""){
     			 var name=$this.attr("name");
     			 var old=getData(rules,name);//获取name 相同的原数据
     			 if(old){
     				old['value'] = old['value'] + "," + v;
     			 }else{
     				 var type=$this.attr("fieldType")||"string";//默认字符串
         			 var op=$this.attr("op")||"equal";//默认运算符
         			 var rule={};
         			 rule["field"]=name;
         			 rule["op"]=op;
         			 rule["value"]=v;
         			 rule["type"]=type;
         			 rules.push(rule);
     			 }
     		 }
		});
		return rules;
	}
	function getData(data,name){
		for(var i=0,k=data.length;i<k;i++){
			var o=data[i];
			if(o["name"]==name){
				return o;
			}
		}
	}
	$dg.datagrid('load', param);//刷新表格数据
	return false;
}

/**
 * ajax表单提交  
 * form 拓展属性：   
 * warn: 表单提交前提示
 * beforeCallback  表单提交前调用js方法名称，传入当前form对象作为参数，此方法返回true，表单继续提交，false，阻止表单提交
 * afterCallback   表单提交完成，返回操作成功之后的回调方法， 传入from ，json格式返回的信息两个参数
 * @param {} form
 * @param {String} confirmMsg 确认信息
 * @param {} callback  表单提交之后的回调方法
 * @return {Boolean}
 */
function validateSubmitForm(form){
	var $form=$(form);
	//从表格数据中获取选中的数据
	var isOk=true;
	$("input[fromDatagrid]",$form).each(function(){
		var $this=$(this);
		var $dg=$("#"+$this.attr("fromDatagrid"));//获取数据表格
		if($dg.length==1){
			var rows=$dg.datagrid('getChecked');
			if(rows.length==0){
				Msg.alert('提示',$this.attr("warn")||'请先勾选数据!','warning');
				isOk=false;
				return ;
			}
			var field = $this.attr("field") || "id";//默认获取 id
			var ids=[];
			for (var i = 0,l=rows.length; i <l ; i++) {
				ids.push(rows[i][field]);
			}
			$this.val(ids.join(","));//赋值
		}else{
			Msg.alert('提示',$this.attr("warn")||'请先勾选数据!','warning');
		}
		
	});
	if(!isOk)return false;
	
	if($form.attr("target")=="callbackframe"){
		//上传文件使用隐藏iframe提交表单,已准备好iframe，可以提交表单
		$.messager.progress({
			text : '表单提交中...'
		});
		//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
		$("button:submit",$form).each(function(){
			$(this).prop("disabled",true).addClass("disabled").attr("oldText",$(this).text()).text("提交中");
		});
		return true;
	}
	if(!$form.form("validate")){
		Msg.alert("提示","表单填写不完整!",'warning');
		return false;
	}
	
	if($form.attr("beforeCallback")){
		var $beforeCallback=eval('(' + $form.attr("beforeCallback") + ')');
		if(!$beforeCallback($form))return false;
	}
	var $afterCallback=submitSuccessCallback;
	if($form.attr("afterCallback")){
		$afterCallback=eval('(' + $form.attr("afterCallback") + ')');
	}
	
	var _submitFn = function(){
		if("multipart/form-data"==$(form).attr("enctype")){
			iframeCallback($form,$afterCallback);
		}else{
			
			$.ajax({
				type: $form.attr("method") || 'POST',
				url:$form.attr("action"),
				data:$form.serializeArray(),
				dataType:"json",
				cache: false,
				success:function(json){
					if(json!=null){
						MUI.ajaxDone(json);
						if(json.statusCode != MUI.statusCode.ok)return false;
						
						$afterCallback($form,json);
						
					}else{
						Msg.alert("提示","表单提交无返回信息",'error');
					}
				},
				beforeSend:function(XMLHttpRequest){
					$.messager.progress({
						text : '表单提交中...'
					});
					//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
					$("button:submit",$form).each(function(){
						$(this).prop("disabled",true).addClass("disabled").attr("oldText",$(this).text()).text("提交中");
					});
				},
				complete:function(XMLHttpRequest, textStatus){
					//禁用表单提交按钮,将按钮文字改为提交中,并记录原文字
					$("button:submit",$form).each(function(){
						$(this).prop("disabled",false).removeClass("disabled").text($(this).attr("oldText"));
					});
					$.messager.progress('close');
				}
			});
		}
	}
	
	if ($form.attr("warn")){
		 Msg.confirm('提示',$form.attr("warn"), function(r){
               if (r){_submitFn();}
         });
	} else {
		_submitFn();
	}
	return false;
}

/**
 * 带文件上传的ajax表单提交
 * @param {Object} form
 * @param {Object} callback
 */
function iframeCallback($form, callback){
	$iframe = $("#callbackframe");
	
	if ($iframe.size() == 0) {
		$iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
	}
	//用ajax标示 说明需要返回json格式消息
	var url=$form.attr("action");
	if(url.indexOf("ajax=1")==-1){
		url=url+(url.indexOf('?') == -1 ? "?" : "&")+"ajax=1";
		$form.attr("action",url);
	}
	$form.attr("target","callbackframe");
	
	_iframeResponse($form,$iframe[0], callback);
	
}
function _iframeResponse($form,iframe, callback){
	var $iframe = $(iframe), $document = $(document);
	
	$iframe.on("load", function(event){
		$iframe.off("load");
		
		if (iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';" || // For Safari
			iframe.src == "javascript:'<html></html>';") { // For FF, IE
			
			return;
		}
		var doc = iframe.contentDocument || iframe.document;

		// fixing Opera 9.26,10.00
		if (doc.readyState && doc.readyState != 'complete'){ 
			
			return;
		} 
		// fixing Opera 9.64
		if (doc.body && doc.body.innerHTML == "false") {
			
			return;
		}
	   
		var response;
		
		if (doc.XMLDocument) {
			// response is a xml document Internet Explorer property
			response = doc.XMLDocument;
		} else if (doc.body){
			try{
				response = $iframe.contents().find("body").text();
				response = jQuery.parseJSON(response);
			} catch (e){ // response is html document or plain text
				response = doc.body.innerHTML;
			}
		} else {
			// response is a xml document
			response = doc;
		}
		$form.removeAttr("target");$iframe.remove();
		$("button:submit",$form).each(function(){
			$(this).prop("disabled",false).removeClass("disabled").text($(this).attr("oldText"));
		});
		$.messager.progress('close');
		//判断服务器是否返回错误
		if(response!=null){
			MUI.ajaxDone(response);
			if(response.statusCode != MUI.statusCode.ok)return false;
			callback($form,response);
		}else{
			Msg.alert('消息','服务器无返回结果，操作失败','error');
		}
	});
	$form.submit();//提交表单
	
}
/**
 * 表单提交成功回调
 * 刷新当前容器，默认不需要为form添加其他属性，
 * 如果当前form不直接在主标签，弹出框刷新，则需要为form添加特殊属性
 * boxType:需要操作的容器类型（当前form所属），
 * boxId:容器id
 * tabPanel_Title: 当boxType为tab类型时，tabPanel_Id为tab标签的panel title, tabPanel_Id 和 tabPanel_Title根据情况使用
 * tabPanel_Id:当boxType为tab类型时，tabPanel_Id为tab标签的panel id

 * @param {} json
 */
function submitSuccessCallback($form,json){
	
	//对form 所在标签或窗口进行的操作
	if(json.currentCallback){
		var boxType=$form.attr("boxType");
		var $currentBox=$("#"+$form.attr("boxId"));
		if($currentBox.length>0){
			if("close"==json.currentCallback){
				//关闭
				if("dialog"==boxType){
					$currentBox.dialog('close');
				}else if("window"==boxType){
					$currentBox.window('close');
				}else if("tab"==boxType){
					//标签
					var tabTitle=$form.attr("tabPanel_Title");
					if(tabTitle){
						$currentBox.tabs('close', tabTitle);
					}else{
						var tabId=$form.attr("tabPanel_Id");
						if(tabId){
							$currentBox.tabs('close', $currentBox.tabs('getTabIndex', $("#"+tabId)));
						}	
					}
				}else{
					$currentBox.panel('close');
				}
			}
			else if("refresh"==json.currentCallback){
				//刷新
				if("dialog"==boxType){
					$currentBox.dialog('refresh');
				}else if("window"==boxType){
					$currentBox.window('refresh');
				}else if("tab"==boxType){
					//标签
					var tabTitle=$form.attr("tabPanel_Title");
					if(tabTitle){
						$currentBox.tabs('update', {
							tab: $currentBox.tabs("getTab",tabTitle),
							options: {}
						});	
					}else{
						var tabId=$form.attr("tabPanel_Id");
						if(tabId){
							$("#"+tabId).panel('refresh');
						}
					}
					
				}else{
					$currentBox.panel('refresh');
				}
			}
			else{
				//请求其他url，并在当前容器刷新
				if("dialog"==boxType){
					$currentBox.dialog('refresh',json.currentCallback);
				}else if("window"==boxType){
					$currentBox.window('refresh',json.currentCallback);
				}else if("tab"==boxType){
					var tabTitle=$form.attr("tabPanel_Title");
					if(tabTitle){
						$currentBox.tabs('update', {
							tab: $currentBox.tabs("getTab",tabTitle),
							options: {	
								href : json.currentCallback
							}
						});	
					}else{
						var tabId=$form.attr("tabPanel_Id");
						if(tabId){
							$("#"+tabId).panel('refresh',json.currentCallback);
						}
					}
					
				}else{
					$currentBox.panel('refresh',json.currentCallback);
				}
			}
		}
	}
	
	ajaxMessageCallback(json);
}
/**
 * ajax请求回调处理
 * @param {} json 返回的json格式的信息
 */
function ajaxMessageCallback(json){
	
	//判断是否需要刷新表格
	if(json.datagridId){
		
		var $datagridBox=$("#"+json.datagridId);
		if($datagridBox.length>0){
			//刷新表格数据
			$datagridBox.datagrid("reload");
			//清空所有选中
			$datagridBox.datagrid("clearChecked");
		}
	}
	//对其他容器进行操作
	if(json.otherCallback&&json.otherBoxId){
		
		var $otherBox =$("#"+json.otherBoxId);
		if($otherBox.length>0){
			var otherType="panel";
			if($otherBox.hasClass("easyui-panel")){
				
			}else if($otherBox.hasClass("easyui-dialog")){
				otherType="dialog";
			}else if($otherBox.hasClass("easyui-window")){
				otherType="window";
			}
			else if($otherBox.hasClass("window-body")){
				if($otherBox.find(".dialog-content").length>0){
					otherType="dialog";
				}else{
					otherType="window";
				}
			}else{
				if("close"==json.otherCallback){
					var $tabsBox=$otherBox.parent().parent().parent();
					if($tabsBox.hasClass("tabs-container")){
						otherType="tab";
					}
				}
			} 
			
			if("close"==json.otherCallback){
				//关闭
				if("dialog"==otherType){
					$otherBox.dialog('close');
				}else if("window"==otherType){
					$otherBox.window('close');
				}else if("tab"==otherType){
					$tabsBox.tabs('close', $tabsBox.tabs('getTabIndex',$otherBox));
				}else{
					$otherBox.panel('close');
				}
			}
			else if("refresh"==json.otherCallback){
				//刷新
				if("dialog"==otherType){
					$otherBox.dialog('refresh');
				}else if("window"==otherType){
					$otherBox.window('refresh');
				}else{
					$otherBox.panel('refresh');
				}
			}
			else{
				//请求其他url，并在容器刷新
				if("dialog"==otherType){
					$otherBox.dialog('refresh',json.otherCallback);
				}else if("window"==otherType){
					$otherBox.window('refresh',json.otherCallback);
				}else{
					$otherBox.panel('refresh',json.otherCallback);
				}
			}
		}
	}
	//执行其他回调方法
	if(json.callback_fn){
		var $callback=json.callback_fn;
		if (! $.isFunction($callback)) $callback = eval('(' + json.callback_fn + ')');
		$callback(json);
	}
}
function ajaxTodo_post(url,$dg, callback){
	var $callback = callback || ajaxMessageCallback;
	if (! $.isFunction($callback)) $callback = eval('(' + callback + ')');
	//将url?之后的参数改为data方式发送
	var urls=url.split("?");
	$.ajax({
		type:'POST',
		url:urls[0],
		dataType:"json",
		data:urls[1],
		cache: false,
		success: function(json){
			MUI.ajaxDone(json);
			if(json.statusCode != MUI.statusCode.ok)return false;
			if($dg){
				//刷新表格
				$dg.datagrid("reload");
				//清空所有选中
				$dg.datagrid("clearChecked");
			};
			$callback(json);//回调
		},
		beforeSend:function(XMLHttpRequest){
			$.messager.progress({
				text : '数据加载中，请稍等...'
			});
		},complete:function(XMLHttpRequest, textStatus){
			$.messager.progress('close');
		}
		
	});
}
function selectedTodo_doPost(url,pm,$dg,callback){
	var $callback = callback || ajaxMessageCallback;
	if (! $.isFunction($callback)) $callback = eval('(' + callback + ')');
	$.ajax({
		type:'POST', url:url, dataType:'json', cache: false,
		data:pm,
		success: function(json){
			MUI.ajaxDone(json);
			if(json.statusCode != MUI.statusCode.ok)return false;
			if($dg){
				//刷新表格
				$dg.datagrid("reload");
				//清空所有选中
				$dg.datagrid("clearChecked");
			};
			$callback(json);//处理其他回调信息
		},
		beforeSend:function(XMLHttpRequest){
			$.messager.progress({
				text : '数据加载中，请稍等...'
			});
		},complete:function(XMLHttpRequest, textStatus){
			$.messager.progress('close');
		}
	});
}
//打印
function selectedTodo_print(url,queryParams){
	
	//使用新打开窗口， form post 方式，解决中文参数乱码，参数长度太长问题
	var html = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">'; 	
	html += '<html><head></head><body><form id="formid" method="post" action="' + url + '">';  

	for(key in queryParams){
		 html += "<input type='hidden' name='" + key + "' value='" + queryParams[key] + "'/>"; 
	}
	html += "</form><script type='text/javascript'>document.getElementById('formid').submit();";  
    html += "<\/script></body></html>".toString().replace(/^.+?\*|\\(?=\/)|\*.+?$/gi, ""); 
    var newWin=window.open();
	if(newWin){
		newWin.document.write(html);
	}
}
//导出
function selectedTodo_export(url,queryParams){
	
	// form post 方式，解决中文参数乱码，参数长度太长问题
	var html = ''; 	
	html += '<form id="export_form_x" method="post" action="' + url + '">';  

	for(key in queryParams){
		 html += "<input type='hidden' name='" + key + "' value='" + queryParams[key] + "'/>"; 
	}
	html += "</form><script type='text/javascript'>document.getElementById('export_form_x').submit();";  
    html += "<\/script>".toString().replace(/^.+?\*|\\(?=\/)|\*.+?$/gi, ""); 
    $(html).prependTo("body").remove();
  
}
$.fn.extend({
	/**
	 * ajax请求
	 * @returns
	 */
	ajaxTodo:function(){
		
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				event.preventDefault();
				var url = $this.attr("href");
				if(url&&url.length>0){
					url=replaceTmByDatagrid($this);
					if(!url)return false;
					var $dg=null;
					var did=$this.attr("datagrid");
					if(did){
						$dg=$("#"+did);//关联的数据表格
					}
					var title = $this.attr("title");
					var titleText = $this.attr("titleText");
					if (title) {
						 Msg.confirm('提示',title, function(r){
			               if (r){ajaxTodo_post(url,$dg, $this.attr("callback"));}
			        	 });
					} else if(titleText){
						Msg.prompt('请输入信息',titleText, function(r){
			                if (r){
			                	url=url+(url.indexOf('?') == -1 ? "?" : "&")+"text="+r;
			                	ajaxTodo_post(url,$dg, $this.attr("callback"));
			                }
					    });
					} 
					
					else {
						ajaxTodo_post(url,$dg, $this.attr("callback"));
					}
					
				
				}else{
					Msg.alert('提示','url不能为空','warning');
					return false;
				}
				
				
				
			});
		});
	},
	selectedTodo:function(){
		
		return this.each(function(){
			var $this = $(this);
			$this.click(function(event){
				event.preventDefault();
				
				var url=$this.attr("href");
				if(url&&url.length>0){
					var gid=$this.attr("datagrid");
					if(gid){
						var $dg=$("#"+gid);
						if($dg.length<0){
							Msg.alert('提示','找不到url需要替换的数据，表格不存在!','warning');
							return false;
						}
						var rows=$dg.datagrid('getChecked');
						if(rows.length>0){
							var field = $this.attr("field") || "id";
							var ids=[];
							l=rows.length;
							for (var i = 0; i <l ; i++) {
								ids.push(rows[i][field]);
							}
							var pm={};
							pm[field+"s"]=ids.join(",");
							var title = $this.attr("title");
							var titleText = $this.attr("titleText");
							var callback=$this.attr("callback");
							var targetType=$this.attr("targetType");
							if (title) {
								Msg.confirm('提示',"当前一共勾选<span style='color:red'>[&nbsp;"+l+"&nbsp;]</span>行数据。<br/>"+title, function(r){
						               if (r){
						            	   if(targetType=="export"){
						            		   selectedTodo_export(url,pm)
						            	   }else if(targetType=="print"){
						            		   selectedTodo_print(url,pm)
						            	   }else{
						            		   selectedTodo_doPost(url,pm,$dg,callback);
						            	   }
						               }
						               
						         });
							}else if(titleText){
								Msg.prompt('请输入信息', "当前一共勾选<span style='color:red'>[&nbsp;"+l+"&nbsp;]</span>行数据。<br/>"+titleText, function(r){
					                if (r){
					                   pm["text"]=r;
					                   selectedTodo_doPost(url,pm,$dg,callback);
					                }
							    });
							} 
							else {
								if(targetType=="export"){
									selectedTodo_export(url,pm)
				            	}else if(targetType=="print"){
				            		selectedTodo_print(url,pm)   
				            	}else{
				            		selectedTodo_doPost(url,pm,$dg,callback);
				            	}
							}
						
						
						}else{
							Msg.topCenter({
								title:"提示",
								msg:$this.attr("warn") ||'请至少勾选一条数据!'
								,msgType:"warning"
							});
							return false;
						}
					}else{
						Msg.alert('提示','找不到url需要替换的数据，表格不存在!','warning');
						return false;
					}
				}else{
					Msg.alert('提示','url不能为空','warning');
				}
			});
		});
	
		
	}
});



