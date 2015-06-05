var workflow;
var workflowId = "";// 流程表主键id
var flowId="";//流程id，key
var flowType="";//流程类型
var flowName = "";//流程名称
var flowDesc = "";//流程描述
var processDefinitionVariables = "";
var nodeid = "";
var rows = "";
var flowUpdateDesc="";//流程修改说明


// 编辑流程时初始化流程图
function createCanvas(isNew, disabled) {

	try {
		
		workflow = new draw2d.MyCanvas("paintarea");
		workflow.scrollArea = document.getElementById("designer-area");
		if (disabled)workflow.setDisabled();
		
		if (isNew) {
			workflow.process.category = flowType;//命名空间(作为流程类型)
			workflow.process.id = flowId;
			workflow.process.name = flowName;
			workflow.process.documentation = flowDesc;
			var startObj = new draw2d.Start();
			//startObj.setId("startxxx");
			workflow.addFigure(startObj, 50,150);
			//var endObj = new draw2d.End();
			//endObj.setId("end");
			//workflow.addFigure(endObj, 200, 400);
			//默认打开流程属性
			openProperties(flowId,'processProperties');
		} else {
			//请求获取xml
			openProcessDef(workflowId);
		}
	} catch (e) {
		Msg.alert('提示',e.message,'warning');
	}
}
//根据Id获取流程XML
function openProcessDef(id) {
	$.ajax({
		url : "workflow/designer/getProcessXml.do",
		dataType : 'xml',
		data : {
			id : id
		},
		error : function(data) {
			Msg.alert('提示','xml请求解析错误<br/>'+data,'error');
			return "";
		},
		success : function(data) {
			
			parseProcessDescriptor(data);
		}
	});
}


// 添加流程元素模型
function addModel(name, x, y, icon) {
	var model = null;
	if (icon != null && icon != undefined)
		model = eval("new draw2d." + name + "('" + icon + "')");
	else
		model = eval("new draw2d." + name + "()");
	model.generateId();
	workflow.addModel(model, x, y);
}
// 删除流程元素模型
function deleteModel(id) {
	var task = workflow.getFigure(id);
	workflow.removeFigure(task);
}
// 打开属性页面
function openProperties(id, turnpage, type) {
	var processId = workflow.process.id;//流程id(key)
	// $('#properties-panel').panel('refresh',
	// 'processController.do?processProperties&turn=' + turnpage + '&id=' + id +
	// '&processId=' + processId + '&processDefinitionId=' +
	// processDefinitionId);
	
	$('#properties-panel').panel('refresh','workflow/designer/getFlowPropertiesPage.do?page=' + turnpage+"&nodeId="+id);

}
// 上一步
function redo() {
	workflow.getCommandStack().redo();
}
// 下一步
function undo() {
	workflow.getCommandStack().undo();
}
// 保存流程
// update-begin--Author:chenxu Date:20130426
// for：流程保存提示消息，跑到层后面了【流程创建成功】，且一直存在，除非刷新页面
function saveProcessDef() {
	gridname = 'processList';
	
	if(workflow.process.name==null||workflow.process.name==""){
		Msg.alert('提示','请填写流程名称','warning');
	}
	
	else {
		
		var params = "";
		var nodes = "";
		var xml = workflow.toXML();
		/*
		var processId = workflow.process.id;
		var descriptor = $(xml);
		var userTasks = descriptor.find('userTask');
		var manualTasks = descriptor.find('manualTask');
		var serviceTasks = descriptor.find('serviceTask');
		var scriptTasks = descriptor.find('scriptTask');
		var receiveTasks = descriptor.find('receiveTask');
		var businessRuleTasks = descriptor.find('businessRuleTask');
		userTasks.each(function(i) {
			var tid = $(this).attr('id');
			var nodeName = $(this).attr('name');
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		manualTasks.each(function(i) {
			var tid = $(this).attr('id');
			var nodeName = $(this).attr('name');
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		serviceTasks.each(function(i) {
			var tid = $(this).attr('id');
			var flag = $(this).attr('activiti:type');
			var nodeName = "";
			if (flag == 'mail') {
				nodeName = "邮件任务";
			} else {
				nodeName = $(this).attr('name');
			}
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		scriptTasks.each(function(i) {
			var tid = $(this).attr('id');
			var nodeName = $(this).attr('name');
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		receiveTasks.each(function(i) {
			var tid = $(this).attr('id');
			var nodeName = $(this).attr('name');
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		businessRuleTasks.each(function(i) {
			var tid = $(this).attr('id');
			var nodeName = "业务规则";
			nodes = nodes + "id=" + tid + "###nodeName=" + nodeName + "@@@";
		});
		if (params.length > 3) {
			params = params.substring(0, params.length - 3);
		}
		if (nodes.length > 3) {
			nodes = nodes.substring(0, nodes.length - 3);
		}
		*/
		$.ajax({
			url : "workflow/designer/save.do",
			type : 'POST',
			data : {
				id:workflowId,//流程表id
				flowXml : xml,// 流程定义XML
				flowName : workflow.process.name,// 流程对象名称
				flowDesc:workflow.process.documentation,
				updateDesc:flowUpdateDesc //流程修改原因
			},
			dataType : 'json',
			success : function(data) {
	
				if (data.statusCode==200) {
					
					Msg.center({
						title:"信息",
						msg:"保存成功",
						msgType:"success"
					});
				}else{
					Msg.alert('消息','<center>'+data.message+'</center><br/>','error');
				}
			}
		});
	
		
	}

}
// update-end--Author:chenxu Date:20130426
// for：流程保存提示消息，跑到层后面了【流程创建成功】，且一直存在，除非刷新页面

