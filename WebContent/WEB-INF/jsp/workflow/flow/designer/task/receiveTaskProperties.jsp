<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%--接收任务--%>
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
						<td><span id="receiveTaskId" ></span>
					</tr>
					<tr>
						<td align="right">名称:</td>
						<td><input type="text" id="receiveTaskName" /></td>
					</tr>
					<tr>
						<td align="right">描述:</td>
						<td><textarea id="documentation" name="documentation" class="textdesc"	></textarea></td>
					</tr>
					
				</table>
				<fieldset style="line-height: 21px;">
						<legend>说明</legend>
						<div>1.接收任务是一个简单任务，它会等待对应消息的到达。 当前，我们只实现了这个任务的java语义。 当流程达到接收任务，流程状态会保存到存储里。 意味着流程会等待在这个等待状态， 直到引擎接收了一个特定的消息， 这会触发流程穿过接收任务继续执行。</div>
									
				</fieldset>
			</div>
			
			
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
	var task = workflow.getFigure('<%=request.getParameter("nodeId") %>');
	$(function(){
		
		$('#receiveTaskId').text(task.taskId);
		$('#receiveTaskName').val(task.taskName);
		
		$('#documentation').val(task.documentation);
	});

	function saveTaskProperties() {
		
		task.taskName = $('#receiveTaskName').val().trim();
		task.setContent(task.taskName);
		
		task.documentation = $('#documentation').val().trim();
		
		
	}
//-->
</script>

