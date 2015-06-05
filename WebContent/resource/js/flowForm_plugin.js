/**
 * 流程表单控件插件
 * 
 * 说明：流程表单控件特殊处理，对input(text,radio,checkbox)，textarea,select做处理
 * 
 * div 拓展属性
 * 		flowForm=flow: 流程全局表单
 * 		
 * 		formType=editor:	编辑模式，将用户不可操作的区域设置不可用
 * 		formType=show:	显示模式，去除所有的编辑框，用于显示	
 * 		taskId:当前办理任务的流程定义id，用于判断哪些输入框可用(输入框的taskId和div的taskId相同才可用)。
 * 				
 * 
 * input，textarea select  都有的拓展的属性：
 * 		taskId：此控件属于的任务定义id,如果有多个任务都可以操作此控件，多个id用，隔开，
 * 		required：是否必填，true，false,默认为false
 * 		processVariable:是否需要将此控件的值保存在流程变量，true，false,默认false，如果为true，将以此控件的name作为流程变量的key		
 * 				
 * 
 * input 拓展属性：
 * 
 * 		loginUserInfo:获取当前登陆用户信息，name:姓名，deptName：部门名称	
 * 		infoId:	loginUserInfo内容对应的id，如果此控件显示当前登录人
 * 
 * 		nowDate：获取当前时间，值为日期格式化方式：yyyy-MM-dd HH:mm:ss
 * 		
 * 
 * textarea 拓展属性:
 * 		
 * 
 * select 拓展属性:
 * 		
 * 	
 * 
 * 任务表单：
 * 
 * div 拓展属性
 * 		flowForm=task: task:任务表单		
 * 		formType=editor:	编辑模式，
 * 		formType=show:	显示模式，去除所有的编辑框，用于显示	
 * 		
 * input，textarea select  都有的拓展的属性：
 * 		
 * 		required：是否必填，true，false,默认为false
 * 		processVariable:是否需要将此控件的值保存在流程变量，true，false,默认false，如果为true，将以此控件的name作为流程变量的key		
 * 				
 * 
 * input 拓展属性：
 * 
 * 		loginUserInfo:获取当前登陆用户信息，name:姓名，deptName：部门名称	
 * 		infoId:	loginUserInfo内容对应的id，如果此控件显示当前登录人
 * 
 * 		nowDate：获取当前时间，值为日期格式化方式：yyyy-MM-dd HH:mm:ss
 * 
 * 
 */

//配置查找带回 查看详情信息
var flowToShow={
		//车辆管理
		char:{
			id:"id",
			name:"name",
			url:"<a href='vehiclemanagement/lookvehiclemanagement.do?id={id}&rel=char_show' target='dialog' width='800' height='400'  rel='char_show'  title='查看车辆详情'>{name}</a>"
		}
		
};

