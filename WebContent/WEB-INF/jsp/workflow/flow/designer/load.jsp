<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	
	<title>工作流程设计</title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<meta content="text/html; charset=UTF-8" http-equiv="content-type">
	
	<!-- 当高于IE9时按IE9解析 解决流程节点 开始，结束，等不显示，IE10问题-->
	<meta http-equiv="X-UA-Compatible" content="IE=9" />
	
	<link rel="stylesheet" href="resource/js/ui/css/ui.css" type="text/css"/>
	<link rel="stylesheet" href="resource/js/ui/css/icon.css" type="text/css"/>
	
	<!-- jquery -->
	<script type="text/javascript" src="resource/js/jquery/jquery-1.10.2.min.js"></script>
	
	
	<!-- easyui -->
	
	<link rel="stylesheet"	href="resource/js/easyui/themes/default/easyui.css" type="text/css" />
	<link rel="stylesheet" href="resource/js/easyui/themes/icon.css"	type="text/css" />
	
	<script type="text/javascript"	src="resource/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- draw2d -->
	<script src="resource/js/workflowDesigner/js/draw2d/wz_jsgraphics.js"></script>
	<script src='resource/js/workflowDesigner/js/draw2d/mootools.js'></script>
	<script src='resource/js/workflowDesigner/js/draw2d/moocanvas.js'></script>
	<script src='resource/js/workflowDesigner/js/draw2d/draw2d.js'></script>
	
	<!-- designer -->
	<link href="resource/js/workflowDesigner/css/designer.css" type="text/css" rel="stylesheet" />
	<script src="resource/js/workflowDesigner/js/designer/MyCanvas.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/ResizeImage.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/event/Start.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/event/End.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/connection/MyInputPort.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/connection/MyOutputPort.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/connection/DecoratedConnection.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/Task.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/UserTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/ManualTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/ServiceTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/gateway/ExclusiveGateway.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/gateway/ParallelGateway.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/gateway/InclusiveGateway.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/boundaryevent/TimerBoundary.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/boundaryevent/ErrorBoundary.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/subprocess/CallActivity.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/ScriptTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/MailTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/ReceiveTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/task/BusinessRuleTask.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/intermediateCatchEvent/TimerIntermediate.js"></script>
	
	<script src="resource/js/workflowDesigner/js/designer/designer.js"></script>
	<script src="resource/js/workflowDesigner/js/designer/mydesigner.js"></script>

	
	<%-- zTree --%>
	<script src="resource/js/zTree/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="resource/js/zTree/zTreeStyle/zTreeStyle.css" type="text/css"/>
	
	<!-- 查找带回 -->
	<script type="text/javascript" src="resource/js/ui/ui.database.js"></script>
	<script type="text/javascript" src="resource/js/ui/ui.ajax.js"></script>
	<script type="text/javascript" src="resource/js/ui/ui.core.js"></script>
	
	<script src="resource/js/ui/plugins/zTree_plugin.js" type="text/javascript"></script>

	<script src="resource/js/ui/ui.msg.js" type="text/javascript"></script>
	<script type="text/javascript" src="resource/js/ui/easyui.defaults.js"></script>
	<script src="resource/js/ui/ui.navTab.js" type="text/javascript"></script>
	<script src="resource/js/ui/ui.util.js" type="text/javascript"></script>
	
	<!-- 初始化信息 -->
	<script type="text/javascript" src="resource/js/workflowDesigner/js/init.js"></script>
	
	<!-- My97DatePicke -->
	<script type="text/javascript" src="resource/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	
	
	
	<style type="text/css">
		/* 流程表单样式   */
		table.flowtable{
			border-collapse:collapse;
			margin: 10px auto 10px;
			border: 1px solid black;
			height: auto;
		}
		table.flowtable caption{
			font-size: 20px;
			font-weight: bold;
			padding: 10px;
		}
		table.flowtable th{
			border: 1px solid black;
			padding: 10px;
			font-weight: bold;
			text-align:center;
		}
		table.flowtable td{
			border: 1px solid black;
			padding: 10px;
		}
		
		table.flowtable textarea{
			width: 300px;
			height: 30px;
		}
		textarea.textdesc{
			font-size: 12px;
			width: 150px;
			height: 60px;
		}
		
		
	</style>
	
