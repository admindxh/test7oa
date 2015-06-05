
/**
 * 查询过滤规则
 */
(function($){
	function init($this){
		var options = $this.data('queryFilter').options;
		var fields=options.fields;
		
		$this.append('<table class="l-filter-group table table-bordered" border="0" cellpadding="0" cellspacing="0"><tbody><tr class="l-filter-rowlast"><td class="l-filter-rowlastcell" colspan="4" style="text-align: right;"><button  class="addgroup btn btn-primary btn-small" type="button">'+options.displays['addgroup']+'</button><button  class="addrule btn btn-primary btn-small" type="button">'+options.displays['addrule']+'</button></td></tr></tbody></table>');
		
		//获取分组html
		var groupHtml=getGroupHtml(options);
		var ruleHtml=getRuleHtml(options);
		
		if(options.data){
			var dataJson=JSON.parse(options.data);
			if(dataJson!=null){
				
				setData($(">table", $this),dataJson,groupHtml,ruleHtml);//初始化数据
			
			}
		}
		initLoopUp($this);
		
		//事件：增加分组
        $this.on('click',".addgroup", function (){
            var jtable = $(this).closest(".l-filter-group");
            addGroup(jtable,groupHtml);
        });
        //事件：删除分组
        $this.on('click',".deletegroup", function (){
            $(this).closest(".l-filter-group").parent().parent().remove();
        });
        //事件：增加条件
        $this.on('click',".addrule", function (){
            var jtable = $(this).closest(".l-filter-group");
            var $rule=addRule(jtable,ruleHtml);
          //解析生成的html
            $.parser.parse($rule); 
    		initUI($rule);
    		initLoopUp($rule);
        });
        //事件：删除条件
        $this.on('click',".deleterole", function (){
            $(this).closest("tr").remove();
        });
       
        //更改条件 下拉列表
        $this.on('change','select.fieldsel',function(){
        	var $sel=$(this);
        	var $tr=$sel.closest("tr");
        	var filed=getFiledOption($sel.val());
        	$tr.attr("fieldtype",filed.type);//说明字段类型
        	//根据类型 更改运算符列表
        	$tr.find(".opsel:first").html(_bulidOpSelectOptionsHtml(options,filed.type));
        	//更改输入控件
        	var html=$tr.find("td.l-filter-value:first").html(filed.html);
        	//解析生成的html 
        	$.parser.parse(html); 
     		initUI(html);
     		initLoopUp(html);
        });
        //根据fieldName 获取 字段
        function getFiledOption(fieldName){
        	for (var i = 0, l = fields.length; i < l; i++){
                var f = fields[i];
                if (f.field == fieldName) return f;
            }
        	return null;
        }
       
        
        //设置规则
        //parm [group] 分组数据
        //parm [jgruop] 分组table dom jQuery对象
        function setData($group,groupData,groupHtml,ruleHtml){
           
            var lastrow = $(">tbody:first > tr:last", $group);
            $group.find(">tbody:first > tr").not(lastrow).remove();
            $("select:first", lastrow).val(groupData.op);
            if (groupData.rules){
                $(groupData.rules).each(function (){
                    var rulerow = addRule($group,ruleHtml);
                    rulerow.attr("fieldtype", this.type || "string");
                    $("select.opsel", rulerow).val(this.op);
                    
                    $("select.fieldsel", rulerow).val(this.field);
                   
                    var fo=getFiledOption(this.field);//字段 
                    
                    var value=this.value;
                    
                    var $tv=$("td.l-filter-value", rulerow).html(fo.html);
                   
                    if("userId"==this.type){
                    	
                    	$tv.find("[toName]").each(function(){
                    		if($(this).attr("type")=="hidden"){
                    			$(this).val(value);
                    		}else{
                    			if(value=="{loginUserId}"){
                    				$(this).val("当前用户");
                            	}else{
                            		ajaxLookUpData("user/getTrueNamesById.do?ids="+value,$(this));
                            	}
                    		}
                    	});
                    }else if("deptId"==this.type){
                 
                    	$tv.find("[toName]").each(function(){
                    		if($(this).attr("type")=="hidden"){
                    			$(this).val(value);
                    		}else{
                    			if(value=="{loginDeptId}"){
                    				$(this).val("当前部门");
                            	}else{
                            		ajaxLookUpData("dept/getDeptNamesById.do?ids="+value,$(this));
                            	}
                    		}
                    	});
                    }if("roleId"==this.type){
                    	
                    	$tv.find("[toName]").each(function(){
                        		if($(this).attr("type")=="hidden"){
                        			$(this).val(value);
                        		}else{
                        			if(value=="{loginRoleId}"){
                        				$(this).val("当前角色");
                                	}else{
                                		ajaxLookUpData("role/getRoleNamesById.do?ids="+value,$(this));
                                	}
                        		}
                    	});
                    }else{
                    	$tv.find("[name='"+this.field+"']").val(value);
                    }
                });
            }
            if (groupData.groups){
                $(groupData.groups).each(function (){
                    var subjgroup = addGroup($group,groupHtml);
                    setData(subjgroup,this,groupHtml,ruleHtml);
                });
            }
        }
        //发送ajax请求，根据id获取name 并赋值
        function ajaxLookUpData(url,$input){
        	$.ajax({
        		url:url,
				type:"post",
				dataType:"json",
				success:function(data){
					$input.val(data.names);
				}
			});
        }
        //查找带回 点击 设置当前用户，角色，部门
        function initLoopUp($box){
        	$box.find("a[lookupSetValue]").each(function(){
        		$(this).click(function(event){
        			event.preventDefault();
        			var $this=$(this);
            		var $td=$this.closest("td");
            		var type=$this.attr("lookupSetValue");
            		if("user"==type){
            			$td.find("[toName]").each(function(){
            				if($(this).attr("type")=="hidden"){
                    			$(this).val("{loginUserId}");
                    		}else{
                    			$(this).val("当前用户");
                    		}
            			});
            		}else if("dept"==type){
            			$td.find("[toName]").each(function(){
            				if($(this).attr("type")=="hidden"){
                    			$(this).val("{loginDeptId}");
                    		}else{
                    			$(this).val("当前部门");
                    		}
            			});
            		}else if("role"==type){
            			$td.find("[toName]").each(function(){
            				if($(this).attr("type")=="hidden"){
                    			$(this).val("{loginRoleId}");
                    		}else{
                    			$(this).val("当前角色");
                    		}
            			});
            		}
        		});
        	});
        }
        
	}  
	//增加分组
    //parm [jgroup] jQuery对象(主分组的table dom元素)
    function addGroup ($jgroup,html){
        var row = $(html);
        $(">tbody:first > tr:last", $jgroup).before(row);
        return row.find("table:first");
    }
    //增加一个条件
    //parm [jgroup] 分组的jQuery对象
    function addRule($jgroup,ruleHtml){
        var rulerow = $(ruleHtml);
        $(">tbody:first > tr:last", $jgroup).before(rulerow);
        return rulerow;
    }
    //获取一个分组的html
    function getGroupHtml(options){
    	
         var groupHtmlArr = [];
         groupHtmlArr.push('<tr class="l-filter-rowgroup"><td class="l-filter-cellgroup" colSpan="4">');
         groupHtmlArr.push(_bulidGroupTableHtml(options));
         groupHtmlArr.push('</td></tr>');
         
         return  groupHtmlArr.join('');
    }
    
    //获取一个分组内的table html
    function _bulidGroupTableHtml(options){
        
        var tableHtmlArr = [];
        tableHtmlArr.push('<table cellpadding="0" cellspacing="0" border="0" class="l-filter-group l-filter-group-alt "><tbody>');
  
        tableHtmlArr.push('<tr class="l-filter-rowlast"><td class="l-filter-rowlastcell" style="text-align: right;" colSpan="4">');
        //and or
        tableHtmlArr.push('<select class="groupopsel">');
        tableHtmlArr.push('<option value="and">' + options.displays['and'] + '</option>');
        tableHtmlArr.push('<option value="or">' + options.displays['or'] + '</option>');
        tableHtmlArr.push('</select>');

        //add group
        tableHtmlArr.push('<button type="button"  class="addgroup btn btn-primary btn-small">' + options.displays['addgroup'] + "</button>");
        //add rule
        tableHtmlArr.push('<button type="button"  class="addrule btn btn-primary btn-small">'+ options.displays['addrule'] +"</button>");
        
        tableHtmlArr.push('<button type="button"  class="deletegroup btn btn-danger btn-small">'+ options.displays['deletegroup'] + "</button>");

        tableHtmlArr.push('</td></tr>');

        tableHtmlArr.push('</tbody></table>');
        
        return tableHtmlArr.join('');
    }
   
    //获取条件规则的html
    function getRuleHtml(options){
        var fields = options.fields;
        var rowHtmlArr = [];
        var fieldType = fields[0].type || "string";
        rowHtmlArr.push('<tr fieldtype="' + fieldType + '"><td class="l-filter-column">');
        rowHtmlArr.push('<select class="fieldsel">');
        for (var i = 0, l = fields.length; i < l; i++)
        {
            var field = fields[i];
            rowHtmlArr.push('<option value="' + field.field + '"');
            if (i == 0) rowHtmlArr.push(" selected ");
            rowHtmlArr.push('>');
            rowHtmlArr.push(field.name);
            rowHtmlArr.push('</option>');
        }
        rowHtmlArr.push("</select>");
        rowHtmlArr.push('</td>');

        rowHtmlArr.push('<td class="l-filter-op">');
        rowHtmlArr.push('<select class="opsel">');
        rowHtmlArr.push(_bulidOpSelectOptionsHtml(options,fieldType));
        rowHtmlArr.push('</select>');
        rowHtmlArr.push('</td>');
        rowHtmlArr.push('<td class="l-filter-value">');
        rowHtmlArr.push(fields[0].html);
        rowHtmlArr.push('</td>');
        rowHtmlArr.push('<td>');
        rowHtmlArr.push('<div class="l-icon-cross deleterole"></div>');
        rowHtmlArr.push('</td>');
        rowHtmlArr.push('</tr>');
        return rowHtmlArr.join('');
    }
    //获取一个运算符选择框的html
    function _bulidOpSelectOptionsHtml(options,fieldType){
    	var o=options.opts;
        var ops = $.fn.queryFilter.operators[fieldType];
        var opHtmlArr = [];
        for (var i = 0, l = ops.length; i < l; i++){
            var op = ops[i];
            opHtmlArr[opHtmlArr.length] = '<option value="' + op + '">';
            opHtmlArr[opHtmlArr.length] = o[op];
            opHtmlArr[opHtmlArr.length] = '</option>';
        }
        return opHtmlArr.join('');
    }
    
	$.fn.queryFilter = function(options, param){
		if (typeof options == 'string'){
			return $.fn.queryFilter.methods[options]($(this), param);
		}
		options = options || {};
		return this.each(function(){
			var $this=$(this);
			var state = $this.data('queryFilter');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $this.data('queryFilter', {
					options: $.extend({}, $.fn.queryFilter.defaults, options)
				});
				init($this);
			}
		});
	};
	$.fn.queryFilter.methods = {
		
		getData: function(jq){
			
			function getGroupData(group){
	           
	            var groupData = {};
	            $("> tbody > tr", group).each(function (i, row){
	            	 
	            	var rowlast = $(row).hasClass("l-filter-rowlast");
	                var rowgroup = $(row).hasClass("l-filter-rowgroup");
	                if (rowgroup){
	                    var groupTable = $("> td:first > table:first", row);
	                    if (groupTable.length){
	                        if (!groupData.groups) groupData.groups = [];
	                       var subGroup= getGroupData(groupTable);
	                       if(subGroup)groupData.groups.push(subGroup);
	                    }
	                } else if (rowlast){
	                    groupData.op = $(".groupopsel:first", row).val();
	                }else{
	                    var fieldName = $("select.fieldsel:first", row).val();
	                    var value = _getRuleValue(row, fieldName);
	                    if(""!=value){
	                    	
	                    	var op = $(".opsel:first", row).val();
	                    	var type = $(row).attr("fieldtype") || "string";
		                    if (!groupData.rules) groupData.rules = [];
		                   
		                    groupData.rules.push({
		                        field: fieldName, op: op, value: value, type: type
		                    });
		                   
	                    }
	                   
	                }
	            });
	            if((groupData.groups&&groupData.groups.length>0)||(groupData.rules&&groupData.rules.length>0)){
	            	return groupData;
	            }else{
	            	return null;
	            }
		    }
	        function _getRuleValue(rulerow, fieldName){
	        	var vals=[];
	        	$(".l-filter-value:first", rulerow).find("[name='"+fieldName+"']").each(function(){
	        		var v=$(this).val().trim();
	        		if(v!="")vals.push(v);
	        		
	        	});
	        	return vals.join(",");
	        }
	      
            return getGroupData($("table:first", jq));
		}
	};
	$.fn.queryFilter.defaults = {
			displays:{
				 "and": "并且",
			     "or": "或者",
			     "addgroup": "增加分组",
			     "addrule": "增加条件",
			     "deletegroup": "删除分组"
			},
			opts: {
		       
		        "equal": "相等",
		        "notequal": "不相等",
		        "startwith": "以..开始",
		        "endwith": "以..结束",
		        "like": "相似",
		        "greater": "大于",
		        "greaterorequal": "大于或等于",
		        "less": "小于",
		        "lessorequal": "小于或等于",
		        "in": "包括在...",
		        "notin": "不包括..."       
		    }
	};
	//字段类型用于后台转换格式 java的基类数据类型 
	//string  byte shot int long float double char boolean
	//字段类型 - 运算符 的对应关系
	$.fn.queryFilter.operators={};
	$.fn.queryFilter.operators['string'] =
    ["equal", "notequal", "startwith", "endwith", "like", "greater", "greaterorequal", "less", "lessorequal", "in", "notin"];

	$.fn.queryFilter.operators['byte'] =
	$.fn.queryFilter.operators['int'] =
	$.fn.queryFilter.operators['shot'] =
	$.fn.queryFilter.operators['int'] =
	$.fn.queryFilter.operators['long'] =
	$.fn.queryFilter.operators['float'] =
	$.fn.queryFilter.operators['double'] =
    ["equal", "notequal", "greater", "greaterorequal", "less", "lessorequal", "in", "notin"];
	
	$.fn.queryFilter.operators['boolean'] =["equal", "notequal"];
	
	$.fn.queryFilter.operators['date'] =
	$.fn.queryFilter.operators['timestamp'] =
	["equal", "notequal", "greater", "greaterorequal", "less", "lessorequal"];
	//特殊类型
	$.fn.queryFilter.operators['userId']=
	$.fn.queryFilter.operators['deptId']=["equal", "notequal","in","notin"];
	$.fn.queryFilter.operators['roleId']=["in","notin"];
	
	
	
})(jQuery);

