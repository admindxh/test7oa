/**
 * 弹出窗口 查找带回
 */
(function($){
	var _lookupList=new Array();//查找带回数据集合
	$.extend({
		//带回赋值，直接覆盖
		bringBack: function(args){
			var _lookup=_lookupList[_lookupList.length-1];
			var $box = _lookup['$target'].closest(_lookup['unitBox']);
			$box.find("[toName^='"+_lookup["currentGroup"]+"']:input").each(function(){
				var $input = $(this), inputName = $input.attr("toName");
				$input.setNewValue(args[inputName.substring(inputName.indexOf(".")+1)]);
					
			});
			_lookup["dialog"].dialog("close");
			//赋值之后清空关联的信息
			if(_lookup["clearClass"]){
				$("."+_lookup["clearClass"],$box).val("");
			}
		},
		//带回赋值，以追加形式
		appendBringBack: function(args){
			var _lookup=_lookupList[_lookupList.length-1];
			var $box = _lookup['$target'].closest(_lookup['unitBox']);
			$box.find("[toName^='"+_lookup["currentGroup"]+"']:input").each(function(){
				var $input = $(this), inputName = $input.attr("toName");
				var oldval=$input.val();
				if(oldval.length==0){
					$input.val(args[inputName.substring(inputName.indexOf(".")+1)]);
				}else{
					//原数组
					var old=oldval.split(",");
					//选中，需要带回的数组
					var arg=args[inputName.substring(inputName.indexOf(".")+1)].split(",");
					//将要添加的数组 不重复的
					var newvalue=new Array();
					for(var j=0,jl=arg.length;j<jl;j++){
						var f=true;
						for(var k=0,kl=old.length;k<kl;k++){
							if(old[k]==arg[j]){
								f=false;
								break;
							}
						}
						if(f) newvalue.push(arg[j]);
					}
					if(newvalue.length!=0){
						$input.setNewValue(oldval+","+newvalue.join(","));
					}
				}

			});
			_lookup["dialog"].dialog("close");
			//赋值之后清空关联的信息
			if(_lookup["clearClass"]){
				$("."+_lookup["clearClass"],$box).val("");
			}
		},
		/**
		 * 查询带回的按钮点击，组装需要带回的数据 
		 * bt:按钮，或a连接对象
		 * isCover: true：覆盖，false：追加
		 * did:关联的数据表格
		 */
		multLookup:function(bt,isCover,did){
			var $this=$(bt);
			if(!did){
				Msg.alert("提示","未指定数据表格",'warning');
				return false;
			}
			var rows = $("#"+did).datagrid('getChecked');
			if (rows.length < 1) {
				Msg.topCenter({
					title:"提示",
					msg:$this.attr("warn") ||'至少选择一条数据!'
					,msgType:"warning"
				});
				return false;
			} 
			var _lookup=_lookupList[_lookupList.length-1];
			if(_lookup["$tbody"]){
				//itemDetail 从表，批量添加
				
				$.itemDetailBringBack(rows);
				
			}else{
			
				var args={};
				for ( var i = 0; i < rows.length; i++) {
					var row=rows[i];
					for (var key in row) {
						var value = args[key] ? args[key]+"," : "";
						args[key] = value + row[key];
					}
				}
				if(isCover){
					$.bringBack(args);
				}else{
					$.appendBringBack(args);
				}
			}
		},
		//itemDetail 批量选择添加 
		itemDetailBringBack: function(rows){
			var _lookup=_lookupList[_lookupList.length-1];
			var $tbody=_lookup["$tbody"];
			var trHtml=_lookup["trHtml"];
			
			for ( var i = 0; i < rows.length; i++) {
				var row=rows[i];
			
				var $tr=$.addItemDetail($tbody,trHtml);
		
				$tr.find("[toName^='"+_lookup["currentGroup"]+"']:input").each(function(){
					var $input = $(this), inputName = $input.attr("toName");
					$input.setNewValue(row[inputName.substring(inputName.indexOf(".")+1)]);
						
				});
			}
		
		},
		/**
		 * ItemDetail 添加一个项目
		 */
		addItemDetail:function ($tbody,trHtml){
			var index=$tbody.find(">tr").length;
			var $tr=$(trHtml);
			//初始化下标
			$("[name]", $tr).each(function(){
				var $this = $(this), name = $this.attr('name');
				$this.attr("name",name.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index));
			});
			$tr.find(".itemDetail-rownumber").text(index+1);
			//其他特殊情况处理
			//easyui numberbox 组件会生成一个临时输入框，进行校验数字，需特殊处理 numberboxname
			$("[numberboxname]", $tr).each(function(){
				var $this = $(this), name = $this.attr('numberboxname');
				$this.attr("numberboxname",name.replace(/\[[0-9]+\]/,'['+index+']').replace('#index#',index));
			});
			
			$tr.appendTo($tbody).find("a.btnDel").click(function(){
						$(this).closest("tr").remove();
						$.initSuffixItemDetail($tbody);
						return false;
					});;
			$.parser.parse($tr);
			initUI($tr);
			return $tr;
		}
		,
		/**
		 * ItemDetail 删除时重新初始化下标
		*/
		initSuffixItemDetail:function ($tbody) {
			$tbody.find('tr').each(function(i){
				$("[name]", this).each(function(){
					var $this = $(this), name = $this.attr('name');
					$this.attr("name",name.replace(/\[[0-9]+\]/,'['+i+']').replace('#index#',i));
				});
				$(this).find(".itemDetail-rownumber").text(i+1);
				//其他特殊情况处理
				//easyui numberbox 组件会生成一个临时输入框，进行校验数字，需特殊处理 numberboxname
				$("[numberboxname]", this).each(function(){
					var $this = $(this), name = $this.attr('numberboxname');
					$this.attr("numberboxname",name.replace(/\[[0-9]+\]/,'['+i+']').replace('#index#',i));
				});
			});
		}
	});
	
	$.fn.extend({
		lookup: function(){
			return this.each(function(){
				var $this = $(this), options = {modal:true, 
					width:$this.attr('width')||850, height:$this.attr('height')||420,
					maximizable:eval($this.attr("canMax") || "true"),
					resizable:eval($this.attr("resizable") || "true"),
					minimizable:false
				};
				$this.click(function(event){
					event.preventDefault();
					var lookupGroup=$this.attr("lookupGroup");
					var title=$this.attr("title");
					if(!title||title==""){
						title=$this.attr("lookupTitle")
					}
					if(!title||title==""){
						return ;
					}
					var url = unescape($this.attr("href")).replaceTmById($(event.target).closest($this.attr("sourceBox") || "form"));
					if (!url.isFinishedTmById()) {
						Msg.topCenter({
							title:"提示",
							msg:$this.attr("warn") ||'级联的数据不能为空!'
							,msgType:"warning"
						});
						return false;
					}
					
					options["onClose"]=function(){
						_lookupList.pop();
						$(this).dialog("destroy");//关闭之后直接销毁
					}
					
					var divId=null;
					if($("#lookup_"+lookupGroup).length>0){
						divId="lookup_"+lookupGroup+"_"+_lookupList.length;
					}else{
						divId="lookup_"+lookupGroup;
					}
					url=url+(url.indexOf('?') == -1 ? "?" : "&")+"rel="+divId;
					
					
					$dig=MUI.openDialog(title,url,divId,options);
					
					var _lookup ={
						currentGroup: $this.attr("lookupGroup") || "",
						unitBox:$this.attr("targetBox") || "form",
						$target: $this,
						dialog:$dig,
						clearClass:$this.attr("clearClass")   //赋值之后需要清空的 控件，class 名称
					};
					_lookupList.push(_lookup);
					
					return false;
				});
			});
		},
		//从表 动态添加项目
		itemDetail: function(){
			return this.each(function(){
				
				var $table = $(this), $tbody = $table.find("tbody"),trHtml=$table.attr("template");
				
				if($table.prev().hasClass("addItem")) return ;//防止重复 
				
				var lookupGroup=$table.attr("lookupGroup");
				
				$tbody.find("a.btnDel").click(function(){
					
					var $btnDel = $(this);
					
					if ($btnDel.is("[href^=javascript:]")){
						$btnDel.closest("tr").remove();
						$.initSuffixItemDetail($tbody);
						return false;
					}
					
					function delDbData(){
						$.ajax({
							type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
							success: function(json){
								//添加错误弹出框提示
								MUI.ajaxDone(json);
								if(json.statusCode != MUI.statusCode.ok)return false;
								$btnDel.closest("tr").remove();
								$.initSuffixItemDetail($tbody);
							},
							beforeSend:function(XMLHttpRequest){
								$.messager.progress({
									text : '正在删除，请稍等...'
								});
							},complete:function(XMLHttpRequest, textStatus){
								$.messager.progress('close');
							}
						});
					}
					
					if ($btnDel.attr("title")){
						 Msg.confirm('提示',$btnDel.attr("title"), function(r){
				               if (r){
				              	  delDbData();
				               }
			        	 });
					} else {
						delDbData();
					}
					
					return false;
				});
				//添加
				var addButTxt = $table.attr('addButton')||"添加";
				var $addBut = $('<button type="button" class="btn btn-primary addItem" style="margin:5px;">'+addButTxt+'</button>').insertBefore($table);
				$addBut.click(function(){
					$.addItemDetail($tbody,trHtml);
				});
				
				//批量查询带回添加
				var addLookUpTxt = $table.attr('addLookUp');
				if(addLookUpTxt){
					var $addLookUpBut = $('<button type="button" class="btn btn-primary addItemLookUp" style="margin:5px;">'+addLookUpTxt+'</button>').insertBefore($table);
					$addLookUpBut.click(function(){
							var options = {modal:true, 
								width:$table.attr('width')||850, height:$table.attr('height')||420,
								maximizable:eval($table.attr("canMax") || "true"),
								resizable:eval($table.attr("resizable") || "true"),
								minimizable:false
							};
						var url = unescape($table.attr("href")).replaceTmById($table.closest($table.attr("sourceBox") || "form"));
						if (!url.isFinishedTmById()) {
							Msg.topCenter({
								title:"提示",
								msg:$table.attr("warn") ||'级联的数据不能为空!'
								,msgType:"warning"
							});
							return false;
						}
						
						options["onClose"]=function(){
							_lookupList.pop();
							$(this).dialog("destroy");//关闭之后直接销毁
						}
						
						var divId=null;
						if($("#lookup_"+lookupGroup).length>0){
							divId="lookup_"+lookupGroup+"_"+_lookupList.length;
						}else{
							divId="lookup_"+lookupGroup;
						}
						url=url+(url.indexOf('?') == -1 ? "?" : "&")+"rel="+divId;
						$dig=MUI.openDialog($this.attr("title"),url,divId,options);
						var _lookup ={
							currentGroup: $table.attr("lookupGroup") || "",
							dialog:$dig,
							$tbody:$tbody,
							trHtml:trHtml
						};
						_lookupList.push(_lookup);
							
							
					
					});
				
				}
				$.initSuffixItemDetail($tbody);//初始化index序列
			});
		},
		/**
		 * 控件赋值方法，解决easyui一些特殊组件需要单独调用方法问题  
		 * value:值
		 */
		setNewValue:function(value){
			return this.each(function(){
				var $this=$(this);
				if($this.hasClass("easyui-numberbox")){
					// easyui numberbox 组件
					$this.numberbox('setValue',value);
				}else{
					$this.val(value);
				}
			});
			
		}
	});
})(jQuery);