// 导出流程
function exportProcessDef(obj) {
	// obj.href="${ctx}/wf/procdef/procdef!exportProcessDef.action?procdefId="+processDefinitionId+"&processName="+processDefinitionName;
}
// 流程图绘制
function parseProcessDescriptor(data) {
	var isWebkit = /webkit/.test(navigator.userAgent.toLowerCase());
	
	var BPMNShape = isWebkit ? 'BPMNShape' : 'bpmndi\\:BPMNShape';
	var BPMNEdge = isWebkit ? 'BPMNEdge' : 'bpmndi\\:BPMNEdge';
	var executionListener = isWebkit ? 'executionListener': 'activiti\\:executionListener';
	var Bounds = isWebkit ? 'Bounds' : 'omgdc\\:Bounds';
	var waypoint = isWebkit ? 'waypoint' : 'omgdi\\:waypoint';
	var taskListener = isWebkit ? 'taskListener'
			: 'activiti\\:taskListener';
	var formProperty = isWebkit ? 'formProperty'
			: 'activiti\\:formProperty';
	var field = isWebkit ? 'activiti\\:field' : 'field';
	var expression = isWebkit ? 'expression'
			: 'activiti\\:expression';
	var intag = isWebkit ? 'in' : 'activiti\\:in';
	var outtag = isWebkit ? 'out' : 'activiti\\:out';
	var descriptor = $(data);
	var definitions = descriptor.find('definitions');
	var process = descriptor.find('process');
	var startEvent = descriptor.find('startEvent');
	var endEvent = descriptor.find('endEvent');
	var manualTasks = descriptor.find('manualTask');
	var userTasks = descriptor.find('userTask');
	var serviceTasks = descriptor.find('serviceTask');
	var scriptTasks = descriptor.find('scriptTask');
	var receiveTasks = descriptor.find('receiveTask');
	var exclusiveGateway = descriptor.find('exclusiveGateway');
	var parallelGateway = descriptor.find('parallelGateway');
	var inclusiveGateway = descriptor.find('inclusiveGateway');
	var boundaryEvent = descriptor.find('boundaryEvent');
	var intermediateCatchEvent = descriptor.find('intermediateCatchEvent');
	var callActivitys = descriptor.find('callActivity');
	var businessRuleTasks = descriptor.find('businessRuleTask');
	var lines = descriptor.find('sequenceFlow');

	var shapes = descriptor.find(BPMNShape);
	var edges = descriptor.find(BPMNEdge);
	workflow.process.category = definitions.attr('targetNamespace');
	
	workflow.process.id = process.attr('id');
	workflow.process.name = process.attr('name');
	var documentation = trim(descriptor.find('process > documentation').text());
	if (documentation != null && documentation != "")
		workflow.process.documentation = documentation;
	var extentsion = descriptor.find('process > extensionElements');
	if (extentsion != null) {
		var listeners = extentsion.find(executionListener);
		workflow.process.setListeners(parseListeners(listeners,
				"draw2d.Process.Listener", "draw2d.Process.Listener.Field"));
	}
	$.each(processDefinitionVariables, function(i, n) {
		var variable = new draw2d.Process.variable();
		variable.name = n.name;
		variable.type = n.type;
		variable.scope = n.scope;
		variable.defaultValue = n.defaultValue;
		variable.remark = n.remark;
		workflow.process.addVariable(variable);
	});
	startEvent.each(function(i) {
		var start = new draw2d.Start();
		start.id = $(this).attr('id');
		start.eventId = $(this).attr('id');
		start.eventName = $(this).attr('name')||"";
		start.formKey = $(this).attr('activiti:formKey')||"";
		var expression = $(this).attr('activiti:initiator')||"";
		if (expression == null || expression == 'null') {
			expression = "";
		}
		start.expression = expression;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == start.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				start.setDimension(w, h);
				workflow.addFigure(start, x, y);
				return false;
			}
		});
	});
	endEvent.each(function(i) {
		var end = new draw2d.End();
		end.id = $(this).attr('id');
		end.eventId = $(this).attr('id');
		end.eventName = $(this).attr('name')||"";
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == end.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				end.setDimension(w, h);
				workflow.addFigure(end, x, y);
				return false;
			}
		});
	});
	userTasks
			.each(function(i) {
				var task = new draw2d.UserTask();
				var tid = $(this).attr('id');
				task.id = tid;
				var tname = $(this).attr('name')||"";
				var assignee = $(this).attr('activiti:assignee');
				var candidataUsers = $(this).attr('activiti:candidateUsers');
				var candidataGroups = $(this).attr('activiti:candidateGroups');
				var formKey = $(this).attr('activiti:formKey');
				var dueDate = $(this).attr('activiti:dueDate');
				var priority = $(this).attr('activiti:priority');
				
				
				if (assignee != null && assignee != "") {
					
					task.performerType = "assignee";
					task.expression = assignee;
				}else{
					
					if (candidataUsers != null && candidataUsers != "") {
						
						task.performerType = "candidate";
						task.users = candidataUsers;
					 } 
					 if (candidataGroups != null && candidataGroups != "") {
						 	
						 	task.performerType = "candidate";
						 	task.groups = candidataGroups;
					 }
					
				}
				//修改此处
				/*
				else if (candidataUsers != null && candidataUsers != "") {
					
					task.performerType = "candidateUsers";
					task.expression = candidataUsers;
				} else if (candidataGroups != null && candidataGroups != "") {
					
					task.performerType = "candidateGroups";
					task.expression = candidataGroups;
				}*/
				 
				
				
				if (formKey != null && formKey != "") {
					task.formKey = formKey;
				}
				if (dueDate != null && dueDate != "") {
					task.dueDate = dueDate;
				}
				if (priority != null && priority != "") {
					task.priority = priority;
				}
				
				
				var documentation = trim($(this).find('documentation').text());
				if (documentation != null && documentation != "")
					task.documentation = documentation;
				task.taskId = tid;
				task.taskName = tname;
				// if (tid != tname)
				task.setContent(tname);
				
				//多实例
				var multiInstance=$(this).find('multiInstanceLoopCharacteristics');
				task.isSequential=multiInstance.attr('isSequential')||"";
				task.collection=multiInstance.attr('activiti:collection')||"";
				task.elementVariable=multiInstance.attr('activiti:elementVariable')||"";
				task.loopCardinality = trim(multiInstance.find('loopCardinality').text());
				if(task.loopCardinality==null||task.loopCardinality=="")task.loopCardinality=null;
				task.completionCondition = trim(multiInstance.find('completionCondition').text());
				if(task.completionCondition==null||task.completionCondition=="")task.completionCondition=null;
				
				//执行监听器
				var listeners = $(this).find('extensionElements').find(executionListener);
				
				task.setListeners(parseListeners(listeners,	"draw2d.Task.Listener", "draw2d.Task.Listener.Field"));
				
				//用户任务监听器
				var taskListeners = $(this).find('extensionElements').find(taskListener);
				task.setTaskListeners(parseListeners(taskListeners,	"draw2d.UserTask.TaskListener", "draw2d.UserTask.TaskListener.Field"));
				
				var forms = $(this).find('extensionElements').find(
						'activiti\\:formProperty');
				task.setForms(parseForms(forms, "draw2d.Task.Form"));
				var performersExpression = $(this).find('potentialOwner').find(
						'resourceAssignmentExpression')
						.find('formalExpression').text();
				if (performersExpression.indexOf('user(') != -1) {
					task.performerType = "candidateUsers";
				} else if (performersExpression.indexOf('group(') != -1) {
					task.performerType = "candidateGroups";
				}
				var performers = performersExpression.split(',');
				$.each(performers, function(i, n) {
					var start = 0;
					var end = n.lastIndexOf(')');
					if (n.indexOf('user(') != -1) {
						start = 'user('.length;
						var performer = n.substring(start, end);
						task.addCandidateUser({
							sso : performer
						});
					} else if (n.indexOf('group(') != -1) {
						start = 'group('.length;
						var performer = n.substring(start, end);
						task.addCandidateGroup(performer);
					}
				});
				shapes.each(function(i) {
					var id = $(this).attr('bpmnElement');
					if (id == task.id) {
						var x = parseInt($(this).find(Bounds).attr('x'));
						var y = parseInt($(this).find(Bounds).attr('y'));
						var w = parseInt($(this).find(Bounds).attr('width'));
						var h = parseInt($(this).find(Bounds).attr('height'));
						task.setDimension(w, h);
						workflow.addModel(task, x, y);
						return false;
					}
				});
			});

	manualTasks
			.each(function(i) {
				var task = new draw2d.ManualTask();
				var tid = $(this).attr('id');
				task.id = tid;
				var tname = $(this).attr('name')||"";
				
				
				var documentation = trim($(this).find('documentation').text());
				if (documentation != null && documentation != "")
					task.documentation = documentation;
				task.taskId = tid;
				task.taskName = tname;
				// if (tid != tname)
				task.setContent(tname);
				var listeners = $(this).find('extensionElements').find(
						'activiti\\:taskListener');
				task.setListeners(parseListeners(listeners,
						"draw2d.Task.Listener", "draw2d.Task.Listener.Field"));
				
				shapes.each(function(i) {
					var id = $(this).attr('bpmnElement');
					if (id == task.id) {
						var x = parseInt($(this).find(Bounds).attr('x'));
						var y = parseInt($(this).find(Bounds).attr('y'));
						var w = parseInt($(this).find(Bounds).attr('width'));
						var h = parseInt($(this).find(Bounds).attr('height'));
						task.setDimension(w, h);
						workflow.addModel(task, x, y);
						return false;
					}
				});
			});

	serviceTasks.each(function(i) {
		
		var flag = $(this).attr('activiti:type');
		if (flag == 'mail') {
			//邮件任务
			var task = new draw2d.MailTask();
			var tid = $(this).attr('id');
			task.id = tid;
			var tname = $(this).attr('name')||"";
			var documentation = trim($(this).find('documentation').text());
			if (documentation != null && documentation != "")
				task.documentation = documentation;
			
			task.taskName = tname;
			task.setContent(tname);

			
			var elements = $(this).find('activiti\\:field');
			elements.each(function(i) {
				if ($(this).attr('name') == 'to') {
					task.toEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'from') {
					task.fromEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'subject') {
					task.subjectEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'cc') {
					task.ccEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'bcc') {
					task.bccEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'charset') {
					task.charsetEmail = $(this).attr('expression');
				}
				if ($(this).attr('name') == 'html') {
					task.htmlEmail = trim($(this).find('activiti\\:expression').text());
				}
				if ($(this).attr('name') == 'text') {
					task.textEmail = trim($(this).find('activiti\\:expression').text());
				}

			});

			task.taskId = tid;

			shapes.each(function(i) {
				var id = $(this).attr('bpmnElement');
				if (id == task.id) {
					var x = parseInt($(this).find(Bounds).attr('x'));
					var y = parseInt($(this).find(Bounds).attr('y'));
					workflow.addModel(task, x, y);
					return false;
				}
			});
		} else {
			//服务任务
			var task = new draw2d.ServiceTask();
			var tid = $(this).attr('id');
			task.id = tid;
			var tname = $(this).attr('name')||"";
			
			var documentation = trim($(this).find('documentation').text());
			if (documentation != null && documentation != "")
				task.documentation = documentation;
			task.taskId = tid;
			task.taskName = tname;
			// if (tid != tname)
			task.setContent(tname);
			
			var javaClass = $(this).attr('activiti:class');
			var expression = $(this).attr('activiti:expression');
			var delegateExpression = $(this).attr('activiti:delegateExpression');
			if(javaClass){
				task.serviceType="javaClass";
				task.expression=javaClass;
			}else if(expression){
				task.serviceType="expression";
				task.expression=expression;
			}else if(delegateExpression){
				task.serviceType="delegateExpression";
				task.expression=delegateExpression;
			}
			
			var listeners = $(this).find('extensionElements').find(
					'activiti\\:taskListener');
			task.setListeners(parseListeners(listeners, "draw2d.Task.Listener",
					"draw2d.Task.Listener.Field"));
			
			shapes.each(function(i) {
				var id = $(this).attr('bpmnElement');
				if (id == task.id) {
					var x = parseInt($(this).find(Bounds).attr('x'));
					var y = parseInt($(this).find(Bounds).attr('y'));
					var w = parseInt($(this).find(Bounds).attr('width'));
					var h = parseInt($(this).find(Bounds).attr('height'));
					task.setDimension(w, h);
					workflow.addModel(task, x, y);
					return false;
				}
			});
		}

	});

	scriptTasks.each(function(i) {
		var task = new draw2d.ScriptTask();
		var tid = $(this).attr('id');
		task.id = tid;
		var tname = $(this).attr('name')||"";
		var scriptFormat = $(this).attr('scriptFormat');
		var resultVariable = $(this).attr('activiti:resultVariable');
		task.scriptFormat = scriptFormat;
		task.resultVariable = resultVariable;
		var documentation = trim($(this).find('documentation').text());
		if (documentation != null && documentation != "")
			task.documentation = documentation;
		var script = trim($(this).find('script').text());
		if (script != null && script != "")
			task.expression = script;
		task.taskId = tid;
		task.taskName = tname;
		// if (tid != tname)
		task.setContent(tname);

		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == task.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				task.setDimension(w, h);
				workflow.addModel(task, x, y);
				return false;
			}
		});
	});

	receiveTasks.each(function(i) {
		var task = new draw2d.ReceiveTask();
		var tid = $(this).attr('id');
		task.id = tid;
		var tname = $(this).attr('name')||"";
		task.taskId = tid;
		task.taskName = tname;
		// if (tid != tname)
		task.setContent(tname);
		var documentation = trim($(this).find('documentation').text());
		if (documentation != null && documentation != "")
			task.documentation = documentation;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == task.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				task.setDimension(w, h);
				workflow.addModel(task, x, y);
				return false;
			}
		});
	});

	exclusiveGateway.each(function(i) {
		var gateway = new draw2d.ExclusiveGateway();
		var gtwid = $(this).attr('id');
		var gtwname = $(this).attr('name')||"";
		gateway.id = gtwid;
		gateway.gatewayId = gtwid;
		gateway.gatewayName = gtwname;
		gateway.defaultFlow=$(this).attr('default')||null;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == gateway.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				gateway.setDimension(w, h);
				workflow.addModel(gateway, x, y);
				return false;
			}
		});
	});
	parallelGateway.each(function(i) {
		var gateway = new draw2d.ParallelGateway();
		var gtwid = $(this).attr('id');
		var gtwname = $(this).attr('name')||"";
		gateway.id = gtwid;
		gateway.gatewayId = gtwid;
		gateway.gatewayName = gtwname;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == gateway.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				gateway.setDimension(w, h);
				workflow.addModel(gateway, x, y);
				return false;
			}
		});
	});
	inclusiveGateway.each(function(i) {
		var gateway = new draw2d.InclusiveGateway();
		var gtwid = $(this).attr('id');
		var gtwname = $(this).attr('name')||"";
		gateway.id = gtwid;
		gateway.gatewayId = gtwid;
		gateway.gatewayName = gtwname;
		gateway.defaultFlow=$(this).attr('default')||null;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == gateway.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				gateway.setDimension(w, h);
				workflow.addModel(gateway, x, y);
				return false;
			}
		});
	});
	boundaryEvent
			.each(function(i) {
				if($(this).find('timerEventDefinition')){
					//边界定时事件
					var timeBoundaryevent = new draw2d.TimerBoundary(
								"resource/js/workflowDesigner/js/designer/icons/timer.png");
					var boundaryId = $(this).attr('id');
					var boundaryName=$(this).attr('name');
					var cancelActivity = $(this).attr('cancelActivity');
					var attachedToRef = $(this).attr('attachedToRef');
					timeBoundaryevent.id = boundaryId;
					timeBoundaryevent.name=boundaryName;
					timeBoundaryevent.boundaryId = boundaryId;
					timeBoundaryevent.cancelActivity = cancelActivity;
					timeBoundaryevent.attached = attachedToRef;
					
					if ($(this).find('timeDate').text() != '') {
						timeBoundaryevent.timeType = 'timeDate';
						timeBoundaryevent.expression = $(this).find('timeDate').text();
					} else if ($(this).find('timeDuration').text() != '') {
						timeBoundaryevent.timeType = 'timeDuration';
						timeBoundaryevent.expression = $(this).find('timeDuration').text();
					} else if ($(this).find('timeCycle').text() != '') {
						timeBoundaryevent.timeType = 'timeCycle';
						timeBoundaryevent.expression = $(this).find('timeCycle').text();
					} 
				}else if ($(this).find('errorEventDefinition').text() != ''){
					//异常边界事件
					var errorBoundaryevent = new draw2d.ErrorBoundary(
							"resource/js/workflowDesigner/js/designer/icons/error.png");
					var boundaryId = $(this).attr('id');
					var attachedToRef = $(this).attr('attachedToRef');
					errorBoundaryevent.id = boundaryId;
					errorBoundaryevent.boundaryId = boundaryId;
					errorBoundaryevent.attached = attachedToRef;
					errorBoundaryevent.expression = $(this).find(
							'errorEventDefinition').attr('errorRef');
				}
				shapes.each(function(i) {
					var id = $(this).attr('bpmnElement');
					if (id == timeBoundaryevent.id) {
						var x = parseInt($(this).find(Bounds).attr('x'));
						var y = parseInt($(this).find(Bounds).attr('y'));
						var w = parseInt($(this).find(Bounds).attr('width'));
						var h = parseInt($(this).find(Bounds).attr('height'));
						timeBoundaryevent.setDimension(w, h);
						workflow.addModel(timeBoundaryevent, x, y);
						return false;
					}
				});
			});
	//中间捕获事件
	intermediateCatchEvent
			.each(function(i) {
				if($(this).find('timerEventDefinition')){
					//定时事件
					var timeevent = new draw2d.TimerIntermediate(
								"resource/js/workflowDesigner/js/designer/icons/timer.png");
					var boundaryId = $(this).attr('id');
					var boundaryName=$(this).attr('name');
					
					timeevent.id = boundaryId;
					timeevent.name=boundaryName;
					timeevent.boundaryId = boundaryId;
					
					
					if ($(this).find('timeDate').text() != '') {
						timeevent.timeType = 'timeDate';
						timeevent.expression = $(this).find('timeDate').text();
					} else if ($(this).find('timeDuration').text() != '') {
						timeevent.timeType = 'timeDuration';
						timeevent.expression = $(this).find('timeDuration').text();
					} else if ($(this).find('timeCycle').text() != '') {
						timeevent.timeType = 'timeCycle';
						timeevent.expression = $(this).find('timeCycle').text();
					} 
				}
				
				shapes.each(function(i) {
					var id = $(this).attr('bpmnElement');
					if (id == timeevent.id) {
						var x = parseInt($(this).find(Bounds).attr('x'));
						var y = parseInt($(this).find(Bounds).attr('y'));
						var w = parseInt($(this).find(Bounds).attr('width'));
						var h = parseInt($(this).find(Bounds).attr('height'));
						timeevent.setDimension(w, h);
						workflow.addModel(timeevent, x, y);
						return false;
					}
				});
			});		
			
			
	callActivitys
			.each(function(i) {
				var callActivity = new draw2d.CallActivity(
						"resource/js/workflowDesigner/js/designer/icons/callactivity.png");
				var subProcessId = $(this).attr('id');
				var name = $(this).attr('name')|"";
				var callSubProcess = $(this).attr('calledElement');
				callActivity.id = subProcessId;
				callActivity.subProcessId = subProcessId;
				callActivity.callSubProcess = callSubProcess;
				callActivity.name = name;
				var documentation = trim($(this).find('documentation').text());
				if (documentation != null && documentation != "")callActivity.documentation = documentation;
				var flag = $(this).find('extensionElements');
				if (flag != null) {
					callActivity.insource = $(this).find(intag).attr('source');
					callActivity.intarget = $(this).find(intag).attr('target');
					callActivity.outsource = $(this).find(outtag)
							.attr('source');
					callActivity.outtarget = $(this).find(outtag)
							.attr('target');
				}
				shapes.each(function(i) {
					var id = $(this).attr('bpmnElement');
					if (id == callActivity.id) {
						var x = parseInt($(this).find(Bounds).attr('x'));
						var y = parseInt($(this).find(Bounds).attr('y'));
						var w = parseInt($(this).find(Bounds).attr('width'));
						var h = parseInt($(this).find(Bounds).attr('height'));
						callActivity.setDimension(w, h);
						workflow.addModel(callActivity, x, y);
						return false;
					}
				});
			});

	businessRuleTasks.each(function(i) {
		var task = new draw2d.BusinessRuleTask();
		var tid = $(this).attr('id');
		var valueInput = $(this).attr('activiti:ruleVariablesInput');
		var valueOutput = $(this).attr('activiti:resultVariables');
		var rules = $(this).attr('activiti:rules');
		var exclude = $(this).attr('exclude');
		if (rules != null && rules != '') {
			task.rules = rules;
			if (exclude != null && exclude != '') {
				task.isclude = 'exclude';
			} else {
				task.isclude = 'include';
			}
		} else {
			task.isclude = '';
		}
		task.id = tid;
		task.taskId = tid;
		task.rulesInput = valueInput;
		task.rulesOutputs = valueOutput;
		shapes.each(function(i) {
			var id = $(this).attr('bpmnElement');
			if (id == task.id) {
				var x = parseInt($(this).find(Bounds).attr('x'));
				var y = parseInt($(this).find(Bounds).attr('y'));
				var w = parseInt($(this).find(Bounds).attr('width'));
				var h = parseInt($(this).find(Bounds).attr('height'));
				task.setDimension(w, h);
				workflow.addModel(task, x, y);
				return false;
			}
		});
	});

	lines.each(function(i) {
		var lid = $(this).attr('id');
		var name = $(this).attr('name')||"";
		var condition = $(this).find('conditionExpression').text().trim();
		var sourceRef = $(this).attr('sourceRef');
		var targetRef = $(this).attr('targetRef');
		if(sourceRef){
			var source = workflow.getFigure(sourceRef);
		}
		if(targetRef){
			var target = workflow.getFigure(targetRef);
		}
		edges.each(function(i) {
			var eid = $(this).attr('bpmnElement');
			if (eid == lid) {
				var startPort = null;
				var endPort = null;
				var points = $(this).find(waypoint);
				var len = points.length;
				var startX = $(points[0]).attr('x');
				var startY = $(points[0]).attr('y');
				var endX = $(points[len - 1]).attr('x');
				var endY = $(points[len - 1]).attr('y');
				
				if(source){
					var sports = source.getPorts();
					for ( var i = 0; i < sports.getSize(); i++) {
						var s = sports.get(i);
						var x = parseInt(s.getAbsoluteX());
						var y = parseInt(s.getAbsoluteY());
	
						if (x == startX && y == startY) {
							startPort = s;
							break;
						}
					}
				}
				if(target){
					var tports = target.getPorts();
	
					for ( var i = 0; i < tports.getSize(); i++) {
						var t = tports.get(i);
						var x = parseInt(t.getAbsoluteX());
						var y = parseInt(t.getAbsoluteY());
						if (x == endX && y == endY) {
							endPort = t;
	
							break;
						}
					}
				}
				if (startPort != null && endPort != null) {

					var cmd = new draw2d.CommandConnect(workflow, startPort,
							endPort);
					var connection = new draw2d.DecoratedConnection();

					connection.id = lid;
					connection.lineId = lid;
					connection.lineName = name;
					connection.setLabel(name);
					if (condition != null && condition != "") {
						connection.condition = condition;
					}
					cmd.setConnection(connection);
					workflow.getCommandStack().execute(cmd);
				}
				return false;
			}
		});
	});
	if (typeof setHightlight != "undefined") {
		setHightlight();
	}
	//默认打开流程属性
	openProperties(workflow.process.id,'processProperties');
}
/**
 * 监听器转换
 * 
 * @param listeners
 * @param listenerType
 * @param fieldType
 * @returns {draw2d.ArrayList}
 */