(function($){
	$.fn.extend({
		//流程表单初始化
		initFlowForm: function(){
			
			return this.each(function(){
				var $div=$(this);
				
				var formType=$div.attr("formType");
				if(formType=="editor"){
					//编辑模式 
					var isFlowForm=false;//是全局工作表单
					if($div.attr("flowForm")=="flow"){
						isFlowForm=true;
					}
					var processVariables=new Array();//需要保存流程变量的变量名称
					
					var taskId = $div.attr("taskId");
					
					$("input[type='text'],input[type='radio'],input[type='checkbox'],input[type='hidden'],textarea,select",$div).each(function(){
						var $this=$(this);
						var isEnable=false;//是否可以使用此控件
						if(isFlowForm){
							//全局工作表单
							var ids=$this.attr("taskId");
							if(ids){
								var taskIds = ids.split(",");
								if($.inArray(taskId, taskIds)==-1){
									//不可编辑,禁用控件只对流程全局表单有效，对任务表单无效
									isEnable=false;								
								}else{
									isEnable=true;
								}
							}else{
								//暂定，当流程全局表单没有taskId时不验证权限，都可以编辑
								isEnable=true;
							}
							
						}else{
							//任务表单 都可以输入 不需要禁止输入控件
							isEnable=true;
						}
						//进行 禁止 或 释放 输入框 功能
						if(isEnable){
							//可编辑,初始化
							$this.removeProp("disabled");
							
							if($this.attr("nowDate")){
								
								$this.val(new Date().format($this.attr("nowDate")));
								
							}
							if($this.attr("loginUserInfo")){
								
								var infoType=$this.attr("loginUserInfo");
								if(infoType=="name"){
									$this.val(loginName);
									$this.attr("infoId",loginUserId);
								}
								else if(infoType=="deptName"){
									$this.val(loginDeptName);
									$this.attr("infoId",loginDeptId);
								}
							}
							if($this.attr("processVariable")){
								processVariables.push($this.attr("name"));
							}
							//显示一些按钮
							$(".btnDel,.btnDelete",$div).hide();
						}else{
							//不可编辑	
							$this.prop("disabled",true);//设置不可用
							//禁用校验
							if($this.hasClass("easyui-validatebox")){
								$this.validatebox("disableValidation");
							}
							//隐藏一些按钮
							$(".btnDel,.btnDelete",$div).hide();
							
						}
					});
					
					var $form=$div.closest("form");
					$("input[name='processVariablesKeys']",$form).val(processVariables.join(","));
					
					//工作表单 其他权限控制
					if(isFlowForm){
						//按钮的特殊处理 
						$("button[taskId]",$div).each(function(){
								var $this=$(this);
								var isEnable=false;//是否可以使用此控件
								var taskIds = $this.attr("taskId").split(",");
								if($.inArray(taskId, taskIds)==-1){
									//不可编辑,禁用控件只对流程全局表单有效，对任务表单无效
									isEnable=false;								
								}else{
									isEnable=true;
								}
								if(isEnable){
									$this.removeProp("disabled");
								}else{
									$this.prop("disabled",true);
								}
						});
						//主从表结构 处理
						$("table.itemDetail[taskId]",$div).each(function(){
							var $this=$(this);
							var isEnable=false;//是否可以使用此控件
							var taskIds = $this.attr("taskId").split(",");
							if($.inArray(taskId, taskIds)==-1){
								isEnable=false;								
							}else{
								isEnable=true;
							}
							if(isEnable){
								$this.prev().show();
							}else{
								$this.prev().hide();
							}
							
						});
						// a 连接处理
						$("a[taskId]",$div).each(function(){
							var $this=$(this);
							var isEnable=false;//是否可以使用此控件
							var taskIds = $this.attr("taskId").split(",");
							if($.inArray(taskId, taskIds)==-1){
								isEnable=false;								
							}else{
								isEnable=true;
							}
							//待拓展
							if(isEnable){
								
							}else{
								
							}
							
							
						});
					}	
				}else if(formType=="show"||formType=="print"){
					//展示模式 打印界面 去掉输入控件 
					if(formType=="show"){
						//展示模式 查找带回 需要添加点击事件 查看详情 的特殊处理
						 $("a[lookupShow],input[lookupShow],textarea[lookupShow]",$div).each(function(){
							 	var $this=$(this);
							 	var $targetBox=$this.closest($this.attr("targetBox"));
							 	var group=$this.attr("lookupGroup");
							 	var type=$this.attr("lookupShow");
							 	if(flowToShow[type]){
								 	var ids=$targetBox.find("[toName='"+group+"."+flowToShow[type]["id"]+"']").val().split(",");
								 	var names=$targetBox.find("[toName='"+group+"."+flowToShow[type]["name"]+"']").val().split(",");
					
								 	var html=[];
								 	for(var i=0,k=ids.length;i<k;i++){
								 		html.push(flowToShow[type]["url"].replace("{id}",ids[i]).replace("{name}",names[i]));
								 	}	
								 	$this.after(html.join(""));
								 	initUI($this.closest("td"));
								 	$this.remove();
							 	}
							 	
							 	
						 });
					}
					$("input[type='text'],textarea",$div).each(function(){
						
						$(this).after($(this).val()).remove();
						
					});
					$("select",$div).each(function(){
						var $this=$(this);
						var v=$this.val();
						if(v!=""){
							$this.after($("option[value='"+v+"']",$this).text());
						}
						$this.remove();
					});
					
					//单选，多选，待完善
					$("input[type='radio'],input[type='checkbox']",$div).each(function(){
						
						if($(this).prop("checked")){
			   				
			   			};
						
					});
					
					//去除一些按钮
					$(".btnDel,.btnDelete",$div).remove();
					$(".addItem",$div).remove();
					
					
					
				}
				
			
			});
		}
	});
	$.extend({
		//流程表单提交，当点击保存时，在提交form之前进行一些特殊处理
		onFlowFormSubmit:function(bt){
			
		   var $form=$(bt).closest("form");
		  
		   //由于在html()方法只能获取到元素（IE可以获取到），先手动赋值
		   var $flowForm=$("div[flowForm='flow']",$form);//工作表单
		   if($flowForm.length!=0){
			   setFormValues($flowForm);
		       $("input[name='flowFormHtml']",$form).val($flowForm.html());
		      
		       
		   }
		   var $taskForm=$("div[flowForm='task']",$form);//任务表单
		   if($taskForm.length!=0){
			   setFormValues($taskForm);
		       $("input[name='taskFormHtml']",$form).val($taskForm.html());
		      
		   }
		   function setFormValues($box){
			   $("input", $box).each(function() {
			        $(this).attr("value",$(this).val());
			   });
			   $("textarea", $box).each(function() {
				   $(this).html($(this).val());
			   });
			   $("input:checked", $box).each(function() {
			    	$(this).prop("checked",true);
			    });
		       $("select option:selected", $box).each(function() {
		    	   $(this).attr("selected","selected");
		       });
		   }
		
		}
	});
})(jQuery);