<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--手工任务 --%>

<div class="easyui-layout" fit="true">
	<div  region="north" border="false"	style="height:30px;background:#E1F0F2;">
		<a href="javascript:;"  class="easyui-linkbutton" plain="true"	iconCls="icon-save" onclick="saveTaskProperties()">保存</a>
	</div>
	<div  region="center" border="true">
		<div  class="easyui-accordion"	fit="true" border="false">
			<div  title="手工任务" selected="true" class="properties-menu">
				<table >
					<tr>
						<td align="right">id:</td>
						<td><span id="manualTaskId" ></span>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="manualTaskName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation" name="documentation" class="textdesc"	></textarea></td>
					</tr>
					
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.手工任务是直接通过的任务， 流程到达它之后会自动向下执行。</div>
									
				</fieldset>
			</div>
			
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var task = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
		
		$('#manualTaskId').text(task.taskId);
		$('#manualTaskName').val(task.taskName);
		$('#documentation').val(task.documentation);
	});
	function saveTaskProperties() {
		
		task.taskName = $('#manualTaskName').val().trim();
		task.setContent(task.taskName);
		
		task.documentation = $('#documentation').val().trim();
		
		
	}
	
	
//-->
</script>