function parseListeners(listeners, listenerType, fieldType) {
	var parsedListeners = new draw2d.ArrayList();
	listeners.each(function(i) {

		var listener = eval("new " + listenerType + "()");
		listener.event = $(this).attr('event');
		listener.id = $(this).attr('id');
		var clazz = $(this).attr('class');
		var expression = $(this).attr('expression');
		
		if (clazz != null && clazz != "") {
			listener.serviceType = 'javaClass';
			listener.serviceClass = clazz;
		}
		else if (expression != null && expression != "") {
			listener.serviceType = 'expression';
			listener.serviceExpression = expression;
		} 
		
		var fields = $(this).find('activiti\\:field');
		fields.each(function(i) {
			var field = eval("new " + fieldType + "()");
			field.name = $(this).attr('name');
			var string = $(this).find('activiti\\:string').text();
			var expression = $(this).find('activiti\\:expression').text();
			if (string != null && string != "") {
				field.type = 'string';
				field.value = string;
			} else if (expression != null && expression != "") {
				field.type = 'expression';
				field.value = expression;
			}
			listener.setField(field);
		});
		parsedListeners.add(listener);
	});
	return parsedListeners;
}
function parseForms(forms, formType) {
	var parsedForms = new draw2d.ArrayList();
	forms.each(function(i) {
		var form = eval("new " + formType + "()");
		form.id = $(this).attr('id');
		var name = $(this).attr('name');
		form.name = name;
		var type = $(this).attr('type');
		form.type = type;
		var value = $(this).attr('value');
		form.value = value;
		var exp = $(this).attr('exp');
		form.exp = exp;
		var remark = $(this).attr('remark');
		form.remark = remark;
		parsedForms.add(form);
	});
	return parsedForms;
}