</head>
<body id="designer" class="easyui-layout">
	<div id="toolbar-panel" region="north" border="false"	style="height: 55px; background: #d8e4fe;" title="工具栏">
		
		<img width="20"	height="18" title="创建流程" src="resource/js/workflowDesigner/css/img/save.png"	onclick="saveProcessDef()" class="buttonStyle" />
		<img width="20"	height="18" title="上一步" src="resource/js/workflowDesigner/css/img/back.png"	onclick="undo()" class="buttonStyle" />
		<img width="20" height="18"	title="下一步" src="resource/js/workflowDesigner/css/img/next.png" onclick="redo()"	class="buttonStyle" />
		<%--<img width="20" height="18" title="导出"	src="resource/js/workflowDesigner/css/img/printer.png"	onclick="exportProcessDef(this)" class="buttonStyle" />
	--%></div>

	<div region="west" split="true" iconCls="palette-icon" title="流程元素" 	style="width: 110px;">
		<div class="easyui-accordion" fit="true" border="false">
			<div id="event" title="事件" iconCls="palette-menu-icon" class="palette-menu">
				<a  href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="start-event-icon" wfModel="Start">开始</a><br/> 
				<a	href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="end-event-icon" wfModel="End">结束</a><br/>
			</div>
			<div id="task" title="任务" iconCls="palette-menu-icon" selected="true" 	class="palette-menu">
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="user-task-icon" wfModel="UserTask">用户任务</a><br/>
				<a href="javascript:;" class="easyui-linkbutton" plain="true"		iconCls="script-task-icon" wfModel="ScriptTask">脚本任务</a> <br/>
				<a href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="service-task-icon" wfModel="ServiceTask">服务任务</a> <br/>
				<a href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="mail-task-icon" wfModel="MailTask">邮件任务</a> <br/> 
				<a href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="manual-task-icon" wfModel="ManualTask">手动任务</a> <br/>
				<a	href="javascript:;" class="easyui-linkbutton" plain="true"		iconCls="receive-task-icon" wfModel="ReceiveTask">接收任务</a> <br/>
				<%--<a href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="business-rule-task-icon" wfModel="BusinessRuleTask">业务规则</a><br/> 
				<a href="javascript:;" class="easyui-linkbutton" plain="true"	iconCls="subprocess-icon">子流程</a><br/>
			
				<a href="javascript:;"	class="easyui-linkbutton" plain="true" iconCls="callactivity-icon"	wfModel="CallActivity">调用活动</a> <br/>
				 --%>
			</div>
			<div id="gateway" title="网关" iconCls="palette-menu-icon"	class="palette-menu">
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="parallel-gateway-icon" wfModel="ParallelGateway">同步</a><br/>
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="exclusive-gateway-icon" wfModel="ExclusiveGateway">分支</a><br/>
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="inclusive-gateway-icon" wfModel="InclusiveGateway">包容</a><br/>
			</div>
			<div id="boundary-event" title="边界事件" iconCls="palette-menu-icon"	class="palette-menu">
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="timer-boundary-event-icon" wfModel="TimerBoundary">定时器</a>	<br/> 
				<%--<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="error-boundary-event-icon" wfModel="ErrorBoundary">错误边界</a>	<br/>--%>
			</div>
	
			
			<div id="boundary-event" title="中间事件" iconCls="palette-menu-icon"	class="palette-menu">
				<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="timer-boundary-event-icon" wfModel="TimerIntermediate">定时器</a>	<br/> 
				<%--<a href="javascript:;" class="easyui-linkbutton" plain="true" 	iconCls="error-boundary-event-icon" wfModel="ErrorBoundary">错误边界</a>	<br/>--%>
			</div>
			
		
		
		</div>
	</div>
	<div id="process-panel" region="center" style="padding: 1px"	split="true" iconCls="process-icon" title="${requestScope.flow.flowName }">
		<div id="process-definition-tab">
			<div id="designer-area" title="设计" 	style="POSITION: absolute; width: 100%; height: 100%; padding: 0; border: none; overflow: auto;">
				<div id="paintarea"	style="POSITION: absolute; WIDTH: 2000px; HEIGHT: 2000px"></div>
			</div>
			<div id="xml-area" title="源码"	style="width: 100%; height: 100%; overflow: hidden; overflow-x: hidden; overflow-y: hidden;">
				<textarea id="descriptorarea" rows="38"	style="width: 100%; height: 100%; padding: 0; border: none; font-size: 12px"	readonly="readonly"></textarea>
			</div>
		</div>
	</div>
	
	
	<div region="east" split="true" iconCls="properties-icon" 	style="padding:1px;width: 300px;">
			<div id="properties-panel" fit="true"  class="easyui-panel" title="流程属性" border="false">
			
			</div>
		
	</div>
	
	<input type="hidden" id="m_workflowId"  value="${requestScope.flow.id }"/>
	<input type="hidden" id="m_flowId"  value="${requestScope.flow.flowId }"/>
	<input type="hidden" id="m_flowType"  value="${requestScope.flow.flowType }"/>
	<input type="hidden" id="m_flowName"  value="${requestScope.flow.flowName }"/>
	<input type="hidden" id="m_flowDesc"  value="${requestScope.flow.flowDesc }"/>
	<c:if test="${requestScope.flow.flowXml!=null&&requestScope.flow.flowXml!='' }">
		<input type="hidden" id="m_flowXml"  value="true"/>
	</c:if>
	
</body>
</html>
