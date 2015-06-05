<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"	+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
%>
<%--
	模块：工作流程--待办任务，办理任务
--%>
<c:choose>
		<c:when test="${empty requestScope.task  }">
				
				<div style="color: red;width: 200px;margin: 40px auto;font-weight: bold;">此任务已处理完成！</div>	
				
		</c:when>
		
		<c:otherwise>
		

<!DOCTYPE html>
<html>
<head>
	

	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>工作流程查看</title>
	<link href="favicon.ico" rel="SHORTCUT ICON" />
	<link href="resource/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="resource/js/ui/css/ui.css" rel="stylesheet" media="screen">
	
	<script type="text/javascript" src="resource/js/jquery/jquery-1.10.2.min.js"></script>
	
	<%-- easyui  --%>
	<link href="resource/js/easyui/themes/icon.css"  rel="stylesheet" type="text/css" media="screen" />
	<link id="easyuiTheme" href="resource/js/easyui/themes/default/easyui.css"  rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript" src="resource/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="resource/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="resource/js/android/easyui.defaults.js"></script>
	<script type="text/javascript" src="resource/js/ui/easyui.validate.js"></script>
	<script type="text/javascript" src="resource/js/jsObject.Expand.js"></script>
	
	<script src="resource/js/ui/ui.core.js" type="text/javascript"></script>
	
	<!-- 流程表单插件 -->
	<script src="resource/js/flowForm_plugin.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="resource/js/ui/ui.database.js"></script>
	
	<script type="text/javascript" src="resource/js/ui/ui.msg.js"></script>
	<script type="text/javascript" src="resource/js/android/ui.ajax.js"></script>
	<script type="text/javascript" src="resource/js/android/ui.util.js"></script>
	
	<!-- My97DatePicke -->
	<script type="text/javascript" src="resource/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
	<%--json --%>
	<script src="resource/js/json/json2.js"></script>
	<%--查询过滤规则 --%>
	<script src="resource/js/ui/ui.queryFilter.js"></script>
	
	
	<style type="text/css">
		body {
			
			
			
		}
		
		/* 流程表单样式   */
		table.flowtable{
			width: 600px;
			border-collapse:collapse;
			margin: 0 auto 10px;
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
			width: 90%;
			height: 30px;
		}
		table.flowtable input{
			width: 90%;
		}
		/* fieldset */
		fieldset.fieldset{padding:3px; margin:5px; border:1px dotted #B8D0D6;}
		fieldset.fieldset legend {font-size:12px ;width:auto;margin-bottom:0;line-height:normal;   padding:2px; border:1px dotted #B8D0D6; font-weight: bold;}
		fieldset.fieldset legend:hover {background-color: #dddddd;}
		
		/* 删除按钮 */
		.btnDel{
			background:url(./resource/images/small/Delete_16x16.png) no-repeat; 
			display:block; 
			width:22px;
			height:20px; 
			margin-top: 5px;
			margin-left: 5px;
			
		}
	
	</style>
	
</head>
<body >


<div id="div_main" style="width: 98%;margin: 0 auto;" >
				
	<form method="post" action="${requestScope.completeUrl }" enctype="multipart/form-data"  onsubmit="return validateSubmitForm(this)">
							
		<div class="workflowform" >
			
			<c:if test="${!empty requestScope.flowFormHtml }">
				<fieldset class="fieldset">
					<legend>工作表单</legend>
					<div   flowForm="flow"  formType="editor"  taskId="${requestScope.task.taskDefinitionKey }" ><my:scriptEscape value="${requestScope.flowFormHtml }"/></div>
				 </fieldset>
			 </c:if> 
			 
			 <c:if test="${!empty requestScope.taskFormHtml }">
				<fieldset class="fieldset">
					<legend>任务表单</legend>
					<div  flowForm="task"  formType="editor">${requestScope.taskFormHtml }</div>
				</fieldset>
			</c:if>	
			
			<c:if test="${requestScope.backActivityId!=null }">
			<fieldset id="task_com_fieldset" class="fieldset">
				<legend>移交下一步</legend>	
						
					<div style="height:30px;;padding-left: 60px;">
						下一步：
							
						<label class="radioButton" ><input type="radio" name="nextType" value="1" checked="checked" onclick="changeFlowType(this)" />正常流转(按流程设计)</label>
									
						<label class="radioButton"><input type="radio" name="nextType" value="2" onclick="changeFlowType(this)" />驳回至</label>
						<select  name="activityId" >
							<option value="">--请选择任务名称--</option>
							<c:forEach var="m" items="${requestScope.backActivityId }">
								
								<option value="${m.id }">${m.name }</option>
								
							</c:forEach>
						</select>
								
					</div>
						
			</fieldset>
			</c:if>
			<fieldset class="fieldset">
				<legend>任务附件</legend>
				<table class="table table-bordered itemDetail" addButton="添加任务附件"  style="width:99%;margin-top: 0;"
						template='
							<tr>
					 			<td style="text-align: center;"><div class="itemDetail-rownumber">1</div></td>
								<td ><input type="file"   name="file[#index#]"  /></td>
								<td style="text-align: center;"><a href="javascript:;" class="btnDel"  title="删除"></a></td>	
							</tr>
						'>
						<thead>
							<tr>
								 <th width="35" style="text-align: center;">序号</th>
								 <th style="text-align: center;">附件</th>
							 	 <th width="35" style="text-align: center;">删除</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				
			</fieldset>
		
				
				
				
		</div>
		
		<c:if test="${!empty param.rel }">
			<input type="hidden" name="datagridId" value="${param.rel }_datagrid" />
		</c:if>
   		<input type="hidden" name="currentCallback" value="close" />
		
		<input type="hidden" name="taskId" value="${param.taskId}"/>
		<input type="hidden" name="nextType" value="1"/>
		<input type="hidden" name="flowFormHtml" />
		<input type="hidden" name="taskFormHtml" />
		<input type="hidden" name="processVariablesKeys">
		
		
		
		
		<div  style="width: 100px;margin: 10px auto;" >
			  <button type="submit" class="btn btn-primary" onclick="$.onFlowFormSubmit(this)">提交</button>
		</div>
			
		 
		
	</form>
	
</div>

<input type="hidden" id="myUserId" value="<my:outLoginInfo type="userId"/>" />
<input type="hidden" id="myTrueName" value="<my:outLoginInfo type="trueName"/>" />
<input type="hidden" id="myDeptId" value="<my:outLoginInfo type="deptId"/>" />
<input type="hidden" id="myDeptName" value="<my:outLoginInfo type="deptName"/>" />

<script type="text/javascript">

	//获取一些基本用户信息
	loginUserId=$("#myUserId").val();
	loginName=$("#myTrueName").val();
	loginDeptId=$("#myDeptId").val();
	loginDeptName=$("#myDeptName").val();

	//改变跳转状态
	function changeFlowType(ipt){
		var $this=$(ipt);
		
		var $fieldset=$this.closest("#task_com_fieldset");
	    
		if($this.val()==1){ 
		    $("select[name='activityId']",$fieldset).removeClass("required");
	    }else{
	    	$("select[name='activityId']",$fieldset).addClass("required");
	    }
	    
	}

</script>
</body>
</html>

</c:otherwise>
</c:choose>

									